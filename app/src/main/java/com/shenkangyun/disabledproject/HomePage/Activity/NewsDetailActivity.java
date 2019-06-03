package com.shenkangyun.disabledproject.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.news_detail)
    WebView newsDetail;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("新闻详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        int newsID = intent.getIntExtra("NewsID", 0);
        String md5Str = Base.getMD5Str();
        String timeSpan = Base.getTimeSpan();
        StringBuilder builder = new StringBuilder();
        builder.append(Base.URL).append("?act=newsById").append("&data={\"appKey\":\"").append(md5Str).append("\",")
                .append("\"timeSpan\":\"").append(timeSpan).append("\",").append("\"id\":\"").append(newsID).append("\"}");
        WebSettings webSettings = newsDetail.getSettings();
        newsDetail.loadUrl(builder.toString());
        newsDetail.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        newsDetail.getSettings().setBuiltInZoomControls(true);
        newsDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        newsDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progress.setProgress(newProgress);//设置进度值
                }
            }
        });
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
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
    protected void onDestroy() {
        newsDetail.removeAllViews();
        newsDetail.destroy();
        super.onDestroy();
    }
}
