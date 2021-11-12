package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.RealBoardVO;

public class BoardDAOImp implements IBoardDAO{
	private static BoardDAOImp instance;
	public static BoardDAOImp getInstance() {
		if(instance == null) instance = new BoardDAOImp();
		return instance;
	}
	private SqlSession sqlSession;
	
	public BoardDAOImp() {
	}
	
	@Override
	public int CountTotalBoard() {
		return 0;
	}

	@Override
	public int InsertBoard(BoardVO vo) {
		int success = 0; 
		try {
	         sqlSession = SqlSessionFactoryUtil.getSqlSession();
	         sqlSession.insert("BOARD.insertBoard", vo);
	         success = Integer.parseInt(vo.getBoard_index());
	         sqlSession.commit();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return success;
	}

	@Override
	public boolean DeleteBoard(BoardVO vo) {
		try {
	         sqlSession = SqlSessionFactoryUtil.getSqlSession();
	         int success = sqlSession.delete("BOARD.deleteBoard", vo);
	         sqlSession.commit();
	         if(success == 1) return true;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return false;
	}

	@Override
	public boolean UpdateBoard(BoardVO vo) {
		try {
	         sqlSession = SqlSessionFactoryUtil.getSqlSession();
	         int success = sqlSession.update("BOARD.updateBoard", vo);
	         sqlSession.commit();
	         if(success == 0) return true;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return false;
	}

	@Override
	public List<RealBoardVO> boardList(Map<String, Integer> map) {
		List<RealBoardVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BOARD.boardList", map);
		} catch (Exception e) {
		}
		return list;
	}
	
	@Override
	public List<RealBoardVO> SelectListBoard(Map<String, String> map) {
		List<RealBoardVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BOARD.boardListSearch", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public RealBoardVO SeeBoard(String idx) {
		 RealBoardVO vo = null;
	      try {
	         sqlSession = SqlSessionFactoryUtil.getSqlSession();
	         vo = sqlSession.selectOne("BOARD.seeBoard", idx);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return vo;
	}

	@Override
	public BoardVO NextBoard(String idx) {
		return null;
	}

	@Override
	public BoardVO PreBoard(String idx) {
		return null;
	}
	
	@Override
	public int getMyBoard(String mail) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("BOARD.selectMyBoard", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getMyComment(String mail) {
		int cnt = 0;
		
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("BOARD.selectMyComment", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int countList()  {
		int boardNumber = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			boardNumber = sqlSession.selectOne("BOARD.countList");
		} catch (Exception e) {
		}
		return boardNumber;
	}

	@Override
	public int countListSearch(Map<String,String> map) {
		int boardNumber = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			boardNumber = sqlSession.selectOne("BOARD.countListSearch",map);
		} catch (Exception e) {
		}
		return boardNumber;
	}
	
	@Override
	public String selectNickName(int idx) {
		sqlSession = SqlSessionFactoryUtil.getSqlSession();
		String nickName = sqlSession.selectOne("BOARD.selectNickName", idx);
		return nickName;
	}
	
	public boolean UpdateCount(String idx) {
		 try {
	         sqlSession = SqlSessionFactoryUtil.getSqlSession();
	         int success = sqlSession.update("BOARD.updateCount", idx);
	         sqlSession.commit();
	         if(success == 1) return true;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return false;
	}

}
