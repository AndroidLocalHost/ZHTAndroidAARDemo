package com.zht.aar.demo.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zht.aar.demo.R;
import com.zht.aar.demo.ui.notifications.MessageFragment;
import com.zht.androidlibrary.fragmet.NewsFragment;

/**
 * @author Administrator
 * 2020/09/27 0027 9:33
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_new_name, R.string.tab_new_name_im};
    private Context mContext;


    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;

    }

    @Override
    public Fragment getItem(int i) {
        if (i == 1) {
            return NewsFragment.newsInstance(i);
        } else {
            return MessageFragment.getInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
