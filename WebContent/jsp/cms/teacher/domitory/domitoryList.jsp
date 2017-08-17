<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.framework.common.BuildingCache" %>
<%@page import="com.framework.common.PropertiesCommon" %>
<%@page import="com.cms.domitory.model.DomitoryQuery" %>
<%@page import="com.cms.domitory.bean.DomitoryBean" %>
<%
	DomitoryQuery query = (DomitoryQuery)request.getAttribute("query");
	PageBean<DomitoryBean> pageBean = query.getPageBean();
	List<DomitoryBean> domitoryList = pageBean.getDatas();

%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>宿舍列表</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
      <link rel="stylesheet" href="${basePath}css/pintuer.css">
      <link rel="stylesheet" href="${basePath}css/admin.css">
      <script src="${basePath }script/nodecheck.js"></script>
      <script src="${basePath}script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
      <script src="${basePath}script/pintuer.js"></script>
  </head>
  <%@include file="/jsp/head.jsp" %>
  <%@include file="/jsp/cms/teacher/menu.jsp" %>
  <%@include file="/jsp/navigation.jsp" %>
  <body>
  <div class="admin">
      <%@include file="/jsp/msg.jsp" %>
      <div class="panel admin-panel">
      <form id="form-findDomitory" class="form-inline" action="${basePath}cms/teacher/domitory/Domitory/list.action" method="post">
                  	<input id="pageCode" type="hidden" name="pageCode" value="" >
          <div class="padding border-bottom">
              <ul class="search" style="padding-left:10px;">
                  <li>宿舍名:<input type="text" name="domitoryCode" value="${query.domitoryCode }" class="input list-input"
                                style="width:100px; line-height:17px;display:inline-block"/></li>
                  <li>
                      <button typr="submit" class="button border-main icon-search">搜索</button>
                  </li>


              </ul>
          </div>
                  	</form>

          <div class="panel-head"><strong class="icon-reorder"> 宿舍列表</strong>
<form action="${basePath }cms/teacher/domitory/Domitory/deleteBatch.action" method="post" id="DomitoryListForm">
    <div class="padding border-bottom">
        <ul class="search">
            <li>
                <button type="button" class="button border-yellow"
                        onclick="window.location.href='${basePath}cms/teacher/domitory/Domitory/create.action'">
                    <span class="icon-plus-square-o"></span> 添加
                </button>
            </li>
            <li><a href="javascript:void(0);"
                   onclick="javascript:batch('items','是否执行【删除】操作','DomitoryListForm')"
                   class=" button border-red"><span class=" icon-trash-o">删除</span></a></li>
        </ul>

    </div>


    <table class="table table-hover text-center">
      <thead>
        <tr>
         <th style="width:45px;"><input id="items_Hander" type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
          <th></th>
          <th>楼宇编码</th>
          <th>宿舍编码</th>
          <th>宿舍类型</th>
          <th>宿舍状态</th>
          <th>操作</th>
          <!-- <th style="width: 26px;"></th> -->
        </tr>
      </thead>
      <tbody>
      	 <% if(domitoryList != null) 
      	   for(int i=0; i<domitoryList.size();++i) {
      		   DomitoryBean domitory = domitoryList.get(i);
      	%> 
      	<tr>
      	  <td><input type="checkbox" name="items" value="<%=domitory.getDomitoryId() %>" onclick="doCheckedItem(this)"></td>
          <td><%=i+1%></td>
          <td><%=BuildingCache.getBuildingName(domitory.getBuildingId())%></td>
          <td><%=domitory.getDomitoryCode() %></td>
          <td><%=PropertiesCommon.getDictValue("DOMITORY.DOMITORYTYPE",""+domitory.getDomitoryType())%></td>
          <td><%=PropertiesCommon.getDictValue("DOMITORY.DOMITORYSTATUS",""+domitory.getDomitoryStatus())%></td>
          <td>
              <div class="button-group">
                  <a class="button border-main"
                     href="${basePath}cms/teacher/domitory/Domitory/edit.action?domitoryId=<%=domitory.getDomitoryId()%>">
                      <span class="icon-edit"></span> 修改
                  </a>
              </div>
        </tr>
      	<%	   
      		   
      	   } 
      	%>
         <% if(domitoryList != null){ %>

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
</form>

</div>
</div>
</div>
  </body>
  <script type="text/javascript">
  	function toPage(pageCode) {
  		$("#pageCode").attr("value",pageCode);
  		$("#form-findDomitory").submit();
  	}
  
  </script>
  
</html>

