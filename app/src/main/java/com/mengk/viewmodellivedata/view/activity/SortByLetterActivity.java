package com.mengk.viewmodellivedata.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.google.gson.Gson;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.util.JsonDataUtil;
import com.mengk.viewmodellivedata.model.adapter.TagSortByIndexOutSideLabelLetterAdapter;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexLableLetterBean;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexListLableLetterBean;
import com.mengk.viewmodellivedata.model.viewmodel.SortModel;
import com.mengk.viewmodellivedata.view.widget.SideIndexBar;

import java.util.ArrayList;
import java.util.List;

public class SortByLetterActivity extends AbsLifecycleActivity<SortModel> {

    @BindView(R.id.rvSortByLetter)
    RecyclerView rvOutSideList;
    @BindView(R.id.tvSideBarOverlay)
    TextView tvSideBarOverlay;
    @BindView(R.id.sideIndexBar)
    SideIndexBar sideIndexBar;
    private LinearLayoutManager layoutManager;
    private TagSortByIndexOutSideLabelLetterAdapter outSideLebelLetterAdapter;
    private List<OutSideIndexListLableLetterBean> labelLetterOutSideList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("按字母排序");
        layoutManager = new LinearLayoutManager(this);
        rvOutSideList.setLayoutManager(layoutManager);
        outSideLebelLetterAdapter = new TagSortByIndexOutSideLabelLetterAdapter(labelLetterOutSideList);
        rvOutSideList.setAdapter(outSideLebelLetterAdapter);
        getTestJson(this);
        //绑定列表和SideIndexBar事件
        bindRvSideIndexBar();
    }


    /**
     * 绑定列表和BfSideIndexBar事件
     */
    private void bindRvSideIndexBar() {
        sideIndexBar.setOverlayTextView(tvSideBarOverlay).setOnIndexChangedListener((index, position) -> layoutManager.scrollToPositionWithOffset(position,0));

        rvOutSideList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sideIndexBar.setScrollPosition(layoutManager.findFirstVisibleItemPosition());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sort_by_letter;
    }

    public void getTestJson(Context context) {
        String json = JsonDataUtil.getJson(context, "sort_by_letter_json.json");
        Log.e("===z","json = " + json);
        Gson gson = new Gson();
        OutSideIndexLableLetterBean outSideIndexLableLetterBean = gson.fromJson(json, OutSideIndexLableLetterBean.class);
        List<OutSideIndexListLableLetterBean> outSideListLableLetterData = mViewModel.getOutSideListLableLetterData(outSideIndexLableLetterBean);
        if (outSideListLableLetterData != null && outSideListLableLetterData.size() != 0) {
            //绑定adapter数据 并筛选上个页面选中的数据
            outSideLebelLetterAdapter.setNewData(outSideListLableLetterData);
            //绑定字母排序的数据
            sideIndexBar.setIndexItems(mViewModel.getIndexListData(labelLetterOutSideList));
            sideIndexBar.postInvalidate();
        }
    }

}
