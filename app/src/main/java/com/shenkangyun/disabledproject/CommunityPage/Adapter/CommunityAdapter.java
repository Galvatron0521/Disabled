package com.shenkangyun.disabledproject.CommunityPage.Adapter;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.disabledproject.BeanFolder.CommunityBean;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.DataForDisplay;

/**
 * Created by Administrator on 2018/2/24.
 */

public class CommunityAdapter extends BaseQuickAdapter<CommunityBean.DataBean.ListBean, BaseViewHolder> {
    public CommunityAdapter() {
        super(R.layout.item_community, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean.DataBean.ListBean item) {

        TextView tvContent = helper.getView(R.id.tv_content);
        CharSequence charSequence = Html.fromHtml(item.getContent());
        tvContent.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        long createTime = item.getCreateTime();
        if (createTime == 0) {
            helper.setText(R.id.tv_time, "");
        } else {
            String display = DataForDisplay.formatDataForDisplay(createTime);
            helper.setText(R.id.tv_time, display);
        }
        if (item.getZanList().size() != 0) {
            Log.i("123456", "convert: " + item.getZanList().size());
            helper.setImageResource(R.id.supportTxt, R.drawable.zan_true);
        } else {
            helper.setImageResource(R.id.supportTxt, R.drawable.zan);
        }

        helper.setText(R.id.tv_userName, item.getCreateUser());
        helper.setText(R.id.tv_comment, String.valueOf(item.getCommentCount()));
        helper.setText(R.id.tv_support, String.valueOf(item.getZanCount()));
        helper.addOnClickListener(R.id.comm_content);
        helper.addOnClickListener(R.id.supportTxt);
    }
}
