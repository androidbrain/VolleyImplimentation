package com.example.vollyexam2909.property;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import com.example.vollyexam2909.R;

import org.json.JSONException;

import static com.example.vollyexam2909.property.PropertyActivity.imagesModelList;


public class MoreImagesActivity extends AppCompatActivity {


    private RecyclerView imageRecy;



    public MoreImagesActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_images);
        imageRecy=findViewById(R.id.imageRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MoreImagesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        imageRecy.setLayoutManager(linearLayoutManager);

        MoreImageAdapter moreImageAdapter=new MoreImageAdapter(imagesModelList);
        imageRecy.setAdapter(moreImageAdapter);
        moreImageAdapter.notifyDataSetChanged();



    }



}