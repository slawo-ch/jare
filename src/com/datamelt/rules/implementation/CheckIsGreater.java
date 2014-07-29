/*
 * Created on 15.09.2006
 *
 * all code by uwe geercken
 */
package com.datamelt.rules.implementation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * checks if one value is greater than the other. in case
 * a string value is used, it checks, if the length of the
 * string if greater than the given length
 * @author uwe geercken
 */
public class CheckIsGreater extends GenericCheck
{
    /**
     * checks, if the length of the
     * string is greater than the given length
     */
    public static boolean evaluate(String value, int length)
    {
        if(value!=null)
        {
	    	if(value.length()> length)
	        {
	            return true; 
	        }
	        else
	        {
	            return false;
	        }
        }
        else
        {
        	return false;
        }
    }
    
    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(int value1, int value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(long value1, long value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(double value1, long value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(double value1, int value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(float value1, int value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }

    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(long value1, int value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(double value1, double value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
    /**
     * checks, if the first value is greater than the second value
     */
    public static boolean evaluate(float value1, float value2)
    {
        if(value1 > value2)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }

    /**
     * checks, if the first date is greater than the second
     * date (after the second date), using the given date format
     */
    public static boolean evaluate(String value1, String value2, String format)
    {
        
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        boolean result = false;
        
        try
        {
	       	Date date1 = sdf.parse(value1);
	        Date date2 = sdf.parse(value2);
	        
	        
	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(date1);
	        
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(date2);
	        
	        result = cal1.after(cal2);
        }
        catch(Exception ex)
        {
        	
        }
        return result;
    
    }
    
    /**
     * checks, if the first date is greater than the string
     * date (after the second date), using the given date format
     */
    public static boolean evaluate(Date date1, String value2, String format)
    {
        
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        boolean result = false;
        
        try
        {
	        Date date2 = sdf.parse(value2);
	        
	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(date1);
	        
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(date2);
	        
	        result = cal1.after(cal2);
        }
        catch(Exception ex)
        {
        	
        }
        return result;
    
    }
    
    /**
     * checks, if the first date is greater than the 
     * date string (after the second date), using the default date format
     */
    public static boolean evaluate(Date date1, String value2)
    {
        
        SimpleDateFormat sdf = new SimpleDateFormat(CheckConstants.DEFAULT_DATE_FORMAT);

        boolean result = false;
        
        try
        {
	        Date date2 = sdf.parse(value2);
	        
	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(date1);
	        
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(date2);
	        
	        result = cal1.after(cal2);
        }
        catch(Exception ex)
        {
        	
        }
        return result;
    
    }
}