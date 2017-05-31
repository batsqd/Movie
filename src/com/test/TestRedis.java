package com.test;
import com.util.RedisUtils;
public class TestRedis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//RedisUtils.getJedis().set("xiaomin", "ok");
		System.out.println(RedisUtils.getJedis().get("countVisitors"));
	}

}
