package com.yb.ballworld.baselib.utils

import android.app.Application
import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.mengk.viewmodellivedata.common.util.AppUtils
import java.security.AccessController.getContext
import com.mengk.viewmodellivedata.BaseApplication




object ViewUtils {

    private val TAG: String = ViewUtils::class.java.simpleName
    /**
     * 获取颜色
     */
    fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(getApplication(), id)
    }



    fun getApplication(): Application {
        return BaseApplication.getApplication()
    }

}