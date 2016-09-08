package com.mubeen.xml.rest_service_testing;

import junit.framework.TestCase;

//This is same as ThomasBayer01Test but using "ThomasBayerGetService" helper class
public class ThomasBayer02Test extends TestCase {
	
	public void testService() throws Exception{
		
		ThomasBayerGetService helperService = new ThomasBayerGetService();
		String sqlrest = helperService.sendGet("http://www.thomas-bayer.com/sqlrest");
//		System.out.println(sqlrest);
		
		String customerList = helperService.sendGet("http://www.thomas-bayer.com/sqlrest/CUSTOMER");
//		System.out.println(customerList);
		
	}

}
