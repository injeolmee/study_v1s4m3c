<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="${pageContext.request.contextPath }/nonuser/free/gnacss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!---------------------------------------------------------------------->
<!---------------------- 실시간 검색 관련 CSS Part -------------------->
<style type="text/css">
  .news {padding:0 0px 20px;}
    .news .open-event {height:25px; position:relative; border:1px solid #ccc; overflow:hidden;}
    .news .open-event ul{position:absolute; top:0px;}
    .news .open-event ul#notice1 {left:0;}
    .news .open-event ul#notice2 {right:20px;}
    .news .open-event ul li {height:20px;}
    .news .open-event ul li a {width:310px;}
    .news .open-event ul li a:hover {color:#FECE1A;}
    .news .open-event ul li a strong {margin-right:10px;}
    .news .open-event ul li span.date {width:60px;}
      .news .open-event .prev{position:absolute; top:1px; right:2px; width:7px; height:4px; line-height:0; font-size:0;}
      .news .open-event .next{position:absolute; bottom:1px; right:2px; width:7px; height:4px; line-height:0; font-size:0;}
    .news .control {float:left; margin:0px 0 0 10px; }
      .news .control a.stop {font-size:12px;}
      .news .control a.on {color:red; font-size:12px;}
</style>
<!---------------------------------------------------------------------->
<script type="text/javascript">
$(function(){
 
});

//******************************************************
// 텍스트 롤링 효과 처리 시작
//******************************************************
function fn_article3(containerID, buttonID, autoStart){
  var $element = $('#'+containerID).find('.notice-list');
  var $prev = $('#'+buttonID).find('.prev');
  var $next = $('#'+buttonID).find('.next');
  var autoPlay = autoStart;
  var auto = null;
  var speed = 2000;
  var timer = null;
  
  var move = $element.children().outerHeight();
  var first = false;
  var lastChild;

  lastChild = $element.children().eq(-1).clone(true);
  lastChild.prependTo($element);
  $element.children().eq(-1).remove();

  if($element.children().length==1){
    $element.css('top','0px');
  }else{
    $element.css('top','-'+move+'px');
  }

  if(autoPlay){
    timer = setInterval(moveNextSlide, speed);
    auto = true;
  }

  /////// 자동 재생 처리 ///////
  $element.find('>li').bind({ 
    'mouseenter': function(){
      if(auto){
        clearInterval(timer);
      }
    },
    'mouseleave': function(){
      if(auto){
        timer = setInterval(moveNextSlide, speed);
      }
    }
  });
  //////////////////////////////////

  $prev.bind({
    'click': function(){
      movePrevSlide();
      return false; 
    },
    'mouseenter': function(){
      if(auto){
        clearInterval(timer);
      }
    },
    'mouseleave': function(){
      if(auto){
        timer = setInterval(moveNextSlide, speed);
      }
    }
  });

  $next.bind({
    'click': function(){
      moveNextSlide();
      return false;
    },
    'mouseenter': function(){
      if(auto){
        clearInterval(timer);
      }
    },
    'mouseleave': function(){
      if(auto){
        timer = setInterval(moveNextSlide, speed);
      }
    }
  });

  ////////// ▲ 버튼 눌렀을 경우 생기는 이벤트 효과 //////////////
  function movePrevSlide(){
    $element.each(function(idx){
      if(!first){
        $element.eq(idx).animate({'top': '0px'},'normal',function(){
          lastChild = $(this).children().eq(-1).clone(true);
          lastChild.prependTo($element.eq(idx));
          $(this).children().eq(-1).remove();
          $(this).css('top','-'+move+'px');
        });
        first = true;
        return false;
      }

      $element.eq(idx).animate({'top': '0px'},'normal',function(){
        lastChild = $(this).children().filter(':last-child').clone(true);
        lastChild.prependTo($element.eq(idx));
        $(this).children().filter(':last-child').remove();
        $(this).css('top','-'+move+'px');
      });
    });
  }
  ///////////////////////////////////////////////////////////////////////////

  ////////// ▼ 버튼 눌렀을 경우 생기는 이벤트 효과 //////////////
  function moveNextSlide(){
    $element.each(function(idx){

      var firstChild = $element.children().filter(':first-child').clone(true);
      firstChild.appendTo($element.eq(idx));
      $element.children().filter(':first-child').remove();
      $element.css('top','0px');

      $element.eq(idx).animate({'top':'-'+move+'px'},'normal');
    });
  }
  //////////////////////////////////////////////////////////////////////////
  
  //******************************************************
  // 텍스트 롤링 효과 처리 종료
  //******************************************************
};

</script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 80%; margin: 0px auto; '>  

<DIV style="line-height: 0px;">

<DIV class="title_no_line" style="padding-top: 30px;">
  <IMG src="${pageContext.request.contextPath }/nonuser/free/images/freetitle.png">
</DIV>

<!-------------------------------------------------------------------------------------------------------------->
<!--                                Part 1. 자유게시판 목록                                                               -->
<!-------------------------------------------------------------------------------------------------------------->
<ASIDE style="float: left; width: 73%;">
  <form name='frm' id='frm' action="./list.do" method="get" style="padding-top: 30px;">
  
    <TABLE class="table table-striped" style='width: 100%; margin: 30px 30px 30px 30px auto;'>
      
      <colgroup style="text-align: center;">
        <col style="width: 10%;"></col>
        <col style="width: 40%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      
      <!----------------- 게시판 상위 목록 시작 --------------------------->
      <thead style='font-size: 14px;'>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일</th>
          <th>조회수</th>
          <th>추천수</th>
        </tr>
      </thead>
      <!----------------- 게시판 상위 목록 종료 --------------------------->
      
      <!-- 현재 날짜와 등록 날짜를 비교하기 위한 구문 -->
      <jsp:useBean id="date" class="java.util.Date"/>
      <fmt:formatDate value="${date }" pattern="yyyy-MM-dd" var="date"/>
      
      <!--------------------------------- 게시판 목록 시작 --------------------------------->
      <tbody>
        <c:forEach var="freeVO" items="${list }">
        <c:set var="freeno" value ="${freeVO.freeno }" /> 
        <c:set var="freedate" value ="${freeVO.freedate.substring(0,10)}" />
       
        <tr>
          <td style='vertical-align: middle;'> ${freeno}</td>
          <c:choose>
            <c:when test="${fn:contains(freedate, date)}">
              <td style='vertical-align: middle;'>
                <A href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeno}&word=${param.word}&search=${param.search}'> ${freeVO.freetitle }</A>
                <IMG src="${pageContext.request.contextPath}/nonuser/free/images/056.gif">
              </td>
            </c:when>
            <c:otherwise>
              <td style='vertical-align: middle;'>
                <A href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeno}&word=${param.word}&search=${param.search}'> ${freeVO.freetitle }</A>
              </td>
            </c:otherwise>
          </c:choose>
          <td style='vertical-align: middle;'> ${freeVO.freename} ${freeVO2.freeno}</td>
          <td style='vertical-align: middle;'> ${freedate }</td>
          <td style='vertical-align: middle;'> ${freeVO.freecnt } </td>
          <td style='vertical-align: middle;'> ${freeVO.freelike } </td>
        </tr>
        
        </c:forEach>
      </tbody>
      <!--------------------------------- 게시판 목록 종료 --------------------------------->
  </TABLE>
  
  <ASIDE style='float: left;'>
     <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/free/create.do?word=${param.word}&search=${param.search}'">등록</button>
      <c:choose>
        <c:when test="${param.word != null && param.word != '' }"> <!-- 검색했을 경우 목록 버튼 생성 -->
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/nonuser/free/list.do'">목록</button>
        </c:when>
      </c:choose>      
  </ASIDE>
  
  
  <ASIDE style='float: right;'>
    <!----------------- 검색 옵션 설정 시작 ------------------------->
    <select name='search' id='search'>
      <option value='freetitle' ${param.search eq "freetitle" ? "selected" :""} >제목</option>
      <option value='freename' ${param.search eq "freename" ? "selected" :""}>작성자</option>
      <option value='freecontent' ${param.search eq "freecontent" ? "selected" :""}>내용</option>
      <option value='freetn' ${param.search eq "freetn" ? "selected" :""}>제목 및 작성자</option>
    </select>
    <!----------------- 검색 옵션 설정 종료 ------------------------->
       
    <c:choose>
      <c:when test="${param.word != '' }"> <!-- 검색했을때 검색창에 검색어 유지 -->
        <input type="search" name="word" id="word" size=12 value='${param.word }' style="height: 30px; margin: 0px auto;">
      </c:when>
      <c:otherwise> <!-- 검색하지 않았을 때 검색창 -->
        <input type="search" name="word" id="word" size=12 placeholder="검색어를 입력해주세요." style="height: 30px; margin: 0px auto;">
      </c:otherwise>
    </c:choose>
    <button type="submit" class="btn btn-default">검색</button>
  </ASIDE> 
  
  <DIV style="width: 100%; padding-top: 30px; display: table; padding-left: 50%; margin: 0px auto;">
    ${paging }
  </DIV>
  
  </form>
</ASIDE>

 <!-------------------------------------------------------------------------------------------------------------->
<!--                       Part 2. 실시간 조회수 + 추천수 베스트목록                                                 -->
<!--------------------------------------------------------------------------------------------------------------> 
<ASIDE style="float: right; padding-top: 90px; width: 22%;">
  <form name='frm_like' id='frm' style="margin-bottom: 90px;" action="./list.do" method="get">
      
    <!----------------------------- ① 실시간 추천수 관련 시작 ----------------------------->
    <DIV style="text-align: left; font-size: 15px; padding-bottom: 15px;"><IMG src="${pageContext.request.contextPath}/nonuser/free/images/now.png"> 실시간 급상승 추천글</DIV>
    <DIV id="like_max" class="news" style="padding-top:0;">   
      <div class="open-event fl" style="width: 100%;">
        
        <!-- 실시간 추천수 Best 3 목록 출력 시작 -->
        <ul class="notice-list" > 
          <c:forEach var="freeVO2" items="${list_like }">
          <c:set var="freeno1" value="${freeVO2.freeno}"/>
          <li class="li_none" id="panel${freeVO2.freeno}" style="margin: 0px;"><span style="font-weight: bold; padding-right: 5px; color: #fbc90e;">${freeno1 }</span>
            <A href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeVO2.freeno}&word=${param.word}&search=${param.search}' style="width: 50%;">${freeVO2.freetitle }</A>
          </li>
          </c:forEach> 
        </ul>
        <!-- 실시간 추천수 Best 3 목록 출력 종료 -->
        
       <!-- 목록 UP / DOWN 버튼 시작 -->
      <span id="bt5"> 
        <a href="#" class="prev"><span class="glyphicon glyphicon-chevron-up" aria-hidden="true" style="line-height: 0px; font-size: 5px; top: 3px;"></span></a>
        <a href="#" class="next"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="line-height: 0px; font-size: 5px;"></span></a>
      </span>
      <!-- 목록 UP / DOWN 버튼 종료 -->
      
      </div>
    </DIV>
    <script type="text/javascript">fn_article3('like_max','bt5',true);</script> <!-- 한줄씩 자동으로 출력되도록 하는 javascript -->
    <!----------------------------- ① 실시간 추천수 관련 종료 ----------------------------->
  
    <!----------------------------- ② 실시간 조회수 관련 시작 ----------------------------->
    <DIV style="text-align: left; font-size: 15px; padding-bottom: 15px; padding-top: 20px;"><IMG src="${pageContext.request.contextPath}/nonuser/free/images/now.png"> 실시간 급상승 조회글</DIV>
      <DIV id="cnt_max" class="news" style="padding-top:0;">   
        <div class="open-event fl" style="width:100%;">
        
          <!-- 실시간 조회수 Best 3 목록 출력 시작 -->
          <ul class="notice-list" >
            <c:forEach var="freeVO3" items="${list_cnt }">
            <li class="li_none" id="panel${freeVO3.freeno}" style="margin: 0px;"><span style="font-weight: bold; color: #fbc90e; padding-right: 5px;">${freeVO3.freeno}</span>
              <A href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeVO3.freeno}&word=${param.word}&search=${param.search}'>${freeVO3.freetitle }</A>
            </li>
            </c:forEach> 
          </ul>
          <!-- 실시간 조회수 Best 3 목록 출력 종료 -->
          
          <!-- 목록 UP / DOWN 버튼 시작 -->
          <span id="bt6">
            <a href="#" class="prev"><span class="glyphicon glyphicon-chevron-up" aria-hidden="true" style="line-height: 0px; font-size: 5px; top: 3px;"></span></a>
            <a href="#" class="next"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="line-height: 0px; font-size: 5px;"></span></a>
          </span>
          <!-- 목록 UP / DOWN 버튼 종료 -->
          
        </div>
    </DIV>
    <script type="text/javascript">fn_article3('cnt_max','bt6',true);</script> <!-- 한줄씩 자동으로 출력되도록 하는 javascript -->
    <!----------------------------- ② 실시간 조회수 관련 종료 ----------------------------->
   </form>
</ASIDE>

</DIV>

</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>