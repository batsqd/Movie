package com.po;

import java.sql.Date;

public class Movie {
	private int movie_id ;
	private String movie_name;
	private String movie_director;
	private String movie_actor;
	private String movie_genres;
	private String movie_language;
	private String movie_district;
	private Date movie_release_date;
	private String movie_keyword;
	private String movie_picture_url;
	private String movie_download_url;
	private int movie_playcount;
	private String movie_synopsis;
	private float movie_average_score;
	private boolean movie_is_hot;
	private int movie_ratings_times;
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_director() {
		return movie_director;
	}
	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}
	public String getMovie_actor() {
		return movie_actor;
	}
	public void setMovie_actor(String movie_actor) {
		this.movie_actor = movie_actor;
	}
	public String getMovie_genres() {
		return movie_genres;
	}
	public void setMovie_genres(String movie_genres) {
		this.movie_genres = movie_genres;
	}
	public String getMovie_language() {
		return movie_language;
	}
	public void setMovie_language(String movie_language) {
		this.movie_language = movie_language;
	}
	public String getMovie_district() {
		return movie_district;
	}
	public void setMovie_district(String movie_district) {
		this.movie_district = movie_district;
	}
	public Date getMovie_release_date() {
		return movie_release_date;
	}
	public void setMovie_release_date(Date movie_release_date) {
		this.movie_release_date = movie_release_date;
	}
	public String getMovie_keyword() {
		return movie_keyword;
	}
	public void setMovie_keyword(String movie_keyword) {
		this.movie_keyword = movie_keyword;
	}
	public String getMovie_picture_url() {
		return movie_picture_url;
	}
	public void setMovie_picture_url(String movie_picture_url) {
		this.movie_picture_url = movie_picture_url;
	}
	public String getMovie_download_url() {
		return movie_download_url;
	}
	public void setMovie_download_url(String movie_download_url) {
		this.movie_download_url = movie_download_url;
	}
	public int getMovie_playcount() {
		return movie_playcount;
	}
	public void setMovie_playcount(int movie_playcount) {
		this.movie_playcount = movie_playcount;
	}
	public String getMovie_synopsis() {
		return movie_synopsis;
	}
	public void setMovie_synopsis(String movie_synopsis) {
		this.movie_synopsis = movie_synopsis;
	}
	public float getMovie_average_score() {
		return movie_average_score;
	}
	public boolean isMovie_is_hot() {
		return movie_is_hot;
	}
	public void setMovie_is_hot(boolean movie_is_hot) {
		this.movie_is_hot = movie_is_hot;
	}
	public int getMovie_ratings_times() {
		return movie_ratings_times;
	}
	public void setMovie_ratings_times(int movie_ratings_times) {
		this.movie_ratings_times = movie_ratings_times;
	}
	public void setMovie_average_score(float movie_average_score) {
		this.movie_average_score = movie_average_score;
	}
	@Override
	public String toString() {
		return "Movie [movie_id=" + movie_id + ", movie_name=" + movie_name
				+ ", movie_director=" + movie_director + ", movie_actor="
				+ movie_actor + ", movie_genres=" + movie_genres
				+ ", movie_language=" + movie_language + ", movie_district="
				+ movie_district + ", movie_release_date=" + movie_release_date
				+ ", movie_keyword=" + movie_keyword + ", movie_picture_url="
				+ movie_picture_url + ", movie_download_url="
				+ movie_download_url + ", movie_playcount=" + movie_playcount
				+ ", movie_synopsis=" + movie_synopsis
				+ ", movie_average_score=" + movie_average_score
				+ ", movie_is_hot=" + movie_is_hot + ", movie_ratings_times="
				+ movie_ratings_times + "]";
	}
	
	
  
}
