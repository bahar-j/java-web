package kosta.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdk.management.resource.internal.TotalResourceContext;
import kosta.model.Board;
import kosta.model.BoardDao;
import kosta.model.ListModel;
import kosta.model.Search;

public class BoardService {
	private static BoardService service = new BoardService();
	private static BoardDao dao;
	private static final int POST_PER_PAGE = 3;
	private static final int PAGE_PER_PAGINATION = 5;
	
	
	public static BoardService getInstance() {
		dao = BoardDao.getInstance();
		return service;
	}
	
	public int insertBaordService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Board board = new Board();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContents(request.getParameter("contents"));
		
		return dao.insertBoard(board);
	}
	
	public ListModel listBoardService(HttpServletRequest request) throws Exception {
		// paging 처리 필요 사항
		// 페이지 당 글 갯수, 총 글 갯수, 총 페이지 수, 현재 페이지, startPage, endPage
		request.setCharacterEncoding("utf-8");
		Search search = new Search();
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		
		// 검색한 경우
		if(request.getParameterValues("area") != null) {
			session.removeAttribute("search");
			search.setArea(request.getParameterValues("area"));
			search.setSearchKey("%"+request.getParameter("searchKey")+"%");
			session.setAttribute("search", search);
		} else if (pageNum == null) {
			session.removeAttribute("search");
		}
		
		// 세션에 검색 정보가 있는 경우
		if (session.getAttribute("search") != null) {
			search = (Search)session.getAttribute("search");
		}
	
		int nPosts = dao.countBoard(search);
		int nPages = nPosts / POST_PER_PAGE;
		if (nPosts % POST_PER_PAGE > 0) {
			nPages++;
		}
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int requestPage = Integer.parseInt(pageNum);
		
		// startPage = 현재페이지 - ((현재페이지 - 1) % 몇 페이지씩 보여줄지)
		int startPage = requestPage - (requestPage - 1) % PAGE_PER_PAGINATION;
		int endPage = startPage + PAGE_PER_PAGINATION - 1;
		if (endPage > nPages) {
			endPage = nPages;
		}
		
		int startRow = POST_PER_PAGE * (requestPage - 1);
		int endRow = startRow + POST_PER_PAGE - 1;
		
		List<Board> list = dao.listBoard(search, startRow, POST_PER_PAGE);
		ListModel listModel = new ListModel(list, requestPage, nPages, startPage, endPage, PAGE_PER_PAGINATION);
		
		return listModel;
	}
	
	public Board detailBoardService(int seq) {
		Board board = dao.detailBoard(seq);
		return board;
	}
	
	public int updateBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Board board = new Board();
		board.setSeq(Integer.parseInt(request.getParameter("seq")));
		board.setTitle(request.getParameter("title"));
		board.setContents(request.getParameter("contents"));
		
		return dao.updateBoard(board);
	}
	
	public int deleteBoardService(int seq) {
		return dao.deleteBoard(seq);
	}
}
