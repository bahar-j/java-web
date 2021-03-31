package kosta.model;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kosta.mapper.BoardMapper;

public class BoardDao {
	private static BoardDao dao = new BoardDao();
	
	public static BoardDao getInstance() {
		return dao;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream in = null; // reader도 가능
		
		try {
			//mybatis-config.xml에 input stream을 연결
			in = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SqlSessionFactoryBuilder().build(in);
	}
	
	public int insertBoard(Board board) {
		int re = -1;
		
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			re = sqlSession.getMapper(BoardMapper.class).insertBoard(board);
			if (re > 0) {
				sqlSession.commit(); // mybatis만 쓰면 이렇게 트랜잭션 처리해줘야함
			} else {
				sqlSession.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return re;
	}
	
	public List<Board> listBoard(int startRow, int postPerPage){
		List<Board> list = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			list = sqlSession.getMapper(BoardMapper.class).listBoard(new RowBounds(startRow, postPerPage));
//			list = sqlSession.selectList("kosta.mapper.BoardMapper.listBoard");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return list;
	}
	
	public Board detailBoard(int seq) {
		Board board = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			board = sqlSession.selectOne("kosta.mapper.BoardMapper.detailBoard", seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return board;
	}
	
	public int updateBoard(Board board) {
		int re = -1;
		
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			re = sqlSession.update("kosta.mapper.BoardMapper.updateBoard", board);
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
	
	public int deleteBoard(int seq) {
		int re = -1;
		
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			re = sqlSession.delete("kosta.mapper.BoardMapper.deleteBoard", seq);
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
	
	public int countBoard() {
		int cnt = 0;
		
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			cnt = sqlSession.selectOne("kosta.mapper.BoardMapper.countBoard");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		
		return cnt;
	}
}
