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
	   	  <label for="title">Title:</label>
	      <input type="text" value="${vo.title}" class="form-control" id="title" name="title">
	      
	      <label for="writer">Writer:</label>
	      <input type="text" class="form-control" id="writer" name="writer"  value="${member.id}" readonly="readonly" >
	      
	      <label for="contents">Contents:</label>
	      <textarea class="form-control" rows="20" id="contents" name="contents">${vo.contents}</textarea>
     
     		<label for="files">Files:</label>
     		<c:forEach items="${vo.boardFileVOs}" var="fileVO">
     			<p>${fileVO.oriName}<i id="${fileVO.fileNum}" class="glyphicon glyphicon-remove remove fileDelete"></i></p>
     		</c:forEach>
      <br>
      
      
      <input type="submit" class="btn btn-primary" value="수정완료">
    </div>
  </form>
</div>
	
<script type="text/javascript" src="../resources/js/boardForm.js" >

</script>
<script type="text/javascript">
$(".fileDelete").click(function(){/* i태그 */
	var s = $(this);
	$.post("../boardFile/fileDelete",{fileNum:$(this).attr("id")},function(data){
		data = data.trim()
		console.log(data);
		console.log(data>0);
 		if(data>0){
				s.parent().remove();
		}else{
			alert("File Delete Fail");
		} 
	});
});

</script>	
</body>
</html>