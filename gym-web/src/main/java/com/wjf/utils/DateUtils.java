package com.wjf.utils;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: 日期处理工具类</p>
 * <p>Description: </p>
 *
 * @author libin293
 * @version 1.0
 */
public class DateUtils {

    /**
     * 日期格式
     */
    public enum DTFormat {
        MM_dd("MM-dd"), yy_MM_dd("yy-MM-dd"), //
        yy_MM_dd_HH("yy-MM-dd HH"), //
        yy_MM_dd_HH_mm("yy-MM-dd HH:mm"), //
        yy_MM_dd_HH_mm_ss("yy-MM-dd HH:mm:ss"), //
        yyyy_MM_dd("yyyy-MM-dd"), //
        yyyy_MM_dd_HH("yyyy-MM-dd HH"), //
        yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"), //
        yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"), //
        MM_dd_HH_mm("MM-dd HH:mm"), //
        HH_mm("HH:mm"), //
        HH_mm_ss("HH:mm:ss"), //
        yyMMdd("yyMMdd"), //
        yyyyMMdd("yyyyMMdd"), //
        HHmmss("HHmmss"), //
        HH("HH"), //
        yyyyMMddHHmmss("yyyyMMddHHmmss"), //
        constomFormat("yyyy年MM月dd日HH点"), //
        yyyy("yyyy"), //
        yyyy_MM("yyyy-MM"), //
        HH_MM("HH:MM");

        private String format;
        private int length;

        DTFormat(String format) {
            this.format = format;
            this.length = format.length();
        }

        public String getFormat() {
            return format;
        }

        public int getLength() {
            return length;
        }
    }


    /**
     * 格式串
     */
    public final static String[] dateFormats = {"yyyy", "yyyy-MM", "yyyy-M-d", "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss", "MM月dd日", "yyyyMMddHHmmss", "yyyyMMddHHmmssms", "yyyy年MM月dd日 HH:mm",
            "yyyy-MM-dd HH:mm", "yyyyMMddHHmm", "yyyyMMdd", "MM-dd", "dd日HH点mm分", "yyyy/MM/dd HH:mm", "HH:mm:ss",
            "yyyyMMddHHmmss"};


    public static final int FORMAT_YYYYMM = 1;
    public static final int FORMAT_YYYYMMDD = 3;
    public static final int FORMAT_YYYYMMDD_HHMMSS = 4;
    public static final int FORMAT_MMDD = 12;


    /**
     * 返回格式化日期
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static String formatDate(Date date, String parseStr) {
        if (parseStr == null) {
            parseStr = DTFormat.yyyy_MM_dd.format;
        }
        String sdate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            if (date != null) {
                sdate = f.format(date);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sdate;
    }

    /**
     * 返回格式化日期
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static String formatDateByLong(long date, String parseStr) {
        String sdate = null;
        if (parseStr == null) {
            parseStr = DTFormat.yyyy_MM_dd_HH_mm_ss.format;
        }
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            if (null != f && date != 0) {
                Date dt = new Date(date);
                sdate = f.format(dt);
            }
        } catch (Exception _ex) {
            _ex.printStackTrace();
        }
        return sdate;
    }


    /**
     * 返回格式化日期2
     *
     * @param date      日期
     * @param dtfFormat
     * @return 返回格式串
     */
    public static String formatDate(Date date, DTFormat dtfFormat) {
        String parseStr = dtfFormat.getFormat();
        String sdate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            if (date != null) {
                sdate = f.format(date);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sdate;
    }


    /**
     * 查询nextDate是否是currentDate的第二天
     *
     * @param currentDate
     * @param nextDate
     * @return
     */
    public static boolean isNextDay(Date currentDate, Date nextDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 1);
        Calendar nextDateCalendar = Calendar.getInstance();
        nextDateCalendar.setTime(nextDate);
        return calendar.get(Calendar.YEAR) == nextDateCalendar.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == nextDateCalendar.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == nextDateCalendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 返回格式化日期
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static Date parseDate(String date, String parseStr) {
        Date ddate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            ddate = f.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ddate;
    }

    /**
     * 返回格式化日期 【webservice调用elong时间差8个小时，目前不明原因，先调整时区解决】
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static Date parseDate4Elong(String date, String parseStr) {
        Date ddate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        f.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        try {
            ddate = f.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ddate;
    }

    /**
     * 返回格式化日期
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static Date parseDate(Date date, String parseStr) {
        SimpleDateFormat format = new SimpleDateFormat(parseStr);
        try {
            String dd = format.format(date);
            date = format.parse(dd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }

    /**
     * getCurrentData 的功能描述  返回当前日期对象
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 根据对应日期的前一天
     *
     * @param day 日期
     */
    public static Date getMinusDay(Date day, int minus) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(day.getTime());
        cal.add(Calendar.DATE, minus);
        Date date = cal.getTime();
        return date;
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param day  天数
     * @return 返回相加后的日期
     * @author keven.luo
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期
     *
     * @param date
     * @return 返回毫秒
     * @author keven.luo
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 根据指定日期返回±N天的日期
     *
     * @param date,n
     */
    public static Date getAnotherDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        date = calendar.getTime();
        return date;
    }

    /**
     * 方法说明：按照分钟向前向后计算日期
     * 作者：lihaifeng 2015年2月2日 下午12:03:58
     *
     * @param date
     * @param mins
     * @return
     */
    public static Date getDayByMins(Date date, int mins) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);
        return cal.getTime();
    }

    /**
     * 方法名： getDaysSubtraction
     * 方法描述:  得到两个Date类型时间的差，long类型
     * 创建人：weiwei.li
     * 创建时间：2015-3-5 上午11:25:48
     * 参数说明： minuendDate被减数  subtractorDate减数
     * 返回值说明：
     * 修改人：
     * 修改时间：
     * 修改备注：
     */
    public static long getMillionSecondSubtraction(Date minuendDate, Date subtractorDate) {
        long result = minuendDate.getTime() - subtractorDate.getTime();
        return result;
    }

    /**
     * 获取日期1和日期2之间的天数
     *
     * @param d1 日期1
     * @param d2 日期2
     *           d2 > d1，正数
     * @return 天数
     */
    public static int getDateDistance(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * to减from，然后折算成天数后返回
     *
     * @param from 开始日期
     * @param to   结束日期
     * @return 两个日期差值折算后的天数
     */
    public static long convertDateToDays(Date from, Date to) {
        if (from == null || to == null)
            return 0;
        long diff = to.getTime() - from.getTime();
        return diff / 86400000;
    }


    /**
     * @param dateStr
     * @return
     * @describe 验证格式是否是yyyy-MM-dd
     */
    public static boolean validDate(String dateStr) {
        try {
            SimpleDateFormat f = new SimpleDateFormat(dateFormats[3]);
            f.setLenient(false);
            f.parse(dateStr);

            String[] dateArr = dateStr.split("-");
            if (dateArr.length == 3 && dateArr[0].length() == 4
                    && dateArr[1].length() == 2 && dateArr[2].length() == 2) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }


    /**
     * @param earlyDate 小日期
     * @param lateDate  大日期
     * @return
     * @describe 如果earlyDate大于lateDate, 返回false, 否则返回true
     */
    public static boolean compareDate(String lateDate, String earlyDate) {
        Date toDate = parseDate(lateDate, dateFormats[3]);
        Date fromDate = parseDate(earlyDate, dateFormats[3]);
        if (fromDate.after(toDate)) {
            return false;
        }
        return true;
    }

    /**
     * 取两个日期之间的所有日期Date 【闭区间】
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static List<Date> dateSplit(Date startDate, Date endDate) {
        List<Date> dateList = Lists.newArrayList();
        if (endDate.before(startDate)) {
            return dateList;
        }
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        dateList.add(startDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    + (24 * 60 * 60 * 1000)));// 比上一天加一
        }
        return dateList;
    }

    /**
     * 取两个日期之间的所有日期Str 【闭区间】
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static List<String> dateSplit(String startDateStr, String endDateStr) {
        List<String> dateStrList = Lists.newArrayList();
        Date startDate = parseDate(startDateStr, dateFormats[3]);
        Date endDate = parseDate(endDateStr, dateFormats[3]);
        if (endDate.before(startDate)) {
            return dateStrList;
        }
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        dateStrList.add(startDateStr);
        for (int i = 1; i <= step; i++) {
            dateStrList.add(formatDate(new Date(parseDate(dateStrList.get(i - 1), dateFormats[3]).getTime()
                    + (24 * 60 * 60 * 1000)), dateFormats[3]));// 比上一天加一
        }
        return dateStrList;
    }


    /**
     * 得到当前的星期，星期日 0  星期一 1  星期二 2 。。。 星期六 6
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.parseDate(date, DateUtils.dateFormats[3]));
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 左闭右开[d1,d2)
     *
     * @param checkInDate
     * @param checkOutDate
     * @return
     * @describe 得到入住离店的晚数 fromDate:2016-02-02,toDate:2016-02-04,返回2016-02-02,2016-02-03
     */
    public static List<String> getPerDayList(Date checkInDate, Date checkOutDate) {
        List<String> dateList = getAllDayList(checkInDate, checkOutDate);
        if (dateList.size() > 0) {
            dateList.remove(dateList.size() - 1);
        }
        return dateList;
    }

    /**
     * @param fromDate
     * @param toDate
     * @return
     * @describe 闭区间[d1, d2] 包含开始结束的所有日期 fromDate:2016-02-02,toDate:2016-02-04,返回2016-02-02,2016-02-03,2016-02-04
     */
    public static List<String> getAllDayList(Date fromDate, Date toDate) {
        List<String> dateList = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        while (!calendar.getTime().after(toDate)) {
            dateList.add(DateUtils.formatDate(calendar.getTime(), DateUtils.dateFormats[3]));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dateList;
    }


    /**
     * date to calendar
     *
     * @param date
     * @return
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


    /**
     * 获取year年后的日期
     *
     * @param day
     * @param year
     * @return
     */
    public static Date getYearDay(Date day, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(day.getTime());
        cal.add(Calendar.YEAR, year);
        Date date = cal.getTime();
        return date;
    }

    /**
     * @param
     * @return
     * @throws
     * @description 功能描述: 比较2个日期大小
     * @author caojinbao
     * @date 2017/10/12 15:59
     * @requireNo: ${REQUIRENO}
     */
    public static boolean compareTo(Date date1, Date date2) {
        /**
         dat1>dat2  va=1;
         dat1=dat2  va=0;
         dat1<dat2  va=-1;
         */
        int res = date2.compareTo(date1);
        if (res == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param
     * @return
     * @throws
     * @description 功能描述: 时间+
     * @author caojinbao
     * @date 2017/11/8 19:55
     * @requireNo: ${REQUIRENO}
     */
    public static Date addDay(Date dd, int day) {
        SimpleDateFormat format = new SimpleDateFormat(DTFormat.yyyy_MM_dd.getFormat());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dd);
        calendar.add(Calendar.DATE, day);
        String T1 = format.format(calendar.getTime());
        try {
            return format.parse(T1);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param
     * @return
     * @throws
     * @description 功能描述: 获取一个时间段的日期
     * @author caojinbao
     * @date 2017/12/19 12:03
     * @requireNo: ${REQUIRENO}
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
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

    /**
     * @param
     * @return
     * @throws
     * @description 功能描述: 获取时间段内所有时间
     * @author caojinbao
     * @date 2018/1/4 16:01
     * @requireNo: ${REQUIRENO}
     */
    public static List<Date> getTimePeriod(int day) {
        Date startDate = parseDate(new Date(), DTFormat.yyyy_MM_dd.getFormat());
        Date endDate = addDate(new Date(), day);
        return findDates(startDate, endDate);
    }



    /**
     * @description 功能描述: 月份增加
     * @author libin293
     * @date 2018/4/12 13:58
     * @param
     * @throws
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(date);   //设置时间为当前时间
        ca.add(Calendar.MONTH, month); //年份减1
        return ca.getTime(); //结果
    }
}