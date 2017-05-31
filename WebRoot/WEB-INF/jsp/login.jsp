<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>袁志远个性化电影推荐</title>
	<link href="/Movie/css/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/Movie/js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="/Movie/js/userVerify.js"></script>
  </head> 
<body>
	<header>
		<h1 class="loginlogo">
			<a href="">个性化电影推荐</a>
		</h1>
	</header>
	<div class="loginform">
		<div class="bgcont newyear"></div>
		<div class="loginbox">
			<h2 class="logintitle">Sign in to movie</h2>
			
				<input id="username" name="username" required="required" type="text" placeholder="username">
				<input id="password" name="password" required="required" type="password" placeholder="password">
				
				<div class="keeplogin">
					<input id="rememberMe" name="rememberMe" value="true" tabindex="4" type="checkbox" />
					<label for="rememberMe" class="label1">Recommend a week</label>
				</div>
				<input id="login-button" name="submit" accesskey="l" value="Sign in" tabindex="4" type="submit" />
				
				<a href='http://yuanzhiyuan-pc:8080/Movie/registerPage.action'><font color='0552F2'><center>Create an new account</center></font></a>
				<input type="hidden" name="lt" value="LT-33783-e2DHGUarPxQuwriuT2PHQuxkG06KZA-cd.carzone365.com" />
				<input type="hidden" name="execution" value="e1s1" />
				<input type="hidden" name="_eventId" value="submit" />
				

			<!-- 27A645 -->
			<div id="loginMessageBox">
              
		      </div>
		</div>
	</div>
	
</body>
</html>
