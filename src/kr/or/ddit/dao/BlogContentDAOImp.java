package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.BlogContentVO;
import kr.or.ddit.vo.RealBlogContentVO;

public class BlogContentDAOImp implements IBlogContentDAO{

	private static BlogContentDAOImp instance;
	public static BlogContentDAOImp getInstance() {
		if(instance == null) instance = new BlogContentDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public BlogContentDAOImp() {
	}
	
	@Override
	public int CountTotalBlogContent(String mail) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.selectOne("BLOGCONTENT.countTotalBlogContent", mail);
			if(success == 1) cnt = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int InsertBlogContent(BlogContentVO vo) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			sqlSession.insert("BLOGCONTENT.insertBlogContent",vo);
			cnt = Integer.parseInt(vo.getBlog_index());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean DeleteBlogContent(String idx) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("BLOGCONTENT.deleteBlogContent", idx);
			System.out.println(success);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override/////////////////////////////////
	public boolean UpdateBlogContent(BlogContentVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.update("BLOGCONTENT.updateBlogContent", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<BlogContentVO> SelectListBlogContents(String idx) {
		List<BlogContentVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BLOGCONTENT.selectListBlogContent", idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BlogContentVO> SelectListBlogContent(String category) {
		List<BlogContentVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BLOGCONTENT.selectListBlogContent", category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<BlogContentVO> blogContentList(RealBlogContentVO vo){
		List<BlogContentVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BLOGCONTENT.blogContentList", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int countList(String mail) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("BLOGCONTENT.countList", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public BlogContentVO seeBlogContent(String idx) {
		BlogContentVO vo = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			vo = sqlSession.selectOne("BLOGCONTENT.seeBlogContent", idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<BlogContentVO> SelectListBlogContent(String category, String condition, String value) {
		/////////////////////////////
		return null;
	}

	@Override
	public BlogContentVO SeeBlogContent(String idx) {
		
		return null;
	}

	@Override
	public BlogContentVO NextBlogContent(String idx) {
		
		return null;
	}

	@Override
	public BlogContentVO NextBlogContent(String idx, String category) {
		
		return null;
	}

	@Override
	public BlogContentVO PreBlogContent(String idx) {
		
		return null;
	}

	@Override
	public BlogContentVO PreBlogContent(String idx, String category) {
		
		return null;
	}

	@Override
	public List<BlogContentVO> categoryContentList(RealBlogContentVO vo) {
		List<BlogContentVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BLOGCONTENT.selectCategoryContent",vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countCategoryList(String category) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("BLOGCONTENT.countCategoryList",category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public boolean updateCount(String idx) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.update("BLOGCONTENT.updateCount", idx);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<BlogCategoryVO> getMyCategory(String email) {
		List<BlogCategoryVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("BLOGCONTENT.getMyCategory", email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
