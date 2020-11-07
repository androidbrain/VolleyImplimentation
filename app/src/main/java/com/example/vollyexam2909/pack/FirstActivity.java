package com.example.vollyexam2909.pack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String URL = "http://wowtaxi.co.in/api/coupon/couponlist";
    private List<ArrayModel> arrayModelsList=new ArrayList<>();;
    private RecyclerView recyclerView;
    private Array1Adapter array1Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

      recyclerView=findViewById(R.id.recyclerView);

      requestQueue = Volley.newRequestQueue(this);
        jsonRequest();




    }

    public void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FirstActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);

                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    Log.d("MyApp", "Array Size  " + jsonArray.length());

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String couponcode=jsonObject.getString("couponcode");
                        String validto=jsonObject.getString("validto");
                        String tripmode=jsonObject.getString("tripmode");
                        String discount=jsonObject.getString("cpnvalue");
                        String minAmount=jsonObject.getString("minamount");
                        String maxDiscount=jsonObject.getString("maxdiscount");
                        arrayModelsList.add(new ArrayModel(couponcode, validto, tripmode,discount, minAmount, maxDiscount ));

                    }

                    array1Adapter =new Array1Adapter(arrayModelsList);
                    recyclerView.setAdapter(array1Adapter);
                    array1Adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "***Error***  " + error.getMessage());
                Toast.makeText(FirstActivity.this, "Error :" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}