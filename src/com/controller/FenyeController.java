package com.controller;

import java.util.ArrayList;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.po.Movie;
import com.service.MovieInfoService;



@Controller
public class FenyeController {
	@RequestMapping("/getMoviesByPage.action")
	public ModelAndView getMoviesByPage(int pageNow,String type,String genres){
		//pageNow是指用户从所看到的页面的那页
		int pageSize=12;
	    int pageCount=1;
	    int begain=0;
	    int end=5;
	    begain=(pageNow)*pageSize;
	    end=begain+pageSize;
	    ArrayList<Movie> moviesList=new ArrayList<Movie>();
	    MovieInfoService movieInfoService=new MovieInfoService();
	    
	    if("byGenres".equals(type)){
	    	moviesList=movieInfoService.getMoviesByGenresToPage(genres, begain, end);
	    	pageCount=movieInfoService.getPageCountByGenres(pageSize, genres);
	    }else if("byReleaseTime".equals(type)){
	    	moviesList=movieInfoService.getMoviesOrderByReleaseTimeToPage(genres, begain, end);
	    	pageCount=movieInfoService.getPageCountByGenres(pageSize, genres);
	    }else if("byRating".equals(type)){
	    	moviesList=movieInfoService.getMoviesOrderByRatingToPage(genres, begain, end);
	    	pageCount=movieInfoService.getPageCountByGenres(pageSize, genres);
	    }
	    
	    
	    
	    ModelAndView modelAndView=new ModelAndView ();
	    String navigationUrlPre="";
	    String navigationUrlNext="";
	    if(pageNow<1){
	    	//没有上一页；
	    	navigationUrlPre="";
	    }else{
	    	//有上一页
	    	/*http://121.42.174.147:8080
	    	navigationUrlPre="<a href='http://yuanzhiyuan-pc:8080/Movie/getMoviesByPage.action?pageNow="+(pageNow-1)+"&type="+type+"&genres="+genres+"'>上一页</a>";
	        */
	    	navigationUrlPre="<a href='http://yuanzhiyuan-pc:8080/Movie/getMoviesByPage.action?pageNow="+(pageNow-1)+"&type="+type+"&genres="+genres+"'>上一页</a>";
	    	
	    }
	    
	    if(pageNow+1<pageCount){
	    	//有下一页
	    	
	    	navigationUrlNext=
	    	"<a href='http://yuanzhiyuan-pc:8080/Movie/getMoviesByPage.action?pageNow="+(pageNow+1)+"&type="+type+"&genres="+genres+"'>下一页</a>";
	    }else{
	    	//没有下一页
	    	navigationUrlNext="";
	    }
	    modelAndView.addObject("genres",genres);
	    modelAndView.addObject("moviesList", moviesList);
	    modelAndView.addObject("navigation","当前为第"+(pageNow+1)+"页&nbsp;&nbsp;"+navigationUrlPre+"  "+navigationUrlNext+" "+"&nbsp;&nbsp;共"+pageCount+"页!");
	    modelAndView.setViewName("/WEB-INF/jsp/romance.jsp");
		return modelAndView;
		
	}
}
