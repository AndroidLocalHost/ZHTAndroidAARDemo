package com.zht.aar.demo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zht.aar.demo.R;


/**
 * @author Administrator
 * 2020/09/22 0022 10:02
 * <p>
 * 我的界面
 */
public class MineAdapterItemAdapter extends RecyclerView.Adapter<MineAdapterItemAdapter.ItemViewHolder> {

    private final String[] arrayName;
    private Context mContext;

    public interface MineViewItemInterface {
        void itemTwoOnClickListener(int position);
    }

    private MineViewItemInterface viewItemInterface;

    public void setViewItemInterface(MineViewItemInterface viewItemInterface) {
        this.viewItemInterface = viewItemInterface;
    }

    public MineAdapterItemAdapter(Context context) {
        this.mContext = context;
        arrayName = mContext.getResources().getStringArray(R.array.fragment_mine_name_array);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.adapter_mine_item_view, parent, false);
        return new ItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tvMineView.setText(arrayName[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                viewItemInterface.itemTwoOnClickListener(adapterPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayName.length;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMineView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMineView = itemView.findViewById(R.id.tv_mine_view);

        }
    }


}
