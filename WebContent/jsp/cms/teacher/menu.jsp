<%@ page pageEncoding="UTF-8"%>

<script type="text/javascript">
    $(function(){
    $(".leftnav h2").click(function(){
    $(this).next().slideToggle(200);
    $(this).toggleClass("on");
    })
    $(".leftnav ul li a").click(function(){
    $("#a_leader_txt").text($(this).text());
    $(".leftnav ul li a").removeClass("on");
    $(this).addClass("on");
    })
    });
</script>

<div class="leftnav">
    <div class="leftnav-title"><strong>欢迎  ${user.name}</strong></div>
    <h2><span class="icon-pencil-square-o"></span>学生管理</h2>
    <ul style="display:block">
        <li><a href="${basePath }cms/teacher/stu/Student/studentList.action" ><span class="icon-caret-right"></span>学生列表</a></li>
        <li><a href="${basePath }cms/teacher/stu/Student/toStudentInsert.action" ><span class="icon-caret-right"></span>学生入住登记</a></li>
        <li><a href="${basePath }cms/teacher/stu/Student/studentExitList.action" ><span class="icon-caret-right"></span>学生迁出登记</a></li>
        <li><a href="${basePath }cms/teacher/stu/Student/updateDomitoryList.action" ><span class="icon-caret-right"></span>学生换寝登记</a></li>
        <li><a href="${basePath }cms/teacher/liveout/LiveOut/list.action" ><span class="icon-caret-right"></span>学生旷寝登记</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>楼宇管理</h2>
    <ul style="display:block">
        <li><a href="${basePath}cms/teacher/building/Building/list.action" ><span class="icon-caret-right"></span>楼宇列表</a></li>

    </ul>


    <h2><span class="icon-pencil-square-o"></span>宿舍管理</h2>
    <ul style="display:block">
        <li><a href="${basePath }cms/teacher/domitory/Domitory/list.action" ><span class="icon-caret-right"></span>宿舍明细</a></li>
        <li><a href="${basePath }cms/teacher/notice/Notice/list.action" ><span class="icon-caret-right"></span>宿舍公告</a></li>
        <li><a href="${basePath }cms/teacher/repair/Repair/list.action" ><span class="icon-caret-right"></span>宿舍申报列表</a></li>
        <li><a href="${basePath }cms/teacher/discipline/Discipline/list.action" ><span class="icon-caret-right"></span>宿舍违规登记</a></li>
    </ul>
</div>


