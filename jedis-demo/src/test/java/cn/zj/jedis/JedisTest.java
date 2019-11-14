package cn.zj.jedis;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {

	@SuppressWarnings("resource")
	@Test
	public void testConnection() throws Exception {
		
		System.out.println("Hello World");
		//连接redis数据库
		
		//创建jedis连接对象
		Jedis jedis = new Jedis("192.168.120.120");
		//设置密码
		jedis.auth("123456");
		jedis.flushAll();
		System.out.println("jedis:"+jedis);
		//获取所有的key
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
			System.out.println(key);
		}
		System.out.println("--------String类型----------------");
		
		//String
		jedis.set("user:id:1:username", "xiaojingge"); 
		String username=jedis.get("user:id:1:username");
		System.out.println(username);
		
		System.out.println("--------list类型-------");
		jedis.lpush("users", "jack","rose","恰逢","有缘千里来相会");
		//获取list数据
		List<String> users = jedis.lrange("users", 0, -1);
		for (String user : users) {
			System.out.println(user);
		}
		
		System.out.println("--------set类型--------------------");
		jedis.sadd("username1", "杨过","小龙女","大雕");
		jedis.sadd("username2", "杨过","小龙女","雕兄","应志平");
		Set<String> sinter = jedis.sinter("username1","username2");
		for (String string : sinter) {
			System.out.println(string);
		}
		
		System.out.println("--------hash类型------0---------");
		jedis.hset("user:id:1", "username", "小李子");
		jedis.hset("user:id:1", "age", "18");
		jedis.hset("user:id:1", "sex", "男");
		jedis.hset("user:id:1", "email", "1234@qq.com");
		//获取
		String name = jedis.hget("user:id:1", "username");
		System.out.println(name);
		Map<String, String> user = jedis.hgetAll("user:id:1");
		System.out.println(user);
		
		System.out.println("--------SortedSet类型---------------");
		jedis.zadd("girls", 1,"貂蝉");
		jedis.zadd("girls", 2,"西施");
		jedis.zadd("girls", 3,"王昭君");
		jedis.zadd("girls", 4,"杨玉环");
		jedis.zadd("girls", 6,"乔碧萝");
		
		//获取
		Set<String> girls = jedis.zrange("girls", 0, 3);
		for (String string : girls) {
			System.out.println(string);
		}
	}
}
