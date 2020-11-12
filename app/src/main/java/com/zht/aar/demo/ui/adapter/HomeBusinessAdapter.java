package com.zht.aar.demo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zht.aar.demo.R;
import com.zht.androidlibrary.bean.BusinessListBean;
import com.zht.androidlibrary.bean.HomeBusinessTotalBean;
import com.zht.androidlibrary.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * 2020/10/28 0028 14:23
 */
public class HomeBusinessAdapter extends RecyclerView.Adapter<HomeBusinessAdapter.ItemViewHolderOne> {

    private Context mContext;
    private List<BusinessListBean> businessListList = new ArrayList<>();

    public HomeBusinessAdapter(Context context) {
        this.mContext = context;

    }

    @NonNull
    @Override
    public ItemViewHolderOne onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.adapter_business_home_item_view, viewGroup, false);
        return new ItemViewHolderOne(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolderOne itemViewHolderOne, int i) {
        BusinessListBean businessListBean = businessListList.get(i);
        itemViewHolderOne.tvTitleName.setText(businessListBean.getName());

        String activeTime = businessListBean.getActiveTime();
        if (!TextUtils.isEmpty(activeTime)) {
            Date date = DateUtils.getTimeToDate(activeTime);
            String dateStr = DateUtils.getDateStr(date);
            String timeDate = String.format(mContext.getResources().getString(R.string.business_date_update), dateStr, businessListBean.getLastTrail());
            itemViewHolderOne.tvTitleTime.setText(timeDate);
        }
        itemViewHolderOne.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterInterface != null) {
                    int adapterPosition = itemViewHolderOne.getAdapterPosition();
                    adapterInterface.onItemViewClickListener(businessListList.get(adapterPosition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return businessListList.size();
    }

    /**
     * 更新数据
     */
    public void updateItem(HomeBusinessTotalBean totalBean) {
        if (businessListList != null && totalBean != null) {
            businessListList.clear();
            List<BusinessListBean> beanList = totalBean.getList();
            businessListList.addAll(beanList);
            notifyDataSetChanged();
        }
    }

    class ItemViewHolderOne extends RecyclerView.ViewHolder {

        private final TextView tvTitleName;
        private final TextView tvTitleTime;
        private final ImageView ivTitleIcon;

        public ItemViewHolderOne(@NonNull View itemView) {
            super(itemView);
            tvTitleName = itemView.findViewById(R.id.tv_business_title_name);
            tvTitleTime = itemView.findViewById(R.id.tv_business_title_time);
            ivTitleIcon = itemView.findViewById(R.id.iv_business_title_icon);
        }
    }

    private HomeBusinessAdapterInterface adapterInterface;

    public void setAdapterInterface(HomeBusinessAdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    public interface HomeBusinessAdapterInterface {
        void onItemViewClickListener(BusinessListBean listBean);
    }


}
