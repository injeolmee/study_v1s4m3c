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
<title>회원 수정</title> 
 
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>

<script type="text/javascript">
var msg = "";

$(function(){ 
  $.cookie('check_email', 'KEEP');
  $.cookie('code_send', 'KEEP');
  $.cookie('code_confirm', 'KEEP');
  
  var admauth = "${adminVO.admauth}";
  $("input[name='admauth']").each(function(){
    if(admauth == "N"){
      $('#N').prop("checked", true);
    } else if (admauth == "A") {
      $('#A').prop("checked", true);
      } else {
        $('#M').prop("checked", true);
      }
  });  
 

  /* -------------------------------- 이메일 중복 검사 클릭 시작-----------------------------------  */ 
  $('#email_btn').click(function(){    
    $.cookie('check_email', 'FALSE'); // Cookie 생성
    var admemail = $('#admemail').val();
    var limitmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    if(admemail== '' || admemail==null) {
      msg = "<span style='color: red;'>이메일을 등록해주세요.</span>";
      $('#panel_email').html(msg); 
      $('#panel_email').show();
    } else {      
      if(limitmail.test(admemail) == false) {
        msg = "<span style='color: red;'>이메일 형식에 맞지 않습니다.</span>";
        $('#panel_email').html(msg); 
        $('#panel_email').show();
      } else { 
        admemail = "admemail="+ $('#admemail').val();      
        // alert(admemail);  
      
        $.ajax({
          type: 'GET',
          url: '/study/admin/admin/check_admemail.do',
          cache: false,
          data: admemail,       
          dataType: 'JSON',        
          success: function(data){
          // alert(data.cnt_admemail);
            if(data.cnt_email==0){
              msg = "<span style='color:green;'>사용 가능한 이메일입니다.</span>";
              $('#panel_email').html(msg); 
              $('#panel_email').show();
             
              $.cookie('check_email', 'TRUE');  // Cookie 값 변경
            } else{
              msg = "<span style='color:red;'>중복된 이메일입니다.</span>";
              $('#panel_email').html(msg); 
              $('#panel_email').show();
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
        
        }           
      } // end else 

  });    // end email_btn
  /* -------------------------------- 이메일 중복 검사 클릭 종료----------------------------------  */  
  
  
  /* -------------------------------- 코드 발송 클릭 시작-----------------------------------  */ 
  $('#code_btn').click(function(){    
    $.cookie('code_send', 'FALSE'); // Cookie 생성
    var tomail = $('#admemail').val();
    var title = "Study Desk 이메일 인증 코드입니다";
    var content = "스터디 데스크 이메일 인증 코드입니다.<br>인증 완료 후 회원가입이 가능합니다.<br>인증하기를 눌러주세요.<br>인증코드 : ";
    
    if ($.cookie('check_email') == 'FALSE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_code').html(msg); 
      $('#panel_code').show();
      return false;
    } else {  // 중복 검사 확인 완료
        
      $.ajax({
        type: 'POST',
        url: '/study/nonuser/login/admin_mailSending.do',
        data: "tomail=" + tomail + "&title=" + title + "&content=" + content,
        dataType: 'JSON',        
        success: function(data){
          // alert("data.codesend: " + data.codesend);
          // alert("data.code: " + data.code); 
          if(data.codesend == "성공") {                       
            copy_code = data.code;
            // alert("copy_code: " + copy_code); 
            msg = "<span style='color:green;'>이메일에 코드가 발송되었습니다.</span>";
            $('#panel_code').html(msg); 
            $('#panel_code').show();
            
            $.cookie('code_send', 'TRUE'); // Cookie 값 변경
          } else {            
            msg = "<span style='color:green;'>코드 발송이 실패했습니다.</span>";
            $('#panel_code').html(msg); 
            $('#panel_code').show();
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
      });    // end ajax   
    }  // end else        
  });  // end code_btn
  /* -------------------------------- 코드 발송 클릭 종료-----------------------------------  */ 
  
  
  /* -------------------------------- 인증 확인 클릭 시작-----------------------------------  */ 
  $('#codecf_btn').click(function(){        
    $.cookie('code_confirm', 'FALSE'); // Cookie 생성
    // alert("copy_code2: " + copy_code); 
    if ($.cookie('check_email') == 'FALSE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    }
    if ($.cookie('code_send') == 'FALSE') { //  코드 발송 실패
      msg = "<span style='color:red;'>코드 발송이 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    } 
    var mailcode = $('#mailcode').val(); // 이메일에 발송된 코드 복사되는 곳
    // alert("mailcode: " + mailcode); 
    if (copy_code == mailcode) { // 복사한 코드와 이메일로 발송된 코드가 같을 때 
      msg = "<span style='color:green;'>인증 확인 되었습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();

      $.cookie('code_confirm', 'TRUE'); // Cookie 값 변경
    } else {
      msg = "<span style='color:red;'>인증 코드가 다릅니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    }        
    
       
  });  // end codecf_btn
  /* -------------------------------- 인증 확인 클릭 종료-----------------------------------  */   
  

    
  $('#update').click(function(){  
    var admpasswd = $('#admpasswd').val();
    var admpasswd_c = $('#admpasswd_c').val();
    var admemail = $('#admemail').val();
    var admname = $('#admname').val();    

    var limitpw = /^[a-z0-9]{6,12}$/;
    
    if (admemail == "" || admemail == null) {
      alert('이메일을 입력하세요');
      focus.admemail;
      return false;
    }    

    if ($.cookie('check_email') == 'FALSE') {
      alert('중복검사가 완료되지 않았습니다');
      focus.admemail;
      return false;    
    }
    
    if ($.cookie('code_send') == 'FALSE') {
      alert('코드발송이 완료되지 않았습니다.');
      focus.admemail;
      return false;    
    }
    
    if ($.cookie('code_confirm') == 'FALSE') {
      alert('인증코드가 확인되지 않았습니다.');
      focus.mailcode;
      return false;    
    }
    
    if (admpasswd == "" || admpasswd == null) {
      alert('패스워드를 입력하세요');
      focus.admpasswd;
      return false;
    }    
    
    if(limitpw.test(admpasswd) == false) {
      alert("비밀번호는 영어나 숫자 조합 6-12자리 입니다.");   
      focus.admpasswd;
      return false;
    }     
    
    if(/(\w)\1\1\1/.test(admpasswd)) {
      alert("같은문자를 4번 이상 사용하실 수 없습니다.");  
      focus.admpasswd;
      return false;
    }     
    
    if (admpasswd_c == "" || admpasswd_c == null) {
      alert('패스워드 확인을 입력하세요');
      focus.admpasswd_c;
      return false;
    }
    
    if (admpasswd != admpasswd_c) {
      alert('패스워드와 패스워드 확인란이 다릅니다');
      focus.admpasswd_c;
      return false;
    }
    
    if (admname == '' || admname == null) {
      alert('이름을 입력하세요');
      focus.admname;
      return false;
    }
    
    if (admname.length < 2) {
      alert('이름을 2자 이상 입력해주세요.');
      focus.admname;
      return false;
    }
    
    if ($.cookie('check_id') == 'FALSE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_id').html(msg); 
      $('#panel_id').show();
      return false;
    }
    
    if ($.cookie('check_email') == 'FALSE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_email').html(msg); 
      $('#panel_email').show();
      return false;
    }
    
    if ($.cookie('code_send') == 'FALSE') { //  코드 발송 실패
      msg = "<span style='color:red;'>코드 발송이 완료되지 않았습니다.</span>";
      $('#panel_code').html(msg); 
      $('#panel_code').show();
      return false;
    } 
    
    if ($.cookie('code_confirm') == 'FALSE') { //  코드 확인 실패
      msg = "<span style='color:red;'>코드 인증이 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    } 
    
  return true;  
  });
  
}); // function 끝
  
</script>

</head> 
 
<body>

<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'>
      
  <ASIDE style='float: left;'>
      
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
  </ASIDE>

<!-- 
<div id="modal_panel" class="modal fade" role="dialog">
  <div class="modal-dialog">

    Modal content
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><span id="modal_title"></span></h4>
      </div>
      <div class="modal-body">
        <p id="modal_content"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>  -->
 
<DIV class='title_line' style='margin: 50px auto; font-size: 20px;'>회원 수정</DIV>
 
<FORM name='frm_join' id='frm_join' method='POST' action='./admin_update.do' enctype="multipart/form-data" class="form-horizontal">  
<input type="hidden" id="adminno" name="adminno" value="${adminVO.adminno }">
<input type="hidden" id="admid" name="admid" value="${adminVO.admid }">
<input type="hidden" id="admconfirm" name="admconfirm" value="Y">

  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 아이디</strong></div>
    <div class="col-sm-7" style='padding-left: 10px;'>
      <input type="text" class="form-control" value="${adminVO.admid }" style="padding:6px; margin:0px; width: 43%;" disabled="disabled">
    </div>  
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 비밀번호</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="password" class="form-control" id="admpasswd" name="admpasswd"  placeholder="Password 영어나 숫자(6 ~ 12자)">
    </div>
    <div class="col-md-4" id="panel_pwd" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 비밀번호 확인</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="password" class="form-control" id="admpasswd_c" name="admpasswd_c">
    </div>
    <div class="col-md-4" id="panel_pwd_c" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 이메일</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admemail" name="admemail" value="${adminVO.admemail }" placeholder="이메일  @ 이메일.com">
    </div>
    <div class="col-md-1" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="email_btn"><strong>중복 검사</strong></button>
    </div>
    <div class="col-md-3" id="panel_email" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 이메일 인증</strong></div>
    <div class="col-md-4" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="code_btn" style='margin-left: 10px;'><strong>코드 전송</strong></button>
    </div>
    <div class="col-md-3" id="panel_code" style= "text-align: left; margin-left:35px; padding: 6px 0px 15px 0px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 인증 코드</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="mailcode" name="mailcode" placeholder="이메일에서 인증 코드를 복사하여 붙여주세요.">
    </div>
    <div class="col-md-1" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="codecf_btn"><strong>인증 확인</strong></button>
    </div>
    <div class="col-md-3" id="panel_confirm" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div> 
  
  <div class="form-group" style='padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 이름</strong></div>
    <div class="col-sm-7" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admname" name="admname" value="${adminVO.admname }" placeholder="홍길동" style="width: 43%; padding:6px; margin:0px;">
    </div>    
  </div>    
  
<c:choose>
  <c:when test="${sessionScope.admauth == 'M'}">
  <div class="form-group" style='padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 권한</strong></div>
    <div class="col-sm-7" style='padding: 10px 0px 5px 10px; text-align: left;'>
      <label class="radio-inline" style= 'padding:0px 0px 5px; margin:0px 30px;'>
        <input type="radio" name="admauth" id="N" value="N" /> 대기</label>        
      <label class="radio-inline" style= 'padding:0px 0px 5px; margin:0px 30px;'>
        <input type="radio" name="admauth" id="A" value="A" /> 관리자</label>    
      <label class="radio-inline" style= 'padding:0px 0px 5px;'>
        <input type="radio" name="admauth" id="M" value="M" /> 마스터</label>    
    </div>
  </div>
  </c:when>
  <c:otherwise>
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 권한</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admauth" name="admauth" value="${adminVO.admauth }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>  
  </div>    
  </c:otherwise>
</c:choose>   
  

  <div class="form-group" style='padding: 30px;'>        
    <div class="col-sm-offset-5 col-sm-2" style='padding: 30px; margin-bottom: 150px;'>
      <button type="submit" id="update" name="update" class="btn btn-success">수정하기</button>
      <button type="button" onclick='history.back()' class="btn btn-success">취소</button>      
    </div>
  </div>
  

</FORM>
 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 