package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.po.Movie;

public interface WatchMovieMapper {
	//userid movie_id,rating
  public void saveRating(HashMap<String,Object> parameter);
  
  public int numsOfRatedMoviesByUserId(int userid);
  
  public List<Integer> selectHotMovies(HashMap<String,Object> parameter);
}
