<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	글제목: ${board.title} <br>
	작성일자:  ${board.regdate} <br>
	작성자:  ${board.writer} <br>
	조회수:  ${board.hitcount} <br>
	내용: ${board.contents}
	
	<a href="listAction.do">글목록</a>
	<a href="updateFormAction.do?seq=${board.seq }">글수정</a>
	<a href="deleteAction.do?seq=${board.seq }">글삭제</a>
</body>
</html>