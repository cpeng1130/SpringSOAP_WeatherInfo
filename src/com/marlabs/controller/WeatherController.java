package com.marlabs.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marlabs.webservice.WeatherSoapRequest;
import com.marlabs.wsdl.WeatherReturn;

@Controller
public class WeatherController {

	private static final Logger LOGGER = Logger.getLogger(WeatherController.class);

	@Resource
	WeatherSoapRequest weatherSoapRequest;

	@RequestMapping("/getWeather")
	@ResponseBody
	public String testSubmitJSON(String zipCode) {
		LOGGER.info("INPUT zipCode is " + zipCode);
		StringBuffer data = new StringBuffer();

		String[] zipCodes = zipCode.split(",");
		for (String zip : zipCodes) {
			if (zip == null || zip.equals("")) {
				data.append("zipCode cannot be null<br>");
				data.append("======================<br>");
			} else {
				WeatherReturn weatherReturn = weatherSoapRequest.getCityWeatherByZip(zip);
				if (weatherReturn.isSuccess()) {
					data.append("ZipCode:").append(zip).append("<br>");
					data.append("City:").append(weatherReturn.getCity()).append("<br>");
					data.append("Description:").append(weatherReturn.getDescription()).append("<br>");
					data.append("Pressure:").append(weatherReturn.getPressure()).append("<br>");
					data.append("RelativeHumidity:").append(weatherReturn.getRelativeHumidity()).append("<br>");
					data.append("Remarks:").append(weatherReturn.getRemarks()).append("<br>");
					data.append("ResponseText:").append(weatherReturn.getResponseText()).append("<br>");
					data.append("State:").append(weatherReturn.getState()).append("<br>");
					data.append("Temperature:").append(weatherReturn.getTemperature()).append("<br>");
					data.append("Visibility:").append(weatherReturn.getVisibility()).append("<br>");
					data.append("WeatherStationCity:").append(weatherReturn.getWeatherStationCity()).append("<br>");
					data.append("Wind:").append(weatherReturn.getWind()).append("<br>");
					data.append("WindChill:").append(weatherReturn.getWindChill()).append("<br>");
					data.append("WeatherID:").append(weatherReturn.getWeatherID()).append("<br>");
					data.append("======================<br>");

				} else {
					data.append("ZipCode:").append(zip).append("<br>");
					data.append("zipCode is not correct<br>");
					data.append("======================<br>");
				}
			}
		}
		return data.toString();
	}
}
