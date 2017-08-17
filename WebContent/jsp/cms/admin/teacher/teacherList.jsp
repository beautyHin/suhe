<%@page import="com.framework.common.BuildingCache"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.cms.teacher.bean.TeacherBean" %>
<%@page import="com.cms.teacher.model.TeacherQuery" %>
<%@page import="com.framework.common.PropertiesCommon" %>
<%
	
	TeacherQuery query =(TeacherQuery)request.getAttribute("query");
	PageBean<TeacherBean> pageBean = query.getPageBean();
	List<TeacherBean> teacherList = pageBean.getDatas();

%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>宿舍管理员列表</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
      <link rel="stylesheet" href="${basePath}css/pintuer.css">
      <link rel="stylesheet" href="${basePath}css/admin.css">
      <script src="${basePath}script/jquery.js"></script>
      <script src="${basePath}script/pintuer.js"></script>
      <script src="${basePath}/script/form-validator.js"></script>
      <script src="${basePath }script/nodecheck.js"></script>
  </head>
  <%@include file="/jsp/head.jsp"%>
  <%@include file="/jsp/cms/admin/menu.jsp"%>
  <%@include file="/jsp/navigation.jsp"%>

  <body>
  <div class="admin">
      <%@include file="/jsp/msg.jsp"%>
      <div class="panel admin-panel">

    	<form id="form-findteacher" class="form-inline" action="${basePath}cms/admin/teacher/TeacherAdmin/list.action" method="post">
                  	<input id="pageCode" type="hidden" name="pageCode" value="" >
                        <div class="padding border-bottom">
                            <ul class="search" style="padding-left:10px;">
                                <li>姓名:<input type="text"  name="teacherName"  value="${query.teacherName }" class="input list-input" style="width:100px; line-height:17px;display:inline-block" /></li>
                                <li>

                                    <button typr="submit"  class="button border-main icon-search" >搜索</button></li>
                            </ul>
                        </div>

                  	</form>
          <div class="panel-head"><strong class="icon-reorder"> 宿舍管理员列表</strong>

<form id="TeacherDelForm" action="${basePath}cms/admin/teacher/TeacherAdmin/batchDelete.action" method="post">

    <div class="padding border-bottom">
        <ul class="search">
            <li>
                <button type="button" class="button border-yellow"
                        onclick="window.location.href='${basePath}cms/admin/teacher/TeacherAdmin/toTeacherInsert.action'">
                    <span class="icon-plus-square-o"></span> 添加
                </button>
            </li>
            <li><a href="javascript:void(0);"
                   onclick="javascript:batch('items','是否执行【删除】操作','TeacherDelForm')"
                   class=" button border-red"><span class=" icon-trash-o">删除</span></a></li>
        </ul>
    </div>

    <table class="table table-hover text-center">
      <thead>
        <tr>
          <th style="width:45px;"><input id="items_Hander" type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
         	<th></th>
          <th>姓名</th>
          <th>性别</th>
          <th>联系电话</th>
          <th>楼宇</th>
          <th>操作</th>
          <!-- <th style="width: 26px;"></th> -->
        </tr>
      </thead>
      <tbody>
      	<% if(teacherList != null) 
      	   for(int i=0; i<teacherList.size();++i) {
      		   TeacherBean  teacher = teacherList.get(i);
      	%>
      	<tr>
          <td><input type="checkbox" name="items" value="<%=teacher.getTeacherId() %>" onclick="doCheckedItem(this)"></td>
          <td><%=i+1%></td>
          <td><%=teacher.getTeacherName() %></td>
          <td><%= PropertiesCommon.getDictValue("STUDENT.SEX", ""+teacher.getSex()) %></td>
          <td><%=teacher.getPhone() %></td>
          <td><%=BuildingCache.getBuildingName(teacher.getManageBuiId()) %></td>
          <td>
              <div class="button-group">
              <a class="button border-main"
                 href="${basePath }cms/admin/teacher/TeacherAdmin/edit.action?teacherId=<%=teacher.getTeacherId()%>">
                  <span class="icon-edit"></span> 修改
              </a>
          </div>
          </td>
          <td></td>
          <td></td>
        </tr>
      	<%	   
      		   
      	   }
      	%>
      
        
      </tbody>
    </table>
    </form>
</div>
<% if(teacherList != null){ %>
<div class="pagelist">
    <ul>
    	<c:if test="<%=pageBean.getpageCode() != 1 %>"> 
    	
       		<li><a  href="javascript:toPage(1)" >首页</a></li>
        </c:if>
       <c:if test="<%=pageBean.getpageCode() != 1 %>"> 
        	<li><a href="javascript:toPage(<%=pageBean.getpageCode() - 1%>)">上一页</a></li>
       </c:if>
       
       <li><a><%=pageBean.getpageCode()%>/<%=pageBean.getTotalPage() %></a></li>
       
       <c:if test="<%=pageBean.getpageCode() != pageBean.getTotalPage() %>">
        <li><a href="javascript:toPage(<%=pageBean.getpageCode() + 1%>);">下一页</a></li>
       </c:if>
       
       <c:if test="<%=pageBean.getpageCode() != pageBean.getTotalPage() %>">
        <li><a href="javascript:toPage(<%=pageBean.getTotalPage()%>)">尾页</a></li>
        </c:if>
    </ul>
</div>
<%} %>      
</div>
</div>
</div>

  </body>
  <script type="text/javascript">
  	function toPage(pageCode) {
  		$("#pageCode").attr("value",pageCode);
  		$("#form-findteacher").submit();
  	}
  </script>
</html>

