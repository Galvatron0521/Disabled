package com.shenkangyun.disabledproject.MinePage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ed_content)
    EditText edContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("关于");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initData();
    }

    private void initData() {

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

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        String content = edContent.getText().toString();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "toolApply")
                .addParams("data", new toolApply(Base.getMD5Str(), Base.getTimeSpan(), "1",
                        "402880526203bb40016203c0e2900006", Base.getUserID(), content).toJson())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("123456", "onError: " + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("123456", "onResponse: " + response);
            }
        });
    }

    static class toolApply {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String toolID;
        private String patientID;
        private String applyreason;

        public toolApply(String appKey, String timeSpan, String mobileType, String toolID,
                         String patientID, String applyreason) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.toolID = toolID;
            this.patientID = patientID;
            this.applyreason = applyreason;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
