package com.zht.aar.demo.ui.notifications;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zht.aar.demo.R;


/**
 * @author Administrator
 * 2020/09/28 0028 15:01
 *
 * 消息
 */
public class MessageFragment extends Fragment {


    private static MessageFragment instance;

    public static MessageFragment getInstance() {
        if (instance == null) {
            instance = new MessageFragment();
        }
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        initFindView(root);
        initView();
        return root;
    }

    private void initView() {

    }


    private void initFindView(View root) {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
