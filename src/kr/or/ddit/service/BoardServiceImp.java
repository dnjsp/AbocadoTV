package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.BoardDAOImp;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.RealBoardVO;

public class BoardServiceImp implements IBoardService{
	private static BoardServiceImp instance;	 
	public static BoardServiceImp getInstance() {
		if(instance == null) {
			instance = new BoardServiceImp();
			return instance;
		}
		return instance;
	}
	
	private BoardServiceImp() {
		dao = BoardDAOImp.getInstance();
	}
	 
	private BoardDAOImp dao;
	@Override
	public int CountTotalBoard() {
		return 0;
	}

	@Override
	public int InsertBoard(BoardVO vo) {
		return dao.InsertBoard(vo);
	}

	@Override
	public boolean DeleteBoard(BoardVO vo) {
		return dao.DeleteBoard(vo);
	}

	@Override
	public boolean UpdateBoard(BoardVO vo) {
		return dao.UpdateBoard(vo);
	}

	@Override
	public RealBoardVO SeeBoard(String idx) {
		return dao.SeeBoard(idx);
	}

	@Override
	public BoardVO NextBoard(String idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO PreBoard(String idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMyBoard(String mail) {
		return dao.getMyBoard(mail);
	}

	@Override
	public int getMyComment(String mail) {
		return dao.getMyComment(mail);
	}

	@Override
	public List<RealBoardVO> boardList(Map<String, Integer> map) {
		return dao.boardList(map);
	}

	@Override
	public int countList() {
		return dao.countList();
	}

	@Override
	public String selectNickName(int idx) {
		return dao.selectNickName(idx);
	}

	@Override
	public boolean UpdateCount(String idx) {
		return dao.UpdateCount(idx);
	}

}
