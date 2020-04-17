<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
	<c:import url="../template/boot.jsp"></c:import>
	
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	 	<div class="row">
		<h1>&nbsp; &nbsp;Title : ${vo.title}</h1>
		<h1>&nbsp; &nbsp;Writer : ${vo.writer}</h1>
		<h1>&nbsp; &nbsp;Contents : ${vo.contents}</h1>
	</div>
	
	<div><br><br>
		&nbsp; &nbsp;<button><a href="./noticeUpdate">Update</a></button>
		&nbsp; &nbsp;<button><a href="./noticeDelete">Delete</a></button>
	</div>
</body>
</html>