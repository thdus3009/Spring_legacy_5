package com.iu.util;

public class Pager {

	private Long curPage;
	private Integer perPage; //null이 넘어올수있기때문에
	
	private long startRow;
	private long lastRow;
	
	private long totalPage; // 한페이지에 10개의글 표시
	private long totalBlock; // 한페이지에 5개의 페이지 표시
	private long curBlock; //현재 블록(페이지)
	
	private long startNum;
	private long lastNum;
	
	private String kind;
	private String search;
	
	
	public void makeRow() {
		System.out.println("curPage : "+this.getCurPage());
		this.startRow = (this.getCurPage()-1)*this.getPerPage()+1;
		
		this.lastRow = this.getCurPage()*this.getPerPage();
	} //특정한 데이터 10개를 가져오는 식 완성
	
	public void makePage(long totalCount) {
		//--------------------------------
				//1. 총 글의 갯수 : totalCount
				//아무것도 검색안하면 전체글의 갯수 가져오기(Mapper)
				
				//2. 총 페이지 갯수(총 num이 140개면 페이지는 총14페이지) // totalCount로 totalPage 계산
				this.totalPage = totalCount/this.getPerPage();
				if(totalCount%this.getPerPage() != 0) { //이미나눠진 페이지가 아닌 원래값에서 나머지를 확인해야 하기 때문
					this.totalPage++;
				}
				
				//3. totalPage로 totalBlock을 계산
				long perBlock = 5L; //한block당 Page 수
				this.totalBlock = totalPage/perBlock;
				if(totalPage%perBlock !=0) {
					this.totalBlock++;
				}
				
				//4.curPage 로 curBlock찾기 / curPage 1~5 > curBlock 1 , curPage 6~10 > curBlock 2 .... 
				this.curBlock = this.curPage/perBlock;
				if(this.curPage%perBlock != 0) {
					this.curBlock++;
				}
				
				//5. curBlock으로 startNum, lastNum 계산
				this.startNum = (this.curBlock-1)*perBlock+1;
				this.lastNum = this.curBlock*perBlock;
				
				//6. 마지막 블럭이면 거기서 끊어주기 
				if(this.curBlock==this.totalBlock) {
					this.lastNum=this.totalPage;
				}
	}
	
	
	
	
	public long getTotalBlock() {
		return totalBlock;
	}

	public long getCurBlock() {
		return curBlock;
	}

	public long getStartNum() {
		return startNum;
	}

	public long getLastNum() {
		return lastNum;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public long getStartRow() {
		return startRow;
	}


	public long getLastRow() {
		return lastRow;
	}




	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	
	
	
	
	public Long getCurPage() {
		if(this.curPage == null || this.curPage ==0) {
			this.curPage=1L; //long타입이라서
		}
		return curPage;
	}
	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
	
	public Integer getPerPage() {
		if(this.perPage == null || this.perPage == 0) {
			this.perPage=10;
		}
		return perPage;
	}
	
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	
	
}
