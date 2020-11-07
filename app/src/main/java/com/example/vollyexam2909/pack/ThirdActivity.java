package com.example.vollyexam2909.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vollyexam2909.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    private AutoCompleteTextView completeTextView;
    private String URL = "http://okayindia.co.in/wotma/api/customer/city";
    private JSONObject jsonBody = new JSONObject( "{\"stateId\":\"28\"}");

    private RequestQueue requestQueue;

    private static final ArrayList<String> STATES=new ArrayList<String>();

    public ThirdActivity() throws JSONException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        completeTextView=findViewById(R.id.autoCompleteTextView);



        requestQueue = Volley.newRequestQueue(this);
        jsonRequest();

    }

    public void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    Log.d("MyApp", "Array Size  " + jsonArray.length());

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        STATES.add(jsonObject.getString("name"));

                    }
                    Log.d("MyApp", String.valueOf(STATES.get(3)));
                    ArrayAdapter<String> adapter =new ArrayAdapter<>(ThirdActivity.this, android.R.layout.simple_list_item_1, STATES);
                    completeTextView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "***Error***  " + error.getMessage());
                Toast.makeText(ThirdActivity.this, "Error :" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}