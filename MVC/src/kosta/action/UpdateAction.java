package kosta.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.service.BoardService;

public class UpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		service.updateBoardService(request);
		
		forward.setRedirect(false); // 원래 true로 하고 글 번호 파라미터 넘겨줘야 함 
		forward.setPath("detailAction.do");
		
		return forward;
	}

}
