package kr.or.ddit.dao;

import kr.or.ddit.vo.VisitorVO;

public interface IVisitorDAO {
	
	public boolean CheckVisitor(VisitorVO vo);

	public boolean InsertVisitor(VisitorVO vo);

	public int CountVisitor(String target);
	
	public int CountTotalVisitor(String target);
	
}
