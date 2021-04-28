package org.juneco.service;

import java.util.List;

import org.juneco.domain.BoardVO;
import org.juneco.domain.Criteria;
import org.juneco.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
public class BoardServiceImple implements BoardService {

	@Setter(onMethod_ = @Autowired)
	BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		// 성공하면 1 반환
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
//		return mapper.getList();
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

}
