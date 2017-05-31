package com.kmeans;

public class KmeansTest {
	public static void main(String[] args) {
		double[][] points = { {1, 0,0,0 },{1, 0,0,0 },{1,0,0,0},
				              {0,1,0,0} ,{0,1,0,0},{0,1,0,0},
		                      {0,0,1,0},{0,0,1,0},{0,0,1,0},
		                      {0,0,0,1},{0,0,0,1},{0,0,0,1},
		                      {1,1,0,0},{1,1,0,0},{1,1,0,0},
		                      {0,0,1,1},{0,0,1,1},{0,0,1,1}
		                      }; 
		Kmeans_data data = new Kmeans_data(points, 18, 4); 
		Kmeans_param param = new Kmeans_param(); 
		param.initCenterMehtod = Kmeans_param.CENTER_RANDOM; 
		
		Kmeans.doKmeans(6, data, param);

	
		System.out.print("The labels of points is: ");
		// labels[i]第i个点属于第labels[i]个聚类中心
		for(int i=0;i<data.labels.length;i++){
			System.out.print(data.labels[i]+" ");
		}
		/*
		System.out.println();
		for (int lable : data.labels) {
			System.out.print(lable + "  ");
		}*/
	}
}
