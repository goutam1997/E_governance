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
        System.out.println("vc.jsp page opened");
		if (u_type.equals("VC"))
		{
			if (flag=='F') // election procedure not strated
			{
                System.out.println("form for electionstart is opened");
			%>
				<h2>Welcome VC</h2>
				<form action = "elec_start" method = "post">
				<button>Start Elections</button>
                </form>
			<%
			}
			else if (flag=='T') // election procedure started
			{
			%>
				<h2> Create list here </h2>
                <!-- Election Committee formed of 3 members plus Dos -->
				<form action = "committee" method = "post">
					<input type="number" name="sno1" placeholder = "S.No" min="1" max="4" step="1" value="1"/>
					<input type="text" name="name1" placeholder="name"/>
					<input type="text" name="cno1" placeholder="contact no"/>
					<select name="utype1">
						<option value="DoS">DoS</option>
						<option value="EC">EC</option>
					</select><br>
					<input type="number" name="sno2" placeholder = "S.No" min="1" max="4" step="1" value="1"/>
					<input type="text" name="name2" placeholder="name"/>
					<input type="text" name="cno2" placeholder="contact no"/>
					<select name="utype2">
						<option value="DoS">DoS</option>
						<option value="EC">EC</option>
					</select><br>
					<input type="number" name="sno3" placeholder = "S.No" min="1" max="4" step="1" value="1"/>
					<input type="text" name="name3" placeholder="name"/>
					<input type="text" name="cno3" placeholder="contact no"/>
					<select name="utype3">
						<option value="DoS">DoS</option>
						<option value="EC">EC</option>
					</select><br>
					<input type="number" name="sno4" placeholder = "S.No" min="1" max="4" step="1" value="1"/>
					<input type="text" name="name4" placeholder="name"/>
					<input type="text" name="cno4" placeholder="contact no"/>
					<select name="utype4">
						<option value="DoS">DoS</option>
						<option value="EC">EC</option>
					</select><br>
					<button>Create Election Committe</button>
				</form>
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

	
