/*
 * Created on 28.06.2008
 *
 * all code by uwe geercken
 */
package com.datamelt.rules.implementation;

/**
 * checks if an integer is not between two given numbers 
 * 
 * @author uwe geercken
 */
public class CheckIsNotBetween extends GenericCheck
{
	/**
	 * specify an integer to evaluate, an integer for the lower limit and an integer for the upper limit
	 */
    public static boolean evaluate(int value,int valueLowerLimit, int valueUpperLimit)
    {
        return value<valueLowerLimit || value>valueUpperLimit;
    }
    
    /**
	 * specify a long to evaluate, a long for the lower limit and an long for the upper limit
	 */
    public static boolean evaluate(long value,long valueLowerLimit, long valueUpperLimit)
    {
        return value<valueLowerLimit || value>valueUpperLimit;
    }
    
    /**
	 * specify an integer to evaluate and a string which contains the upper and the lower limit
	 * seperated by a comma 
	 */
    public static boolean evaluate(int value,String valueLowerUpperLimit)
    {
    	String[] stringValues= valueLowerUpperLimit.split(",");
    	int lowerLimit = Integer.parseInt(stringValues[0].trim());
    	int upperLimit = Integer.parseInt(stringValues[1].trim());
    	
        return value<lowerLimit || value>upperLimit;
    }
    
    /**
	 * specify an long to evaluate and a string which contains the upper and the lower limit
	 * seperated by a comma 
	 */
    public static boolean evaluate(long value,String valueLowerUpperLimit)
    {
    	String[] stringValues= valueLowerUpperLimit.split(",");
    	long lowerLimit = Long.parseLong(stringValues[0].trim());
    	long upperLimit = Long.parseLong(stringValues[1].trim());
    	
        return value<lowerLimit || value>upperLimit;
    }
}