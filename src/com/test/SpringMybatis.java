package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mapper.MovieMapper;
import com.mapper.UserMapper;
import com.po.User;

public class SpringMybatis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		MovieMapper movieMapper=(MovieMapper) ac.getBean("movieMapper");
		System.out.println(movieMapper.selectMovieById(2));
		UserMapper userMapper=(UserMapper) ac.getBean("userMapper");
		User user=userMapper.selectUserByUsername("yzy");
		System.out.print(user);
	}

}
