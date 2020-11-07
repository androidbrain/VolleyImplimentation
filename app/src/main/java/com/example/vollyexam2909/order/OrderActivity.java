package com.example.vollyexam2909.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class OrderActivity extends AppCompatActivity {
    private String URL = "https://ozonewallet.in/webapi/getOrderInfo.php";

    private JSONObject jsonBody = new JSONObject( "{\n" +
            "\t\"infood\": {\n" +
            "\t\t\"customer_id\": \"6a2feef8ed6a9fe76d6b3f30f02150b4\",\n" +
            "\t\t\"history_type\": \"order\",\n" +
            "\t\t\"order_id\": \"982\"\n" +
            "\t}\n" +
            "}");

    private RequestQueue requestQueue;

    private TextView orderNo, orderDate,itemsAndAmount, productName, brandName, quantity, pPrice, paymentMode, totalOrder, subTotalPrice, orderDoneCancel;
    private ImageView productImage;


    public OrderActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderNo=findViewById(R.id.order_no);
        orderDate=findViewById(R.id.order_date);
        itemsAndAmount=findViewById(R.id.item_and_amount);
        productName=findViewById(R.id.product_name);
        brandName=findViewById(R.id.brand_name);
        quantity=findViewById(R.id.product_quantity);
        pPrice=findViewById(R.id.product_price);
        productImage=findViewById(R.id.product_image);
        paymentMode=findViewById(R.id.payment_mode);
        totalOrder=findViewById(R.id.total_order);
        subTotalPrice=findViewById(R.id.sub_total);

        Toolbar toolbar = findViewById(R.id.toolbarset);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestQueue = Volley.newRequestQueue(this);
        jsonRequest();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("MyApp", String.valueOf(response));
                try {
                    JSONObject obj=response.getJSONObject("data").getJSONObject("order_info");
                    int order_id = obj.getInt("order_id");
                     String date_added = obj.getString("date_added");
                     String payment_method = obj.getString("payment_method");
                    int amount = obj.getInt("total");
                   // String payment_method = response.getJSONObject("data").getJSONObject("order_info").getString("payment_method");
                    String name = obj.getJSONArray("product_info").getJSONObject(0).getString("name");
                    int ordered_qty = obj.getJSONArray("product_info").getJSONObject(0).getInt("ordered_qty");
                    String brand = obj.getJSONArray("product_info").getJSONObject(0).getString("brand");
                    int price = obj.getJSONArray("product_info").getJSONObject(0).getInt("regular_price");
                    String imageUrl = obj.getJSONArray("product_info").getJSONObject(0).getString("image_url");

                    Log.d("MyApp", response+String.valueOf(ordered_qty));
                    orderNo.setText("Order No : "+order_id);
                   orderDate.setText(date_added);
                   itemsAndAmount.setText(ordered_qty+" Items - Amount : ₹ "+String.valueOf(amount)) ;
                   productName.setText(name);
                   brandName.setText(brand);
                   quantity.setText(String.valueOf(ordered_qty));
                   pPrice.setText(String.valueOf(price));
                    Glide.with(OrderActivity.this).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.placeholder))
                            .into(productImage);

                  paymentMode.setText(payment_method);
                    totalOrder.setText(String.valueOf(ordered_qty));
                   subTotalPrice.setText("₹ "+amount);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "***Error***  " + error.getMessage());
                Toast.makeText(OrderActivity.this, "Error :" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}