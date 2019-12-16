package me.sailor.demolist.ui.widget.doublerecycle;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.sailor.demolist.R;
import me.sailor.libcommon.base.BaseActivity;

public class DoubleRecycleActivity extends BaseActivity {
    private RecyclerView mRecyclerView;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_double_recycle;
    }

    @Override
    protected void init() {
        mRecyclerView = findViewById(R.id.doublerecycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DoubleAdapter doubleAdapter = new DoubleAdapter(this,initData());
        mRecyclerView.setAdapter(doubleAdapter);
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, DoubleRecycleActivity.class);
        context.startActivity(starter);
    }
    private List<List<String>> initData() {
        List<List<String>> lists = new ArrayList<>();
        List<String> strings;
        String pro = "产品";
        for (int i = 0; i < 5; i++) {
            strings = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                strings.add(pro + j);
            }
            lists.add(strings);
        }
        return lists;
    }

}
