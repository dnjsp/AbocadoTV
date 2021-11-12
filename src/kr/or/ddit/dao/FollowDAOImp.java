package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.FollowVO;

public class FollowDAOImp implements IFollowDAO{
	private static FollowDAOImp instance;
	public static FollowDAOImp getInstance() {
		if(instance == null) instance = new FollowDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	private FollowDAOImp() {
	}
	
	@Override
	public boolean InsertFollow(FollowVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.insert("FOLLOW.insertFollow", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteFollow(FollowVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("FOLLOW.deleteFollow", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean CheckFollow(FollowVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			return sqlSession.selectOne("FOLLOW.checkFollow", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int CountFollow(String mail) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("FOLLOW.countFollow", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
