<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.cms.building.dao.IBuildingDao" %>
<%@page import="com.cms.building.dao.impl.BuildingDao" %>
<%@page import="com.cms.building.bean.BuildingBean"%>
<%@page import="java.util.List"%>
<%@ page import="com.cms.domitory.bean.DomitoryBean" %>
<%@ page import="com.framework.common.BuildingCache" %>
<% DomitoryBean model = (DomitoryBean) request.getAttribute("model");%>
 <input type="hidden" name="domitoryId" value="${model.domitoryId }">

<div class="form-group">
	<div class="label">
		<label>楼宇编号:</label>
	</div>

	<div class="field">
		<select id="buildingId-select" name="buildingId" require="true" class="input w50" onchange="javascript:gradeChange();">
			<option value="${model.buildingId}">
				<%= BuildingCache.getBuildingName(model.getBuildingId()) == null ? "":BuildingCache.getBuildingName(model.getBuildingId()) %>
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
		<label>宿舍编号:</label>
	</div>
	<div class="field">
		<input  type="text" name="domitoryCode"  class="input w50" require="true"  value="${model.domitoryCode }"/>
	</div>
</div>


<div class="form-group">
	<div class="label">
		<label>宿舍状态：</label>
	</div>
	<div class="field">
		<div class="button-group radio">

			<label class="button ${model.domitoryType == null ? 'active':''} ${model.domitoryType == 4 ? 'active' : ''}">
				<span class="icon icon-check"></span>
				<input name="domitoryType" value="4" type="radio" checked="checked">四人寝
			</label>

			<label class="button ${model.domitoryType == 6 ? 'active' : ''}">
				<span class="icon icon-check"></span>
				<input name="domitoryType" value="6"  type="radio" >六人寝
			</label>
		</div>
	</div>
</div>


<div class="form-group">
	<div class="label">
		<label>宿舍状态：</label>
	</div>
	<div class="field">
		<div class="button-group radio">

			<label class="button ${model.domitoryStatus == null ? 'active':''} ${model.domitoryStatus == 0 ? 'active' : ''}">
				<span class="icon icon-check"></span>
				<input name="domitoryStatus" value="0" type="radio" checked="checked">空缺
			</label>

			<label class="button  ${model.domitoryStatus == 1 ? 'active' : ''}">
				<span class="icon icon-check"></span>
				<input name="domitoryStatus" value="1"  type="radio" >住满
			</label>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>公共财产：</label>
	</div>
	<div class="field">
		<textarea name="domitoryAsset" class="input w50"  dataType="Limit" min="0" max="200" maxlength="200">${model.domitoryAsset }</textarea>
		<p>最多可输入200个字符</p>
	</div>
</div>


