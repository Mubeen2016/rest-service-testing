package com.mubeen.rest_service_testing;

import junit.framework.TestCase;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

//This tutorial is to learn to send Get request to Rest Service
public class WeatherService1Test extends TestCase {

	private final String USER_AGENT = "Mozilla/5.0";

	public void testWeatherService1() throws Exception {

		System.out.println("Testing WeatherPage - Send HTTP Get Request");
		sendGetWeather();

	}

	private void sendGetWeather() throws Exception {
		String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=bb00cfb0674dc3adadf73d1bdcf6d2bf";

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
		System.out.println(response.toString());
	}

}
