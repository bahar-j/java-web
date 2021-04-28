package org.juneco.mapper;

import java.util.List;

import org.juneco.domain.BoardVO;
import org.juneco.domain.Criteria;

public interface BoardMapper {
	public List<BoardVO> getList();
	public List<BoardVO> getListWithPaging(Criteria cri);
	public void insert(BoardVO board);
	public Integer insertSelectKey(BoardVO board);
	public BoardVO read(Long bno);
	public int delete(Long bno);
	public int update(BoardVO board);
	public int getTotalCount(Criteria cri);
}