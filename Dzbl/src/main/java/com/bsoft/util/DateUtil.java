package com.bsoft.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/20.
 */
public class DateUtil {
    public static Map<String, Object> getPersonAge(Date birthday, Date nowDate) {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        if (nowDate != null) {
            // nowDate = new Date();
            now.setTime(nowDate);
        }
        // Calendar now = Calendar.getInstance();
        // now.setTime(nowDate);
        // Calendar birth = Calendar.getInstance();
        // birth.setTime(birthday);
        int age = calculateAge(birthday, nowDate);
        String reAge = age + "岁";
        if (age < 3 && age >= 1) {
            int month = getMonths(birthday, now.getTime());
            reAge = age + "岁";
            if ((month - 12 * age) > 0) {
                reAge = age + "岁" + (month - 12 * age) + "个月";
            }
        } else if (age < 1) {
            int month = getMonths(birthday, now.getTime());
            if (month < 12 && month >= 6) {
                reAge = month + "个月";
            } else {
                int day = getPeriod(birthday, null);
                if (day >= 29 && month > 0) {
                    if (now.get(Calendar.DAY_OF_MONTH) >= birth
                            .get(Calendar.DAY_OF_MONTH)) {
                        day = now.get(Calendar.DAY_OF_MONTH)
                                - birth.get(Calendar.DAY_OF_MONTH);
                    } else {
                        now.set(Calendar.MONTH, birth.get(Calendar.MONTH) + 1);
                        day = now.get(Calendar.DAY_OF_YEAR)
                                - birth.get(Calendar.DAY_OF_YEAR);
                    }
                    reAge = month + "个月";
                    if (day > 0) {
                        reAge = month + "个月" + day + "天";
                    }
                } else {
                    if (day >= 4) {
                        if ((now.get(Calendar.DAY_OF_YEAR) - birth
                                .get(Calendar.DAY_OF_YEAR)) > 0) {
                            day = now.get(Calendar.DAY_OF_YEAR)
                                    - birth.get(Calendar.DAY_OF_YEAR);
                        }
                        reAge = day - 1 + "天";
                    } else {
                        int hour = now.get(Calendar.HOUR_OF_DAY)
                                - birth.get(Calendar.HOUR_OF_DAY);
                        reAge = hour + 24 * (day) + "小时";
                    }
                }
            }
        }
        HashMap<String, Object> resBody = new HashMap<String, Object>();
        resBody.put("age", age);
        resBody.put("ages", reAge);
        return resBody;
    }
    /**
     * 计算年龄（周岁）。
     *
     * @param birthday
     *            出生日期。
     * @param calculateDate
     *            计算日。
     * @return
     */
    public static int calculateAge(Date birthday, Date calculateDate) {
        Calendar c = Calendar.getInstance();
        if (calculateDate != null) {
            c.setTime(calculateDate);
        }
        if (birthday == null)
            return 0;
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        int age = c.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        c.set(Calendar.YEAR, birth.get(Calendar.YEAR));
        if (dateCompare(c.getTime(), birth.getTime()) < 0) {
            return age - 1;
        }
        return age;
    }

    /**
     * 比较两个日期的年月日，忽略时分秒。
     *
     * @param d1
     * @param d2
     * @return 如果d1晚于d2返回大于零的值，如果d1等于d2返回0，否则返回一个负值。
     */
    public static int dateCompare(Date d1, Date d2) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c2.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
        Date date0 = c2.getTime();

        c.setTime(d2);
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c2.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
        Date date1 = c2.getTime();

        return date0.compareTo(date1);
    }

    /**
     * 计算两个日期间的月数。
     *
     * @param date1
     *            较早的一个日期
     * @param date2
     *            较晚的一个日期
     * @return 如果前面的日期小于后面的日期将返回-1。
     */
    public static int getMonths(Date date1, Date date2) {
        Calendar beginDate = Calendar.getInstance();
        beginDate.setTime(date1);
        Calendar now = Calendar.getInstance();
        now.setTime(date2);
        if (beginDate.after(now)) {
            return -1;
        }
        int mon = now.get(Calendar.MONTH) - beginDate.get(Calendar.MONTH);
        if (now.get(Calendar.DAY_OF_MONTH) < beginDate
                .get(Calendar.DAY_OF_MONTH)) {
            if (!(now.getActualMaximum(Calendar.DAY_OF_MONTH) == now
                    .get(Calendar.DAY_OF_MONTH))) {
                mon -= 1;
            }
        }
        if (now.get(Calendar.YEAR) == beginDate.get(Calendar.YEAR)) {
            return mon;
        }
        return 12 * (now.get(Calendar.YEAR) - beginDate.get(Calendar.YEAR))
                + mon;
    }

    /**
     * 计算两个日期之间的天数，参数null表示当前日期。
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getPeriod(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return 0;
        }
        if (date1 != null && date2 != null && date1.compareTo(date2) == 0) {
            return 0;
        }
        Calendar begin = Calendar.getInstance();
        if (date1 != null) {
            begin.setTime(date1);
        }
        Calendar end = Calendar.getInstance();
        if (date2 != null) {
            end.setTime(date2);
        }
        if (begin.after(end)) {
            Calendar temp = end;
            end = begin;
            begin = temp;
        }
        if (end.get(Calendar.YEAR) == begin.get(Calendar.YEAR)) {
            return end.get(Calendar.DAY_OF_YEAR)
                    - begin.get(Calendar.DAY_OF_YEAR);
        }
        int years = end.get(Calendar.YEAR) - begin.get(Calendar.YEAR);
        int days = begin.getActualMaximum(Calendar.DAY_OF_YEAR)
                - begin.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < years - 1; i++) {
            begin.add(Calendar.YEAR, 1);
            days += begin.getActualMaximum(Calendar.DAY_OF_YEAR);
        }
        days += end.get(Calendar.DAY_OF_YEAR);
        return days;
    }
}
