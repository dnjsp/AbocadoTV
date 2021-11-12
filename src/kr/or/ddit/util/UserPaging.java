package kr.or.ddit.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.BoardDAOImp;
import kr.or.ddit.dao.MemberDAOImp;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.RealBoardVO;

public class UserPaging {
	// 총 페이지 수
	private int totalPageCnt;
	// 시작 페이지
	private int startPage;
	// 마지막 페이지
	private int endPage;
	// 현재 페이지
	private int nowPage;
	// 게시글 수
	private int totalArticleCnt;
	// 한 페이지에 보여줄 게시글 수
	private int onePageArticleCnt;
	
	private int pagingList;

	public UserPaging(int totalArticleCnt, int onePageArticleCnt, int nowPage) {
		this.totalArticleCnt = totalArticleCnt;
		this.onePageArticleCnt = onePageArticleCnt;
		this.nowPage = nowPage;
		if(totalArticleCnt % onePageArticleCnt == 0) {
			totalPageCnt = totalArticleCnt / onePageArticleCnt;
		} else {
			totalPageCnt = totalArticleCnt / onePageArticleCnt + 1;
		}
		this.pagingList = nowPage/10;
		this.startPage = Math.max(1, nowPage - 4);
		this.endPage = Math.min(totalPageCnt, nowPage + 5);
	}

	public List<MemberVO> showMemberList(){
		MemberDAOImp memberDAO = MemberDAOImp.getInstance();
		List<MemberVO> list = null;
		Map<String,String> map = new HashMap<String, String>();
		map.put("start", nowPage * 10 - 10 + "");
		list = memberDAO.SelectListMember(map);
		
		return list;
	}
	
	public List<MemberVO> showMemberList(String value){
		MemberDAOImp memberDAO = MemberDAOImp.getInstance();
		List<MemberVO> list = null;
		Map<String,String> map = new HashMap<String, String>();
		map.put("member_mail", value);
		map.put("start", nowPage * 10 - 10 + "");
		list = memberDAO.SelectSearchListMember(map);
		
		return list;
	}
	
	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalArticleCnt() {
		return totalArticleCnt;
	}

	public void setTotalArticleCnt(int totalArticleCnt) {
		this.totalArticleCnt = totalArticleCnt;
	}

	public int getOnePageArticleCnt() {
		return onePageArticleCnt;
	}

	public void setOnePageArticleCnt(int onePageArticleCnt) {
		this.onePageArticleCnt = onePageArticleCnt;
	}
	
	public int getPagingList() {
		return pagingList;
	}
	
	public void setPagingList(int pagingList) {
		this.pagingList = pagingList;
	}
}
