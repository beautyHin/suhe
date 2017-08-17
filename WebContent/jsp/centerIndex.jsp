<%@page import="com.cms.user.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.cms.user.bean.UserBean"%>
<% UserBean user = (UserBean)session.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% if(user == null) {%>	
<meta http-equiv="refresh" content="0; url=${basePath }jsp/signin.jsp">
<% } else {%>
<% if(user.getRole().equals(UserBean.USER_SHUSE_ADMIN)) {%>
<meta http-equiv="refresh" content="0; url=${basePath }jsp/cms/teacher/index.jsp">
<%} %>
<%if(user.getRole().equals(UserBean.USER_STUDENT)){ %>
<meta http-equiv="refresh" content="0; url=${basePath }jsp/cms/student/index.jsp">
<%} %>
<%if(user.getRole().equals(UserBean.USER_ADMIN)){ %>
<meta http-equiv="refresh" content="0; url=${basePath }jsp/cms/admin/index.jsp">
<%} }%>
</head>
<body>
</body>
</html>

