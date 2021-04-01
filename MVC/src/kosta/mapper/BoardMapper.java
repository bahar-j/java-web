package kosta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import kosta.model.Board;
import kosta.model.Search;

public interface BoardMapper {
	int insertBoard(Board board); //추상메서드
	List<Board> listBoard(Search search, RowBounds rowBounds); // RowBound는 항상 마지막 인자로
	Board detailBoard(int seq);
	int updateBoard(Board board);
	int deleteBaord(int seq);
	int countBoard(Search search);
}
