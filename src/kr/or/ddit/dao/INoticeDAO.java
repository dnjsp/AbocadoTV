package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.RealBoardVO;

public interface INoticeDAO {
	public int InsertNotice(NoticeVO vo);
	
	public boolean UpdateNotice(NoticeVO vo);
	
	public boolean DeleteNotice(String idx);
	
	public List<NoticeVO> SelectListNotice();
	
	public int countList();
	
	public List<NoticeVO> noticeList(Map<String, Integer> map);
	
	public NoticeVO SeeNotice(String idx);
	
	public boolean updateCount(String idx);
}
