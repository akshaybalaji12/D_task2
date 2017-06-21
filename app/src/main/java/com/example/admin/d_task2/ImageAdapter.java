package com.example.admin.d_task2;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>{
    private List<ImageList> imageList;
    int mLastPosition = 0;




    public ImageAdapter(List<ImageList> imageList) {

        this.imageList = imageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("onBindViewHolder", imageList.size() + "");
        holder.caption.setText(imageList.get(position).getCaption());
        holder.img.setImageBitmap(imageList.get(position).getImg());
        mLastPosition= position;
    }

    @Override
    public int getItemCount() {
        return (null != imageList?imageList.size():0);
    }

    public void notifyData(List<ImageList> imageList){
        Log.d("notifyData", imageList.size() + "");
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView caption;
        public ImageView img;
        public MyViewHolder(View view){
            super(view);
            caption = (TextView) view.findViewById(R.id.textView);
            img = (ImageView) view.findViewById(R.id.imageView);
        }
    }


}