<%@ page pageEncoding="UTF-8" %>
<ul class="bread">
    <li><a href="" target="right" class="icon-home"> 首页</a></li>
    <li id="a_leader_txt"></li>
</ul>
<script type="text/javascript">
    var titleText = $("title").text();
    $("#a_leader_txt").append(titleText);
</script>