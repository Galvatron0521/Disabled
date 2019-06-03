package com.shenkangyun.disabledproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.disabledproject.BeanFolder.NewsBean;
import com.shenkangyun.disabledproject.R;

/**
 * Created by Administrator on 2018/2/23.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean.DataBean.NewslistBean, BaseViewHolder> {

    public NewsAdapter() {
        super(R.layout.item_news, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean.NewslistBean item) {
        helper.setText(R.id.news_title, item.getTitle());
    }
}
