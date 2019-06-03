package com.shenkangyun.disabledproject.CommunityPage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.PublishResultBean;
import com.shenkangyun.disabledproject.DBFolder.User;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishInfoActivity extends AppCompatActivity {
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.ed_title)
    EditText edTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;

    private String content;
    private String title;
    private String name;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("发布动态");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        users = new ArrayList<>();
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

    private void initRequest() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "publishShow")
                .addParams("data", new publishShow(Base.getMD5Str(), Base.getTimeSpan(), "1", Base.getUserID(),
                        title, content, "", name).toJson())
                .build().execute(new GsonCallBack<PublishResultBean>() {
            @Override
            public void onSuccess(PublishResultBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    Toast.makeText(PublishInfoActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(8, intent);
                    finish();
                } else {
                    Toast.makeText(PublishInfoActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        users.clear();
        users = DataSupport.findAll(User.class);
        name = users.get(0).getName();
        content = edContent.getText().toString();
        title = edTitle.getText().toString();
        if (TextUtils.isEmpty(content) && TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请填写标题或内容", Toast.LENGTH_SHORT).show();
        } else {
            initRequest();
        }
    }

    static class publishShow {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String id;
        private String name;
        private String content;
        private String coverPhoto;
        private String createUser;

        public publishShow(String appKey, String timeSpan, String mobileType, String id, String name,
                           String content, String coverPhoto, String createUser) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.id = id;
            this.name = name;
            this.content = content;
            this.coverPhoto = coverPhoto;
            this.createUser = createUser;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
