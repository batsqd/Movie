<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Registration Page</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="/Movie/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/Movie/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="/Movie/css/blue.css">
  <script type="text/javascript" src="/Movie/js/jquery-1.11.1.js"></script>
  <script type="text/javascript" src="/Movie/js/userVerify.js"></script>
 
</head>
<body class="hold-transition register-page">
<div class="register-box">
  <div class="register-logo">
    <b>Movie</b>Watch
  </div>

  <div class="register-box-body">
    <p class="login-box-msg">Create your personal account</p>

     <div class="form-group has-feedback">
        <input id="username_register" username_register type="text" class="form-control" placeholder="username">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
     
      <div class="form-group has-feedback">
        <input id="password_register" type="password" class="form-control" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input id="password_register_again" type="password" class="form-control" placeholder="Retype password">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <a href='http://yuanzhiyuan-pc:8080/Movie/login.action' class="text-center">I already have a membership</a>
            </label>
          </div>
        </div>
        
        <div class="col-xs-4">
          <button id="register" type="button" class="btn btn-primary btn-block btn-flat">Register</button>
        </div>
        
      </div>
    
    
 </div>
 <div id="registerMessageBox">
 </div>
</div>


</body>
</html>
