package com.example.vollyexam2909.property;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import static com.example.vollyexam2909.property.PropertyActivity.imagesModelList;

import com.example.vollyexam2909.R;
import com.google.android.material.tabs.TabLayout;

public class ImageViewPagerActivity extends AppCompatActivity {
    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_pager);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);

        int positionNo=getIntent().getIntExtra("Position", -1);


        PagerImagesAdapter productImagesAdapter = new PagerImagesAdapter(imagesModelList);
        productImagesViewPager.setAdapter(productImagesAdapter);
        productImagesViewPager.setCurrentItem(positionNo, true);



        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);
    }
}