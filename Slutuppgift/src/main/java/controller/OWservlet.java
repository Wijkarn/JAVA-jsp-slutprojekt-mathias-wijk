package controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.GettheWeather;
import model.weatherBean;
import model.cookieBean;

@WebServlet("/OWservlet")
public class OWservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cityStr = request.getParameter("city");
		String countryStr = request.getParameter("country");
		//String countryStr2 = countryStr.replace(" ", "+");

		weatherBean wBean = new weatherBean(cityStr, countryStr);

		request.setAttribute("wBean", wBean);

		GettheWeather.getWeather(wBean);
		//String searchHistoryString = cityStr.concat(".").concat(countryStr);
		
		// Create a cookie and set its value
		Cookie cookieCity = new Cookie("city", cityStr);
		Cookie cookieCountry = new Cookie("country", countryStr);
		
		//Cookie[] cookieList = request.getCookies();
		
		//for (int i = 0; i < cookieList.length; i++) {
			//System.out.println("Index : " + i + " equals - "+cookieList[i].getName());

			//if(cookieList[i].getName().equals("searchHistory")) {
			//	System.out.println("Result of cookie list "+cookieList[i].getName());
				
			//}
			
			//else {
			//	Cookie cookieSearchHistory = new Cookie("searchHistory", searchHistoryString);
			//}
			
		//}
		
		// Add the cookie to the response
		response.addCookie(cookieCity);
		response.addCookie(cookieCountry);
		//response.addCookie(cookieSearchHistory);

		RequestDispatcher rd = request.getRequestDispatcher("views/showWeather.jsp");
		rd.forward(request, response);
	}

}