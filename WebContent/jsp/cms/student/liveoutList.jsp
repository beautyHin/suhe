<%@page import="com.framework.common.DateSimple" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.cms.liveout.model.LiveOutQuery" %>
<%@page import="com.cms.liveout.bean.LiveOutBean" %>
<%
    LiveOutQuery query = (LiveOutQuery) request.getAttribute("query");
    PageBean<LiveOutBean> pageBean = query.getPageBean();
    List<LiveOutBean> liveOutList = pageBean.getDatas();

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>旷寝记录</title>
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
        <% if (liveOutList != null) {
                for (int i = 0; i < liveOutList.size(); ++i) {
                    LiveOutBean liveOut = liveOutList.get(i);
         %>
            <tr>
        <div class="error-container">
            <h1></h1>
            <div class="errorcon">
                <%=liveOut.getLiveoutContent() %>
            </div>
            <span style="float:right;color:red; ">旷寝日期：<%=DateSimple.dateToStr(liveOut.getLiveoutDate(),"yyyy-MM-dd") %></span>
        </div>
            </tr>
            <% }
            } else {%>
            <tr>
        <div class="error-container">
            <h1>暂无 </h1>
            <div class="errorcon">
                表现很好，暂无旷寝记录！
            </div>
        </div>
            </tr>
            <%} %>

            <% if(liveOutList != null){ %>

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
<form id="form-findLiveOut" action="${basePath }cms/student/liveout/LiveOutStudent/list.action" method="post">
    <input id="pageCode" type="hidden" name="pageCode" value="">
</form>
</body>
</div>
<script type="text/javascript">
    function toPage(pageCode) {
        $("#pageCode").attr("value", pageCode);
        $("#form-findLiveOut").submit();
    }
</script>

</html>
