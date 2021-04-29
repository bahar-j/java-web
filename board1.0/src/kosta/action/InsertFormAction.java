package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// insert_form.jsp로 이동
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/insert_form.jsp");
		return forward;
	}

}
