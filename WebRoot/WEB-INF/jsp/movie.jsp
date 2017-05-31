<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'movie.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
     电影列表：
<table width="100%" border=1>
<tr>
	<td>电影ID</td>
	<td>电影名称</td>
	<td>导演</td>
	<td>演员</td>
	<td>电影类型</td>>
	
</tr>
<c:forEach items="${moviesList}" var="movie">
<tr>
	<td>${movie.id }</td>
	<td>${movie.movie_name}</td>
	<td>${movie.director }</td>
	<td>${movie.actor}</td>
	<td>${movie.type}</td>
</tr>
</c:forEach>

</table>
  </body>
</html>
