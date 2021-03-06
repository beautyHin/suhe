<%@ page language="java" pageEncoding="UTF-8" %>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>宿舍违规登记</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${basePath}css/pintuer.css">
    <link rel="stylesheet" href="${basePath}css/admin.css">
    <script src="${basePath}/script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
    <script src="${basePath}/script/form-validator.js"></script>
    <script src="${basePath }script/nodecheck.js"></script>
    <script src="${basePath}script/pintuer.js"></script>
</head>
<%@include file="/jsp/head.jsp" %>
<%@include file="/jsp/cms/teacher/menu.jsp" %>
<%@include file="/jsp/navigation.jsp" %>
<body>
<div class="admin">
    <div class="panel admin-panel margin-top">
        <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>&nbsp;&nbsp;宿舍违规登记</strong>
        </div>
        <div class="body-content">

            <form id="disciplineInsert_form" class="form-x" action="${basePath }cms/teacher/discipline/Discipline/insert.action"
                  method="post" onsubmit="return FormValidator.Validate(this,3)">
                <%@include file="discipline_form.jsp" %>
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
</html>
