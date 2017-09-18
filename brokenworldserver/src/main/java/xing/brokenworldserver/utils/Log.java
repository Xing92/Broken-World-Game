package xing.brokenworldserver.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log{
	
	private static Logger logger = LogManager.getLogger(Log.class);
	
	public static void info(String message){
		logger.info(message);
	}
	
	public static void info(String message, Object... object){
		logger.info(message, object);
	}
}
