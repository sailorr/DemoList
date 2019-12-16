package me.sailor.demolist.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import me.sailor.libcommon.R;
import me.sailor.libcommon.utils.DensityUtils;

/**
 * @author sailor
 * @description
 * @time 2018/12/13
 */
public class CustomTabLayout extends RelativeLayout {
    private TabLayout mTabLayout = new TabLayout(getContext());
    private ViewPager mViewPager;

    public CustomTabLayout(Context context) {
        this(context, null);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                    mTabLayout.setSelectedTabIndicatorHeight(DensityUtils.dip2px(getContext(), 2f));
            }
        });
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue, null));
        mTabLayout.setBackgroundColor(Color.GRAY);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(mTabLayout, params);

    }

    private View createItemView(TabItem item){
        View view = new LinearLayout(getContext());
        ((LinearLayout) view).setOrientation(LinearLayout.VERTICAL);
        ((LinearLayout) view).setGravity(Gravity.CENTER);

        return view;
    }

    class TabItem {
        private String tabtext;
        private int resId;

        public String getTabtext() {
            return tabtext;
        }

        public void setTabtext(String tabtext) {
            this.tabtext = tabtext;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public TabItem(String tabtext, int resId) {

        }
    }


}
