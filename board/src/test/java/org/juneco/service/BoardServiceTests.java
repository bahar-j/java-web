package org.juneco.service;

import static org.junit.Assert.assertNotNull;

import org.juneco.domain.BoardVO;
import org.juneco.domain.Criteria;
import org.juneco.mapper.BoardMapperTests;
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
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
//	@Test
//	public void testExists() {
//		log.info(service);
//		assertNotNull(service);
//	}
//	
	@Test
	public void testGetList() {
		service.getList(new Criteria(3, 3)).forEach(board -> log.info(board));
	}
//	
//	@Test
//	public void testRemove() {
//		log.info("remove result: " + service.remove(2L));
//	}
//	
//	@Test
//	public void testRead() {
//		log.info(service.get(3L));
//	}
//	
//	@Test
//	public void testModify() {
//		
//		BoardVO board = service.get(1L);
//
//		if (board == null) {
//			return;
//		}
//		
//		board.setTitle("new title");
//		log.info("update result: " + service.modify(board));
//	}
//
//	@Test
//	public void testRegister() {
//		BoardVO board = new BoardVO();
//		board.setTitle("new post");
//		board.setContent("new content");
//		board.setWriter("newbie");
//		
//		service.register(board);
//		
//		log.info("new post num: " + board.getBno());
//	}
	
}
