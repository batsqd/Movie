package com.test;

import java.util.List;
import com.po.RecommendedMovie;
import com.recommender.ImproveSlopeOne;

public class TestImprovedSlopOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImproveSlopeOne improveSlopeOne=new ImproveSlopeOne();
		List<RecommendedMovie> list= improveSlopeOne.getPredictMovies(1, 5);
		for(int i=0;i<list.size();i++){
			System.out.println("movie_id:"+list.get(i).getMovie_id()+" recommended_value:"+list.get(i).getRecommend_vale());
		}
		System.out.println("=========list=============");
        System.out.println(list.size());
        System.out.println("=========list=============");
	}

}
