<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenWeather</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<div class="centerRow">
		<form class="centerColumn" action="OWservlet" method="get">
			<p class="form">City:</p>
			<input type="text" name="city" required/><br /> 
			<p class="form">Country:</p>
			<input type="text" name="country"/><br /> 
			<input class="button" type="submit" value="Get Weather!" />
		</form>
	</div>
</body>
</html>