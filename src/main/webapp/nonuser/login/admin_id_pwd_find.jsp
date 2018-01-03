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
<link href="./jecss/login.css" rel="Stylesheet" type="text/css">
<link href="./jecss/login.scss" rel="Stylesheet" type="text/css">
<style>

</style>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=root%>/nonuser/login/login.js"></script>
<script type="text/javascript">
var copy_code = ""; // 이메일로 보내진 인증 코드
var msg = "";
var adminno;

$(function(){   
  
  /* -------------------------------- 아이디찾기 코드 전송 클릭 시작-----------------------------------  */ 
  $('#code_btn').click(function(){    
    $.cookie('code_send', 'FALSE'); // Cookie 생성
    
    $('#panel_code').html("<span style='color:green;'>메일을 전송중입니다.</span>"); 
    $('#panel_code').show();
    
    var tomail = $('#admemail').val();
    tomail = tomail.trim();
    // alert("tomail.trim(): " + tomail);
    var title = "Study Desk 이메일 인증 코드입니다";
    var content = "스터디 데스크 이메일 인증 코드입니다.<br>인증 완료 후 아이디를 찾을 수 있습니다. <br>인증확인 눌러주세요.<br>인증코드 : ";
        
      $.ajax({
        type: 'POST',
        url: '/study/nonuser/login/mailSending.do',
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
  
  });  // end code_btn
  /* -------------------------------- 아이디찾기 코드 전송 클릭 종료-----------------------------------  */ 
  
  /* -------------------------------- 아이디찾기 인증 확인 클릭 시작-----------------------------------  */ 
  $('#codecf_btn').click(function(){        
    $.cookie('code_confirm', 'FALSE'); // Cookie 생성
    // alert("copy_code2: " + copy_code); 
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
  /* --------------------------------아이디찾기 인증 확인 클릭 종료-----------------------------------  */ 
  
  
  /* -------------------------------- 비밀번호찾기 코드 전송 클릭 시작-----------------------------------  */ 
  $('#code_btn1').click(function(){    
    $.cookie('code_send1', 'FALSE'); // Cookie 생성
    
    $('#panel_code1').html("<span style='color:green;'>메일을 전송중입니다.</span>"); 
    $('#panel_code1').show();
    
    var tomail = $('#admemail1').val();
    tomail = tomail.trim();
    // alert("tomail.trim(): " + tomail);
    var title = "Study Desk 이메일 인증 코드입니다";
    var content = "스터디 데스크 이메일 인증 코드입니다.<br>인증 완료 후 비밀번호를 바꿀 수 있습니다. <br>인증확인 눌러주세요.<br>인증코드 : ";
        
      $.ajax({
        type: 'POST',
        url: '/study/nonuser/login/mailSending.do',
        data: "tomail=" + tomail + "&title=" + title + "&content=" + content,
        dataType: 'JSON',        
        success: function(data){
           // alert("data.codesend: " + data.codesend);
           // alert("data.code: " + data.code); 
          if(data.codesend == "성공") {                       
            copy_code = data.code;
            // alert("copy_code: " + copy_code); 
            msg = "<span style='color:green;'>이메일에 코드가 전송되었습니다.</span>";
            $('#panel_code1').html(msg); 
            $('#panel_code1').show();
            
            $.cookie('code_send1', 'TRUE'); // Cookie 값 변경
          } else {            
            msg = "<span style='color:red;'>코드 전송을 실패했습니다.</span>";
            $('#panel_code1').html(msg); 
            $('#panel_code1').show();
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
  
  });  // end code_btn
  /* -------------------------------- 비밀번호찾기 코드 전송 클릭 종료-----------------------------------  */ 
  
  /* -------------------------------- 비밀번호찾기 인증 확인 클릭 시작-----------------------------------  */ 
  $('#codecf_btn1').click(function(){        
    $.cookie('code_confirm1', 'FALSE'); // Cookie 생성
    // alert("copy_code2: " + copy_code); 
    if ($.cookie('code_send1') != 'TRUE') { //  코드 전송 실패
      msg = "<span style='color:red;'>코드 전송이 완료되지 않았습니다.</span>";
      $('#panel_confirm1').html(msg); 
      $('#panel_confirm1').show();
      return false;
    } 
    var mailcode = $('#mailcode1').val(); // 이메일에 전송된 코드 복사되는 곳
    // alert("mailcode: " + mailcode); 
    if (copy_code == mailcode) { // 복사한 코드와 이메일로 전송된 코드가 같을 때 
      msg = "<span style='color:green;'>인증 확인 되었습니다.</span>";
      $('#panel_confirm1').html(msg); 
      $('#panel_confirm1').show();

      $.cookie('code_confirm1', 'TRUE'); // Cookie 값 변경
    } else {
      msg = "<span style='color:red;'>인증 코드가 다릅니다.</span>";
      $('#panel_confirm1').html(msg); 
      $('#panel_confirm1').show();
      return false;
    }        
       
  });  // end codecf_btn
  /* --------------------------------비밀번호찾기 인증 확인 클릭 종료-----------------------------------  */ 
  
  
});

function find_admid() {
  if($.cookie('code_send') == 'TRUE' && $.cookie('code_confirm') == 'TRUE') {
    var admname = $('#admname').val();
    var admemail = $('#admemail').val();

    $.ajax({
      type: 'GET',
      url: '/study/nonuser/login/find_admid.do',
      cache: false,
      data: "admname=" + admname + "&admemail=" + admemail,       
      dataType: 'JSON',        
      success: function(data){
      // alert(data.admid);
        if(data.admid == "none"){
          $('#modal-title').html("아이디찾기");
          $('#modal-body').html("입력하신 정보와 <br>일치하는 아이디가 없습니다.");
          $('#myModal').modal();
          
        } else{
          var admid = data.admid;
          
          msg = "회원님의 아이디는 ";
          msg += admid;
          msg += " 입니다.";
          
          $('#modal-title').html("아이디 찾기");
          $('#modal-body').html(msg);
          $('#modal-footer').html("<button class='btn' onclick=\"location.href='<%=root%>/nonuser/login/login.do'\">로그인</button>");
          $('#myModal').modal();
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
}
  
  
function find_admpasswd() {
  if($.cookie('code_send1') == 'TRUE' && $.cookie('code_confirm1') == 'TRUE') {
    var admid = $('#admid1').val();
    var admname = $('#admname1').val();
    var admemail = $('#admemail1').val();

    $.ajax({
      type: 'GET',
      url: '/study/nonuser/login/find_admpasswd.do',
      cache: false,
      data: "admname=" + admname + "&admemail=" + admemail + "&admid=" + admid,       
      dataType: 'JSON',        
      success: function(data){
      // alert(data.cnt_id);
        if(data.adminno == "none"){
          $('#modal-title').html("비밀번호 찾기");
          $('#modal-body').html("입력하신 정보와 <br>일치하는 비밀번호가 없습니다.");
          $('#myModal').modal();
          
        } else{
          adminno = data.adminno;
          // alert("관리자번호: " + adminno);
          
          msg = "<label style='float: left; margin: 5px 0px 5px 5px; font-size: 15px;'><strong>새 비밀번호</strong></label>";
          msg += '<input type="password" id="modal_pwd" name="modal_pwd" value="" style="padding: 6px; margin-left: 37px; width:50%;" >';
          msg += "<label style='float: left; margin: 5px 0px 5px 5px; font-size: 15px;'><strong>새 비밀번호 확인</strong></label>";
          msg += '<input type="password" id="modal_pwd_c" name="modal_pwd_c" value="" style="padding: 6px; margin-left: 5px; width:50%;" >';
          msg += '<div id="modal_msg" style= "text-align: center; font-weight: 900; font-size: 15px;"></div>';
          
          $('#modal-title').html("새 비밀번호 생성");
          $('#modal-body').html(msg);
          $('#modal-footer').html('<button class="btn" onclick="admpasswd_change();">저장</button> <button class="btn" data-dismiss="modal">닫기</button>');
          $('#myModal').modal();
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
  
}


function admpasswd_change() {
  var admpasswd = $('#modal_pwd').val();
  var admpasswd_c = $('#modal_pwd_c').val();
  
  var limitpw = /^[a-z0-9]{6,12}$/;
  
  if(limitpw.test(admpasswd) == false) {
    msg = "<span style='color:red;'>비밀번호는 영어나 숫자 조합 6자리 이상입니다.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.admpasswd;
    return false;
  }     
  
  if(/(\w)\1\1\1/.test(admpasswd)) {
    msg = "<span style='color:red;'>같은문자를 4번 이상 사용하실 수 없습니다.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.admpasswd;
    return false;
  }     
  
  if (admpasswd_c == "" || admpasswd_c == null) {
    msg = "<span style='color:red;'>비밀번호 확인을 입력하세요.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.admpasswd_c;
    return false;
  }
  
  if (admpasswd != admpasswd_c) {
    msg = "<span style='color:red;'>비밀번호와 비밀번호 확인란이 다릅니다.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.admpasswd_c;
    return false;
  }
  
  $.ajax({
    type: 'GET',
    url: '/study/nonuser/login/admpasswd_change.do',
    cache: false,
    data: "adminno=" + adminno + "&admpasswd=" + admpasswd,       
    dataType: 'JSON',         
    success: function(data){
    // alert(data.cnt_id)
      if(data.success == "success"){
        $('#modal-body').html("비밀번호가 변경되었습니다.");
        $('#modal-footer').html("<button class='btn' onclick=\"location.href='<%=root%>/nonuser/login/login.do'\">로그인</button>");
      } else{
        $('#modal-body').html("비밀번호 변경이 실패하였습니다.");
        $('#modal-footer').html('<button class="btn" onclick="location.reload();">다시 시도</button>');
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
  
}


</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content'>

  <div class="modal fade modal-sm" id="myModal" role="dialog" style='position: absolute; top:30%; left:56%; width: 25%; display: none;'>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="modal-title"></h4>
      </div>
      <div class="modal-body" id="modal-body" style='text-align: center; height: 100px;'>
        <span></span>
      </div>
      <div class="modal-footer" id="modal-footer">
        <button class="btn" data-dismiss="modal">닫기</button>    
      </div>
    </div>
  </div>
    
    <div class="logmod__container" style='width: 27%; margin: 0px auto; padding-top: 30px;'>
      <ul class="logmod__tabs">
        <li data-tabtar="lgm-1"><a href="#">관리자ID 찾기</a></li>
        <li data-tabtar="lgm-2"><a href="#">관리자Passwd 찾기</a></li>
      </ul>
      <div class="logmod__tab-wrapper">
      <div class="logmod__tab lgm-1">
        <div class="logmod__form">
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">NAME</label>
                <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="admname" name="admname" value='' placeholder="이름" type="text" size="50" />
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">EMAIL</label>
                <input class="string optional" maxlength="255" id="admemail" name="admemail" value='' placeholder="이메일" type="email" size="50" />
              </div>
              <div class="" style='padding: 0px; text-align: left; margin-bottom: 10px;'>
                <button type="button" class="btn" id="code_btn" style='margin-left: 10px; padding: 0px;'><strong>코드 전송</strong></button>
                <span id="panel_code" style='font-size: 15px;'></span>
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">CODE</label>
                <input class="string optional" maxlength="255" id="mailcode" name="mailcode" value='' placeholder="인증 코드" type="text" size="50" />
              </div>
              <div class="" style='padding: 0px; text-align: left; margin-bottom: 10px;'>
                <button type="button" class="btn" id="codecf_btn" style='margin-left: 10px; padding: 0px;'><strong>인증 확인</strong></button>
                <span id="panel_confirm" style='font-size: 15px;'></span>
              </div>
            </div>
            <div class="simform__actions">
              <input class="sumbit" name="commit" type="button" onclick="find_admid();" value="아이디 찾기" />
            </div> 
        </div> 
        <div class="logmod__alter">
          <div class="logmod__alter-container">
          </div>
        </div>
      </div>
      
      <div class="logmod__tab lgm-2">
        <div class="logmod__form">
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">NAME</label>
                <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="admname1" name="admname1" value='' placeholder="이름" type="text" size="50" />
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">ID</label>
                <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="admid1" name="admid1" value='' placeholder="아이디" type="text" size="50" />
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">EMAIL</label>
                <input class="string optional" maxlength="255" id="admemail1" name="admemail1" value='' placeholder="이메일" type="email" size="50" />
              </div>
              <div class="" style='padding: 0px; text-align: left; margin-bottom: 10px;'>
                <button type="button" class="btn" id="code_btn1" style='margin-left: 10px; padding: 0px;'><strong>코드 전송</strong></button>
                <span id="panel_code1" style='font-size: 15px;'></span>
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">CODE</label>
                <input class="string optional" maxlength="255" id="mailcode1" name="mailcode1" value='' placeholder="인증 코드" type="text" size="50" />
              </div>
              <div class="" style='padding: 0px; text-align: left; margin-bottom: 10px;'>
                <button type="button" class="btn" id="codecf_btn1" style='margin-left: 10px; padding: 0px;'><strong>인증 확인</strong></button>
                <span id="panel_confirm1" style='font-size: 15px;'></span>
              </div>
            </div>
            <div class="simform__actions">
              <input class="sumbit" name="commit" type="button" onclick="find_admpasswd();" value="비밀번호 찾기" />
            </div> 
        </div> 
        <div class="logmod__alter">
          <div class="logmod__alter-container">
          </div>
        </div>
      </div>
    </div>
  </div>

  
</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>