package com.misrobot.mismarketing.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 默认时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static SimpleDateFormat defaultsdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);

    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parse(String param, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(param);
    }

    public Timestamp parseToTimestamp(String param, String format) throws ParseException {
        return new Timestamp(parse(param, format).getTime());
    }

    public Date getFirstTimeOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public Date getFirstTimeOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public Date getLastTimeOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_YEAR, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public Date getLastTimeOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_YEAR, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public Date getTimesMonthmorning(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public Date getEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public Date getFirstTimeOfYear(String year) {
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(year), 0, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public Date getLastTimeOfYear(String year) {
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(year), 11, 31, 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public Date getNextMonthTime(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);
        c.add(Calendar.MONTH, 1);

        return c.getTime();
    }

    public int getYearValue(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);

        return c.get(Calendar.YEAR);
    }

    public int getMonthValue(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);

        return c.get(Calendar.MONTH) + 1;
    }

    public int getDayValue(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);

        return c.get(Calendar.DAY_OF_MONTH) + 1;
    }

    public String getYear(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);

        return Integer.toString(c.get(Calendar.YEAR));
    }

    public String getMonth(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);

        return Integer.toString(c.get(Calendar.MONTH) + 1);
    }

    public String getDay(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);

        return Integer.toString(c.get(Calendar.DAY_OF_MONTH) + 1);
    }

    public Date getFirstTimeOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.MONTH, (month - 1));
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.YEAR, year);

        return c.getTime();
    }

    public Date getLastTimeOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.MONTH, (month - 1));
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_YEAR, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        c.set(Calendar.YEAR, year);

        return c.getTime();
    }

    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得当天24点时间
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); //因为缺省时每星期的第一天是星期日，把每星期的第一天设为星期一
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    public static void main(String[] args) {
        /*System.out.println(getTime4int(DateUtil.getTimesmorning()));
        System.out.println(getTime4int(DateUtil.getTimesnight()));
		System.out.println(getTime4int(DateUtil.getTimesWeekmorning()));
		System.out.println(getTime4int(DateUtil.getTimesWeeknight()));
		System.out.println(getTime4int(DateUtil.getTimesMonthmorning()));
		System.out.println(getTime4int(DateUtil.getTimesMonthnight()));*/

        System.out.println(getCurrentDateStr());
        //System.out.println(convertInt2String(1493340900));
        long expireTime = 1505404800000l;
        long nowTime = 1496759534200l;
        //long  nowTime=gets().getTime();
        double days = (expireTime - nowTime) / 1000.0 / 60 / 60 / 24;
        //int t=(int)days;
        System.out.println((int) days);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(getTime4int(DateUtil.getTimesWeekmorning()));
//        System.out.println("============"+endDate);
//        System.out.println("============"+getTime4int(DateUtil.getTimesWeeknight()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        //cal.setTime(cal.getTime());
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 2);
        String imptimeMi = sdf.format(cal.getTime());
        System.out.println("所在周星期三的日期：" + imptimeMi);
        cal.add(Calendar.DATE, 4);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        String imptimeEnd = sdf.format(cal.getTime());
        System.out.println("所在周星期五的日期：" + imptimeEnd);

        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        System.out.println(df.format(3.12345));

        //new java.text.DecimalFormat("#.00").format(3.1415926);

    }

    /**
     * 返回当前日期所在的周一与周日(字符串类型)
     *
     * @param dayofweek
     * @return
     */
    public static String getDayOfWeek(int dayofweek) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        //cal.setTime(cal.getTime());
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        //System.out.println("所在周星期一的日期："+imptimeBegin);
        if (dayofweek == 1) {
            return imptimeBegin;
        }
        if (dayofweek == 7) {
            cal.add(Calendar.DATE, 6);
            String imptimeEnd = sdf.format(cal.getTime());
            System.out.println("所在周星期五的日期：" + imptimeEnd);
            return imptimeEnd;
        }
        return null;
    }

    /**
     * 返回当前日期所在的周一与周日(Date类型)
     *
     * @param dayofweek
     * @return
     */
    public static Date getDateOfWeek(int dayofweek) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        //cal.setTime(cal.getTime());
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        //System.out.println("所在周星期一的日期："+imptimeBegin);
        if (dayofweek == 1) {
            try {
                Date date = sdf.parse(imptimeBegin);

                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (dayofweek == 7) {
            cal.add(Calendar.DATE, 6);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            String imptimeEnd = sdf.format(cal.getTime());
            System.out.println("所在周日星期的日期：" + imptimeEnd);
            try {
                Date date = sdf.parse(imptimeBegin);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static DateUtil instance;

    private DateUtil() {

    }

    public static DateUtil getInstance() {
        if (null == instance) {
            synchronized (DateUtil.class) {
                if (null == instance) {
                    instance = new DateUtil();
                }
            }
        }

        return instance;
    }

    /**
     * 返回int型值
     *
     * @param time yyyy-MM-dd HH:mm:ss 格式字符串
     * @return 单位秒
     */
    public static int getTime4int(String time) {
        int result = 0;
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = (int) (ft.parse(time).getTime() / 1000);
        } catch (ParseException e) {
        }
        return result;

    }

    /**
     * 返回Date型值
     *
     * @param timeStr yyyy-MM-dd HH:mm:ss 格式字符串
     * @return 日期
     */
    public static Date getTime4Date(String timeStr) {
        Date result = null;
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = ft.parse(timeStr);
        } catch (ParseException e) {
        }
        return result;

    }

    /**
     * 返回int型值
     *
     * @param time 日期
     * @return 单位秒
     */
    public static int getTime4int(Date time) {

        int result = (int) (time.getTime() / 1000);

        return result;

    }

    /**
     * @param time
     * @return
     */
    public static String convertInt2String(int time) {

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(time * 1000L);
        String format = ft.format(date);

        return format;
    }

    /**
     * 获取当前日期和时间
     *
     * @return String
     */
    public static String getCurrentDateStr() {
        Date date = new Date();
        String str = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        str = df.format(date);
        return str;
    }

    public static Date getCurrentDateTime() {
        Date date = new Date();
        defaultsdf.format(date);
        return date;
    }

    /**
     * 获取随机数
     *
     * @return
     */
    public static Long getRandomNum() {
        return Math.round(Math.random() * 8999 + 1000);
    }

    /**
     * 获取当前日期和时间
     *
     * @return String
     */
    public static String getCurrentDate() {
        Date date = new Date();
        String str = defaultsdf.format(date);
        return str;
    }

    /**
     * 获取当前年yyyy
     *
     * @return String
     */
    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }

    /**
     * 获取当前月MM
     *
     * @return String
     */
    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }

    //当前日期的100天后的日期
    public static Date getAddDays() {
        Calendar cld = Calendar.getInstance();
        cld.add(Calendar.DATE, 100);
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MINUTE, 59);
        Date date = cld.getTime();
        return date;
    }

    public List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    public int dayForWeek(Date date) {


        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
}

