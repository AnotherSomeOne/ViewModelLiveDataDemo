package com.mengk.viewmodellivedata.model.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.model.bean.IndexLableLetterBean;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexListLableLetterBean;
import org.jetbrains.annotations.NotNull;


import java.util.List;

/**
 * Desc 球队标签库外层adapter
 * Date 2019/11/7
 * author mengk
 */
public class TagSortByIndexOutSideLabelLetterAdapter extends BaseQuickAdapter<OutSideIndexListLableLetterBean, BaseViewHolder> {


    private TagSortByIndexInSideLabelLetterAdapter tagSortByIndexInSideAdapter;

    public TagSortByIndexOutSideLabelLetterAdapter(@Nullable List<OutSideIndexListLableLetterBean> data) {
        super(R.layout.item_tag_out_side, data);
    }

    public TagSortByIndexInSideLabelLetterAdapter getTagSortByIndexInSideAdapter() {
        return tagSortByIndexInSideAdapter;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper,
                           @org.jetbrains.annotations.Nullable OutSideIndexListLableLetterBean item) {
        if (item == null) return;

        helper.getView(R.id.view_info_tag_line).setVisibility(View.INVISIBLE);

        TextView tvTitle = helper.getView(R.id.tv_title_out_side);
        //这个title一定不为空
        tvTitle.setText(item.getTitle());
        List<IndexLableLetterBean> list = item.getList();
        if (list == null) return;
        RecyclerView recyclerView = helper.getView(R.id.rv_inside);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
//        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setLayoutManager(flexboxLayoutManager);
        tagSortByIndexInSideAdapter = new TagSortByIndexInSideLabelLetterAdapter(list);
        recyclerView.setAdapter(tagSortByIndexInSideAdapter);
        tagSortByIndexInSideAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.rl_tag_root_view) {
                Object bean = adapter.getItem(position);
                if (bean instanceof IndexLableLetterBean) {
                    if (onClickTagListener != null) {
                        onClickTagListener.clickTag(view,position,(IndexLableLetterBean) bean);
                    }
                }
            }
        });
    }

    public interface OnClickTagListener {
        void clickTag(View view, int position, IndexLableLetterBean bean);
    }

    private OnClickTagListener onClickTagListener;

    public void setOnClickTagListener(OnClickTagListener onClickTagListener) {
        this.onClickTagListener = onClickTagListener;
    }
}
