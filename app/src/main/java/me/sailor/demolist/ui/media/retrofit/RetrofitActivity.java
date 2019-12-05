package me.sailor.demolist.ui.media.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.sailor.demolist.contant.Api;
import me.sailor.demolist.R;
import me.sailor.demolist.bean.JsonRootBean;
import me.sailor.demolist.bean.Result;
import me.sailor.libcommon.base.BaseActivity;
import me.sailor.libcommon.net.retrofit.RetrofitManager;

public class RetrofitActivity extends BaseActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ReRVAdapter mAdapter;
    private List<Result> mResults = new ArrayList<>();


    @Override
    protected int setLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void init() {
        initView();
        loadData();
    }

    private void loadData() {
        RetrofitManager.getInstance().getServer(Api.class)
                .getImgs()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<JsonRootBean>() {
            @Override
            public void accept(JsonRootBean jsonRootBean) throws Exception {
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                mResults.clear();
                mResults.addAll(jsonRootBean.getResults());
                mAdapter.notifyDataSetChanged();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("RetrofitActivity", "accept: "+throwable.getMessage().toString());
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RetrofitActivity.class);
        context.startActivity(starter);
    }
    private void initView() {
        initOriginal();
    }

    public void initOriginal() {
        mSwipeRefreshLayout = findViewById(R.id.retrofit_swipe);
//        //禁止下拉
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        mRecyclerView = findViewById(R.id.retrofit_recycle);
        mAdapter = new ReRVAdapter(this, mResults, R.layout.item_retofit_rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(mAdapter);

    }


//  okhttp
//    private void loadData() {
//        showLoadingDialog();
//
//        HttpManager.getInstance().GET(ContantApi.GIRL_IMGS, JsonRootBean.class, new HttpCallback() {
//            @Override
//            public void onSuccess(Object body) {
//                final JsonRootBean jsonRootBean = (JsonRootBean) body;
//                mResults.clear();
//                mResults.addAll(jsonRootBean.getResults());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (mSwipeRefreshLayout.isRefreshing()) {
//                            mSwipeRefreshLayout.setRefreshing(false);
//                        }
//                        hideLoadingDialog();
//                        mAdapter.notifyDataSetChanged();
//                    }
//                });
//            }
//
//            @Override
//            public void onFail(int responseCode) {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
//    }
}
