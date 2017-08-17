<% if(session.getAttribute("user") == null) {%>
<script>
	window.location.href = "${basePath}jsp/loginTip.jsp";
</script>
<%}%>
