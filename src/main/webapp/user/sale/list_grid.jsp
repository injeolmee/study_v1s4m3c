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
<link href="${pageContext.request.contextPath}/user/sale/gnacss/style.css" rel="Stylesheet" type="text/css">
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
  <form name='frm'  id='frm' action='./list_grid.do'>
   
    <IMG src="${pageContext.request.contextPath}/user/sale/images/saletitle_photo.png">
    <div class='menu_line' style='clear: both; width: 100%;'></div>
                   
    <div style='position: center; width: 100%; margin: 30px 0px 30px 0px auto;'> <!-- 리스트 출력 시작 -->
      <jsp:useBean id="date" class="java.util.Date"/>
      <fmt:formatDate value="${date }" pattern="yyyy-MM-dd" var="date"/>
      
      <c:forEach var="saleVO" items="${list_grid}">
        
        <div class='div_grid_img' style="width: 27%; height: 300px; margin: 30px 3% 0px 3%; background-color:#ffffff; "> <!-- lavender left: 22px -->
          <c:choose>
            <c:when test="${saleVO.saletum != null}"> <!-- 이미지 파일인 경우 -->
              <IMG id='saletum' src='${pageContext.request.contextPath}/user/sale/storage/${saleVO.saletum}' style='width: 80%; height: 200px; padding-left: 30px; padding-right: 30px; text-align: cneter; margin: 0px 0px 6px 0px;'>
            </c:when>
            <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->
              <IMG id='saletum' src='${pageContext.request.contextPath}/user/sale/images/none.png' style='width: 80%; height: 200px; padding-left: 30px; padding-right: 30px; text-align: cneter; margin: 0px 0px 6px 0px;'>
            </c:otherwise>
          </c:choose>
          
            <div style="width: 100%; line-height: 30px; text-align: left;">
              <span style='font-size: 12px; font-weight: 550; margin: 0px 0px 0px 3px;'>
                <A href="${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleVO.saleno}&word=${param.word}&search=${param.search}">${saleVO.saletitle}</A> <!-- 글 제목 출력창 -->
              </span>
              <span style='font-size: 12px; margin: 0px 10px 0px 10px;'>
                <c:if test="${fn:contains(saleVO.saledate, date)}"> <!-- 현재날짜 == 등록날짜 -->
                  <IMG src="${pageContext.request.contextPath}/user/sale/images/056.gif">
                </c:if>
              </span>
            </div>
       
            <div style='border-bottom: dotted 1px #777777; margin: 5px 0px 5px 0px;'></div>
              <span style='font-size: 12px; float: left;'>
                ${saleVO.saledate.substring(0,10)}
              </span>
              <span style='font-size: 12px; float: right;'>
                ${saleVO.salename} 
              </span>
              
            <div style='margin-bottom: 6px;'></div>
        </div> <!-- 리스트 출력 종료 -->
      </c:forEach>
    </div>  
    
  <DIV class='bottom_menu' >
    <ASIDE style='float: left;'>
     <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/create.do'">등록</button>
     <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/list.do?search=${param.search}&word=${param.word }'">목록형</button>
     <c:choose>
      <c:when test="${param.word != null && param.word != '' }"> <!-- 검색했을 경우 목록 버튼 생성 -->
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/list_grid.do'">전체보기</button>
      </c:when>
    </c:choose>
    </ASIDE>
  
    <ASIDE style='float: right;'>
    <!----------------- 검색 옵션 설정 ------------------------->
      <select name='search' id='search'>
        <option value='saletitle' ${param.search eq "saletitle" ? "selected" :""} >제목</option>
        <option value='salename' ${param.search eq "salename" ? "selected" :""}>작성자</option>
        <option value='salecontent' ${param.search eq "salecontent" ? "selected" :""}>내용</option>
        <option value='saletn' ${param.search eq "saletn" ? "selected" :""}>제목 및 작성자</option>
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
  
    <DIV style="padding-top: 70px;"> <!-- 페이징 부분 -->
      ${paging_grid }
    </DIV>  
  
  </DIV>  
 </form>
  
</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>