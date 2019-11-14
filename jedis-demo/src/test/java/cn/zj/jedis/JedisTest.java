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
		//����redis���ݿ�
		
		//����jedis���Ӷ���
		Jedis jedis = new Jedis("192.168.120.120");
		//��������
		jedis.auth("123456");
		jedis.flushAll();
		System.out.println("jedis:"+jedis);
		//��ȡ���е�key
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
			System.out.println(key);
		}
		System.out.println("--------String����----------------");
		
		//String
		jedis.set("user:id:1:username", "xiaojingge"); 
		String username=jedis.get("user:id:1:username");
		System.out.println(username);
		
		System.out.println("--------list����-------");
		jedis.lpush("users", "jack","rose","ǡ��","��Եǧ�������");
		//��ȡlist����
		List<String> users = jedis.lrange("users", 0, -1);
		for (String user : users) {
			System.out.println(user);
		}
		
		System.out.println("--------set����--------------------");
		jedis.sadd("username1", "���","С��Ů","���");
		jedis.sadd("username2", "���","С��Ů","����","Ӧ־ƽ");
		Set<String> sinter = jedis.sinter("username1","username2");
		for (String string : sinter) {
			System.out.println(string);
		}
		
		System.out.println("--------hash����------0---------");
		jedis.hset("user:id:1", "username", "С����");
		jedis.hset("user:id:1", "age", "18");
		jedis.hset("user:id:1", "sex", "��");
		jedis.hset("user:id:1", "email", "1234@qq.com");
		//��ȡ
		String name = jedis.hget("user:id:1", "username");
		System.out.println(name);
		Map<String, String> user = jedis.hgetAll("user:id:1");
		System.out.println(user);
		
		System.out.println("--------SortedSet����---------------");
		jedis.zadd("girls", 1,"����");
		jedis.zadd("girls", 2,"��ʩ");
		jedis.zadd("girls", 3,"���Ѿ�");
		jedis.zadd("girls", 4,"����");
		jedis.zadd("girls", 6,"�Ǳ���");
		
		//��ȡ
		Set<String> girls = jedis.zrange("girls", 0, 3);
		for (String string : girls) {
			System.out.println(string);
		}
	}
}
