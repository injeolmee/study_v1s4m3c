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
var msg = "";

$(function(){   
  var auth = '${sessionScope.admauth}';
  var adminno_session = '${sessionScope.adminno}';
  var adminno_VO = '${adminVO.adminno}';
  if(adminno_session != adminno_VO) {
    if(auth == 'M') {
    } else {
      alert("유효한 접근이 아닙니다.");
      location.href ='<%=root %>/main/index.do';
    }
  } 
    
$('#update_btn').click(function(){  
  var  modal_pwd = "modal_pwd=" + $('#modal_pwd').val();  
  alert(modal_pwd);  
  
  $.ajax({
    type: 'GET',
    url: '/study/admin/admin/admin_read_pwdcheck.do',
    cache: false,
    data: modal_pwd,       
    dataType: 'JSON',   
    async: false,
    success: function(data){
     alert(data.cnt_admpasswd);
      if(data.cnt_admpasswd==1){
        var url ='<%=root %>/admin/admin/admin_update.do?adminno=${adminVO.adminno}';
        $(location).attr('href', url);
      } else{
        msg = "<span style='color:red;'>패스워드가 다릅니다.</span>";
        $('#panel_pwd').html(msg); 
        $('#panel_pwd').show();
        return false;
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
  });  //end ajax  
});
  
}); // function 끝

  
</script>

</head> 
 
<body>

<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content' style='width: 90%; margin: 0px auto;'>

  <div class="modal fade modal-sm" id="myModal" role="dialog" style='top:30%; left:58%;'>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">패스워드 검사</h4>
      </div>
      <div class="modal-body" style='text-align: center; height: 80px;'>
        <label style='float: left; margin: 5px 0px 5px 5px;'><strong>패스워드</strong></label>
        <input type="password" id="modal_pwd" name="modal_pwd" value="" style='padding: 6px; margin: 0px; width:50%;' >
        <input type="text" style="display: none;">
        <div id="panel_pwd" style= "text-align: center; padding: 10px; font-weight: 900; font-size: 15px;"></div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" id="update_btn">확인</button> 
        <button class="btn" data-dismiss="modal">닫기</button>    
      </div>
    </div>
  </div>
  
  <div class="logmod__container" style='width: 30%; margin: 0px auto; padding-top: 30px;'>
    <ul class="logmod__tabs">
      <li data-tabtar="lgm-1"><a href="#">관리자 정보</a></li>
    </ul>
    <div class="logmod__tab-wrapper">
    <div class="logmod__tab lgm-1">
    
    <div class="sminputs">        
      <div class="input full">
        <label class="string optional" for="user-name">* ID</label>
        <input class="string optional" style='margin-bottom: 0px; ' maxlength="255" id="admid1" name="admid" value='${adminVO.admid }' type="text" size="50" disabled="disabled"/>
      </div> 
    </div>
    
    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">* EMAIL</label>
        <input class="string optional" style='margin-bottom: 0px; ' maxlength="255" id="admemail" name="admemail" value="${adminVO.admemail }" type="email" size="50" disabled="disabled"/>
        <span id="panel_email" style='font-size: 15px;'></span>
      </div>
    </div>

    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">* NAME</label>
        <input class="string optional" maxlength="255" id="admname" name="admname" value='${adminVO.admname }' type="text" size="50" disabled="disabled"/>
      </div>
    </div>
 
   <c:choose>
    <c:when test="${sessionScope.admauth == 'M'}">   
      <div class="sminputs">
        <div class="input full">
          <label class="string optional" for="user-pw">* AUTHORITY</label>
          <input class="string optional" maxlength="255" id="admauth" name="admauth" value='${adminVO.admauth }' type="text" size="50" disabled="disabled"/>
        </div>
      </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
  </c:choose>
    
  <c:choose>
    <c:when test="${sessionScope.adminno != adminVO.adminno && sessionScope.admauth == 'M'}">
      <div class="simform__actions">
        <input class="sumbit" onclick="location.href='<%=root%>/admin/admin/admin_update.do?adminno=${adminVO.adminno}' " type="button" value="정보 수정하기" />
        <input class="sumbit" onclick="location.href='<%=root%>/master/admin_list.do' " type="button" value="관리자 목록" style='border-right: solid' />
      </div> 
    </c:when>
    <c:otherwise>
      <div class="simform__actions">
        <input class="sumbit" data-toggle="modal" data-target="#myModal" type="button" value="정보 수정하기" />
      </div>
    </c:otherwise>
  </c:choose>
    
  </div>
</div>
</div>
  
 
 
</DIV> <!-- content END -->
</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 