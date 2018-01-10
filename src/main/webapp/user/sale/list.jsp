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
<DIV class='content' style='width: 70%; margin: 0px auto; '>  

<DIV class="title_no_line" style="text-align: center; margin-bottom: 30px;">
  <IMG src="${pageContext.request.contextPath}/user/sale/images/saletitle2.png">
</DIV>

<form name='frm' id='frm' action="./list.do" method="get" style="padding-top: 20px;">

  <TABLE class="table table-striped" style='width: 100%; margin: 30px 30px 30px 30px auto;'>
      
      <colgroup style="text-align: center;">
        <col style="width: 10%;"></col>
        <col style="width: 40%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      
      <!----------------- 게시판 상위 목록 --------------------------->
      <thead style='font-size: 14px;'>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일</th>
          <th>조회수</th>
          <th>상태</th>
        </tr>
      </thead>
      <!----------------------------------------------------------------->
      
      <jsp:useBean id="date" class="java.util.Date"/>
      <fmt:formatDate value="${date }" pattern="yyyy-MM-dd" var="date"/>
      
      <!------------------------------------ 게시판 내용 --------------------------------->
      <tbody>
        <c:forEach var="saleVO" items="${list }">
        <c:set var="saleno" value ="${saleVO.saleno }" /> 
        <c:set var="saledate" value ="${saleVO.saledate.substring(0,10)}" />
       
        <tr> 
          <!---------------------- 판매가 완료된 상품일 경우 --------------------->
          <c:if test="${saleVO.saleseqno == 1}">
            <td style='vertical-align: middle; background-color: #ebebe0;'> ${saleno}</td>
            <c:choose>
              <c:when test="${fn:contains(saledate, date)}"> <!-- 현재날짜 == 등록날짜 -->
                <td style='vertical-align: middle; background-color: #ebebe0;'>
                  <A href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleno}&word=${param.word}&search=${param.search}'> ${saleVO.saletitle }</A>
                  <IMG src="${pageContext.request.contextPath}/user/sale/images/056.gif">
                </td>
              </c:when>
              <c:otherwise>
                <td style='vertical-align: middle; background-color: #ebebe0;'>
                  <A href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleno}&word=${param.word}&search=${param.search}'> ${saleVO.saletitle }</A>
                </td>
              </c:otherwise>
            </c:choose>
            <td style='vertical-align: middle; background-color: #ebebe0;'> ${saleVO.salename}</td>
            <td style='vertical-align: middle; background-color: #ebebe0;'> ${saledate }</td>
            <td style='vertical-align: middle; background-color: #ebebe0;'> ${saleVO.salecnt } </td>
            <td style='vertical-align: middle; background-color: #ebebe0;'>
              <IMG src="${pageContext.request.contextPath}/user/sale/images/end.png">
            </td>
          </c:if>
           
          <!----------------- 판매중인 상품일 경우 --------------------------->
          <c:if test="${saleVO.saleseqno == 0}">
            <td style='vertical-align: middle;'> ${saleno}</td>
            <c:choose>
              <c:when test="${fn:contains(saledate, date)}"> <!-- (현재날짜 == 등록날짜) -->
                <td style='vertical-align: middle;'>
                  <A href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleno}&word=${param.word}&search=${param.search}'> ${saleVO.saletitle }</A>
                  <IMG src="${pageContext.request.contextPath}/user/sale/images/056.gif">
                </td>
              </c:when>
              <c:otherwise>
                <td style='vertical-align: middle;'>
                  <A href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleno}'> ${saleVO.saletitle }</A>
                </td>
              </c:otherwise>
            </c:choose>
            <td style='vertical-align: middle;'> ${saleVO.salename}</td>
            <td style='vertical-align: middle;'> ${saledate }</td>
            <td style='vertical-align: middle;'> ${saleVO.salecnt } </td>
            <td style='vertical-align: middle;'> 
              <IMG src="${pageContext.request.contextPath}/user/sale/images/ing.png">
            </td>
          </c:if> 
        </tr>
      </c:forEach>
      </tbody>
  </TABLE>
  
  <ASIDE style='float: left;'>
     <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/create.do'">등록</button>
     <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/list_grid.do?search=${param.search}&word=${param.word}'">앨범형</button>
     <c:choose>
        <c:when test="${param.word != null && param.word != '' }"> <!-- 검색했을 경우 목록 버튼 생성 -->
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/list.do'">전체보기</button>
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
    <!------------------------------------------------------------>
       
    <c:choose>
      <c:when test="${param.word != '' }"> <!-- 검색했을때 검색어 유지 -->
        <input type="search" name="word" id="word" size=12 value='${param.word }' style="height: 30px; margin: 0px auto;">
      </c:when>
      <c:otherwise> <!-- 검색하지 않았을 때 검색창 -->
        <input type="search" name="word" id="word" size=12 style="height: 30px; margin: 0px auto;">
       </c:otherwise>
    </c:choose>
    <button type="submit" class="btn btn-default">검색</button>
  </ASIDE> 
  
  <DIV style="width: 100%; padding-top: 30px; display: table; padding-left: 50%; margin: 0px auto; ">
     ${paging}
  </DIV>
  
</form>


</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>