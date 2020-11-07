package com.example.vollyexam2909.property;

import android.content.Intent;
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

public class MoreImageAdapter extends RecyclerView.Adapter<MoreImageAdapter.MyViewHolder> {

    private List<MoreImagesModel> moreImagesModelList;

    public MoreImageAdapter(List<MoreImagesModel> moreImagesModelList) {
        this.moreImagesModelList = moreImagesModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.more_image_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imgUrl=moreImagesModelList.get(position).getImgUrl();
        String title=moreImagesModelList.get(position).getTitle();
        holder.setData(imgUrl,title, position);

    }

    @Override
    public int getItemCount() {
        return moreImagesModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView count, imageTitle;
        private ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            count=itemView.findViewById(R.id.imgCount);
            imageTitle=itemView.findViewById(R.id.imageTitle);
            image=itemView.findViewById(R.id.images);
        }
        private void setData(final String urlImage, String title, final int position){
            imageTitle.setText(title);
            count.setText("("+String.valueOf(position+1)+"/"+String.valueOf(moreImagesModelList.size())+")");
            Glide.with(itemView.getContext()).load(urlImage).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(image);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openIntent = new Intent(itemView.getContext(), ImageViewPagerActivity.class);
                    openIntent.putExtra("Position",position );
                   itemView.getContext().startActivity(openIntent);
                }
            });

        }
    }
}
