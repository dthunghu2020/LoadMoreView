package com.example.apiseemore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.apiseemore.R;

import java.util.ArrayList;
import java.util.List;

public class DetailPhotosAdapter extends PagerAdapter {

    private List<String> images;
    private LayoutInflater layoutInflater;

    public DetailPhotosAdapter(Context context, List<String> images) {
        layoutInflater = LayoutInflater.from(context);
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_detail_photo, container, false);
        ImageView imgDetailPhoto = view.findViewById(R.id.imgDetailPhoto);
        String image = images.get(position);
        Glide.with(layoutInflater.getContext())
                .load(image)
                .into(imgDetailPhoto);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
