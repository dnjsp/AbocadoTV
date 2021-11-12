package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.BlogContentVO;
import kr.or.ddit.vo.BlogVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.RealBlogContentVO;



public interface IBlogService {
	public int CountTotalBoard();
	
	public int InsertBoard(BlogContentVO vo);
	
	public boolean DeleteBoard(String idx);
	
	public boolean UpdateBoard(BlogContentVO vo);

	public List<BlogContentVO> SelectListBlogContents(String idx);
	
	public List<BlogVO> SelectListBoard(String category);

	public List<BlogVO> SelectListBoard(String category,String condition,String value);
	
	public BlogVO SeeBoard(String idx);
	
	public BlogVO NextBoard(String idx);
	
	public BlogVO NextBoard(String idx, String category);
	
	public BlogVO PreBoard(String idx);
	
	public BlogVO PreBoard(String idx, String category);
	
	public BlogContentVO seeBlogContent(String idx);
	
	public int countList(String mail);
	
	public List<BlogContentVO> blogContentList(RealBlogContentVO vo);
	
	public List<BlogContentVO> categoryContentList(RealBlogContentVO vo);
	
	public int countCategoryList(String cateogry);
	
	public boolean updateCount(String idx);
	
	public List<BlogCategoryVO> getMyCategory(String email);

}
