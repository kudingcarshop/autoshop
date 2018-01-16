package com.kuding.commons;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5757828834514074591L;
	
	/**错误码：未授权-1000*/
	public static final int ERROR_CODE_NOT_AUTHEN = 1000;
	
	private int errorCode;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BusinessException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int error_code) {
		this.errorCode = error_code;
	}

}
