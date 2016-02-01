package com.cogxio.database;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Counter {
	
	  @PrimaryKey
	  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	  private Long id;

	  @Persistent
	  private Integer count;
	  
	  @Persistent
	  private Integer shardNumber;
	  
	  @Persistent
	  private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getShardNumber() {
		return shardNumber;
	}

	public void setShardNumber(Integer shardNumber) {
		this.shardNumber = shardNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String string) {
		this.type = string;
	}
}
