package com.example.apiseemore.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.apiseemore.KEY;
import com.example.apiseemore.R;
import com.example.apiseemore.adapter.DetailPhotosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class DetailPhotoActivity extends AppCompatActivity {
    private ViewPager viewPagerDetailPhoto;
    private DetailPhotosAdapter detailPhotosAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_photo);
        initView();
        event();
    }


    private void initView() {
        viewPagerDetailPhoto = findViewById(R.id.vpDetailPhoto);
    }


    private void event() {
        Intent intent = getIntent();
        List<String> images = new ArrayList<>();
        String json_image = "{\"image\":" + intent.getStringExtra(KEY.IMAGES) + "}";
        try {

            JSONObject mImagesJsonObject = new JSONObject(json_image);
            JSONArray mImagesJsonArray = mImagesJsonObject.getJSONArray("image");
            for (int i = 0; i < mImagesJsonArray.length(); i++) {
                JSONObject image = mImagesJsonArray.getJSONObject(i);
                images.add(image.getString("url"));
            }
            detailPhotosAdapter = new DetailPhotosAdapter(DetailPhotoActivity.this, images);
            viewPagerDetailPhoto.setAdapter(detailPhotosAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
