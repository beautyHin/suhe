<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.cms.building.dao.IBuildingDao" %>
<%@page import="com.cms.building.dao.impl.BuildingDao" %>
<%@page import="com.cms.building.bean.BuildingBean"%>
<%@page import="java.util.*"%>
<%@ page import="com.framework.common.BuildingCache" %>
<%@ page import="com.cms.stu.bean.StudentBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>学生换寝编辑</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
	<link rel="stylesheet" href="${basePath}css/pintuer.css">
	<link rel="stylesheet" href="${basePath}css/admin.css">
<script src="${basePath}/script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="${basePath}script/pintuer.js"></script>
<script src="${basePath}/script/form-validator.js"></script>
</head>
<%@include file="/jsp/head.jsp"%>
<%@include file="/jsp/cms/teacher/menu.jsp"%>
<%@include file="/jsp/navigation.jsp"%>
<% StudentBean model = (StudentBean) request.getAttribute("model");%>
<body>
<div class="admin">
<div class="panel admin-panel margin-top">
	<div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>&nbsp;&nbsp;学生换寝编辑</strong></div>
	<div class="body-content">
						<form id="tab" class="form-x" action="${basePath }cms/teacher/stu/Student/updateDomitory.action"
							method="post" onsubmit="return FormValidator.Validate(this,3)">
							<input type="hidden" name="id" value="${model.id }" >
                            <c:if test="${model != null}">
                            <div class="form-group">
                                    <div class="label">
                                        <label >原寝室：</label>
                                    </div>
                                    <div class="field">
                                        <label style="line-height:33px;">
                                            楼宇:<%= BuildingCache.getBuildingName(model.getBuildingId()) %>&nbsp;宿舍:${model.domitoryCode}
                                        </label>
                                    </div>
                                </div>
                            </c:if>

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
									<label></label>
								</div>
								<div class="field">
									<button class="button bg-main icon-check-square-o" type="submit"> 保存</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
</body>
<script type="text/javascript">
	function getDomitory(buildingId) {
  			 $.ajax({
  	              url:"${basePath }cms/teacher/domitory/Domitory/ajaxGetDomitorys.action",
  	              data:{"buildingId":buildingId},
  	              type:"POST",
  	              success: function(data){
  	                      var domitoryArr = eval(data);
  	                      for(var i=0; i<domitoryArr.length; ++i) {
  	                    	  $("#domitoryId-select").append("<option value="+domitoryArr[i].domitoryId+">"+domitoryArr[i].domitoryCode+"</option>");
  	                      }
  	                  },
  	             erro: function(){
  	          	   alert("服务器出错请稍后再试!");
  	          	   
  	             }
  	          });
  			
  		}
  	
  
  	       function gradeChange(){
  	    	   $("#domitoryId-select").empty();
  	    	   var objS = document.getElementById("buildingId-select");
	  	        var buildingId = objS.options[objS.selectedIndex].value;
					if(buildingId != "") {
						getDomitory(buildingId);
					}
  	       }
  	     </script>

</html>
