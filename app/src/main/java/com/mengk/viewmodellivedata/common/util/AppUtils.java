package com.mengk.viewmodellivedata.common.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.mengk.viewmodellivedata.BaseApplication;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import kotlin.Suppress;

import static android.text.TextUtils.isEmpty;

/**
 * Desc: <App级别的工具类，提供系统的Context和常用的工具类>
 * Author: JS-Barder
 * Created On: 2019/10/27 13:22
 */
public class AppUtils {
    private static final String TAG = "AppUtils";
    public static AppUtils INSTANCE = AppUtils.INSTANCE;


    @Suppress(names = "DEPRECATION")
    private static AppInfo getAppInfo(PackageManager manager, PackageInfo info) {
        if (manager == null || info == null) {
            return null;
        }
        ApplicationInfo applicationInfo = info.applicationInfo;
        String packageName = info.packageName;
        String name = applicationInfo.loadLabel(manager).toString();
        Drawable icon = applicationInfo.loadIcon(manager);
        String packagePath = applicationInfo.sourceDir;
        String versionName = info.versionName;
        int versionCode = info.versionCode;
        boolean isSystem = (ApplicationInfo.FLAG_SYSTEM & applicationInfo.flags) != 0;
        return new AppInfo(packageName, name, icon, packagePath, versionName, versionCode, isSystem);
    }

    public static class AppInfo {
        public String packageName;
        public String name;
        public Drawable icon;
        public String packagePath;
        public String versionName;
        public int versionCode;
        public boolean isSystem;

        public AppInfo() {
        }

        public AppInfo(String packageName, String name, Drawable icon, String packagePath, String versionName, int versionCode, boolean isSystem) {
            this.packageName = packageName;
            this.name = name;
            this.icon = icon;
            this.packagePath = packagePath;
            this.versionName = versionName;
            this.versionCode = versionCode;
            this.isSystem = isSystem;
        }

        @NonNull
        @Override
        public String toString() {
            return "pkg name: " + packageName +
                    "\napp icon: " + icon +
                    "\napp name: " + name +
                    "\napp path: " + packagePath +
                    "\napp v name: " + versionName +
                    "\napp v code: " + versionCode +
                    "\nis system: " + isSystem;
        }
    }
}
