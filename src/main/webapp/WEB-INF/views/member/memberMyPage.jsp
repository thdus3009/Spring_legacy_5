<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <c:import url="../template/boot.jsp"></c:import>
<c:import url="../template/header.jsp"></c:import>


<div class="container">
  <h3>Right Aligned Navbar</h3>
  <p>The .navbar-right class is used to right-align navigation bar buttons.</p>
</div>

	<div class="container">
	<div class="row">
	<h1>"My Page"</h1>
		<!-- Session에서 정보꺼내기 / EL사용 -->
		<h3>Id : ${sessionScope.member.id}</h3>
		<h3>Name : ${member.name}</h3>
		<h3>Email : ${member.email}</h3>
		<h3>Phone : ${member.phone}</h3>
		<h3>Age : ${member.age}</h3>
		
		<br>
		<h4>
			<div><a href="./fileDelete">FileDelete</a> </div>
			<br>
			<img alt="" src="../resources/memberUpload/${file.fileName}" width="350px"> 
		</h4>

		
	<br>	
	<button href="" class="btn btn-primary" id="d2" >Update</button>
	<button type="submit" class="btn btn-danger" id="d1">Delete</button> <!-- 탈퇴버튼(한번더 확인해주어야함) -->
	<br><br>
	</div>
	</div>

<script type="text/javascript">
	//js document.getElementById
	//js document.querySelector
	//jquery $(선택자)
	$("#d2").click(function(){
		location.href="./memberUpdate"
	})
	
	$("#d1").click(function() {
		
		var result = confirm("탈퇴 하시겠습니까?");
		if(result){//result자체값이 true이기때문에 result==true라고 쓸 필요 없다.
		location.href="./memberDelete";
			
		}
	});
	
</script>
