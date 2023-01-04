<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.weatherBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Weather</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
	<jsp:include page="../index.jsp"></jsp:include>

	<%
	weatherBean wBean = (weatherBean) request.getAttribute("wBean");
	
	
	// If
	if(wBean == null){
		response.sendRedirect("./index.jsp");	
	}
	out.print("<p class=\"centerRow\">The weather in " + wBean.getCityStr()  + ", " + wBean.getCountryStr() + " is now a " + wBean.getCloudsStr() + " and is " + wBean.getTemperatureInt() + "°C.</p>");
	out.print("<div class=\"centerColumn\"><p>Time: " + wBean.getLastUpdateTimeStr() + " UTC </p><br/>");
	out.print("<p> Date: " + wBean.getLastUpdateStr() + "</p><br/></div>");
	%>

<h1 class="centerRow">Previous Search</h1>
<div class="centerRow">
	<p>${cookie["city"].getValue()}, ${cookie["country"].getValue()} ${cookie["searchHistory"].getValue()}</p>
	<!--  <p>${cookie["searchHistory"].getValue()}</p>-->
</div>
</body>
</html>