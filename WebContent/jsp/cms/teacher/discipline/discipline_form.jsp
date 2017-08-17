<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cms.discipline.bean.DisciplineBean" %>
<%@page import="com.cms.building.dao.IBuildingDao" %>
<%@page import="com.cms.building.dao.impl.BuildingDao" %>
<%@page import="com.cms.building.bean.BuildingBean" %>
<%@page import="java.util.List" %>
<%@ page import="com.framework.common.BuildingCache" %>
<%
    DisciplineBean model = (DisciplineBean) request.getAttribute("model");

%>
<script type="text/javascript" src="${basePath }My97DatePicker/WdatePicker.js"></script>

<div class="form-group">
    <div class="label">
        <label>楼宇编号:</label>
    </div>

    <div class="field">
        <select id="buildingId-select" name="buildingId" require="true" class="input w50" onchange="javascript:gradeChange();">
            <option value="${model.buildingId}">
                <%= BuildingCache.getBuildingName(model.getBuildingId()) == null ? "": BuildingCache.getBuildingName(model.getBuildingId()) %>
            </option>
            <%
                IBuildingDao buildingDao = new BuildingDao();
                List<BuildingBean> buildList = buildingDao.findList();
                if(buildList != null) {
                    for(int i=0; i<buildList.size(); ++i) {
                        BuildingBean buildBean = buildList.get(i);
                        if(buildBean.getBuildingId() == model.getBuildingId()) {
                            continue;
                        }

            %>
            <option value="<%=buildBean.getBuildingId()%>" ><%=buildBean.getBuildingName() %></option>
            <%
                    }}
            %>
        </select>
    </div>
</div>


<div class="form-group">
    <div class="label">
        <label>宿舍号:</label>
    </div>
    <div class="field">
        <select id="domitoryId-select" class="input w50" name="domitoryId" require="true">
        <c:choose>
            <c:when test="${model.domitoryId != null}">
                <option value="${model.domitoryId}">${model.domitoryCode}</option>
            </c:when>

            <c:otherwise>
                <option value=""></option>
            </c:otherwise>
        </c:choose>
        </select>
    </div>
</div>

<div class="form-group">
    <div class="label">
        <label>违规内容：</label>
    </div>
    <div class="field">
        <textarea name="disciplineContent" class="input w50" dataType="Limit" min="0" max="200"
                  maxlength="200">${model.disciplineContent }</textarea>
        <p>最多可输入200个字符</p>
    </div>
</div>

<div class="form-group">
    <div class="label">
        <label>违规时间：</label>
    </div>
    <div class="field">
        <input id="d12" onClick="WdatePicker()" class="input w50" type="text" name="disciplineDate" id="disciplineDate" require="true"
               value="${model.disciplineDate }"/>
    </div>
</div>

<div class="form-group">
    <div class="label">
        <label>是否通报批评：</label>
    </div>
    <div class="field">
        <div class="button-group radio">

            <label class="button active">
                <span class="icon icon-check"></span>
                <input type="radio" require="true" name="isCriticize" value="0" checked="true">否
            </label>

            <label class="button ">
                <span class="icon icon-check"></span>
                <input type="radio" require="true" name="isCriticize" value="1">是
            </label>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(document).ready( function () {
        if(${model.domitoryId != null}) {
            getDomitory(${model.buildingId});
        }
    });

    function appendDomitory(data) {
        var domitoryArr = eval(data);
        console.log(domitoryArr);
        for (var i = 0; i < domitoryArr.length; ++i) {
            if(${model.domitoryId != null}) {
                if(domitoryArr[i].domitoryId != <%=model.getDomitoryId()%>) {
                    $("#domitoryId-select").append("<option value=" + domitoryArr[i].domitoryId + ">" + domitoryArr[i].domitoryCode + "</option>");
                }
            } else {
                $("#domitoryId-select").append("<option value=" + domitoryArr[i].domitoryId + ">" + domitoryArr[i].domitoryCode + "</option>");
            }

        }
    }
    /*根据楼宇获取宿舍*/
    function getDomitory(buildingId) {
        $.ajax({
            url: "${basePath }cms/teacher/domitory/Domitory/ajaxGetDomitorys.action",
            data: {"buildingId": buildingId},
            type: "POST",
            success: function (data) {
                appendDomitory(data);
            },
            erro: function () {
                alert("服务器出错请稍后再试!");

            }
        });
    }

    function gradeChange() {
        $("#domitoryId-select").empty();
        var objS = document.getElementById("buildingId-select");
        var buildingId = objS.options[objS.selectedIndex].value;
        if (buildingId != "") {
            getDomitory(buildingId);
        }
    }
</script>
