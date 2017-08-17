<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${basePath}css/pintuer.css">
    <link rel="stylesheet" href="${basePath}css/admin.css">
    <script src="${basePath}script/jquery.js"></script>
    <script src="${basePath}script/pintuer.js"></script>
    <script src="${basePath}/script/form-validator.js"></script>

</head>

<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form id="signinForm" action="javascript:login()" onsubmit="return FormValidator.Validate(this,3)">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>登录</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" id="username" name="username"
                                       require="true"/>
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" id="password" name="password"
                                       require="true"/>
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                    </div>
                    <div style="padding:30px;"><input type="submit"
                                                      class="button button-block bg-main text-big input-big" value="登录">
                    </div>
                    <p style="float: right;"><a href="${basePath }jsp/signup.jsp">立即注册</a></p>
                </div>
            </form>

        </div>
    </div>

</div>


</body>

<script type="text/javascript">
    function login() {
        $.ajax({
            url: "${basePath }cms/user/login.action",
            data: $('#signinForm').serialize(),
            type: "POST",
            success: function (data) {
                if (data.trim() == "true") {
                    location.href = "${basePath}jsp/centerIndex.jsp";
                } else {
                    alert("用户名或密码错误");
                }
            },
            erro: function () {
                alert("服务器出错请稍后再试!");

            }
        });
    }
</script>


</html>


