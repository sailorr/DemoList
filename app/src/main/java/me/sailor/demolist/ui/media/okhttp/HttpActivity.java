package me.sailor.demolist.ui.media.okhttp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import me.sailor.demolist.R;
import me.sailor.demolist.bean.Result;
import me.sailor.demolist.contant.ContantApi;
import me.sailor.libcommon.base.BaseActivity;
import me.sailor.libcommon.net.okhttp.HttpCallback;
import me.sailor.libcommon.net.okhttp.HttpManager;

public class HttpActivity extends BaseActivity implements HttpContract.IView {
    private SwipeRefreshLayout mRefreshLayout;
    private List<Result> mList = new ArrayList<>();
    private HttpPresenter mPresenter = new HttpPresenter();
    private OkRVAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_http;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, HttpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        showLoadingDialog();
        mPresenter.loadData(ContantApi.GIRL_IMGS);
        mRefreshLayout = findViewById(R.id.swipe);
        mRefreshLayout.setColorSchemeColors
                (Color.parseColor("#DB7093"), Color.parseColor("#8A2BE2"), Color.parseColor("#98FB98"));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(ContantApi.GIRL_IMGS);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.http_rv);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止加载过程中图片错位
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        mAdapter = new OkRVAdapter(this, mList, R.layout.item_http_rv);
        recyclerView.setAdapter(mAdapter);

        HttpManager.getInstance().GET("https://app.fivesky.com.cn:8443/appbg/index", String.class, new HttpCallback() {
            @Override
            public void onSuccess(Object body) {
                Log.d("HttpActivity", "onSuccess: "+body.toString());
            }

            @Override
            public void onFail(int responseCode) {
                Log.d("HttpActivity", "onFail: ");
            }

            @Override
            public void onError(Exception e) {
                Log.d("HttpActivity", "onError: ");
            }
        });


    }

    @Override
    public void notifyRV(List<Result> results) {
        mList.addAll(results);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mRefreshLayout.isRefreshing()) {
                    //取消加载动画
                    mRefreshLayout.setRefreshing(false);
                }
                mAdapter.notifyDataSetChanged();
                hideLoadingDialog();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

}
