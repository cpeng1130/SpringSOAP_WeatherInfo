<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
</head>
<body>
	<div>
		<label for="input1">Input: </label><input id="input1" type="text">
		<span id="spanWarning"></span> <br>
		<button id="buttonSubmit">Submit</button>
	</div>
	<div id="weatherInfo"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#input1').on('focus',function() {
				if (!this.value) { // check empty on cursor blur
					$('#spanWarning').html("<strong>The field should not be empty.</strong>");
				} else {
					$('#spanWarning').html("");
				}
			})
			
			$('#input1').on('keyup',function() {
				if (!this.value) { // check empty on cursor blur
					$('#spanWarning').html("<strong>The field should not be empty.</strong>");
				} else {
					$('#spanWarning').html("");
				}
			})

			$('#buttonSubmit').on('click',function() {
				if ($('#input1').val().length === 0) { // check empty on button click
					alert('The input should not be empty!');
				} else {
					var myUrl = 'http://localhost:8080/WeatherInfo/getWeather';
					$.ajax({
						method : "POST",
						url : myUrl,
						data : "zipCode="+$('#input1').val()
					}).done(function(data){
						var json = JSON.stringify(data);
						$('#weatherInfo').html(json);
					});
				}
			})
		});
	</script>
</body>
</html>