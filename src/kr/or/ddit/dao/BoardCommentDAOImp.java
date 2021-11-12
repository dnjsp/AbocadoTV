package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.BoardCommentVO;
import kr.or.ddit.vo.RealCommentVO;

public class BoardCommentDAOImp implements IBoardCommentDAO{
	private static BoardCommentDAOImp instance;
	public static BoardCommentDAOImp getInstance() {
		if(instance == null) instance = new BoardCommentDAOImp();
		return instance;
	}
	private SqlSession sqlSession;
	
	public BoardCommentDAOImp() {
	}
	
	@Override
	public boolean InsertComment(BoardCommentVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = 0;
			success = sqlSession.insert("COMMENT.insertComment",vo);
			if(success == 1) return true;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteComment(String idx) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = 0;
			success = sqlSession.insert("COMMENT.deleteComment",idx);
			if(success == 1) return true;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RealCommentVO> SelectListComment(String boardIdx) {
		List<RealCommentVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			 list = sqlSession.selectList("COMMENT.selectComment", boardIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
