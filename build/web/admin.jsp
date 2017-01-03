<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
	<link rel="stylesheet" type="text/css" href="stylelogin.css">
    <title>Admin Page</title>
</head>
<body>
	<%
		Object u=request.getAttribute("result");
		String str=u.toString();
		String u_type=str.substring(0,2);
		out.println("\n"+u_type);
		char flag=str.charAt(3);
		if (u_type.equals("VC"))
		{
			if (flag=='F') // election procedure not strated
			{
			%>
				<h2>Welcome VC</h2>
				<form action = "elec_start" method = "post">
				<button>Start Elections</button>
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

	
