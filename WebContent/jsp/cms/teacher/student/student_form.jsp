<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.cms.building.dao.IBuildingDao" %>
<%@page import="com.cms.building.dao.impl.BuildingDao" %>
<%@page import="com.cms.building.bean.BuildingBean"%>
<%@page import="java.util.*"%>

<div class="form-group">
	<div class="label">
		<label>学号:</label>
	</div>
	<div class="field">
		<input type="text" name="stuCode" class="input w50"  dataType="Limit" min="1" max="20" require="true"/>
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>姓名:</label>
	</div>
	<div class="field">
		<input type="text" name="name"  class="input w50"  require="true" dataType="Limit,Chinese"  min="2" max="6">
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
		<label>班级:</label>
	</div>
	<div class="field">
		<input type="text" name="className" class="input w50" require="true" min="1" max="20">
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>楼宇编号:</label>
	</div>

	<div class="field">
		 <select id="buildingId-select" name="buildingId" require="true" class="input w50" onchange="javascript:gradeChange();">
		<option value=""></option>
		<%
			IBuildingDao buildingDao = new BuildingDao();
			List<BuildingBean> buildList = buildingDao.findList();
			if(buildList != null) {
				for(int i=0; i<buildList.size(); ++i) {
					BuildingBean buildBean = buildList.get(i);

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
		<select id="domitoryId-select"  class="input w50" name="domitoryId" require="true">
		</select>
	</div>
</div>


<div class="form-group">
	<div class="label">
		<label>学生电话：</label>
	</div>
	<div class="field">
		<input type="text" name="phone" class="input w50" dataType="Mobile"  require="true">
	</div>
</div>
<div class="form-group">
	<div class="label">
		<label>辅导员电话：</label>
	</div>
	<div class="field">
		<input type="text" name="coachTel" class="input w50" dataType="Mobile"  require="true">
	</div>
</div>
