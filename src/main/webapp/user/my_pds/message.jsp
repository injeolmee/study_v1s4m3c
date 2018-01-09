<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<% 
String root = request.getContextPath(); 
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>

<link href="/study/my_pds/css/my_pds_style.css" rel='Stylesheet' type='text/css'>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>


</head> 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'>
  
  <DIV class='message' style="width: 50%; margin: 0px auto; border: solid 1px; border-radius:15px; margin-bottom: 30px; margin-top: 30px;">
    <fieldset class='fieldset_basic'>
      <ul>
        <c:forEach var="msg" items="${result_msg}">
          <li class='li_none'>
            <span style="font-weight: bolder;">${msg}</span>
          </li>
        </c:forEach>
        
        <li class='li_none'></li>
        
        <c:forEach var="link" items="${result_link}">
          <li class='li_none'>${link}</li>
        </c:forEach>
      </ul>
    </fieldset>
  </DIV>
 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 