package cn.zj.jedis;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class ClusterJedisTest {
	
	@Test
	public void testName() throws Exception {
		
		Set<HostAndPort> nodes = new HashSet<>();
		
		nodes.add(new HostAndPort("192.168.120.120", 7001));
		nodes.add(new HostAndPort("192.168.120.120", 7002));
		nodes.add(new HostAndPort("192.168.120.120", 7003));
//		nodes.add(new HostAndPort("192.168.120.120", 7004));
		nodes.add(new HostAndPort("192.168.120.120", 7005));
		nodes.add(new HostAndPort("192.168.120.120", 7006));
//		nodes.add(new HostAndPort("192.168.120.120", 7007));
//		nodes.add(new HostAndPort("192.168.120.120", 7008));
		
		JedisCluster jedisCluster =new JedisCluster(nodes);
		
		jedisCluster.hset("user:id:1", "address", "广州天河元岗");
		String address=jedisCluster.hget("user:id:1", "address");
		System.out.println("address :"+address);
		
	}
	
}
