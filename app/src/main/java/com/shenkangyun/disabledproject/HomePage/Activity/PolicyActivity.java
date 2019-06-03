package com.shenkangyun.disabledproject.HomePage.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shenkangyun.disabledproject.DBFolder.ServiceProject;
import com.shenkangyun.disabledproject.HomePage.PolicyFragment;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PolicyActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.policy_frame)
    FrameLayout policyFrame;

    private Bundle bundle;
    private FragmentManager fragmentManager;
    private List<ServiceProject> serviceProjects;
    private int typeDetailCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("补贴政策");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        bundle = new Bundle();
        fragmentManager = getSupportFragmentManager();
        serviceProjects = DataSupport.findAll(ServiceProject.class);
    }

    private void initData() {
        for (int i = 0; i < serviceProjects.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(serviceProjects.get(i).getTypeDetailName()));
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PolicyFragment policyFragment = new PolicyFragment();
        typeDetailCode = serviceProjects.get(0).getTypeDetailCode();
        bundle.putInt("typeDetailCode", typeDetailCode);
        policyFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.policy_frame, policyFragment);
        fragmentTransaction.commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PolicyFragment policyFragment = new PolicyFragment();
                typeDetailCode = serviceProjects.get(tab.getPosition()).getTypeDetailCode();
                bundle.putInt("typeDetailCode", typeDetailCode);
                policyFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.policy_frame, policyFragment);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
