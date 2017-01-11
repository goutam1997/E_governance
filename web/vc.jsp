<!doctype html>
<html>
<head>
    <script type='text/javascript'>
        function addFields()
		{
            // Number of inputs to create
            var number = document.getElementById("member").value;
            // Container <div> where dynamic content will be placed
            var container = document.getElementById("container");
            // Clear previous contents of the container
            while (container.hasChildNodes())
			{
                container.removeChild(container.lastChild);
            }
			container.appendChild(document.createTextNode("Enter list of Election Committee Members:\n"));
			container.appendChild(document.createElement("br"));
            for (i=1;i<=number;i++)
			{
				//container.appendChild("Hello");
                // Append a node with a random text
                container.appendChild(document.createTextNode("Member " + i));
                // Create an <input> element, set its type and name attributes
                var input = document.createElement("input");
                input.type = "text";
                input.name = "name" + i;
				input.placeholder="name";
                container.appendChild(input);
				var input = document.createElement("input");
                input.type = "text";
                input.name = "cno" + i;
				input.placeholder="contact no";
				container.appendChild(input);
				if (i==1)
				container.appendChild(document.createTextNode("Dos"));
				else
				container.appendChild(document.createTextNode("EC"));
                // Append a line break
                container.appendChild(document.createElement("br"));

            }
			var butn=document.createElement("input");
			butn.type="submit";
			butn.value="Create Committee"
			container.appendChild(butn);
			var size=document.createElement("input");
			size.type="hidden";
			size.name="size";
			size.value=""+number;
			container.appendChild(size);
        }
    </script>
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
		char flag=str.charAt(3);
        char flag2=str.charAt(5);
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
			else if ((flag=='T') && (flag2=='F')) // election procedure started
			{
			%>
				<h2> Create list here </h2>
				<!-- Election Committee formed of 3 members plus Dos -->
				Number of members: (max. 10)<input type="text" id="member" name="member" value=""><br />
				<input type="button" value="Fill Details" id="filldetails" onclick="addFields()">
				<form id="container" action = "committee" method = "post">
				</form>
			<%
			}
			else
			{
			%>
				<p> Election Committe has been formed. </p>
				<p> Display Table here </p>
			<%
			}
		}
	%>	
</body>
</html>

	
