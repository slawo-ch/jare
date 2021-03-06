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
package com.datamelt.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipFile;

import javax.net.ServerSocketFactory;

import org.apache.log4j.Logger;

import com.datamelt.rules.core.ReferenceField;
import com.datamelt.rules.engine.BusinessRulesEngine;
import com.datamelt.server.transform.Transformer;
import com.datamelt.util.FileUtility;

public class RuleEngineServer extends Thread
{
	private ServerSocket serverSocket;
    private String ruleFileFolder;
    private String ruleFile;
    private Properties properties = new Properties();
    private Transformer transformer;
    private int port;
    private String propertiesFileFullname;
    private long serverStart;
    
    private static final String PROPERTIES_FILE 			= "server.properties";
    
    private static final String PROPERTY_PORT 				= "server.port";
    private static final String PROPERTY_FOLDER_RULEFILE 	= "rulefile.folder";
    private static final String PROPERTY_RULEFILE 			= "rulefile.name";
    private static final String PROPERTY_TRANSFORMER 		= "transformer.classname";

    private static final int 	DEFAULT_PORT 				= 9000;
    private static final String DEFAULT_FOLDER_RULEFOLDER 	= ".";
    private static final String DEFAULT_RULEFILE 			= "rules.zip";
    
    private static final String DEFAULT_DATETIME_FORMAT		= "yyyy-MM-dd HH:mm:ss";
    private static boolean ok								= true;
    
    private static SimpleDateFormat sdf						= new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
    
    final static Logger logger 								= Logger.getLogger(RuleEngineServer.class);
    
    private static BusinessRulesEngine ruleEngine;
    
    private RuleEngineServer() throws Exception
    {
    	loadProperties();
    	setVariables();
    	createSocket();
        createTransformer();
    }
    
    private RuleEngineServer(String propertiesFile) throws Exception
    {
    	propertiesFileFullname = propertiesFile;
    	loadProperties(propertiesFile);
    	setVariables();
    	createSocket();
        createTransformer();
    }
    
    private void loadProperties() throws IOException
    {
    	propertiesFileFullname = FileUtility.adjustSlash(RuleEngineServer.class.getClassLoader().getResource("").getPath()) + PROPERTIES_FILE;
    	properties.load(RuleEngineServer.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
    }
    
    private void loadProperties(String propertiesFilename) throws IOException
    {
    	FileInputStream inputStream = new FileInputStream(new File(propertiesFilename));
    	properties.load(inputStream);
    	inputStream.close();
    }
    
    private void setVariables()
    {
    	if(properties.getProperty(PROPERTY_PORT)!=null)
    	{
    		port = Integer.parseInt(properties.getProperty(PROPERTY_PORT));
    	}
    	else
    	{
    		port = DEFAULT_PORT;
    	}
    	if(getProperty(PROPERTY_FOLDER_RULEFILE)!=null)
    	{
    		ruleFileFolder = FileUtility.adjustSlash(getProperty(PROPERTY_FOLDER_RULEFILE));
    	}
    	else
    	{
    		ruleFileFolder = RuleEngineServer.class.getClassLoader().getResource(DEFAULT_FOLDER_RULEFOLDER).getPath();
    	}
    	if(getProperty(PROPERTY_RULEFILE)!=null)
    	{
    		ruleFile = getProperty(PROPERTY_RULEFILE);
    	}
    	else
    	{
    		ruleFile = DEFAULT_RULEFILE;
    	}
    }
    
    private void createSocket() throws IOException
    {
    	serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
    }
    
    private void createTransformer() throws Exception
    {
    	if(getProperty(PROPERTY_TRANSFORMER)!=null)
    	{
    		String className = getProperty(PROPERTY_TRANSFORMER).trim();
	    	if(className.length()>0)
	    	{
	    		Class<?> transformerClass = Class.forName(className);
	    		transformer = (Transformer)transformerClass.newInstance();
	    		transformer.setProperties(properties);
	    		transformer.init();
	    	}
    	}
    }

    public static void main(String args[]) throws Exception
    {
    	RuleEngineServer server = null;
    	if(args.length==0)
    	{
    		server = new RuleEngineServer();
    	}
    	else
    	{
    		if(args[0].equals("-h")||args[0].equals("-help"))
    		{
    			//help();
    		}
    		else
    		{
    			server = new RuleEngineServer(args[0]);
    		}
    	}
    	
    	// initialize the ruleengine
    	RuleEngineServer.ruleEngine = new BusinessRulesEngine(new ZipFile(server.ruleFileFolder + server.ruleFile));
    	
    	server.serverStart = System.currentTimeMillis();
    	logger.info("server start...");
    	
    	if(FileUtility.fileExists(server.ruleFileFolder, server.ruleFile))
    	{
    		logger.info("using properties from: " + server.propertiesFileFullname);
    		if(server.transformer!=null)
    		{
    			logger.info("output with transformer: " + server.transformer.getClass());
    		}
    		else
    		{
    			logger.info("no transformer defined: no ruleengine detailed ouput is generated");
    		}
    		logger.info("rule engine file: " + FileUtility.adjustSlash(server.ruleFileFolder) + server.ruleFile);
    		logger.info("rule engine file: rule groups: " + ruleEngine.getNumberOfGroups());
    		logger.info("rule engine file: rules: " + ruleEngine.getNumberOfRules());
    		logger.info("rule engine file: actions: " + ruleEngine.getNumberOfActions());
            for(ReferenceField field : ruleEngine.getReferenceFields())
            {
            	logger.info("rule engine file: reference field: " + field.getName() + " - type: " + field.getJavaTypeName());
            }
    		InetAddress IP=InetAddress.getLocalHost();
    		logger.info("server ready for connections: " + IP.getHostAddress() + ", port: " + server.port);
    		
    		// start the server
    		server.start();
    		
    	}
    	else
    	{
    		logger.error("file not found or is not a file: " + FileUtility.adjustSlash(server.ruleFileFolder) + server.ruleFile);
    	}
    }
    
    @Override
    public void run()
    {
        while(ok)
        {
            try 
            {
                final Socket socketToClient = serverSocket.accept();
                logger.info("client connected from: " + socketToClient.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(getProcessId(socketToClient.getInetAddress().toString()),socketToClient,ruleEngine,transformer,serverStart);
                clientHandler.start();
            }
            catch (Exception e)
            {
            	ok = false;
            	if(!serverSocket.isClosed())
            	{
            		try 
            		{
						serverSocket.close();
					} 
            		catch (IOException e1)
            		{
						e1.printStackTrace();
					}
            	}
                e.printStackTrace();
            }
        }
    }

	private String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	private String getProcessId(String clientInetAddress)
	{
		return "client-" + clientInetAddress + "-" + ruleFile + "_" + sdf.format(new Date());
	}
}
