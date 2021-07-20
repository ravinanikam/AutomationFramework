package Automation.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;

public class Log {
    private  static Logger logger = Logger.getLogger(Log.class.getName()); //initialization of Logger

    public  static Logger getLogData(String ClassName){
        String path = new File("").getAbsolutePath();
        DOMConfigurator.configure("log4j.xml"); //to read the file and configure the file as per listed in log4j.xml file
        return Logger.getLogger(ClassName);
    }

    public static void  startTest(String testName){
        logger.info("Test called"+ testName +"has started");
    }

    public static void  endTest(String testName){
        logger.info("Test called"+ testName +"has ended");
    }

    public static void info(String message){
     logger.info(message);
    }

    public static void  warn(String message){
        logger.warn(message);
    }

    public static void  error(String message){
        logger.error(message);
    }

    public  static void fatal(String message){
        logger.fatal(message);
    }

    public static void debug(String message){
        logger.debug(message);
    }

}
