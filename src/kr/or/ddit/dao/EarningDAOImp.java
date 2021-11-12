package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.EarningVO;

public class EarningDAOImp implements IEarningDAO {
	private static EarningDAOImp instance;
	public static EarningDAOImp getInstance() {
		if(instance == null) instance = new EarningDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public EarningDAOImp() {
	}
	
	@Override
	public int insertEarning(EarningVO evo) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession(); 
			int success = sqlSession.insert("EARNING.insertEarning", evo);
			sqlSession.commit();
			if(success > 0) {
				cnt = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
