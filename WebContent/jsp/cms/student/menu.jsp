<%@ page  pageEncoding="UTF-8"%>
<div class="leftnav">
    <div class="leftnav-title"><strong>欢迎  ${user.name}</strong></div>

    <h2 onclick="location.href='${basePath}cms/student/notice/NoticeStudent/list.action'" ><span class="icon-pencil-square-o"></span>宿舍公告</h2>
    <h2 onclick="location.href='${basePath}cms/student/building/BuildingStudent/list.action'"><span   class="icon-pencil-square-o"></span>楼宇信息</h2>
    <h2 onclick="location.href='${basePath }cms/student/liveout/LiveOutStudent/list.action'"><span  class="icon-pencil-square-o"></span>旷寝记录</h2>
    <h2 onclick="location.href='${basePath }cms/student/repair/RepairStudent/list.action'"><span  class="icon-pencil-square-o"></span>寝室报修</h2>
    <h2 onclick="location.href='${basePath}cms/student/discipline/DisciplineStudent/list.action'"><span  class="icon-pencil-square-o"></span>宿舍违规记录</h2>
</div>
