package com.example.apiseemore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apiseemore.R;
import com.example.apiseemore.model.comment.Datum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CommentListAdapter commentListAdapter;

    private CommetAdapter.OnDCItemClickListener onDCItemClickListener;
    private final int VIEW_TYPE_COMMENT = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isOutOfData = false;
    private Boolean liked = true;

    private LayoutInflater layoutInflater;
    private List<Datum> commentDatas;

    public CommetAdapter(Context context, List<Datum> commentDatas) {
        this.commentDatas = commentDatas;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setOutOfData(boolean outOfData) {
        isOutOfData = outOfData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_COMMENT) {
            View view = layoutInflater.inflate(R.layout.item_comment, parent, false);
            return new CommetAdapter.CommentViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.item_progressbar, parent, false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentViewHolder) {
            commentView((CommentViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView();
        }
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    private void commentView(final CommentViewHolder holder, int position) {
        Datum comment = commentDatas.get(position);
        Glide.with(layoutInflater.getContext())
                .load(comment.getPicture())
                .into(holder.imgAvatarComment);

        holder.txtUserNameComment.setText(comment.getFirstname() + " " + comment.getLastname());
        holder.txtCommentText.setText(comment.getContent());
        //Date
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = simpleDateFormat.parse(comment.getCreateTime());
            assert date != null;
            long timeCreated = date.getTime();
            long checkTime = calendar.getTimeInMillis() - timeCreated;
            if (checkTime < 60000) {
                holder.txtDatetimeComment.setText("Vừa xong");
            } else if (checkTime < 3600000) {
                holder.txtDatetimeComment.setText((checkTime / 60000) + " Phút trước");
            } else if (checkTime < 86400000) {
                holder.txtDatetimeComment.setText((checkTime / 3600000) + " Giờ trước");
            } else if (checkTime < 604800000) {
                holder.txtDatetimeComment.setText((checkTime / 86400000) + " Ngày trước");
            } else
                holder.txtDatetimeComment.setText(format.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Like và Comment Button

        if (comment.getLiked()) {
            holder.btnLikeInComment.setTextColor(layoutInflater.getContext().getResources().getColor(R.color.colorGreen));
        } else {
            liked = false;
        }
        holder.btnLikeInComment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(liked){
                    holder.btnLikeInComment.setTextColor(layoutInflater.getContext().getResources().getColor(R.color.colorDarkGray));
                    liked = false;
                }else{
                    holder.btnLikeInComment.setTextColor(layoutInflater.getContext().getResources().getColor(R.color.colorGreen));
                    liked=true;
                }

            }
        });

        holder.btnCommentInComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDCItemClickListener.OnItemClicked(holder.getAdapterPosition());
            }
        });

        //Xem thêm
        if (comment.getTotalSub() < 4) {
            holder.btnSeeMoreComment.setVisibility(View.GONE);
        } else {
            holder.btnSeeMoreComment.setText("Xem thêm " + (comment.getTotalSub() - 3) + " trả lời");
            holder.btnSeeMoreComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDCItemClickListener.OnItemClicked(holder.getAdapterPosition());
                }
            });
        }

        //List Comment
        commentListAdapter = new CommentListAdapter(layoutInflater.getContext(), comment.getSubs());
        holder.rcvListInComment.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext()));
        holder.rcvListInComment.setAdapter(commentListAdapter);
    }

    @Override
    public int getItemCount() {
        if (commentDatas != null) {
            return commentDatas.size();
        } else {
            return 0;
        }
    }

    //Check view type
    @Override
    public int getItemViewType(int position) {
        if (commentDatas.get(position) == null && !isOutOfData) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_COMMENT;
        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBtnCMLike;
        private TextView txtCMLike;
        private ImageView imgBtnDown;
        private ImageView imgAvatarComment;
        private TextView txtUserNameComment;
        private TextView txtCommentText;
        private TextView txtDatetimeComment;
        private TextView btnLikeInComment;
        private TextView btnCommentInComment;
        private TextView btnSeeMoreComment;
        private RecyclerView rcvListInComment;
        private EditText edtCMComment;
        private ImageView imgBtnCMSendComment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBtnCMLike = itemView.findViewById(R.id.imgBtnCMLike);
            txtCMLike = itemView.findViewById(R.id.txtCMLike);
            imgBtnDown = itemView.findViewById(R.id.imgBtnDown);
            imgAvatarComment = itemView.findViewById(R.id.imgAvatarComment);
            txtUserNameComment = itemView.findViewById(R.id.txtUserNameComment);
            txtCommentText = itemView.findViewById(R.id.txtCommentText);
            txtDatetimeComment = itemView.findViewById(R.id.txtDatetimeComment);
            btnLikeInComment = itemView.findViewById(R.id.btnLikeInComment);
            btnCommentInComment = itemView.findViewById(R.id.btnCommentInComment);
            btnSeeMoreComment = itemView.findViewById(R.id.btnSeeMoreComment);
            rcvListInComment = itemView.findViewById(R.id.rcvListInComment);
            edtCMComment = itemView.findViewById(R.id.edtCMComment);
            imgBtnCMSendComment = itemView.findViewById(R.id.imgBtnCMSendComment);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView() {
    }

    public void setOnItemClickListener(OnDCItemClickListener onDCItemClickListener) {
        this.onDCItemClickListener = onDCItemClickListener;
    }

    public interface OnDCItemClickListener {
        void OnItemClicked(int position);
    }
}
