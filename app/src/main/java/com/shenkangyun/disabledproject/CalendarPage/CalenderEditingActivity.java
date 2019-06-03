package com.shenkangyun.disabledproject.CalendarPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.ResultBean;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderEditingActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.Journal_title)
    EditText JournalTitle;
    @BindView(R.id.Journal_Start)
    TextView JournalStart;
    @BindView(R.id.Journal_End)
    TextView JournalEnd;
    @BindView(R.id.Journal_content)
    EditText JournalContent;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private SimpleDateFormat dateFormat;

    private String contentTxt;
    private String titleTxt;

    private long calendarid;
    private String title;
    private String content;
    private String startIntent;
    private String endIntent;
    private String Start;
    private String End;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_editing);
        FuncUtil.iniSystemBar(this, R.color.white);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (actionBar != null) {
            toolBarTitle.setText("编辑备忘录");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        calendarid = intent.getLongExtra("Calendarid", 0);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("Content");
        startIntent = intent.getStringExtra("Start");
        endIntent = intent.getStringExtra("End");
        JournalTitle.setText(title);
        JournalContent.setText(content);
        long StartLong = Long.parseLong(startIntent);
        JournalStart.setText(dateFormat.format(StartLong));
        long EndLong = Long.parseLong(endIntent);
        JournalEnd.setText(dateFormat.format(EndLong));
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

    @OnClick({R.id.tv_commit, R.id.Journal_Start, R.id.Journal_End})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                titleTxt = JournalTitle.getText().toString();
                contentTxt = JournalContent.getText().toString();
                if (!TextUtils.isEmpty(titleTxt.trim()) && !TextUtils.isEmpty(contentTxt.trim())) {
                    initHttp();
                }
                break;
            case R.id.Journal_Start:
                DatePickDialog datePickDialog = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                datePickDialog.setYearLimt(80);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        startIntent = String.valueOf(date.getTime());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Start = simpleDateFormat.format(date);
                        JournalStart.setText(Start);
                    }
                });
                datePickDialog.show();
                break;
            case R.id.Journal_End:
                DatePickDialog datePickDialog1 = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                datePickDialog1.setYearLimt(80);
                //设置标题
                datePickDialog1.setTitle("选择时间");
                //设置类型
                datePickDialog1.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog1.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        endIntent = String.valueOf(date.getTime());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        End = simpleDateFormat.format(date);
                        JournalEnd.setText(End);
                    }
                });
                datePickDialog1.show();
                break;
        }
    }

    private void initHttp() {
        String ID = String.valueOf(calendarid);
        OkHttpUtils.post().url(Base.URL)
                .addParams("act", "insertOrUpdateCalendar")
                .addParams("data", new insertOrUpdateCalendar("update", Base.getMD5Str(), Base.getTimeSpan(), "1", Base.getUserID(),
                        Integer.valueOf(ID), titleTxt, contentTxt, startIntent, endIntent).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    Intent intent = new Intent();
                    setResult(100, intent);
                    Toast.makeText(CalenderEditingActivity.this, response.getData().getData(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CalenderEditingActivity.this, response.getData().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    static class insertOrUpdateCalendar {

        private String optionTag;
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private int calendarid;
        private String id;
        private String title;
        private String calendardescribe;
        private String start;
        private String end;

        public insertOrUpdateCalendar(String optionTag, String appKey, String timeSpan, String mobileType, String id, int calendarid,
                                      String title, String calendardescribe, String start, String end) {
            this.optionTag = optionTag;
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.id = id;
            this.calendarid = calendarid;
            this.title = title;
            this.calendardescribe = calendardescribe;
            this.start = start;
            this.end = end;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
