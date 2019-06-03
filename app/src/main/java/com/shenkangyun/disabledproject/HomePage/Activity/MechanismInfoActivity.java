package com.shenkangyun.disabledproject.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BeanFolder.JsonBean;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GetJsonDataUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MechanismInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.mechanism_name)
    TextView mechanismName;
    @BindView(R.id.mechanism_province)
    TextView mechanismProvince;
    @BindView(R.id.mechanism_city)
    TextView mechanismCity;
    @BindView(R.id.mechanism_region)
    TextView mechanismRegion;
    @BindView(R.id.mechanism_content)
    TextView mechanismContent;
    @BindView(R.id.mechanism_phone)
    TextView mechanismPhone;
    @BindView(R.id.mechanism_responsibility)
    TextView mechanismResponsibility;

    private String name;
    private String regionID;
    private String cityID;
    private String provinceID;
    private String content;
    private String street;
    private String village;
    private String phone;
    private String responsibility;

    private int proPosition;
    private int cityPosition;

    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanism_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("机构详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        regionID = intent.getStringExtra("regionID");
        cityID = intent.getStringExtra("cityID");
        provinceID = intent.getStringExtra("provinceID");
        content = intent.getStringExtra("content");
        street = intent.getStringExtra("street");
        village = intent.getStringExtra("village");
        phone = intent.getStringExtra("phone");
        responsibility = intent.getStringExtra("responsibility");
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    private void initJsonData() {
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int i = 0; i < jsonBean.size(); i++) {
            String proId = jsonBean.get(i).getId();
            if (proId.equals(provinceID)) {
                provinceID = jsonBean.get(i).getName();
                proPosition = i;
                break;
            }
        }
        List<JsonBean.CitylistBean> citylist = jsonBean.get(proPosition).getCitylist();
        for (int i = 0; i < citylist.size(); i++) {
            String cityId = citylist.get(i).getId();
            if (cityId.equals(cityID)) {
                cityID = citylist.get(i).getName();
                cityPosition = i;
                break;
            }
        }
        List<JsonBean.CitylistBean.RegionlistBean> regionlist = citylist.get(cityPosition).getRegionlist();
        for (int i = 0; i < regionlist.size(); i++) {
            String regionId = regionlist.get(i).getId();
            if (regionId.equals(regionID)) {
                regionID = regionlist.get(i).getName();
                proPosition = i;
                break;
            }
        }

        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("provinceID", provinceID);
        bundle.putString("cityID", cityID);
        bundle.putString("regionID", regionID);
        message.setData(bundle);
        message.what = 1;
        handler.sendMessage(message);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle data = msg.getData();
                    String provinceID = data.getString("provinceID");
                    String cityID = data.getString("cityID");
                    String regionID = data.getString("regionID");
                    CharSequence charSequence = Html.fromHtml(content);
                    mechanismContent.setText(charSequence);
                    //该语句在设置后必加，不然没有任何效果
                    mechanismContent.setMovementMethod(LinkMovementMethod.getInstance());
                    mechanismName.setText(name);
                    mechanismProvince.setText(provinceID);
                    mechanismCity.setText(cityID);
                    mechanismRegion.setText(regionID);
                    mechanismPhone.setText(phone);
                    mechanismResponsibility.setText(responsibility);
                    break;
            }
        }
    };

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
            }
        }
    };


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
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
