package com.shenkangyun.disabledproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.disabledproject.BeanFolder.MechanismBean;
import com.shenkangyun.disabledproject.R;

/**
 * Created by Administrator on 2018/2/23.
 */

public class MechanismAdapter extends BaseQuickAdapter<MechanismBean.DataBean.ListBean, BaseViewHolder> {


    public MechanismAdapter() {
        super(R.layout.item_mechanism, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MechanismBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_title, item.getName());
    }
}
