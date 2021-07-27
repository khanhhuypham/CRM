<%@ page import="cybersoft.java12.crmapp.util.UrlUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
		<!-- Breadcrumb -->
		<div class="container page__heading-container">
			<div class="page__heading">
				<div class="d-flex align-items-center">
					<div>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb mb-0">
								<li class="breadcrumb-item"><a
									href="<c:url value=""/>">Home</a></li>

								<li class="breadcrumb-item active" aria-current="page">
									Task</li>
								<li class="breadcrumb-item active" aria-current="page">
									TaskDashBoard</li>
							</ol>
						</nav>
						<h1 class="m-0">Task</h1>
					</div>
					<div class="ml-auto">
						<a href="<c:url value=" <%=UrlUtils.TASK_DELETE%>" />"
							class="btn btn-light"><i
							class="material-icons icon-16pt text-muted mr-1">settings</i>
							Settings</a>
					</div>
				</div>
			</div>
		</div>
		<!-- End Breadcrumb -->


<!-- Page Content -->
<div class = "container">
	<table
		class="table table-dark table-striped table mb-0 thead-border-top-0"
		style="width: 1200px; margin-left: auto; margin-right: auto;">
		<thead class="table-dark">
			<tr>
				<th style="color: white;">Id</th>
				<th style="color: white;">Công Việc</th>
				<th style="color: white;">Tên Dự Án</th>
				<th style="color: white;">Người Thực Hiện</th>
				<th style="color: white;">Description</th>
				<th style="color: white;">Start_date</th>
				<th style="color: white;">End_date</th>
				<th style="color: white;"></th>
				
			</tr>
		</thead>
		<tbody class="list" id="staff">
			<c:choose>
				<c:when test="${tasks==null}">
					<tr class="row">
						<td>no data</td>
					</tr>
				</c:when>
	
				<c:otherwise>
	
					<c:forEach var="task" items="${tasks}">
						<tr>
							<td>${task.id }</td>
							<td>${task.name }</td>
							<td>${task.project.name }</td>
							<td>${task.user.name }</td>
							<td>${task.description }</td>
							<td>${task.start_date }</td>
							<td>${task.end_date }</td>
							
							<td> <a
								href="<c:url value="<%=UrlUtils.TASK_UPDATE %>" />?id=${task.id}" ><i class="fa fa-cog" style="color: white;"> </i></a></td>
								
							<td> <a href="<c:url value="<%=UrlUtils.TASK_DELETE%>" />?id=${task.id}" > <i class="fa fa-trash" style="color: white;"></i></a></td>
						
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>

<!-- END Page Content -->
    
</body>
</html>