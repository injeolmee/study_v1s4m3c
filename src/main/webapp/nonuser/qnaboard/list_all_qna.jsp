<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>QnA</title>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">
$(function(){   
  
}); // function 끝
</script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>
  <DIV style="overflow: auto; width: 810px; height: 155px; margin: 0px auto; padding-left: 20%; background: url(./images/sub_bg01.gif) top right no-repeat; display: inline; float: left;">
    <H3 style="margin-top: 30px; margin-bottom: 20px; line-height: 34px; font-size: 34px;">QnA 게시판</H3>
    <p style="line-height: 20px; font-size: 13px; text-align: left; color: #000;">
    궁금하신 내용을 검색해 주세요.<br>
    잘 살펴보시고 더 궁금한 점이 있으시면 문의하기를 이용해주세요.
    </p>
  </DIV>
  <form name="frm" id="frm" method="get" action="./list_all_qna.do">
  <DIV style="position: relative; overflow: hidden; width: 55%; text-align: center; margin: 0px auto; border: 1px solid #DBDBDB; padding: 15px 0; background: #f6f6f6;">
    <c:choose>
      <c:when test="${qnatitle != '' }">
        <span style="display: inline-block; height: 28px; margin-left: 10px; vertical-align: middle;">
          <input type="text" id="qnatitle" name="qnatitle" value="${qnatitle }">
        </span>
      </c:when>
      <c:otherwise>
        <span style="display: inline-block; height: 28px; margin-left: 10px; vertical-align: middle;">
          <input type="text" id="qnatitle" name="qnatitle" value="">
        </span>
      </c:otherwise>
    </c:choose>
    <button type="submit" class="btn btn-default btn-sm" style="line-height: 15px;">Search</button>
    <button type="button" class="btn btn-default btn-sm" style="line-height: 15px;" onclick="location.href='/study/user/qnaboard/create.do'">문의하기</button>
  </DIV>
  </form>
  <table style="width: 55.3%; border-collapse: collapse; margin: 0px auto;">
    <colgroup>
      <col width="5%">
      <col width="15%">
      <col width="46%">
      <col width="10%">
      <col width="14%">
      <col width="10%">
    </colgroup>
    <thead style="vertical-align: middle; border-color: inherit; background-color: #FFF; color: #000; line-height: 25px; border-bottom: 2px solid #dedede;">
      <tr>
        <td scope="col" style="text-align: center;">번호</td>
        <td scope="col" style="text-align: center;">분류</td>
        <td scope="col" style="text-align: center;">제목</td>
        <td scope="col" style="text-align: center;">작성자</td>
        <td scope="col" style="text-align: center;">등록일</td>
        <td scope="col" style="text-align: center;">조회수</td>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="qnaVO" items="${list }">
        <tr style="background-color: #FFF; color: #000; border-bottom: 1px solid #dedede; font-size: 16px; line-height:35px">
          <td style="vertical-align: middle; text-align: center;">
            ${qnaVO.qnano }
          </td>
          <td style="vertical-align: middle; text-align: center;">
            ${qnaVO.qnagrp }
          </td>
          <td style="vertical-align: middle; text-align: left;">
            <A href="/study/nonuser/qnaboard/read.do?qnano=${qnaVO.qnano }">${qnaVO.qnatitle }</A>
            <%-- <c:choose>
              <c:when test="${qnaVO.qnapwd != '' }">
                <img src="./images/lock.png" style="padding-bottom: 4px;">
              </c:when>
              <c:otherwise>
                <img src="./images/unlock.png" style="padding-bottom: 4px;">
              </c:otherwise>
            </c:choose> --%>
          </td>
          <td style="vertical-align: middle; text-align: center;">
            ${qnaVO.wname }
          </td>
          <td style="vertical-align: middle; text-align: center;">
            ${qnaVO.qdate.substring(0, 10) }
          </td>
          <td style="vertical-align: middle; text-align: center;">
            ${qnaVO.qnacnt }
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <DIV class="cb t10"></DIV>
  <div class= "bottom_menu">${paging }</div>
 <!--  <div class="modal fade modal-sm" id="myModal" role="dialog" style='position: absolute; top:50%; left:58%; display: none;'>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">패스워드 검사</h4>
      </div>
      <div class="modal-body" style='text-align: center; height: 70px;'>
        <label style='float: left; margin: 5px 0px 5px 5px;'><strong>패스워드</strong></label>
        <input type="password" id="modal_pwd" name="modal_pwd" value="" style='padding: 6px; margin: 0px; width:50%;' >
        <input type="text" style="display: none;">
        <div id="panel_pwd" style= "text-align: center; padding: 15px; font-weight: 900;"></div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" id="read_btn">확인</button>
        <button class="btn" data-dismiss="modal">닫기</button>    
      </div>
    </div>
  </div> -->
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>