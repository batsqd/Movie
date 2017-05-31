<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'movieDetail.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="/Movie/css/MovieDetailStyle.css">
	<link rel="stylesheet" type="text/css" href="/Movie/css/starstyle.css">
	<script type="text/javascript" src="/Movie/js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="/Movie/js/userRating.js"></script>
  </head>
  <body>
  <div class="div1">
 <div>
<img src="${sessionScope.movieDetail.movie_picture_url}"/><br/>
<div class="block">
<ul>
   <li>Do you like：</li>
</ul>
<ul class='star-rating'>
  <li><a name='http://yuanzhiyuan-pc:8080/Movie/saveRating.action?userid=${sessionScope.user.userid}&movie_id=${movieDetail.movie_id}&rating=1' title='一星' class='one-star'>1</a></li>
  <li><a name='http://yuanzhiyuan-pc:8080/Movie/saveRating.action?userid=${sessionScope.user.userid}&movie_id=${movieDetail.movie_id}&rating=2' title='二星' class='two-stars' >2</a></li>
  <li><a name='http://yuanzhiyuan-pc:8080/Movie/saveRating.action?userid=${sessionScope.user.userid}&movie_id=${movieDetail.movie_id}&rating=3' title='三星' class='three-stars'>3</a></li>
  <li><a name='http://yuanzhiyuan-pc:8080/Movie/saveRating.action?userid=${sessionScope.user.userid}&movie_id=${movieDetail.movie_id}&rating=4' title='四星' class='four-stars'>4</a></li>
  <li><a name='http://yuanzhiyuan-pc:8080/Movie/saveRating.action?userid=${sessionScope.user.userid}&movie_id=${movieDetail.movie_id}&rating=5' title='五星' class='five-stars'>5</a></li>


</ul>
 
  <ul>
     <font color='red'><li id="rating"></li></font>
  </ul>
 
</div>
 <ul>
   <li>Movie：${sessionScope.movieDetail.movie_name}</li>
   <li>Director：${sessionScope.movieDetail.movie_director }</li>
   <li>Actors：${sessionScope.movieDetail.movie_actor}</li>
   <li>Genre：${sessionScope.movieDetail.movie_genres}</li>
   <li>Language：${sessionScope.movieDetail.movie_language }</li>
   <li>Release date：${sessionScope.movieDetail.movie_release_date}</li>
   <li>District：${sessionScope.movieDetail.movie_district }</li>
   <li>Link：${sessionScope.movieDetail.movie_download_url}</li>
   <li>Playcount：${sessionScope.movieDetail.movie_playcount }</li>
   <li>Score：${sessionScope.movieDetail.movie_average_score }</li>
   <li>Synopsis：${sessionScope.movieDetail.movie_synopsis }</li>
  </ul>
 </div>
 </div>
</body>
</html>
