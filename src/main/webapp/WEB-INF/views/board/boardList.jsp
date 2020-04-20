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
	<h1>${board} List</h1>
	
						
			<form class="col-xs-6" action="./${board}List">
			  <div class="input-group">
			  <select class="form-control" id="sel1" name="kind">
				    <option value="bt">Title</option>
				    <option value="bc">Contents</option>
				    <option value="bw">Writer</option><!-- noticeMapper참고 -->
				</select>
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
				<td>num</td>
				<td>title</td>
				<td>writer</td>
				<td>date</td>
				<td>hit</td>
			</tr>
			<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.num}</td>
				<td><a href="./notice/noticeSelect?num=${vo.num}">${vo.title}</a></td>
				<td>${vo.writer}</td>
				<td>${vo.regDate}</td>
				<td>${vo.hit}</td>
			</tr>
			</c:forEach>
		
		</table>
		

		
		
		<br>
		<div style="margin-left: 400px">
		
		<ul class="pagination">
		<c:if test="${ pager.curBlock gt 1}"><!-- gt: greater 1보다 크다면 보이게 한다.(현재블록(1~5페이지)에서 이전보이면 안됨) -->
			<li><a href="./${board}List?curPage+${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a></li>
		</c:if>

			
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
			<li style="margin-left: 10px"><a href="./${board}List?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
			</c:forEach>
			
			<c:if test="${ pager.curBlock lt pager.totalBlock}"> <!-- total블럭 까지만 존재하게 끊어주기 / lt : 작다면 -->
			<li><a href="./${board}List?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
			</c:if>
		</ul>
		
		</div>
		
		
		
		 <br><br>
		<div>
			<a href="./noticeWrite" class="btn btn-danger">WRITE</a>
		</div>
		
	</div>
</div>	

</body>
</html>