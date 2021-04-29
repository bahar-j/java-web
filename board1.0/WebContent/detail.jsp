<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>글 세부 보기</h3>
	<ul>
		<li>글제목: ${board.title}</li>
		<li>작성일자: ${board.regdate}</li>
		<li>작성자:  ${board.writer}</li>
		<li>조회수:  ${board.hitcount}</li>
		<li>내용: ${board.contents}</li>
		<li>파일: <a href="/MVC/download.jsp?filename=${board.fname }">${board.fname}</a></li>
	</ul><br><br>
	
	<div>
		<h3>댓글 목록</h3>
		<table border="1">
			<tr>
				<td>댓글 제목</td>
				<td>댓글 작성자</td>
				<td>댓글 내용</td>
				<td>댓글 날짜</td>
			</tr>
			<c:forEach var="reply" items="${replies }">
				<tr>
					<td>${reply.r_title }</td>
					<td>${reply.r_writer }</td>
					<td>${reply.r_contents }</td>
					<td>${reply.r_regdate }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<form action="insertReplyAction.do" method="post">
		<input type="hidden" name="seq" value="${board.seq }">
		댓글 제목: <input type="text" name="r_title"><br>
		댓글 작성자: <input type="text" name="r_writer"><br>
		댓글 내용: <input type="text" name="r_contents"><br>
		<input type="submit" value="댓글쓰기">
	</form>
	
	<a href="listAction.do">글목록</a>
	<a href="updateFormAction.do?seq=${board.seq }">글수정</a>
	<a href="deleteAction.do?seq=${board.seq }">글삭제</a>
</body>
</html>