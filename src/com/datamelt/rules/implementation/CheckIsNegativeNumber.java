/*
 * Created on 15.09.2006
 *
 * all code by uwe geercken
 */
package com.datamelt.rules.implementation;

/**
 * checks if a given value is smaller than zero (0)
 * @author uwe geercken
 */
public class CheckIsNegativeNumber extends GenericCheck
{
	/**
     * checks, if the integer is a number smaller than zero
     */
    public static boolean evaluate(int value1)
    {
        if(value1 < 0)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
	/**
     * checks, if the long is a number smaller than zero
     */
    public static boolean evaluate(long value1)
    {
        if(value1 < 0)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
	/**
     * checks, if the double is a number smaller than zero
     */
    public static boolean evaluate(double value1)
    {
        if(value1 < 0)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
    
	/**
     * checks, if the float is a number smaller than zero
     */
    public static boolean evaluate(float value1)
    {
        if(value1 < 0)
        {
            return true; 
        }
        else
        {
            return false;
        }
    }
}