<%@ page language="java" import="java.util.*,com.po.Movie" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'romance.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/Movie/css/homeRightStyle.css">
</head>
  <body>
   <%
	      out.println("<div class='div1'>");
	      ArrayList<Movie> moviesList=(ArrayList<Movie>)request.getAttribute("moviesList");
	      if(moviesList.size()>0){
		       for(int i=1;i<=3&&((i-1)*4<moviesList.size());i++){
		          out.println("<div>");
		          out.println("<ul class='faceul'>");
		          for(int j=0;j<4&&((i-1)*4+j<moviesList.size());j++){
			        String picurl=moviesList.get((i-1)*4+j).getMovie_picture_url();
			        String movie_name=moviesList.get((i-1)*4+j).getMovie_name();
				    out.println("<li><a href='http://yuanzhiyuan-pc:8080/Movie/getMovieDetail.action?movie_id="+moviesList.get((i-1)*4+j).getMovie_id()+
				    "'><img src='"+picurl+"'/><br/>"+movie_name+"</a></li>");
			      }
			     out.println("</ul>");
		         out.println("</div>");
		       }
	      }else{
	       out.println("对不起，无相关电影信息！");
	      }
	     out.println("</div>");
	     if(request.getAttribute("navigation")!=null){
	       out.println("<font color='red'><center>"+request.getAttribute("navigation")+"</center></font>");
	     }
     %>
  </body>
</html>
