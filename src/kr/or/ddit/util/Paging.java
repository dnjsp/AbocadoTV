package kr.or.ddit.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.BlogContentDAOImp;
import kr.or.ddit.dao.BoardDAOImp;
import kr.or.ddit.dao.NoticeDAOImp;
import kr.or.ddit.vo.BlogContentVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.RealBlogContentVO;
import kr.or.ddit.vo.RealBoardVO;


public class Paging {
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
	
	
	public Paging(int totalArticleCnt, int onePageArticleCnt, int nowPage) {
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
	public List<RealBoardVO> showBoardList() {
		BoardDAOImp dao = BoardDAOImp.getInstance();
		List<RealBoardVO> list = null;
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", nowPage * 10 - 10);
		list = dao.boardList(map);
		return list;
	}
	
	public List<RealBoardVO> showSearchList(String search, String condition){
		BoardDAOImp dao = BoardDAOImp.getInstance();
		List<RealBoardVO> list = null;
		Map<String,String> map = new HashMap<String, String>();
		map.put("start", nowPage * 10 - 10 + "");
		map.put("condition", condition);
		map.put("search",search);
		list = dao.SelectListBoard(map);
		
		return list;
	}
	
	public List<NoticeVO> showNoticeList() {
		NoticeDAOImp dao = NoticeDAOImp.getInstance();
		List<NoticeVO> list = null;
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", nowPage * 10 - 10);
		list = dao.noticeList(map);
		return list;
	}

	public List<BlogContentVO> showBlogContentList(RealBlogContentVO vo) {
		BlogContentDAOImp dao = BlogContentDAOImp.getInstance();
		List<BlogContentVO> list = null;
		vo.setStartPage(nowPage * 10 -10);

		list = dao.blogContentList(vo);
		return list;
	}

	public List<BlogContentVO> showCategoryContentList(RealBlogContentVO vo) {
		BlogContentDAOImp dao = BlogContentDAOImp.getInstance();
		List<BlogContentVO> list = null;
		vo.setStartPage(nowPage * 10 -10);
		
		list = dao.categoryContentList(vo);
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
