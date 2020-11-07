package com.example.vollyexam2909.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vollyexam2909.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String URL = "http://www.dekho24.com/webservices/customer/customer-contact-details.php";
    private TextView companyName, email, message, officeMobileNo, personalmobileNo, OfficeAddress, permanentAdd;
    private ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        companyName = findViewById(R.id.company_name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);
        officeMobileNo = findViewById(R.id.officeMobileNo);
        personalmobileNo = findViewById(R.id.personalmobileNo);
        OfficeAddress = findViewById(R.id.OfficeAddress);
        permanentAdd = findViewById(R.id.permanentAdd);
        logoImageView = findViewById(R.id.logo);

        requestQueue = Volley.newRequestQueue(this);
        jsonRequest();
    }

    public void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String comapnyNameTxt = response.getJSONObject("data").getString("company");
                    String emailTxt = response.getJSONObject("data").getString("email");
                    String messageTxt = response.getString("message");
                    String officeMobileNoTxt = response.getJSONObject("data").getString("officeMobileNo");
                    String personalMobileNoTxt = response.getJSONObject("data").getString("personalmobileNo");
                    String officeAddressTxt = response.getJSONObject("data").getString("OfficeAddress");
                    String permanentAddressTxt = response.getJSONObject("data").getString("permanentAdd");
                    String logoUrl = response.getJSONObject("data").getString("logo");
                    companyName.setText("Company Name : " + comapnyNameTxt);
                    email.setText("Email : " + emailTxt);
                    message.setText("Message : " + messageTxt);
                    officeMobileNo.setText("Office Mobile No : " + officeMobileNoTxt);
                    personalmobileNo.setText("Personal Mobile No : " + personalMobileNoTxt);
                    OfficeAddress.setText("Office Address : " + officeAddressTxt);
                    permanentAdd.setText("Permanent Address : " + permanentAddressTxt+"\n\n\n"+"Image Link : "+logoUrl);
                    Glide.with(SecondActivity.this).load(logoUrl).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                            .into(logoImageView);

                    Log.d("MyApp", logoUrl);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "***Error***  " + error.getMessage());

            }
        });
        requestQueue.add(jsonObjectRequest);


    }
}