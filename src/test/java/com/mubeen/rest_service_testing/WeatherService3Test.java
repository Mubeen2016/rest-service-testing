package com.mubeen.rest_service_testing;

import junit.framework.TestCase;
import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.Iterator;

import org.json.JSONObject;

//import javax.net.ssl.HttpsURLConnection;

//This tutorial is to learn parameterization of input
public class WeatherService3Test extends TestCase {

	private final String USER_AGENT = "Mozilla/5.0";
	private String response;
	private JSONObject mainObj;
	
	public void testWeatherService1() throws Exception {

		System.out.println("Testing WeatherPage - Send HTTP Get Request");
		String city, country;
		city = "London";
		country = "uk";
		response = sendGetWeather(city , country);
		
		//System.out.println(response);
		JSONObject weatherJsonObj  = new JSONObject(response);
		
		System.out.println("Base = " + weatherJsonObj.getString("base"));
		
		
//		Accessing inner objects
		mainObj = (JSONObject)weatherJsonObj.get("main");        
		System.out.println(city + "'s temp = " + mainObj.get("temp"));
		System.out.println(city + "'s humidity = " + mainObj.get("humidity"));
		
//		 Below will loop through all the keys and get their values
//		 Iterator iterator  = mainObj.keys();
//         String key = null;
//         while(iterator.hasNext()){
//             key = (String)iterator.next();
//             System.out.println(key + " : "+mainObj.get(key));
//         }
         
	}

	private String sendGetWeather(String city,String country) throws Exception {
		
		String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"," + country+"&APPID=bb00cfb0674dc3adadf73d1bdcf6d2bf";

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
		return response.toString();
	}

}
