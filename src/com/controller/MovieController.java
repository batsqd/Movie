package com.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;






import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.confg.Configs;
import com.mapper.MovieMapper;
import com.mapper.RecommendMapper;
import com.mapper.WatchMovieMapper;
import com.po.Movie;
import com.po.RecommendedMovie;
import com.po.User;
import com.recommender.ImproveSlopeOne;
import com.recommender.SlopeOne;
import com.service.MovieInfoService;
import com.util.ApplicationContextUtil;

@Controller
public class MovieController {
	@RequestMapping("/queryMovies.action")
	public ModelAndView queryMovies(){
		
		  ArrayList<Movie> moviesList=new ArrayList<Movie>();
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		  Movie movie=movieMapper.selectMovieById(1);
		  moviesList.add(movie);
		 
		  ModelAndView modelAndView=new ModelAndView();
		  modelAndView.addObject("moviesList", moviesList);
		  modelAndView.setViewName("/WEB-INF/jsp/movie.jsp");
		  return modelAndView;
	}
	
	@RequestMapping("getMovieDetail.action")
	public ModelAndView getMovieDetail(int movie_id,HttpSession session){
		  ArrayList<Movie> moviesList=new ArrayList<Movie>();
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		  Movie movie=movieMapper.selectMovieById(movie_id);
		  ModelAndView modelAndView=new ModelAndView();
		  session.setAttribute("movieDetail", movie);
		  //modelAndView.addObject("movieDetail", movie);
		  modelAndView.setViewName("/WEB-INF/jsp/movieDetail.jsp");
		  return modelAndView;
		
	}
	
	@RequestMapping("getMovieDetailAndCollectFeedback.action")
	public ModelAndView getMovieDetailAndCollectionFeedback(int movie_id,HttpSession session){
		  
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		  Movie movie=movieMapper.selectMovieById(movie_id);
		  ModelAndView modelAndView=new ModelAndView();
		  session.setAttribute("movieDetail", movie);
		  //modelAndView.addObject("movieDetail", movie);
		  modelAndView.setViewName("/WEB-INF/jsp/userFeedback.jsp");
		  return modelAndView;
		
	}
	
	@RequestMapping("getHotMovies.action")
	public ModelAndView getHotMovies(){
		
		long l = System.currentTimeMillis();
		Date date = new Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String rating_time=dateFormat.format(date);
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		WatchMovieMapper watchMovieMapper=(WatchMovieMapper) ac.getBean("watchMovieMapper");
		HashMap<String,Object> parameter=new HashMap<String,Object>();
		parameter.put("rating_time", rating_time);
		parameter.put("hot_movie_nums", Configs.hot_movie_nums);
		ArrayList<Integer> movie_id_List=(ArrayList<Integer>) watchMovieMapper.selectHotMovies(parameter);
		MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		List<Movie> moviesList=new ArrayList<Movie>();
		for(int i=0;i<movie_id_List.size();i++){
			//此处拿到movie ID 去movie表查询List<movie>
			Movie movie=movieMapper.selectMovieById(movie_id_List.get(i));
			moviesList.add(movie);
		}
		ModelAndView modelAndView=new ModelAndView();
	    
		modelAndView.addObject("moviesList", moviesList);
		modelAndView.setViewName("/WEB-INF/jsp/hotMovies.jsp");
		return modelAndView;
	}
	
	/////////////////////////////////////////////////////
	@RequestMapping("getRecommendedMoviesByOurAlgorithm.action")
	public ModelAndView getRecommendedMoviesByOurAlgorithm(HttpSession session) throws Exception{
		
		User user=(User) session.getAttribute("user");
		int user_id=user.getUserid();
		int recommend_movie_num=Configs.recommend_movie_num;
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		WatchMovieMapper watchMovieMapper= (WatchMovieMapper) ac.getBean("watchMovieMapper");
		int numsOfRatedMoviesByUserId=watchMovieMapper.numsOfRatedMoviesByUserId(user_id);
		ModelAndView modelAndView=new ModelAndView();
		if(numsOfRatedMoviesByUserId<5){
			modelAndView.addObject("numsOfRatedMoviesByUserId", numsOfRatedMoviesByUserId);
			modelAndView.setViewName("/WEB-INF/jsp/guessYouLike.jsp");
			return modelAndView;
	    }else{
	    	
	    	//超过5人评价，才做预测
	    	ImproveSlopeOne improveSlopeOne=new ImproveSlopeOne();
			List<RecommendedMovie> recommendedMovieslist= improveSlopeOne.getPredictMovies(user_id, recommend_movie_num);
			//System.out.println("=========================================");
			//System.out.println("推荐电影的数量："+recommendedMovieslist.size());
			//System.out.println(recommendedMovieslist.toString());
			//System.out.println("==========================================");
			MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
			RecommendMapper recommendMapper=(RecommendMapper) ac.getBean("recommendMapper");
			ArrayList<Movie> moviesList=new ArrayList<Movie>();
			
			for(int i=0;i<recommendedMovieslist.size();i++){
				double movie_recommend_value=recommendedMovieslist.get(i).getRecommend_vale();
				int recommendedMovie_id=recommendedMovieslist.get(i).getMovie_id();
				
				  HashMap<String,Object> parameter=new HashMap<String,Object>();
				  parameter.put("movie_id", recommendedMovie_id);
				  parameter.put("user_id", user_id);
				  
				  parameter.put("recommend_value",movie_recommend_value);
				  //最好处理下异常
				  //只是保存user_id movie_id recommend_value timestamp
				  recommendMapper.saveRecommendedMovies(parameter);
				Movie movie=movieMapper.selectMovieById(recommendedMovie_id);
				if(movie!=null){
					moviesList.add(movie);
				}
			}
			
		    modelAndView.addObject("moviesList", moviesList);
			modelAndView.setViewName("/WEB-INF/jsp/guessYouLike.jsp");
			return modelAndView;
	    }
		
	}
	
	
	
	
	/////////////////////////////////////////////
	
	
	//该控制器是Slope One Api
	/*
	@RequestMapping("getRecommendMovies.action")
	public ModelAndView getRecommendMovies(HttpSession session) throws Exception{
		
		User user=(User) session.getAttribute("user");
		int user_id=user.getUserid();
		int recommend_movie_num=Configs.recommend_movie_num;
		List<RecommendedItem> moviesRecommendList=new SlopeOne().getRecommenderMovies(user_id, recommend_movie_num);
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		ArrayList<Movie> moviesList=new ArrayList<Movie>();
		for (RecommendedItem recommendedItem : moviesRecommendList) {
			int movie_id=(int) recommendedItem.getItemID();
			Movie movie=movieMapper.selectMovieById(movie_id);
			if(movie!=null){
				moviesList.add(movie);
			}
		}
		
		//System.out.println(moviesList.toString());
		ModelAndView modelAndView=new ModelAndView();
	    modelAndView.addObject("moviesList", moviesList);
		modelAndView.setViewName("/WEB-INF/jsp/guessYouLike.jsp");
		return modelAndView;
	}*/

}
