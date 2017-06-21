package com.example.admin.d_task2;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.example.admin.d_task2.R.id.imageView;

public class MainActivity extends Activity {
    List<ImageList> imageList = new ArrayList<>();
    private RecyclerView mRecycler;
    private LinearLayoutManager mLinear;
    private ImageAdapter mAdapter;
    Bitmap img;
    String caption="HELLO";
    public final static int PICK_PHOTO_CODE = 1046;
    private static final int CAMERA_REQUEST = 1888;
    FloatingActionButton gallery;
    FloatingActionButton cam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mRecycler = (RecyclerView) findViewById(R.id.list);
        mLinear = new LinearLayoutManager(getApplicationContext());
        mRecycler.setLayoutManager(mLinear);
        mAdapter = new ImageAdapter(imageList);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       gallery = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        cam = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_PHOTO_CODE)
        {if (data != null) {
                Uri photoUri = data.getData();
                img = null;
                try {
                    img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageList iL = new ImageList();
                iL.setCaption(caption);
                iL.setImg(img);
                imageList.add(iL);
                mAdapter.notifyData(imageList);
            }
    }

        else  if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img=photo;
            ImageList iL = new ImageList();
            iL.setImg(img);
            iL.setCaption(caption);
            imageList.add(iL);
            mAdapter.notifyData(imageList);
        }
    }
}
