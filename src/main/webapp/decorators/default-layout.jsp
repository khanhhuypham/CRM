<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://www.opensymphony.com/sitemesh/decorator" prefix = "dec"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page = "/decorators/components/head-link.jsp"/>
    
    <title>
		<dec:title />
	</title>
	<dec:head />
</head>
<body class="layout-fixed">
	<div class = "preloader"></div>
	
	<!--Header layout -->
	<div class="mdk-header-layout js-mdk-header-layout">
		<!-- Header -->
		<jsp:include page = "/decorators/components/header.jsp"></jsp:include>
		<!-- // END Header -->
		
		<!-- Header Layout Content -->
		<div class = "mdk-header-layout__content page">
			<jsp:include page = "/decorators/components/navigation-bar.jsp"></jsp:include>
			<jsp:include page = "/decorators/components/main.jsp"/>
		</div>    	
    	<!-- // END Header Layout Content -->
    	
	</div>
    
    <jsp:include page = "/decorators/components/body-script.jsp"/>
</body>
</html>