<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlUtils" %>
<head>
<meta charset="UTF-8">
<title>Add New User</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlUtils.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlUtils.USER_DASHBOARD %>" />">User</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Update User
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Update User</h1>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
            <div class="row no-gutters">
                <div class="col-lg-4 card-body">
                    <p><strong class="headings-color">Rules</strong></p>
                    <p class="text-muted">There is no rule!</p>
                </div>
                <div class="col-lg-8 card-form__body card-body">
                    <form action="<c:url value="<%=UrlUtils.USER_UPDATE %>" />" method="post">
                    	<div class = "form-group">
                    		<label for = "id">Employee ID:</label>
                    		<input type = "text" class = "form-control" name = "id" id = "id" aria-describedby = "helpId" value = "${userDTO.getID()}" readonly/>
                    	</div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name ="email" value = "${userDTO.email}">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name = "password" value = "${userDTO.password}">
                        </div>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name = "name" value = "${userDTO.name}">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="text" class="form-control" id="phone" name = "phone" value = "${userDTO.phone}">
                        </div>
                         <div class="form-group">
                            <label for="address">Address:</label>
                            <textarea type="text" class="form-control" id="address" name = "address" aria-label="With textarea" value = "${userDTO.address}"></textarea>
                        </div>
	                        <c:if test = "">
		                        <div class="form-group">
		                                <label for="role">Role</label>
		                                <select id="role" name = "role" data-toggle="select" class="form-control">
		                                    <option selected="" value="1">ADMIN</option>
		                                    <option value="2">LEADER</option>
		                                    <option value="3">STAFF</option>
		                                </select>
		                        </div>
	                        </c:if>
                        <button type="submit" class="btn w-25 text-center btn-primary">Update</button>
                    </form>
                </div>
            </div>
        </div>
     </div>
</body>