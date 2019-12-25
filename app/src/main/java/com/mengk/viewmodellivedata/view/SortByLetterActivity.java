package com.mengk.viewmodellivedata.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.util.JsonDataUtil;
import com.mengk.viewmodellivedata.model.viewmodel.SortModel;

public class SortByLetterActivity extends AbsLifecycleActivity<SortModel> {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rvSortByLetter)
    RecyclerView rvSortByLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        setTitle("按字母排序");

        getTestJson(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sort_by_letter;
    }

    public void getTestJson(Context context) {
        Log.e("===z","1111");
        String json = JsonDataUtil.getJson(context, "sort_by_letter_json.json");
        Log.e("===z","json = " + json);
    }

}
