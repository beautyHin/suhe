<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="com.cms.building.dao.IBuildingDao" %>
<%@page import="com.cms.building.dao.impl.BuildingDao" %>
<%@page import="com.cms.building.bean.BuildingBean"%>
<%@page import="java.util.*" %>
<%@page import="com.cms.stu.bean.StudentBean" %>
<%@ page import="com.framework.common.BuildingCache" %>
<%
	StudentBean model = (StudentBean)request.getAttribute("model");
%>
<div class="form-group">
	<div class="label">
		<label>学号:</label>
	</div>
	<div class="field">
		<input type="text" name="stuCode" class="input w50"  dataType="Limit" min="1" max="20" require="true" value="${model.stuCode}"/>
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>姓名:</label>
	</div>
	<div class="field">
		<input type="text" name="name"  class="input w50"  require="true" dataType="Limit,Chinese"  min="2" max="6" value="${model.name}">
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>性别：</label>
	</div>
	<div class="field">
		<div class="button-group radio">

			<label class="button ${model.sex == null ? 'active':''} ${model.sex == 0 ? 'active':''}">
				<span class="icon icon-check"></span>
				<input  name="sex" value="0" type="radio"  checked="checked">女
			</label>

			<label class="button ${model.sex == 1 ? 'active' : ''} ">
				<span class="icon icon-check"></span>
				<input  name="sex" value="1"  type="radio" >男
			</label>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>班级:</label>
	</div>
	<div class="field">
		<input type="text" name="className" class="input w50" require="true" min="1" max="20" value="${model.className}">
	</div>
</div>

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
		<label>学生电话：</label>
	</div>
	<div class="field">
		<input type="text" name="phone" class="input w50" dataType="Mobile"  require="true" value="${model.phone}">
	</div>
</div>
<div class="form-group">
	<div class="label">
		<label>辅导员电话：</label>
	</div>
	<div class="field">
		<input type="text" name="coachTel" class="input w50" dataType="Mobile"  require="true" value="${model.coachTel}">
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
