<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>111</title>
   <link rel="stylesheet" href="/Movie/css/stylehomeleft.css" media="screen" type="text/css" />
   <link rel="stylesheet" href="/Movie/css/SearchBoxStyle.css" media="screen" type="text/css" />
   <script type="text/javascript" src="/Movie/js/jquery-1.11.1.js"></script>
   <script type="text/javascript" src="/Movie/js/searchBox.js"></script>
</head>
<body>
<div style="text-align:center;clear:both">

</div>
<nav>
  <ul>
    <li class="store"><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=romance' target="right">Romance</a></li>
    <li class="movies"><span class="movies-icon"></span><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=documentary' target="right">Documentary</a></li>
    <li class="music"><span class="movies-icon"></span><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=animation' target="right">Animation</a></li>
    <li class="books"><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=comedy' target="right">Comedy</a></li>
    <li class="magazines"><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=crime' target="right">Crime</a></li>
    <li class="fantasy"><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=fantasy' target="right">Fantasy</a></li>
	<li class="fantasy"><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=horror' target="right">Horror</a></li>
	<li class="fantasy"><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=action' target="right">Action</a></li>
	<li class="fantasy"><a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=war' target="right">War</a></li>
</ul>
</nav>
<div id="search_box"> 
	
		<input type="text" id="s"  value="" placeholder="Search Movie" class="swap_value" /> 
		<a id="myhref" href="" target="right">
		  <input type="image" src="image/searchBox/btn_search_box.gif" width="27" height="24" id="go" alt="Search" title="Search"/> 
		</a>
		
</div> 
</body>
</html>