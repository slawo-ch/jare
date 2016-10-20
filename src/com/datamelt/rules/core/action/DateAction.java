package com.datamelt.rules.core.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.datamelt.rules.core.XmlAction;
/**
 * Class containing possible actions that are related to dates.
 * 
 * Actions belong to a rulegroup and are execute depending on the status of rulegroup - if it passed or failed (or both).
 *
 * @author uwe geercken
 * 
 */
public class DateAction
{
	// the default date format used whenever no other format is specified
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * sets the date to the given date
	 * 
	 * @param action 		the action use
	 * @param value			the value to set
	 * @return				date
	 * @throws Exception	exception in date handling
	 */
	public Date setValue(XmlAction action, Date value) throws Exception
	{
		return value;
	}
	
	/**
	 * sets the date to the given date
	 * 
	 * @param action		the action to use
	 * @param value			the value to set
	 * @return				the date in the default format
	 * @throws Exception	exception in date parsing
	 */
	public Date setValue(XmlAction action, String value) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(value));
		return cal.getTime();
	}

	/**
	 * sets the date to the given date
	 * 
	 * @param action		the action to use
	 * @param value			the value to set
	 * @param dateFormat	the format to use for the date
	 * @return				the date in the default format
	 * @throws Exception	exception in date parsing
	 */
	public Date setValue(XmlAction action, String value, String dateFormat) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(value));
		return cal.getTime();
	}
	/**
	 * method will set the value of the relevant object to the current date.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
	 * @param action		the action to use
	 * @param dateFormat	the format to use for the date
	 * @return				the date as a string in the default format
	 */
	public String setTodayDate(XmlAction action,String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}
	
	/**
	 * method will set the value of the relevant object to the current date.
	 * 
	 * @param action		the action to use
	 * @return				the date in the default format
	 */
	public Date setTodayDate(XmlAction action)
	{
		return new Date();
	}
	
	/**
	 * method will set the value of the relevant object to the current date plus/minus
	 * the specified number of days offset.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
 	 * @param action		the action to use
	 * @param dateFormat	the format to use for the date
	 * @param daysOffset	the number of days offset from today

	 * @return	todays date in the given format as a string
	 */
	public String setTodayDate(XmlAction action,String dateFormat, int daysOffset)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysOffset);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * last day of the month.
	 * The default date format will be used to format the returned string
	 * 
	 * @param action		the action to use
	 * @param year			the year used for calculation
	 * @param month			the month used for calculation
	 * @return	todays date in the default format as a string
	 */
	public String setLastDayOfMonth(XmlAction action,int year, int month)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month -1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return sdf.format(cal.getTime());
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * last day of the current month.
	 * 
	 * @param action		the action to use
	 * @return				last day of the current month
	 */
	public Date setLastDayOfMonth(XmlAction action)
	{
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return cal.getTime();
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * last day of the month.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
	 * @param action		the action to use
	 * @param date			the date involved
	 * @return				last day of the month of the given date
	 */
	public Date setLastDayOfMonth(XmlAction action,Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return cal.getTime();
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * last day of the month.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
	 * @param action		the action to use
	 * @param year			the year to use	 
	 * @param month			the month to use
	 * @param dateFormat	the date format to use
	 * @return				last day of the given year and month in the given format as a string
	 */
	public String setLastDayOfMonth(XmlAction action,int year, int month,String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		
	    cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month -1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return sdf.format(cal.getTime());
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * first day of the month.
	 * The default date format will be used to format the returned string
	 * 
	 * @param action		the action to use
	 * @param year			the year to use	 
	 * @param month			the month to use
	 * @return				first day of the given year and month in the default format
	 */
	public String setFirstDayOfMonth(XmlAction action,int year, int month)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month -1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
		
		return sdf.format(cal.getTime());
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * first day of the current month.
	 * 
	 * @param action		the action to use
	 * @return				first day of the current month
	 */
	public Date setFirstDayOfMonth(XmlAction action)
	{
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
		
		return cal.getTime();
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * first day of the month.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
	 * @param action		the action to use
	 * @param date			the date involved	 
	 * @return				first day of the month of the given date
	 */
	public Date setFirstDayOfMonth(XmlAction action,Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
		
		return cal.getTime();
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * first day of the month.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
	 * @param action		the action to use
	 * @param year			the year to use	 
	 * @param month			the month to use
	 * @param dateFormat	the date format to use
	 * @return				first day of the month of the given year and month in the specified format
	 */
	public String setFirstDayOfMonth(XmlAction action,int year, int month,String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		
	    cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month -1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
		
		return sdf.format(cal.getTime());
	}

	/**
	 * method will set the value of the relevant object to the date of the
	 * mid day of the month.
	 * The default date format will be used to format the returned string
	 * 
	 * @param action		the action to use
	 * @param year			the year to use	 
	 * @param month			the month to use
	 * @return				mid day of the month of the given year and month
	 */
	public String setMidDayOfMonth(XmlAction action,int year, int month)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month -1);
	    cal.set(Calendar.DAY_OF_MONTH, 15);
		
		return sdf.format(cal.getTime());
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * mid day of the current month.
	 * 
	 * @param action		the action to use
	 * @return				mid day of the month of the current date
	 */
	public Date setMidDayOfMonth(XmlAction action)
	{
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 15);
		
		return cal.getTime();
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * mid day of the month.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
	 * @param action		the action to use
	 * @param date			the date involved	 
	 * @return				mid day of the month of the given date
	 */
	public Date setMidDayOfMonth(XmlAction action,Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.set(Calendar.DAY_OF_MONTH, 15);
		
		return cal.getTime();
	}
	
	/**
	 * method will set the value of the relevant object to the date of the
	 * mid day of the month.
	 * the dateFormat parameter is used to specify the date format according
	 * to the specification defined in the SimpleDateFormat class.
	 * 
	 * @param action		the action to use
	 * @param year			the year to use	 
	 * @param month			the month to use
	 * @param dateFormat	the date format to use
	 * @return				mid day of the month of the given year and month in the specified format as a string
	 */
	public String setMidDayOfMonth(XmlAction action,int year, int month,String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		
	    cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month -1);
	    cal.set(Calendar.DAY_OF_MONTH, 15);

		return sdf.format(cal.getTime());
	}

	/**
	 * method will add the given number of minutes to the provided date
	 * 
	 * @param action		the action to use
	 * @param date			the date involved	 
	 * @param minutes		number of minutes to add
	 * @return				the given date plus the given minutes as a date
	 */
	public Date addMinutes(XmlAction action, Date date, long minutes)
	{
		final long ONE_MINUTE_IN_MILLISECONDS = 60000;
		
		long dateInMilliseconds = date.getTime();
	    return new Date(dateInMilliseconds + (minutes * ONE_MINUTE_IN_MILLISECONDS));

	}
}
