package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.BoardLikeVO;

public class BoardLikeDAOImp implements IBoardLikeDAO{
	private static BoardLikeDAOImp instance;
	public static BoardLikeDAOImp getInstance() {
		if(instance == null) instance = new BoardLikeDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public BoardLikeDAOImp() {
	}
	
	@Override
	public boolean InsertBoardLike(BoardLikeVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.insert("BOARDLIKE.insertBoardLike", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteBoardLike(BoardLikeVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("BOARDLIKE.deleteBoardLike", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean CheckBoardLike(BoardLikeVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			boolean success = sqlSession.selectOne("BOARDLIKE.checkBoardLike", vo);
			if(success == true) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int CountBoardLike(String idx) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("BOARDLIKE.countBoardLike", idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
