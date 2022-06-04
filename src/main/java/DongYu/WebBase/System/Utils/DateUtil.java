package DongYu.WebBase.System.Utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String sdf_year = "yyyy";
    public static final String sdf_month = "yyyy-MM";
    public static final String sdf_date = "yyyy-MM-dd";
    public static final String sdf_date_time = "yyyy-MM-dd HH:mm:ss";
    public static final String sdf_hh_mm = "HH:mm";

    public static final String sdf_utc = "yyyyMMdd'T'HHmmss'Z'";
    public static final String sdf_utc_date = "yyyyMMdd";


    public static Date parseUtcString(String strDate){
        if(strDate == null || "".equals(strDate.trim())) {
            return null;
        }
        return parse(strDate,sdf_utc);
    }

    public static String formatUtcDate(Date date){
        return formatDate(date,sdf_utc);
    }

    public static String formatUtcStartDate(Date date){
        if(date == null){
            return null;
        }
        return formatDate(date,sdf_utc_date)+"T000000Z";
    }

    public static String formatUtcEndDate(Date date){
        if(date == null){
            return null;
        }
        return formatDate(date,sdf_utc_date)+"T235959Z";
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return formatDate(new Date(), sdf_year);
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,num);
        return formatDate(calendar.getTime(),sdf_year);
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear(Date date) {
        return formatDate(date, sdf_year);
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return formatDate(new Date(), sdf_date);
    }

    public static String getDaytime() {
        return formatDate(new Date(), sdf_date_time);
    }

    public static String getDaytimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,hour);
        return formatDate(calendar.getTime(), sdf_date_time);
    }

    public static String getMonth(Date date,int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,num);
        return formatDate(calendar.getTime(),sdf_month);
    }

    public static String getLastMonth(String dateString) {
        Calendar calendar = Calendar.getInstance();
        Date date = parse(dateString,sdf_date);
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-1);
        return formatDate(calendar.getTime(),sdf_month);
    }

    public static String getMonth(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,num);
        return formatDate(calendar.getTime(),sdf_month);
    }

    public static String getMonthByDay(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,num);
        return formatDate(calendar.getTime(),sdf_month);
    }

    public static String getSameMonth(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-1);
        calendar.add(Calendar.MONTH,num);
        return formatDate(calendar.getTime(),sdf_month);
    }


    public static String getMonth(String month,int num) {
        Date monthDate = parse(month,sdf_month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthDate);
        calendar.add(Calendar.MONTH,num);
        return formatDate(calendar.getTime(),sdf_month);
    }

    public static int getDayOfDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static String getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return formatDate(calendar.getTime(),sdf_date);
    }

    public static String getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return formatDate(calendar.getTime(),sdf_date);
    }

    public static String getMonthEnd() {
        return getMonthEnd(0,0);
    }

    public static String getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return formatDate(calendar.getTime(),sdf_date);
    }

    public static String getLastMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return formatDate(calendar.getTime(),sdf_date);
    }

    public static String getMonthStart(int year,int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,year);
        calendar.add(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return formatDate(calendar.getTime(),sdf_date);
    }

    public static String getMonthEnd(int year,int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,year);
        calendar.add(Calendar.MONTH,month+1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return formatDate(calendar.getTime(),sdf_date);
    }


    public static String getLastMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return formatDate(calendar.getTime(),sdf_date);
    }

   public static String getLastday(Date date , Integer days){
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(date);
       calendar.add(Calendar.DATE,days);
       return formatDate(calendar.getTime(),sdf_date);
   }

    public static Date getSubDay(Date date , Integer days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }

    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return formatDate(calendar.getTime(),sdf_date);
    }

    public static Date getStartOfDay(Date date){
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay(Date date) {
        return formatDate(date, sdf_date);
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return formatDate(new Date(), sdf_date_time);
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     *
     * @return
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(Date date) {
        return formatDate(date, sdf_date_time);
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, sdf_date);
        }
        return formatDate;
    }

    /**
     * @Title: compareDate
     * @Description:(日期比较，如果s>=e 返回true 否则返回false)
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseDate(String date) {
        return parse(date,sdf_date);
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseTime(String date) {
        return parse(date,sdf_date_time);
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
//            return null;
//            DateUtils.
            return DateUtils.parseDate(date,pattern);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }


    public static String formatDate(Date date) {
        return formatDate(date,sdf_date);
    }

    public static String formatDatetime(Date date) {
        return formatDate(date,sdf_date_time);
    }

    public static boolean isBeforeYesterday(Date date) {
        Date today = DateUtil.getStartOfDay(new Date());
        Date date2 = DateUtil.getStartOfDay(date);
        int days = (int) ((today.getTime() - date2.getTime()) / (1000*3600*24));
        return days > 1;
    }


    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        return parse(s, sdf_date_time) != null;
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat(sdf_date);
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(sdf_date);
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * <li>功能描述：时间相减得到秒数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getTimeSub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(sdf_date);
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(sdf_date_time);
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    public static Date getBeforeDate(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,-days);
        return calendar.getTime();
    }

    public static Date clearSeconds(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    public static String stampToTime(String stamp) {
        if(stamp!=null&&stamp!="") {
            String sd = "";
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(sdf_date_time);
            sd = sdf.format(new Date(Long.parseLong(stamp))); // 时间戳转换日期
            return sd;
        }
        else
            return "";
    }

    public static String timestampToDate(long time) {
        if (time < 10000000000L) {
            time = time * 1000;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(sdf_date_time);
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
        return sd;
    }

    /**
     * 格式化
     * flag:true 是00:00:00
     * flag:false是23:59:59
     * @return
     */
    public static Date 	formatZeroTime(Date date,boolean flag){
        if (date == null)
            return null;
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        if(flag==true){
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
        }
        else{
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.SECOND,59);
        }
        return calendar.getTime();
    }

    /**
     * 格式化  例 2019-03-02 12:22:22  - >2019-03-02 00:00:00
     * flag:true 是00:00:00
     * flag:false是23:59:59
     * @return
     */
    public static String formatZeroTime(String time,boolean flag){
        Date date = formatZeroTime(parseTime(time),flag);
        return  getTime(date);
    }
    public static void main(String[] args) {
        System.out.println(getTime(new Date()));
        System.out.println(getDay(new Date()));
        System.out.println(getAfterDayWeek("3"));
        System.out.println(getLastMonthStart());
        System.out.println(getLastMonthEnd());
        System.out.println("===="+getMonthEnd());
        System.out.println(getMonth("2017-08",-2));

        System.out.println(getDayOfDate());


        Date date = DateUtil.parseDate("2018-06-03");

        System.out.println(isBeforeYesterday(date));


        date = DateUtil.parseDate("2018-06-04");

        System.out.println(isBeforeYesterday(date));

        System.err.println(getStartOfDay(new Date()));

        System.err.println(getTime(getStartOfDay(new Date())));

        System.err.println(getDaySub("2019-09-04 14:24:00",getTime(new Date())));
        System.err.println(getDaySub("2019-09-04 14:24:00","2019-09-05 14:27:06"));
        System.err.println(getTimeSub("2019-09-04 14:24:00","2019-09-05 14:27:06"));
    }
}
