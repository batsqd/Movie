package com.test;




import org.apache.log4j.Logger;




public class Log4j_Teat {
	
	private static Logger feedback = Logger.getLogger("feedback_log");
	private static Logger rating = Logger.getLogger("rating_log");
	private static Logger login = Logger.getLogger("login_log");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		feedback.info("1 2");
		rating.info("1	2	3");
		login.info("login");
		System.out.println("ok");
	}
	
	


}
