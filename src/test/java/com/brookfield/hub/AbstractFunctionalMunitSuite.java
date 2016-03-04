package com.brookfield.hub;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.mule.api.config.MuleProperties;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class AbstractFunctionalMunitSuite extends FunctionalMunitSuite{
	
    @Override
    protected Properties getStartUpProperties() {
        Properties properties = new Properties(super.getStartUpProperties());
        properties.put(MuleProperties.APP_HOME_DIRECTORY_PROPERTY, new File("target").getAbsolutePath());
        addMuleAppEnvironment();
        return properties;
    }
    
    protected void addMuleAppEnvironment()
    {
    	ResourceBundle resourceBundle = ResourceBundle.getBundle("mule-app");
    	final Enumeration<String> keys = resourceBundle.getKeys();
    	while(keys.hasMoreElements())
    	{
    		String aKey = keys.nextElement();
    		System.setProperty(aKey, resourceBundle.getString(aKey));
    	}
    }
	
    protected static String buildIncludeFlowString(String... flows) {
        final StringBuilder include = new StringBuilder();
        for (final String aFlow : flows) {
            if (include.length() != 0) {
                include.append(",");
            }
            if (!aFlow.startsWith("test-")) {
                include.append("brookfield-");
                if (aFlow.length() != 0) {
                    include.append("-");
                }
            }
            include.append(aFlow);
            include.append(".xml");
        }
        return include.toString();
    }
}
