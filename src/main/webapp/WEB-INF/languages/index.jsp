<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

	<table>
	    <thead>
	        <tr>
	            <th>Name</th>
	            <th>Creator</th>
	            <th>Version</th>
	            <th>Action</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${languagesList}" var="x">
	        <tr>
	            <td><a href="/languages/${x.id}"><c:out value="${x.name}"/></a></td>
	            <td><c:out value="${x.creator}"/></td>
	            <td><c:out value="${x.version}"/></td>
	            <td><a href="/languages/${x.id}/edit">Edit </a>|<a href="/languages/delete/${x.id}"> Delete</a></td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>

<form:form action="/languages" method="post" modelAttribute="languages">
    <p>
        <form:label path="name">Name</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="creator">Creator</form:label>
        <form:errors path="creator"/>
        <form:input path="creator"/>
    </p>
    <p>
        <form:label path="version">Version</form:label>
        <form:errors path="version"/>
        <form:input path="version"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form>
