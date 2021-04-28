package org.juneco.mapper;

import java.util.List;

import org.juneco.domain.BoardVO;
import org.juneco.domain.Criteria;
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
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
//	
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setTitle("insertTest2");
//		board.setContent("insertContentTest2");
//		board.setWriter("newbie");
//		mapper.insert(board);
//		mapper.insertSelectKey(board);
//		
//		log.info(board);
//	}
//	
//	@Test
//	public void testRead() {
//		mapper.read((long)1);
//	}
//	
//	@Test
//	public void testDelete() {
//		// 있다면 삭제 후 1 리턴 없다면 0리턴 		
//		mapper.delete(1L);
//	}
//	
//	@Test
//	public void testUpdate() {
//		BoardVO board = new BoardVO();
//		board.setTitle("updateTest");
//		board.setContent("updateContentTest");
//		board.setWriter("newbie2");
//		board.setBno(2L);
//		mapper.update(board);
//	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(3);
		cri.setAmount(10);
		cri.setKeyword("new");
		cri.setType("T");
		List<BoardVO> boards = mapper.getListWithPaging(cri);
		boards.forEach(board -> log.info(board));
	}
	
}
