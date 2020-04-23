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
	
	
	<div>
		<c:forEach items="${vo.boardFileVOs}" var="file">
		<div>
			<a href="../boardFile/fileDown?fileNum=${file.fileNum}&board=${file.board}">${file.oriName}</a>
		</div>
		</c:forEach>
	</div>
	
	<div><br><br>
		&nbsp; &nbsp;<button><a href="./${board}Update?num=${vo.num}">Update</a></button>
		&nbsp; &nbsp;<button><a href="./${board}Delete?num=${vo.num}">Delete</a></button>
		
		<!-- notice일때는 reply가 보이면 안된다. -->
		<c:if test="${board ne 'notice'}">
		&nbsp; &nbsp;<button><a href="./${board}Reply?num=${vo.num}">Reply</a></button>
		</c:if>
	</div>
</body>
</html>