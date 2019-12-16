package me.sailor.demolist.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import me.sailor.demolist.R;
import me.sailor.demolist.ui.TitleRadioGroupView;


public class TitleRadioViewActivity extends AppCompatActivity {
    private TitleRadioGroupView mView;
    private CustomTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_radio_view);
        mView = findViewById(R.id.title);
        mView.setTitleSelectedListener(new TitleRadioGroupView.TitleSelectedListener() {
            @Override
            public void titleSelected(CompoundButton buttonView, boolean isChecked) {
                Log.d("XXXXX", "第" + buttonView.getTag() + "个发生了变化，当前状态 isChecked=" + isChecked);
            }
        });
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, TitleRadioViewActivity.class);
        context.startActivity(starter);
    }
}
