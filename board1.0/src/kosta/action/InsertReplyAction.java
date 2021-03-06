package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.service.ReplyService;

public class InsertReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ReplyService service = ReplyService.getInstance();
		ActionForward forward = new ActionForward();
		
		service.insertReplyService(request);
		
		forward.setRedirect(true);
		forward.setPath("detailAction.do?seq=" + request.getParameter("seq"));
		
		return forward;
	}

}
