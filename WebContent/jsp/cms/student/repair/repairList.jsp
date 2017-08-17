<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cms.repair.bean.RepairBean" %>
<%@page import="com.cms.repair.model.RepairQuery" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.framework.common.PropertiesCommon" %>
<%@page import="java.util.List" %>
<%@ page import="com.framework.common.DateSimple" %>
<%@ page import="com.cms.domitory.bean.DomitoryBean" %>
<%@ page import="com.framework.common.BuildingCache" %>
<%
	RepairQuery query = (RepairQuery)request.getAttribute("query");
	PageBean<RepairBean> pageBean = query.getPageBean();
	List<RepairBean> repairList = pageBean.getDatas();
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>我的报修记录</title>
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
  <%@include file="/jsp/cms/student/menu.jsp" %>
  <%@include file="/jsp/navigation.jsp" %>
  <div class="admin">
      <body class="no-skin">
      <div class="padding border-bottom">
          <ul class="search">
              <li>
                  <button type="button" class="button border-yellow"
                          onclick="window.location.href='${basePath }cms/student/repair/RepairStudent/create.action'">
                      <span class="icon-plus-square-o"></span> 我要报修
                  </button>
              </li>
          </ul>
      </div>
	<% if(repairList != null){
			for(int i=0;i<repairList.size();++i) {	
				RepairBean  repair = repairList.get(i);
	%>
            <div class="error-container">
                <h1>报修单</h1>
                <div class="errorcon">
                    <%=repair.getRepairContent() %>
                </div>
                <div class="errorcon">
                    报修寝室:<%=BuildingCache.getBuildingName(repair.getBuildingId()) %>-<%=repair.getDomitoryCode()%>
                </div>
                <div class="errorcon">
                   联系电话: <%=repair.getPhone() %>
                </div>
                <div class="errorcon">
                    申报日期：<%=DateSimple.dateToStr(repair.getCreateDate(),"yyyy-MM-dd") %>
                </div>

                <h4 style="color: #00aa00">
                    <%=PropertiesCommon.getDictValue("REPAIR.STATUS", ""+repair.getStatus()) %>
                </h4>
                <% if(repair.getStatus() == 0) { %>
                <div class="button-group">
                    <a class="button border-main"
                       href="${basePath }cms/student/repair/RepairStudent/edit.action?repairId=<%=repair.getRepairId()%>">
                        <span class="icon-edit"></span> 修改
                    </a>
                </div>
                <%}%>
            </div>
          <%}} %>

	<form action="${basePath }cms/student/Repair/RepairStudent/list.action" id="form-findRepair" method="post">
		<input id="pageCode" type="hidden" name="pageCode" value="">
	</form>

      <% if(repairList != null){ %>
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







      </body>

</div>
  <script type="text/javascript">
  	function toPage(pageCode) {
  		$("#pageCode").attr("value",pageCode);
  		$("#form-findRepair").submit();
  	}
  
  </script>
  
</html>
