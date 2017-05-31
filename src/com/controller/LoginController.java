package com.controller;

import javax.servlet.http.HttpSession;











import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mapper.UserMapper;
import com.po.Message;
import com.po.User;
import com.util.ApplicationContextUtil;
 
@Controller
public class LoginController {
	 @RequestMapping("/login.action") 
		public ModelAndView login(){
	    	
	    	ModelAndView modelAndView=new ModelAndView ();
	     	modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
	 		return modelAndView;
	     }
	  
	 @RequestMapping("/validation.action") 
     public @ResponseBody User validation(String username,String password,HttpSession session){
    	//如果用户名不是yzy,itemsShow.jsp退出会失败
		 
		    ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
			UserMapper userMapper=(UserMapper) ac.getBean("userMapper");
			User user =userMapper.selectUserByUsername(username);
			if(user!=null && user.getUserpasswd().equals(password)){
		    //登录成功,设置session
			session.setAttribute("user",user);
			
			return user;
		}else{
     		 //登录失败,返回登录页面
     		return null;
     	 }
    	
     }
	 @RequestMapping("/showHome.action")
	 public ModelAndView showHome(){
		 
		 ModelAndView modelAndView=new ModelAndView();
		 modelAndView.setViewName("/WEB-INF/jsp/home.jsp");
		 return modelAndView;
		 
	 }
	 
	 
	 @RequestMapping("/registerPage.action") 
		public ModelAndView registerPage(){
	    	
	    	ModelAndView modelAndView=new ModelAndView ();
	     	modelAndView.setViewName("/WEB-INF/jsp/register.jsp");
	 		return modelAndView;
	     }
	 
	 @RequestMapping("/register.action")
	  public @ResponseBody Message register(String username,String password){
		    
		 ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
			UserMapper userMapper=(UserMapper) ac.getBean("userMapper");
			User user=userMapper.selectUserByUsername(username);
			Message message=new Message();
			if(user==null){
				//不存在该用户，可以注册
				User register_user=new User();
				register_user.setUsername(username);
				register_user.setUserpasswd(password);
				
				try{
					userMapper.insertUsetByUsername(register_user);
					
				}catch(Exception e){
					//插入数据库异常，跳转至失败页面
					message.setRegister_prompt_info("抱歉，注册信息保存失败，请再次尝试！");
				    return message;
			    }
				//数据保存成功
				message.setRegister_prompt_info("嘻嘻，注册成功！");
				return message;
			}else{
				//该用户名被占用,调到失败
				message.setRegister_prompt_info("用户名被占用，请更换！");
				return message;
			}
			
	}
}
