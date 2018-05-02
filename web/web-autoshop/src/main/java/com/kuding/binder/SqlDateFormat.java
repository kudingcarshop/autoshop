package com.kuding.binder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlDateFormat extends SimpleDateFormat {
	private static final long serialVersionUID = 1L;


	public SqlDateFormat(String pattern) {
		super(pattern);
	}


	@Override
	public Date parse(String source) throws ParseException {
		DateFormat df = null;
		if (source.length() <= 10) {
			df = new SimpleDateFormat("yyyy-MM-dd");
			return new java.sql.Date(df.parse(source).getTime());
		}
		java.util.Date date = super.parse(source);
		return new java.sql.Date(date.getTime());
	}

	
}
