<%@page import="com.framework.common.DateSimple"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.cms.building.model.BuildingQuery" %>
<%@page import="com.cms.building.bean.BuildingBean" %>
<%
	BuildingQuery query = (BuildingQuery)request.getAttribute("query");
	PageBean<BuildingBean> pageBean = query.getPageBean();
	System.out.println();
	List<BuildingBean> buildingList = pageBean.getDatas();

%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>楼宇信息</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
      <link rel="stylesheet" href="${basePath}css/pintuer.css">
      <link rel="stylesheet" href="${basePath}css/admin.css">
      <script src="${basePath}script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
      <script src="${basePath}script/pintuer.js"></script>
  </head>
  <%@include file="/jsp/head.jsp" %>
  <%@include file="/jsp/cms/student/menu.jsp" %>
  <%@include file="/jsp/navigation.jsp" %>

  <div class="admin">
      <body class="no-skin">
      <table>

	<% if(buildingList != null){
			for(int i=0;i<buildingList.size();++i) {
			    BuildingBean  building = buildingList.get(i);
	%>
          <tr>
        <div class="error-container">
            <h1><%=building.getBuildingName() %></h1>
            <div class="errorcon">
                <%=building.getBuildingInfo() %>
            </div>
            <span style="float:right "><%=DateSimple.dateToStr(building.getCreateDate(),"yyyy-MM-dd") %></span>
        </div>
          </tr>
	<% }} else {%>
          <tr>
    <div class="error-container">
        <h1>暂无</h1>
        <div class="errorcon">
            暂无,请等待管理员发布！
        </div>
    </div>
    </tr>

	<%} %>
          <% if(buildingList != null){ %>
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
      </table>

	<form action="${basePath }cms/student/building/BuildingStudent/list.action" id="form-findbuilding" method="post">
		<input id="pageCode" type="hidden" name="pageCode" value="">
	</form>


  </body>
  </div>
  <script type="text/javascript">
  	function toPage(pageCode) {
  		$("#pageCode").attr("value",pageCode);
  		$("#form-findbuilding").submit();
  	}
  
  </script>
  
</html>
