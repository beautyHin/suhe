<%@page import="com.framework.common.BuildingCache" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.cms.repair.bean.RepairBean" %>
<%@page import="com.cms.repair.model.RepairQuery" %>
<%@page import="com.framework.common.PropertiesCommon" %>
<%

    RepairQuery query = (RepairQuery) request.getAttribute("query");
    PageBean<RepairBean> pageBean = query.getPageBean();
    List<RepairBean> repairList = pageBean.getDatas();

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>报修管理</title>
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
    <div class="panel-head"><strong class="icon-reorder"> 报修列表</strong>
        <form id="form-findrepair" action="${basePath }cms/teacher/repair/Repair/list.action" method="post">
        <input id="pageCode" type="hidden" name="pageCode" value="">
            <table class="table table-hover text-center">
                <thead>
                <tr>
                    <th></th>
                    <th>楼宇</th>
                    <th>宿舍</th>
                    <th>联系电话</th>
                    <th>报修内容</th>
                    <th>处理状态</th>
                    <!-- <th style="width: 26px;"></th> -->
                </tr>
                </thead>
                <tbody>
                <% if (repairList != null)
                    for (int i = 0; i < repairList.size(); ++i) {
                        RepairBean repair = repairList.get(i);
                %>
                <tr>
                    <td><%=i + 1%>
                    </td>
                    <td><%=BuildingCache.getBuildingName(repair.getBuildingId()) %>
                    </td>
                    <td><%=repair.getDomitoryCode()%>
                    </td>
                    <td><%=repair.getPhone() %>
                    </td>
                    <td><%=repair.getRepairContent() %>
                    </td>
                    <td>
                        <% if (repair.getStatus() == 0) { //处理中%>
                        <div class="button-group">
                            <a class="button border-main"
                               href="javascript:tabStatus('<%=repair.getRepairId()%>')">
                                <span class="icon-edit"></span> <%=PropertiesCommon.getDictValue("REPAIR.STATUS", "" + repair.getStatus()) %>...
                            </a>
                        </div>
                        <%} else {%>
                        <%=PropertiesCommon.getDictValue("REPAIR.STATUS", "" + repair.getStatus()) %>
                        <%} %></td>
                </tr>
                <%

                        }
                %>

                <% if(repairList != null){ %>

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
        $("#pageCode").attr("value", pageCode);
        $("#form-findrepair").submit();
    }

    function tabStatus(repairId) {
        var url = "${basePath}cms/teacher/repair/Repair/update.action?";
        var params = "repairId=" + repairId + "&status=1";
        if (confirm("确认将维修状态改为【已完成】!")) {
            location.href = url + params;
        } else {
            return;
        }

    }

</script>
</html>
