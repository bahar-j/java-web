package org.juneco.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.juneco.domain.BoardAttachVO;
import org.juneco.domain.BoardVO;
import org.juneco.domain.Criteria;
import org.juneco.domain.PageDTO;
import org.juneco.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	// 자동 DI
	private BoardService service;
	
	@GetMapping("/list")
	// return 타입이 void면 url이 jsp 이름 (board/list.jsp)
	public void list(Criteria cri, Model model) {
		log.info("list...." + cri);
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	}
	
	@GetMapping({"/get", "/modify"})
	// ModelAttribute: 받아옴과 동시에 jsp로 넘겨줄 수 있음	+ 변수명 지어줄 수 있음
	// ModelAttribute 안써도 객체는 맨 앞 글자를 소문자로 바꿔서 받을 수 있음
	public void read(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("read one...");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/register")
	public String registerBoard(BoardVO board, RedirectAttributes rttr) {
		log.info("register: " + board);
		
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/remove")
	public String registerBoard(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		if (service.remove(bno)) {
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "fail");
		}
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/modify")
	public String modifyBoard(@ModelAttribute("cri") Criteria cri, BoardVO board, RedirectAttributes rttr) {
		log.info("modify: " + board);
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "fail");
		}
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	public void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		attachList.forEach(attach -> {
			Path file = Paths.get("/Users/leejooeun/Spring/upload/" + attach.getUploadPath() +
			"/" +attach.getUuid() + "_" + attach.getFileName());
			
			try {
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbnail = Paths.get("/Users/leejooeun/Spring/upload/" + attach.getUploadPath() +
					"/s_" + attach.getUuid() + "_" + attach.getFileName());
					Files.deleteIfExists(thumbnail);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("delete file error" + e.getMessage());
			}
		});
	}
	
}
