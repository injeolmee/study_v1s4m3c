<%@ page contentType="text/html; charset=UTF-8" %>
 
 <% 
String root = request.getContextPath(); 
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<style>

</style>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>
<script type="text/javascript">
$(function(){

});
</script>
</head>

<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>

  <ASIDE style='float: right;'>
    <A href="<%=root%>/nonuser/login/admin_login.do">관리자 로그인</A>
  </ASIDE>

<DIV class='title_line' style='width: 20%;'>로그인</DIV>
 
<DIV style='margin: 0px auto; padding: 30px;'>
  <FORM name='frm' method='POST' action='./login.do' class="form-horizontal">
  
    <div class="form-group">
      <label for="id" class="col-md-offset-3 col-md-1 control-label">아이디</label>    
      <div class="col-md-7">
        <input type='text' class="form-control input-md" name='memid' id='memid' value='${ck_id }' required="required" style='width: 30%; margin-right: 10px;' placeholder="아이디" autofocus="autofocus">
        <Label>   
          <input type='checkbox' name='id_save' value='Y' 
                    ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 저장
        </Label>
      </div>
    </div>   
 
    <div class="form-group">
      <label for="passwd" class="col-md-offset-3 col-md-1 control-label">패스워드</label>    
      <div class="col-md-7">
        <input type='password' class="form-control input-md" name='mempasswd' id='mempasswd' value='${ck_passwd }' required="required" style='width: 30%; margin-right: 10px;' placeholder="패스워드">
        <Label>
          <input type='checkbox' name='passwd_save' value='Y' 
                    ${ck_passwd_save == 'Y' ? "checked='checked'" : "" }> 저장
        </Label>
      </div>
    </div>   
 
    <div class="form-group" >
      <div class="col-md-offset-4 col-md-7" style='margin-top: 50px;'>
        <button type="submit" class="btn">로그인</button>
        <button type="button" onclick="history.back()" class="btn">취소</button>
        <button type="button" onclick="location.href='<%=root%>/nonuser/login/member_id_pwd_find.do'" class="btn btn-info">아이디/비밀번호 찾기</button>
 
      </div>
    </div>   
  </FORM>
</DIV>

  
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>