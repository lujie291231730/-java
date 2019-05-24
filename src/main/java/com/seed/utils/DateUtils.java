package com.seed.utils;

import java.util.Calendar;

public class DateUtils {

	public static java.util.Date DateAdd(java.util.Date in, int addtype, int addcount) {
		Calendar cin = Calendar.getInstance();
		cin.setTime(in);
		
		cin.add(addtype, addcount);
		
		return cin.getTime();
	}
	
	public static int BetweenDay(java.util.Date begin, java.util.Date end) {
		Calendar cbegin = Calendar.getInstance();
		cbegin.setTime(begin);
		
		Calendar cend = Calendar.getInstance();
		cend.setTime(end);
		
		int daybegin = cbegin.get(Calendar.DAY_OF_YEAR);
		int dayend = cend.get(Calendar.DAY_OF_YEAR);
		
		int yearbegin = cbegin.get(Calendar.YEAR);
    int yearend = cend.get(Calendar.YEAR);
    
		if (yearbegin != yearend)
		{
			int distance = 0;
			
			for (int i = yearbegin; i < yearend; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					distance += 366;
				} else // 不是闰年
				{
					distance += 365;
				}
			}

			return distance + (dayend - daybegin) + 1;
		}
    else    //不同年
    {
    	return dayend- daybegin + 1;
    }
	}
	
	public static void main(String args[]) {
		System.out.println(BetweenDay(ToolUtils.GetDateByFmt("2018-09-02", "yyyy-MM-dd"), ToolUtils.GetDateByFmt("2019-09-01", "yyyy-MM-dd")));
		
		System.out.println(BetweenDay(ToolUtils.GetDateByFmt("2018-09-02", "yyyy-MM-dd"), ToolUtils.GetDateByFmt("2020-09-01", "yyyy-MM-dd"))); 
	}
}
