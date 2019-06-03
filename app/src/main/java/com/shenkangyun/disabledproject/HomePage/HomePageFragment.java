package com.shenkangyun.disabledproject.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.AdBean;
import com.shenkangyun.disabledproject.BeanFolder.NewsBean;
import com.shenkangyun.disabledproject.BeanFolder.URLBean;
import com.shenkangyun.disabledproject.HomePage.Activity.MechanismActivity;
import com.shenkangyun.disabledproject.HomePage.Activity.NearByActivity;
import com.shenkangyun.disabledproject.HomePage.Activity.NewsDetailActivity;
import com.shenkangyun.disabledproject.HomePage.Activity.PolicyActivity;
import com.shenkangyun.disabledproject.HomePage.Activity.ProjectsActivity;
import com.shenkangyun.disabledproject.HomePage.Adapter.NewsAdapter;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.GlideImageLoader;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2018/2/22.
 */

public class HomePageFragment extends Fragment {

    @BindView(R.id.carousel)
    Banner carousel;
    @BindView(R.id.img_mechanism)
    LinearLayout imgMechanism;
    @BindView(R.id.img_policy)
    LinearLayout imgPolicy;
    @BindView(R.id.img_project)
    LinearLayout imgProject;
    @BindView(R.id.homeRecycler)
    RecyclerView homeRecycler;

    private URLBean urlBean;
    private String imgUrl;
    private Gson gson;
    private StringBuilder builder;

    private int pageNo = 0;
    private int pageCount = 5;
    private int size;

    private int id;
    private String title;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager layoutManager;
    private List<NewsBean.DataBean.NewslistBean> totalList;
    private List<String> imgUrls;

    private String[] imageUrl = {"http://img3.imgtn.bdimg.com/it/u=2047777376,2936752451&fm=27&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3477760860,3923392959&fm=27&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=756296638,2900475926&fm=27&gp=0.jpg"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initView();
        initBanner();
        initNews();
        return view;
    }

    private void initView() {
        gson = new Gson();
        totalList = new ArrayList<>();
        imgUrls = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        newsAdapter = new NewsAdapter();
        homeRecycler.setLayoutManager(layoutManager);
        homeRecycler.setAdapter(newsAdapter);

    }

    private void initBanner() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "adList")
                .addParams("data", new AdList(Base.getMD5Str(), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<AdBean>() {
            @Override
            public void onSuccess(AdBean response) throws JSONException {
                for (int i = 0; i < response.getData().getAdlist().size(); i++) {
                    String picUrl = response.getData().getAdlist().get(i).getPicUrl();
                    urlBean = gson.fromJson(picUrl, URLBean.class);
                    for (int j = 0; j < urlBean.getJson().size(); j++) {
                        imgUrl = urlBean.getJson().get(j).getAttachmentUrl();
                        builder = new StringBuilder();
                        builder.append(Base.imageURLs).append(imgUrl);
                        imgUrls.add(builder.toString());
                    }
                }
                initImgData();
            }

            private void initImgData() {
                List<String> strings = Arrays.asList(imageUrl);
                carousel.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                carousel.setImageLoader(new GlideImageLoader());
                //设置图片集合
                if (imgUrls.size() == 0) {
                    carousel.setImages(strings);
                } else {
                    carousel.setImages(imgUrls);
                }
                //设置banner动画效果
                carousel.setBannerAnimation(Transformer.DepthPage);
                //设置自动轮播，默认为true
                carousel.isAutoPlay(true);
                //设置轮播时间
                carousel.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                carousel.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                carousel.start();
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    private void initNews() {
        final List<NewsBean.DataBean.NewslistBean> listBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "newsList")
                .addParams("data", new newsList(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                .build().execute(new GsonCallBack<NewsBean>() {
            @Override
            public void onSuccess(NewsBean response) throws JSONException {
                size = response.getData().getNewslist().size();
                for (int i = 0; i < size; i++) {
                    NewsBean.DataBean.NewslistBean newslistBean = new NewsBean.DataBean.NewslistBean();
                    id = response.getData().getNewslist().get(i).getId();
                    title = response.getData().getNewslist().get(i).getTitle();

                    newslistBean.setId(id);
                    newslistBean.setTitle(title);
                    listBeans.add(newslistBean);
                    totalList.add(newslistBean);
                }
                newsAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        newsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                homeRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<NewsBean.DataBean.NewslistBean> listBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + pageCount;
                            OkHttpUtils.post()
                                    .url(Base.URL)
                                    .addParams("act", "newsList")
                                    .addParams("data", new newsList(Base.getMD5Str(), Base.getTimeSpan(), "1", String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                                    .build().execute(new GsonCallBack<NewsBean>() {
                                @Override
                                public void onSuccess(NewsBean response) throws JSONException {
                                    size = response.getData().getNewslist().size();
                                    for (int i = 0; i < size; i++) {
                                        NewsBean.DataBean.NewslistBean newslistBean = new NewsBean.DataBean.NewslistBean();
                                        id = response.getData().getNewslist().get(i).getId();
                                        title = response.getData().getNewslist().get(i).getTitle();

                                        newslistBean.setId(id);
                                        newslistBean.setTitle(title);

                                        listBeans.add(newslistBean);
                                        totalList.add(newslistBean);
                                    }
                                    newsAdapter.addData(listBeans);
                                    newsAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            newsAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, homeRecycler);
    }

    private void initClick() {
        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("NewsID", totalList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.img_nearby, R.id.img_mechanism, R.id.img_policy, R.id.img_project})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_nearby:
                Intent intentNear = new Intent(getContext(), NearByActivity.class);
                startActivity(intentNear);
                break;
            case R.id.img_mechanism:
                Intent intentMechanism = new Intent(getContext(), MechanismActivity.class);
                startActivity(intentMechanism);
                break;
            case R.id.img_policy:
                Intent intentPolicy = new Intent(getContext(), PolicyActivity.class);
                startActivity(intentPolicy);
                break;
            case R.id.img_project:
                Intent intentProjects = new Intent(getContext(), ProjectsActivity.class);
                startActivity(intentProjects);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        carousel.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        carousel.stopAutoPlay();
    }

    static class AdList {
        private String appKey;
        private String timeSpan;

        public AdList(String appKey, String timeSpan) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class newsList {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;

        public newsList(String appKey, String timeSpan, String mobileType, String pageNo, String pageCount) {
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
