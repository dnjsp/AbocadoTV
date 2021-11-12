package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.StreamingLikeVO;

public class StreamingLikeDAOImp implements IStreamingLikeDAO{
	private static StreamingLikeDAOImp instance;
	public static StreamingLikeDAOImp getInstance() {
		if(instance == null) instance = new StreamingLikeDAOImp();
		return instance;
	}
	
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	
	public StreamingLikeDAOImp() {
		factory = SqlSessionFactoryUtil.getInstance();
	}
	

	@Override
	public boolean InsertStreamingLike(StreamingLikeVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteStreamingLike(StreamingLikeVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CheckStreamingLike(String mail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int CountStreamingLike(String idx) {
		// TODO Auto-generated method stub
		return 0;
	}

}
