package com.example.vollyexam2909.property;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PropertyActivity extends AppCompatActivity {

    private String URL = "http://skypropertys.com/api/rentproperty_detail";
    private JSONObject jsonBody = new JSONObject( "{  \"property_id\": \"167\",\"user_id\": \"79\"}");
    private RequestQueue requestQueue;

    private ImageView propertyImageMain, ownerPhoto;
    private TextView propertyTitle, propertyLocation, pPrice, pLength, pWidth, pFacing, pFloorNo, pParking, pBathroom, pPropertyType, moreImage;
    private  TextView ownerName, ownerPhoneNo, ownerMail, ownerAddress;

    public static List<MoreImagesModel> imagesModelList;


    public PropertyActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        propertyImageMain=findViewById(R.id.propertyImageMain);
        propertyTitle=findViewById(R.id.title);
        propertyLocation=findViewById(R.id.location);
        pPrice=findViewById(R.id.price);
        pLength=findViewById(R.id.length);
        pWidth=findViewById(R.id.width);
        pFacing=findViewById(R.id.facing);
        pFloorNo=findViewById(R.id.floorNo);
        pParking=findViewById(R.id.parking);
        pBathroom=findViewById(R.id.bathroom);
        pPropertyType=findViewById(R.id.property_type);
        moreImage=findViewById(R.id.more_image);


        ownerName=findViewById(R.id.owner_name);
        ownerPhoneNo=findViewById(R.id.owner_phone);
        ownerMail=findViewById(R.id.owner_mail);
        ownerAddress=findViewById(R.id.owner_address);
        ownerPhoto=findViewById(R.id.owner_image);

        requestQueue = Volley.newRequestQueue(this);
        jsonRequest();
    }


    public void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.d("MyApp", String.valueOf(response));

                try {
                   // String propertyImageMainUrl = response.getJSONObject("data").getString("image");
                    final String propertyImageMainUrl = response.getJSONArray("data").getJSONObject(0).getString("image");
                    String propertyTitleTxt = response.getJSONArray("data").getJSONObject(0).getString("title");
                    String propertyAddress = response.getJSONArray("data").getJSONObject(0).getString("address");
                    String propertyCity = response.getJSONArray("data").getJSONObject(0).getString("city");
                    String propertyState = response.getJSONArray("data").getJSONObject(0).getString("state");
                    String propertyPrice = response.getJSONArray("data").getJSONObject(0).getString("price");
                    String propertyLength = response.getJSONArray("data").getJSONObject(0).getString("length");
                    String propertyWidth = response.getJSONArray("data").getJSONObject(0).getString("width");
                    String propertyFacing = response.getJSONArray("data").getJSONObject(0).getString("facing");
                    String propertyFloorNo = response.getJSONArray("data").getJSONObject(0).getString("floor_no");
                    String propertyParking = response.getJSONArray("data").getJSONObject(0).getString("parking");
                    String propertyBathroom = response.getJSONArray("data").getJSONObject(0).getString("bathrooms");
                    String propertyType = response.getJSONArray("data").getJSONObject(0).getString("property_type");
                    String ownerNameTxt = response.getJSONArray("data").getJSONObject(0).getString("owner_name");
                    String ownerMailTxt = response.getJSONArray("data").getJSONObject(0).getString("owner_email");
                    String ownerPhoneNumber = response.getJSONArray("data").getJSONObject(0).getString("owner_mobile");
                    String ownerAddressTxt = response.getJSONArray("data").getJSONObject(0).getString("owner_address");
                    final String ownerImageUrl = response.getJSONArray("data").getJSONObject(0).getString("owner_image");
                    Glide.with(PropertyActivity.this).load(propertyImageMainUrl).apply(new RequestOptions().placeholder(R.drawable.placeholder))
                            .into(propertyImageMain);
                    propertyImageMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(PropertyActivity.this, OpenImageActivity.class);
                            intent.putExtra("Image",propertyImageMainUrl );
                            startActivity(intent);
                        }
                    });

                    JSONArray jsonArray=response.getJSONArray("data").getJSONObject(0).getJSONArray("property_images");
                    imagesModelList=new ArrayList<>();
                    for (int i=0;i<jsonArray.length() ;i++){
                        String imageLink=jsonArray.getJSONObject(i).getString("image");
                        String imageTitle=jsonArray.getJSONObject(i).getString("title");
                        imagesModelList.add(new MoreImagesModel(imageLink, imageTitle));
                      //  Log.d("MyApp", imageTitle);
                    }

                    if(imagesModelList.size()!=0) {
                        moreImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PropertyActivity.this, MoreImagesActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    propertyTitle.setText(propertyTitleTxt);
                    propertyLocation.setText(propertyAddress+", "+propertyCity+", "+propertyState);
                    pPrice.setText("₹ "+propertyPrice);

                    pPropertyType.setText(propertyType);
                    pLength.setText(propertyLength);
                    pWidth.setText(propertyWidth);
                    pFacing.setText(propertyFacing);
                    pFloorNo.setText(propertyFloorNo);
                    pBathroom.setText(propertyBathroom);
                    pParking.setText(propertyParking);

                    ownerName.setText(ownerNameTxt);
                    ownerPhoneNo.setText(ownerPhoneNumber);
                    ownerMail.setText(ownerMailTxt);
                    ownerAddress.setText(ownerAddressTxt);

                    Glide.with(PropertyActivity.this).load(ownerImageUrl).apply(new RequestOptions().placeholder(R.drawable.placeholder))
                            .into(ownerPhoto);
                    ownerPhoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //ownerImage(ownerImageUrl);
                            final Dialog dialog=new Dialog(PropertyActivity.this);
                            dialog.setContentView(R.layout.owner_photo_dialog_layout);
                            dialog.setCancelable(true);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            ImageView ownerImgDialog=dialog.findViewById(R.id.ownerImgDialog);

                            Glide.with(getApplicationContext()).load(ownerImageUrl).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(ownerImgDialog);


                            dialog.show();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "***Error***  " + error.getMessage());
                Toast.makeText(PropertyActivity.this, "Error :" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }



}