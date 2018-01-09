<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% 
String root = request.getContextPath(); 
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
 
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/javascript" src="<%=root %>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckfinder/ckfinder.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/javascript">
window.onload=function(){
  CKEDITOR.replace('ncontent');  // <TEXTAREA>태그 id 값
};

$(function(){
   
});

</script>
 
<script type="text/javascript">
</script>
</head>
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'> 

  <div style='text-align:center;'>
      <FORM name='frm' method='POST' action='./notice_update.do' class="form-horizontal">
      <input type='hidden' name='adminno' id='adminno' value='${sessionScope.adminno }'>        
      <input type='hidden' name='nname' id='nname' value='${sessionScope.admname }'>        
      <input type='hidden' name='noticeno' id='noticeno' value='${noticeVO.noticeno }'>        
      <input type='hidden' name='word' id='word' value='${noticeVO.word }'>        
      <input type='hidden' name='search' id='search' value='${noticeVO.search }'>        
      <input type='hidden' name='nowPage' id='nowPage' value='${nowPage }'>        
      
      <div class="form-group">   
        <label for="ntitle" class="col-md-offset-1 col-md-1 control-label" style='text-align: center; padding-left: 5%;'>제목</label>
        <div class="col-md-7">
          <input type='text' class="form-control input-md" name='ntitle' id='ntitle' value='${noticeVO.ntitle}' required="required">
        </div>        
      </div>
      <div class="form-group">   
        <label for="nseqno" class="col-md-offset-1 col-md-1 control-label" style='text-align: center; padding-left: 5%;'>출력순서</label>
        <div class="col-md-7" style='text-align: left;'>
          <input type='number' class="form-control input-md" name='nseqno' id='nseqno' value='${noticeVO.nseqno}' required="required" style='width:20%;'>
        </div>        
      </div>   
      <div class="form-group">   
        <div class="col-md-offset-1 col-md-9" style='padding-left: 5%;'>
          <textarea class="form-control input-md" name='ncontent' id='ncontent' rows='10'>${noticeVO.ncontent}</textarea>
        </div>
      </div>
    <DIV style='text-align: center; padding:10%;'>
      <button type="submit" style='margin-top: 5%;'>수정</button>
      <button type="button" onclick="history.back();" style='margin-top: 5%;'>취소</button>
    </DIV>
    </FORM>
  </div>
  

 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>
 
 