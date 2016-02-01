package com.cogxio.database;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class LocationJDO {
	
	@Persistent
	@PrimaryKey
	private String id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String category;
	
	@Persistent
	private Float rating;
	
	@Persistent
	private Float latitude;
	
	@Persistent
	private Float longitude;
	
	@Persistent
	private Set<String> searchToken;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Set<String> getSearchToken() {
		return searchToken;
	}

	public void setSearchToken(Set<String> searchToken) {
		this.searchToken = searchToken;
	}
}
