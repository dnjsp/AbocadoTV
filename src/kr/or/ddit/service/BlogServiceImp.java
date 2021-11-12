package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.BlogContentDAOImp;
import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.BlogContentVO;
import kr.or.ddit.vo.BlogVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.RealBlogContentVO;

public class BlogServiceImp implements IBlogService{
	private static BlogServiceImp instance;
	public static BlogServiceImp getInstance() {
		if(instance == null) {
			instance = new BlogServiceImp();
			return instance;
		}
		return instance;
	}
	
	private BlogContentDAOImp dao;
	
	public BlogServiceImp() {
		dao  = BlogContentDAOImp.getInstance();
	}
	
	@Override
	public int CountTotalBoard() {
		return 0;
	}

	@Override
	public int InsertBoard(BlogContentVO vo) {
		return dao.InsertBlogContent(vo);
	}

	@Override
	public boolean DeleteBoard(String idx) {
		return dao.DeleteBlogContent(idx);
	}

	@Override
	public boolean UpdateBoard(BlogContentVO vo) {
		return dao.UpdateBlogContent(vo);
	}
	
	@Override
	public List<BlogContentVO> SelectListBlogContents(String idx) {
		return dao.SelectListBlogContents(idx);
	}

	@Override
	public List<BlogVO> SelectListBoard(String category) {
		return null;
	}

	@Override
	public List<BlogVO> SelectListBoard(String category, String condition, String value) {
		return null;
	}

	@Override
	public BlogVO SeeBoard(String idx) {
		return null;
	}

	@Override
	public BlogVO NextBoard(String idx) {
		return null;
	}

	@Override
	public BlogVO NextBoard(String idx, String category) {
		return null;
	}

	@Override
	public BlogVO PreBoard(String idx) {
		return null;
	}

	@Override
	public BlogVO PreBoard(String idx, String category) {
		return null;
	}

	@Override
	public BlogContentVO seeBlogContent(String idx) {
		return dao.seeBlogContent(idx);
	}

	@Override
	public int countList(String mail) {
		return dao.countList(mail);
	}

	@Override
	public List<BlogContentVO> blogContentList(RealBlogContentVO vo) {
		return dao.blogContentList(vo);
	}

	@Override
	public List<BlogContentVO> categoryContentList(RealBlogContentVO vo) {
		return dao.categoryContentList(vo);
	}

	@Override
	public int countCategoryList(String cateogry) {
		return dao.countCategoryList(cateogry);
	}

	@Override
	public boolean updateCount(String idx) {
		return dao.updateCount(idx);
	}

	@Override
	public List<BlogCategoryVO> getMyCategory(String email) {
		return dao.getMyCategory(email);
	}

}
