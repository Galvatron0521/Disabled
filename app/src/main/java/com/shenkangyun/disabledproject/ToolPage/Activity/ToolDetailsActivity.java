package com.shenkangyun.disabledproject.ToolPage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.ToolBean;
import com.shenkangyun.disabledproject.DBFolder.LeaseBean;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.ToolPage.Adapter.LeaseAdapter;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GlideImageLoader;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.carousel)
    Banner carousel;
    @BindView(R.id.leastRecycler)
    RecyclerView leastRecycler;
    @BindView(R.id.tool_name)
    TextView toolName;
    @BindView(R.id.tool_content)
    TextView toolContent;


    private String id;
    private String name;
    private String content;
    private ArrayList<String> imgList;

    private int orderNum;
    private String title;
    private String leaseContent;
    private List<LeaseBean> leaseList = new ArrayList<>();
    private LeaseAdapter leaseAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_details);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("辅具详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
        if (leaseList.size() == 0) {
            initNetworkRequest();
        } else {
            initProcedure();
        }
    }

    private void initView() {
        leaseList.clear();
        leaseList = DataSupport.findAll(LeaseBean.class);
        layoutManager = new LinearLayoutManager(this);
        leaseAdapter = new LeaseAdapter();
        leastRecycler.setLayoutManager(layoutManager);
        leastRecycler.setAdapter(leaseAdapter);
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        content = intent.getStringExtra("content");
        imgList = intent.getStringArrayListExtra("imgList");

        toolName.setText(name);
        CharSequence charSequence = Html.fromHtml(content);
        toolContent.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        toolContent.setMovementMethod(LinkMovementMethod.getInstance());
        carousel.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        carousel.setImageLoader(new GlideImageLoader());
        //设置图片集合
        carousel.setImages(imgList);
        //设置banner动画效果
        carousel.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        carousel.isAutoPlay(true);
        //设置轮播时间
        carousel.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        carousel.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        carousel.start();
    }

    private void initNetworkRequest() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "toolList")
                .addParams("data", new toolList(Base.getMD5Str(), Base.getTimeSpan(), "0", "8", 1).toJson())
                .build().execute(new GsonCallBack<ToolBean>() {

            @Override
            public void onSuccess(ToolBean response) throws JSONException {
                for (int i = 0; i < response.getData().getLeaseList().size(); i++) {
                    LeaseBean leaseBean = new LeaseBean();

                    orderNum = response.getData().getLeaseList().get(i).getOrderNum();
                    title = response.getData().getLeaseList().get(i).getTitle();
                    leaseContent = response.getData().getLeaseList().get(i).getContent();

                    leaseBean.setOrderNum(orderNum);
                    leaseBean.setTitle(title);
                    leaseBean.setContent(leaseContent);
                    leaseBean.save();
                }
                initProcedure();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initProcedure() {
        leaseList.clear();
        leaseList = DataSupport.findAll(LeaseBean.class);
        leaseAdapter.setNewData(leaseList);
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

    @Override
    public void onStart() {
        super.onStart();
        carousel.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        carousel.stopAutoPlay();
    }

    static class toolList {

        private String appKey;
        private String timeSpan;
        private String pageNo;
        private String pageCount;
        private int serviceObject;

        public toolList(String appKey, String timeSpan, String pageNo, String pageCount, int serviceObject) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.serviceObject = serviceObject;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
