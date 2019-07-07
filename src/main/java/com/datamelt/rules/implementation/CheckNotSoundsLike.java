/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.datamelt.rules.implementation;

import com.datamelt.rules.core.util.Soundex;
import com.datamelt.util.CheckAnnotation;

/**
 * checks if a string does not sound like a given string using the soundex algorithm. 
 * <p>
 * The first parameter of a given method is always the value of the field that shall be checked. The second parameter is either another field to check against 
 * or an expected value (fixed value) to check against the first value.
 * 
 * @author uwe geercken
 */
@CheckAnnotation(name="Check Not Sounds Like", description="Checks if a string not sounds like the other, using the soundex algorithm",nameDescriptive="not sounds like",checkSingleField=0)
public class CheckNotSoundsLike extends GenericCheck
{
	/**
	 * Checks if a string does not sound like a given string using the soundex algorithm 
     * 
     * @param value			the first value for comparison
     * @param expectedValue	the second value for comparison - to compare against the first value
     * @return				indication if the first value does not sound like the second value
     */
    public static boolean evaluate(String value, String expectedValue)
    {
        if(value!=null && expectedValue!=null)
        {
        	return !Soundex.soundex(value).equals(Soundex.soundex(expectedValue));
        }
        else
        {
        	return false;
        }
    }

}
