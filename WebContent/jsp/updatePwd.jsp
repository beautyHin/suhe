<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	  <link rel="stylesheet" href="${basePath}css/pintuer.css">
	  <link rel="stylesheet" href="${basePath}css/admin.css">
	  <script src="${basePath}script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
	  <script src="${basePath}script/pintuer.js"></script>
	  <script src="${basePath}/script/form-validator.js"></script>
  </head>
  <body>

  <body>

  <div class="panel admin-panel" >
	  <div class="panel-head"><strong><span class="icon-key"></span> 修改会员密码</strong></div>
<form id="updateForm" method="post" class="form-x" >
	<div class="form-group">
		<div class="label">
			<label >用户名：</label>
		</div>
		<div class="field">
			<label style="line-height:33px;">
				${user.username}
			</label>
		</div>
	</div>
	<div class="form-group">
		<div class="label">
			<label>当前密码：</label>
		</div>
		<div class="field">
			<input type="password" class="input w50" id="password" name="password" require="true"  />
		</div>
	</div>

	<div class="form-group">
		<div class="label">
			<label >新密码：</label>
		</div>
		<div class="field">
			<input class="input w50" id="Password1" type="password" name="newPassword"   require="true" dataType="Custom" regexp="^[a-zA-Z0-9_\.]{5,20}$" msg="密码由字母和数字_.组成，长度在5-16" />
		</div>
	</div>

	<div class="form-group">
		<div class="label">
			<label >确认新密码：</label>
		</div>
		<div class="field">
			<input class="input w50" id="Repeat1" dataType="Repeat" to="Password1" msg="两次输入的密码不一致" type="password" />
		</div>
	</div>

	<div class="form-group">
		<div class="label">
			<label></label>
		</div>
		<div class="field">
			<button class="button bg-main"  onclick="javascript:updatePwd();" type="button">修改</button>
			<a href="${basePath}jsp/centerIndex.jsp" class="button bg-main">首页</a>
		</div>
	</div>

</form>
</div>
</body>
<script type="text/javascript">
	function updatePwd() {
		if(FormValidator.Validate(updateForm,3)) {
		  $.ajax({
              url:"${basePath }cms/user/updatePwd.action",
              data:$('#updateForm').serialize(),
              type:"POST",
              success: function(data){
                  var dateArr = eval(data);    
            	 	if(dateArr[0].success =="true"){
                        alert(dateArr[0].msg);   
            		  	location.href= "${basePath}jsp/centerIndex.jsp";
                          }else{
                       alert(dateArr[0].msg);
         				}
                  },
             erro: function(){
          	   alert("服务器出错请稍后再试!");
          	   
             }
          });
		}
		
	}
</script>
</html>