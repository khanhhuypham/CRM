<%@ page import="cybersoft.java12.crmapp.util.UrlUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- Breadcrumb -->
<div class="container page__heading-container">
    <div class="page__heading">
        <div class="d-flex align-items-center">
            <div>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlUtils.HOME %>"/>">Home</a></li>
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlUtils.ROLE_DASHBOARD %>"/>">Role</a></li>
                        <li class="breadcrumb-item active" aria-current="page">
                            Roles Dashboard
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Role Dashboard</h1>
            </div>
            <div class="ml-auto">
                <a href="<c:url value = "<%=UrlUtils.ROLE_ADD %>"/>" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
    Add New Role</a>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumb -->

<div class="container">
	<div class="table-responsive border-bottom" data-toggle="lists" data-lists-values="[&quot;js-lists-values-employee-name&quot;]">
	    <table class="table mb-0 thead-border-top-0">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>name</th>
	                <th>Department</th>
	                <th>Description</th>
	                <th></th>
	            </tr>
	        </thead>
	        <tbody class="list" id="staff02">
		        <c:choose>
		        	<c:when test = "${roles == null}">
		        		<tr class="row">
               				<td class="col-12 text-center">There is no data.</td>
               			</tr>
		        	</c:when>
		        	<c:otherwise>
		        		<c:forEach var = "role" items = "${roles}">
		        		<tr>
		        			<td>${role.getID()}</td>
			                <td><span class="badge badge-warning">${role.getName()}</span></td>
			                <td><small class="text-muted">IT</small></td>
			                <td>${role.getDescription()}</td>
		                 	<td>
	                           	<a href="<c:url value = "<%=UrlUtils.ROLE_UPDATE %>"/>?id=${role.getID()}" class="text-muted"><i class="material-icons">settings</i></a>
	                           	<a href="<c:url value="<%=UrlUtils.ROLE_DELETE%>"/>?id=${role.getID()}" class="text-muted"><i class="material-icons">delete</i></a>
                           	</td>
			            </tr>
		        	</c:forEach>
		        	</c:otherwise>
		        </c:choose>
            </tbody>
	    </table>
	</div>
</div>

	
</body>
</html>