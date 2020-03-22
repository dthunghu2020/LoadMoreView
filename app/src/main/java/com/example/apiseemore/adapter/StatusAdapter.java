package com.example.apiseemore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apiseemore.KEY;
import com.example.apiseemore.R;
import com.example.apiseemore.model.seemore.Datum;
import com.example.apiseemore.presenter.seemore.ISeeMorePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_STATUS = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isOutOfData;


    private OnItemClickListener onItemClickListener;
    private List<Datum> dataList;
    private LayoutInflater layoutInflater;
    private Boolean liked;
    private int countLike;

    public StatusAdapter(Context context, List<Datum> dataList) {
        layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    public void setOutOfData(boolean outOfData) {
        isOutOfData = outOfData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_STATUS) {
            View view = layoutInflater.inflate(R.layout.item_status, parent, false);
            return new StatusViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.item_progressbar, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StatusViewHolder) {
            statusView((StatusViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView();
        }
    }

    @Override
    public int getItemCount() {
        if (dataList != null) {
            return dataList.size();
        } else {
            return 0;
        }
    }

    //Check view type
    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) == null && !isOutOfData) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_STATUS;
        }
    }


    class StatusViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUserAvatar;
        private TextView txtUserName;
        private TextView txtStatusCreatedDate;
        private TextView rmtvContent;

        private FrameLayout mFLImg;
        private ImageView img1Img;
        private LinearLayout mLL2Img;
        private ImageView imgLL21;
        private ImageView imgLL22;
        private LinearLayout mLL3Img;
        private ImageView imgLL31;
        private ImageView imgLL32;
        private ImageView imgLL33;
        private TextView txtPlusImg;

        private TextView txtNBLike;
        private TextView txtNBComment;
        private LinearLayout btnLike;
        private ImageView imgLike;
        private ImageView imgLiked;
        private LinearLayout btnComment;

        private RecyclerView rcvTextItem;

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUserAvatar = itemView.findViewById(R.id.imgUserAvatar);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtStatusCreatedDate = itemView.findViewById(R.id.txtStatusCreatedDate);
            rmtvContent = itemView.findViewById(R.id.rmtvContent);
            mFLImg = itemView.findViewById(R.id.mFLImg);
            img1Img = itemView.findViewById(R.id.img1Img);
            mLL2Img = itemView.findViewById(R.id.mLL2Img);
            imgLL21 = itemView.findViewById(R.id.imgLL21);
            imgLL22 = itemView.findViewById(R.id.imgLL22);
            mLL3Img = itemView.findViewById(R.id.mLL3Img);
            imgLL31 = itemView.findViewById(R.id.imgLL31);
            imgLL32 = itemView.findViewById(R.id.imgLL32);
            imgLL33 = itemView.findViewById(R.id.imgLL33);
            txtNBLike = itemView.findViewById(R.id.txtNBLike);
            txtPlusImg = itemView.findViewById(R.id.txtPlusImg);
            txtNBComment = itemView.findViewById(R.id.txtNBComment);
            btnLike = itemView.findViewById(R.id.btnLike);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgLiked = itemView.findViewById(R.id.imgLiked);
            btnComment = itemView.findViewById(R.id.btnComment);
            rcvTextItem = itemView.findViewById(R.id.rcvTextItem);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    @SuppressLint("SetTextI18n")
    private void statusView(final StatusViewHolder holder, final int position) {
        final Datum data = dataList.get(position);
        //Avatar
        Glide.with(layoutInflater.getContext())
                .load(data.getUser().getPicture())
                .override(75, 75)
                .into(holder.imgUserAvatar);
        //UserName
        holder.txtUserName.setText(data.getUser().getFirstname() + " " + data.getUser().getLastname());
        //xử lí DateTime
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm a");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = simpleDateFormat.parse(data.getCreatedDate());
            assert date != null;
            long timeCreated = date.getTime();
            long checkTime = calendar.getTimeInMillis() - timeCreated;
            if (checkTime < 60000) {
                holder.txtStatusCreatedDate.setText("Vừa xong");
            } else if (checkTime < 3600000) {
                holder.txtStatusCreatedDate.setText((checkTime / 60000) + " Phút trước");
            } else if (checkTime < 86400000) {
                holder.txtStatusCreatedDate.setText((checkTime / 3600000) + " Giờ trước");
            } else if (checkTime < 604800000) {
                holder.txtStatusCreatedDate.setText((checkTime / 86400000) + " Ngày trước");
            } else
                holder.txtStatusCreatedDate.setText(format.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //ReadMoreTextView
        holder.rmtvContent.setText(data.getContent());
        //Xử lí ảnh (Json Object)
        String json_image = "{\"image\":" + data.getImage() + "}";
        try {
            JSONObject imageJSONObject = new JSONObject(json_image);
            JSONArray imageList = imageJSONObject.getJSONArray("image");
            if (imageList.length() == 0) {
                //no photo
                holder.mFLImg.setVisibility(View.GONE);
            } else if (imageList.length() == 1) {
                //1 photos
                holder.mFLImg.setVisibility(View.VISIBLE);
                holder.img1Img.setVisibility(View.VISIBLE);
                holder.mLL2Img.setVisibility(View.GONE);
                holder.mLL3Img.setVisibility(View.GONE);
                JSONObject image = imageList.getJSONObject(0);
                Glide.with(layoutInflater.getContext())
                        .load(image.getString("url"))
                        .centerCrop()
                        .into(holder.img1Img);
            } else if (imageList.length() == 2) {
                //2 photos
                holder.mFLImg.setVisibility(View.VISIBLE);
                holder.img1Img.setVisibility(View.GONE);
                holder.mLL2Img.setVisibility(View.VISIBLE);
                holder.mLL3Img.setVisibility(View.GONE);
                for (int i = 0; i < imageList.length(); i++) {
                    JSONObject image = imageList.getJSONObject(i);
                    switch (i) {
                        case 0:
                            Glide.with(layoutInflater.getContext())
                                    .load(image.getString("url"))
                                    .centerCrop()
                                    .into(holder.imgLL21);
                        case 1:
                            Glide.with(layoutInflater.getContext())
                                    .load(image.getString("url"))
                                    .centerCrop()
                                    .into(holder.imgLL22);
                    }

                }
            } else if (imageList.length() > 2) {
                // >2 photos
                holder.mFLImg.setVisibility(View.VISIBLE);
                holder.img1Img.setVisibility(View.GONE);
                holder.mLL2Img.setVisibility(View.GONE);
                holder.mLL3Img.setVisibility(View.VISIBLE);
                if (imageList.length() == 3) {
                    holder.txtPlusImg.setVisibility(View.INVISIBLE);
                } else {
                    holder.txtPlusImg.setVisibility(View.VISIBLE);
                    holder.txtPlusImg.setText("+" + (imageList.length() - 3));
                }

                for (int i = 0; i < imageList.length(); i++) {
                    JSONObject image = imageList.getJSONObject(i);
                    switch (i) {
                        case 0:
                            Glide.with(layoutInflater.getContext())
                                    .load(image.getString("url"))
                                    .centerCrop()
                                    .into(holder.imgLL31);
                        case 1:
                            Glide.with(layoutInflater.getContext())
                                    .load(image.getString("url"))
                                    .centerCrop()
                                    .into(holder.imgLL32);
                        case 2:
                            Glide.with(layoutInflater.getContext())
                                    .load(image.getString("url"))
                                    .centerCrop()
                                    .into(holder.imgLL33);
                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Khi Click ảnh
        holder.mFLImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemClicked(holder.getAdapterPosition(), KEY.IMAGES);
            }
        });

        //Text Like and CommentBottomSheetDialogFragment
        holder.txtNBLike.setText(data.getCountLiked() + " Like");
        holder.txtNBComment.setText(data.getCountComments() + " Comment");

        //Xử lí các nút
        liked = data.getLiked();
        //todo
        //todo
        //todo
        if (liked) {
            holder.imgLiked.setVisibility(View.VISIBLE);
            holder.imgLike.setVisibility(View.INVISIBLE);
        } else {
            holder.imgLiked.setVisibility(View.INVISIBLE);
            holder.imgLike.setVisibility(View.VISIBLE);
        }
         countLike = data.getCountLiked();
        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (liked) {
                    holder.imgLiked.setVisibility(View.INVISIBLE);
                    holder.imgLike.setVisibility(View.VISIBLE);
                    countLike--;
                    holder.txtNBLike.setText(countLike + " Like");
                    liked = false;
                } else {
                    holder.imgLiked.setVisibility(View.VISIBLE);
                    holder.imgLike.setVisibility(View.INVISIBLE);
                    countLike++;
                    holder.txtNBLike.setText(countLike + " Like");
                    liked = true;
                }
            }
        });
        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemClicked(holder.getAdapterPosition(), KEY.COMMENTS);
            }
        });

        //Xử lí list danh sách Name
        if (data.getPests().size() != 0 ||
                data.getFertilizers().size() != 0 ||
                data.getProducts().size() != 0 ||
                data.getTransporters().size() != 0 ||
                data.getPackages().size() != 0) {
            holder.rcvTextItem.setVisibility(View.VISIBLE);
            List<String> listName = new ArrayList<>();
            if (data.getPests().size() > 0) {
                for (int i = 0; i < data.getPests().size(); i++) {
                    listName.add(data.getPests().get(i).getName());
                }
            }
            if (data.getFertilizers().size() > 0) {
                for (int i = 0; i < data.getFertilizers().size(); i++) {
                    listName.add(data.getFertilizers().get(i).getName());
                }
            }
            if (data.getProducts().size() > 0) {
                for (int i = 0; i < data.getProducts().size(); i++) {
                    listName.add(data.getProducts().get(i).getName());
                }
            }
            if (data.getTransporters().size() > 0) {
                for (int i = 0; i < data.getTransporters().size(); i++) {
                    listName.add(data.getTransporters().get(i).getName());
                }
            }
            if (data.getPackages().size() > 0) {
                for (int i = 0; i < data.getPackages().size(); i++) {
                    listName.add(data.getPackages().get(i).getName());
                }
            }

            TextAdapter textAdapter = new TextAdapter(layoutInflater.getContext(), listName);
            holder.rcvTextItem.setAdapter(textAdapter);
            holder.rcvTextItem.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext(), RecyclerView.HORIZONTAL, false));
        } else {
            holder.rcvTextItem.setVisibility(View.GONE);
        }
    }

    private void showLoadingView() {
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClicked(int position, String type);
    }
}
