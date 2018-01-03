<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
String root = request.getContextPath();
%>



 
<c:forEach var="grpname" items="${grp_namelist}" begin="1" end="8">
  ${grpname}       
      
</c:forEach>
 
