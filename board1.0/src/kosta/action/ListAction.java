package kosta.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.ListModel;
import kosta.service.BoardService;

public class ListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		ListModel listModel = service.listBoardService(request);
		
		request.setAttribute("listModel", listModel);
		
		forward.setRedirect(false);
		forward.setPath("/list.jsp");
		
		return forward;
	}

}
