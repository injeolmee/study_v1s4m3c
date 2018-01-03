<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>QnA</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content' style='margin-bottom: 50px;'>

<!-- 질문, 분류, 첨부파일, 질문내용 -->
<DIV style='color: #333333; width: 20%; margin: 20px auto; padding: 3px; border-bottom: solid 2px #555555; text-align: center;'>질문 수정</DIV>
<FORM name='frm' method='POST' action='/study/user/qnaboard/update.do'
               enctype="multipart/form-data" class="form-horizontal" style='text-align: center; margin: 0px auto; width: 50%;'>
  <input type="hidden" name="qnano" id="qnano" value="${qnaVO.qnano }"> 
  <!-- <input type='hidden' name='memberno' id='memberno' value='1'> -->
  
  <DIV class = "form-group form-group-sm">
    <label for='qnatitle' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>질문 제목</label>
    <DIV class="col-md-6" style='width: 65%;'>
      <input type='text' class="form-control input-lg" name='qnatitle' id='qnatitle' value='${qnaVO.qnatitle }' required="required">
    </DIV>
  </DIV>
  <!-- <DIV style="clear: both; padding-top: 10px !important;"></DIV> -->
  <DIV class = "form-group form-group-sm">
    <label for='qnagrp' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>질문 분류</label>
    <DIV class="col-md-6" style='width: 72%;'>
      <input type="radio" name="qnagrp" id="qnagrp" value="스터디 그룹">스터디그룹&nbsp;&nbsp;
      <input type="radio" name="qnagrp" id="qnagrp" value="스터디 룸">스터디룸&nbsp;&nbsp;
      <input type="radio" name="qnagrp" id="qnagrp" value="공모전 정보">공모전정보&nbsp;&nbsp;
      <input type="radio" name="qnagrp" id="qnagrp" value="채용 정보">채용정보&nbsp;&nbsp;&nbsp;&nbsp;
    </DIV>
  </DIV>
  
  <DIV class="cb t10"></DIV>
  
  <div class="form-group">   
    <label for="qnacont" class="col-md-6 control-label" style='width: 13%; padding-left: 1%;'>질문 내용</label>
    <div class="col-md-6" style='width: 64.5%;'>
      <textarea class="form-control input-md" name='qnacont' id='qnacont' rows='12' cols='12'>${qnaVO.qnacont }</textarea>
      <br>
    </div>
  </div>
     
  <div class="form-group form-group-sm">   
    <label for="file1MF" class="col-md-4 control-label" style='width: 13.3%; padding-left: 1%;'>첨부파일</label>
    <div class="col-md-6" style="width: 65%;">
      <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' style="width: 100%;">
    </div>
  </div>  
  
  <DIV style="clear: both; padding-top: 15px !important;"></DIV>
  
  <DIV class = "form-group form-group-sm">
    <label for='wname' class='col-md-2 control-label' style='width: 12%;'>작성자</label>
    <DIV class="col-md-6" style='width: 10%;'>
      <input type='text' class="form-control input-sm" name='wname' id='wname' value='${qnaVO.wname }' required="required">
    </DIV>
    <DIV style='margin: 0px auto; text-align: center; width:80%; padding-top: 10px;'>
      <button class='btn btn-default' type="submit">수정</button>
      <button class='btn btn-default' type="button" onclick="location.href='javascript: history.back()'">취소[목록]</button>
    </DIV>
  </DIV>
</FORM>
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>