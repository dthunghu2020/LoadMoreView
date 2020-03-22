package com.example.apiseemore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import com.example.apiseemore.model.commentDetail.Datum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailCommentAdapter extends RecyclerView.Adapter<DetailCommentAdapter.DetailCommentViewHolder>{

    private LayoutInflater layoutInflater;
    private List<Datum> datas;
    private boolean liked = true;

    public DetailCommentAdapter(Context context,List<Datum> datas) {
        layoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @NonNull
    @Override
    public DetailCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.iteam_detail_comment,parent,false);
        return new DetailCommentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final DetailCommentViewHolder holder, int position) {
        Datum data = datas.get(position);
        Glide.with(layoutInflater.getContext())
                .load(data.getPicture())
                .into(holder.imgAvatarDetailComment);
        holder.txtUserNameDC.setText(data.getFirstname()+" "+data.getLastname());
        holder.txtDetailCommentText.setText(data.getContent());
        //time
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = simpleDateFormat.parse(data.getCreateTime());
            assert date != null;
            long timeCreated = date.getTime();
            long checkTime = calendar.getTimeInMillis() - timeCreated;
            if (checkTime < 60000) {
                holder.txtDatetimeDC.setText("Vừa xong");
            } else if (checkTime < 3600000) {
                holder.txtDatetimeDC.setText((checkTime / 60000) + " Phút trước");
            } else if (checkTime < 86400000) {
                holder.txtDatetimeDC.setText((checkTime / 3600000) + " Giờ trước");
            } else if (checkTime < 604800000) {
                holder.txtDatetimeDC.setText((checkTime / 86400000) + " Ngày trước");
            } else
                holder.txtDatetimeDC.setText(format.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(data.getLiked()){
            holder.btnLikeDC.setTextColor(layoutInflater.getContext().getResources().getColor(R.color.colorGreen));
        }else {
            liked = false;
        }
        holder.btnLikeDC.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(liked){
                    holder.btnLikeDC.setTextColor(layoutInflater.getContext().getResources().getColor(R.color.colorDarkGray));
                    liked=false;
                }else{
                    holder.btnLikeDC.setTextColor(layoutInflater.getContext().getResources().getColor(R.color.colorGreen));
                    liked=true;
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class DetailCommentViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgAvatarDetailComment;
        private TextView txtUserNameDC;
        private TextView txtDetailCommentText;
        private TextView txtDatetimeDC;
        private TextView btnLikeDC;

        public DetailCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarDetailComment = itemView.findViewById(R.id.imgAvatarDetailComment);
            txtUserNameDC = itemView.findViewById(R.id.txtUserNameDC);
            txtDetailCommentText = itemView.findViewById(R.id.txtDetailCommentText);
            txtDatetimeDC = itemView.findViewById(R.id.txtDatetimeDC);
            btnLikeDC = itemView.findViewById(R.id.btnLikeDC);
        }
    }
}
