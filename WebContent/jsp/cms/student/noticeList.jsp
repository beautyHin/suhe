<%@page import="com.framework.common.DateSimple"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.cms.notice.model.NoticeQuery" %>
<%@page import="com.cms.notice.bean.NoticeBean" %>
<%
	NoticeQuery query = (NoticeQuery)request.getAttribute("query");
	PageBean<NoticeBean> pageBean = query.getPageBean();
	List<NoticeBean> noticeList = pageBean.getDatas();

%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>宿舍公告</title>
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
        <table>
          <% if(noticeList != null){
			for(int i=0;i<noticeList.size();++i) {
				NoticeBean  notice = noticeList.get(i);
	    %>
            <tr>
      <div class="error-container">
          <h1><%=notice.getNoticeName() %> </h1>
          <div class="errorcon">
              <%=notice.getNoticeInfo() %>
          </div>
          <span style="float:right"><%=DateSimple.dateToStr(notice.getCreateDate(),"yyyy-MM-dd") %></span>


      </div>
            </tr>
              <% }} else {%>
            <tr>
            <div class="error-container">
              <h1>暂无</h1>
              <div class="errorcon">
                  暂无公告,请等待管理员发布！
              </div>

          </div>
            </tr>
              <%} %>

            <% if(noticeList != null){ %>

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
	<form id="form-findnotice" action="${basePath }cms/student/notice/NoticeStudent/list.action" method="post">
		<input id="pageCode" type="hidden" name="pageCode" value="">
	</form>
  </body>
  </div>
  <script type="text/javascript">
  	function toPage(pageCode) {
  		$("#pageCode").attr("value",pageCode);
  		$("#form-findnotice").submit();
  	}
  </script>
  
</html>
