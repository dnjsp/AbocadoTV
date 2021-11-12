package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.BlogContentVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.RealBlogContentVO;



public interface IBlogContentDAO {
	public int CountTotalBlogContent(String mail);
	
	public int InsertBlogContent(BlogContentVO vo);
	
	public boolean DeleteBlogContent(String idx);
	
	public boolean UpdateBlogContent(BlogContentVO vo);

	public List<BlogContentVO> SelectListBlogContents(String idx);
	
	public List<BlogContentVO> SelectListBlogContent(String category);

	public List<BlogContentVO> SelectListBlogContent(String category,String condition,String value);
	
	public BlogContentVO SeeBlogContent(String idx);
	
	public BlogContentVO NextBlogContent(String idx);
	
	public BlogContentVO NextBlogContent(String idx, String category);
	
	public BlogContentVO PreBlogContent(String idx);
	
	public BlogContentVO PreBlogContent(String idx, String category);
	///
	public BlogContentVO seeBlogContent(String idx);
	
	public int countList(String mail);
	
	public List<BlogContentVO> blogContentList(RealBlogContentVO vo);
	
	public List<BlogContentVO> categoryContentList(RealBlogContentVO vo);
	
	public int countCategoryList(String cateogry);
	
	public boolean updateCount(String idx);
	
	public List<BlogCategoryVO> getMyCategory(String email);
}
