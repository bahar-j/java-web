package org.juneco.service;

import java.util.List;

import org.juneco.domain.BoardAttachVO;
import org.juneco.domain.BoardVO;
import org.juneco.domain.Criteria;
import org.juneco.mapper.BoardAttachMapper;
import org.juneco.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	BoardAttachMapper attachMapper;
	
	@Transactional
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		// attachList는 프론트에서 BoardVO 객체에 저장돼서 넘어옴
		// BoardAttachVO(uuid=~, uploadPath=2021/05/04, fileName=Main.txt, fileType=false, bno=null)
		// bno는 설정안돼있음
		board.getAttachList().forEach(attach -> {
			log.info(attach.getBno());
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		
		boolean modifyResult = mapper.update(board) == 1;
		attachMapper.deleteAll(board.getBno());
		
		if (modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}

	@Override
	public boolean remove(Long bno) {
		// 성공하면 1 반환
		attachMapper.deleteAll(bno);
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

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		// TODO Auto-generated method stub
		return attachMapper.findByBno(bno);
	}

}
