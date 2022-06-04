package DongYu.WebBase.System.Entity.SysBase;


import DongYu.WebBase.System.Utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SubList<T> {


    /**
     * @param list1
     * @param list2
     * @return list2 .remove( list1  )
     */
    public List<T> subList(List<T> list1, List<T> list2) {
        List<T> diff = new ArrayList<>();
        Map<T, Integer> map = new HashMap<>();
        for (T list : list1) {
            map.put(list, 1);
        }
        for (T list : list2) {
            if (map.get(list) == null) {
                diff.add(list);
            }
        }
        return diff;
    }



    public static String downOneDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化为年月
        Calendar min = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(date));
            min.add(Calendar.DATE, -1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(min.getTime());
    }

    public static String formatDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
        Calendar min = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(min.getTime());
    }
    public static String upOneDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
        Calendar min = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(date));
            min.add(Calendar.DATE, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(min.getTime());

    }

    /**
     * 几周 按周分割时间
     * @param maxDate
     * @param
     * @param weekNumber
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> splitMonthOrDayByWeekNumber( String maxDate ,Integer weekNumber) throws Exception {
        Integer splitNumber = 7 ;//n天时间间隔
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar max = Calendar.getInstance();
        max.setTime(sdf.parse(maxDate));
        max.add(Calendar.DATE, -splitNumber*weekNumber+1);
        String minDate = sdf.format(max.getTime());
        return splitMonthOrDay(minDate,maxDate,true,splitNumber);
    }

        /**
         * 按月 分割 时间段
         *
         * @param minDate
         * @param maxDate
         * @return
         * @throws Exception
         */
    public static List<Map<String, String>> splitMonthOrDay(String minDate, String maxDate, Boolean spliteToDay ,Integer splitNumber) throws Exception {

        maxDate = DateUtil.formatZeroTime(maxDate,false);

        List<Map<String, String>> list = new ArrayList<>();
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(sdf.parse(minDate));
        max.setTime(sdf.parse(maxDate));

        if (spliteToDay) {
            list=travelTimeToDayByNumberTwo(min,max,list,sdf,splitNumber);
        } else {
            list = travelTimeToMouth(min, max, list, sdf);
        }
        return list;
    }

    private static List<Map<String, String>> travelTimeToMouth(Calendar min, Calendar max, List<Map<String, String>> list, SimpleDateFormat sdf) {
        while (min.before(max)) {
            if (min.get(Calendar.YEAR) < max.get(Calendar.YEAR) || min.get(Calendar.MONTH) < max.get(Calendar.MONTH)) {
                Map<String, String> map = new HashMap();
                map.put("minDate", sdf.format(min.getTime()));
                min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH) + 1, 1);
                min.add(Calendar.SECOND,-1);
                map.put("maxDate", sdf.format(min.getTime()));
                min.add(Calendar.SECOND, 1);
                list.add(map);
            } else if (min.get(Calendar.YEAR) == max.get(Calendar.YEAR)&&min.get(Calendar.MONTH) == max.get(Calendar.MONTH)) {
                Map<String, String> map = new HashMap();
                map.put("minDate", sdf.format(min.getTime()));
                map.put("maxDate", sdf.format(max.getTime()));
                list.add(map);
                min=max;
            }
        }
        return list;
    }

    private static List<Map<String,String>> travelTimeToDayByNumberTwo( Calendar min , Calendar max , List<Map<String,String>> list,SimpleDateFormat sdf ,Integer splitNumber) {
        while (min.before(max)) {
            Map<String, String> map = new HashMap();
            if (min.get(Calendar.YEAR) < max.get(Calendar.YEAR)
                    || (min.get(Calendar.YEAR) == max.get(Calendar.YEAR)
                    && min.get(Calendar.MONTH) < max.get(Calendar.MONTH))
                    || (min.get(Calendar.YEAR) == max.get(Calendar.YEAR)
                    && min.get(Calendar.MONTH) == max.get(Calendar.MONTH)
                    && (min.get(Calendar.DATE) < max.get(Calendar.DATE)))
            ) {
                map.put("maxDate", sdf.format(max.getTime()));
                max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DATE)- splitNumber);
                max.add(Calendar.SECOND,1);
                map.put("minDate", sdf.format(max.getTime()));
                max.add(Calendar.SECOND,-1);
                list.add(map);
            } else if (min.get(Calendar.YEAR) == max.get(Calendar.YEAR) && min.get(Calendar.MONTH) == max.get(Calendar.MONTH) && min.get(Calendar.DATE) == max.get(Calendar.DATE)) {
                map.put("maxDate", sdf.format(max.getTime()));
                max.add(Calendar.DATE, -splitNumber);
                map.put("minDate", sdf.format(min.getTime()));
                list.add(map);
            }
        }
        return list;

    }
}
