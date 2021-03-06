package com.example.apiseemore.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apiseemore.KEY;
import com.example.apiseemore.R;
import com.example.apiseemore.adapter.StatusAdapter;
import com.example.apiseemore.model.seemore.Datum;
import com.example.apiseemore.presenter.seemore.ISeeMorePresenter;
import com.example.apiseemore.presenter.seemore.SeeMorePresenter;

import java.util.ArrayList;
import java.util.List;

public class SeeMoreActivity extends AppCompatActivity implements ISeeMorePresenter {

    private EditText edtSM;
    private ImageView imgBtPictureSM;
    private RecyclerView rcvStatusSM;

    private SwipeRefreshLayout srLayout;
    private SeeMorePresenter mSeeMorePresenter;
    private StatusAdapter statusAdapter;

    private ArrayList<Datum> datas = new ArrayList<>();

    private String pageLimit = "5";
    private int firstPage = 1;
    private int page = 1;
    private String token;
    private boolean isFirstData = true;
    private boolean isLoading = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        initView();
        init();
        statusAdapter = new StatusAdapter(this, datas);
        event();
    }

    private void initView() {
        srLayout = findViewById(R.id.srLayout);
        edtSM = findViewById(R.id.edtSM);
        imgBtPictureSM = findViewById(R.id.imgBtPictureSM);
        rcvStatusSM = findViewById(R.id.rcvStatusSM);

    }

    private void init() {
        mSeeMorePresenter = new SeeMorePresenter(this);
    }

    private void event() {
        final Intent intent = getIntent();
        token = intent.getStringExtra(KEY.TOKEN);
        mSeeMorePresenter.getDataSM("Bearer"+token, pageLimit, String.valueOf(firstPage));
        //Load Refresh
        srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datas.clear();
                datas.add(null);
                page = 1;
                statusAdapter.setOutOfData(false);
                mSeeMorePresenter.getDataSM("Bearer"+token, pageLimit, String.valueOf(firstPage));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srLayout.setRefreshing(false);
                    }
                }, 1350);
            }
        });
        //Button Ảnh
        imgBtPictureSM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });

        //Loadmore Recycleview
        rcvStatusSM.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == datas.size() - 1) {
                        //Loadmore
                        if (datas.get(datas.size() - 1) != null) {
                            datas.add(null);
                            statusAdapter.notifyDataSetChanged();
                            page += 1;
                            mSeeMorePresenter.getDataSM("Bearer"+token, pageLimit, String.valueOf(page));
                        }
                    }
                }
            }
        });
        //Xử lí khi click ảnh
        statusAdapter.setOnItemClickListener(new StatusAdapter.OnItemClickListener() {
            @Override
            public void OnItemClicked(int position,String type) {
                if(type.equals(KEY.IMAGES)){
                    Intent intent1 = new Intent(SeeMoreActivity.this,DetailPhotoActivity.class);
                    intent1.putExtra(KEY.IMAGES,datas.get(position).getImage().toString());
                    startActivity(intent1);
                }
                if (type.equals(KEY.COMMENTS)){
                    Intent intent2 = new Intent(SeeMoreActivity.this,CommentActivity.class);
                    intent2.putExtra(KEY.ID_COMMENT,datas.get(position).getId().toString());
                    intent2.putExtra(KEY.TOKEN,token);
                    startActivity(intent2);

                    /*CommentBottomSheetDialogFragment comment = new CommentBottomSheetDialogFragment();
                    comment.show(getSupportFragmentManager(),"hello");*/
                }
            }
        });
    }


    @Override
    public void onGetDataSuccess(final List<Datum> dataList, int recordsTotal) {
        if (!isFirstData) {
            if (datas.size() == recordsTotal) {
                statusAdapter.setOutOfData(true);
            }
            if (datas.get(datas.size() - 1) == null) {
                datas.remove(datas.size() - 1);
            }
            datas.addAll(dataList);
            statusAdapter.notifyDataSetChanged();
        } else {
            datas.addAll(dataList);
            rcvStatusSM.setLayoutManager(new LinearLayoutManager(this));
            rcvStatusSM.setAdapter(statusAdapter);
            rcvStatusSM.setHasFixedSize(true);
            isFirstData = false;
        }
    }

    @Override
    public void onGetDataFail() {
    }


}
