package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.NoticeDAOImp;
import kr.or.ddit.vo.NoticeVO;

public class NoticeServiceImp implements INoticeService{
	private static NoticeServiceImp instance;
	public static NoticeServiceImp getInstance() {
		if(instance == null) {
			instance = new NoticeServiceImp();
			return instance;
		}
		return instance;
	}
	
	private NoticeDAOImp dao;
	
	private NoticeServiceImp() {
		dao = NoticeDAOImp.getInstance();
	}
	
	@Override
	public int InsertNotice(NoticeVO vo) {
		return dao.InsertNotice(vo);
	}

	@Override
	public boolean UpdateNotice(NoticeVO vo) {
		return dao.UpdateNotice(vo);
	}

	@Override
	public boolean DeleteNotice(String idx) {
		return dao.DeleteNotice(idx);
	}

	@Override
	public List<NoticeVO> SelectListNotice() {
		return dao.SelectListNotice();
	}

	@Override
	public int CountList() {
		return dao.countList();
	}
	
	@Override
	public List<NoticeVO> noticeList(Map<String, Integer> map) {
		return dao.noticeList(map);
	}
	
	@Override
	public NoticeVO SeeNotice(String idx) {
		return dao.SeeNotice(idx);
	}

	@Override
	public boolean updateCount(String idx) {
		return dao.updateCount(idx);
	}
}
