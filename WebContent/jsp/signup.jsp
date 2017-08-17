<%@page import="com.cms.user.bean.UserBean"%>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>注册</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

   <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="${basePath}/css/theme.css">
	<link rel="stylesheet" href="${basePath }/css/font-awesome/css/font-awesome.css">

	<script src="${basePath}/script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${basePath}/script/bootstrap/bootstrap.js"></script>
	<script src="${basePath}/script/form-validator.js"></script>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <style type="text/css">

    	.tips{

			color: rgba(0, 0, 0, 0.5);

			padding-left: 10px;

		}

		.tips_true,.tips_false{

			padding-left: 10px;

		}

		.tips_true{

			color: green;

		}

		.tips_false{

			color: red;

		}



    </style>
    
  </head>
  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!-->
  <body>
  <!--<![endif]-->
    <div class="container-fluid">
        <div class="row-fluid">
    <div class="dialog span4">
        <div class="well">
            <div class="block-heading">注册</div>
            
            <ul class="nav nav-tabs">
      			<li class="active"><a href="#signupStu" data-toggle="tab">学生</a></li>
      			<li><a href="#signupSA" data-toggle="tab">宿舍管理员</a></li>
      			<li><a href="#signupA" data-toggle="tab">管理员</a></li>
    		</ul>
    		
    		<div id="myTabContent" class="tab-content">
            <div class="tab-pane active in" id="signupStu">
                <form id="signupStuForm" action="${basePath }cms/user/signup.action" method="post" onsubmit="return FormValidator.Validate(this,3)">
                    <input type="hidden" name="role" value="<%= UserBean.USER_STUDENT%>">
                  	<table>
	                   	<tr><td>用户名</td></tr>
	                   	<tr><td><input type="text" name="username"  class="span12" require="true" dataType="Custom" regexp="^[a-zA-Z0-9]{3,16}$" msg="用户名由字母和数字组成，长度在3-16" /></td></tr>
	                   	<tr><td>密码</td></tr>
	                   	<tr><td><input type="password" name="password" class="span12"  require="true" dataType="Custom" regexp="^[a-zA-Z0-9_\.]{5,20}$" msg="密码由字母和数字_.组成，长度在5-16"/></td></tr>
	                   	<tr><td>姓名</td></tr>
	                   	<tr><td><input type="text" name="name"  class="span12" require="true" dataType="Limit,Chinese"  min="2" max="6"/></td></tr>
	                   
	                   	<tr><td>学号</td></tr>
	                   	<tr><td><input type="text" name="code"  class="span12" dataType="Limit" min="1" max="20" require="true"></td></tr>
	                   	<tr><td> <input type="submit" class="btn btn-primary pull-right" value="注册"></td></tr>
                    </table>
                </form>
            </div>
            <!--宿舍管理员  -->
           <div  class="tab-pane fade"   id="signupSA">
            	<form id="signupSAForm" action="${basePath }/cms/user/signup.action" method="post" onSubmit="return FormValidator.Validate(this,3)">
                   <input type="hidden" name="role" value="<%= UserBean.USER_SHUSE_ADMIN%>">
                   
                   <table>
	                   	<tr><td>用户名</td></tr>
	                   	<tr><td><input type="text" name="username"  class="span12" require="true" dataType="Custom" regexp="^[a-zA-Z0-9]{3,16}$" msg="用户名由字母和数字组成，长度在3-16" /></td></tr>
	                   
	                   	<tr><td>密码</td></tr>
	                   	<tr><td><input type="password" name="password" class="span12"  require="true" dataType="Custom" regexp="^[a-zA-Z0-9_\.]{5,20}$" msg="密码由字母和数字_.组成，长度在5-16"/></td></tr>
	                   	
	                   	<tr><td>姓名</td></tr>
	                   	<tr><td><input type="text" name="name"  class="span12" require="true" dataType="Limit,Chinese"  min="2" max="6"/></td></tr>
	                   
	                   	<tr><td>性别</td></tr>
	                   	<tr>
	                   		<td><input type="radio" name="sex" value="0" checked="checked">女</td>
	                   		<td><input type="radio" name="sex" value="1">男</td>
	                   	</tr>
	                  
	                    <tr><td>联系电话</td></tr>
	                   	<tr><td><input type="text" name="phone"  class="span12" dataType="Mobile"  require="true"></td></tr>
	                   	
	                   <!-- 	<tr>
	                   		<td>负责楼宇编号</td>
	                   		<td><inpu</td>
	                   	</tr> -->
	                   	<tr><td></td></tr>
	                   	
	                   	<tr><td> <input type="submit" class="btn btn-primary pull-right" value="注册"></td></tr>
                    </table>
                   
              </form>
          </div>
          <!-- 管理员 -->
          <div  class="tab-pane fade"   id="signupA">
          	 <form id="signupAForm" action="${basePath }cms/user/signup.action" method="post" onsubmit="return FormValidator.Validate(this,3)">
                    <input type="hidden" name="role" value="<%= UserBean.USER_ADMIN%>">
                  	<table>
	                   	<tr><td>用户名</td></tr>
	                   	<tr><td><input type="text" name="username"  class="span12" require="true" dataType="Custom" regexp="^[a-zA-Z0-9]{3,16}$" msg="用户名由字母和数字组成，长度在3-16" /></td></tr>
	                   	<tr><td>密码</td></tr>
	                   	<tr><td><input type="password" name="password" class="span12"  require="true" dataType="Custom" regexp="^[a-zA-Z0-9_\.]{5,20}$" msg="密码由字母和数字_.组成，长度在5-16"/></td></tr>
	                   	<tr><td>姓名</td></tr>
	                   	<tr><td><input type="text" name="name"  class="span12" require="true" dataType="Limit,Chinese"  min="2" max="6"/></td></tr>
	                   
	                   	<tr><td>联系电话</td></tr>
	                   	<tr><td><input type="text" name="phone"  class="span12" dataType="Mobile"  require="true"></td></tr>
	                   	
	                   	<tr><td> <input type="submit" class="btn btn-primary pull-right" value="注册"></td></tr>
                    </table>
                </form>
          
          
          </div>
          
            </div>
            
            
        </div>
         <p class="pull-right" style=""><a href="${basePath }/jsp/signin.jsp">去登录</a></p>
    </div>
</div>
</div>
  </body>
  
  <script type="text/javascript">
	  window.onload = function () {
          if(${requestScope.msg != null}) {
	      	alert('${requestScope.msg}');
		  }
      }

		/* function mycheck(){
			   if(isNull(form1.role.value)){  
			   divrole.innerHTML='<font class="tips_false">请选择身份</font>'; 
			   return false;
			   }
			   
			   if(isNull(form1.name.value)){  
				   divname.innerHTML='<font class="tips_false">请输入姓名</font>'; 
				   return false;
				} 
			   
			   if(isNull(form1.username.value)){   
				   divusername.innerHTML='<font class="tips_false">请填写用户名</font>'; 
			   return false;
			   } 
			   
			   if(isNull(form1.password.value)){
				   divpassword.innerHTML='<font class="tips_false">请输入密码</font>'; 
			   return false;
			   }
			      
			   if(isNull(form1.code.value)) {
				   divcode.innerHTML='<font class="tips_false">学号不能为空</font>'; 
				   return false;
			   }
			}
		 
			 function isNull(str){
			if ( str == "" ) return true;
			var regu = "^[ ]+$";
			var re = new RegExp(regu);
			return re.test(str);
			}  */

  </script>
  
</html>
