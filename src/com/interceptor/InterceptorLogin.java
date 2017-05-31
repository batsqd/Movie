package com.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import com.po.User;
import com.util.RedisUtils;

public class InterceptorLogin implements HandlerInterceptor{
	
	private static Logger logger_login = Logger.getLogger("logger_login");
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception exception)
			throws Exception {
		if(exception==null && request.getSession().getAttribute("user")!=null){
			//登录日志处理
			User user=(User)request.getSession().getAttribute("user");
	        if(user!=null){
	        	logger_login.info("用户ID："+user.getUserid()+" 登录成功！");
	        	//System.out.println("======================"+user.getUserid()+"====================================");
	        }
			
			
	   }
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse reponse,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//用户通过登录，进入主页面，记录浏览量
		
		synchronized(this){
			Jedis jedis=RedisUtils.getJedis();
			//第一个用户访问
			if(jedis.get("countVisitors")==null){
				jedis.set("countVisitors", "1");
				request.getSession().setAttribute("countVisitors", 1);
			}else{
				int countVisitors=Integer.parseInt(jedis.get("countVisitors"));
			    countVisitors++;
			    jedis.set("countVisitors", countVisitors+"");
			    request.getSession().setAttribute("countVisitors", countVisitors);
			}
			
	    }
	    
		
	    return true;
	}
	


}
