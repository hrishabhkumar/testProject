package com.cogxio.database;

import java.util.List;
import java.util.Random;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.cogxio.util.PMF;

/**
 * This initial implementation simply counts all instances of the
 * Counter class in the datastore. The only way to increment the
 * number of shards is to add another shard by creating another entity in the
 * datastore
 */
public class ShardedCounter {

  private static final int NUM_SHARDS = 20;

  /**
   * Retrieve the value of this sharded counter.
   *
   * @return Summed total of all shards' counts
   */
  public int getCount(String type) {
    int sum = 0;
    PersistenceManager pm = PMF.get().getPersistenceManager();

    try 
    {
      List<Counter> shards = (List<Counter>) pm.newQuery(Counter.class, "type == '"+type+"'").execute();
     
      if (shards != null && !shards.isEmpty()) 
      {
        for (Counter shard : shards) 
        {
          sum += shard.getCount();
        }
      }
    } 
    finally 
    {
      pm.close();
    }

    return sum;
  }

  /**
   * Increment the value of this sharded counter.
   */
  public void increment(String type) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    Random generator = new Random();
    int shardNum = generator.nextInt(NUM_SHARDS);

    try 
    {
      Query shardQuery 			= pm.newQuery(Counter.class, "shardNumber == "+shardNum+" && type == '"+type+"'");
      List<Counter> shards 		=  (List<Counter>) shardQuery.execute();
      Counter shard				= null;

      if (shards != null && !shards.isEmpty()) 
      {
        shard = shards.get(0);
        shard.setCount(shard.getCount() + 1);
      } 
      else 
      {
        shard = new Counter();
        shard.setShardNumber(shardNum);
        shard.setCount(1);
      }

      pm.makePersistent(shard);
    } 
    finally 
    {
      pm.close();
    }
  }
}