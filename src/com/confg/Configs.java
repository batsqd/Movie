package com.confg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Configs {
	private static Properties pp=null;
    private static InputStream fis=null;
  //保存用于聚类的用户数据
    public static  String user_file_path;
    //用户对电影的评价数据
    public static  String rating_file_path;
    //public static final String 
    //被用于训练的用户和电影总数
    public static  int countUsers;//943
    public static int countItems;
    //使用用户的8个特征用于聚类
    public static int num_of_feature;
    //已经废弃，用于 basic slope
    //public static final String train_data_address="D:/21test/u1.base";
    public static  int recommend_movie_num;
    
    public static int hot_movie_nums=8;
	static{
    	try{
    		
    		//从dbinfo.Properties文件中读取配置信息
    		pp=new Properties();
    		fis=Configs.class.getClassLoader().getResourceAsStream("SystemParameter.Properties");
    		pp.load(fis);
    		
    		user_file_path=pp.getProperty("user_file_path");
    		rating_file_path=pp.getProperty("rating_file_path");
    		countUsers=Integer.parseInt(pp.getProperty("countUsers"));
    		countItems=Integer.parseInt(pp.getProperty("countItems"));
    		recommend_movie_num=Integer.parseInt(pp.getProperty("recommend_movie_num"));
    		num_of_feature=Integer.parseInt(pp.getProperty("num_of_feature"));
    		//System.out.println("user_file_path:"+user_file_path+" rating_file_path"+rating_file_path);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		try{
    			fis.close();
    		}catch(IOException e){
    			e.printStackTrace();
    		}
    		fis=null;
    	}
    }
	
   

   
}
