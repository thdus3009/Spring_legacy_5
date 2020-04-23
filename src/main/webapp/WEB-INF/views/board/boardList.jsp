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
				<td><!-- title 들여쓰기 / for(int i=0;i<=?;i++)-->
				
				<c:catch> <!-- try catch 역할 -->
					<c:forEach begin="1" end="${vo.depth}">
						&nbsp;--&nbsp;
					</c:forEach>
				</c:catch>
				<a href="./${board}Select?num=${vo.num}">${vo.title}</a></td>
				<td>${vo.writer}</td>
				<td>${vo.regDate}</td>
				<td>${vo.hit}</td>
			</tr>
			</c:forEach>
		
		</table>
		<!-- vo : controller에서 지정 -->

		
		
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
		
		
		<!-- admin만 write할 수 있게.. -->
		 <br><br>
		 <!-- qna는 회원들만 글쓰기가능 / 로그인했는지 안했는지 확인 필요-->
		 
		 
		 
		 <a href="./${board}Write" class="btn btn-danger">WRITE</a>
		 
		 
		 <c:catch><!-- nullpointexception 발생할 경우를 대비하여 catch문 사용 -->
		 <c:choose>
		 	
		 	<c:when test="${board eq 'notice'}"><!-- notice -->
		 		<c:if test="${member.id eq 'admin'}">
		 			<div>
		 				<a href="./${board}Write" class="btn btn-danger">WRITE</a>
		 			</div>
		 		</c:if>
		 	</c:when>
		 	
		 	<c:otherwise>
		 		<c:if test="${not empty member}"><!-- qna -->
		 			<div>
		 				<a href="./${board}Write" class="btn btn-danger">WRITE</a>
		 			</div>
		 		</c:if>
		 	</c:otherwise>
		
		 </c:choose>
		 </c:catch>
		<div>
			
		</div>
		
	</div>
</div>	

</body>
</html>