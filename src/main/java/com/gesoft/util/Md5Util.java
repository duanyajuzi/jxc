package com.gesoft.util;

import java.util.UUID;

/**
 * Created by lz on 2018-3-1.
 */
public class Md5Util {
    public static String UUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
