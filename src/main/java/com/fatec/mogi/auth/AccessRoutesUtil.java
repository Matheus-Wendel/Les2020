package com.fatec.mogi.auth;

public class AccessRoutesUtil {
//free routes
	public static final String[] GET_FREE_ACCESS_ROUTES = {
	"/disc",
	"/disc/**",
	"/artist",
	"/genre",
	"/recorder",
	"/cardBrand",
	"/city",
	"/country",
	"/state" };

	//client only
	public static final String[] GET_CLIENT_ACCESS_ROUTES = {"/cart","/cartProduct","/creditCard","/client"};
	public static final String[] POST_CLIENT_ACCESS_ROUTES = {"/address","/creditCard","/cart","/cartProduct","/purchase"};
	public static final String[] DELETE_CLIENT_ACCESS_ROUTES = {"/address","/creditCard","/client","/cartProduct"};
	
	public static final String[] PUT_CLIENT_ACCESS_ROUTES = { "/client", "/address", "/creditCard","/cartProduct"};
	
	
	//employee only
	public static final String[] POST_EMPLOYEE_ACCESS_ROUTES = { "/disc", "/artist", "/genre","/recorder","/pricing","/stock","/sale","/employee"};
	public static final String[] PUT_EMPLOYEE_ACCESS_ROUTES = { "/disc", "/artist", "/genre","/recorder","/pricing","/stock","/sale","/employee"};
	public static final String[] DELETE_EMPLOYEE_ACCESS_ROUTES = { "/disc", "/artist", "/genre","/recorder","/pricing","/stock","/sale"};

}
