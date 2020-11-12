package com.zht.aar.demo.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zht.aar.demo.R;
import com.zht.aar.demo.ui.adapter.HomeBusinessAdapter;
import com.zht.androidlibrary.activity.BusinessActivity;
import com.zht.androidlibrary.activity.BusinessDetailsActivity;
import com.zht.androidlibrary.activity.TaskActivity;
import com.zht.androidlibrary.bean.BusinessListBean;
import com.zht.androidlibrary.bean.DataConfig;
import com.zht.androidlibrary.bean.DeployDataBean;
import com.zht.androidlibrary.bean.HomeBusinessTotalBean;
import com.zht.androidlibrary.bean.HomeTaskTotalBean;
import com.zht.androidlibrary.utils.sdkUtils.YYEngineEventHandler;
import com.zht.androidlibrary.utils.sdkUtils.YYExpressEngine;

public class HomeFragment extends Fragment implements View.OnClickListener, YYEngineEventHandler, HomeBusinessAdapter.HomeBusinessAdapterInterface {

    private RelativeLayout homeTaskIng;
    private RelativeLayout homeTaskNot;
    private RelativeLayout homeTaskExpired;
    private TextView tvUnreadBusiness;
    private TextView tvTaskIng;
    private TextView tvTaskNo;
    private TextView tvTaskExpired;
    private RecyclerView recyclerView;
    private HomeBusinessAdapter homeBusinessAdapter;
    private LinearLayout llBusinessItemView;
    private TextView tvTodayBusiness;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initFindView(root);
        initView();
        return root;
    }

    private void initFindView(View root) {
        homeTaskIng = root.findViewById(R.id.ll_home_task_ing);
        homeTaskNot = root.findViewById(R.id.ll_home_task_not);
        homeTaskExpired = root.findViewById(R.id.ll_home_task_expired);
        tvUnreadBusiness = root.findViewById(R.id.tv_unread_business);
        tvTaskIng = root.findViewById(R.id.tv_task_ing);
        tvTaskNo = root.findViewById(R.id.tv_task_no_start);
        tvTaskExpired = root.findViewById(R.id.tv_task_expired);
        recyclerView = root.findViewById(R.id.recycler_home_view);
        llBusinessItemView = root.findViewById(R.id.ll_business_item_view);
        tvTodayBusiness = root.findViewById(R.id.tv_today_business);
        homeTaskIng.setOnClickListener(this);
        homeTaskNot.setOnClickListener(this);
        homeTaskExpired.setOnClickListener(this);
        llBusinessItemView.setOnClickListener(this);

    }

    private void initView() {
        YYExpressEngine instance = YYExpressEngine.getInstance();
        //设置返回接口
        instance.setEngineEventHandler(this);
        //获取营销任务统计数据
        instance.getMarketingTaskStatistics();
        //获取商机提示数据
        instance.getBusinessStatistics();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeBusinessAdapter = new HomeBusinessAdapter(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeBusinessAdapter);
        homeBusinessAdapter.setAdapterInterface(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home_task_ing:
                //进行中 0
                startActivity(TaskActivity.createIntent(getContext(), 0));
                break;
            case R.id.ll_home_task_not:
                //未开始 1
                startActivity(TaskActivity.createIntent(getContext(), 1));
                break;
            case R.id.ll_home_task_expired:
                //已过期 2
                startActivity(TaskActivity.createIntent(getContext(), 2));
                break;
            case R.id.ll_business_item_view:
                //商机列表
                DataConfig dataConfig = new DataConfig();
                startActivity(BusinessActivity.createIntent(getContext(), dataConfig));
                break;
        }

    }

    @Override
    public void onMarketingTaskSuccess(HomeTaskTotalBean totalBean) {
        tvTaskIng.setText(String.valueOf(totalBean.getInProgressNum()));
        tvTaskNo.setText(String.valueOf(totalBean.getNotStartedNum()));
        tvTaskExpired.setText(String.valueOf(totalBean.getExpiredNum()));

    }

    @Override
    public void onBusinessStatisticsSuccess(HomeBusinessTotalBean totalBean) {
        if (homeBusinessAdapter != null) {
            homeBusinessAdapter.updateItem(totalBean);
        }
        String todayCont = String.format(getResources()
                .getString(R.string.number_of_business_opportunities_today), totalBean.getTodayCount());
        String noReadCount = String.format(getResources().getString(R.string.number_of_unread_opportunities), totalBean.getNoReadCount());
        tvUnreadBusiness.setText(noReadCount);
        tvTodayBusiness.setText(todayCont);

    }

    @Override
    public void onItemViewClickListener(BusinessListBean listBean) {
        //商机详情
        DeployDataBean dataBean = new DeployDataBean();
        dataBean.setId(listBean.getId());
        dataBean.setUserId(listBean.getUserId());
        dataBean.setStaffId(listBean.getStaffId());
        DataConfig dataConfig = new DataConfig();
        String listBeans = new Gson().toJson(dataBean);
        dataConfig.setMessage(listBeans);
        startActivity(BusinessDetailsActivity.createIntent(getContext(), dataConfig));


    }
}