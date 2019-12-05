package me.sailor.demolist.ui.main;


import android.content.Context;
import android.os.Debug;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.ArrayList;
import java.util.List;

import me.sailor.demolist.R;
import me.sailor.demolist.base.BaseRecycleViewAdapter;
import me.sailor.libcommon.base.BaseActivity;
import me.sailor.demolist.bean.ItemModel;
import me.sailor.demolist.util.ActivityManager;
import me.sailor.libcommon.utils.FileUtils;

/**
 *10.39 修改一次
 */
public class MainActivity extends BaseActivity implements MainContract.IView, BaseRecycleViewAdapter.ItemClickListener {
    private RecyclerView mRecyclerView;
    private MainRVAdapter mAdapter;
    private List<ItemModel> mModels = new ArrayList<>();
    private MainPresenter mPresenter = new MainPresenter();

    /**
     * 10.40 修改一次
     * @return
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mPresenter.loadData();
        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (reqPermissions()) {
            setFilePah();
        }
    }

    @Override
    public void onItemClickListener(View view, int positon) {
        ActivityManager.startActivity(this, mModels.get(positon).getActivity());
    }

    @Override
    public void notify(List<ItemModel> list) {
        this.mModels = list;
        mAdapter = new MainRVAdapter(list, R.layout.item_recycler);
        mAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
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


    private Boolean reqPermissions() {
        if (AndPermission.hasPermissions(this, Permission.WRITE_EXTERNAL_STORAGE)) {
            return true;
        }
        AndPermission.with(this)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .rationale(rationale)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (data.contains(Permission.WRITE_EXTERNAL_STORAGE)) {
                            setFilePah();
                        }
                    }
                }).start();
        return false;
    }

    private Rationale<List<String>> rationale = new Rationale<List<String>>() {
        @Override
        public void showRationale(Context context, List<String> data, RequestExecutor executor) {
            executor.execute();
            executor.cancel();
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        Debug.stopMethodTracing();
        Log.d("MainActivity", "onResume: ");
    }

    private long exitTime = 0l;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis()-exitTime>2000){
            showToast("再按一下退出。");
            exitTime = System.currentTimeMillis();
        }else {
            finish();
        }
    }

    private void setFilePah() {
        FileUtils.init(FileUtils.BASEPATH);
    }
}

