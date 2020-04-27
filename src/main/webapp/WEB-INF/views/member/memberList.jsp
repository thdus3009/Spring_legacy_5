<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
  <c:import url="../template/boot.jsp"></c:import>
  <script type="text/javascript">
	//jquery
	$(function() {
		
		/////////////////////선택된것 지우기///////////////////
		//지우고 나서 이벤트를 위임 해주어야 한다.
		$("#result").on("click","#del",function(){
			var ids = []; //빈배열 생성
			//반복문
			$(".c1").each(function(){
			
				if($(this).prop("checked")){//this는 자기자신
					//var id = $(this).attr("title");
					//alert($("#"+id).text());
					//alert($(this).attr("id"));
					ids.push($(this).attr("id"));
				}
			});
			console.log(ids);

			$.ajax({
				type:"get",
				traditional : true,
				url:"./memberDeletes",
				data:{
					ids:ids
				},
				
				success:function(data){
					alert(data+"개의 정보가 지워졌습니다");
					$.get("./memberLists", function(data){
						$("#result").html(data.trim());
						//lists정보를 list에 덮어씌우기
					})
					//ajax로 지워지고 새로고침된 페이지를 바로 보여줘야 한다. / ajax로 다시 list를 가져오는 방법도 있다.
				}
		});

	});
		
		//////////////d1을 클릭하면(true) c1이전부 true로 바뀜 ////////
		
		$("#result").on("click","#d1",function(){//이벤트 위임(on)
			$(".c1").prop("checked", $(this).prop("checked"));
		});
		
		//////////////c1이 하나라도 체크안되면 d1체크 풀림/////////////
		
		$("#result").on("click",".c1",function(){//이벤트 위임(on)
			var result=true;
			$(".c1").each(function() {
				if(!$(this).prop("checked")){
					result=false;
				}
			});
			
			$("#d1").prop("checked", result);
		});

		
	//////////////////////////////////////////////////
	});
  </script>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	
	<div class="container">
	<div class="row">
		<h1>Member List</h1>
		
		<form action="./memberList">
		<div class="input-group">
		<select class="form-control" id="sel1" name="kind">
			<option value="mi">Id</option>
			<option value="mn">Name</option>
			<option value="mp">Phone</option>
			<option value="me">Email</option>
			
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
	
	
	<div id="result">
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
	
	</div>
	</div>
	
	
</div>	<!-- id:result 끝 -->

  <script type="text/javascript">	
//  java script

// 	var d1 = document.getElementById("d1");
//	var c1 = document.getElementsByClassName("c1");
	
//		$("#d1").click(function(){//방법1
//			for(i=0; i<c1.length; i++){
//				c1[i].checked=d1.checked; //d1.checked : true/false
//			}
//		});//d1을 클릭하면(true) c1이전부 true로 바뀜 
		
//		for(i=0; i<c1.length;i++){
//			c1[i].addEventListener("click", function(){//방법2
//				var result = true;
				
//				for(j=0; j<c1.length; j++){
//					if(!c1[j].checked){
//						result = false;
//						break;
//					}
//				}
//				d1.checked=result;
//			});
//		};  
	</script>
	
</body>
</html>