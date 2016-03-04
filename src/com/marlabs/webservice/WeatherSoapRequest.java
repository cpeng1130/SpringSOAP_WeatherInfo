package com.marlabs.webservice;

import org.apache.log4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.marlabs.wsdl.GetCityWeatherByZIP;
import com.marlabs.wsdl.GetCityWeatherByZIPResponse;
import com.marlabs.wsdl.WeatherReturn;

public class WeatherSoapRequest extends WebServiceGatewaySupport {

	private static final Logger LOGGER = Logger.getLogger(WeatherSoapRequest.class);

	public WeatherReturn getCityWeatherByZip(String zipCode) {
		LOGGER.info("input zipCode is " + zipCode);
		GetCityWeatherByZIP request = new GetCityWeatherByZIP();
		request.setZIP(zipCode);
		GetCityWeatherByZIPResponse response = (GetCityWeatherByZIPResponse) super.getWebServiceTemplate()
				.marshalSendAndReceive("http://wsf.cdyne.com/WeatherWS/Weather.asmx", request,
						new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetCityWeatherByZIP"));
		return response.getGetCityWeatherByZIPResult();
	}

}
