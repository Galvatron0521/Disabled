package com.shenkangyun.disabledproject.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.type_title)
    TextView typeTitle;
    @BindView(R.id.policy_title)
    TextView policyTitle;
    @BindView(R.id.policy_content)
    TextView policyContent;

    private String name;
    private String content;
    private String typeDetailName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("服务项目详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        content = intent.getStringExtra("content");
        typeDetailName = intent.getStringExtra("typeDetailName");
    }

    private void initData() {
        typeTitle.setText(typeDetailName);
        policyTitle.setText(name);
        CharSequence charSequence = Html.fromHtml(content);
        policyContent.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        policyContent.setMovementMethod(LinkMovementMethod.getInstance());
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
