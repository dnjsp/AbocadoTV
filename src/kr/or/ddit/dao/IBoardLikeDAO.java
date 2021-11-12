package kr.or.ddit.dao;

import kr.or.ddit.vo.BoardLikeVO;

public interface IBoardLikeDAO {
	public boolean InsertBoardLike(BoardLikeVO vo);
	
	public boolean DeleteBoardLike(BoardLikeVO vo);
	
	public boolean CheckBoardLike(BoardLikeVO vo);
	
	public int CountBoardLike(String idx);
}
