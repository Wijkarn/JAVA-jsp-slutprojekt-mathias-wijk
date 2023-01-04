package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GettheWeather {

	public static void getWeather(weatherBean wBean) throws IOException {
		String API_KEY = "f09978adc681890ff58dea9f39f88872";
		// Build the API call URL by adding city+country into a URL
		String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" + wBean.getCityStr() + ","
				+ wBean.getCountryStr() + "&APPID=" + API_KEY + "&mode=xml";

		// print and test in a browser
		System.out.println("This is the URL to send, " + URLtoSend);

		// Set the URL that will be sent
		URL line_api_url = new URL(URLtoSend);

		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Make a Buffer to read the response from the API
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		// a String to temp save each line in the response
		String inputLine;

		// a String to save the full response to use later
		String ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {

			// System.out.println(inputLine);
			// Save the temp line into the full response
			ApiResponse += inputLine;
		}
		in.close();

		// print the response
		System.out.println("This is the API response, " + ApiResponse);

		// Call a method to make a XMLdoc out of the full response
		Document doc = convertStringToXMLDocument(ApiResponse);

		// check that the XML response is OK by getting the Root element
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		// Create a Node list that gets everything in and under the "clouds" tag
		NodeList cloudList = doc.getElementsByTagName("clouds");

		// loop through the content of the tag
		for (int i = 0; i < cloudList.getLength(); i++) {
			// Save a node of the current list id
			Node node = cloudList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				// set the current node as an Element
				Element eElement = (Element) node;
				// get the content of an attribute in element
				String XMLclouds = eElement.getAttribute("name");
				// and print it
				System.out.println(wBean.getCityStr() + " is now a " + XMLclouds);
				// save it
				wBean.setCloudsStr(XMLclouds);
			}
		}

		// Create a Node list that gets everything in and under the "temperature" tag
		NodeList temperatureList = doc.getElementsByTagName("temperature");

		// loop through the content of the tag
		for (int i = 0; i < temperatureList.getLength(); i++) {
			// Save a node of the current list id
			Node node = temperatureList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				// set the current node as an Element
				Element eElement = (Element) node;

				// get the content of an attribute in element
				String XMLtemperature = eElement.getAttribute("value");

				// convert from kelvin to celcius and string to int
				double kelvin = Double.parseDouble(XMLtemperature);
				int celcius = (int) (kelvin - 273.15);

				// and print it
				System.out.println(wBean.getCityStr() + " temperature is " + celcius);

				// save it
				wBean.setTemperatureInt(celcius);

			}
		}
		
		// Create a Node list that gets everything in and under the "lastupdate" tag
		NodeList dateList = doc.getElementsByTagName("lastupdate");
				
		// loop through the content of the tag
		for (int i = 0; i < dateList.getLength(); i++) {
			// Save a node of the current list id
			Node node = dateList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				// set the current node as an Element
				Element eElement = (Element) node;

				// get the content of an attribute in element
				String lastUpdate = eElement.getAttribute("value");

				// replaces the "T" in string to " "
				String lastUpdateTemp = lastUpdate.replace("T", " ");
				
				// removes the last 9 characters so only the date is left
				String lastUpdateStr = lastUpdateTemp.substring(0, lastUpdateTemp.length()-9);
				
				// removes the first the date from the string so only time is left
				String lastUpdateTimeTemp = lastUpdateTemp.substring(10);		
				// removes seconds
				String lastUpdateTimeStr = lastUpdateTimeTemp.substring(0, lastUpdateTimeTemp.length()-3);
				
				// and print it
				System.out.println(wBean.getCityStr() + " last update is " + lastUpdateStr + " " + lastUpdateTimeStr);

				// save it
				wBean.setLastUpdateStr(lastUpdateStr);
				
				wBean.setLastUpdateTimeStr(lastUpdateTimeStr);

			}
		}

	}

	// Method the makes a XML doc out of a string, if it is in a XML format.
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
