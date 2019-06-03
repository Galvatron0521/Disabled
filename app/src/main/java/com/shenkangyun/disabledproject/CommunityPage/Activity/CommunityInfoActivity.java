package com.shenkangyun.disabledproject.CommunityPage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.CommResultBean;
import com.shenkangyun.disabledproject.BeanFolder.CommunityBean;
import com.shenkangyun.disabledproject.CommunityPage.Adapter.CommentAdapter;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.DataForDisplay;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityInfoActivity extends AppCompatActivity {


    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.View)
    android.view.View View;
    @BindView(R.id.commentTxt)
    ImageView commentTxt;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.supportTxt)
    ImageView supportTxt;
    @BindView(R.id.tv_support)
    TextView tvSupport;
    @BindView(R.id.commentRecycler)
    RecyclerView commentRecycler;
    @BindView(R.id.circleEt)
    EditText circleEt;
    @BindView(R.id.sendIv)
    ImageView sendIv;
    @BindView(R.id.editTextBodyLl)
    LinearLayout editTextBodyLl;

    private String id;
    private String content;
    private String createUser;
    private String createTime;
    private int zanCount;
    private int commentCount;
    private CommentAdapter commentAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> timeList = new ArrayList<>();
    private ArrayList<String> commentList = new ArrayList<>();
    private List<CommunityBean.DataBean.ListBean.CommentListBean> commentListBeans = new ArrayList<>();
    private List<CommunityBean.DataBean.ListBean.CommentListBean> ListBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_info);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("动态详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initIntent();
        initData();
    }

    private void initView() {
        commentAdapter = new CommentAdapter();
        layoutManager = new LinearLayoutManager(this);
        commentRecycler.setLayoutManager(layoutManager);
        commentRecycler.setAdapter(commentAdapter);
    }

    private void initIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        content = intent.getStringExtra("content");
        createUser = intent.getStringExtra("createUser");
        createTime = intent.getStringExtra("createTime");
        zanCount = intent.getIntExtra("zanCount", 0);
        commentCount = intent.getIntExtra("commentCount", 0);
        nameList = intent.getStringArrayListExtra("nameList");
        timeList = intent.getStringArrayListExtra("timeList");
        commentList = intent.getStringArrayListExtra("commentList");
        for (int i = 0; i < commentList.size(); i++) {
            CommunityBean.DataBean.ListBean.CommentListBean commentListBean = new CommunityBean.DataBean.ListBean.CommentListBean();
            commentListBean.setContent(commentList.get(i));
            if (timeList.size() != 0) {
                commentListBean.setCreateTime(timeList.get(i));
            } else {
                commentListBean.setCreateTime("");
            }
            if (nameList.size() != 0) {
                if (TextUtils.isEmpty(nameList.get(i))) {
                    commentListBean.setCreateUser("未命名");
                } else {
                    commentListBean.setCreateUser(nameList.get(i));
                }
            }
            commentListBeans.add(commentListBean);
        }
        commentAdapter.setNewData(commentListBeans);
    }

    private void initData() {
        CharSequence charSequence = Html.fromHtml(content);
        tvContent.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvUserName.setText(createUser);
        tvSupport.setText(String.valueOf(zanCount));
        tvComment.setText(String.valueOf(commentCount));
        if (TextUtils.isEmpty(createTime)) {
            tvTime.setText("");
        } else {
            String display = DataForDisplay.formatDataForDisplay(Long.parseLong(createTime));
            tvTime.setText(display);
        }
        if (zanCount != 0) {
            supportTxt.setImageResource(R.drawable.zan_true);
        }
    }

    @OnClick({R.id.commentTxt, R.id.sendIv})
    public void onViewClicked(android.view.View view) {
        switch (view.getId()) {
            case R.id.commentTxt:
                editTextBodyLl.setVisibility(android.view.View.VISIBLE);
                break;
            case R.id.sendIv:
                String dynamic = circleEt.getText().toString();
                if (!TextUtils.isEmpty(dynamic.trim())) {
                    initComment(dynamic);
                } else {
                    Toast.makeText(this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initComment(String dynamic) {
        ListBeans.clear();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "commentShow")
                .addParams("data", new commentShow(Base.getMD5Str(), Base.getTimeSpan(), "1", Base.getUserID()
                        , Base.getUserName(), id, dynamic).toJson())
                .build().execute(new GsonCallBack<CommResultBean>() {

            @Override
            public void onSuccess(CommResultBean response) throws JSONException {
                String status = response.getStatus();
                CommunityBean.DataBean.ListBean.CommentListBean commentListBean = new CommunityBean.DataBean.ListBean.CommentListBean();
                if ("0".equals(status)) {
                    content = response.getData().getComments().getContent();
                    commentListBean.setContent(content);
                    commentListBean.setCreateUser(Base.getUserName());
                    ListBeans.add(commentListBean);
                    editTextBodyLl.setVisibility(android.view.View.GONE);
                }
                commentAdapter.addData(ListBeans);
            }

            @Override
            public void onError(Exception e) {

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

    static class commentShow {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String raceiverID;
        private String createUser;
        private String showID;
        private String content;

        public commentShow(String appKey, String timeSpan, String createUser, String mobileType, String raceiverID, String showID, String content) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.raceiverID = raceiverID;
            this.createUser = createUser;
            this.showID = showID;
            this.content = content;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
