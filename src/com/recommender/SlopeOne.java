package com.recommender;
import java.io.File;
import java.util.List;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import com.confg.Configs;


public class SlopeOne {
	//给用户（user_id）推荐recommend_movie_nums部电影
	/*
  public   List<RecommendedItem> getRecommenderMovies(int user_id,int recommend_movie_nums) throws Exception{
	    
	    File dataFile=new File(Configs.train_data_address);
		DataModel model=new FileDataModel(dataFile);
	 	Recommender oneRecommender=new SlopeOneRecommender(model);	
		List<RecommendedItem>  recommendMovieList=oneRecommender.recommend(user_id,recommend_movie_nums);
        return recommendMovieList;
		
  }*/
  


}
