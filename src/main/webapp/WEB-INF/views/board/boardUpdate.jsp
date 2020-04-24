<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="../template/boot.jsp"></c:import>
	<c:import url="../template/summer.jsp"></c:import>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	
	<div class="container">
  <h2>Form control: text area</h2>
  
  <form action="./${board}Update" id="frm" method="post" >
  
  <input type="hidden" name="num" value="${vo.num}">
    <div class="form-group">
	   	  <label for="comment">Title:</label>
	      <input type="text" value="${vo.title}" class="form-control" id="title" name="title">
	      
	      <label for="comment">Writer:</label>
	      <input type="text" class="form-control" id="writer" name="writer"  value="${member.id}" readonly="readonly" >
	      
	      <label for="comment">Contents:</label>
	      <textarea class="form-control" rows="20" id="contents" name="contents">${vo.contents}</textarea>
      <br>
      
      <input type="submit" class="btn btn-primary" value="수정완료">
    </div>
  </form>
</div>
	
<script type="text/javascript" src="../resources/js/boardForm.js">

</script>	
</body>
</html>