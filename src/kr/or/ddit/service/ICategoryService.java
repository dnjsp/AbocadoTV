package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BlogCategoryVO;

public interface ICategoryService {
	public String InsertCategory(BlogCategoryVO vo);
	
	public String DeleteCategory(String idx);
	
	public String UpdateCategory(BlogCategoryVO vo);
	
	public List<BlogCategoryVO> SelectCategory(String mail);
}
