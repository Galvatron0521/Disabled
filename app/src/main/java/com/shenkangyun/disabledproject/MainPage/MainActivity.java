package com.shenkangyun.disabledproject.MainPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenkangyun.disabledproject.CalendarPage.CalendarPageFragment;
import com.shenkangyun.disabledproject.CommunityPage.Activity.PublishInfoActivity;
import com.shenkangyun.disabledproject.CommunityPage.CommunityFragment;
import com.shenkangyun.disabledproject.HomePage.HomePageFragment;
import com.shenkangyun.disabledproject.MinePage.MinePageFragment;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.ToolPage.ToolPageFragment;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.NetworkChangeReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_public)
    FrameLayout layoutPublic;
    @BindView(R.id.img_menu)
    ImageView imgMenu;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver changeReceiver;
    private FragmentManager fragmentManager;
    private Fragment HomeFragment, CalendarFragment, CommunityFragment, MineFragment, ToolFragment, replaceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FuncUtil.iniSystemBar(this, R.color.white);
        ButterKnife.bind(this);
        intentFilter = new IntentFilter();
        changeReceiver = new NetworkChangeReceiver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(changeReceiver, intentFilter);
        initView();
        initFragment();
    }

    private void initView() {
        HomeFragment = new HomePageFragment();
        CalendarFragment = new CalendarPageFragment();
        CommunityFragment = new CommunityFragment();
        MineFragment = new MinePageFragment();
        ToolFragment = new ToolPageFragment();

        tvTitle.setText("首页");
        fragmentManager = getSupportFragmentManager();
    }

    private void initFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_public, HomeFragment);
        fragmentTransaction.commit();
        replaceFragment = HomeFragment;
    }

    @OnClick({R.id.btn_home, R.id.btn_community, R.id.btn_tool, R.id.btn_calendar, R.id.btn_mine})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.btn_home:
                tvTitle.setText("首页");
                imgMenu.setVisibility(View.GONE);
                replaceFragment(HomeFragment, fragmentTransaction);
                break;
            case R.id.btn_community:
                tvTitle.setText("社区");
                imgMenu.setVisibility(View.VISIBLE);
                replaceFragment(CommunityFragment, fragmentTransaction);
                break;
            case R.id.btn_tool:
                tvTitle.setText("辅具");
                imgMenu.setVisibility(View.GONE);
                replaceFragment(ToolFragment, fragmentTransaction);
                break;
            case R.id.btn_calendar:
                tvTitle.setText("日历");
                imgMenu.setVisibility(View.GONE);
                replaceFragment(CalendarFragment, fragmentTransaction);
                break;
            case R.id.btn_mine:
                tvTitle.setText("我的");
                imgMenu.setVisibility(View.GONE);
                replaceFragment(MineFragment, fragmentTransaction);
                break;
        }
    }

    public void replaceFragment(Fragment showFragment, FragmentTransaction fragmentTransaction) {
        if (showFragment.isAdded()) {
            fragmentTransaction.hide(replaceFragment).show(showFragment).commit();
        } else {
            fragmentTransaction.hide(replaceFragment).add(R.id.layout_public, showFragment).commit();
        }
        replaceFragment = showFragment;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(changeReceiver);
        super.onDestroy();
    }

    @OnClick(R.id.img_menu)
    public void onViewClicked() {
        Intent intent = new Intent(this, PublishInfoActivity.class);
        startActivityForResult(intent, 9);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9 && resultCode == 8) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            tvTitle.setText("社区");
            imgMenu.setVisibility(View.VISIBLE);
            replaceFragment(CommunityFragment, fragmentTransaction);
        }
    }
}
