package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.RealBoardVO;

public interface IBoardDAO {
	public int CountTotalBoard();
	
	public int InsertBoard(BoardVO vo);
	
	public boolean DeleteBoard(BoardVO vo);
	
	public boolean UpdateBoard(BoardVO vo);
	
	/**
	 * 검색
	 * @param condition 검색 조건
	 * @param value 검색 값
	 * @return
	 */
	public List<RealBoardVO> SelectListBoard(Map<String, String> map);
	
	public RealBoardVO SeeBoard(String idx);
	
	public BoardVO NextBoard(String idx);
	
	public BoardVO PreBoard(String idx);
	
	public List<RealBoardVO> boardList(Map<String, Integer> map) throws SQLException;
	
	public int countList();
	
	public int countListSearch(Map<String,String>map);
	
	public int getMyBoard(String mail);
	
	public int getMyComment(String mail);
	
	public String selectNickName(int idx);
	
	public boolean UpdateCount(String idx);

}
