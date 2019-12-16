package me.sailor.demolist.ui.widget.rocker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import me.sailor.demolist.R;

public class RockerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocker);
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, RockerActivity.class);
        context.startActivity(starter);
    }
}
