package com.prueba.app.entity;

public class UserConstants {
	
	public static final String REGEX_EMAIL = "^(.+)@(.+).cl$";
	public static final String REGEX_PASSWORD = "(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,20}$";
	public static final String MSG_ERROR_EMAIL_CREATED = "El email ya se encuentra registrado.";
	public static final String MSG_ERROR_EMAIL_FORMAT = "El formato del email es incorrecto.";
	public static final String MSG_ERROR_USER_UNREGISTRED = "No se encontro usuario con ID ingresado.";
	public static final String MSG_ERROR_EMAIL_UNREGISTRED = "No se encontro usuario con el email ingresado.";
	public static final String MSG_ERROR_PASS_FORMAT = "La contraseña debe contener entre 8 y 20 caracteres, al menos una mayusucula, al menos una minusucula y un digito.";
}
