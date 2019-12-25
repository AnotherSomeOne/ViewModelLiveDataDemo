package com.mengk.viewmodellivedata.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.util.JsonDataUtil;
import com.mengk.viewmodellivedata.model.designpatterns.ImageLoader;
import com.mengk.viewmodellivedata.model.designpatterns.NetWorkLoad;
import com.mengk.viewmodellivedata.model.viewmodel.BaseViewModel;

public class SortByLetterActivity extends BaseActivity {

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

    public void getTestJson(Context context) {
        Log.e("===z","1111");
        String json = JsonDataUtil.getJson(context, "sort_by_letter_json.json");
        Log.e("===z","json = " + json);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_sort_by_letter;
    }

    @Override
    public BaseViewModel createViewModel() {
        return null;
    }


}
