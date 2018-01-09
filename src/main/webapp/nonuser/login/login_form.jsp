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
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="<%=root%>/nonuser/login/jecss/login.css" rel="Stylesheet" type="text/css">
<link href="<%=root%>/nonuser/login/jecss/login.scss" rel="Stylesheet" type="text/css">
<style>

</style>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=root%>/nonuser/login/login.js"></script>
<script type="text/javascript">
$(function(){ 
  
  $('#login').click(function(){  
    var frm_login = $("#frm_login").serialize();
    $.ajax({
      url : '/study/nonuser/login/login.do',
      type: "POST",
      data : frm_login, 
      dataType : 'JSON',
      success : function(data){
        // alert(data.login_cnt);
        if(data.login_cnt==1){
          location.href ='<%=root %>/main/index.do';
        } else if(data.login_cnt==2){
          alert("아이디나 패스워드가 다릅니다.\n다시 시도해주세요.");
          location.href ='<%=root %>/nonuser/login/login.do';
        } else {
          alert("유효한 회원이 아닙니다.");
          location.href ='<%=root %>/nonuser/login/login.do';
        }
       },
    // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
       error: function (request, status, error){  
         var msg = "에러가 발생했습니다. <br><br>";
         msg += "다시 시도해주세요.<br><br>";
         msg += "request.status: " + request.status + "<br>";
         msg += "request.responseText: " + request.responseText + "<br>";
         msg += "status: " + status + "<br>";
         msg += "error: " + error;

         $('#modal_title').html("에러");   
         $('#modal_content').html(msg); 
         $('#modal_panel').modal(); 
       }
     });
    
    
  });
  
  
  $('#login_admin').click(function(){  
    var frm_admin_login = $("#frm_admin_login").serialize();
    $.ajax({
      url : '/study/nonuser/login/admin_login.do',
      type: "POST",
      data : frm_admin_login, 
      dataType : 'JSON',
      success : function(data){
        // alert(data.login_cnt);
        if(data.login_cnt==1){
          location.href ='<%=root %>/main/index.do';
        } else if(data.login_cnt==2){
          alert("아이디나 패스워드가 다릅니다.\n다시 시도해주세요.");
          location.href ='<%=root %>/nonuser/login/login.do';
        } else {
          alert("유효한 회원이 아닙니다.");
          location.href ='<%=root %>/nonuser/login/login.do';
        }
       },
    // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
       error: function (request, status, error){  
         var msg = "에러가 발생했습니다. <br><br>";
         msg += "다시 시도해주세요.<br><br>";
         msg += "request.status: " + request.status + "<br>";
         msg += "request.responseText: " + request.responseText + "<br>";
         msg += "status: " + status + "<br>";
         msg += "error: " + error;

         $('#modal_title').html("에러");   
         $('#modal_content').html(msg); 
         $('#modal_panel').modal(); 
       }
     });
    
    
  });

}); // function 끝
</script>
</head>

<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>

    <div class="logmod__container" style='width: 27%; margin: 0px auto; padding-top: 30px;'>
      <ul class="logmod__tabs">
        <li data-tabtar="lgm-1"><a href="#">회원 Login</a></li>
        <li data-tabtar="lgm-2"><a href="#">관리자 Login</a></li>
      </ul>
      <div class="logmod__tab-wrapper">
      <div class="logmod__tab lgm-1">
        <div class="logmod__heading">
          <span class="logmod__heading-subtitle">Enter your ID and Password <strong>to member login in</strong></span>
        </div>
        <div class="logmod__form">
          <form accept-charset="utf-8" name='frm_login' id='frm_login' method='POST' class="simform">
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">ID*</label>
                <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="memid" name="memid" value='${ck_id }' placeholder="ID" type="text" size="50" />
              </div>
              <div style='padding-bottom: 5px; float: right;'>
                <input type='checkbox' name='id_save' value='Y' ${ck_id_save == 'Y' ? "checked='checked'" : "" }>
                <span style='margin-left: 5px; font-size: 13px;'>저장</span>
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">Password *</label>
                <input class="string optional" maxlength="255" id="mempasswd" name="mempasswd" value='${ck_passwd }' placeholder="Password" type="password" size="50" />
                            <span class="hide-password">Show</span>
              </div>
              <div style='padding-bottom: 5px; float: right;'>
                <input type='checkbox' name='passwd_save' value='Y' ${ck_passwd_save == 'Y' ? "checked='checked'" : "" }>
                <span style='margin-left: 5px; font-size: 13px;'>저장</span>
              </div>
            </div>
            <div class="simform__actions">
              <input class="sumbit" id="login" name="login" type="button" value="Log In" />
              <span class="simform__actions-sidetext"><a class="special" role="link" href="<%=root%>/nonuser/login/member_id_pwd_find.do">Forgot your ID/Password?<br>Click here</a></span>
            </div> 
          </form>
        </div> 
        <div class="logmod__alter">
          <div class="logmod__alter-container">
          </div>
        </div>
      </div>
      <div class="logmod__tab lgm-2">
        <div class="logmod__heading">
          <span class="logmod__heading-subtitle">Enter your ID and Password <strong>to admin login in</strong></span>
        </div> 
        <div class="logmod__form">
          <form accept-charset="utf-8" name='frm_admin_login' id='frm_admin_login' method='POST' action='./admin_login.do' class="simform">
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">ID*</label>
                <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="admid1" name="admid" value='${ck_admid }' placeholder="ID" type="text" size="50" />
              </div>
              <div style='padding-bottom: 5px; float: right;'>
                <input type='checkbox' name='admid_save' value='Y' ${ck_admid_save == 'Y' ? "checked='checked'" : "" }>
                <span style='margin-left: 5px; font-size: 13px;'>저장</span>
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">Password *</label>
                <input class="string optional" maxlength="255" id="admpasswd" name="admpasswd" value='${ck_admpasswd }' placeholder="Password" type="password" size="50" />
                            <span class="hide-password">Show</span>
              </div>
              <div style='padding-bottom: 5px; float: right;'>
                <input type='checkbox' name='admpasswd_save' value='Y' ${ck_admpasswd_save == 'Y' ? "checked='checked'" : "" }>
                <span style='margin-left: 5px; font-size: 13px;'>저장</span>
              </div>
            </div>
            <div class="simform__actions">
              <input class="sumbit" id="login_admin" name="login_admin" type="button" value="Log In" />
              <span class="simform__actions-sidetext"><a class="special" role="link" href="<%=root%>/nonuser/login/admin_id_pwd_find.do">Forgot your ID/Password?<br>Click here</a></span>
            </div> 
          </form>
        </div>  
        <div class="logmod__alter">
          <div class="logmod__alter-container">
          </div>
        </div>
          </div>
      </div>
    </div>


  
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>