package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.BlogCommentVO;

public class BlogCommentDAOImp implements IBlogCommentDAO{

	private static BlogCommentDAOImp instance;
	public static BlogCommentDAOImp gatInstance() {
		if(instance == null) instance = new BlogCommentDAOImp();
		return instance;
	}
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	
	public BlogCommentDAOImp() {
		factory = SqlSessionFactoryUtil.getInstance();
	}
	
	@Override
	public boolean InsertComment(BlogCommentVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdateComment(BlogCommentVO vo) {
		
		return false;
	}

	@Override
	public List<BlogCommentVO> SelectListComment(String boardIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
