<%@ page language="java" pageEncoding="UTF-8"%>
<div class="leftnav">
    <div class="leftnav-title"><strong>欢迎  ${user.name}</strong></div>

    <h2 onclick="location.href='${basePath}cms/admin/teacher/TeacherAdmin/list.action'" ><span class="icon-pencil-square-o"></span>宿舍管理员信息</h2>
    <h2 onclick="location.href='${basePath}cms/admin/stu/StudentAdmin/list.action'"><span   class="icon-pencil-square-o"></span>学生信息</h2>

</div>