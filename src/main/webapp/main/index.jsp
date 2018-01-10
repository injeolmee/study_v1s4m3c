<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-------------------------- ROBOTO FONT Part -------------------------->
<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700&amp;subset=latin,latin-ext'  rel='stylesheet' type='text/css'>
<!-------------------------- CSS Part -------------------------->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/pluton.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.cslider.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css" />
<link rel="stylesheet" type="text/css" href="css/animate.css" />
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="images/ico/Short Logo.png">
</head>
<script type="text/javascript">


</script>
<body>

   <!---------------------상단 Aside 메뉴 시작-------------------------->

  <div style="text-align: right; margin: 10px; color: #333333; font-size: 15px;">
     <div style="float: left; text-align: left; color: #333333; font-size: 15px;">
       오늘 방문자 : ${sessionScope.todayCount}<span style='margin:5px;'>|</span>전체 방문자 : ${sessionScope.totalCount}
      <c:choose>
        <c:when test="${sessionScope.memauth == 'U' || sessionScope.admauth == 'M' || sessionScope.admauth == 'A'}">
        <span style='margin:5px;'>|</span>로그인 시간 : ${sessionScope.login_time}
        </c:when>
      </c:choose>
     </div>
  
<%-- 0. login 세션의 설정값 : <FONT COLOR=BLUE><%= session.getAttribute("memid") %></font> 님의 세션 유지<BR>
1. 세션 ID : <FONT COLOR=BLUE><%= session.getId() %></font> <BR>
2. 세션 유지시간(최초요청:톰켓 디폴트 1800초/두번째요청:아래설정값 나타남 10초/10초 지나면 다시 서버 디폴트 값 적용) :<FONT
COLOR=BLUE> <%= session.getMaxInactiveInterval() %></font> <BR>
3. 세션 유지시간 다시 설정 :<FONT COLOR=red> session.setMaxInactiveInterval(10)</font><==세션 인터벌 10초 재지정 <BR>
<%
session.setMaxInactiveInterval(10);
%>
4. login 세션의 재설정(같은 이름을"login" 주면 덮어쓰기 됩니다.) :<FONT COLOR=red> session.setAttribute("login",session.getId())</font> <==세션 아이디 저장<BR>
5. login 세션의 재설정값 : <FONT COLOR=BLUE><%= session.getAttribute("memid") %></font> 님의 세션 유지(세션아이디 값) <BR>
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
      </c:when> 
      <c:otherwise> 
        <a href="<%=root%>/nonuser/login/login.do">로그인</a>
        <span style='margin:5px;'>|</span>
        <a href="<%=root%>/nonuser/login/memberjoin.do">회원가입</a> 
      </c:otherwise>      
    </c:choose>
  </div>
  <!--------------------------상단 Aside 메뉴 시작-------------------------->

  <div class="navbar">
    <div class="navbar-inner">
      <div class="container">
      
        <!-------------------------- 웹사이트 로고 부분 시작 -------------------------->
        <a href="./index.do" class="brand"> <img src="images/Logo.png" 
        width="400px" height="150" alt="Logo" /> 
        </a>
        <!-------------------------- 웹사이트 로고 부분 종료 -------------------------->
        
        <!-------------------------- Navigation button -------------------------->
        <button type="button" class="btn btn-navbar"
          data-toggle="collapse" data-target=".nav-collapse">
          <i class="icon-menu"></i>
        </button>
        <!------------------------------------------------------------------------->
        
                <!-------------------------- 웹사이트 메인 메뉴 부분 시작 ------------------------ -->
        <div class="nav-collapse collapse pull-right">
          <ul class="nav" id="top-navigation">

          <c:choose>
            <c:when test="${sessionScope.memauth == 'U'}">
            <!-- <Menu1> 내 스터디 -->
            <li><a href="${pageContext.request.contextPath }/user/mystudy/mystudy.do">내 스터디</a></li>
            </c:when>
          </c:choose>
           
            <!-- <Menu2> 스터티그룹 찾기 -->
            <li><a href="<%=root %>/nonuser/studylist/list.do ">스터디 그룹</a></li>
            
            <!-- <Menu3> 스터디룸 찾기 -->
            <li><a href="<%=root %>/nonuser/room/list.do ">스터디룸</a></li>
            
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
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">공지사항<b class="caret"></b></a>
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
  
  <!-------------------------- 배너(Home) Section 시작 -------------------------->
  <div id="home">
    <!-------------------------- Slider Images 부분 시작 -------------------------->
    <div id="da-slider" class="da-slider">
      <div class="triangle"></div>
      <!-- mask elemet use for masking background image -->
      <div class="mask"></div>
      <!-- All slides centred in container element -->
      <div class="container">
        <!-------------------------- First Slider 부분 시작 -------------------------->
        <div class="da-slide">
          <h2 class="fittext2">Welcome Study Desk!</h2>
          <h4>차별화된 Study Matching 시스템</h4>
          <p>찾기 힘들었던 Study 모임은 이제 그만! Study DESK만의 <br>
               특별한 Matching System으로 손쉽게 Study 모임을 만나 <br>
               보세요! Study 전용 게시판을 손쉽게 만들어 스터디원과 <br>
               다양한 정보를 공유할 수 있습니다! </p>
          <a href="#" class="da-link button">더보기</a>
          <div class="da-img">
            <img src="images/Slider01.png" alt="image01" width="240">
          </div>
        </div>
        <!-------------------------- First Slider 부분 종료 -------------------------->
        
        <!-------------------------- Second Slider 부분 시작 -------------------------->
        <div class="da-slide">
          <h2>Customer Search System </h2>
          <h4>사용자를 위한 맞춤형 검색 서비스</h4>
          <p>맞춤형 검색 서비스로 인하여 사용자가 원하는 Study 모임의 <br>
            주제와 지역별로 검색할 수 있습니다. 또한, Study 를 위한 <br>
            스터디룸의 검색까지 제공하고 있습니다. </p>
          <a href="/study/nonuser/studylist/list.do" class="da-link button">더보기</a>
          <div class="da-img">
            <img src="images/Slider02.png" width="220" alt="image02">
          </div>
        </div>
        <!-------------------------- Second Slider 부분 종료 -------------------------->
        
        <!-------------------------- Third Slider 부분 시작 -------------------------->
        <div class="da-slide">
          <h2>Schedule Management System</h2>
          <h4>체계적인 일정 관리 서비스</h4>
          <p>일정 관리를 손 쉽게 Check 해주는 Scheduler 서비스를 제공합니다. <br>
             또한, 가입한 Study의 Project에 대해 알림 서비스를 제공하여 <br>
             체계적인 일정 관리를 도와드립니다! <br>
          </p>
          <a href="${pageContext.request.contextPath }/user/mystudy/mystudy.do" class="da-link button">더보기</a>
          <div class="da-img">
            <img src="images/Slider03.png" width="240" alt="image03">
          </div>
        </div>
        <!-------------------------- Third Slider 부분 종료 -------------------------->
        
        <!-- Start cSlide navigation arrows -->
        <div class="da-arrows">
          <span class="da-arrows-prev"></span> <span
            class="da-arrows-next"></span>
        </div>
        <!-- End cSlide navigation arrows -->
      </div>
    </div>
  </div>
  <!-------------------------- 배너(Home) Section 시작 -------------------------->
  
  <!--------------------------서비스안내(Service) Section 시작 -------------------------->
  <div class="section primary-section" id="service">
    <div class="container">

      <div class="title">
        <h1>Study Desk의 강력한 기능 3가지</h1>
      </div>
      
      <div class="row-fluid">
        
        <!-- 서비스안내 Section 1 -->
        <div class="span4">
          <div class="centered service">
            <div class="circle-border zoom-in">
              <img class="img-circle" src="images/Service1.png"
                alt="service 1">
            </div>
            <h3>스터디 관련 검색의 체계적 시스템</h3>
            <p>We Create Modern And Clean Theme For Your Business
              Company.</p>
          </div>
        </div>
        
        <!-- 서비스안내 Section 2 -->    
        <div class="span4">
          <div class="centered service">
            <div class="circle-border zoom-in">
              <img class="img-circle" src="images/Service2.png"
                alt="service 2" />
            </div>
            <h3>종합 일정 관리 시스템 </h3>
            <p>We Create Modern And Powerful Theme With Lots
              Animation And Features</p>
          </div>
        </div>
        
        <!-- 서비스안내 Section 3-->
        <div class="span4">
          <div class="centered service">
            <div class="circle-border zoom-in">
              <img class="img-circle" src="images/Service3.png"
                alt="service 3">
            </div>
            <h3>스터디와 관련된 다양한 정보 시스템</h3>
            <p>We Create Modern And Powerful Html5 And CSS3 Code
              Easy For Read And Customize.</p>
          </div>
        </div>
        
      </div>
    </div>
  </div>
 <!--------------------------서비스안내(Service) Section 종료 -------------------------->
  
  <!--------------------------Study Room(Portfolio) Section 시작-------------------------->
  <div class="section secondary-section " id="portfolio">
    <div class="triangle"></div>
    <div class="container">
    
      <div class=" title">
        <h1>Study Room </h1>
        <p>Study Desk에서 제공 중인 Study Room을 만나보세요!</p>
      </div>
      
      <!------------------------- Study Room project 1 상세보기 시작------------------------->
      <div id="single-project">
        <div id="slidingDiv" class="toggleDiv row-fluid single-project">
          <div class="span6">
            <img src="images/Portfolio01.png" alt="project 1" />
          </div>
          <div class="span6">
            <div class="project-description">
              <div class="project-title clearfix">
                <h3>Study Room</h3>
                <span class="show_hide close"> <i
                  class="icon-cancel"></i>
                </span>
              </div>
              <div class="project-info">
                <div>
                  <span>Client</span>Some Client Name
                </div>
                <div>
                  <span>Date</span>July 2013
                </div>
                <div>
                  <span>Skills</span>HTML5, CSS3, JavaScript
                </div>
                <div>
                  <span>Link</span>http://examplecomp.com
                </div>
              </div>
              <p>Believe in yourself! Have faith in your abilities!
                Without a humble but reasonable confidence in your own
                powers you cannot be successful or happy.</p>
            </div>
          </div>
        </div>
        <!------------------------- Study Room project 1 상세보기 종료------------------------->
        
         <!------------------------- Study Room project 2 상세보기 시작------------------------->
        <div id="slidingDiv1" class="toggleDiv row-fluid single-project">
          <div class="span6">
            <img src="images/Portfolio02.png" alt="project 2">
          </div>
          <div class="span6">
            <div class="project-description">
              <div class="project-title clearfix">
                <h3>Study Room</h3>
                <span class="show_hide close"> <i
                  class="icon-cancel"></i>
                </span>
              </div>
              <div class="project-info">
                <div>
                  <span>Client</span>Some Client Name
                </div>
                <div>
                  <span>Date</span>July 2013
                </div>
                <div>
                  <span>Skills</span>HTML5, CSS3, JavaScript
                </div>
                <div>
                  <span>Link</span>http://examplecomp.com
                </div>
              </div>
              <p>Life is a song - sing it. Life is a game - play it.
                Life is a challenge - meet it. Life is a dream - realize
                it. Life is a sacrifice - offer it. Life is love - enjoy
                it.</p>
            </div>
          </div>
        </div>
        <!------------------------- Study Room project 2 상세보기 종료------------------------->
        
        <!------------------------- Study Room project 3 상세보기 시작------------------------->
        <div id="slidingDiv2" class="toggleDiv row-fluid single-project">
          <div class="span6">
            <img src="images/Portfolio03.png" alt="project 3">
          </div>
          <div class="span6">
            <div class="project-description">
              <div class="project-title clearfix">
                <h3>Study Room</h3>
                <span class="show_hide close"> <i
                  class="icon-cancel"></i>
                </span>
              </div>
              <div class="project-info">
                <div>
                  <span>Client</span>Some Client Name
                </div>
                <div>
                  <span>Date</span>July 2013
                </div>
                <div>
                  <span>Skills</span>HTML5, CSS3, JavaScript
                </div>
                <div>
                  <span>Link</span>http://examplecomp.com
                </div>
              </div>
              <p>How far you go in life depends on your being tender
                with the young, compassionate with the aged, sympathetic
                with the striving and tolerant of the weak and strong.
                Because someday in your life you will have been all of
                these.</p>
            </div>
          </div>
        </div>
        <!------------------------- Study Room project 3 상세보기 종료------------------------->

        <!------------------------- Study Room project Grid 형식 시작------------------------->
        <ul id="portfolio-grid" class="thumbnails row">
          
          <li class="span4 mix web">          
            <div class="thumbnail">
              <img src="images/Portfolio01.png" alt="project 1"> <a
                href="#single-project" class="more show_hide"
                rel="#slidingDiv"> <i class="icon-plus"></i>
              </a>
              <h3>Study Room 이름</h3>
              <p>Study Room 간략정보</p>
             <!--  <div class="mask"></div> -->
            </div>
          </li>
          
          <li class="span4 mix photo">
            <div class="thumbnail">
              <img src="images/Portfolio02.png" alt="project 2"> <a
                href="#single-project" class="show_hide more"
                rel="#slidingDiv1"> <i class="icon-plus"></i>
              </a>
              <h3>Study Room 이름</h3>
              <p>Study Room 간략정보</p>
             <!--  <div class="mask"></div> -->
            </div>
          </li>
          <li class="span4 mix identity">
            <div class="thumbnail">
              <img src="images/Portfolio03.png" alt="project 3"> <a
                href="#single-project" class="more show_hide"
                rel="#slidingDiv2"> <i class="icon-plus"></i>
              </a>
              <h3>Study Room 이름</h3>
              <p>Study Room 간략정보</p>
              <!-- <div class="mask"></div> -->
            </div>
          </li>
        </ul>
        </div>
      </div>
   </div>
   <!------------------------- Study Room project Grid 형식 종료------------------------->
   
   <!------------------------- Client section 시작------------------------->
   <div class="section third-section">
     <div class="container centered">
       <div class="sub-section">
       
          <!--  Client section 제목 -->
         <div class="title clearfix">
           <div class="pull-left">
             <h3>Study 모임</h3>
           </div>
           
           <!-- Section 이동 구간 -->
           <ul class="client-nav pull-right"> 
             <li id="client-prev"></li>
             <li id="client-next"></li>
           </ul>
         </div>
        
        <!-- Section 별 시작 -->
        <ul class="row client-slider" id="clint-slider">
          <li>
            <a href=""><img src="images/clients/ClientLogo01.png" alt="client logo 1"></a>
          </li>
          <li>
            <a href=""> <img src="images/clients/ClientLogo02.png" alt="client logo 2"></a>
          </li>
          <li>
            <a href=""><img src="images/clients/ClientLogo03.png" alt="client logo 3"></a>
          </li>
          <li>
            <a href=""><img src="images/clients/ClientLogo04.png" alt="client logo 4"></a>
          </li>
          <li>
            <a href=""><img src="images/clients/ClientLogo05.png" alt="client logo 5"></a>
          </li>
          <li>
            <a href=""><img src="images/clients/ClientLogo02.png" alt="client logo 6"></a>
          </li>
          <li>
            <a href=""><img src="images/clients/ClientLogo04.png" alt="client logo 7"></a>
          </li> 
        </ul>
        <!-- Section 별 종료 -->
        
      </div>
    </div>
  </div>

   <!-------------------------- Footer Section 시작 -------------------------->
   <div class="footer">
     <p>Copyright ⓒ 2017 Soldesk Bigdata Machine Running 7 - 1 all rights reserved.</p>
   </div>
   <!-------------------------- Footer Section 종료 -------------------------->
        
   <!--------------------------  ScrollUp button 시작 -------------------------->
   <div class="scrollup">
     <a href="#"><i class="icon-up-open"></i></a>
   </div>
   <!--------------------------  ScrollUp button 종료 -------------------------->
        
   <!-------------------------- Javascript 관련 Include -------------------------->
  <script src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.mixitup.js"></script>
  <script type="text/javascript" src="js/bootstrap.js"></script>
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/jquery.bxslider.js"></script>
  <script type="text/javascript" src="js/jquery.cslider.js"></script>
  <script type="text/javascript" src="js/jquery.placeholder.js"></script>
  <script type="text/javascript" src="js/jquery.inview.js"></script>
  <!-- <script type="text/javascript" src="js/bootstrap-hover-dropdown.js"></script>
  <script type="text/javascript" src="bootstrap-hover-dropdown.min.js"></script> -->
  <!-- Load google maps api and call initializeMap function defined in app.js -->
<!--   <script async="" defer="" type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false&callback=initializeMap"></script> -->
  <!-- css3-mediaqueries.js for IE8 or older -->
  <script type="text/javascript" src="js/app.js"></script>
</body>
</html>
                