package org.juneco.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.juneco.domain.Criteria;
import org.juneco.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	private Long[] bnoArr = {123L, 124L, 125L, 126L, 127L};
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
//	
//	@Test
//	public void testCreate() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[i%5]);
//			vo.setReply("댓글 테스트 " + i);
//			vo.setReplyer("replyer" + i);
//			
//			mapper.insert(vo);
//			
//		});
//	}
//	
//	@Test
//	public void testRead() {
//		ReplyVO vo = mapper.read(1L);
//		log.info(vo);
//	}
//	
//	@Test
//	public void testDelete() {
//		mapper.delete(1L);
//	}
//
//	@Test
//	public void testUpdate() {
//		int count = -1;
//		ReplyVO vo = mapper.read(2L);
//		if (vo != null) {
//			vo.setReply("updated");
//			count = mapper.update(vo);
//		}
//		log.info("update count : " + count);
//	}
//	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
}
