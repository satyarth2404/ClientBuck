<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ClientBuck</title>

<link type = "text/css" rel = "stylesheet" href = "${pageContext.request.contextPath}/resources/css/style.css"/>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
</head>
<body>
	<div id = "wrapper">
		<div id = "header">
			<h2>ClientBuck- Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id = "container">
		
		<div id = "content">
		
		<input type = "button" value = "Add Customer" 
			onclick = "window.location.href = 'showFormForAdd';return false;"
			 class = "add-button" />
		<br>
		 Search: <input class="form-control" id="myInput" type="text" placeholder="Search.."><br><br>
		
			<table>
			 <thead>
				<tr>
					<th> First Name </th>
					<th> Last Name </th>
					<th> Email </th>
					<th> Action </th>
				</tr>
			</thead>
			<tbody id = "myTable">
				<c:forEach var = "tempCustomer" items = "${customers}">
				
				<!-- Construct an update link with customer id -->
				<c:url var = "updateLink" value = "/customer/showFormForUpdate">
					<c:param name = "customerId"  value = "${tempCustomer.id}" />
				</c:url>
				
				<!-- Construct a delete link with customer id -->
				<c:url var = "deleteLink" value = "/customer/delete">
					<c:param name = "customerId"  value = "${tempCustomer.id}" />
				</c:url>
				
					<tr>
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email}</td>
						<td>
						
						<a href = "${updateLink}">Update</a>
						|
						<a href = "${deleteLink}"
						 onclick =  "if(!(confirm('Are you sure you want to delete this customer'))) return false">Delete</a>
						</td>
					
					</tr>
				</c:forEach>
				</tbody>
				
			</table>
		
		</div>
	
	</div>
	
	<hr>
	
	<form:form action = "${pageContext.request.contextPath}/logout" method = "POST">
		<input type = "submit" value = "Logout" />
	</form:form>
	
	
</body>
</html>