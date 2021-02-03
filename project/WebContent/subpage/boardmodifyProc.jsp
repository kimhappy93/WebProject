<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.MovieBean"%>
<%
	String movie_num=request.getParameter("MOVIE_NUM");
	String id = request.getParameter("ID");
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=movie_num %></h1>
	<h1><%=id %></h1>
</body>
</html>