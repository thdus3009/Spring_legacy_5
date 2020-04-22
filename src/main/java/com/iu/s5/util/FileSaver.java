package com.iu.s5.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component //service, controller, repository가 아닌 나머지 : component
public class FileSaver {
	
	//1.폴더 생성
	//2.저장할 파일명 생성
	//3.파일 HDD 저장
	
	
	//join에 넣은 파일을 C:\java\sts-bundle\pivotal-tc-server\instances\base-instance\wtpwebapps\Spring_legacy_5\resources\memberUpload에 올리기

	//1. FileCopyUtils 클래스 사용
	public String saveByUtils(MultipartFile file, String path)throws Exception{ // 파일이랑 경로 받아오기!(MultipartFile,String)
		//memberService에서 실행하려는 일을 분담한다.
		File f = new File(path);
		
		if(!f.exists()) { //exists : boolean타입
			//file이 존재하지 않는다면 해당정보의 폴더를 만들어준다.
			f.mkdirs();  //mkdir : make directory > 있는폴더/없는폴더  //mkdirs : 부모폴더까지 만들고 싶을때 > 있는폴더/없는폴더/없는폴더 
		}
		
		//똑같은 명의 파일만드는거 방지하기 > "UUID" 또는 "시간"을 이용
		//a. 저장할 파일명 생성
		String fileName = this.makeNameByUUID(file.getOriginalFilename());
		f= new File(f, fileName); //parent : 경로명 , child : 파일명
		
		//b. HDD에 저장
		FileCopyUtils.copy(file.getBytes(), f); //파일 byte가져오기~
		
		return fileName;
	}
	
	//2. MultipartFile클래스 (위랑 똑같은데 저장하는 경로가 다르다)
	public String saveByTransfer(MultipartFile file, String path)throws Exception{
		File f = new File (path);
		
		if(!f.exists()){
			f.mkdirs();
		}
		
		String fileName = this.makeNameByUUID(file.getOriginalFilename());
		f= new File(f, fileName);
		file.transferTo(f); //요기부분만 틀림
		
		return fileName;		
	}
	
	//3.OutputStream(어려운 방법. 혹시 회사가면 볼지도 모르니 적어놓기만 할거임)
	public String saveByStream(MultipartFile file, String path) throws Exception{
		File f = new File(path);
		
		if(!f.exists()){
			f.mkdirs();
		}
		String fileName = this.makeNameByUUID(file.getOriginalFilename());
		f= new File(f, fileName);
		FileOutputStream fs = new FileOutputStream(f);
		fs.write(file.getBytes());
		fs.close();
		return fileName;
	}

	
	
	private String makeNameByUUID(String name) {
		String result = UUID.randomUUID().toString();//랜덤으로 만든것을 string으로 리턴
		result = result + "_" + name;
		return result;
	}
	
	
	private String makeNameByTime(String name) {
		Calendar ca = Calendar.getInstance(); //추상클래스
		Long l = ca.getTimeInMillis(); // 현재시간을 밀리세컨즈로 바꾼다
		
		//substring, split
		String result = name.substring(0, name.indexOf("."));
		result = result +"_"+l; //파일이름_시간(long타입).확장자(jpg)
		result = result + name.substring(name.lastIndexOf("."));
		
		System.out.println(result);
		return result;
	}
	
}
