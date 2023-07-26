<%@page import="com.itwill.board.BoardService"%>
<%@page import="com.itwill.board.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf" %>
<%
if(request.getMethod().equalsIgnoreCase("get")){
	response.sendRedirect("board_list.jsp");
	return;
}

Board board = new Board();
board.setTitle(request.getParameter("title"));
board.setUserId(sUserId);

//비밀글 체크 시 - T, 체크 안 할 시 - F
String secret = request.getParameter("secret");
if(secret == null){
	secret = "F";
	board.setSecret(secret);
}else{
	board.setSecret(secret);
}


board.setContent(request.getParameter("content"));

BoardService boardService = new BoardService();
boardService.create(board);

response.sendRedirect("board_list.jsp");


%>