package com.zht.aar.demo.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zht.aar.demo.R;
import com.zht.aar.demo.ui.adapter.MineAdapterItemAdapter;
import com.zht.androidlibrary.activity.BusinessCardActivity;
import com.zht.androidlibrary.activity.MineMaterialActivity;
import com.zht.androidlibrary.bean.DataConfig;

/**
 * 我的界面
 */
public class MineFragment extends Fragment {


    private RecyclerView mineRecyclerView;
    private MineAdapterItemAdapter mineAdapterItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        mineRecyclerView = root.findViewById(R.id.mine_recycler_view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineAdapterItemAdapter = new MineAdapterItemAdapter(requireContext());
        mineRecyclerView.setLayoutManager(linearLayoutManager);
        mineRecyclerView.setAdapter(mineAdapterItemAdapter);

        mineAdapterItemAdapter.setViewItemInterface(new MineAdapterItemAdapter.MineViewItemInterface() {
            @Override
            public void itemTwoOnClickListener(int position) {
                if (position == 0) {
                    //我的名片
                    DataConfig dataConfig = new DataConfig();
                    startActivity(BusinessCardActivity.createIntent(getContext(), dataConfig));
                } else {
                    //我的物料
                    DataConfig dataConfig = new DataConfig();
                    startActivity(MineMaterialActivity.createIntent(getContext(), dataConfig));
                }


            }
        });


    }
}