package org.juneco.controller;

import java.util.List;

import org.juneco.domain.Criteria;
import org.juneco.domain.ReplyPageDTO;
import org.juneco.domain.ReplyVO;
import org.juneco.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@RequestMapping("/replies/*")
@Log4j
public class ReplyController {
	
	ReplyService service;
	
	@PostMapping(value = "/new",
				consumes = "application/json",
				produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		int count = service.insert(vo);
		
		return count == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable int page, @PathVariable Long bno) {
		// TODO: 10이라는 페이징 기준이 백에도 있고 프론트에도 있어서 관리하기 어려움 -> 수정 필요
		ReplyPageDTO replyDto = service.getListPage(new Criteria(page, 10), bno);
		log.info(replyDto);
		return new ResponseEntity<ReplyPageDTO>(replyDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}",
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("read one...");
		return new ResponseEntity<ReplyVO>(service.read(rno), HttpStatus.OK);
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		return service.remove(rno) == 1? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value="/{rno}",
					consumes="application/json",
					produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		vo.setRno(rno);
		log.info("rno: " + rno);
		log.info("modify: " + vo);
		return service.modify(vo) == 1?
				new ResponseEntity<String>("success", HttpStatus.OK):
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
