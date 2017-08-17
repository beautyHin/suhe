<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="com.cms.building.dao.IBuildingDao" %>
<%@page import="com.cms.building.dao.impl.BuildingDao" %>
<%@page import="com.cms.building.bean.BuildingBean"%>
<%@page import="java.util.*" %>
<%@page import="com.cms.teacher.bean.TeacherBean" %>
<%@ page import="com.framework.common.BuildingCache" %>
<%
	TeacherBean model = (TeacherBean)request.getAttribute("model");
%>

<div class="form-group">
	<div class="label">
		<label>姓名:</label>
	</div>
	<div class="field">
		<input type="text" name="teacherName"  class="input w50"   value="${model.teacherName }" require="true" dataType="Limit,Chinese"  min="2" max="6">
	</div>
</div>


<div class="form-group">
	<div class="label">
		<label>性别：</label>
	</div>
	<div class="field">
		<div class="button-group radio">

			<label class="button active">
				<span class="icon icon-check"></span>
				<input name="sex" value="0" type="radio" checked="checked">女
			</label>

			<label class="button ">
				<span class="icon icon-check"></span>
				<input name="sex" value="1"  type="radio" >男
			</label>
		</div>
	</div>
</div>
<div class="form-group">
	<div class="label">
		<label>联系电话：</label>
	</div>
	<div class="field">
		<input type="text" name="phone" class="input w50" dataType="Mobile"  require="true" value="${model.phone }">
	</div>
</div>


<div class="form-group">
	<div class="label">
		<label>负责楼宇:</label>
	</div>

	<div class="field">
		<select id="buildingId-select" name="manageBuiId" require="true" class="input w50" onchange="javascript:gradeChange();">
			<option value="${model.manageBuiId}">
				<%= BuildingCache.getBuildingName(model.getManageBuiId()) == null ? "": BuildingCache.getBuildingName(model.getManageBuiId()) %>
			</option>
			<%
				IBuildingDao buildingDao = new BuildingDao();
				List<BuildingBean> buildList = buildingDao.findList();
				if(buildList != null) {
					for(int i=0; i<buildList.size(); ++i) {
						BuildingBean buildBean = buildList.get(i);
						if(buildBean.getBuildingId() == model.getManageBuiId()) {
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
