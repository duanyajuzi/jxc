package com.gesoft.util;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
/**
 * Created by WCL on 2017/6/28.
 */
public class DateConvertUtils   {
    public static Date parse(String dateString, String dateFormat)
    {
        return parse(dateString, dateFormat, Date.class);
    }

    public static Date parse(String dateString, String dateFormat, Class<Date> targetResultType)
    {
        if (StringUtils.isEmpty(dateString))
        {
            return null;
        }
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            long time = df.parse(dateString).getTime();
            Date date1 = (Date)targetResultType.getConstructor(new Class[] { Long.TYPE }).newInstance(new Object[] { Long.valueOf(time) });
            return date1;
        } catch (ParseException e) {
            String errorInfo = "cannot use dateformat:" + dateFormat + " parse datestring:" + dateString;
            throw new IllegalArgumentException(errorInfo, e);
        }
        catch (Exception e) {
        }
        return null;
    }

    public static String format(Date date, String dateFormat)
    {
        if (date == null)
            return null;
        return new SimpleDateFormat(dateFormat).format(date);
    }
}
