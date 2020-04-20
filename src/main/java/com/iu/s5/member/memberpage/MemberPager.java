package com.iu.s5.member.memberpage;

public class MemberPager {

	private Long curPage;
	private Integer perPage;
	
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
		System.out.println("curPage : " +this.getCurPage());
		this.startRow = (this.getCurPage()-1)*this.getPerPage()+1;
		
		this.lastRow = this.getCurPage()*this.getPerPage();
	}
	
	
	public void makePage(long totalCount) {
		//perPage : 10
		//perBlock : 5
		
		this.totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			this.totalPage++;
		}
		
		long perBlock = 5L;
		this.totalBlock = totalPage/perBlock;
		if(totalPage%perBlock != 0) {
			this.totalBlock++;
		}
		//
		this.curBlock = this.curPage/perBlock;//curPage :2 
		if(this.curPage%perBlock != 0) {
			this.curBlock++;
		}
		//
		this.startNum = (this.curBlock-1)*perBlock+1;
		this.lastNum = this.curBlock*perBlock;
		
		if(this.curBlock==this.totalBlock) {
			this.lastNum=this.totalPage;
		}
	}

	
	
	
	public Long getCurPage() {
		if(this.curPage==null|| this.curPage==0) {
			this.curPage=1L; 
			// 처음들어와서 현재페이지를 선택못해줬을때 (파라미터값을 못넘겨 주었을때)
			// 페이지를 1페이지로 설정한다.
		}
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public Integer getPerPage() {
		if(this.perPage == null || this.perPage==0) {
			this.perPage=10; //계속 값이 바뀌니까 getter에 적어주었다.
		}
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	
	
	
	public long getStartRow() {
		return startRow;
	}

	public long getLastRow() {
		return lastRow;
	}

	public long getTotalPage() {
		return totalPage;
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

	public String getKind() {
		return kind;
	}

	public String getSearch() {
		return search;
	}

	
}
