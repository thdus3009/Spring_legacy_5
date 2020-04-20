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
		<h1>Member List</h1>
		
		<form action="./memberList">
		<div class="input-group">
		<select class="form-control" id="sel1" name="kind">
			<option value="id">Id</option>
			<option value="name">Name</option>
			<option value="phone">Phone</option>
			<option value="email">Email</option>
		</select>
		<!-- placeholder : 박스내 입력전 설명글 -->
		<input type="text" class="form-control" placeholder="Search" name="search">
		
		<div class="input-group-btn">
			      <button class="btn btn-default" type="submit">
			        <i class="glyphicon glyphicon-search"></i>
			      </button>
			    </div>
		</div>
		</form>
	
	
	
			<table class="table table-hover">
			<tr>
				<td>id</td>
				<td>name</td>
				<td>phone</td>
				<td>email</td>
			
			</tr>
			<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.id}</td>
				<td>${vo.name}</td>
				<td>${vo.phone}</td>
				<td>${vo.email}</td>
			</tr>
			</c:forEach>
		
		</table>
	
	
		<div>
		
		<ul class="pagination">
		<c:if test="${pager.curBlock gt 1}">
			<li><a href="./memberList?curPage+${pager.startNum-1}&kind=${pager.kind}&search=${pager.search} ">이전</a></li>
		</c:if>
		
		<c:if test="">
			<li style="margin-left: 10px"><a href="./memberList?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
		</c:if>
		
		<c:if test="${pager.curBlock lt pager.totalPage}">
			<li><a href="./memberlist?curPage+${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
		</c:if>
		</ul>
		
		</div>
	
	
	
	</div>
	</div>
	
</body>
</html>