<%@ page language="java" import="java.util.*,com.po.Movie" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>Romance</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/Movie/css/homeRightStyle.css">
	<link rel="stylesheet" type="text/css" href="/Movie/css/hidden.css">
</head>
  <body>
  
 <div class="navigation_bar" >
  <a href='http://yuanzhiyuan-pc:8080/Movie/getRecommendedMoviesByOurAlgorithm.action'>Your Taste</a>
  <a href='http://yuanzhiyuan-pc:8080/Movie/getHotMovies.action'>hot movie</a>
  <a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byRating&genres=${genres}'>By Score</a>
  <a href='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byReleaseTime&genres=${genres}'>By Update</a> 
 </div>
  
  
    <%
	      out.println("<div class='div1'>");
	      ArrayList<Movie> moviesList=(ArrayList<Movie>)request.getAttribute("moviesList");
	      if(moviesList!=null){
	       for(int i=1;i<=3&&((i-1)*4<moviesList.size());i++){
	          out.println("<div>");
	          out.println("<ul class='faceul'>");
	          for(int j=0;j<4&&((i-1)*4+j<moviesList.size());j++){
		        String picurl=moviesList.get((i-1)*4+j).getMovie_picture_url();
		        String movie_name=moviesList.get((i-1)*4+j).getMovie_name();
		        //class="li1" onmouseover="this.className='li2'" onmouseout="this.className='li1'"
			    out.println("<li ><a href='http://yuanzhiyuan-pc:8080/Movie/getMovieDetail.action?movie_id="+moviesList.get((i-1)*4+j).getMovie_id()+
			    "'><img src='"+picurl+"'/><br/>"+movie_name+"</a></li>");//
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
	     out.println("<center>浏览量"+request.getSession().getAttribute("countVisitors")+"人次</center>");
     %>
  </body>
</html>
