package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.BlogCategoryVO;

public interface IBlogCategoryDAO {
	public int InsertCategory(BlogCategoryVO vo);
	
	public boolean DeleteCategory(String idx);
	
	public boolean UpdateCategory(BlogCategoryVO vo);
	
	public List<BlogCategoryVO> SelectCategory(String mail);
}
