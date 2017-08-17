<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.cms.stu.bean.StudentBean" %>
<%@page import="com.cms.stu.model.StudentQuery" %>
<%@page import="com.framework.common.PropertiesCommon" %>
<%
	StudentQuery query =(StudentQuery)request.getAttribute("query");
	PageBean<StudentBean> pageBean = query.getPegeBean();
	List<StudentBean> stuList = pageBean.getDatas();
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>学生列表</title>
    <link rel="stylesheet" href="${basePath}css/pintuer.css">
    <link rel="stylesheet" href="${basePath}css/admin.css">
    <script src="${basePath}script/jquery.js"></script>
    <script src="${basePath}script/pintuer.js"></script>
</head>
<body>
<%@include file="/jsp/head.jsp"%>
<%@include file="/jsp/cms/teacher/menu.jsp"%>

<div class="admin">
    <%@include file="/jsp/msg.jsp"%>
    <div class="panel admin-panel">

    <form method="post"  action="${basePath}cms/teacher/stu/Student/studentList.action" id="listform">
    <input id="pageCode" type="hidden" name="pageCode" value="" >
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li>姓名:<input type="text"  name="name"  value="${query.name }" class="input list-input" style="width:100px; line-height:17px;display:inline-block" /></li>
                <li>学号:<input type="text"  name="stuCode"  value="${query.stuCode }" class="input list-input" style="width:100px; line-height:17px;display:inline-block" /></li>
                <li>班级:<input type="text"  name="className"  value="${query.className }" class="input list-input" style="width:100px; line-height:17px;display:inline-block" /></li>
                <li>

                    <button typr="submit"  class="button border-main icon-search" >搜索</button></li>
            </ul>
        </div>
</form>

<div class="panel-head"><strong class="icon-reorder"> 学生列表</strong>
    <table class="table table-hover text-center">
    <thead>
    <tr>
        <th></th>
        <th>姓名</th>
        <th>学号</th>
        <th>班级</th>
        <th>性别</th>
        <th>居住状态</th>
        <th>楼宇</th>
        <th>宿舍号</th>
    </tr>
    </thead>
    <tbody>
    <% if(stuList != null)
        for(int i=0; i<stuList.size();++i) {
            StudentBean stu = stuList.get(i);
    %>
    <tr>
        <td><%=i+1%></td>
        <td><%=stu.getName() %></td>
        <td><%=stu.getStuCode() %></td>
        <td><%=stu.getClassName() %></td>
        <td><%= PropertiesCommon.getDictValue("STUDENT.SEX", ""+stu.getSex()) %></td>
        <td><%= PropertiesCommon.getDictValue("STUDENT.ISSTAY", ""+stu.getIsStay()) %></td>
        <td><%=stu.getBuildingName() %></td>
        <td><%=stu.getDomitoryCode() %></td>
        <td></td>
        <td></td>
    </tr>
    <%

            }
    %>

<% if(stuList != null){ %>

    <tr>
        <td colspan="8">
            <div class="pagelist">
                <c:if test="<%=pageBean.getpageCode() != 1 %>">
                    <a  href="javascript:toPage(1)" >首页</a>
                </c:if>

                <c:if test="<%=pageBean.getpageCode() != 1 %>">
                    <a href="javascript:toPage(<%=pageBean.getpageCode() - 1%>)">上一页</a>
                </c:if>
                <a><%=pageBean.getpageCode()%>/<%=pageBean.getTotalPage() %></a>

                <c:if test="<%=pageBean.getpageCode() != pageBean.getTotalPage() %>">
                    <a href="javascript:toPage(<%=pageBean.getpageCode() + 1%>);">下一页</a>
                </c:if>

                <c:if test="<%=pageBean.getpageCode() != pageBean.getTotalPage() %>">
                    <a href="javascript:toPage(<%=pageBean.getTotalPage()%>)">尾页</a>
                </c:if>
            </div>
    </tr>
    <%}%>
    </tbody>
    </table>

</div>
    </div>
</body>
<script type="text/javascript">

    function toPage(pageCode) {
        $("#pageCode").attr("value",pageCode);
        $("#listform").submit();
    }
</script>

</html>
