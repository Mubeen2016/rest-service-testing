package com.mubeen.xml.rest_service_testing;

import junit.framework.TestCase;

public class ThomasBayer03Test extends TestCase {
	
	public void testXMLFormatting() throws Exception{
		ThomasBayerGetService helperService = new ThomasBayerGetService();
		String sqlrest = helperService.sendGet("http://www.thomas-bayer.com/sqlrest");
		System.out.println(sqlrest);
		
		System.out.println("---------------------------------------------");
		XMLFormatter xmlFormatter = new XMLFormatter();
		String sqlrestFormatted = xmlFormatter.format(sqlrest);
		System.out.println(sqlrestFormatted);
		
	}
}
