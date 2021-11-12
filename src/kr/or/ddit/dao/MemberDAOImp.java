package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImp implements IMemberDAO {
	private static MemberDAOImp instance;
	public static MemberDAOImp getInstance() {
		if(instance == null) instance = new MemberDAOImp();
		return instance;
	}
	
	private SqlSession sqlSession;
	
	public MemberDAOImp() {
	}
	
	@Override
	public boolean CheckMember(String mail) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int check = sqlSession.selectOne("MEMBER.checkMember",mail);
			if(check==0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean InsertMember(MemberVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.insert("MEMBER.insertMember", vo);
			sqlSession.commit();
			if(success!=0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MemberVO> SelectListMember(Map<String,String> map) {
		List<MemberVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("MEMBER.memberRank",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<MemberVO> SelectSearchListMember(Map<String,String> map) {
		List<MemberVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("MEMBER.memberSearchRank",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int MemberCount() {
		int count = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			count = sqlSession.selectOne("MEMBER.countMember");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public int MemberCountSearch(String mail) {
		int count = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			count = sqlSession.selectOne("MEMBER.countSearchMember",mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public MemberVO SelectMember(String mail) {
		MemberVO vo = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			vo = sqlSession.selectOne("MEMBER.selectMember", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean LoginMember(MemberVO vo) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			boolean check = sqlSession.selectOne("MEMBER.loginMember", vo);
			if (check) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateMember(Map<String,String> map) {
		int cnt = 0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.update("MEMBER.update", map);
			sqlSession.commit();
			if(cnt>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean UpdateMemberState(Map<String, String> map) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.update("MEMBER.updateMemberState", map);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int CheckFreezedate(String mail) {
		int cnt =0;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			cnt = sqlSession.selectOne("MEMBER.checkFreezedate", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<MemberVO> SelectListMember() {
		List<MemberVO> list = null;
		try {	
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("MEMBER.memberList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
