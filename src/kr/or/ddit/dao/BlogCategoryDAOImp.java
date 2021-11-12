package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.BlogCategoryVO;

public class BlogCategoryDAOImp implements IBlogCategoryDAO{
	private static BlogCategoryDAOImp instance;
	public static BlogCategoryDAOImp getInstance() {
		if(instance == null) instance = new BlogCategoryDAOImp();
		return instance;
	}
	private SqlSession sqlSession;
	private GsonUtil gsonUtil;

	private BlogCategoryDAOImp() {
		gsonUtil = GsonUtil.getInstance();
	}
	
	public String start() {
		return gsonUtil.SwOK();
	}
	
	public String end() {
		return gsonUtil.SwOK();
	}
	
	@Override
	public int InsertCategory(BlogCategoryVO vo) {
		int success = 0;
		try {
			sqlSession =SqlSessionFactoryUtil.getSqlSession();
			sqlSession.insert("BLOGCATEGORY.insertBlogCategory", vo);
			success = Integer.parseInt(vo.getCategory_index());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean DeleteCategory(String idx) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("BLOGCATEGORY.deleteBlogCategory", idx);
			if(success == 1) return true;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateCategory(BlogCategoryVO vo) {
		sqlSession = SqlSessionFactoryUtil.getSqlSession();
		int success = sqlSession.update("BLOGCATEGORY.updateBlogCategory", vo);
		if(success == 1) return true;
		sqlSession.commit();
		return false;
	}

	@Override
	public List<BlogCategoryVO> SelectCategory(String mail) {
		List<BlogCategoryVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BLOGCATEGORY.selectBlogCategory", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
