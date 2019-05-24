package com.seed.utils;

import java.io.Reader;
import java.sql.PreparedStatement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtils {

	private static SqlSessionFactory factory;
	private static SqlSession sqlSession = null;

	public static SqlSessionFactory getInstance() {
		if (factory == null) {
			synchronized (DBUtils.class) {
				if (factory == null) {
					try {
						Reader reader = Resources.getResourceAsReader(PropertiesUtils.pps.getProperty(Consts.CONFIG_DATABASE_FILE));
						factory = new SqlSessionFactoryBuilder().build(reader, PropertiesUtils.pps.getProperty(Consts.CONFIG_DATABASE_ENV));
						reader.close();
						SqlSession session = factory.openSession();
						PreparedStatement ps = session.getConnection().prepareStatement("select 1");
						ps.execute();
						ps.close();
						session.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return factory;
	}

	public static SqlSession getFactory() {
		return factory.openSession();
	}
	
	public static SqlSession getSession() {
		return factory.openSession();
	}

	public static <T> T getMapper(SqlSession session, Class<T> mapper) {
		return (T) session.getMapper(mapper);
	}

	public static <T> T getMapper(Class<T> mapper) {
		SqlSession session = getSession();
		
		return (T) session.getMapper(mapper);
	}

	public static void commit() {
		sqlSession.commit();
	}

	public static void rollback() {
		sqlSession.rollback();
	}

	public static void close() {
		if (sqlSession != null) {
			sqlSession.close();
			sqlSession = null;
		}
	}

	public static void clearCache() {
		if (sqlSession != null)
			sqlSession.clearCache();
	}
}
