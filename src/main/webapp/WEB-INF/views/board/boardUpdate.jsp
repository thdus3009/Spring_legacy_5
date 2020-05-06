<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    <!-- fn함수 사용가능 -->
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
  <h2>${fn:toUpperCase(board)} Update Form</h2>
  
  <form action="./${board}Update" id="frm" method="post" enctype="multipart/form-data" >
  
  <input type="hidden" name="num" value="${vo.num}">
  
    <div class="form-group">
	   	  <label for="title">Title:</label>
	      <input type="text" value="${vo.title}" class="form-control" id="title" name="title">
	      
	      <label for="writer">Writer:</label>
	      <input type="text" class="form-control" id="writer" name="writer"  value="${member.id}" readonly="readonly" >
	      
	      <label for="contents">Contents:</label>
	      <textarea class="form-control" rows="20" id="contents" name="contents">${vo.contents}</textarea>
     
     
        <input type="button" id="add" class="btn btn-info" value="AddFile">
    	<br>
    	
    	<div id="file">
    	</div>
    	
		<br>
     <!-- boardFileVOs > 여러개 > list,map,배열 / NoticeVO에서 확인하면 list임을 알수있다.-->
    		<label for="files">Files:</label>
    		<c:catch>
    		
     		<c:forEach items="${vo.boardFileVOs}" var="fileVO">
     			<p>${fileVO.oriName}<i id="${fileVO.fileNum}"  title="${fileVO.board}" class="glyphicon glyphicon-remove remove fileDelete"></i></p>
     		</c:forEach>
     		
     		</c:catch>

      <br>
      
      
      <input type="submit" class="btn btn-primary" value="수정완료">
    </div>
  </form>
</div>
	
<script type="text/javascript" src="../resources/js/boardForm.js" ></script>

<script type="text/javascript">


var size = ${size};

size = ${vo.boardFileVOs.size()};
size = ${fn:length(vo.boardFileVOs)};

alert(size);
//setCount(size);
count = count+size;

$(".fileDelete").click(function(){/* i태그 */
	
	var check = confirm("정말 지우시겠습니까?");
	
	if(check){
			var s = $(this);
			$.post("../boardFile/fileDelete",{fileNum:$(this).attr("id") , board:$(this).attr("title")} , function(data){
				data = data.trim()
				console.log(data);

		 		if(data>0){
						s.parent().remove();
						count--;
				}else{
					alert("File Delete Fail");
				} 
			});
	}
});

</script>	
</body>
</html>