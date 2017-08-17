<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
      <title>学生入住登记</title>
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
      <meta name="renderer" content="webkit">
      <link rel="stylesheet" href="${basePath}css/pintuer.css">
      <link rel="stylesheet" href="${basePath}css/admin.css">
      <script src="${basePath}/script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
      <script src="${basePath}script/pintuer.js"></script>
      <script src="${basePath}/script/form-validator.js"></script>
  </head>
  <%@include file="/jsp/head.jsp"%>
  <%@include file="/jsp/cms/teacher/menu.jsp"%>
  <%@include file="/jsp/navigation.jsp"%>
  <body>

  <div class="admin">
          <div class="panel admin-panel margin-top">
              <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>&nbsp;&nbsp;学生入住登记</strong></div>
              <div class="body-content">
    <form id="tab" class="form-x" action="${basePath }cms/teacher/stu/Student/studentInsert.action" method="post" onsubmit="return FormValidator.Validate(this,3)">
       <%@include file="student_form.jsp" %>
           <div class="form-group">
               <div class="label">
                   <label></label>
               </div>
               <div class="field">
                   <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
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


