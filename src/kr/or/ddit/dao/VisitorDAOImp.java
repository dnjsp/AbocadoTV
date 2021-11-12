package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.VisitorVO;

public class VisitorDAOImp implements IVisitorDAO{
	private static VisitorDAOImp instance;
	public static VisitorDAOImp getInstance() {
		if(instance == null) instance = new VisitorDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public VisitorDAOImp() {
	}
	
	@Override
	public boolean CheckVisitor(VisitorVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			return sqlSession.selectOne("VISITOR.checkVisit",vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean InsertVisitor(VisitorVO vo) {
		int success = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			success = sqlSession.insert("VISITOR.insertVisit",vo);
			sqlSession.commit();
			if(success > 0) return true; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int CountVisitor(String target) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			return sqlSession.selectOne("VISITOR.countVisitor",target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int CountTotalVisitor(String target) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			return sqlSession.selectOne("VISITOR.countTotalVisitor",target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
