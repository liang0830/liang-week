package com.liang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.la.utils.UserUtils;
import com.liang.beans.User;
/**
 * 
 * @ClassName: RedisTest 
 * @Description: 测试类
 * @author:liAng
 * @date: 2019年12月9日 上午9:09:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 
	 * @Title: testJDK 
	 * @Description: JDK的序列化存储
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testJDK() {
		
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			//创建User对象
			User user = new User();
			//向User对象中存值
			user.setId(i);
			user.setName(UserUtils.getName());
			user.setGender(UserUtils.getSex());
			user.setPhone(UserUtils.getPhone());
			user.setEmail(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			redisTemplate.opsForValue().set("users"+i, user);
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为：JDK");
		System.out.println("保存数量为：50000");
		System.out.println("所耗时间为："+(end-start)+"毫秒");
	}
	/**
	 * 
	 * @Title: testJSON 
	 * @Description: JSON的序列化存储
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testJSON() {
		
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			
			//创建User对象
			User user = new User();
			//向User对象中存值
			user.setId(i);
			user.setName(UserUtils.getName());
			user.setGender(UserUtils.getSex());
			user.setPhone(UserUtils.getPhone());
			user.setEmail(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			redisTemplate.opsForValue().set("users"+i, user);
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为：JSON");
		System.out.println("保存数量为：50000");
		System.out.println("所耗时间为："+(end-start)+"毫秒");
	}
	
	/**
	 * 
	 * @Title: testHash 
	 * @Description: Hash系列化存储方式
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testHash() {
		
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			
			//创建User对象
			User user = new User();
			//向User对象中存值
			user.setId(i);
			user.setName(UserUtils.getName());
			user.setGender(UserUtils.getSex());
			user.setPhone(UserUtils.getPhone());
			user.setEmail(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			redisTemplate.opsForHash().put("users"+i, "user"+i, user.toString());
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为：Hash");
		System.out.println("保存数量为：50000");
		System.out.println("所耗时间为："+(end-start)+"毫秒");
	}
	
}
