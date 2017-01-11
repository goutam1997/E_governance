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
    <title>EC Admin Page</title>
</head>
<body>
	<%
		Object u=request.getAttribute("result");
		String str=u.toString();
		String u_type=str.substring(0,2);
		out.println("\n"+u_type);
		char flag,flag2;
        flag=str.charAt(3);//for election start
        flag2=str.charAt(5);//for committee creation
        %>
        <h1>welcome</h1>
        <%
		if (u_type.equals("EC"))
		{
			if (flag=='F') // election procedure not strated
			{
			%>
				<h2>Election not started</h2>
			<%
			}
			else if (flag=='T' && flag2=='T') // election procedure started
			{
			%>
				<form action = "nom_filed" method = "post">
                    <input type="submit" value="See Nominations Filed">
                </form>
			<%
			}
			else
			{
			%>
				<p> Committee yet to be formed </p>
			<%
			}
		}
	%>
</body>
</html>


