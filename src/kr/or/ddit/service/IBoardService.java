package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.RealBoardVO;

public interface IBoardService {
	public int CountTotalBoard();
	
	public int InsertBoard(BoardVO vo);
	
	public boolean DeleteBoard(BoardVO vo);
	
	public boolean UpdateBoard(BoardVO vo);
	
	public RealBoardVO SeeBoard(String idx);
	
	public BoardVO NextBoard(String idx);
	
	public BoardVO PreBoard(String idx);
	
	public int getMyBoard(String mail);
	
	public int getMyComment(String mail);
	
	public List<RealBoardVO> boardList(Map<String, Integer> map) throws SQLException;
	
	public int countList() throws SQLException;
	
	public String selectNickName(int idx);
	
	public boolean UpdateCount(String idx);
}
