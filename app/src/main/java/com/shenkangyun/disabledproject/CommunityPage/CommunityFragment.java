package com.shenkangyun.disabledproject.CommunityPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.CommunityBean;
import com.shenkangyun.disabledproject.BeanFolder.ZanRespond;
import com.shenkangyun.disabledproject.CommunityPage.Activity.CommunityInfoActivity;
import com.shenkangyun.disabledproject.CommunityPage.Adapter.CommunityAdapter;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/22.
 */

public class CommunityFragment extends Fragment {


    @BindView(R.id.communityRecycler)
    RecyclerView communityRecycler;
    @BindView(R.id.easyLayout)
    SwipeRefreshLayout easyLayout;
    private int size = 5;
    private int pageNo = 0;
    private int pageCount = 5;

    private CommunityAdapter communityAdapter;
    private LinearLayoutManager layoutManager;
    private List<CommunityBean.DataBean.ListBean> totalList;

    private String id;
    private String name;
    private String content;
    private int commentCount;
    private int zanCount;
    private long createTime;
    private String createUser;
    private List<CommunityBean.DataBean.ListBean.ZanListBean> zanList;
    private List<CommunityBean.DataBean.ListBean.CommentListBean> commentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, null);
        ButterKnife.bind(this, view);
        initView();
        initNetRequest();
        initData();
        return view;
    }

    private void initView() {
        totalList = new ArrayList<>();
        communityAdapter = new CommunityAdapter();
        layoutManager = new LinearLayoutManager(getContext());
        communityRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        communityRecycler.setAdapter(communityAdapter);
        communityRecycler.setLayoutManager(layoutManager);
    }

    private void initNetRequest() {
        pageNo = 0;
        pageCount = 5;
        totalList.clear();
        final List<CommunityBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "communityShowList")
                .addParams("data", new communityShowList(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserName(), "1", String.valueOf(pageNo),
                        String.valueOf(pageCount)).toJson())
                .build().execute(new GsonCallBack<CommunityBean>() {
            @Override
            public void onSuccess(CommunityBean response) throws JSONException {
                size = response.getData().getList().size();
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    CommunityBean.DataBean.ListBean listBean = new CommunityBean.DataBean.ListBean();
                    id = response.getData().getList().get(i).getId();
                    name = response.getData().getList().get(i).getName();
                    content = response.getData().getList().get(i).getContent();
                    commentCount = response.getData().getList().get(i).getCommentCount();
                    zanCount = response.getData().getList().get(i).getZanCount();
                    createTime = response.getData().getList().get(i).getCreateTime();
                    createUser = response.getData().getList().get(i).getCreateUser();
                    zanList = response.getData().getList().get(i).getZanList();
                    commentList = response.getData().getList().get(i).getCommentList();
                    listBean.setId(id);
                    listBean.setName(name);
                    listBean.setContent(content);
                    listBean.setCommentCount(commentCount);
                    listBean.setZanCount(zanCount);
                    listBean.setCreateTime(createTime);
                    listBean.setCreateUser(createUser);
                    listBean.setZanList(zanList);
                    listBean.setCommentList(commentList);
                    listBeans.add(listBean);
                    totalList.add(listBean);
                }
                communityAdapter.setNewData(listBeans);
                if (easyLayout.isRefreshing()) {
                    easyLayout.setRefreshing(false);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        communityAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                communityRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<CommunityBean.DataBean.ListBean> listBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + pageCount;
                            OkHttpUtils.post()
                                    .url(Base.URL)
                                    .addParams("act", "communityShowList")
                                    .addParams("data", new communityShowList(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserName(), "1", String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                                    .build().execute(new GsonCallBack<CommunityBean>() {
                                @Override
                                public void onSuccess(CommunityBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    for (int i = 0; i < response.getData().getList().size(); i++) {
                                        CommunityBean.DataBean.ListBean listBean = new CommunityBean.DataBean.ListBean();
                                        id = response.getData().getList().get(i).getId();
                                        name = response.getData().getList().get(i).getName();
                                        content = response.getData().getList().get(i).getContent();
                                        commentCount = response.getData().getList().get(i).getCommentCount();
                                        zanCount = response.getData().getList().get(i).getZanCount();
                                        createTime = response.getData().getList().get(i).getCreateTime();
                                        createUser = response.getData().getList().get(i).getCreateUser();
                                        zanList = response.getData().getList().get(i).getZanList();
                                        commentList = response.getData().getList().get(i).getCommentList();
                                        listBean.setId(id);
                                        listBean.setName(name);
                                        listBean.setContent(content);
                                        listBean.setCommentCount(commentCount);
                                        listBean.setZanCount(zanCount);
                                        listBean.setCreateTime(createTime);
                                        listBean.setCreateUser(createUser);
                                        listBean.setZanList(zanList);
                                        listBean.setCommentList(commentList);
                                        listBeans.add(listBean);
                                        totalList.add(listBean);
                                    }
                                    communityAdapter.addData(listBeans);
                                    communityAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            communityAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, communityRecycler);
    }

    private void initClick() {
        communityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.comm_content:
                        ArrayList<String> nameList = new ArrayList<>();
                        ArrayList<String> timeList = new ArrayList<>();
                        ArrayList<String> contentList = new ArrayList<>();
                        for (int i = 0; i < totalList.get(position).getCommentList().size(); i++) {
                            String time = totalList.get(position).getCommentList().get(i).getCreateTime();
                            String content = totalList.get(position).getCommentList().get(i).getContent();
                            String userName = totalList.get(position).getCommentList().get(i).getCreateUser();
                            timeList.add(time);
                            nameList.add(userName);
                            contentList.add(content);
                        }
                        Intent intent = new Intent(getContext(), CommunityInfoActivity.class);
                        intent.putExtra("id", totalList.get(position).getId());
                        intent.putExtra("content", totalList.get(position).getContent());
                        intent.putExtra("createUser", totalList.get(position).getCreateUser());
                        intent.putExtra("createTime", totalList.get(position).getCreateTime());
                        intent.putExtra("zanCount", totalList.get(position).getZanCount());
                        intent.putExtra("commentCount", totalList.get(position).getCommentCount());
                        intent.putStringArrayListExtra("nameList", nameList);
                        intent.putStringArrayListExtra("timeList", timeList);
                        intent.putStringArrayListExtra("commentList", contentList);
                        startActivityForResult(intent, 0);
                        break;
                    case R.id.supportTxt:
                        View childView = communityRecycler.getLayoutManager().findViewByPosition(position);
                        ImageView supportTxt = childView.findViewById(R.id.supportTxt);
                        if (totalList.get(position).getZanList().size() > 0) {
                            Toast.makeText(getContext(), "已点赞", Toast.LENGTH_SHORT).show();
                        } else {
                            supportTxt.setImageResource(R.drawable.zan_true);
                            initZan(position);
                            Toast.makeText(getContext(), "你点击了按钮", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    private void initZan(final int position) {
        String showId = totalList.get(position).getId();
        String userName = Base.getUserName();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "onclickZan")
                .addParams("data", new onclickZan(Base.getMD5Str(), Base.getTimeSpan(), "1", "", showId, userName, "0").toJson())
                .build().execute(new GsonCallBack<ZanRespond>() {

            @Override
            public void onSuccess(ZanRespond response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    View childView = communityRecycler.getLayoutManager().findViewByPosition(position);
                    TextView tv_support = childView.findViewById(R.id.tv_support);
                    int supportCount = Integer.parseInt(tv_support.getText().toString());
                    supportCount = supportCount + 1;
                    tv_support.setText(String.valueOf(supportCount));
                    initNetRequest();
                    communityRecycler.smoothScrollToPosition(position);
                } else {
                    Toast.makeText(getContext(), "点赞失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initData() {
        easyLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initNetRequest();
            }
        });
    }


    static class onclickZan {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String id;
        private String showID;
        private String createUser;
        private String delFlag;

        public onclickZan(String appKey, String timeSpan, String mobileType, String id, String showID, String createUser, String delFlag) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.id = id;
            this.showID = showID;
            this.createUser = createUser;
            this.delFlag = delFlag;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class communityShowList {

        private String appKey;
        private String timeSpan;
        private String createUser;
        private String mobileType;
        private String pageNo;
        private String pageCount;

        public communityShowList(String appKey, String timeSpan, String createUser, String mobileType, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.createUser = createUser;
            this.mobileType = mobileType;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
