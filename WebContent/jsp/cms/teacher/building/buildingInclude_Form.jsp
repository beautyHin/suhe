<%@ page pageEncoding="UTF-8"%>
<input type="hidden" name="buildingId" value="${model.buildingId }">

	<div class="form-group">
		<div class="label">
			<label>楼宇名:</label>
		</div>
		<div class="field">
			<input  type="text" name="buildingName" class="input w50"  dataType="Limit" min="1" max="20" require="true" value="${model.buildingName }"/>
		</div>
	</div>

	<div class="form-group">
		<div class="label">
			<label>楼宇介绍：</label>
		</div>
		<div class="field">
			<textarea name="buildingInfo" class="input w50"  dataType="Limit" min="0" max="200" maxlength="200">${model.buildingInfo }</textarea>
			<p>最多可输入200个字符</p>
		</div>
	</div>


