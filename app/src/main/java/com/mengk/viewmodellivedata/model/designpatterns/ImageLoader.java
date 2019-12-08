package com.mengk.viewmodellivedata.model.designpatterns;

/**
 * Desc
 * Date 2019/12/8
 * author mengk
 */
public class ImageLoader {


    IStrategy strategy;


    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void load(){
        strategy.load();
    }

}
