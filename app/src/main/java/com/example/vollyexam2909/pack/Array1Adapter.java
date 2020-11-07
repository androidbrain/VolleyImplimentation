package com.example.vollyexam2909.pack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vollyexam2909.R;

import java.util.List;

public class Array1Adapter extends RecyclerView.Adapter<Array1Adapter.MyViewHolder> {
    private List<ArrayModel> arrayModelsList;

    public Array1Adapter(List<ArrayModel> arrayModelsList) {
        this.arrayModelsList = arrayModelsList;
    }

    public Array1Adapter(ThirdActivity thirdActivity, int simple_list_item_1, String[] states) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layeout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String couponCodeTxt=arrayModelsList.get(position).getCouponCode();
        String validityTxt=arrayModelsList.get(position).getValidTo();
        String tripModeTxt=arrayModelsList.get(position).getTripMode();
        String discountTxt=arrayModelsList.get(position).getDiscount();
        String minAmountTxt=arrayModelsList.get(position).getMinAmount();
        String maxDiscountTxt=arrayModelsList.get(position).getMaxdiscount();
        holder.setData(couponCodeTxt, validityTxt, tripModeTxt, discountTxt, maxDiscountTxt, minAmountTxt);

    }

    @Override
    public int getItemCount() {
        return arrayModelsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView couponCode;
        private TextView validityUpTo;
        private TextView tripMode;
        private TextView discount;
        private TextView maxDiscount;
        private TextView minAmount;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            couponCode=itemView.findViewById(R.id.couponCode);
            validityUpTo=itemView.findViewById(R.id.couponValidity);
            tripMode=itemView.findViewById(R.id.triptype);
            discount=itemView.findViewById(R.id.discount);
            maxDiscount=itemView.findViewById(R.id.maxDiscount);
            minAmount=itemView.findViewById(R.id.minAmount);
        }
        private void setData(String couponCodeTxt, String validityUpTotxt, String tripModetxt, String discounttxt, String maxDiscounttxt, String minAmounttxt){
            couponCode.setText(couponCodeTxt);
            validityUpTo.setText(validityUpTotxt);
            tripMode.setText(tripModetxt);
            discount.setText(""+discounttxt+" %\n\n"+"OFF");
            maxDiscount.setText("₹ "+maxDiscounttxt);
            minAmount.setText("₹ "+minAmounttxt);
        }
    }
}
