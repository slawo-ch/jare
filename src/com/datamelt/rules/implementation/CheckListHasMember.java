/*
 * Created on 13.07.2014
 *
 * all code by uwe geercken
 */
package com.datamelt.rules.implementation;

/**
 * checks if a string containing multiple values separated by commas (,) 
 * contains a given string. spaces in the individual values are removed (trim)
 * 
 * an example for a list would be:
 * 
 * 		"Rome, Paris, New York, Berlin"
 * 
 * @author uwe geercken
 */
public class CheckListHasMember extends GenericCheck
{
	/**
     * checks, if the string is contained in the list of values
     * separated by commas
     */
    public static boolean evaluate(String list, String value)
    {
        boolean matches = false;
        if(list!=null)
        {
        	String [] entries = list.split(",");
	        for(int i=0;i< entries.length;i++)
	    	{
	        	if(entries[i].trim().equals(value.trim()))
	    		{
	        		matches = true;
	    			break;
	    		}
	    	}
        }
        return matches;
    }
    
    /**
     * checks, if the string is contained in the list of values
     * separated by commas, ignore or not ignoring the case
     */
    public static boolean evaluate(String list, String value, boolean ignoreCase)
    {
    	boolean matches = false;
    	if(list!=null)
    	{
	    	String [] entries = list.split(",");
	        for(int i=0;i<entries.length;i++)
	    	{
	        	if(!ignoreCase)
	    		{
		        	if(entries[i].trim().equals(value.trim()))
		    		{
		    			matches = true;
		    			break;
		    		}
	    		}
		        else
		        {
		        	if(entries[i].trim().toLowerCase().equals(value.trim().toLowerCase()))
		    		{
		    			matches = true;
		    			break;
		    		}
		        }
	    	}
    	}
        return matches;
    }
    
    /**
     * checks, if the list of values contains a certain integer value
     */
    public static boolean evaluate(String list, int value )
    {
    	boolean matches = false;
    	String [] entries = list.split(",");
        for(int i=0;i<entries.length;i++)
    	{
        	if(Integer.parseInt(entries[i].trim()) == value)
    		{
    			matches = true;
    			break;
    		}
    	}
        return matches;
    }
    
    /**
    * checks, if the list of values contains a certain long value
    */
    public static boolean evaluate(String list, long value)
    {
    	boolean matches = false;
    	String [] entries = list.split(",");
        for(int i=0;i<entries.length;i++)
    	{
        	if(Long.parseLong(entries[i].trim()) == value)
    		{
    			matches = true;
    			break;
    		}
    	}
        return matches;
    }
}