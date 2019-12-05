package me.sailor.demolist.ui.system_about;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import me.sailor.demolist.R;
import me.sailor.demolist.util.ContentUriUtil;

public class OfficeActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        mTextView = findViewById(R.id.text);

        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType(“image/*”);//选择图片
                //intent.setType(“audio/*”); //选择音频
                //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType(“video/*;image/*”);//同时选择视频和图片
                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.open2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (uri!=null){
                    intent.setDataAndType(Uri.fromFile(new File(uri)), "application/msword");
                    Log.d("TAG",Uri.fromFile(new File(uri)).toString());
                    startActivity(intent);
                }

            }
        });
    }

    private String uri;
    private Uri mUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == OfficeActivity.RESULT_OK) {
            if (requestCode == 1) {
                mUri = data.getData();
                Log.d("TAG","contenturi---->"+mUri.toString());

                uri = ContentUriUtil.getPath(this, mUri);
                Log.d("TAG","String path---->"+uri);
                mTextView.setText(uri);
            }
        }
    }
}
