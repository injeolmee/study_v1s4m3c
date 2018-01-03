<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>메일 보내기</title> 
 
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 

<script type="text/javascript">    
  
</script>

</head> 
 
<body>

<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>

<DIV class='content' style='width: 70%; margin: 0px auto; text-align: center;'>
 
<DIV class='title_line' style='width: 60%; margin: 50px 150px 50px; font-size: 20px;'>메일 보내기</DIV>
 
<FORM name='send_email' id = 'send_email' method='POST' action='./mailSending.do' class="form-horizontal">  
  <div style= 'background-color: #f2f2f2; padding: 30px 5px 10px;'>
  <div class="input-prepend input-append">
  <span class="add-on" style="width:110px; margin-left: -160px">* 이메일</span>
     <input type="text" id="tomail" name="tomail" autofocus="autofocus" placeholder="보낼 상대의 이메일" style="width:300px;" value="${tomail }" title="이메일">    
  </div>
  </div>
  
  <div style= 'background-color: #f2f2f2; padding: 30px 5px 10px;'>
  <div class="input-prepend input-append">
  <span class="add-on" style="width:110px; margin-left: -160px">* 제목</span>
     <input type="text" id="title" name="title" autofocus="autofocus" placeholder="제목을 입력해주세요" style="width:300px;" value="" title="Study Desk 이메일 인증 코드입니다">    
  </div>
  </div>   
  
  <div style= 'background-color: #f2f2f2; padding: 30px 5px 10px;'>
  <div class="input-prepend input-append">
  <span class="add-on" style="width:110px; margin-left: -160px">* 내용</span>
     <textarea id="content" name="content" autofocus="autofocus" placeholder="내용" style="width:300px;" value="" title="내용">
       스터디 데스크 이메일 인증 코드입니다<br>
       인증 완료 후 회원가입이 가능합니다<br>
       인증하기를 눌러주세요<br>
       인증코드       
     </textarea>    
  </div>
  </div>    
  
  <div style= 'background-color: #f2f2f2; padding: 5px 5px 30px;'>
    <button type="submit" class="btn btn-success">메일 보내기</button>
  </div>  
</FORM>
 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 