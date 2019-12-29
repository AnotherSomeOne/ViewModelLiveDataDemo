package com.mengk.viewmodellivedata.common.util;

import android.content.Context;
import android.content.Intent;

public class JumpUtl {
    public static void navigate(Context context,Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
