package com.example.apiseemore.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiseemore.KEY;
import com.example.apiseemore.R;
import com.example.apiseemore.adapter.CommetAdapter;
import com.example.apiseemore.adapter.PeopleLikedAdapter;
import com.example.apiseemore.model.comment.Datum;
import com.example.apiseemore.presenter.comment.CommentPresenter;
import com.example.apiseemore.presenter.comment.ICommentPresenter;
import com.example.apiseemore.presenter.peopleLiked.IPeopleLikedPresenter;
import com.example.apiseemore.presenter.peopleLiked.PeopleLikedPresenter;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements ICommentPresenter, IPeopleLikedPresenter {

    private CommetAdapter commentAdapter;
    private RecyclerView rcvComment;
    private RecyclerView rcvPeopleLiked;
    private CommentPresenter commentPresenter;
    private PeopleLikedPresenter peopleLikedPresenter;
    private ArrayList<Datum> commentDatas = new ArrayList<>();
    private List<com.example.apiseemore.model.peopleLiked.Datum> listPeopleLiked = new ArrayList<>();
    private TextView txtBlankComment;
    private ImageView imgBtnCMLike;
    private ImageView imgBtnCMLiked;
    private TextView txtCMLike;
    private ImageView imgBtnDown;

    private boolean isFirstData = true;
    private int pageLimit = 10;
    private int page = 1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        commentPresenter = new CommentPresenter(this);
        commentAdapter = new CommetAdapter(this, commentDatas);
        event();
    }

    private void initView() {
        imgBtnCMLike = findViewById(R.id.imgBtnCMLike);
        imgBtnCMLiked = findViewById(R.id.imgBtnCMLiked);
        txtCMLike = findViewById(R.id.txtCMLike);
        imgBtnDown = findViewById(R.id.imgBtnDown);
        rcvComment = findViewById(R.id.rcvComment);
        txtBlankComment = findViewById(R.id.txtBlankComment);
    }

    private void event() {
        final Intent intent = getIntent();
        final String token = intent.getStringExtra(KEY.TOKEN);
        final String commentID = intent.getStringExtra(KEY.ID_COMMENT);

        commentPresenter.getCommentData(commentID, String.valueOf(pageLimit), String.valueOf(page), token);

        //Xem danh sách lượt thích
        txtCMLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(commentID,token);
            }
        });

        //btn back
        imgBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentActivity.super.onBackPressed();
            }
        });

        //Loadmore Comment
        rcvComment.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == commentDatas.size() - 1) {
                        //Loadmore
                        if (commentDatas.get(commentDatas.size() - 1) != null) {
                            commentDatas.add(null);
                            commentAdapter.notifyDataSetChanged();
                            page += 1;
                            commentPresenter.getCommentData(commentID, String.valueOf(pageLimit), String.valueOf(page), token);
                        }
                    }
                }
            }
        });

        //Comment Click
        commentAdapter.setOnItemClickListener(new CommetAdapter.OnDCItemClickListener() {
            @Override
            public void OnItemClicked(int position) {
                Intent intent1 = new Intent(CommentActivity.this, DetailCommentActivity.class);
                intent1.putExtra(KEY.COMMENTS_DETAIL_ID, commentDatas.get(position).getId());
                intent1.putExtra(KEY.TOKEN, token);
                startActivity(intent1);
            }
        });
    }

    private void openDialog(String commentID, String token) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.people_liked_dialog);

        rcvPeopleLiked = dialog.findViewById(R.id.rcvPeopleLiked);
        peopleLikedPresenter = new PeopleLikedPresenter(this);
        peopleLikedPresenter.getListPL(commentID,token);
        PeopleLikedAdapter peopleLikedAdapter = new PeopleLikedAdapter(this, listPeopleLiked);
        rcvPeopleLiked.setLayoutManager(new LinearLayoutManager(this));
        rcvPeopleLiked.setAdapter(peopleLikedAdapter);
        dialog.show();
    }


    @Override
    public void onRequestCommentSuccess(List<Datum> commentList, int recordsTotal) {
        if (!isFirstData) {
            if (commentDatas.size() == recordsTotal) {
                commentAdapter.setOutOfData(true);
            }
            if (commentDatas.get(commentDatas.size() - 1) == null) {
                commentDatas.remove(commentDatas.size() - 1);
            }
            commentDatas.addAll(commentList);
            commentAdapter.notifyDataSetChanged();
        } else {
            if (recordsTotal == 0) {
                rcvComment.setVisibility(View.GONE);
            } else {
                rcvComment.setVisibility(View.VISIBLE);
                txtBlankComment.setVisibility(View.GONE);
            }
            commentDatas.addAll(commentList);
            rcvComment.setLayoutManager(new LinearLayoutManager(this));
            rcvComment.setAdapter(commentAdapter);
            isFirstData = false;
        }
    }

    @Override
    public void onRequestCommentFail() {
        rcvComment.setVisibility(View.GONE);
        txtBlankComment.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetPLSuccess(List<com.example.apiseemore.model.peopleLiked.Datum> peopleLiked) {
        listPeopleLiked.addAll(peopleLiked);
    }

    @Override
    public void onGetPLFail() {

    }
}
