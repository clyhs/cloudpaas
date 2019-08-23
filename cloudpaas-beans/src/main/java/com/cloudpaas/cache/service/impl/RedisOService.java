/**
 * 
 */
package com.cloudpaas.cache.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.cloudpaas.cache.service.IRedisOService;

import redis.clients.jedis.BinaryClient.LIST_POSITION;

/**
 * @author 大鱼
 *
 * @date 2019年8月22日 上午10:51:28
 */
@Service
public class RedisOService implements IRedisOService {
	
	@Autowired
	RedisTemplate<String,Object> redisTemplate;

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().get(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#getByPrefix(java.lang.String)
	 */
	@Override
	public Set<String> getByPrefix(String prefix) {
		// TODO Auto-generated method stub
		return redisTemplate.keys(prefix+"*");
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#set(java.lang.String, java.lang.String)
	 */
	@Override
	public void set(String key, Object value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#set(java.lang.String, java.lang.String, int)
	 */
	@Override
	public void set(String key, Object value, long expire) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value,expire , TimeUnit.SECONDS);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#delPrefix(java.lang.String)
	 */
	@Override
	public Boolean delPrefix(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.delete(key+"*");
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#del(java.lang.String[])
	 */
	@Override
	public long del(String... keys) {
		// TODO Auto-generated method stub
		long size = 0;
		if(null!=keys){
			for(String k:keys){
				redisTemplate.delete(k);
				size++;
			}
		}
		return size;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#append(java.lang.String, java.lang.String)
	 */
	@Override
	public Integer append(String key, String value) {
		// TODO Auto-generated method stub
		
		return redisTemplate.opsForValue().append(key, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#exists(java.lang.String)
	 */
	@Override
	public Boolean exists(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.hasKey(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#setnx(java.lang.String, java.lang.String)
	 */
	@Override
	public Long setnx(String key, Object value) {
		// TODO Auto-generated method stub
		Long success = 1l;
		if(exists(key)){
			success = 0l;
		}else{
			redisTemplate.opsForValue().set(key, value);
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#setex(java.lang.String, java.lang.String, int)
	 */
	@Override
	public void setex(String key, Object value, int seconds) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value,seconds , TimeUnit.SECONDS);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#setrange(java.lang.String, java.lang.String, int)
	 */
	@Override
	public void setrange(String key, Object value, int offset) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value, offset);
		
	
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#mget(java.lang.String[])
	 */
	@Override
	public List<Object> lget(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#mset(java.lang.String[])
	 */
	@Override
	public Long lset(String key,List<Object> values) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().rightPushAll(key, values);

	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#msetnx(java.lang.String[])
	 */
	@Override
	public Long msetnx(String... keysvalues) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#getset(java.lang.String, java.lang.String)
	 */
	@Override
	public Object getset(String key, String value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#getrange(java.lang.String, int, int)
	 */
	@Override
	public String getrange(String key, int startOffset, int endOffset) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().get(key, startOffset, endOffset);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#incr(java.lang.String)
	 */
	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().increment(key, 1);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#incrBy(java.lang.String, java.lang.Long)
	 */
	@Override
	public Long incrBy(String key, Long integer) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().increment(key,integer);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#decr(java.lang.String)
	 */
	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().increment(key, -1);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#decrBy(java.lang.String, java.lang.Long)
	 */
	@Override
	public Long decrBy(String key, Long integer) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().increment(key, -integer);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#serlen(java.lang.String)
	 */
	@Override
	public Long serlen(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hset(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void hset(String key, Object field, Object value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().put(key, field, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hsetnx(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Long hsetnx(String key, String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hmset(java.lang.String, java.util.Map)
	 */
	@Override
	public void hmset(String key, Map<Object, Object> hash) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().putAll(key,hash);;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hget(java.lang.String, java.lang.String)
	 */
	@Override
	public Object hget(String key, Object field) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().get(key, field);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hmget(java.lang.String, java.lang.String[])
	 */
	@Override
	public List<String> hmget(String key, String... fields) {
		// TODO Auto-generated method stub
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hincrby(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public Long hincrby(String key, Object field, Long value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().increment(key, field, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hexists(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean hexists(String key, Object field) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().hasKey(key, field);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hlen(java.lang.String)
	 */
	@Override
	public Long hlen(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().size(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hdel(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long hdel(String key, Object... fields) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().delete(key, fields);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hkeys(java.lang.String)
	 */
	@Override
	public Set<Object> hkeys(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().keys(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hvals(java.lang.String)
	 */
	@Override
	public List<Object> hvals(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().values(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#hgetall(java.lang.String)
	 */
	@Override
	public Map<Object, Object> hgetall(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().entries(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#lpush(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long lpush(String key, Object... strs) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().leftPushAll(key, strs);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#rpush(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long rpush(String key, Object... strs) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().rightPushAll(key, strs);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#linsert(java.lang.String, redis.clients.jedis.BinaryClient.LIST_POSITION, java.lang.String, java.lang.String)
	 */
	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#lset(java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public void lset(String key, Long index, Object value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForList().set(key, index, value);;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#lrem(java.lang.String, long, java.lang.String)
	 */
	@Override
	public Long lrem(String key, long count, Object value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().remove(key, count, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#ltrim(java.lang.String, long, long)
	 */
	@Override
	public void ltrim(String key, long start, long end) {
		// TODO Auto-generated method stub
		redisTemplate.opsForList().trim(key, start, end);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#lpop(java.lang.String)
	 */
	@Override
	public Object lpop(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().leftPop(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#rpop(java.lang.String)
	 */
	@Override
	public Object rpop(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().rightPop(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#rpoplpush(java.lang.String, java.lang.String)
	 */
	@Override
	public Object rpoplpush(String srckey, String dstkey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().rightPopAndLeftPush(srckey, dstkey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#lindex(java.lang.String, long)
	 */
	@Override
	public Object lindex(String key, long index) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().index(key, index);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#llen(java.lang.String)
	 */
	@Override
	public Long llen(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().size(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#lrange(java.lang.String, long, long)
	 */
	@Override
	public List<Object> lrange(String key, long start, long end) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().range(key, start, end);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sadd(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long sadd(String key, Object... members) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().add(key, members);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#srem(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long srem(String key, Object... members) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().remove(key, members);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#spop(java.lang.String)
	 */
	@Override
	public Object spop(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().pop(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sdiff(java.lang.String[])
	 */
	@Override
	public Set<Object> sdiff(String key, String otherKey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().difference(key, otherKey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sdiffstore(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long sdiffstore(String key, String otherKey,String destKey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sinter(java.lang.String[])
	 */
	@Override
	public Set<Object> sinter(String key,String otherKey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sinterstore(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long sinterstore(String key, String otherKey,String dstKey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().intersectAndStore(key, otherKey, dstKey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sunion(java.lang.String[])
	 */
	@Override
	public Set<Object> sunion(String key,String otherKey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().union(key, otherKey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sunionstore(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long sunionstore(String key,String otherKey, String destKey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#smove(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean smove(String srckey, String dstkey, Object value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().move(srckey, value, dstkey);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#scard(java.lang.String)
	 */
	@Override
	public Long scard(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().size(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#sismember(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean sismember(String key, Object value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#srandmember(java.lang.String)
	 */
	@Override
	public Object srandmember(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().randomMember(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#smembers(java.lang.String)
	 */
	@Override
	public Set<Object> smembers(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForSet().members(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zadd(java.lang.String, double, java.lang.String)
	 */
	@Override
	public Long zadd(String key, double score, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zrem(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long zrem(String key, Object... members) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().remove(key, members);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zincrby(java.lang.String, double, java.lang.String)
	 */
	@Override
	public Double zincrby(String key, double score, Object value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().incrementScore(key, value, score);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zrank(java.lang.String, java.lang.String)
	 */
	@Override
	public Long zrank(String key, Object value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().rank(key, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zrevrank(java.lang.String, java.lang.String)
	 */
	@Override
	public Long zrevrank(String key, Object value) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().reverseRank(key, value);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zrevrange(java.lang.String, long, long)
	 */
	@Override
	public Set<Object> zrevrange(String key, long start, long end) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zrangebyscore(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Set<Object> zrangebyscore(String key, double max, double min) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zcount(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Long zcount(String key, double min, double max) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zcard(java.lang.String)
	 */
	@Override
	public Long zcard(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().size(key);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zscore(java.lang.String, java.lang.String)
	 */
	@Override
	public Double zscore(String key, Object member) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().score(key, member);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zremrangeByRank(java.lang.String, long, long)
	 */
	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#zremrangeByScore(java.lang.String, double, double)
	 */
	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForZSet().removeRangeByScore(key, start, end);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#keys(java.lang.String)
	 */
	@Override
	public Set<String> keys(String pattern) {
		// TODO Auto-generated method stub
		return redisTemplate.keys(pattern);
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#type(java.lang.String)
	 */
	@Override
	public String type(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.type(key).name();
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.service.IRedisService#getExpireDate(java.lang.String)
	 */
	@Override
	public Long getExpireDate(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.getExpire(key);
	}

	
	
	
	

}
