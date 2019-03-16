package com.lx.eims.util;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 15:01
 * description:日期处理工具类,使用Jodom日期
 */
public class DateUtils {
    /**
     * 日期模板
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间模板
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     *
     * @param date
     * @return 返回 yyyy-MM-dd
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * @param date
     * @param pattern
     * @return 返回 yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date, String pattern) {
        // 存在线程安全问题
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 测试一下.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(format(new Date(),DATE_TIME_PATTERN));
    }
}
