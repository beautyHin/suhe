<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cms.repair.bean.RepairBean" %>
<%@page import="com.cms.building.dao.IBuildingDao" %>
<%@page import="com.cms.building.dao.impl.BuildingDao" %>
<%@page import="com.cms.building.bean.BuildingBean"%>
<%@page import="java.util.List"%>
<%@ page import="com.framework.common.BuildingCache" %>
<%
RepairBean model = (RepairBean)request.getAttribute("model");

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
		<label>报修内容：</label>
	</div>
	<div class="field">
		<textarea name="repairContent" class="input w50"  dataType="Limit" min="0" max="200" maxlength="200">${model.repairContent }</textarea>
		<p>最多可输入200个字符</p>
	</div>
</div>
<div class="form-group">
	<div class="label">
		<label>报修人电话：</label>
	</div>
	<div class="field">
		<input type="text" name="phone" class="input w50" dataType="Mobile"  require="true" value="${model.phone }">
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