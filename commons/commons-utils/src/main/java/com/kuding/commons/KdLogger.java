package com.kuding.commons;

import org.apache.log4j.Logger;

public class KdLogger {
	
	private Logger logger;
	
	private KdLogger(Class<?> clasz) {
		logger = Logger.getLogger(clasz);
	}
	
	public static KdLogger getLogger(Class<?> clasz) {
		return new KdLogger(clasz);
	}
	
	public void i(String message) {
		if(Constants.IS_DEBUG) {
			logger.info(message);
		}
	}
	
	public void d(String message) {
		if(Constants.IS_DEBUG) {
			logger.debug(message);
		}
	}
	
	
	public void e(String message) {
		if(Constants.IS_DEBUG) {
			logger.error(message);
		}
	}
	
}
