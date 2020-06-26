package com.alm.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ReadProperty {
	static Properties prop = new Properties();
	
	public ReadProperty() {
		
	}
	public ReadProperty(String Filepath) {

		
		  File file = new File(Filepath);
		  						//ENTER LOCALTION OF "Config.properties" FILE

			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found !!  "+Filepath );
			}

			try {
				prop.load(fileInput);
			} catch (IOException e) {
				System.out.println("File Not Loaded Properly !!  "+Filepath);
			}
//			return prop;
//			return null;

		}	
	
	public String getString(String KeyName) {
		
		return prop.getProperty(KeyName);
		
	}
	


}
