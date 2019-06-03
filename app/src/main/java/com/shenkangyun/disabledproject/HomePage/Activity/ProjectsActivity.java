package com.shenkangyun.disabledproject.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.PolicyBean;
import com.shenkangyun.disabledproject.BeanFolder.ProjectsBean;
import com.shenkangyun.disabledproject.HomePage.Adapter.ProjectsAdapter;
import com.shenkangyun.disabledproject.HomePage.PolicyFragment;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ProjectsActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.projectRecycler)
    RecyclerView projectRecycler;

    private int size = 8;
    private int pageNo = 0;
    private int pageCount = 8;
    private int totalCount;

    private String name;
    private String content;
    private String typeDetailName;

    private ProjectsAdapter projectsAdapter;
    private LinearLayoutManager layoutManager;
    private List<ProjectsBean.DataBean.ListBean> totalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("服务项目");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        totalList = new ArrayList<>();
        projectsAdapter = new ProjectsAdapter();
        layoutManager = new LinearLayoutManager(this);
        projectRecycler.setLayoutManager(layoutManager);
        projectRecycler.setAdapter(projectsAdapter);
    }

    private void initData() {
        final List<ProjectsBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "serviceProjectList")
                .addParams("data", new serviceProjectList(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                .build().execute(new GsonCallBack<ProjectsBean>() {
            @Override
            public void onSuccess(ProjectsBean response) throws JSONException {
                totalCount = response.getData().getTotalCount();
                size = response.getData().getList().size();
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    ProjectsBean.DataBean.ListBean listBean = new ProjectsBean.DataBean.ListBean();
                    name = response.getData().getList().get(i).getName();
                    content = response.getData().getList().get(i).getContent();
                    typeDetailName = response.getData().getList().get(i).getTypeDetailName();

                    listBean.setName(name);
                    listBean.setContent(content);
                    listBean.setTypeDetailName(typeDetailName);
                    listBeans.add(listBean);
                    totalList.add(listBean);
                }
                projectsAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        if (size < totalCount) {
            initLoadMore();
        }
        initClick();
    }

    private void initLoadMore() {
        projectsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                projectRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<ProjectsBean.DataBean.ListBean> listBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + pageCount;
                            OkHttpUtils.post()
                                    .url(Base.URL)
                                    .addParams("act", "serviceProjectList")
                                    .addParams("data", new serviceProjectList(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                                    .build().execute(new GsonCallBack<ProjectsBean>() {
                                @Override
                                public void onSuccess(ProjectsBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    for (int i = 0; i < size; i++) {
                                        ProjectsBean.DataBean.ListBean listBean = new ProjectsBean.DataBean.ListBean();
                                        name = response.getData().getList().get(i).getName();
                                        content = response.getData().getList().get(i).getContent();
                                        typeDetailName = response.getData().getList().get(i).getTypeDetailName();

                                        listBean.setName(name);
                                        listBean.setContent(content);
                                        listBean.setTypeDetailName(typeDetailName);

                                        listBeans.add(listBean);
                                        totalList.add(listBean);
                                    }
                                    projectsAdapter.addData(listBeans);
                                    projectsAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            projectsAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, projectRecycler);
    }

    private void initClick() {
        projectsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ProjectsActivity.this, ProjectsInfoActivity.class);
                intent.putExtra("name", totalList.get(position).getName());
                intent.putExtra("content", totalList.get(position).getContent());
                intent.putExtra("typeDetailName", totalList.get(position).getTypeDetailName());
                startActivity(intent);
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

    static class serviceProjectList {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;

        public serviceProjectList(String appKey, String timeSpan, String mobileType, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
