package com.guru84.todo.app.security;

import com.guru84.todo.app.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	public static final String SING_UP_URL = "/users";
	//public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";
	
	public static String getTokenSecret() {
		AppProperties appproperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appproperties.getTokenSecret();
	}

}