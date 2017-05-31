package com.mapper;
import java.util.ArrayList;



import java.util.HashMap;

import com.po.Movie;

public interface MovieMapper {
	/*
	 * 1、	Mapper.xml文件中的namespace与mapper接口的类路径相同。
       2、	Mapper接口方法名和Mapper.xml中定义的每个statement的id相同 
       3、	Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
       4、	Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

	 */
	public Movie selectMovieById(int movie_id);
	
	public ArrayList<Movie> selectMoviesByGenres(String movie_genres);
	
	public ArrayList<Movie> selectMoviesByGenresAndLimit(HashMap<String,Object> parameter);
	
	public int selectCountMoviesByGenres(String movie_genres);
	
	public int selectCountMoviesByKeyWord(String movie_name);
	
	public ArrayList<Movie> selectMoviesByReleaseTimeLimit(HashMap<String,Object> parameter);
	
	public ArrayList<Movie> selectMoviesByRatingLimit(HashMap<String,Object> parameter);
	
	public ArrayList<Movie> selectMoviesBySearchKeyWordLimit(HashMap<String,Object> parameter);
	
	//4部热点电影
	public ArrayList<Movie> selectHotMovies(int nums);
}
