package com.gesoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lz on 2018-3-14.
 */
public class StringOrderNoUtil {
    private static long tmpID = 1;
    
    private static boolean tmpIDlocked = false;
    
    public static String getOrderNo()
    {
        long ltime = 1;
        while (true)
        {
            if(tmpIDlocked == false)
            {
                tmpIDlocked = true;
                //当前：（年、月、日、时、分、秒、毫秒）*10000
                ltime = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date()).toString()) * 10000;
                if(tmpID < ltime)
                {
                    tmpID = ltime;
                }
                else
                {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return String.valueOf(ltime);
            }
        }
    }
}
