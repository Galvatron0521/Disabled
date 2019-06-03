package com.shenkangyun.disabledproject.HomePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.PolicyBean;
import com.shenkangyun.disabledproject.HomePage.Adapter.PolicyAdapter;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.shenkangyun.disabledproject.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/7.
 */

public class PolicyFragment extends Fragment {

    @BindView(R.id.policyRecycler)
    RecyclerView policyRecycler;
    private int detailCode;
    private PolicyAdapter policyAdapter;
    private LinearLayoutManager layoutManager;

    private String title;
    private String content;
    private int size = 8;
    private int pageNo = 0;
    private int pageCount = 8;
    private int totalCount;
    private List<PolicyBean.DataBean.ListBean> totalList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_policy, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {
        totalList = new ArrayList<>();
        Bundle bundle = getArguments();
        detailCode = bundle.getInt("typeDetailCode");
        policyAdapter = new PolicyAdapter();
        layoutManager = new LinearLayoutManager(getContext());
        policyRecycler.setLayoutManager(layoutManager);
        policyRecycler.setAdapter(policyAdapter);
    }

    private void initData() {
        final List<PolicyBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "policyList")
                .addParams("data", new policyList(Base.getMD5Str(), Base.getTimeSpan(), detailCode, String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                .build().execute(new GsonCallBack<PolicyBean>() {
            @Override
            public void onSuccess(PolicyBean response) throws JSONException {
                totalCount = response.getData().getTotalCount();
                size = response.getData().getList().size();
                for (int i = 0; i < size; i++) {
                    PolicyBean.DataBean.ListBean listBean = new PolicyBean.DataBean.ListBean();
                    title = response.getData().getList().get(i).getTitle();
                    content = response.getData().getList().get(i).getContent();
                    listBean.setTitle(title);
                    listBean.setContent(content);
                    listBeans.add(listBean);
                }
                policyAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        if (size < totalCount) {
            initLoadMore();
        }
    }

    private void initLoadMore() {
        policyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                policyRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<PolicyBean.DataBean.ListBean> listBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + pageCount;
                            OkHttpUtils.post()
                                    .url(Base.URL)
                                    .addParams("act", "policyList")
                                    .addParams("data", new policyList(Base.getMD5Str(), Base.getTimeSpan(), detailCode, String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                                    .build().execute(new GsonCallBack<PolicyBean>() {
                                @Override
                                public void onSuccess(PolicyBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    for (int i = 0; i < size; i++) {
                                        PolicyBean.DataBean.ListBean newslistBean = new PolicyBean.DataBean.ListBean();
                                        content = response.getData().getList().get(i).getContent();
                                        title = response.getData().getList().get(i).getTitle();

                                        newslistBean.setTitle(title);
                                        newslistBean.setContent(content);

                                        listBeans.add(newslistBean);
                                    }
                                    policyAdapter.addData(listBeans);
                                    policyAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            policyAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, policyRecycler);
    }

    static class policyList {

        private String appKey;
        private String timeSpan;
        private int typeDetailCode;
        private String pageNo;
        private String pageCount;

        public policyList(String appKey, String timeSpan, int typeDetailCode, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.typeDetailCode = typeDetailCode;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
