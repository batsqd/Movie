<%@ page language="java" import="java.util.*,com.po.Movie" pageEncoding="utf-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>猜你喜欢</title>
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
	      Integer numsOfRatedMoviesByUserId=(Integer)request.getAttribute("numsOfRatedMoviesByUserId");
	      if(numsOfRatedMoviesByUserId!=null&&numsOfRatedMoviesByUserId<5){
	         out.println("评价5部电影及以上，推荐系统将推荐您可能喜欢的电影！");
	      }else{
		      ArrayList<Movie> moviesList=(ArrayList<Movie>)request.getAttribute("moviesList");
		      if(moviesList!=null&&moviesList.size()>0){
		     
		       for(int i=1;i<=3&&((i-1)*4<moviesList.size());i++){
		          out.println("<div>");
		          out.println("<ul class='faceul'>");
		          for(int j=0;j<4&&((i-1)*4+j<moviesList.size());j++){
			        String picurl=moviesList.get((i-1)*4+j).getMovie_picture_url();
			        String movie_name=moviesList.get((i-1)*4+j).getMovie_name();
				    out.println("<li><a href='http://yuanzhiyuan-pc:8080/Movie/getMovieDetailAndCollectFeedback.action?movie_id="+moviesList.get((i-1)*4+j).getMovie_id()+
				    "'><img src='"+picurl+"'/><br/>"+movie_name+"</a></li>");
			      }
			     out.println("</ul>");
		         out.println("</div>");
		       }
		       out.println("<font color='red' size=3>请点击推荐给您的电影进行评价，您的真实评价用来评估推荐系统的精度，这对于我们的研究和学习极其重要！谢谢亲！</font><font size=3 color='green'>(袁志远)</font>");
		     
		     }else{
		       out.println("对不起，您的历史电影评价数据太少，无法为您个性化推荐,如果您确实评价了5部及以上电影，那么刷新尝试！");
		     }
		     out.println("</div>");
		     if(request.getAttribute("navigation")!=null){
		       out.println("<font color='red'><center>"+request.getAttribute("navigation")+"</center></font>");
		       
		     }
		     out.println("<center>浏览量"+request.getSession().getAttribute("countVisitors")+"人次</center>");
	     }
	      
     %>
  </body>
</html>
