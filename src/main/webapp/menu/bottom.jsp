<%@ page contentType="text/html; charset=UTF-8" %>

<%
String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- CSS Part -------------------------->
<link rel="stylesheet" type="text/css" href="<%=root %>/menu/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=root %>/menu/css/pluton.css" />
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
</head>
<body>

<!-------------------------- Footer Section 시작 -------------------------->
   <div class="footer" style=''>
     <p>Copyright ⓒ 2017 Soldesk Bigdata Machine Running 7 - 1 all rights reserved.</p>
   </div>
   <!-------------------------- Footer Section 종료 -------------------------->
        
   <!--------------------------  ScrollUp button 시작 -------------------------->
   <div class="scrollup">
     <a href="#"><i class="icon-up-open"></i></a>
   </div>
   <!--------------------------  ScrollUp button 종료 -------------------------->
  
 <!-------------------------- Javascript 관련 Include -------------------------->

  <%-- <script type="text/javascript" src="<%=root %>/menu/js/respond.min.js"></script> --%>
  <script type="text/javascript" src="<%=root %>/menu/js/app.js"></script>
  <script type="text/javascript" src="<%=root %>/menu/js/jquery.mixitup.js"></script>
  <script type="text/javascript" src="<%=root %>/menu/js/jquery.placeholder.js"></script>
</body>
</html>