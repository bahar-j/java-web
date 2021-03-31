package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.service.BoardService;

public class UpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		Board board = service.detailBoardService(Integer.parseInt(request.getParameter("seq")));
		
		request.setAttribute("board", board);
		
		forward.setRedirect(false);
		forward.setPath("/update_form.jsp");
		
		return forward;
	}

}
