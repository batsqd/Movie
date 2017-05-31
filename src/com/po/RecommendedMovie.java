package com.po;

public class RecommendedMovie {
    private Integer movie_id;
    private double recommend_vale;
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public double getRecommend_vale() {
		return recommend_vale;
	}
	public void setRecommend_vale(double recommend_vale) {
		this.recommend_vale = recommend_vale;
	}
	@Override
	public String toString() {
		return "RecommendedMovie [movie_id=" + movie_id + ", recommend_vale="
				+ recommend_vale + "]";
	}
    
	
	
	
}
