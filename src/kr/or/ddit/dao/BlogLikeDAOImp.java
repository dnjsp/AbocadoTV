package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.BlogLikeVO;

public class BlogLikeDAOImp implements IBlogLikeDAO{
	private static BlogLikeDAOImp instance;
	public static BlogLikeDAOImp getInstance() {
		if(instance == null) instance = new BlogLikeDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public BlogLikeDAOImp() {
	}
	
	@Override
	public boolean InsertBlogLike(BlogLikeVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.insert("BLOGLIKE.insertBlogLike", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteBlogLike(BlogLikeVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("BLOGLIKE.deleteBlogLike", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean CheckBlogLike(String mail) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			boolean success = sqlSession.selectOne("BLOGLIKE.checkBlogLike", mail);
			if(success == true) return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public int CountBlogLike(String idx) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("BLOGLIKE.countBlogLike", idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
