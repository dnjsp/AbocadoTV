package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.NoticeVO;

public interface INoticeService {
	public int InsertNotice(NoticeVO vo);
	
	public boolean UpdateNotice(NoticeVO vo);
	
	public boolean DeleteNotice(String idx);
	
	public List<NoticeVO> SelectListNotice();
	
	public int CountList();
	
	public List<NoticeVO> noticeList(Map<String, Integer> map);

	public NoticeVO SeeNotice(String idx);
	
	public boolean updateCount(String idx);
}
