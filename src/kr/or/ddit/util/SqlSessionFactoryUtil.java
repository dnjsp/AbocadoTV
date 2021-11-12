package kr.or.ddit.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession sqlSession;
	
	public static SqlSessionFactory getInstance() {
		if(sqlSessionFactory==null) {
			try {
				Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch (Exception e) {
				System.out.println("SqlMapConfig 오류 : " + e);
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession getSqlSession() {
		if(sqlSessionFactory==null) sqlSessionFactory = SqlSessionFactoryUtil.getInstance();
		if(sqlSession==null) sqlSession=sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public static void endSession(SqlSession sqlSession) {
		sqlSession.commit();
		sqlSession.close();
	}
}
