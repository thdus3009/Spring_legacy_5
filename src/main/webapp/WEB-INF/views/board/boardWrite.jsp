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
	<div class="row">
	<h1>${board} Write</h1>
		<form action="./${board}Write" id="frm" method="post" enctype="multipart/form-data">
		
		<div class="form-group">
      <label for="title">TITLE:</label>
      <input type="text" class="form-control" id="title" name="title" >
    	</div>
    	
    	<div class="form-group">
      <label for="writer">WRITER:</label><!-- 로그인 하고 사용해야함 / tile contents내용이 있는지없는지-->
      <input type="text" class="form-control" id="writer" name="writer" value="${member.id}" readonly="readonly">
    	</div>
    	
		<div class="form-group">
      <label for="title">CONTENTS:</label>
      <textarea rows="5" cols="" class="form-control" id="contents" name="contents"></textarea>
    	</div>
    	
    	
    	<input type="button" id="add" class="btn btn-info" value="AddFile">
    	<br>
    	<div id="file"></div>
		<br>


		<input type="button" id="btn" class="btn btn-default" value="write"></input>
		</form>
		
	</div>
	</div>
	
 <script type="text/javascript" src="../resources/js/boardForm.js">
	/* main > webapp > resources > js > boardForm */
 
</script>	

<!-- 
 <script type="text/javascript">
	$("#contents").summernote({
	height : 400 ,
	
	 callbacks: {
		 onImageUpload: function(files) {
			 //ajax를 통해서 파일을 보내야함
			 $.ajax({
				type : "POST",
				url : "../boardFile/fileInsert",
				enctype : "multipart/form-data",
				cach: false,
				contentType : false,
				processData : false,
				success : function(imageName){}//결과는 매개변수로 들어간다.
			 });
		 }
 }
});
 </script> -->
</body>
</html>