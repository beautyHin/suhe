<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/jsp/checkLogin.jsp" %>
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1>后台管理中心</h1>
    </div>
    <div class="head-l">
        <a href="javascript:;" class="button button-little bg-blue" onclick="location.href='${basePath}cms/user/toUpdatePwd.action'"><span class="icon-wrench"></span> 修改密码</a> &nbsp;&nbsp;
        <a href="javascript:;" class="button button-little bg-red" onclick="location.href='${basePath}cms/user/userOut.action'"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
