package com.cogxio.services;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.cogxio.database.LocationJDO;
import com.cogxio.database.ShardedCounter;
import com.cogxio.util.PMF;


public class Services 
{
	public List<LocationJDO> getLocationsList()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(LocationJDO.class);
		
		List<LocationJDO> locations = (List<LocationJDO>) query.execute();
		return locations;
	}
	
	public Integer getCount()
	{
		ShardedCounter shardedCounter = new ShardedCounter();
		return shardedCounter.getCount("location");
	}
	
	public List<LocationJDO> getSearch(String queryString)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(LocationJDO.class, "searchToken == '"+queryString.trim()+"'");
		
		List<LocationJDO> locations = (List<LocationJDO>) query.execute();
		return locations;
	}
}
