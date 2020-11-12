package com.zht.aar.demo.ui.notifications;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zht.aar.demo.R;
import com.zht.aar.demo.ui.adapter.SectionsPagerAdapter;

public class MainNewFragment extends Fragment {

    private TabLayout tabLayoutNew;
    private ViewPager newViewPage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        initFindView(root);
        initView();
        return root;
    }


    private void initFindView(View root) {
        tabLayoutNew = root.findViewById(R.id.tab_layout_new);
        newViewPage = root.findViewById(R.id.new_view_page);

    }

    private void initView() {
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), getContext());
        newViewPage.setAdapter(pagerAdapter);
        newViewPage.setOffscreenPageLimit(2);
        tabLayoutNew.setupWithViewPager(newViewPage);
        tabLayoutNew.setTabMode(TabLayout.MODE_FIXED);
        newViewPage.setCurrentItem(1);


    }
}