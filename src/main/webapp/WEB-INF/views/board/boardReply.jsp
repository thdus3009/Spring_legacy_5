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

<div class="container">
	<div class="row">
	<h1>${board} Reply</h1>
		<form action="./${board}Reply" id="frm" method="post">
		
		<input type="hidden" name="num" value="${num}">
		
		<div class="form-group">
      <label for="title">TITLE:</label>
      <input type="text" class="form-control" id="title" name="title">
    	</div>
    	
    	<div class="form-group">
      <label for="writer">WRITER:</label>
      <input type="text" class="form-control" id="writer" name="writer">
    	</div>
    	
		<div class="form-group">
      <label for="title">CONTENTS:</label>
      <textarea rows="5" cols="" class="form-control" id="contents" name="contents"></textarea>
    	</div>
		
		<button type="submit" class="btn btn-default">write</button>
		</form>
		
	</div>
	</div>
</body>
</html>