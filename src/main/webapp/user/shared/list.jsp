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
<link href="${pageContext.request.contextPath}/user/shared/gnacss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!---------------------------------------------------------------------->
<script type="text/javascript">
$(function(){
  
});

</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 70%; margin: 0px auto; '>  

  <DIV class="title_no_line" style="text-align: center; margin-bottom: 30px;">
    <IMG src="${pageContext.request.contextPath}/user/shared/images/sharedtitle2.png" style="height: 160px; width: 850px;">
  </DIV>

  <FORM name='frm' id='frm' action="./list.do" method="get" style="padding-top: 20px;">

    <!------------------------------------------------------------------------------------------------>
    <!--                                Shared 게시판 목록 TABLE 시작                                     -->
    <!------------------------------------------------------------------------------------------------>
    <TABLE class="table table-striped" style='width: 100%; margin: 30px 30px 30px 30px auto;'>
      
      <colgroup style="text-align: center;">
        <col style="width: 10%;"></col>
        <col style="width: 50%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      
      <!----------------- 게시판 상위 메뉴 시작 --------------------------->
      <thead style='font-size: 14px;'>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일</th>
          <th>조회수</th>
        </tr>
      </thead>
      <!----------------- 게시판 상위 메뉴 종료 --------------------------->
      
      <jsp:useBean id="date" class="java.util.Date"/>
      <fmt:formatDate value="${date }" pattern="yyyy-MM-dd" var="date"/>
      
      <!--------------------------------- 게시판 목록 출력 시작 --------------------------------->
      <tbody>
        
        <c:forEach var="sharedVO" items="${list }">
        <c:set var="sharedno" value ="${sharedVO.sharedno }" /> 
        <c:set var="shareddate" value ="${sharedVO.shareddate.substring(0,10)}" />
       
        <tr>
          <td style='vertical-align: middle;'> ${sharedno}</td>
          <!----- 현재 날짜와 등록한 날짜가 같으면 이미지 출력 하는 처리 시작 ----->
          <c:choose>
            <c:when test="${fn:contains(shareddate, date)}"> 
              <td style='vertical-align: middle;'>
                <A href='${pageContext.request.contextPath}/user/shared/read.do?sharedno=${sharedno}&word=${param.word}&search=${param.search}'>${sharedVO.sharedtitle }</A>
                <IMG src="${pageContext.request.contextPath}/user/shared/images/056.gif">
              </td>
            </c:when>
            <c:otherwise>
              <td style='vertical-align: middle;'>
                <A href='${pageContext.request.contextPath}/user/shared/read.do?sharedno=${sharedno}&word=${param.word}&search=${param.search}'> ${sharedVO.sharedtitle }</A>
              </td>
            </c:otherwise>
          </c:choose>
          <!----- 현재 날짜와 등록한 날짜가 같으면 이미지 출력 하는 처리 종료 ----->
          <td style='vertical-align: middle;'> ${sharedVO.sharedname}</td>
          <td style='vertical-align: middle;'> ${shareddate }</td>
          <td style='vertical-align: middle;'> ${sharedVO.sharedcnt } </td>
        </tr>
        </c:forEach>
        <!--------------------------------- 게시판 목록 출력 종료 --------------------------------->
      </tbody>
      
    </TABLE>
    <!------------------------------------------------------------------------------------------------>
    <!--                                Shared 게시판 목록 TABLE 종료                                     -->
    <!------------------------------------------------------------------------------------------------>
    
  
    <ASIDE style='float: left;'>
      <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/create.do'">등록</button>
      <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/list_grid.do?search=${param.search}&word=${param.word}'">앨범형</button>
      <c:choose>
        <c:when test="${param.word != null && param.word != '' }"> <!-- 검색했을 경우 목록 버튼 생성 -->
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/list.do'">전체보기</button>
        </c:when>
      </c:choose>
    </ASIDE>
  
    <ASIDE style='float: right;'>
      <!----------------- 검색 옵션 설정 ------------------------->
      <select name='search' id='search'>
        <option value='sharedtitle' ${param.search eq "sharedtitle" ? "selected" :""} >제목</option>
        <option value='sharedname' ${param.search eq "sharedname" ? "selected" :""}>작성자</option>
        <option value='sharedcontent' ${param.search eq "sharedcontent" ? "selected" :""}>내용</option>
        <option value='sharedtn' ${param.search eq "sharedtn" ? "selected" :""}>제목 및 작성자</option>
      </select>
       
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
  
    <!-- 페이징 처리 부분 -->
    <DIV style="width: 100%; padding-top: 30px; display: table; padding-left: 50%; margin: 0px auto;">
      ${paging }
    </DIV>
  
  </FORM>

</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>