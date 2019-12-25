package com.mengk.viewmodellivedata.common.util;

import java.util.UUID;

public class StringUtil {

    public static String getEventKey() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
