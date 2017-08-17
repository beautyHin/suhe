<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>宿舍管理系统</title>
    <link rel="stylesheet" href="${basePath}css/pintuer.css">
    <link rel="stylesheet" href="${basePath}css/admin.css">
    <script src="${basePath}script/jquery.js"></script>
    <script src="${basePath}script/pintuer.js"></script>
</head>

<body style="background-color:#f2f9fd;">
<%@include file="/jsp/head.jsp"%>
<%@include file="menu.jsp"%>
<ul class="bread">
    <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
</ul>
<div class="admin">
    <h1>欢迎</h1>
</div>
</body>


</html>