package com.example.vollyexam2909.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class ProductsActivity extends AppCompatActivity {
    private String URL = "https://ozonewallet.in/webapi/gproduct.php";
    private JSONObject jsonBody = new JSONObject( "{\"product_id\":\"4\",\"city_id\":\"1\",\"customer_id\":\"12\"}");
    private RequestQueue requestQueue;

    private TextView productTitle, availability, brand, stockStatus, description, productPrice, optionPrice, orderNow;
    private ImageView thumb;

    public ProductsActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        productTitle=findViewById(R.id.product_title);
        availability=findViewById(R.id.availability);
        brand=findViewById(R.id.brand);
        stockStatus=findViewById(R.id.stock_status);
        description=findViewById(R.id.product_description);
        productPrice=findViewById(R.id.product_price);
        optionPrice=findViewById(R.id.product_origi_price);
        thumb=findViewById(R.id.thumb);
        orderNow=findViewById(R.id.order_now);

        Toolbar toolbar = findViewById(R.id.toolbarset);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductsActivity.this, OrderActivity.class);
                startActivity(intent);

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        jsonRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent intent=new Intent(this, CartActivity.class);
            startActivity(intent);
        }if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.d("MyApp", String.valueOf(response));

                try {
                    String heading_title = response.getJSONObject("product_data").getString("heading_title");
                    String stock_status = response.getJSONObject("product_data").getJSONObject("product_info").getString("stock_status");
                    String stock = response.getJSONObject("product_data").getString("stock");
                    String model = response.getJSONObject("product_data").getString("model");
                    String descriptionTxt = response.getJSONObject("product_data").getString("description");
                    String thumbUrl = response.getJSONObject("product_data").getString("thumb");

                    int price = response.getJSONObject("product_data").getJSONArray("product_option_value_data").getJSONObject(0).getInt("price");
                    int optionPriceTxt = response.getJSONObject("product_data").getJSONArray("product_option_value_data").getJSONObject(0).getInt("option_orig_price");

                    productTitle.setText(heading_title);
                    availability.setText(stock);
                    brand.setText(model);
                   // description.setText(descriptionTxt);
                    stockStatus.setText(stock_status);
                    productPrice.setText(String.valueOf(price));
                    optionPrice.setText(String.valueOf(optionPriceTxt));

                    Glide.with(ProductsActivity.this).load(thumbUrl).apply(new RequestOptions().placeholder(R.drawable.placeholder))
                            .into(thumb);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        description.setText(Html.fromHtml(descriptionTxt, Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        description.setText(Html.fromHtml(descriptionTxt));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "***Error***  " + error.getMessage());
                Toast.makeText(ProductsActivity.this, "Error :" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}