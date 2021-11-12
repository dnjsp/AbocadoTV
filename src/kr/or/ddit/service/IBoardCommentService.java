package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BoardCommentVO;
import kr.or.ddit.vo.RealCommentVO;

public interface IBoardCommentService {
	public boolean InsertComment(BoardCommentVO vo);
	
	public List<RealCommentVO> SelectListComment(String boardIdx);
}
