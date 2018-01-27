package yann.uppermonitor.utils;

import android.text.TextUtils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class ExDateUtil {

	private ExDateUtil() {}

	private static class ExDateUtilHolder {
		private static final ExDateUtil edu = new ExDateUtil();
	}

	public static final ExDateUtil getInstance() {
		return ExDateUtilHolder.edu;
	}

	public final String dateFormat(Date date, String strFormat) {
		SimpleDateFormat format = new SimpleDateFormat(strFormat);//yyyy-MM-dd
		return format.format(date);
	}
	
	public final long getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}
	
	public final String getCalendar(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		String str = MessageFormat.format("{0}月{1}日 {2}:{3}", month, day, hour, minute > 9 ? minute : TextUtils.concat("0", String.valueOf(minute)));
		return str;
	}
	
	public final String getWeekday(String date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdw = new SimpleDateFormat("E");
		Date d = null;
		try {
			d = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdw.format(d);
	}
	
	public final long getFirstDayOfDateInMonth(long date){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);
		return cal.getTimeInMillis();
	}

	public final long getFirstDayOfDateInLastMonth(long date){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTimeInMillis();
	}
	
	public final long getLastDayOfDateInMonth(long date){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTimeInMillis();
	}
	
	public final long getLastDayOfDateInLastMonth(long date){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTimeInMillis();
	}
	
	public final long getNextDay(long date){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTimeInMillis();
	}

	public final long getLastDay(long date){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTimeInMillis();
	}
	
	public final long getSomeDayByNum(long date,int num){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.DAY_OF_YEAR, num);
		return cal.getTimeInMillis();
	}
	
	public final long getSomeMonthByNum(long date,int num){
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.MONTH, num);
		return cal.getTimeInMillis();
	}
	
	public final int getWeekOfDate(long date) {
        int[] weekDays = {0, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(date));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	public final int getDayNum(long date){
		SimpleDateFormat format = new SimpleDateFormat("d");
		return Integer.parseInt(format.format(new Date(date)));
	} 
	
	public final String getDateFormat(long time, String sf) {
		Date date = new Date(time);
		SimpleDateFormat df = new SimpleDateFormat(sf);
		String str = df.format(date);
		return str;
	}

    public final long getDateFormat(String time, String sf) {
        SimpleDateFormat df = new SimpleDateFormat(sf);
        Date date = null;
        try {
            date = df.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

	/**
	 * 根据日期获取星期
	 * @param pTime yyyy-MM-dd
	 * @return
	 */
	public String getWeek(String pTime) {

		String Week = "";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			Week += "日";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			Week += "一";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			Week += "二";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			Week += "三";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			Week += "四";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			Week += "五";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 7) {
			Week += "六";
		}

		return Week;
	}
	
}
