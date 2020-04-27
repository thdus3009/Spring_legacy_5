<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../template/boot.jsp"></c:import>
<c:import url="../template/header.jsp"></c:import>


<div class="container">
  <h3>Welcome Our Website</h3>
  <p>The .navbar-right class is used to right-align navigation bar buttons.</p>
</div>

<!-- Nav -->	
<div class="container">
  <div class="jumbotron">
    <h1>Member Join</h1>      
    <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing responsive, mobile-first projects on the web.</p>
  </div>
  
</div>
	
	<div class="container">
		<div class="row">
			<h1>Member Join</h1>
			<form action="./memberJoin" method="post" enctype="multipart/form-data"><!-- file업로드 안하려면 enctype="multipart/form-data" 쓰면 안된다. -->
			
			
			
	 <div class="form-group">
      <label for="pic">Picture:</label>
      <input type="file" class="form-control" id="pic" placeholder="Enter Picture" name="pic">
    </div> 		
			
	<div class="form-group">
      <label for="id">ID:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter Id" name="id">
    	<!-- 중복체크는 이 -->
    </div>

    <div class="form-group">
      <label for="pw">PW:</label>
      <input type="password" class="form-control" id="pw" placeholder="Enter PassWord" name="pw">
    </div> 
       
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
    </div>  
    
    <div class="form-group">
      <label for="age">Age:</label>
      <input type="text" class="form-control" id="age" placeholder="Enter Age" name="age">
    </div>  
    
    <div class="form-group">
      <label for="phone">Phone:</label>
      <input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone">
    </div>          
   
     <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" placeholder="Enter Email" name="email">
    </div>  
      

      
    <button type="submit" class="btn btn-default">Add</button><br><br>
    

			</form>
			
		</div>
	</div>

<script type="text/javascript">
	$("#id").blur(function(){
		var id = $("#id").val();
		
		$.ajax({
			url:"./memberIdCheck",
			type:"post",//method형식
			data:{id:id},//parameter(서버로 보내는 데이터) //id만 보내도 충분하기 때문에 id만 보냄
			
			success:function(data){
				alert(data);
			},
			error:function(){
				alert("에러발생");
			}
		})
		
/* 		$.post("./memberIdCheck" , {id:id} , function(data){
			alert(data);
		}); */
	});
</script>
	