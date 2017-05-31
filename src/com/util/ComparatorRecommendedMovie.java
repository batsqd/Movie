package com.util;

import java.util.Comparator;

import com.po.RecommendedMovie;

public class ComparatorRecommendedMovie  implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		RecommendedMovie recommendedMovie1=(RecommendedMovie)o1;
		RecommendedMovie recommendedMovie2=(RecommendedMovie)o2;
		if(recommendedMovie1.getRecommend_vale()>recommendedMovie2.getRecommend_vale()){
			return -1;
		}else if(recommendedMovie1.getRecommend_vale()<recommendedMovie2.getRecommend_vale()){
			return 1;
		}else{
			return 0;
		}
		
	}

	

}
