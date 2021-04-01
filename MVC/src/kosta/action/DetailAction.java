package kosta.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.Reply;
import kosta.service.BoardService;
import kosta.service.ReplyService;

public class DetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		ReplyService replyService = ReplyService.getInstance();

		Board board = service.detailBoardService(Integer.parseInt(request.getParameter("seq")));
		
		List<Reply> replies = replyService.selectReplyService(Integer.parseInt(request.getParameter("seq")));
		
		request.setAttribute("board", board);
		request.setAttribute("replies", replies);
		
		forward.setRedirect(false);
		forward.setPath("/detail.jsp");
		
		return forward;
	}

}
