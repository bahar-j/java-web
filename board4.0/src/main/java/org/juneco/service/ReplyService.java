package org.juneco.service;

import java.util.List;

import org.juneco.domain.Criteria;
import org.juneco.domain.ReplyPageDTO;
import org.juneco.domain.ReplyVO;

public interface ReplyService {
	public int insert(ReplyVO vo);
	public ReplyVO read(Long bno);
	public int remove(Long bno);
	public int modify(ReplyVO reply);
	public List<ReplyVO> getList(Criteria cri, Long bno);
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
