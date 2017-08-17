<%@ page  pageEncoding="UTF-8"%>
<input type="hidden" name="noticeId" value="${model.noticeId }">
<table>

	<div class="form-group">
		<div class="label">
			<label>标题:</label>
		</div>
		<div class="field">
			<input  type="text" name="noticeName"  class="input w50"   dataType="Limit" min="1" max="20" require="true" value="${model.noticeName }"/>
		</div>
	</div>

	<div class="form-group">
		<div class="label">
			<label>内容：</label>
		</div>
		<div class="field">
			<textarea name="noticeInfo" class="input w50"  dataType="Limit" min="0" max="200" maxlength="200">${model.noticeInfo }</textarea>
			<p>最多可输入200个字符</p>
		</div>
	</div>
</table>
       
