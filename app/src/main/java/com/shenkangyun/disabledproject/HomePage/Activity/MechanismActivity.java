package com.shenkangyun.disabledproject.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.MechanismBean;
import com.shenkangyun.disabledproject.HomePage.Adapter.MechanismAdapter;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MechanismActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.mechanismRecycler)
    RecyclerView mechanismRecycler;

    private int size;
    private int pageNo = 0;
    private int pageCount = 10;

    private String Id;
    private String name;
    private String phone;
    private String regionID;
    private String cityID;
    private String provinceID;
    private String content;
    private String street;
    private String village;
    private String responsibility;

    private MechanismAdapter mechanismAdapter;
    private LinearLayoutManager layoutManager;
    private List<MechanismBean.DataBean.ListBean> totalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanism);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("服务机构");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        mechanismAdapter = new MechanismAdapter();
        layoutManager = new LinearLayoutManager(this);
        mechanismRecycler.setLayoutManager(layoutManager);
        mechanismRecycler.setAdapter(mechanismAdapter);
        totalList = new ArrayList<>();
    }

    private void initData() {
        final List<MechanismBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "organizeList")
                .addParams("data", new organizeList(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                .build().execute(new GsonCallBack<MechanismBean>() {
            @Override
            public void onSuccess(MechanismBean response) throws JSONException {
                size = response.getData().getList().size();
                for (int i = 0; i < size; i++) {
                    MechanismBean.DataBean.ListBean listBean = new MechanismBean.DataBean.ListBean();
                    Id = response.getData().getList().get(i).getId();
                    name = response.getData().getList().get(i).getName();
                    phone = response.getData().getList().get(i).getPhone();
                    regionID = response.getData().getList().get(i).getRegionID();
                    cityID = response.getData().getList().get(i).getCityID();
                    provinceID = response.getData().getList().get(i).getProvinceID();
                    content = response.getData().getList().get(i).getContent();
                    street = response.getData().getList().get(i).getStreet();
                    village = response.getData().getList().get(i).getVillage();
                    responsibility = response.getData().getList().get(i).getResponsibilityName();

                    listBean.setId(Id);
                    listBean.setName(name);
                    listBean.setRegionID(regionID);
                    listBean.setCityID(cityID);
                    listBean.setProvinceID(provinceID);
                    listBean.setContent(content);
                    listBean.setStreet(street);
                    listBean.setVillage(village);
                    listBean.setPhone(phone);
                    listBean.setResponsibilityName(responsibility);
                    listBeans.add(listBean);
                    totalList.add(listBean);
                }
                mechanismAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        mechanismAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mechanismRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<MechanismBean.DataBean.ListBean> listBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + pageCount;
                            OkHttpUtils.post()
                                    .url(Base.URL)
                                    .addParams("act", "organizeList")
                                    .addParams("data", new organizeList(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                                    .build().execute(new GsonCallBack<MechanismBean>() {
                                @Override
                                public void onSuccess(MechanismBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    for (int i = 0; i < size; i++) {
                                        MechanismBean.DataBean.ListBean listBean = new MechanismBean.DataBean.ListBean();
                                        Id = response.getData().getList().get(i).getId();
                                        name = response.getData().getList().get(i).getName();
                                        phone = response.getData().getList().get(i).getPhone();
                                        regionID = response.getData().getList().get(i).getRegionID();
                                        cityID = response.getData().getList().get(i).getCityID();
                                        provinceID = response.getData().getList().get(i).getProvinceID();
                                        content = response.getData().getList().get(i).getContent();
                                        street = response.getData().getList().get(i).getStreet();
                                        village = response.getData().getList().get(i).getVillage();
                                        responsibility = response.getData().getList().get(i).getResponsibilityName();

                                        listBean.setId(Id);
                                        listBean.setName(name);
                                        listBean.setRegionID(regionID);
                                        listBean.setCityID(cityID);
                                        listBean.setProvinceID(provinceID);
                                        listBean.setContent(content);
                                        listBean.setStreet(street);
                                        listBean.setVillage(village);
                                        listBean.setPhone(phone);
                                        listBean.setResponsibilityName(responsibility);
                                        listBeans.add(listBean);
                                        totalList.add(listBean);
                                    }
                                    mechanismAdapter.addData(listBeans);
                                    mechanismAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            mechanismAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, mechanismRecycler);
    }

    private void initClick() {
        mechanismAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MechanismActivity.this, MechanismInfoActivity.class);
                intent.putExtra("name", totalList.get(position).getName());
                intent.putExtra("regionID", totalList.get(position).getRegionID());
                intent.putExtra("cityID", totalList.get(position).getCityID());
                intent.putExtra("provinceID", totalList.get(position).getProvinceID());
                intent.putExtra("content", totalList.get(position).getContent());
                intent.putExtra("street", totalList.get(position).getStreet());
                intent.putExtra("village", totalList.get(position).getVillage());
                intent.putExtra("phone", totalList.get(position).getPhone());
                intent.putExtra("responsibility", totalList.get(position).getResponsibilityName());
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

    static class organizeList {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;

        public organizeList(String appKey, String timeSpan, String mobileType, String pageNo, String pageCount) {
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
