<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CXF Rest test application</title>
<style>
	.error {color : red;}
</style>
</head>
<body>

<h2>Technologies</h2>
<ul>
<li>CXF, JAX-RS (REST), JAX-WS (SOAP)</li>
<li>Spring Security</li>
<li>Spring bean validation, JSR 303</li>
</ul>

<a href="<c:url value="/cxf/services/"/>">WSDL / WADL location</a>



<c:if test="${not empty userDataList}">

<hr />
All users : <a href="<c:url value="/cxf/rest/user/*"/>">/rest/user/*</a>
<h2>Result</h2>

<table border="1">
<tr>
<th>Result</th>
<th>Rest URLs</th>
</tr>

<c:forEach items="${userDataList}" var="userData">


<tr>
<td>

User NIF is : ${userData.nif}
<br />
Name is : ${userData.name}
<br />
Address is : ${userData.address}
<br />
Contact is : ${userData.contact}
<br />

</td>
<td>
	<a href="<c:url value="/cxf/rest/user/${userData.nif}"/>">/cxf/rest/user/${userData.nif}</a>
<br />
	<a href="<c:url value="/cxf/rest/user/${userData.nif}.xml"/>">/cxf/rest/user/${userData.nif}.xml</a>
<br />
	<a href="<c:url value="/cxf/rest/user/${userData.nif}.json"/>">/cxf/rest/user/${userData.nif}.json</a>
<br />
</td>
</c:forEach>

</table>
<hr />

</c:if>

<h2>Read All Users</h2>
<form method="get" action="readall">
<label for="SOAP_readall">SOAP
<input class="radio" id="SOAP_readall" type="radio" name="protocol" value="SOAP">
</label>

<label for="REST_readall">REST
<input class="radio" checked id="REST_readall" type="radio" name="protocol" value="REST">
</label>
<br />

<input type="submit" value="read all" />
</form>


<hr />

<h2>Read User By NIF</h2>
<form method="get" action="read">

<label for="SOAP_read">SOAP
<input class="radio" id="SOAP_read" type="radio" name="protocol" value="SOAP">
</label>

<label for="REST_read">REST
<input class="radio" checked id="REST_read" type="radio" name="protocol" value="REST">
</label>
<br />


<label for="nif">NIF 
<input id="nif" name="nif"/>
</label>

<br />
<input type="submit" value="read" />

</form>

<hr />


<h2>Create User</h2>
<form:form method="post" action="create" commandName="userData">

<label for="SOAP_create">SOAP
<input class="radio" id="SOAP_create" type="radio" name="protocol" value="SOAP">
</label>

<label for="REST_create">REST
<input class="radio" checked id="REST_create" type="radio" name="protocol" value="REST">
</label>
<br />

<label for="userCreate">User NIF (9 char) 
<form:input id="userCreate" path="nif"/>
<form:errors path="nif" cssClass="error"/>
</label>

<label for="name">Name 
<input id="name" name="name"/>
</label>

<label for="address">Address
<input id="address" name="address"/>
</label>

<label for="contact">Contact (9 digits)
<input id="contact" name="contact"/>
</label>


<br />
<input type="submit" value="create"/>

</form:form>

<hr />

<h2>Delete User</h2>
<form method="get" action="delete">

<label for="SOAP_delete">SOAP
<input class="radio" id="SOAP_delete" type="radio" name="protocol" value="SOAP">
</label>

<label for="REST_delete">REST
<input class="radio" checked id="REST_delete" type="radio" name="protocol" value="REST">
</label>
<br />

<label for="userDelete">NIF 
<input id="userDelete" name="nif"/>
</label>

<br />
<input type="submit" value="delete" />

</form>
</body>
</html>