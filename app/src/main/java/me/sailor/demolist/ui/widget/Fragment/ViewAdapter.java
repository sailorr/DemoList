package me.sailor.demolist.ui.widget.Fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author sailor on2019/1/29 9:25
 * @desc
 */
public class ViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] strings;
    public ViewAdapter(FragmentManager manager, List<Fragment> list, String[] strings) {
        super(manager);
        this.fragments = list;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
