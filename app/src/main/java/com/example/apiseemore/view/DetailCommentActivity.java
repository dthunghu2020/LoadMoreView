package com.example.apiseemore.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiseemore.KEY;
import com.example.apiseemore.R;
import com.example.apiseemore.adapter.DetailCommentAdapter;
import com.example.apiseemore.model.comment.Sub;
import com.example.apiseemore.model.commentDetail.Datum;
import com.example.apiseemore.presenter.commentDetail.CommentDetailPresenter;
import com.example.apiseemore.presenter.commentDetail.ICommentDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class DetailCommentActivity extends AppCompatActivity implements ICommentDetailPresenter {

    private CommentDetailPresenter commentDetailPresenter;
    private DetailCommentAdapter detailCommentAdapter;

    private ImageView imgBtnLeft;
    private RecyclerView rcvCommentDetail;
    private TextView txtBlankComment;
    private EditText edtCDCommentDetail;
    private ImageView imgBtnSendCD;

    private ArrayList<Datum> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_comment);
        initView();
        commentDetailPresenter = new CommentDetailPresenter(this);
        detailCommentAdapter = new DetailCommentAdapter(this, datas);
        event();
    }

    private void initView() {
        imgBtnLeft = findViewById(R.id.imgBtnLeft);
        rcvCommentDetail = findViewById(R.id.rcvCommentDetail);
        txtBlankComment = findViewById(R.id.txtBlankComment);
        edtCDCommentDetail = findViewById(R.id.edtCDCommentDetail);
        imgBtnSendCD = findViewById(R.id.imgBtnSendCD);
    }

    private void event() {
        Intent intent = getIntent();
        String token = intent.getStringExtra(KEY.TOKEN);
        int commentDetailID = intent.getIntExtra(KEY.COMMENTS_DETAIL_ID, 0);
        commentDetailPresenter.getSubData(commentDetailID,token);
        imgBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailCommentActivity.super.onBackPressed();
            }
        });
    }


    @Override
    public void onGetCDSuccess(List<Datum> dataList) {
        if (dataList.size() == 0) {
            rcvCommentDetail.setVisibility(View.GONE);
        } else {
            rcvCommentDetail.setVisibility(View.VISIBLE);
            txtBlankComment.setVisibility(View.GONE);
        }
        datas.addAll(dataList);
        rcvCommentDetail.setLayoutManager(new LinearLayoutManager(this));
        rcvCommentDetail.setAdapter(detailCommentAdapter);
    }

    @Override
    public void onGetCDFail() {
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
    }
}
