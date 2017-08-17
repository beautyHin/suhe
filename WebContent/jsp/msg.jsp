<%@ page pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    Map<String ,String > msgMap = (Map<String, String>) session.getAttribute("msg");
%>
<%
    if(msgMap != null) {
        if(msgMap.containsKey("success")){%>
            <div class="flash_success">
                <%= msgMap.get("success")%>
            </div>
<%}%>
<% if( msgMap.containsKey("error")) {%>
<div class="flash_error">
    <%=msgMap.get("error")%>
</div>
<%}
    session.removeAttribute("msg");
}%>
</html>