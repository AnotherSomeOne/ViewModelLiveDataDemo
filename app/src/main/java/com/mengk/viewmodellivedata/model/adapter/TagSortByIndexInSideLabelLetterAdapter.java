package com.mengk.viewmodellivedata.model.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.util.DensityUtil;
import com.mengk.viewmodellivedata.common.util.GlideUtil;
import com.mengk.viewmodellivedata.model.bean.IndexLableLetterBean;


import java.util.List;

/**
 * Desc 球队标签库内层adapter
 * Date 2019/11/7
 * author mengk
 */
public class TagSortByIndexInSideLabelLetterAdapter extends BaseQuickAdapter<IndexLableLetterBean, BaseViewHolder> {

    public TagSortByIndexInSideLabelLetterAdapter(@Nullable List<IndexLableLetterBean> data) {
        super(R.layout.item_tag_in_side, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexLableLetterBean item) {
        if (item == null) return;
        RelativeLayout rlTagRootView = helper.getView(R.id.rl_tag_root_view);
        setMargin(rlTagRootView);

        TextView tvTitle = helper.getView(R.id.tv_title_in_side);
        String cnAlias = item.getLable();
        tvTitle.setText(cnAlias);

        ImageView imageView = helper.getView(R.id.iv_icon_in_side);
        Glide.with(getContext()).load(item.getPicUrl())
                .into(imageView);

    }

    private void setMargin(RelativeLayout rlTagRootView) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) rlTagRootView.getLayoutParams();
        layoutParams.bottomMargin = DensityUtil.dp2px(15);
        layoutParams.topMargin = 0;
        layoutParams.leftMargin = DensityUtil.dp2px(12);
    }

}
