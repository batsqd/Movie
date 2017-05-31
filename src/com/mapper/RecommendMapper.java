package com.mapper;

import java.util.HashMap;

public interface RecommendMapper {
	  //userid movie_id,rating
	  public void saveUserFeedbackRating(HashMap<String,Object> parameter);
	  
	  public void saveRecommendedMovies(HashMap<String,Object> parameter);
}
