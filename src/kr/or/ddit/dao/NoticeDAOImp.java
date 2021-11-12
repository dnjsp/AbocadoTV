package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.RealBoardVO;

public class NoticeDAOImp implements INoticeDAO{
	private static NoticeDAOImp instance;
	
	public static NoticeDAOImp getInstance() {
		if(instance == null) instance = new NoticeDAOImp();
		return instance;
	}
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	
	public NoticeDAOImp() {
		factory = SqlSessionFactoryUtil.getInstance();
	}
	
	
	@Override
	public int InsertNotice(NoticeVO vo) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			sqlSession.insert("NOTICE.insertNotice", vo);
			cnt = Integer.parseInt(vo.getNotice_index());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean UpdateNotice(NoticeVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.update("NOTICE.updateNotice",vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteNotice(String idx) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("NOTICE.deleteNotice", idx);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<NoticeVO> SelectListNotice() {
		List<NoticeVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("NOTICE.selectListNotice");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int countList() {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("NOTICE.countList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<NoticeVO> noticeList(Map<String, Integer> map) {
		List<NoticeVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("NOTICE.noticeList", map);
		} catch (Exception e) {
		}
		return list;
	}


	@Override
	public NoticeVO SeeNotice(String idx) {
		NoticeVO vo = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			vo = sqlSession.selectOne("NOTICE.seeNotice", idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}


	@Override
	public boolean updateCount(String idx) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.update("NOTICE.updateCount", idx);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
