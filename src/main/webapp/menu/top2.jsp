<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%
String root = request.getContextPath();
%>



<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- CSS Part -------------------------->

<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/animate.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/pluton.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/jquery.cslider.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/menu/css/jquery.bxslider.css" />
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">

</head>
<body>

<!---------------------상단 Aside 메뉴 시작-------------------------->
 <c:import url="/admin/category/list_top.do" /> 
  <div style="text-align: right; margin: 10px; color: #333333; font-size: 15px;">
   
    <c:choose>
      <c:when test="${sessionScope.memid == null}">
        <a href="<%=root%>/login/login.do">로그인</a>
        <span style='margin:5px;'>|</span>
        <a href="<%=root%>/login/memberjoin.do">회원가입</a>    
           
      </c:when>
      <c:otherwise>
        <a href="<%=root%>/login/logout.do">로그아웃  </a>
        <span style='margin:5px;'>|</span>
        <span style="font-weight: bold;">${sessionScope.memid } 님의
        <a href="<%=root%>/member/mypage.do">My Page</a></span>     
      </c:otherwise>
    </c:choose>
        <span style='margin:5px;'>|</span>
        <a href="<%=root%>/admin/admin/admin_create.do">관리자가입</a> 
        <span style='margin:5px;'>|</span> 
        <a href='${pageContext.request.contextPath }/user/message/message_home.do'><img src='<%=root%>/menu/images/message.png'>[쪽지함]</a>
  </div>
  <!--------------------------상단 Aside 메뉴 시작-------------------------->

  <div class="navbar" style="width: 100%; position: relative;">
    <div class="navbar-inner">
      <div class="container">
      
        <!-------------------------- 웹사이트 로고 부분 시작 -------------------------->
        <a href="<%=root%>/main/index.do" class="brand"> <img src="<%=root%>/menu/images/Logo.png" 
        width="400px" height="150" alt="Logo" /> 
        </a>
        <!-------------------------- 웹사이트 로고 부분 종료 -------------------------->
        
        <!------------------Navigation button (창 줄였을시 상단 메뉴가 버튼으로 뜨는것) --------------->
        <button type="button" class="btn btn-navbar"
          data-toggle="collapse" data-target=".nav-collapse">
          <i class="icon-menu"></i>
        </button>
        
        <!------------------------------------------------------------------------->
        
        <!-------------------------- 웹사이트 메인 메뉴 부분 시작 ------------------------ -->
        <div class="nav-collapse collapse pull-right">
          <ul class="nav" id="top-navigation">

            <!-- <Menu1> 내 스터디 -->
            <li><a href="${pageContext.request.contextPath }/mystudy/mystudy.do">내 스터디</a></li>
           
            <!-- <Menu2> 스터티그룹 찾기 -->
            <li><a href="<%=root %>/nonuser/studylist/list.do">스터디 그룹</a></li>
            
            <!-- <Menu3> 스터디룸 찾기 -->
            <li><a href="#">스터디룸</a></li>
            
            <!-- <Menu4> 공모전 / 취업 -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">공모전/취업<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">공모전</a></li>
                <li><a href="#">취업</a></li>
              </ul>
            </li>
            
            <!-- <Menu5> 커뮤니티 -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">커뮤니티<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">자유 게시판</a></li>
                <li><a href="#">거래 게시판</a></li>
                <li><a href="#"> 자료실 </a></li>
              </ul>
            </li>
            
            <!-- <Menu6> 공지사항 -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">공지/Q&A<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">공지사항</a></li>
                <li><a href="#">Q & A</a></li>
              </ul>
            </li>
            
            <!-- <Menu7> 관리자 -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<%=root%>/admin/categrp/grp_list.do">카테그룹</a></li>
                <li><a href="<%=root%>/member/mem_list.do">회원 관리</a></li>
                <li><a href="#">관리자 관리</a></li>
              </ul>
            </li>
            
          </ul>
        </div>
        <!-------------------------- 웹사이트 메인 메뉴 부분 종료 --------------------------> 
        
      </div>
    </div>
  </div>

 <!-------------------------- Javascript 관련 Include -------------------------->
  <script type="text/javascript" src="<%=root%>/menu/js/bootstrap.js"></script>
  <script type="text/javascript" src="<%=root%>/menu/js/respond.min.js"></script>
  <script type="text/javascript" src="<%=root%>/menu/js/jquery.bxslider.js"></script>
  <script type="text/javascript" src="<%=root%>/menu/js/jquery.cslider.js"></script>
  <script type="text/javascript" src="<%=root%>/menu/js/modernizr.custom.js"></script>
  <script type="text/javascript" src="<%=root%>/menu/js/jquery.inview.js"></script>
  <script type="text/javascript" src="<%=root%>/menu/js/app.js"></script>
  <script type="text/javascript" src="<%=root%>/menu/js/jquery.mixitup.js"></script>

</body>
</html>