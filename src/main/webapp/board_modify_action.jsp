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
// Board 객체 만들고 사용자가 입력한 데이터 저장
Board board = new Board();
board.setBoardNo(Integer.parseInt(request.getParameter("boardno")));
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

// 변경된 내용 적용
/*
BoardService.getInstance().update(board);
*/

BoardService boardService = new BoardService();
boardService.update(board);

String pageno = "1";
if(request.getParameter("pageno") != null){
	pageno = request.getParameter("pageno");
}

response.sendRedirect("board_view.jsp?boardno="+board.getBoardNo()+"&pageno="+pageno);
%>