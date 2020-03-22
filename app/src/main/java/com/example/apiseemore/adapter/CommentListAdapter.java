package com.example.apiseemore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apiseemore.R;
import com.example.apiseemore.model.comment.Sub;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentListViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Sub> subs;
    private boolean liked = false;

    public CommentListAdapter(Context context, List<Sub> subs) {
        layoutInflater = LayoutInflater.from(context);
        this.subs = subs;
    }

    @NonNull
    @Override
    public CommentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list_in_comment, parent, false);
        return new CommentListViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull final CommentListViewHolder holder, int position) {
        Sub sub = subs.get(position);
        Glide.with(layoutInflater.getContext())
                .load(sub.getPicture())
                .into(holder.imgAvatarListComment);
        holder.txtUserNameListComment.setText(sub.getFirstname()+" "+sub.getLastname());
        holder.txtTextListComment.setText(sub.getContent());
    }

    @Override
    public int getItemCount() {
        return subs.size();
    }

    class CommentListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatarListComment;
        TextView txtUserNameListComment;
        TextView txtTextListComment;

        public CommentListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarListComment = itemView.findViewById(R.id.imgAvatarListComment);
            txtUserNameListComment = itemView.findViewById(R.id.txtUserNameListComment);
            txtTextListComment = itemView.findViewById(R.id.txtTextListComment);
        }
    }
}
