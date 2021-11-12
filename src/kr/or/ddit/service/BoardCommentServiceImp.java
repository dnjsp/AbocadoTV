package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.BoardCommentDAOImp;
import kr.or.ddit.vo.BoardCommentVO;
import kr.or.ddit.vo.RealCommentVO;

public class BoardCommentServiceImp implements IBoardCommentService{
	private static BoardCommentServiceImp instance;
	
	public static BoardCommentServiceImp getInstance() {
		if(instance == null) {
			instance = new BoardCommentServiceImp();
			return instance;
		}
		return instance;
	}
	
	private BoardCommentDAOImp dao;
	
	private BoardCommentServiceImp() {
		dao = BoardCommentDAOImp.getInstance();
	}
	
	@Override
	public boolean InsertComment(BoardCommentVO vo) {
		return dao.InsertComment(vo);
	}

	@Override
	public List<RealCommentVO> SelectListComment(String boardIdx) {
		return dao.SelectListComment(boardIdx);
	}

	
}
