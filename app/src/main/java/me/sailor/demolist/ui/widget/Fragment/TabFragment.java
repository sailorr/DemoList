package me.sailor.demolist.ui.widget.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.sailor.demolist.R;

/**
 * @author sailor on2019/1/28 17:17
 * @desc
 */
@SuppressLint("ValidFragment")
public class TabFragment extends Fragment {
    private TextView textView ;
    private static final String KEY="title";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,container,false);
        textView = view.findViewById(R.id.frag_text);
        textView.setText(getArguments().getString(KEY));
        return view;
    }

    public static TabFragment NEWINSTANCE(String string){
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,string);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
}
