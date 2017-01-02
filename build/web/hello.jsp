<%-- 
    Document   : hello
    Created on : 2 Jan, 2017, 7:47:34 PM
    Author     : MANOSIJ
--%>

<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
	<link rel="stylesheet" type="text/css" href="stylelogin.css">
    <title>Hello</title>
</head>
<body>
	<h1>Student Home</h1>
	<h2> Welcome
	<%
		Object u=request.getAttribute("result"); // object forwarded form servlet
		out.println("\n"+u.toString());
	%>
	</h2>
</body>
</html>


