package com.petstore.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	
	Properties props;
	
	public LoadProperties() {
		FileInputStream inputStream;
		try {
			String rootPath = System.getProperty("user.dir")+ "/src/test/resources/";
			//String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String configPath = rootPath + "config.properties";
			inputStream = new FileInputStream(configPath);
			props = new Properties();
			props.load(inputStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String get(String key) {
		return props.getProperty(key);
	}
}
