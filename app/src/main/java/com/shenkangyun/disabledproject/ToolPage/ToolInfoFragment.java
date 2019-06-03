package com.shenkangyun.disabledproject.ToolPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BaseFolder.MyApp;
import com.shenkangyun.disabledproject.BeanFolder.ProjectsBean;
import com.shenkangyun.disabledproject.BeanFolder.ToolBean;
import com.shenkangyun.disabledproject.BeanFolder.ToolImgBean;
import com.shenkangyun.disabledproject.HomePage.Activity.ProjectsActivity;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.ToolPage.Activity.ToolDetailsActivity;
import com.shenkangyun.disabledproject.ToolPage.Adapter.ToolAdapter;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ToolInfoFragment extends Fragment {

    @BindView(R.id.toolRecycler)
    RecyclerView toolRecycler;

    private int size = 10;
    private int pageNo = 0;
    private int pageCount = 10;
    private int totalCount;

    private String id;
    private String name;
    private String content;
    private String imageListURL;
    private int serviceObject;

    private int detailCode;
    private Gson gson;
    private StringBuilder builder;
    private ArrayList<String> imgList;
    private ToolAdapter toolAdapter;
    private ToolImgBean toolImgBean;
    private StaggeredGridLayoutManager layoutManager;
    private ArrayList<List<String>> arrayList;
    private List<ToolBean.DataBean.ToolListBean> totalList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool_info, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {
        gson = new Gson();
        arrayList = new ArrayList<>();
        totalList = new ArrayList<>();
        Bundle bundle = getArguments();
        detailCode = bundle.getInt("typeDetailCode");
        toolAdapter = new ToolAdapter();
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        toolRecycler.setLayoutManager(layoutManager);
        toolRecycler.setAdapter(toolAdapter);
    }

    private void initData() {
        final List<ToolBean.DataBean.ToolListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "toolList")
                .addParams("data", new toolList(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo),
                        String.valueOf(pageCount), detailCode).toJson())
                .build().execute(new GsonCallBack<ToolBean>() {
            @Override
            public void onSuccess(ToolBean response) throws JSONException {
                size = response.getData().getToolList().size();
                totalCount = response.getData().getTotalCount();
                for (int i = 0; i < size; i++) {
                    ToolBean.DataBean.ToolListBean listBean = new ToolBean.DataBean.ToolListBean();

                    id = response.getData().getToolList().get(i).getId();
                    name = response.getData().getToolList().get(i).getName();
                    content = response.getData().getToolList().get(i).getContent();
                    imageListURL = response.getData().getToolList().get(i).getImageListURL();
                    serviceObject = response.getData().getToolList().get(i).getServiceObject();

                    toolImgBean = gson.fromJson(imageListURL, ToolImgBean.class);
                    String attachmentUrl = toolImgBean.getJson().get(0).getAttachmentUrl();
                    builder = new StringBuilder();
                    builder.append(Base.ImgURl).append(attachmentUrl);
                    for (int j = 0; j < toolImgBean.getJson().size(); j++) {
                        imgList = new ArrayList<>();
                        String url = toolImgBean.getJson().get(j).getAttachmentUrl();
                        builder = new StringBuilder();
                        builder.append(Base.imageURLs).append(url);
                        imgList.add(builder.toString());
                        arrayList.add(imgList);
                    }
                    listBean.setId(id);
                    listBean.setName(name);
                    listBean.setContent(content);
                    listBean.setServiceObject(serviceObject);
                    listBean.setImageListURL(builder.toString());
                    listBeans.add(listBean);
                    totalList.add(listBean);
                }
                toolAdapter.setNewData(listBeans);
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

    private void initClick() {
        toolAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<String> strings = arrayList.get(position);
                Intent intent = new Intent(getContext(), ToolDetailsActivity.class);
                intent.putExtra("id", totalList.get(position).getId());
                intent.putExtra("name", totalList.get(position).getName());
                intent.putExtra("content", totalList.get(position).getContent());
                intent.putStringArrayListExtra("imgList", (ArrayList<String>) strings);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void initLoadMore() {
        toolAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                toolRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<ToolBean.DataBean.ToolListBean> listBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + pageCount;
                            OkHttpUtils.post()
                                    .url(Base.URL)
                                    .addParams("act", "toolList")
                                    .addParams("data", new toolList(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), detailCode).toJson())
                                    .build().execute(new GsonCallBack<ToolBean>() {
                                @Override
                                public void onSuccess(ToolBean response) throws JSONException {
                                    size = response.getData().getToolList().size();
                                    for (int i = 0; i < size; i++) {
                                        ToolBean.DataBean.ToolListBean listBean = new ToolBean.DataBean.ToolListBean();

                                        id = response.getData().getToolList().get(i).getId();
                                        name = response.getData().getToolList().get(i).getName();
                                        content = response.getData().getToolList().get(i).getContent();
                                        imageListURL = response.getData().getToolList().get(i).getImageListURL();
                                        serviceObject = response.getData().getToolList().get(i).getServiceObject();

                                        ToolImgBean toolImgBean = gson.fromJson(imageListURL, ToolImgBean.class);
                                        String attachmentUrl = toolImgBean.getJson().get(0).getAttachmentUrl();
                                        builder = new StringBuilder();
                                        builder.append(Base.ImgURl).append(attachmentUrl);
                                        for (int j = 0; j < toolImgBean.getJson().size(); j++) {
                                            imgList = new ArrayList<>();
                                            String url = toolImgBean.getJson().get(j).getAttachmentUrl();
                                            builder = new StringBuilder();
                                            builder.append(Base.imageURLs).append(url);
                                            imgList.add(builder.toString());
                                            arrayList.add(imgList);
                                        }
                                        listBean.setId(id);
                                        listBean.setName(name);
                                        listBean.setContent(content);
                                        listBean.setServiceObject(serviceObject);
                                        listBean.setImageListURL(builder.toString());
                                        listBeans.add(listBean);
                                        totalList.add(listBean);
                                    }
                                    toolAdapter.addData(listBeans);
                                    toolAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            toolAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, toolRecycler);
    }

    static class toolList {

        private String appKey;
        private String timeSpan;
        private String pageNo;
        private String pageCount;
        private int serviceObject;

        public toolList(String appKey, String timeSpan, String pageNo, String pageCount, int serviceObject) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.serviceObject = serviceObject;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
