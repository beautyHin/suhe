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
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>学生迁出记录</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${basePath}css/pintuer.css">
    <link rel="stylesheet" href="${basePath}css/admin.css">
	<script src="${basePath}script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
    <script src="${basePath}script/pintuer.js"></script>
    <script src="${basePath}/script/form-validator.js"></script>
    <script src="${basePath }script/nodecheck.js"></script>

  </head>
  <%@include file="/jsp/head.jsp"%>
  <%@include file="/jsp/cms/teacher/menu.jsp"%>
  <%@include file="/jsp/navigation.jsp"%>

  <body>

  <div class="admin">
      <%@include file="/jsp/msg.jsp"%>
      <div class="panel admin-panel">
            <form id="form-findStu" class="form-inline" action="${basePath}cms/teacher/stu/Student/studentExitList.action" method="post">
            <input id="pageCode" type="hidden" name="pageCode" value="" >
                    <div class="padding border-bottom">
                        <ul class="search" style="padding-left:10px;">
                            <li>姓名:<input type="text"  name="name"  value="${query.name }" class="input list-input" style="width:100px; line-height:17px;display:inline-block" /></li>
                            <li>学号:<input type="text"  name="stuCode"  value="${query.stuCode }" class="input list-input" style="width:100px; line-height:17px;display:inline-block" /></li>
                            <li>

                                <button typr="submit"  class="button border-main icon-search" >搜索</button></li>
                        </ul>
                    </div>
            </form>
          <div class="panel-head"><strong class="icon-reorder"> 学生列表</strong>

<form action="${basePath }cms/teacher/stu/Student/batchStudentExit.action" method="post" id="studentExitForm">

    <div class="padding border-bottom">
        <ul class="search">
            <li>
                <a href="javascript:void(0);" onclick="javaScript:batch('items','是否执行【迁出】操作','studentExitForm')" class=" button border-red" ><span class=" icon-trash-o" >批量迁出</span></a>
            </li>
        </ul>
    </div>
    <table class="table table-hover text-center">
      <thead>
        <tr>
          <th style="width:45px;"><input id="items_Hander" type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
          <th>姓名</th>
          <th>学号</th>
          <th>班级</th>
          <th>性别</th>
          <th>居住状态</th>
          <th>楼宇</th>
          <th>宿舍号</th>
          <!-- <th style="width: 26px;"></th> -->
        </tr>
      </thead>
    <tbody>
      <% if(stuList != null){
    	  	for(int i=0; i<stuList.size(); ++i) {
    	  		StudentBean student = stuList.get(i);
    %>
    	  	
    	 <tr>
	          <td><input type="checkbox" name="items" value="<%=student.getStuCode() %>" onclick="doCheckedItem(this)"></td>
	          <td><%=student.getName() %></td>
	          <td><%=student.getStuCode() %></td>
	          <td><%=student.getClassName() %></td>
	          <td><%=PropertiesCommon.getDictValue("STUDENT.SEX", ""+student.getSex()) %></td>
	          <td><%=PropertiesCommon.getDictValue("STUDENT.ISSTAY",""+student.getIsStay()) %></td>
	          <td><%=student.getBuildingName()%></td>
	          <td><%=student.getDomitoryCode()%></td>
	          <td></td>
        </tr> 
    	  <% 	
    	  	}	
    	 }
    	  %>
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

      </tbody> 
    </table>
    
      </form>   
</div>
  </body>
  <script type="text/javascript">
  	function toPage(pageCode) {
  		$("#pageCode").attr("value",pageCode);
  		$("#form-findStu").submit();
  	}

  </script>
  
</html>