<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>

      </li>
      <li><a href="${pageContext.request.contextPath}/notice/noticeList">Notice</a></li>
      <li><a href="${pageContext.request.contextPath}/qna/qnaList">QnA</a></li>
      <li><a href="${pageContext.request.contextPath}/member/memberList">Member List</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    
    <!-- c태그가 없으면 이미지가 이상하게 뜰수있다.(에러) -->
    <c:if test="${empty member}"><!-- sessionScope.생략가능 -->
      <li><a href="${pageContext.request.contextPath}/member/memberJoin"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="${pageContext.request.contextPath}/member/memberLogin"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </c:if>
      
    <c:if test="${not empty member}">  
      <li><a href="${pageContext.request.contextPath}/member/memberMyPage"><span class="glyphicon glyphicon-user"></span> My Page</a></li>
      <li><a href="${pageContext.request.contextPath}/member/memberLogOut"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
    </c:if>
    
    </ul>
  </div>
</nav>
  
 
  
</body>
</html>