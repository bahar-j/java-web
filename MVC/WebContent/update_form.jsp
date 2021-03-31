<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="updateAction.do" method="post">
		<input type="hidden" name="seq" value="${board.seq}">
		작성자: <input type="text" name="writer" value="${board.writer }"><br>
		제목: <input type="text" name="title" value="${board.title }"><br>
		내용 <br>
		<textarea rows="6" cols="70" name="contents">${board.contents }</textarea>
		<br>
		<input type="submit" value="완료">
	</form>
</body>
</html>