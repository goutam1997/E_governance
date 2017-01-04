<%-- 
    Document   : ec
    Created on : 3 Jan, 2017, 10:01:00 PM
    Author     : MANOSIJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
	<link rel="stylesheet" type="text/css" href="stylelogin.css">
    <title>VC Admin Page</title>
</head>
<body>
	<%
		Object u=request.getAttribute("result");
		String str=u.toString();
		String u_type=str.substring(0,2);
		out.println("\n"+u_type);
		char flag=str.charAt(3);%>
        <h1>welcome</h1>
        <%
		if (u_type.equals("EC"))
		{
			if (flag=='F') // election procedure not strated
			{
			%>
				<h2>Election not started</h2>
				<form action = "elec_start" method = "post">
				<button>Start Elections</button>
                </form>
			<%
			}
			else if (flag=='T') // election procedure started
			{
			%>
				<h2> Create list here </h2>
			<%
			}
			else
			{
			%>
				<p> List of members of Election Committe: </p>
				<p> Enter Table here </p>
			<%
			}
		}
	%>
</body>
</html>


