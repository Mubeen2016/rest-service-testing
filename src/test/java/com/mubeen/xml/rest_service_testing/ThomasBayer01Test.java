package com.mubeen.xml.rest_service_testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import junit.framework.TestCase;

//This tutorial is to learn Rest Service using XML
//We are using local getService method in this test
public class ThomasBayer01Test extends TestCase {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public void testGetService() throws Exception {
		String sqlrest = sendGet("http://www.thomas-bayer.com/sqlrest");
//		System.out.println(sqlrest);
		
		String customerList = sendGet("http://www.thomas-bayer.com/sqlrest/CUSTOMER");
//		System.out.println(customerList);
		
		String customer0 = sendGet("http://www.thomas-bayer.com/sqlrest/CUSTOMER/0");
//		System.out.println(customer0);
	}

	private String sendGet(String url) throws Exception {
		

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		//System.out.println(response.toString());
		return response.toString();
	}
}
