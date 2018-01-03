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
<DIV class='content' style='width: 80%;'>  

  <FORM name='frm' id='frm' action='./list_grid.do'>
  
    <IMG src="${pageContext.request.contextPath}/user/shared/images/sharedtitle_photo.png">
    <div class='menu_line' style='clear: both; width: 100%;'></div>
                
    <!------------------------------------------------ 리스트 출력 시작 ------------------------------------------------>             
    <div style='position: center; width: 100%; margin: 30px 0px 30px 0px auto;'> 
      <jsp:useBean id="date" class="java.util.Date"/>
      <fmt:formatDate value="${date }" pattern="yyyy-MM-dd" var="date"/>
      
      <c:forEach var="sharedVO" items="${list_grid}">
        
        <div class='div_grid_img' style="width: 27%; height: 300px; margin: 30px 3% 0px 3%; background-color:#ffffff; "> <!-- lavender left: 22px -->
          <c:choose>
            <c:when test="${sharedVO.sharedtum != null}"> <!-- 이미지 파일인 경우 -->
              <IMG id='sharedtum' src='${pageContext.request.contextPath}/user/shared/storage/${sharedVO.sharedtum}' style='width: 100%; height: 200px; margin: 0px 0px 6px 0px;'>
            </c:when>
            <c:when test="${sharedVO.sharedyoutube != null }"> <!-- Youtube 인 경우 -->
              <IMG id='sharedtum' src='${pageContext.request.contextPath}/user/shared/images/play.jpg' style='width: 100%; height: 200px; margin: 0px 0px 6px 0px;'>
            </c:when>
            <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->
              <IMG id='sharedtum' src='${pageContext.request.contextPath}/user/shared/images/none.png' style='width: 100%; height: 200px; margin: 0px 0px 6px 0px;'>
            </c:otherwise>
          </c:choose>
          
          <div style="width: 100%; line-height: 30px;">
            <span style='font-size: 12px; font-weight: 550; margin: 0px 0px 0px 3px;'>
              <A href="${pageContext.request.contextPath}/user/shared/read.do?sharedno=${sharedVO.sharedno}&word=${param.word}&search=${param.search}">${sharedVO.sharedtitle}</A> <!-- 글 제목 출력창 -->
            </span>
            <span style='font-size: 12px; margin: 0px 10px 0px 10px;'>
              <c:if test="${fn:contains(sharedVO.shareddate, date)}"> <!-- 현재날짜 == 등록날짜 -->
                <IMG src="${pageContext.request.contextPath}/user/shared/images/056.gif">
              </c:if>
            </span>
          </div>
       
          <div style='border-bottom: dotted 1px #777777; margin: 5px 0px 5px 0px;'></div>
            <span style='font-size: 12px; float: left;'>
              ${sharedVO.shareddate.substring(0,10)}
            </span>
            <span style='font-size: 12px; float: right;'>
              ${sharedVO.sharedname} 
            </span>
        </div> 
      </c:forEach>
      
    </div>  
    <!------------------------------------------------ 리스트 출력 종료 ------------------------------------------------> 
    
    <DIV class='bottom_menu' >
    
      <ASIDE style='float: left;'>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/create.do'">등록</button>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/list.do?search=${param.search}&word=${param.word}'">목록형</button>
        <c:choose>
          <c:when test="${param.word != null && param.word != '' }"> <!-- 검색했을 경우 목록 버튼 생성 -->
            <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/list_grid.do'">전체보기</button>
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
            <input type="search" name="word" id="word" size=12 style="height: 30px; margin: 0px auto;">
           </c:otherwise>
        </c:choose>
        <button type="submit" class="btn btn-default">검색</button>
      </ASIDE>
  
      <!-- 페이징 부분 -->
      <DIV style="padding-top: 70px;"> 
        ${paging_grid }
      </DIV>  
  
    </DIV>  
  </FORM>
  
</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>