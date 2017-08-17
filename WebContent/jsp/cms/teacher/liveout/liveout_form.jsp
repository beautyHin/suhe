<%@ page  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cms.liveout.bean.LiveOutBean" %>
<%
LiveOutBean model = (LiveOutBean)request.getAttribute("model");
%>
<script type="text/javascript" src="${basePath }My97DatePicker/WdatePicker.js"></script>
<div class="form-group">
	<div class="label">
		<label>学号:</label>
	</div>
	<div class="field">
		<input  type="text" name="stuCode" class="input w50"  dataType="Limit" min="1" max="20" require="true" value="${model.stuCode }"/>
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>姓名:</label>
	</div>
	<div class="field">
		<input  type="text" name="stuName"  class="input w50" require="true" dataType="Limit,Chinese"  min="2" max="6" value="${model.stuName }"/>
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
		<label>旷寝内容：</label>
	</div>
	<div class="field">
		<textarea name="liveoutContent" class="input w50"  dataType="Limit" min="0" max="200" maxlength="200">${model.liveoutContent }</textarea>
		<p>最多可输入200个字符</p>
	</div>
</div>


<div class="form-group">
	<div class="label">
		<label>旷寝时间：</label>
	</div>
	<div class="field">
	<input id="d12" onclick="WdatePicker()" class="input w50"  type="text" name="liveoutDate" id="liveoutDate" require="true" value="${model.liveoutDate}"/>
	</div>
</div>

<div class="form-group">
	<div class="label">
		<label>是否通报批评：</label>
	</div>
	<div class="field">
		<div class="button-group radio">

			<label class="button ${model.isCriticize == null ? 'active': ''} ${model.isCriticize == 0 ? 'active' : ''}">
				<span class="icon icon-check"></span>
				<input  type="radio" require="true" name="isCriticize" value="0" checked="true">否
			</label>

			<label class="button ${model.isCriticize == 1 ? 'active' : ''}">
				<span class="icon icon-check"></span>
				<input  type="radio" require="true" name="isCriticize" value="1"  >是
			</label>
		</div>
	</div>
</div>