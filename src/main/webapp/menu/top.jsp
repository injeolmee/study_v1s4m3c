<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%
String root = request.getContextPath();

//isNew() 메서드를 이용해 최초세션설정을 확인하고 있다.
/* if(session.isNew()) {
out.println("<script> alert(‎'새로운 세션이 시작 되었습니다.') </script>");
} */
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
<script type="text/javascript">
// Logout Timer 객체 정의
<%-- var LogOutTimer = function() {
  var S = {
            timer : null,
            limit : 1000 * 60 * 30,
            fnc   : function() {},
            start : function() {
                      S.timer = window.setTimeout(S.fnc, S.limit);
                    },
            reset : function() {
                      window.clearTimeout(S.timer);
                      S.start();
                    }
          };
  
document.onmousemove = function() { S.reset(); };
  
  return S;
}();

// 로그아웃 체크시간 설정
LogOutTimer.limit = 1000 * 60 * 30;

// 로그아웃 함수 설정 
LogOutTimer.fnc = function() {
  alert("LogOut");   
  window.location.href='<%=root%>/nonuser/login/login.do';
}

// 로그아웃 타이머 실행
LogOutTimer.start(); --%>


</script>

<body>

<!---------------------상단 Aside 메뉴 시작-------------------------->
  <div style="text-align: right; margin: 10px; color: #333333; font-size: 15px;">
   <div style="float: left; text-align: left; color: #333333; font-size: 15px;">
     오늘 방문자 : ${sessionScope.todayCount}<span style='margin:5px;'>|</span>전체 방문자 : ${sessionScope.totalCount}
     ${sessionScope.mem_login_time}
   </div>
  
<%--   0. login 세션의 설정값 : <FONT COLOR=BLUE><%= session.getAttribute("memid") %></font> 님의 세션 유지<BR>
1. 세션 ID : <FONT COLOR=BLUE><%= session.getId() %></font> <BR>
2. 세션 유지시간(최초요청:톰켓 디폴트 1800초/두번째요청:아래설정값 나타남 10초/10초 지나면 다시 서버 디폴트 값 적용) :<FONT
COLOR=BLUE> <%= session.getMaxInactiveInterval() %></font> <BR>
3. 세션 유지시간 다시 설정 :<FONT COLOR=red> session.setMaxInactiveInterval(10)</font><==세션 인터벌 10초 재지정 <BR>
<%
session.setMaxInactiveInterval(10);
%>
4. login 세션의 재설정(같은 이름을"login" 주면 덮어쓰기 됩니다.) :<FONT COLOR=red> session.setAttribute("login",session.getId())</font> <==세션 아이디 저장<BR>
 --%>
    <c:choose>
      <c:when test="${sessionScope.memauth == 'U'}">
        <a href="<%=root%>/nonuser/login/logout.do">로그아웃  </a>
        <span style='margin:5px;'>|</span>
        <span style="font-weight: bold;"><img src='<%=root%>/jeimages/mypage.png' style='margin-right:5px;'>${sessionScope.memid } 님의
        <a href="<%=root%>/user/member/mem_read.do?memberno=${sessionScope.memberno}">My Page</a></span>
        <span style='margin:5px;'>|</span> 
        <a href='${pageContext.request.contextPath }/user/message/message_home.do'><img src='<%=root%>/jeimages/message.png' style='margin-right:5px;'>쪽지함</a>
      </c:when>              
      <c:when test="${sessionScope.admauth == 'M' || sessionScope.admauth == 'A'}">
        <a href="<%=root%>/nonuser/login/admin_logout.do">로그아웃  </a>
        <span style='margin:5px;'>|</span>
        <span style="font-weight: bold;"><img src='<%=root%>/jeimages/mypage.png' style='margin-right:5px;'>${sessionScope.admid } 님의
        <a href="<%=root%>/admin/admin/admin_read.do?adminno=${sessionScope.adminno}">My Page</a></span>
        <span style='margin:5px;'>|</span> 
        <a href='${pageContext.request.contextPath }/user/message/message_home.do'><img src='<%=root%>/jeimages/message.png' style='margin-right:5px;'>쪽지함</a>
      </c:when> 
      <c:otherwise> 
        <a href="<%=root%>/nonuser/login/login.do">로그인</a>
        <span style='margin:5px;'>|</span>
        <a href="<%=root%>/nonuser/login/memberjoin.do">회원가입</a>
      </c:otherwise>      
    </c:choose>
    
  </div>
  <!--------------------------상단 Aside 메뉴 시작-------------------------->
 
  <div class="navbar" style="width: 100%; /* position: relative; */">
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
                <li><a href="<%=root %>/nonuser/contest/list_all_contest.do">공모전</a></li>
                <li><a href="<%=root %>/nonuser/jobinfo/list_all_jobinfo.do">취업</a></li>
              </ul>
            </li>
            
            <!-- <Menu5> 커뮤니티 -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">커뮤니티<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/nonuser/free/list.do">자유 게시판</a></li>
                <li><a href="${pageContext.request.contextPath}/user/sale/list.do">거래 게시판</a></li>
                <li><a href="${pageContext.request.contextPath}/user/shared/list.do"> 자료실 </a></li>
              </ul>
            </li>
            
            <!-- <Menu6> 공지사항 -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">공지/Q&A<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<%=root%>/nonuser/notice/notice_list.do">공지사항</a></li>
                <li><a href="<%=root %>/nonuser/qnaboard/list_all_qna.do">Q & A</a></li>
              </ul>
            </li>
            
            <!-- <Menu7> 관리자 -->
        <c:choose>
          <c:when test='${sessionScope.admauth == "A"}'>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<%=root%>/admin/categrp/grp_list.do">카테그룹</a></li>
                <li><a href="<%=root%>/admin/member/mem_list.do">회원 관리</a></li>
              </ul>
            </li>
          </c:when>
          <c:when test='${sessionScope.admauth == "M"}'>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<%=root%>/admin/categrp/grp_list.do">카테그룹</a></li>
                <li><a href="<%=root%>/admin/member/mem_list.do">회원 관리</a></li>
                <li><a href="<%=root%>/master/admin_list.do">관리자 관리</a></li>
                <li><a href="<%=root%>/admin/message/message_admin.do">쪽지함 관리</a></li>
              </ul>
            </li>
          </c:when>
        </c:choose>
            
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