package com.example.vollyexam2909.property;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vollyexam2909.R;

public class OpenImageActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView imageView;

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_image);
         imageView=findViewById(R.id.openimage);

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

      /*  matrix.setScale(ratio+15, ratio+15);
        imageView.setImageMatrix(matrix);*/

        String image=getIntent().getStringExtra("Image");

        Glide.with(OpenImageActivity.this).load(image).apply(new RequestOptions().placeholder(R.drawable.placeholder))
                .into(imageView);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mScaleGestureDetector.onTouchEvent(event);
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        // when a scale gesture is detected, use it to resize the image
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 4.0f));

            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}