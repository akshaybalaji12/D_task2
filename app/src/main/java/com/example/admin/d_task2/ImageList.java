package com.example.admin.d_task2;


import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

public class ImageList {
     String caption;
     Bitmap img;

     RecyclerView data;

    public void setCaption(String caption){
        this.caption= caption;
    }

    public void setImg(Bitmap img){
        this.img= img;
    }

    public String getCaption(){

        return caption;
    }

    public Bitmap getImg(){
        return img;
    }
}
