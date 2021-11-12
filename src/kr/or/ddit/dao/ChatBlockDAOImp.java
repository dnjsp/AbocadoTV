package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.ChatBlockVO;

public class ChatBlockDAOImp implements IChatBlockDAO{
	private static ChatBlockDAOImp instance;
	public static ChatBlockDAOImp getInstance() {
		if(instance == null) instance = new ChatBlockDAOImp();
		return instance;
	}
	
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	
	public ChatBlockDAOImp() {
		factory = SqlSessionFactoryUtil.getInstance();
	}
	
	@Override
	public boolean InsertChatBlock(ChatBlockVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CheckChatBlock(String mail) {
		// TODO Auto-generated method stub
		return false;
	}

}
