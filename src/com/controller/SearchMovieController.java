package com.controller;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.po.Movie;
import com.service.MovieInfoService;

@Controller
public class SearchMovieController {
	@RequestMapping("/showSearchContent.action")
	public ModelAndView showSearchContent(String keyWord) throws UnsupportedEncodingException{
		  //防止中文乱码
		  String decode_keyWord=URLDecoder.decode(keyWord, "UTF-8");
		  System.out.println("decode_keyWord"+decode_keyWord);
		  System.out.println("keyWord"+keyWord);
		  ModelAndView modelAndView=new ModelAndView ();
		  MovieInfoService movieInfoService=new MovieInfoService();
		  ArrayList<Movie> moviesList=new ArrayList<Movie>();
		  int pageCount=0;
		  if(decode_keyWord!=null){
		    	//search box提交的请求
			  
			  moviesList=movieInfoService.getMoviesBySearchKeyWordToPage(decode_keyWord, 0, 12);
			  pageCount=movieInfoService.getPageCountByKeyWord(12, decode_keyWord);
		  }
		  
		  if(pageCount>1){
			  //此时有下一页
		     modelAndView.addObject("navigation","<a href='http://yuanzhiyuan-pc:8080/Movie/getSearchMovieByPage.action?pageNow=1&keyWord="+URLEncoder.encode(URLEncoder.encode(decode_keyWord,"UTF-8"),"UTF-8")+"'>下一页</a>"+"&nbsp;&nbsp;共"+pageCount+"页！");
		  }
		  modelAndView.addObject("moviesList", moviesList);
		  modelAndView.setViewName("/WEB-INF/jsp/ShowSearchResult.jsp");
		  return modelAndView;
		
	}
	@RequestMapping("/getSearchMovieByPage.action")
	public ModelAndView getSearchMovieByPage(int pageNow,String keyWord) throws UnsupportedEncodingException{
		int pageSize=12;
	    int pageCount=1;
	    int begain=0;
	    int end=5;
	    begain=(pageNow)*pageSize;
	    end=begain+pageSize;
	    //防止中文乱码
	    String decode_keyWord=URLDecoder.decode(keyWord, "UTF-8");
	    
	    MovieInfoService movieInfoService=new MovieInfoService();
	    ArrayList<Movie> moviesList=movieInfoService.getMoviesBySearchKeyWordToPage(decode_keyWord,begain, end);
	    pageCount=movieInfoService.getPageCountByKeyWord(pageSize, decode_keyWord);
	    ModelAndView modelAndView=new ModelAndView ();
	    String navigationUrlPre="";
	    String navigationUrlNext="";
	    if(pageNow<1){
	    	//没有上一页；
	    	navigationUrlPre="";
	    }else{
	    	//有上一页
	    	navigationUrlPre="<a href='http://yuanzhiyuan-pc:8080/Movie/getSearchMovieByPage.action?pageNow="+(pageNow-1)+"&keyWord="+URLEncoder.encode(URLEncoder.encode(decode_keyWord,"UTF-8"),"UTF-8")+"'>上一页</a>";
	    	
	    }
	    
	    if(pageNow+1<pageCount){
	    	//有下一页
	    	navigationUrlNext=
	    	"<a href='http://yuanzhiyuan-pc:8080/Movie/getSearchMovieByPage.action?pageNow="+(pageNow+1)+"&keyWord="+URLEncoder.encode(URLEncoder.encode(decode_keyWord,"UTF-8"),"UTF-8")+"'>下一页</a>";
	    }else{
	    	//没有下一页
	    	navigationUrlNext="";
	    }
	   
	    modelAndView.addObject("moviesList", moviesList);
	    modelAndView.addObject("navigation","当前为第"+(pageNow+1)+"页&nbsp;&nbsp;"+navigationUrlPre+"  "+navigationUrlNext+" "+"&nbsp;&nbsp;共"+pageCount+"页!");
	    modelAndView.setViewName("/WEB-INF/jsp/ShowSearchResult.jsp");
		return modelAndView;
		
	}
   
}
