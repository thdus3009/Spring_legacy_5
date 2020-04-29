/**
 * Other : java script source file
 */

	//$("선택자").action();
		
	$("#btn").click(function(){
/* 		console.log(title=="");
		console.log(contents=='');
		console.log(title.length);
		console.log(contents.length);
		console.log($("#contents").summernote('isEmpty')); */
		
		var title = $("#title").val();
		var contents = $("#contents").val();
		
		contents = $("#contents").summernote('code');
		
		var ch3 = true; 
		
		$(".files").each(function(){/* 앞에 class는 . id는 # */
			if($(this).val()==""){
				ch3=false; //중간에 한번이라도 안넘긴게 있으면 false;
			}
		});
		
		
		
		var ch1 = title !=""; //html이기 때문에 안쓰면 그냥 "" , null안됨
		var ch2 = contents !="";
		if(ch1 && ch2 && ch3){
			//form 전송(submit 발생)
			alert("입력 완료!");
			$("#frm").submit();
		}else{
			//submit이벤트 막기
			alert("필수요소를 입력해주세요");
		}
		
		
	});
	
	/* .......................................................... */
	
	$("#contents").summernote({
		height : 400 ,
		
		 callbacks: {
			 onImageUpload: function(files, editor) {//<옆에 둘다 변수 ,files:데이터 서버에 전송,editor:summernote 자기자신 자체
				 //ajax를 통해서 파일을 보내야함
				var formData = new FormData(); //<form>태그와 유사
				 
				formData.append('files',files[0]) 
				//append(파라미터이름(name),정보(value)): 추가 <input type="file" name=""(파라미터이름)>
				
				 $.ajax({
					type : "POST",
					url : "../boardFile/fileInsert",
					data : formData,
					enctype : "multipart/form-data",
					cach: false,
					contentType : false,
					processData : false,
					success : function(imageName){//결과는 매개변수로 들어간다.
						//앞뒤 공백제거
						imageName=imageName.trim();
						//summernote에 넣기
						$("#contents").summernote('editor.insertImage', imageName);
					}
				 });
				
			 },//onImageUpload 끝
			 
			 onMediaDelete:function(files){ //files:지우려고 하는 파일의 정보가 들어있음

				 var fileName = $(files[0]).attr("src"); //fileName의 출력값에서 파일명만 필요함
				 console.log(fileName);
				 fileName = fileName.substring(fileName.lastIndexOf("/"));//자르려고하는 index번호 넣기
				 console.log(fileName);
				 $.ajax({
					 type:"POST",
					 url : "../boardFile/summerDelete",
					 data : {
						 fileName : fileName
					 },
				 	success : function(data){
				 		console.log(data);
				 	}
				 })
				 
			 }//OnMediaDelete 끝
			 
	 }//callbacks 끝
	});
	
	
	/* .......................................................... */
	
	var count = 1; //하나씩늘리기
	

	
	$("#file").on("click",".remove",function(){
		$(this).parent().remove();
		count--;
	}); /* 부모로 전달 (이벤트위임) */

	
	var count = 1;
	$("#add").click(function(){
		if(count<6){
		 $("#file").append('<div class="form-group"><input type="file" class="form-control files" name="files"><i class="glyphicon glyphicon-remove remove"></i></div>');
			count++;
		}
	});
	
	
	/* .......................................................... */
	
//	$("#contents").summernote({
//			height : 400 ,
//			
//			 callbacks: {
//				 onImageUpload: function(file) {
//					 alert("upload");
//				 }
//			 }
//		});

	
		