package com.test;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;




import com.mapper.MovieMapper;
import com.po.Movie;




public class TestMybatis {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		 String resource="SqlMapConfig.xml";
	        InputStream inputStream=Resources.getResourceAsStream(resource);
	        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
	        SqlSession sqlSession=sqlSessionFactory.openSession();
	        
	        MovieMapper movieMapper=sqlSession.getMapper(MovieMapper.class);
	        Movie movie=movieMapper.selectMovieById(3);
	        System.out.println(movie.toString());
	        
	}

}
