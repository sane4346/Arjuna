package com.guru84.todo.app.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	
	public String generateAddressId(int length) {
		return generateRandomString(length);
	}
	
	public String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder();
		
		for(int i = 0; i< length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

}
