<%@page import="kosta.model.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="insertForm.do">글쓰기</a>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>작성일자</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${listModel.list}"> <!-- el 태그 안이라서 그냥 .으로 접근 -->
		<tr>
			<td>${board.seq}</td>
			<td>${board.title}</td>
			<td><a href="detailAction.do?seq=${board.seq}">${board.writer}</a></td>
			<td>
				<fmt:parseDate var="dt" value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${dt}" pattern="yyyy/MM/dd"/>
			</td>
			<td>${board.hitcount}</td>
		</tr>
		</c:forEach>
		<%/* } */ %>
	</table>
	<br><br>
	
	<c:if test="${listModel.startPage > 5 }">
		<a href="listAction.do?pageNum=${listModel.startPage-1 }">[이전]</a>
	</c:if>
	
	<c:forEach var="pageNo" begin="${listModel.startPage }" end="${listModel.endPage }">
		<c:if test="${listModel.requestPage == pageNo}"><b></c:if>
		<a href="listAction.do?pageNum=${pageNo }">[${pageNo }]</a>
		<c:if test="${listModel.requestPage == pageNo}"></b></c:if>
	</c:forEach>
	
	<c:if test="${listModel.endPage < listModel.nPages }">
		<a href="listAction.do?pageNum=${listModel.endPage+1 }">[이후]</a>
	</c:if>
	
	<form action="list.jsp" method="post">
		<input type = "checkbox" name = "area" value = "title"> 제목
		<input type = "checkbox" name = "area" value = "writer"> 작성자
		<input type = "text" name = "searchKey" size = "10">
		<input type = "submit" value = "검색">
	</form>
</body>
</html>