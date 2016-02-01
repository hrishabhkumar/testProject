package com.cogxio.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.cogxio.database.Counter;
import com.cogxio.database.LocationJDO;
import com.cogxio.util.PMF;
import com.cogxio.util.Utility;

public class DataUploader 
{
	Logger logger  = Logger.getLogger(this.getClass().getName());
	public void uploadLocationData()
	{
		StringTokenizer token = null;
		BufferedReader bufferedReader = null;
		String line=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		LocationJDO locationJDO=null;;
		Collection<LocationJDO> locations=new ArrayList<LocationJDO>();
		try
		{
			bufferedReader=new BufferedReader(new FileReader("places.txt"));
			while((line=bufferedReader.readLine())!=null)
			{	
				
				token=new StringTokenizer(line, ",");
				locationJDO=new LocationJDO();
				if(token.countTokens() == 6)
				{
					String id			= token.nextToken().trim();
					
					String name			= token.nextToken().trim();
					
					String category		=token.nextToken().trim();
					
					Float latitude		= Float.parseFloat(token.nextToken());
					
					Float longitude		= Float.parseFloat(token.nextToken());
					
					Float rating		= Float.parseFloat(token.nextToken());
					
					locationJDO.setId(id);
					locationJDO.setName(name);
					locationJDO.setCategory(category);
					locationJDO.setLatitude(latitude);
					locationJDO.setLongitude(longitude);
					locationJDO.setRating(rating);
					
					Set<String> searchTokenSet = Utility.getTokensForIndexingOrQuery(name, 40, 2);
					searchTokenSet.add(category);
					searchTokenSet.add(latitude+"");
					searchTokenSet.add(longitude+"");
					searchTokenSet.add(rating+"");
					locationJDO.setSearchToken(searchTokenSet);
				}
				locations.add(locationJDO);
			}
			
			pm.makePersistentAll(locations);
			Counter counter = new Counter();
			counter.setShardNumber(1);
			counter.setType("location");
			counter.setCount(locations.size());
			pm.makePersistent(counter);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.warning(e.getMessage());
			logger.warning("error occured");
		}
		finally{
			pm.close();
		}
	}
}