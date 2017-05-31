package com.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;

import com.mapper.MovieMapper;
import com.po.Movie;
import com.util.ApplicationContextUtil;

public class MovieInfoService {
	
	public ArrayList<Movie> getMoviesByGenresToPage(String movie_genres,int begain,int end){
		  
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		  HashMap<String,Object> parameter=new HashMap<String,Object>();
		  parameter.put("movie_genres", "%"+movie_genres+"%");//movie_genres
		  parameter.put("begain", begain);
		  parameter.put("end", end);
		  ArrayList<Movie> moviesList=movieMapper.selectMoviesByGenresAndLimit(parameter);
		  return moviesList;
	}
	public ArrayList<Movie> getMoviesBySearchKeyWordToPage(String keyWord,int begain,int end){
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		  HashMap<String,Object> parameter=new HashMap<String,Object>();
		 
		  parameter.put("keyWord", "%"+keyWord+"%");
		  parameter.put("begain", begain);
		  parameter.put("end", end);
		  ArrayList<Movie> moviesList=movieMapper.selectMoviesBySearchKeyWordLimit(parameter);
		  return moviesList;
		
	}
	public ArrayList<Movie> getMoviesOrderByRatingToPage(String movie_genres,int begain,int end){
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		  HashMap<String,Object> parameter=new HashMap<String,Object>();
		 
		  parameter.put("movie_genres", "%"+movie_genres+"%");
		  parameter.put("begain", begain);
		  parameter.put("end", end);
		  ArrayList<Movie> moviesList=movieMapper.selectMoviesByRatingLimit(parameter);
		  return moviesList;
		
	}
	public ArrayList<Movie> getMoviesOrderByReleaseTimeToPage(String movie_genres,int begain,int end){
		  
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		  HashMap<String,Object> parameter=new HashMap<String,Object>();
		 
		  parameter.put("movie_genres", "%"+movie_genres+"%");///////--
		  parameter.put("begain", begain);
		  parameter.put("end", end);
		  ArrayList<Movie> moviesList=movieMapper.selectMoviesByReleaseTimeLimit(parameter);
		  return moviesList;
	}
	
	
	public int getCountMoviesByGenres(String movie_genres){
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		int countMoviesByGenres=movieMapper.selectCountMoviesByGenres(movie_genres);
		return countMoviesByGenres;
	}
	
	public int getPageCountByGenres(int pageSize,String movie_genres){
		
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		int countMovies=movieMapper.selectCountMoviesByGenres("%"+movie_genres+"%");
		int pageCount=countMovies%12==0?countMovies/12:(countMovies/12)+1;
		return pageCount;
		
	}
	
   public int getPageCountByKeyWord(int pageSize,String keyWord){
		
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		int countMovies=movieMapper.selectCountMoviesByKeyWord("%"+keyWord+"%");
		int pageCount=countMovies%12==0?countMovies/12:(countMovies/12)+1;
		return pageCount;
		
	}
}
