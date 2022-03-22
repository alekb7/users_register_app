package com.prueba.app.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.prueba.app.entity.UserConstants;

@Service
public class UserUtilServiceImpl implements UserUtilService{

	public boolean emailValidation(String email) {
		String regx = UserConstants.REGEX_EMAIL;
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public boolean passwordValidation(String password) {
		String regx = UserConstants.REGEX_PASSWORD;
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
}
