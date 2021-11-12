package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.ManagerVO;

public class ManagerDAOImp implements IManagerDAO{

	private static ManagerDAOImp instance;
	public static ManagerDAOImp getInstance() {
		if(instance == null) instance = new ManagerDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public ManagerDAOImp() {
	}
	
	@Override
	public boolean InsertManager(ManagerVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.insert("MANAGER.insertManager", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteManager(ManagerVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("MANAGER.deleteManager", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ManagerVO> SelectListManager(String mail) {
		List<ManagerVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("MANAGER.selectListManager", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean CheckManager(String mail) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.selectOne("MANAGER.checkManager", mail);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
