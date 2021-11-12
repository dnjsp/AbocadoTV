package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.SqlSessionFactoryUtil;
import kr.or.ddit.vo.TagVO;

public class TagDAOImp implements ITagDAO{

	private static TagDAOImp instance;
	public static TagDAOImp getInstance() {
		if(instance == null) instance = new TagDAOImp();
		return instance;
	}
	
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	
	public TagDAOImp() {
		factory = SqlSessionFactoryUtil.getInstance();
	}
	
	@Override
	public String InsertTag(TagVO vo) {
		String success = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			sqlSession.insert("TAG.insertTag", vo);
			success=vo.getTag_index();
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean DeleteTag(String idx) {
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			int success = sqlSession.delete("TAG.deleteTag", idx);
			sqlSession.commit();
			if(success == 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<TagVO> SelectListTag(String mail) {
		List<TagVO> list = null;
		try {
			sqlSession = SqlSessionFactoryUtil.getSqlSession();
			list = sqlSession.selectList("TAG.selectListTag", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
