package com.shenkangyun.disabledproject.CommunityPage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.CommunityBean;
import com.shenkangyun.disabledproject.R;

/**
 * Created by Administrator on 2018/3/27.
 */

public class CommentAdapter extends BaseQuickAdapter<CommunityBean.DataBean.ListBean.CommentListBean, BaseViewHolder> {
    public CommentAdapter() {
        super(R.layout.item_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean.DataBean.ListBean.CommentListBean item) {
        if (item.getCreateUser().equals("1")) {
            helper.setText(R.id.tv_userName, Base.getUserName());
        }
        helper.setText(R.id.tv_time, item.getCreateTime());
        helper.setText(R.id.tv_content, item.getContent());
    }
}
