<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
	<link rel="stylesheet" type="text/css" href="stylelogin.css">
    <title>Student Page</title>
</head>
<body>
	<h1>Student Home</h1>
	<h2> Welcome
	<%
		Object u=request.getAttribute("result"); // object forwarded from servlet
        Object v=request.getAttribute("id");
        int id=Integer.parseInt(v.toString());
        String str=u.toString(),name;
        out.println("\n"+str);
        int l=str.length();
        char flag,flag2,flag3;
        flag3=str.charAt(l-1);//electing start
        flag2=str.charAt(l-3);//end of nomination
        flag=str.charAt(l-5);//start of nomination
        name=str.substring(0,l-6);
		out.println(name);
        out.println("<br>");
        out.println(id);
        //out.println(""+flag+" "+flag2+" "+flag3);
        if(flag=='T' && flag2 == 'F')
        {
            //request.setAttribute("name",name);
            //request.setAttribute("id",id);
        %>
            <form action="student_file" method="post">
                <select name="post">
					<option value="Ch">Chairman</option>
					<option value="Gs">General Secretary</option>
                    <option value="AGs">Asst. General Secretary</option>
                </select><br>
                <input type="submit"  value="File Nomination">
                <input type="hidden" name="name" value='<%=name %>'>
                <input type="hidden" name="id" value='<%=id %>'>
            </form>
            <%
            }
            else if(flag=='T' && flag2=='T'){
            %>
            <form action = "nom_filed" method = "post">
                <input type="submit" value="See Nominations Filed">
            </form>
            <%
            }
            else
            {
                out.println("<br></br>");
                out.println("Nominations have not started yet");
             }
            %>
	</h2>
</body>
</html>
