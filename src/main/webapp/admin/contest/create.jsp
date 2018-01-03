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
<title>CONTEST</title>
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

<DIV style='width: 60%; margin: 0px auto; text-align: center;'>
  <ASIDE style='float: left;'>
    <button type='button' class='btn btn-default' onclick="location.href='/study/nonuser/contest/list_all_contest.do'">공모전 목록</button>
  </ASIDE>
  
  <ASIDE style='float: right; width: 36%;'>
    <!-- <select id='keyWord' name='keyWord' style='margin-bottom: 10px; height: 32px;'>
      <option value='all' selected="selected">전 체</option>
      <option value='conCont'>내 용</option>
      <option value='memname'>작성자</option>
    </select> -->
    <c:choose>
      <c:when test="${param.conWord != '' }">
        <input type='text' name='conWord' id='conWord' value='${param.conWord}' style='width: 50%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='conWord' id='conWord' value='' style='width: 50%;'>
      </c:otherwise>
    </c:choose>
    <button class="btn btn-success" type='submit' style='margin-bottom: 10px;'>검색</button>
    <button type='button' class='btn btn-info' onclick="location.href='javascript:location.reload();'" style='margin-bottom: 10px;'>
        새로고침
    </button>
  </ASIDE> 
  </DIV>
  <div class='menu_line' style='margin: 0px auto; text-align: center; width: 60%; clear: both;'></div>

<DIV style='color: #333333; width: 20%; margin: 20px auto; padding: 3px; border-bottom: solid 2px #555555; text-align: center;'>공모전 등록</DIV>
<FORM name='frm' method='POST' action='/study/admin/contest/create.do'
               enctype="multipart/form-data" class="form-horizontal" style='text-align: center; margin: 0px auto; width: 50%;'> 
  <input type='hidden' name='adminno' id='adminno' value='${sessionScope.adminno }'>
  <!-- <input type='hidden' name='memberno' id='memberno' value='1'> -->
  
  <DIV class = "form-group form-group-sm">
    <label for='conTitle' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>제 목</label>
    <DIV class="col-md-6" style='width: 65%;'>
      <input type='text' class="form-control input-lg" name='conTitle' id='conTitle' value='공모전 제목' required="required">
    </DIV>
  </DIV>
  <!-- <DIV style="clear: both; padding-top: 10px !important;"></DIV> -->
  <DIV class = "form-group form-group-sm">
    <label for='conUrl' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>홈페이지</label>
    <DIV class="col-md-6" style='width: 65%;'>
      <input type='text' class="form-control input-lg" name='conUrl' id='conUrl' value='http://' required="required">
    </DIV>
  </DIV>
  
  <DIV class = "form-group form-group-sm">
    <label for='conUrl' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>YouTube</label>
    <DIV class="col-md-6" style='width: 65%;'>
      <input type='text' class="form-control input-lg" name='conYou' id='conYou' value='http://' required="required">
    </DIV>
  </DIV>
  <!-- <DIV style="clear: both; padding-top: 10px !important;"></DIV> -->
  <DIV class = "form-group form-group-sm">
    <label for='conHost' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>주 관</label>
    <DIV class="col-md-6" style='width: 25%;'>
      <input type='text' class="form-control input-lg" name='conHost' id='conHost' value='공모전 주관' required="required">
    </DIV>
    <label for="conWord" class="col-md-6 control-label" style='width: 8.6%; padding-left: 1%;'>검색어</label>
    <div class="col-md-6" style='width: 25%;'>
      <input type='text' class="form-control input-lg" name='conWord' id='conWord' value='검색어를 입력해주세요.'>
    </div>
  </DIV>  
  <!-- <DIV style="clear: both; padding-top: 10px !important;"></DIV> -->
  <DIV class = "form-group form-group-sm">
    <label for='conStart' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>시작일</label>
    <DIV class="col-md-6" style='width: 25%;'>
      <input type='date' class="form-control input-lg" name='conStart' id='conStart' value='시작일' required="required">
    </DIV>
    <label for='conEnd' class='col-md-4 control-label' style='width: 8.6%; padding-left: 1%;'>종료일</label>
    <DIV class="col-md-6" style='width: 25%;'>
      <input type='date' class="form-control input-lg" name='conEnd' id='conEnd' value='종료일' required="required">
    </DIV>
  </DIV>
  
  <div class="form-group">   
    <label for="conCont" class="col-md-6 control-label" style='width: 13%; padding-left: 1%;'>내 용</label>
    <div class="col-md-6" style='width: 64.5%;'>
      <textarea class="form-control input-md" name='conCont' id='conCont' rows='12' cols='12'>공모전 정보 내용</textarea>
      <br>
    </div>
  </div>
     
  <div class="form-group form-group-sm">   
    <label for="file1MF" class="col-md-4 control-label" style='width: 13.3%; padding-left: 1%;'>썸네일</label>
    <div class="col-md-6" style="width: 65%;">
      <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' style="width: 100%;">
    </div>
  </div>  
  
  <DIV style="clear: both; padding-top: 10px !important;"></DIV>
  
  <div class="form-group form-group-sm">
    <label for="file2MF" class="col-md-4 control-label" style='width: 13.3%; padding-left: 1%;'>이미지</label>
    <div class="col-md-6" style="width: 65%;">
      <input type="file" class="form-control input-lg" name='file2MF' id='file2MF' style="width: 100%;">
    </div>
  </div>
  
  <DIV style="clear: both; padding-top: 10px !important;"></DIV>
  
  <div class="form-group form-group-sm">
    <label for="file3MF" class="col-md-4 control-label" style='width: 13.3%; padding-left: 1%;'>첨부파일</label>
    <div class="col-md-6" style="width: 65%;">
      <input type="file" class="form-control input-lg" name='file3MF' id='file3MF' style="width: 100%;">
    </div>
  </div>
  
  <DIV style="clear: both; padding-top: 10px !important;"></DIV>
  
  <div class="form-group form-group-sm">    
    <DIV style='margin: 0px auto; text-align: center; width:50%; padding-top: 10px;'>
      <button class='btn btn-primary' type="submit">등록</button>
      <button class='btn btn-danger' type="button" onclick="location.href='javascript: history.back()'">취소[목록]</button>
    </DIV>
  </div>
</FORM>
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>