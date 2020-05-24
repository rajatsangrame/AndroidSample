package com.example.daggerdemo.foody.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllReviews{

	@SerializedName("reviews")
	private List<ReviewsItem> reviews;

	public void setReviews(List<ReviewsItem> reviews){
		this.reviews = reviews;
	}

	public List<ReviewsItem> getReviews(){
		return reviews;
	}

	@Override
 	public String toString(){
		return 
			"AllReviews{" + 
			"reviews = '" + reviews + '\'' + 
			"}";
		}
}