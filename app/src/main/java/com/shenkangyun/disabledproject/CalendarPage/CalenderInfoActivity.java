package com.shenkangyun.disabledproject.CalendarPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalenderInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.Journal_title)
    TextView JournalTitle;
    @BindView(R.id.Journal_Start)
    TextView JournalStart;
    @BindView(R.id.Journal_End)
    TextView JournalEnd;
    @BindView(R.id.Journal_content)
    TextView JournalContent;

    private SimpleDateFormat dateFormat;
    private String title;
    private String content;
    private String start;
    private String end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_info);
        FuncUtil.iniSystemBar(this, R.color.white);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (actionBar != null) {
            toolBarTitle.setText("备忘录详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("Content");
        start = intent.getStringExtra("Start");
        end = intent.getStringExtra("End");

        JournalTitle.setText(title);
        JournalContent.setText(content);
        long StartLong = Long.parseLong(start);
        JournalStart.setText(dateFormat.format(StartLong));
        long EndLong = Long.parseLong(end);
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
}
