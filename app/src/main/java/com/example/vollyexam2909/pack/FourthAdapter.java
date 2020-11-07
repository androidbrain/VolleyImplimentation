package com.example.vollyexam2909.pack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vollyexam2909.R;

import java.util.List;

public class FourthAdapter extends RecyclerView.Adapter<FourthAdapter.MyViewHolder> {

    private List<FourthModel> fourthModelList;

    public FourthAdapter(List<FourthModel> fourthModelList) {
        this.fourthModelList = fourthModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fourth_items_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String id=fourthModelList.get(position).getId();
        String mobile=fourthModelList.get(position).getMobileno();
        String emralert=fourthModelList.get(position).getTitle();
        String body=fourthModelList.get(position).getText();
        String imageurl=fourthModelList.get(position).getImageurl();
        holder.setData(id, mobile, emralert, body, imageurl);

    }

    @Override
    public int getItemCount() {
        return fourthModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView idtv;
        private TextView mobile;
        private TextView emer;
        private TextView body;
        private ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idtv=itemView.findViewById(R.id.id);
            mobile=itemView.findViewById(R.id.mobilenumber);
            emer=itemView.findViewById(R.id.alertMsg);
            body=itemView.findViewById(R.id.body);
            image=itemView.findViewById(R.id.image);

        }
        private void setData(String idtvtxt, String mobiletxt, String emertxt, String bodytxt, String imgUrl){
            idtv.setText("id : "+idtvtxt);
            mobile.setText("Mo : "+mobiletxt);
            emer.setText(emertxt);
            body.setText(bodytxt);
            Glide.with(itemView.getContext()).load(imgUrl).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                    .into(image);
        }
    }
}
