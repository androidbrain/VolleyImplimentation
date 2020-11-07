package com.example.vollyexam2909;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vollyexam2909.order.OrderActivity;
import com.example.vollyexam2909.pack.FirstActivity;
import com.example.vollyexam2909.pack.FourthActivity;
import com.example.vollyexam2909.pack.SecondActivity;
import com.example.vollyexam2909.pack.ThirdActivity;
import com.example.vollyexam2909.order.ProductsActivity;
import com.example.vollyexam2909.property.PropertyActivity;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void openFirstExample(View view) {
        Intent firstIntent=new Intent(MainActivity.this, FirstActivity.class);
        startActivity(firstIntent);
    }

    public void openSecondExample(View view) {
        Intent secondIntent=new Intent(MainActivity.this, SecondActivity.class);
        startActivity(secondIntent);
    }

    public void openThirdActivity(View view) {
        Intent thirdIntent=new Intent(MainActivity.this, ThirdActivity.class);
        startActivity(thirdIntent);
    }
    public void openFourthActivity(View view) {
        Intent fourthIntent=new Intent(MainActivity.this, FourthActivity.class);
        startActivity(fourthIntent);
    }

    public void openProductsActivity(View view) {
        Intent productsIntent=new Intent(MainActivity.this, ProductsActivity.class);
        startActivity(productsIntent);
    }

    public void nextActivity(View view) {
        Intent propertyIntent=new Intent(MainActivity.this, PropertyActivity.class);
        startActivity(propertyIntent);
    }

    public void gonext(View view) {
        Intent orderIntent=new Intent(MainActivity.this, OrderActivity.class);
        startActivity(orderIntent);
    }
}