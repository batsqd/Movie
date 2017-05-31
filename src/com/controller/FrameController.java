package com.controller;
import java.util.ArrayList;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mapper.MovieMapper;
import com.po.Movie;
import com.service.MovieInfoService;
import com.util.ApplicationContextUtil;

@Controller
public class FrameController {
	
	 @RequestMapping("/top.action")
	   public ModelAndView showTop(){
		  ModelAndView modelAndView=new ModelAndView ();
		  modelAndView.setViewName("/WEB-INF/jsp/top.jsp");
		  return modelAndView;
		}
	 
	 @RequestMapping("/left.action")
	   public ModelAndView showLeft(){
		  ModelAndView modelAndView=new ModelAndView ();
		  modelAndView.setViewName("/WEB-INF/jsp/left.jsp");
		  return modelAndView;
		}
	 
	 @RequestMapping("/romance.action")
	   public ModelAndView showRomance(String type,String genres){//type指的是按照流派，按照发行时间，按照评分
		 
		 ModelAndView modelAndView=new ModelAndView ();
		  MovieInfoService movieInfoService=new MovieInfoService();
		  ArrayList<Movie> moviesList=new ArrayList<Movie>();
		  int pageCount=0;
		  if("byGenres".equals(type)){
			  moviesList=movieInfoService.getMoviesByGenresToPage(genres, 0, 12);
			  pageCount=movieInfoService.getPageCountByGenres(12, genres);
		  }else if("byReleaseTime".equals(type)){
			  //此处按发布时间指的是按照Romance类别中电影的发布时间顺序
			  moviesList=movieInfoService.getMoviesOrderByReleaseTimeToPage(genres, 0, 12);
			  pageCount=movieInfoService.getPageCountByGenres(12, genres);
		  }else if("byRating".equals(type)){
			  moviesList=movieInfoService.getMoviesOrderByRatingToPage(genres, 0, 12);
			  pageCount=movieInfoService.getPageCountByGenres(12, genres);
		  }
		 
		  if(pageCount>1){
			  //此时有下一页
		     modelAndView.addObject("navigation","<a href='http://yuanzhiyuan-pc:8080/Movie/getMoviesByPage.action?pageNow=1&type="+type+"&genres="+genres+"'>下一页</a>"+"&nbsp;&nbsp;共"+pageCount+"页！");
		  }
		  modelAndView.addObject("genres",genres);
		  modelAndView.addObject("moviesList", moviesList);
		  modelAndView.setViewName("/WEB-INF/jsp/romance.jsp");//genres=romance,所有的类型都可以调到同一个页面
		  return modelAndView;
		}
}
