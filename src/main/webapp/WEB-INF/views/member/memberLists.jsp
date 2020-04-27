<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

			<table class="table table-hover">
			<tr>
				<td>id</td>
				<td>name</td>
				<td>phone</td>
				<td>email</td>
				<td><input type="checkbox" id="d1"></td>
			</tr>
			
			
			<c:forEach items="${list}" var="vo" varStatus="i"><!-- ${list} : Controller참고  // c:forEach > EL문 -->
			<tr>
				<td id="id${i.index}">${vo.id}</td>
				<td>${vo.name}</td>
				<td>${vo.phone}</td>
				<td>${vo.email}</td>
				<td><input type="checkbox" class="c1" name="del" title="id${i.index}" id="${vo.id}"></td><!-- 중복안된다면 title에  ${vo.id}사용가능-->
			</tr>
			</c:forEach>
		
		</table>
	
	<button class="btn btn-danger" id="del">delete</button>
	
	
		<div>
		
		<ul class="pagination">
		<c:if test="${pager.curBlock gt 1}">
			<li><a href="./memberList?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search} ">이전</a></li>
		</c:if>
		
		<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
			<li><a href="./memberList?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
		</c:forEach>
		
		<c:if test="${pager.curBlock lt pager.totalBlock}">
			<li><a href="./memberList?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
		</c:if>
		</ul>
		
		</div>
	

	
