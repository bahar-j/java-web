package kosta.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kosta.model.Reply;
import kosta.model.ReplyDao;

public class ReplyService {
	private static ReplyService service = new ReplyService();
	private static ReplyDao dao;
	
	public static ReplyService getInstance() {
		dao = ReplyDao.getInstance();
		return service;
	}
	
	public int insertReplyService(HttpServletRequest request) throws Exception {
		Reply reply = new Reply();
		
		reply.setR_title(request.getParameter("r_title"));
		reply.setR_writer(request.getParameter("r_writer"));
		reply.setR_contents(request.getParameter("r_contents"));
		reply.setSeq(request.getParameter("seq"));
		
		return dao.insertReply(reply);
	}
	
	public List<Reply> selectReplyService(int seq){
		return dao.selectReplies(seq);
	}
}
