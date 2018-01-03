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
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>

<script type="text/javascript">
var copy_code = ""; // 이메일로 보내진 인증 코드
var msg = "";

$(function(){   
  
/*   $("#id_btn").click(function(){
    
    $("#modal_panel").modal();
  }); */
  
  
  /* -------------------------------- 아이디 중복 검사 클릭 시작-----------------------------------  */ 
  $('#id_btn').click(function(){    
    $.cookie('check_id', 'FALSE');// Cookie 생성
    var admid = $("input[name='admid']").val();
    var limitid =  /^[a-z0-9_-]{6,15}$/;
    if(admid== '' || admid==null) {
      msg = "<span style='color: red;'>아이디를 등록해주세요.</span>";
      $('#panel_id').html(msg); 
      $('#panel_id').show();
    } else {      
      if(limitid.test(admid) == false) {
        msg = "<span style='color: red;'>영어소문자 숫자 _ - (6~15자)</span>";
        $('#panel_id').html(msg); 
        $('#panel_id').show();
      } else { 
        admid = "admid="+ $("input[name='admid']").val();      
        // alert(admid);  
      
        $.ajax({
          type: 'GET',
          url: '/study/nonuser/login/check_admid.do',
          cache: false,
          data: admid,       
          dataType: 'JSON',        
          success: function(data){
          // alert(data.cnt_admid);
            if(data.cnt_admid==0){
              msg = "<span style='color:green;'>사용 가능한 아이디입니다.</span>";
              $('#panel_id').html(msg); 
              $('#panel_id').show();
             
              $.cookie('check_id', 'TRUE'); // Cookie 값 변경
            } else{
              msg = "<span style='color:red;'>중복된 아이디입니다.</span>";
              $('#panel_id').html(msg); 
              $('#panel_id').show();
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

  });    // end id_btn
  /* -------------------------------- 아이디 중복 검사 클릭 종료----------------------------------  */  
  
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
          url: '/study/nonuser/login/check_admemail.do',
          cache: false,
          data: admemail,       
          dataType: 'JSON',        
          success: function(data){
          // alert(data.cnt_admemail);
            if(data.cnt_admemail==0){
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
  
  
  /* -------------------------------- 코드 전송 클릭 시작-----------------------------------  */ 
  $('#code_btn').click(function(){    
    $.cookie('code_send', 'FALSE'); // Cookie 생성
    
    $('#panel_code').html("<span style='color:green;'>메일을 전송중입니다.</span>"); 
    $('#panel_code').show();
    
    var tomail = $('#admemail').val();
    tomail = tomail.trim();
    // alert("tomail.trim(): " + tomail);
    var title = "Study Desk 이메일 인증 코드입니다";
    var content = "스터디 데스크 이메일 인증 코드입니다.<br>인증 완료 후 회원가입이 가능합니다.<br>인증하기를 눌러주세요.<br>인증코드 : ";
    
    if ($.cookie('check_email') != 'TRUE') { // 중복 검사 실패
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
            msg = "<span style='color:green;'>이메일에 코드가 전송되었습니다.</span>";
            $('#panel_code').html(msg); 
            $('#panel_code').show();
            
            $.cookie('code_send', 'TRUE'); // Cookie 값 변경
          } else {            
            msg = "<span style='color:red;'>코드 전송을 실패했습니다.</span>";
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
  /* -------------------------------- 코드 전송 클릭 종료-----------------------------------  */ 
  
  
  /* -------------------------------- 인증 확인 클릭 시작-----------------------------------  */ 
  $('#codecf_btn').click(function(){        
    $.cookie('code_confirm', 'FALSE'); // Cookie 생성
    // alert("copy_code2: " + copy_code); 
    if ($.cookie('check_email') != 'TRUE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    }
    if ($.cookie('code_send') != 'TRUE') { //  코드 전송 실패
      msg = "<span style='color:red;'>코드 전송이 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    } 
    var mailcode = $('#mailcode').val(); // 이메일에 전송된 코드 복사되는 곳
    // alert("mailcode: " + mailcode); 
    if (copy_code == mailcode) { // 복사한 코드와 이메일로 전송된 코드가 같을 때 
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


  $('#join').click(function(){  
    var admid = $("input[name='admid']").val();
    var admpasswd = $('#admpasswd').val();
    var admpasswd_c = $('#admpasswd_c').val();
    var admemail = $('#admemail').val();
    var admname = $('#admname').val();

    var limitpw = /^[a-z0-9]{6,12}$/;
    
    if (admid == "" || admid == null) {
      alert('아이디를 입력하세요');
      focus.admid;
      return false;
    }    

    if ($.cookie('check_id') != 'TRUE') {
      alert('중복검사가 완료되지 않았습니다');
      focus.admid;
      return false;    
    }
    if (admemail == "" || admemail == null) {
      alert('이메일을 입력하세요');
      focus.admemail;
      return false;
    }    

    if ($.cookie('check_email') != 'TRUE') {
      alert('중복검사가 완료되지 않았습니다');
      focus.admemail;
      return false;    
    }
    
    if ($.cookie('code_send') != 'TRUE') {
      alert('코드전송이 완료되지 않았습니다.');
      focus.admemail;
      return false;    
    }
    
    if ($.cookie('code_confirm') != 'TRUE') {
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
      alert("비밀번호는 영어나 숫자 조합 6자리 이상입니다.");   
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
    
    if ($.cookie('check_id') != 'TRUE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_id').html(msg); 
      $('#panel_id').show();
      return false;
    }
    
    if ($.cookie('check_email') != 'TRUE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_email').html(msg); 
      $('#panel_email').show();
      return false;
    }
    
    if ($.cookie('code_send') != 'TRUE') { //  코드 전송 실패
      msg = "<span style='color:red;'>코드 전송이 완료되지 않았습니다.</span>";
      $('#panel_code').html(msg); 
      $('#panel_code').show();
      return false;
    } 
    
    if ($.cookie('code_confirm') != 'TRUE') { //  코드 확인 실패
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

<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>

<DIV class='content' style='width: 90%; margin: 0px auto; text-align: center;'>

  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="<%=root%>/nonuser/login/memberjoin.do">회원가입</A>
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
</div>   -->
 
<DIV class='title_line' style='margin: 50px auto; font-size: 20px;'>관리자 가입</DIV>
 
<FORM name='frm_join' id='frm_join' method='POST' action='./admin_create.do' enctype="multipart/form-data" class="form-horizontal">  
<input type="hidden" id="admconfirm" name="admconfirm" value="Y">
<input type="hidden" id="admauth" name="admauth" value="N">

  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 아이디</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="adminid" name="admid" placeholder="영어 대소문자, 숫자, _ , - 가능합니다">
    </div>
    <div class="col-md-1" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="id_btn"><strong>중복 검사</strong></button>
    </div>
    <div class="col-md-3" id="panel_id" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div> 

  
    <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 비밀번호</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="password" class="form-control" id="admpasswd" name="admpasswd" placeholder="Password 영어나 숫자(6 ~ 12자)">
    </div>
    <div class="col-md-4" id="panel_pwd" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 비밀번호 확인</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="password" class="form-control" id="admpasswd_c" name="admpasswd_c" placeholder="Password 확인">
    </div>
    <div class="col-md-4" id="panel_pwd_c" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div> 
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 이메일</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admemail" name="admemail" placeholder="이메일  @ 이메일.com">
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
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 이름</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admname" name="admname" placeholder="홍길동">
    </div>
    <div class="col-md-offset-1 col-md-3" id="panel_pwd_c" style= "text-align: left; padding: 20px; font-weight: 900;"></div>     
  </div>    
 
  <div class="form-group" style='padding: 20px;'>        
    <div class="col-md-offset-5 col-md-2" style= 'padding: 50px;'>
      <button type="submit" id="join" name="join" class="btn btn-success">가입하기</button>
    </div>    
  </div>  
 

</FORM>
 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 