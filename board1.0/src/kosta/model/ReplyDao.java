package kosta.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kosta.mapper.ReplyMapper;

public class ReplyDao {
	private static ReplyDao dao = new ReplyDao();
	
	public static ReplyDao getInstance() {
		return dao;
	}
	
	public SqlSession getSqlSession() {
		String resource = "mybatis-config.xml";
		InputStream stream = null;
		
		try {
			stream = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SqlSessionFactoryBuilder().build(stream).openSession();
	}
	
	public int insertReply(Reply reply) {
		int re = -1;
		
		SqlSession sqlSession = getSqlSession();
		try {
			re = sqlSession.getMapper(ReplyMapper.class).insertReply(reply);
			if (re > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		
		return re;
	}
	
	public List<Reply> selectReplies(int seq){
		List<Reply> replies = null;
		SqlSession sqlSession = getSqlSession();
		try {
			replies = sqlSession.getMapper(ReplyMapper.class).selectReplies(seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return replies;
	}
	
}
