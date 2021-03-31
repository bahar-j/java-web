package kosta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import kosta.model.Board;

public interface BoardMapper {
	int insertBoard(Board board); //추상메서드
	List<Board> listBoard(RowBounds rowBounds);
	Board detailBoard(int seq);
	int updateBoard(Board board);
	int deleteBaord(int seq);
	int countBoard();
}
