package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.SeedVO;

public class SeedDAOImp implements ISeedDAO{
	private static SeedDAOImp instance;
	public static SeedDAOImp getInstnace() {
		if(instance == null) instance = new SeedDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public SeedDAOImp() {
	}
	
	@Override
	public boolean InsertSeed(SeedVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.insert("SEED.insertSeed", vo);
			sqlSession.commit();
			if (success != 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int SumSales(String mail) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("SEED.selectSumSeed", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<SeedVO> SelectSeed(String mail) {
		List<SeedVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("SEED.chargedSeedList", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
