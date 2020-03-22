package com.example.apiseemore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apiseemore.R;
import com.example.apiseemore.model.peopleLiked.Datum;

import java.util.List;

public class PeopleLikedAdapter extends RecyclerView.Adapter<PeopleLikedAdapter.PeopleLikedViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Datum> listPeopleLiked;

    public PeopleLikedAdapter(Context context, List<Datum> listPeopleLiked) {
        layoutInflater = LayoutInflater.from(context);
        this.listPeopleLiked = listPeopleLiked;
    }

    @NonNull
    @Override
    public PeopleLikedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_people_liked, parent, false);
        return new PeopleLikedViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PeopleLikedViewHolder holder, int position) {
        Datum peopleLiked = listPeopleLiked.get(position);
        Glide.with(layoutInflater.getContext())
                .load(peopleLiked.getAvatar())
                .into(holder.imgPeopleLiked);
        holder.txtNamePeopleLiked.setText(peopleLiked.getFirstname()+" "+peopleLiked.getLastname());
    }

    @Override
    public int getItemCount() {
        return listPeopleLiked.size();
    }

    class PeopleLikedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPeopleLiked;
        TextView txtNamePeopleLiked;

        public PeopleLikedViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPeopleLiked = itemView.findViewById(R.id.imgPeopleLiked);
            txtNamePeopleLiked = itemView.findViewById(R.id.txtNamePeopleLiked);
        }
    }
}
