package com.shenkangyun.disabledproject.ToolPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.ToolBean;
import com.shenkangyun.disabledproject.DBFolder.ServiceProject;
import com.shenkangyun.disabledproject.DBFolder.canjiType;
import com.shenkangyun.disabledproject.HomePage.PolicyFragment;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2018/2/23.
 */

public class ToolPageFragment extends Fragment {

    @BindView(R.id.tooLTab)
    TabLayout tooLTab;
    @BindView(R.id.toolFrame)
    FrameLayout toolFrame;

    private Bundle bundle;
    private FragmentManager fragmentManager;
    private List<canjiType> serviceProjects;
    private int typeDetailCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool, null);
        ButterKnife.bind(this, view);
        initView();
        initTab();
        return view;
    }

    private void initView() {
        bundle = new Bundle();
        fragmentManager = getActivity().getSupportFragmentManager();
        serviceProjects = DataSupport.findAll(canjiType.class);
    }

    private void initTab() {
        for (int i = 0; i < serviceProjects.size(); i++) {
            tooLTab.addTab(tooLTab.newTab().setText(serviceProjects.get(i).getTypeDetailName()));
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ToolInfoFragment toolInfoFragment = new ToolInfoFragment();
        typeDetailCode = serviceProjects.get(0).getTypeDetailCode();
        bundle.putInt("typeDetailCode", typeDetailCode);
        toolInfoFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.toolFrame, toolInfoFragment);
        fragmentTransaction.commit();
        tooLTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ToolInfoFragment toolInfoFragment = new ToolInfoFragment();
                typeDetailCode = serviceProjects.get(tab.getPosition()).getTypeDetailCode();
                bundle.putInt("typeDetailCode", typeDetailCode);
                toolInfoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.toolFrame, toolInfoFragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
