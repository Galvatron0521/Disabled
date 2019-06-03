package com.shenkangyun.disabledproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.disabledproject.BeanFolder.ProjectsBean;
import com.shenkangyun.disabledproject.R;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ProjectsAdapter extends BaseQuickAdapter<ProjectsBean.DataBean.ListBean, BaseViewHolder> {
    public ProjectsAdapter() {
        super(R.layout.item_projects, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectsBean.DataBean.ListBean item) {
        helper.setText(R.id.type_title, item.getTypeDetailName());
    }
}
