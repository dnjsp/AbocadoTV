package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.BoardCommentVO;
import kr.or.ddit.vo.RealCommentVO;

public interface IBoardCommentDAO {
	public boolean InsertComment(BoardCommentVO vo);
	
	public boolean DeleteComment(String idx);
	
	public List<RealCommentVO> SelectListComment(String boardIdx);
}
