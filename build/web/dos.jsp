<%-- 
    Document   : dos
    Created on : 3 Jan, 2017, 9:59:45 PM
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
    <title>DoS Admin Page</title>
</head>
<body>
	<%
        System.out.println("Dos page is accesed");
		Object u=request.getAttribute("result");
		String str=u.toString();
		String u_type=str.substring(0,3);
		out.println("\n"+u_type);
		char flag=str.charAt(4);
        char flagn=str.charAt(6);%>
        <h1>welcome</h1>
        <%
		if (u_type.equals("DoS"))
		{
			if (flag=='F') // election procedure not strated
			{
			%>
				<h2>Welcome DoS</h2>
                <p>Elections haven't started</p>
			<%
			}
			else if (flag=='T') // election procedure started
			{
			%>
                <form action = "elec_list" method = "post">
				<button>Create list</button>
                </form>
                <%
                if(flagn=='F')
                {
                %>
                <form action = "nom_start" method = "post">
				<input type="submit" value="Start Nomination">
                </form>
			<%
                }
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


