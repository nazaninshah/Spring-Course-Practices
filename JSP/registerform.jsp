<html>
   <body>
      
      <form action = "result" method = "Post">
         First Name: <input type = "text" name = "first_name">
         <br />
         Last Name: <input type = "text" name = "last_name" />
		 
		 Year of Birth : <select id ="yearOfBirth" name ="yearOfBirth" required>
							<option value=""> Select Year </option>
							<%
								int currentYear = java.util.Calendar.getInstance.get(java.util.Calendar.Year);
								for (int year =currentYear; year >=1800; year--){
							%>
							<option value="<%= year %>"> <%= year %> </option>
							<%
								}
							%>
		  <br />  
         <input type = "submit" value = "Submit" />
		 
		 
      </form>
      
   </body>
</html>