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

	
		