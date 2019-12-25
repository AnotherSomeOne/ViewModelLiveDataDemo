package com.mengk.viewmodellivedata.model.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsViewModel;
import com.mengk.viewmodellivedata.model.bean.DelayTimeBean;
import com.mengk.viewmodellivedata.model.bean.IndexLableLetterBean;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexLableLetterBean;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexListLableLetterBean;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description 字母排序viewModel
 */
public class SortModel extends AbsViewModel implements LifecycleObserver {


    public SortModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 获取index字母排序的数据
     * @param labelLetterOutSideList
     * @return
     */
    public List<String> getIndexListData(List<OutSideIndexListLableLetterBean> labelLetterOutSideList) {
        List<String> indexList = new ArrayList<>();
        for (OutSideIndexListLableLetterBean outSideIndexListLableLetterBean : labelLetterOutSideList) {
            indexList.add(outSideIndexListLableLetterBean.getTitle());
        }
        return indexList;
    }

    /**
     * 组装数据--二级页面按字母排序的数据组装
     * @param sourceBean
     * @return
     */
    public List<OutSideIndexListLableLetterBean> getOutSideListLableLetterData(OutSideIndexLableLetterBean sourceBean) {
        List<OutSideIndexListLableLetterBean> list = new ArrayList<>();
        addResData("热",list,sourceBean.getLableFilterCountList());
        addResData("A",list,sourceBean.getA());
        addResData("B",list,sourceBean.getB());
        addResData("C",list,sourceBean.getC());
        addResData("D",list,sourceBean.getD());
        addResData("E",list,sourceBean.getE());
        addResData("F",list,sourceBean.getF());
        addResData("G",list,sourceBean.getG());
        addResData("H",list,sourceBean.getH());
        addResData("I",list,sourceBean.getI());
        addResData("J",list,sourceBean.getJ());
        addResData("K",list,sourceBean.getK());
        addResData("L",list,sourceBean.getL());
        addResData("M",list,sourceBean.getM());
        addResData("N",list,sourceBean.getN());
        addResData("O",list,sourceBean.getO());
        addResData("P",list,sourceBean.getP());
        addResData("Q",list,sourceBean.getQ());
        addResData("R",list,sourceBean.getR());
        addResData("S",list,sourceBean.getS());
        addResData("T",list,sourceBean.getT());
        addResData("U",list,sourceBean.getU());
        addResData("V",list,sourceBean.getV());
        addResData("W",list,sourceBean.getW());
        addResData("X",list,sourceBean.getX());
        addResData("Y",list,sourceBean.getY());
        addResData("Z",list,sourceBean.getZ());

        return list;
    }


    private void addResData(String title,List<OutSideIndexListLableLetterBean> list,List<IndexLableLetterBean> resultList) {
        if (isNotNull(resultList)) {
            OutSideIndexListLableLetterBean bean = new OutSideIndexListLableLetterBean();
            bean.setTitle(title);
            bean.setList(resultList);
            list.add(bean);
        }
    }


    private boolean isNotNull(List list) {
        return list != null && list.size() != 0;
    }

}
