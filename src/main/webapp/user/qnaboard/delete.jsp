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
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
 
</head> 
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'>   
  
  <DIV style='width: 100%;'>
    <FORM name='frm' method='POST' action='/study/user/qnaboard/delete.do'
               onsubmit = 'return send();' class="form-horizontal" style='text-align: center; margin: 0px auto; width: 80%;'>
      <input type='hidden' name='qnano' id='qnano' value='${param.qnano}'>
    
      <div class="form-group">   
        <div class="col-md-12" style='text-align: center; margin: 30px;'>
              삭제되는 글: ${qnaVO.qnatitle} <br><br>
              삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br><br>
          <button type = "submit">삭제 진행</button>
          <button type = "button" onclick = "history.back()">취소</button>
        </div>
      </div>   
    </FORM>
  </DIV>
 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 