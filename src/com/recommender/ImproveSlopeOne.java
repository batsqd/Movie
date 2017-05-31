package com.recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.confg.Configs;
import com.kmeans.Kmeans;
import com.kmeans.Kmeans_data;
import com.kmeans.Kmeans_param;
import com.kmeans.Kmeans_result;
import com.po.Node;
import com.po.RecommendedMovie;
import com.util.ComparatorRecommendedMovie;
import com.util.RandomNumsGenerator;
import com.util.ReadRatingData;
import com.util.ReadUserData;

public class ImproveSlopeOne {
	public static void main(String args[]){
		
		double primitiveRatingData[][]=ReadRatingData.readRatingFile("C:/Users/yuanzhiyuan/Desktop/slope/ml-100k/u1.base", 943, 1682);
	    Node MovieFansDataset[][]=getMovieFansDataset("C:/Users/yuanzhiyuan/Desktop/slope/ml-100k/u.user",943,8,primitiveRatingData);
        double[][] TestDataSet=	ReadRatingData.readRatingFile("C:/Users/yuanzhiyuan/Desktop/slope/ml-100k/u1.test", 462, 1682);	
		
		long  time_start=System.currentTimeMillis();
		double MAE=getSlopeMAE(MovieFansDataset,primitiveRatingData,TestDataSet);
		long time_end=System.currentTimeMillis();
		System.out.println("improved slope one 训练模型+预测耗时："+(time_end-time_start));
		System.out.println("slope 预测精度："+MAE);
		
	}
	//返回的map<Movie_Id,Score>
	public  List<RecommendedMovie> getPredictMovies(int userid,int mums){
		double primitiveRatingData[][]=ReadRatingData.readRatingFile(Configs.rating_file_path, Configs.countUsers,Configs.countItems);
		Node MovieFansDataset[][]=getMovieFansDataset(Configs.user_file_path,Configs.countUsers,Configs.num_of_feature,primitiveRatingData);		
		HashMap<String,double[][]> getDev_avg = getDev_avg(MovieFansDataset);
	    double dev_avg[][]=getDev_avg.get("dev_avg");
	    List<RecommendedMovie> list=new ArrayList<RecommendedMovie>();
	    
	    for(int movie_id=0;movie_id<Configs.countItems;movie_id++){
	    	//应该注意的是在评价矩阵中user_id 0开始，数据库user_id 1开始
	    	double recommend_value=getPredictUseriToItemj(userid-1, movie_id, primitiveRatingData,dev_avg);
	    	if(recommend_value>=3){
	    		//电影预测值大于3，随机选择list中5部电影推送给用户；
	    		RecommendedMovie recommendedMovie=new RecommendedMovie();
		    	recommendedMovie.setMovie_id(movie_id);
		    	recommendedMovie.setRecommend_vale(recommend_value);
		    	list.add(recommendedMovie);
	    	}
	    	
	    	//System.out.println(list.size());
	    	//在这里使用二叉堆选择排名较高的电影，可能要考虑多线程
	    }
	    List<RecommendedMovie> selectMoviesByRandomToUser=new ArrayList<RecommendedMovie>();
	    //randomCommon(0, list.size(), 5) 产生【0 list.size()-1】中的任何随机数5个
	    List<Integer> indexs=RandomNumsGenerator.randomCommon(0, list.size(), 5);
	    
	    for(int i=0;i<indexs.size();i++){
		    	selectMoviesByRandomToUser.add(list.get(indexs.get(i)));
		}
	    
	    
	    return selectMoviesByRandomToUser;
	    //打印所有推荐的电影
	    /*
	    for(int i=0;i<list.size();i++){
	    	System.out.println(list.get(i));
	    }*/
	    /*
	    ComparatorRecommendedMovie comparator=new ComparatorRecommendedMovie();
	    Collections.sort(list, comparator);
	    
	   List<RecommendedMovie> top_nums_movies=new ArrayList<RecommendedMovie>();
	   for(int i=0;i<mums;i++){
		   //推荐值<2.6不向用户推荐
		   if(list.get(i).getRecommend_vale()>2.6){
			   top_nums_movies.add(list.get(i));
		   }
		   
	   }
	   for(int i=0;i<top_nums_movies.size();i++){
	    	System.out.println(top_nums_movies.get(i).getRecommend_vale());
	    }		
		return top_nums_movies;
		*/
	}
	
	  //-----------------slope one------------------
	  public static double getSlopeMAE(Node MovieFansDataset[][], double TrainDataSet[][],double TestDataSet[][]){
		    double MAE=0;
		    HashMap<String,double[][]> getDev_avg = getDev_avg(MovieFansDataset);
		    double dev_avg[][]=(double[][]) getDev_avg.get("dev_avg");
	        MAE=getMAE(TrainDataSet,TestDataSet,dev_avg);
	        return MAE;
		}
	   
	  public static HashMap<String,double[][]> getDev_avg(Node TrainDataSet[][]){
			/*
			if(TrainDataSet.length==0){
				System.out.println("有不合理的聚类！");
			}
			*/
			double devj_col=0;
			int matrix_row=TrainDataSet.length;
			int matrix_col=TrainDataSet[0].length;
		    double dev_avg[][]=new double[matrix_col][matrix_col];//matrix_col
	        ArrayList <Double> al=new ArrayList<Double>();
	        ArrayList <Integer> commonsCount=new ArrayList<Integer>();
	        HashMap<String,double[][]> saveTwoArray=new HashMap<String,double[][]>();
	        for(int j=0;j<matrix_col;j++){
	        	for(int col=j+1;col<matrix_col;col++){
	        		for(int row=0;row<matrix_row;row++){
	        			if(TrainDataSet[row][j]!=null&&TrainDataSet[row][col]!=null){
	        				devj_col=TrainDataSet[row][j].getAvg_rating()-TrainDataSet[row][col].getAvg_rating();
	        				al.add(devj_col);
	        				if(TrainDataSet[row][j].getCount()>TrainDataSet[row][col].getCount()){
	        					commonsCount.add(TrainDataSet[row][col].getCount());
	        				}else{
	        					commonsCount.add(TrainDataSet[row][j].getCount());
	        				}
	        			}
	        		}
	        		if(al.size()==0){
	        			dev_avg[j][col]=Double.MAX_VALUE;
	    			}else{
	        			HashMap<String,Double> hp=calculate_dev(al,commonsCount);
	    				dev_avg[j][col]=hp.get("dev_avg");////////////////////
	    				al.clear();
	    				commonsCount.clear();
	        		}
	        	}
	        }
	        saveTwoArray.put("dev_avg", dev_avg);
	        return saveTwoArray;
			
		}
		
	  public static HashMap<String,Double> calculate_dev(ArrayList<Double> al,ArrayList<Integer> commonsCount){
			
			HashMap<String,Double> hp=new HashMap<String,Double>();
		    double dev_avg=0;
			double allRatedev=0;
			int count=0;
			for(int i=0;i<al.size();i++){
				allRatedev+=al.get(i)*commonsCount.get(i);
			}
			for(int i=0;i<commonsCount.size();i++){
				count+=commonsCount.get(i);
			}
			dev_avg=allRatedev/count;
			hp.put("dev_avg",dev_avg );
			return hp;
		}
		
		public static double getMAE(double TrainDataSet[][],double TestDataSet[][],double dev_avg[][]){
	       
			double MAE=0;
			int n=0;
			double err=0;
			double countErr=0;
	        for(int i=0;i<TestDataSet.length;i++){
	        	for(int j=0;j<TestDataSet[i].length;j++){
	        		if(TestDataSet[i][j]!=-1){
	        			err=getPredictUseriToItemj(i,j,TrainDataSet,dev_avg)-TestDataSet[i][j];
	        			countErr+=Math.abs(err);
	        			n++;
	        		}
	        	}
	        }
	        MAE=countErr/n;
	        return MAE;
		}
		
		
		public static double getPredictUseriToItemj(int Useri,int Itemj,double[][] trainDataSet,double dev_avg[][]){
			double countRate=0;
			int count=0;
			for(int k=0;k<trainDataSet[Useri].length;k++){
				
				if(trainDataSet[Useri][k]!=-1 && dev_avg[k][Itemj]!=Double.MAX_VALUE && k<Itemj){
					
					countRate+=(trainDataSet[Useri][k]-dev_avg[k][Itemj]);
				    count++;
				}
				if(trainDataSet[Useri][k]!=-1 && dev_avg[Itemj][k]!=Double.MAX_VALUE  && k>Itemj){
					countRate+=(trainDataSet[Useri][k]+dev_avg[Itemj][k]);
				    count++;
				}
			}
			//System.out.println("count:"+count);
			if(count==0){
				return 2.5;
			}else{
				/*    A   B           A    B
				 * X  1   5           1    5
				 * Y  1   5  训练                  1    5
				 * =================================
				 * Z  4   ? 预测                  ?    3
				         (8)        (-1)
				 */       
				double predict_value=countRate/count;
				
				if(predict_value>5){
					
					predict_value=5;
					
				}else if(predict_value<1){
					
					predict_value=1;
				}else{
					//原值
				}
				return predict_value;
			}
			
		}
		 
		
		
		public static HashMap<String,Double> calculate_dev(ArrayList<Double> al){
			
			HashMap<String,Double> hp=new HashMap<String,Double>();
		    double dev_avg=0;
			int allRatedev=0;
			for(int i=0;i<al.size();i++){
				allRatedev+=al.get(i);
			}
			dev_avg=(double)allRatedev/al.size();
			hp.put("dev_avg",dev_avg );
			return hp;
		}
	
	
	
	
	
	
	
	//======================movie=========
	    //cluster core as movie fans
		public static Node[][] getMovieFansDataset(String userDataTxt,int countUser,int numOffeature,double primitiveRatingData[][]){
			 //得到用户聚类的数据
		      int[][] user_data=ReadUserData.readUserFile(userDataTxt,countUser,numOffeature);
		      //将输入的int型数据转换为double
		      double preData[][]=new double[user_data.length][user_data[0].length];
		      for(int i=0;i<user_data.length;i++){
		    	  for(int j=0;j<user_data[i].length;j++){
		    		  preData[i][j]=user_data[i][j];
		    	  }
		      }
		      Kmeans_data data = new Kmeans_data(preData, preData.length, preData[0].length); 
				Kmeans_param param = new Kmeans_param(); 
				param.initCenterMehtod = Kmeans_param.CENTER_RANDOM; //随机产生聚类中心
				Kmeans_result kmeans_result=Kmeans.doKmeans(7, data, param);//原来7
				HashMap <Integer,ArrayList<Integer>> hp=new HashMap<Integer,ArrayList<Integer>>();
				
				//判断聚类结果是否为空
				for(int i=0;i<kmeans_result.k;i++){
					if(kmeans_result.centerCounts[i]==0){
						//System.out.println("（某一个聚类中元素为空！）");
					}
					hp.put(i, new ArrayList<Integer>());
					 //    聚类核心       该聚类下对应的用户
					
				}
				Node fansRatedata[][]=new Node[kmeans_result.k][primitiveRatingData[0].length];
				//double fansRatedata[][]=new double[kmeans_result.k][primitiveRatingData[0].length];
				
				////labels[i]代表data[i]这个元素所在的聚类在第labels[i]个聚类中
				
			    for(int useri=0;useri<kmeans_result.labels.length;useri++){
			    	hp.get(kmeans_result.labels[useri]).add(useri);//key ：聚类       value:用户
			    }	
				
			   
			    for(int i=0;i<kmeans_result.k;i++){
			    	fansRatedata[i]=Singlefans(hp.get(i),primitiveRatingData);
			    }
			    
				return fansRatedata;
			}
		
		
		public static Node[] Singlefans(ArrayList<Integer> simUser,double[][] primitiveRatingData){
			    
			    double sum=0;
			    Node row[]=new Node[primitiveRatingData[0].length];
			    //double row[]=new double[primitiveRatingData[0].length];
				int count=0; //错了吧-4-10
				for(int j=0;j<primitiveRatingData[0].length;j++){
					count=0;
					sum=0;
					for(int i=0;i<simUser.size();i++){
					  if(primitiveRatingData[simUser.get(i)][j]>0){
						  //System.out.println("===========1111111111111111======================");
						  //System.out.print(primitiveRatingData[simUser.get(i)][j]+" ");
						  //System.out.println("===========111111111111111111======================");
						//sum+=primitiveRatingData[i][j];//4-10
						sum+=primitiveRatingData[simUser.get(i)][j];
						count++;
					   }
					 }
					if(count==0){
						//没有人对该电影评价
						//row[j]=-1;
						//System.out.println(row[j]);
					}else{
						//count 越大 可行度越强，待考虑加权
					    //row[j]=sum/count;
						//count 越大 可行度越强，待考虑加权
						Node node=new Node();
						node.setAvg_rating(sum/count);
						node.setCount(count);
					   // row[j]=sum/count;
						row[j]=node;
					    
					}
					
					
					
			}
			return row;
		
		}

}
