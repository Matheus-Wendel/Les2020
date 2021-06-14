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
	"/state",
	"/coupon",
	"/test/**" };

	//client only
	public static final String[] GET_CLIENT_ACCESS_ROUTES = {"/cart","/cartProduct","/creditCard","/client","/purchase"};
	public static final String[] POST_CLIENT_ACCESS_ROUTES = {"/address","/creditCard","/cart","/cartProduct/add","/purchase","/trade"};
	public static final String[] DELETE_CLIENT_ACCESS_ROUTES = {"/address","/creditCard","/client","/cartProduct"};
	
	public static final String[] PUT_CLIENT_ACCESS_ROUTES = { "/client", "/address", "/creditCard","/cartProduct"};
	
	
	//employee only
	public static final String[] GET_EMPLOYEE_ACCESS_ROUTES = {"/purchase/all","/trade","/employee/me"};
	public static final String[] POST_EMPLOYEE_ACCESS_ROUTES = { "/disc", "/artist", "/genre","/recorder","/pricing","/stock","/sale"};
	public static final String[] PUT_EMPLOYEE_ACCESS_ROUTES = { "/disc", "/artist", "/genre","/recorder","/pricing","/purchase","/trade"};
	public static final String[] DELETE_EMPLOYEE_ACCESS_ROUTES = { "/disc", "/artist", "/genre","/recorder","/pricing","/sale"};

	//sales mananger only
	public static final String[] GET_SALES_MANANGER_ACCESS_ROUTES = {"/employee"};
	public static final String[] POST_SALES_MANANGER_ACCESS_ROUTES = {"/employee","/coupon"};
	public static final String[] PUT_SALES_MANANGER_ACCESS_ROUTES = {"/employee","/sale","/coupon",};
	public static final String[] DELETE_SALES_MANANGER_ACCESS_ROUTES = {"/employee"};
}
