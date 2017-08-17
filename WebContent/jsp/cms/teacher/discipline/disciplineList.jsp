<%@page import="com.framework.common.BuildingCache" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="com.framework.common.PageBean" %>
<%@page import="com.framework.common.DateSimple" %>
<%@page import="com.framework.common.PropertiesCommon" %>
<%@page import="com.cms.discipline.model.DisciplineQuery" %>
<%@page import="com.cms.discipline.bean.DisciplineBean" %>
<%
    DisciplineQuery query = (DisciplineQuery) request.getAttribute("query");
    PageBean<DisciplineBean> pageBean = query.getPageBean();
    List<DisciplineBean> disciplineList = pageBean.getDatas();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>宿舍违规管理</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${basePath}css/pintuer.css">
    <link rel="stylesheet" href="${basePath}css/admin.css">
    <script src="${basePath}script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
    <script src="${basePath }script/nodecheck.js"></script>
    <script src="${basePath}script/pintuer.js"></script>
</head>
<%@include file="/jsp/head.jsp" %>
<%@include file="/jsp/cms/teacher/menu.jsp" %>
<%@include file="/jsp/navigation.jsp" %>
<body>
<div class="admin">
    <%@include file="/jsp/msg.jsp" %>
    <div class="panel-head"><strong class="icon-reorder"> 宿舍违规列表</strong>
                <form action="${basePath }cms/teacher/discipline/Discipline/deleteBatch.action" method="post"
                      id="DisciplineListForm">
                    <div class="padding border-bottom">
                        <ul class="search">
                            <li>
                                <button type="button" class="button border-yellow"
                                        onclick="window.location.href='${basePath}cms/teacher/discipline/Discipline/toDisciplineInsert.action'">
                                    <span class="icon-plus-square-o"></span> 添加
                                </button>
                            </li>
                            <li><a href="javascript:void(0);"
                                   onclick="javascript:batch('items','是否执行【删除】操作','DisciplineListForm')"
                                   class=" button border-red"><span class=" icon-trash-o">删除</span></a></li>
                        </ul>

                    </div>


                    <table class="table table-hover text-center">
                    <thead>
                    <tr>
                        <th style="width:45px;"><input id="items_Hander" type="checkbox"
                                                       onclick="setAllCheckboxState('items',this.checked)"></th>
                        <th></th>
                        <th>楼宇</th>
                        <th>宿舍号</th>
                        <th>违规内容</th>
                        <th>违规时间</th>
                        <th>通报批评</th>
                        <!-- <th style="width: 26px;"></th> -->
                    </tr>
                    </thead>
                    <tbody>
                    <% if (disciplineList != null)
                        for (int i = 0; i < disciplineList.size(); ++i) {
                            DisciplineBean Discipline = disciplineList.get(i);

                    %>
                    <tr>
                        <td><input type="checkbox" name="items" value="<%=Discipline.getDisciplineId() %>"
                                   onclick="doCheckedItem(this)"></td>
                        <td><%=i + 1%>
                        </td>
                        <td><%=BuildingCache.getBuildingName(Discipline.getBuildingId())%>
                        </td>
                        <td><%=Discipline.getDomitoryCode()%>
                        </td>
                        <td><%=Discipline.getDisciplineContent()%>
                        </td>
                        <td><%=DateSimple.dateToStr(Discipline.getDisciplineDate(),"yyyy-MM-dd")%>
                        </td>
                        <td><%=PropertiesCommon.getDictValue("DISCIPLINE.ISCRITICIZE", "" + Discipline.getIsCriticize()) %>
                        </td>
                        <td>
                            <div class="button-group">
                                <a class="button border-main"
                                   href="${basePath}cms/teacher/discipline/Discipline/edit.action?DisciplineId=<%=Discipline.getDisciplineId()%>">
                                    <span class="icon-edit"></span> 修改
                                </a>
                            </div>
                        </td>
                    </tr>
                    <%

                            }
                    %>
                    <% if(disciplineList != null){ %>

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
</div>
</body>
<script type="text/javascript">
    function toPage(pageCode) {
        $("#pageCode").attr("value", pageCode);
        $("#form-findDiscipline").submit();
    }

</script>

</html>

