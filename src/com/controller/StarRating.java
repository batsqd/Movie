package com.controller;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mapper.RecommendMapper;
import com.mapper.WatchMovieMapper;
import com.po.Message;
import com.util.ApplicationContextUtil;

@Controller
public class StarRating {
	
	private static Logger logger_rating = Logger.getLogger("logger_rating");
	
	@RequestMapping("/saveRating.action")
	public @ResponseBody Message saveRating(int userid,int movie_id,int rating){
		  //日志处理
		  logger_rating.info(userid+"	"+movie_id+"	"+rating);
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  WatchMovieMapper watchMovieMapper=(WatchMovieMapper) ac.getBean("watchMovieMapper");
		  HashMap<String,Object> parameter=new HashMap<String,Object>();
		  parameter.put("userid", userid);
		  parameter.put("movie_id", movie_id);
		  parameter.put("rating", rating);
		  //得到long类型当前时间
		  long l = System.currentTimeMillis();
		  //new日期对象
		  Date date = new Date(l);
		  //转换提日期输出格式
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  System.out.println(dateFormat.format(date));
		  parameter.put("rating_time", date);
		  
		  parameter.put("comment", "");
		  watchMovieMapper.saveRating(parameter);
		  /*
		  ModelAndView modelAndView=new ModelAndView();
		  
		  try{
		     watchMovieMapper.saveRating(parameter);
		     modelAndView.addObject("insertException","感谢您的评分:"+rating+"分！");
		  }catch(Exception e){
			 modelAndView.addObject("insertException","保存数据异常！");
		  }finally{
			  modelAndView.setViewName("/WEB-INF/jsp/movieDetail.jsp");
			  return modelAndView;
		  }*/
		Message message=new Message();
		message.setRegister_prompt_info("1111111111");
		return message;
		
		
	}
	
	
	
	/////////////////////////////////////////
	@RequestMapping("/saveUserFeedbackRating.action")
	public ModelAndView saveUserFeedbackRating(int user_id,int movie_id,int user_feedback_rating){
		  //日志处理
		  logger_rating.info(user_id+"	"+movie_id+"	"+user_feedback_rating);
		 //在这里只是update feedback
		  ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		  RecommendMapper recommendMapper=(RecommendMapper) ac.getBean("recommendMapper");
		  HashMap<String,Object> parameter=new HashMap<String,Object>();
		  parameter.put("movie_id", movie_id);
		  parameter.put("user_id", user_id);
		  parameter.put("user_feedback_rating", user_feedback_rating);
		  ModelAndView modelAndView=new ModelAndView();
		  
		  try{
			  recommendMapper.saveUserFeedbackRating(parameter);
		     modelAndView.addObject("insertException","感谢您的评分:"+user_feedback_rating+"分！");
		  }catch(Exception e){
			 modelAndView.addObject("insertException","保存数据异常！");
		  }finally{
			  modelAndView.setViewName("/WEB-INF/jsp/userFeedback.jsp");
			  return modelAndView;
		  }
		
		}

}
