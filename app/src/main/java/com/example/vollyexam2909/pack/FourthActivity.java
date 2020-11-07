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

public class FourthActivity extends AppCompatActivity {

    private String URL = "http://wowtaxi.co.in/api/sos/Sos_notification";
    private JSONObject jsonBody = new JSONObject( "{\"dts\":{\"id\":\"4088\"}}");
    private RequestQueue requestQueue;
    private List<FourthModel> fourthModelList=new ArrayList<>();;
    private RecyclerView recyclerView;
    private FourthAdapter fourthAdapter;

    public FourthActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        recyclerView=findViewById(R.id.recyclerViewid);

        requestQueue = Volley.newRequestQueue(this);
        jsonRequest();

    }
    public void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            // Log.d("MyApp", String.valueOf(response));
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FourthActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    Log.d("MyApp", "Array Size  " + jsonArray.length());

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String mobile=jsonObject.getString("mobileno");
                        String emrtitle=jsonObject.getString("title");
                        String body=jsonObject.getString("text");
                        String imageurl=jsonObject.getString("imageurl");

                        fourthModelList.add(new FourthModel(id, mobile, emrtitle,body, imageurl ));

                    }


                    fourthAdapter =new FourthAdapter(fourthModelList);
                    recyclerView.setAdapter(fourthAdapter);
                    fourthAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "***Error***  " + error.getMessage());
                Toast.makeText(FourthActivity.this, "Error :" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}