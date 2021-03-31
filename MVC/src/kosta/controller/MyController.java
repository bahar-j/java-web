package kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.action.Action;
import kosta.action.ActionForward;
import kosta.action.InsertFormAction;
import kosta.action.InsertAction;
import kosta.action.DeleteAction;
import kosta.action.DetailAction;
import kosta.action.ListAction;
import kosta.action.UpdateFormAction;
import kosta.action.UpdateAction;


/**
 * Servlet implementation class MyController
 */
@WebServlet("/board/*")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath(); // /MVC
    	String command = requestURI.substring(contextPath.length()+7);
    	
    	Action action = null;
    	ActionForward forward = null;
    	System.out.println(command);
    	if (command.equals("insertForm.do")){
    		// 페이지 이동
    		action = new InsertFormAction();
    		try { // 이렇게 해두면 이 부분에 대한 에러 메세지가 나옴 -> 안그럼 전체에서 찾아야 함
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if (command.equals("insertAction.do")) {
    		// DB에 데이터 저장
    		//boardList.do로 이동 
    		action = new InsertAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} else if (command.equals("listAction.do")) {
    		action = new ListAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
    	} else if (command.equals("detailAction.do")) {
    		action = new DetailAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if (command.equals("updateFormAction.do")) {
    		action = new UpdateFormAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if (command.equals("updateAction.do")) {
    		action = new UpdateAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if (command.equals("deleteAction.do")) {
    		action = new DeleteAction();
    		
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	if (forward != null) {
    		if (forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		} else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
