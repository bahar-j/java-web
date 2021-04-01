package kosta.mapper;

import java.util.List;

import kosta.model.Reply;

public interface ReplyMapper {
	int insertReply(Reply reply);
	List<Reply> selectReplies(int seq);
}
