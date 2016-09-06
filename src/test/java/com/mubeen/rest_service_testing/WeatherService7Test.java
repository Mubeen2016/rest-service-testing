package com.mubeen.rest_service_testing;

import junit.framework.Assert;
import junit.framework.TestCase;
import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONObject;

//import javax.net.ssl.HttpsURLConnection;

//This tutorial is to move input data creation into its own function (method)
public class WeatherService7Test extends TestCase {

	private final String USER_AGENT = "Mozilla/5.0";
	private String response;
	private JSONObject mainObj;

	public void testWeatherService1() throws Exception {

		JSONObject input = createTestData();

		Iterator iterator = input.keys();
		String key, value = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = input.getString(key);
			// System.out.println(key + ", " + value);
			response = sendGetWeather(key, value);
			validateWeather(key, value, response);
			System.out.println("------------------");
		}

	}

	private String sendGetWeather(String city, String country) throws Exception {

		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country
				+ "&APPID=bb00cfb0674dc3adadf73d1bdcf6d2bf";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();

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

	// passing inputs and outputs for validation
	private void validateWeather(String city, String country, String response) {

		JSONObject weatherJsonObj = new JSONObject(response);

		System.out.println("Base = " + weatherJsonObj.getString("base"));

		// Accessing inner objects
		mainObj = (JSONObject) weatherJsonObj.get("main");
		System.out.println(city + "'s temp = " + mainObj.get("temp"));
		System.out.println(city + "'s humidity = " + mainObj.get("humidity"));

		// Below will loop through all the keys and get their values
		// Iterator iterator = mainObj.keys();
		// String key = null;
		// while(iterator.hasNext()){
		// key = (String)iterator.next();
		// System.out.println(key + " : "+mainObj.get(key));
		// }

		String actualCity = weatherJsonObj.getString("name");

		Assert.assertEquals(city.toLowerCase(), actualCity.toLowerCase());

	}

	private JSONObject createTestData() {

		JSONObject data = new JSONObject();
		data.put("london", "uk");
		data.put("chicago", "usa");
		data.put("delhi", "india");
		data.put("Portland", "usa");
		return data;
	}
}
