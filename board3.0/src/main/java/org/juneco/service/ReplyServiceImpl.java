package org.juneco.service;

import java.util.List;

import org.juneco.domain.Criteria;
import org.juneco.domain.ReplyVO;
import org.juneco.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
//	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
	@Override
	public int insert(ReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO read(Long bno) {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public int remove(Long bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno);
	}

	@Override
	public int modify(ReplyVO reply) {
		// TODO Auto-generated method stub
		return mapper.update(reply);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		return mapper.getListWithPaging(cri, bno);
	}

}
