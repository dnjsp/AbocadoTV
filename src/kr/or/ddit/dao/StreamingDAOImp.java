package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.StreamingVO;

public class StreamingDAOImp implements IStreamingDAO{
	private static StreamingDAOImp instance;
	public static StreamingDAOImp getInstance() {
		if(instance == null) instance = new StreamingDAOImp();
		return instance;
	}
	
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	
	public StreamingDAOImp() {
		factory = SqlSessionFactoryUtil.getInstance();
	}
	
	@Override
	public boolean InsertStreaming(StreamingVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.insert("STREAMING.insertStreaming", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<StreamingVO> SelectListStreaming() {
		List<StreamingVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("STREAMING.selectListStreaming");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean UpdateStreaming(StreamingVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.update("STREAMING.updateStreaming", vo);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
