package com.shenkangyun.disabledproject.ToolPage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.disabledproject.DBFolder.LeaseBean;
import com.shenkangyun.disabledproject.R;

/**
 * Created by Administrator on 2018/3/8.
 */

public class LeaseAdapter extends BaseQuickAdapter<LeaseBean, BaseViewHolder> {
    public LeaseAdapter() {
        super(R.layout.item_lease, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeaseBean item) {
        helper.setText(R.id.lease_title, item.getTitle());
        helper.setText(R.id.lease_content, item.getContent());
    }
}
