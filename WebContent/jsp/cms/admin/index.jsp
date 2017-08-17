<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>宿舍管理系统</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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
      <li><a href="##" id="a_leader_txt">网站信息</a></li>
  </ul>
  </body>
</html>


