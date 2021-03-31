package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.service.BoardService;

public class InsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		        
		// 비즈니스 로직 호출
		service.insertBaordService(request);

		// 뷰를 선정
		forward.setRedirect(true);
		// list.jsp는 데이터를 가지고 있지 않으니까
		// 새로운 요청으로 데이터를 가지고 다시 가야함
		forward.setPath("listAction.do");
		
		return forward;
	}

}
