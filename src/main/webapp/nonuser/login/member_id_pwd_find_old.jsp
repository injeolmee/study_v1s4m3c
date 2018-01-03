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
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!--------------------------J Query Part ------------------------------>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!---------------------------------------------------------------------->
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>
<script type="text/javascript">
var copy_code = ""; // 이메일로 보내진 인증 코드
var msg = "";
var memberno;

$(function(){   
  
  /* -------------------------------- 아이디찾기 코드 전송 클릭 시작-----------------------------------  */ 
  $('#code_btn').click(function(){    
    $.cookie('code_send', 'FALSE'); // Cookie 생성
    
    $('#panel_code').html("<span style='color:green;'>메일을 전송중입니다.</span>"); 
    $('#panel_code').show();
    
    var tomail = $('#mememail').val();
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
    
    var tomail = $('#mememail1').val();
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
  
  
}); // $(function(){  끝

function find_memid() {
  if($.cookie('code_send') == 'TRUE' && $.cookie('code_confirm') == 'TRUE') {
    var memname = $('#memname').val();
    var mememail = $('#mememail').val();

    $.ajax({
      type: 'GET',
      url: '/study/nonuser/login/find_memid.do',
      cache: false,
      data: "memname=" + memname + "&mememail=" + mememail,       
      dataType: 'JSON',        
      success: function(data){
      // alert(data.memid);
        if(data.memid == "none"){
          $('#modal-title').html("아이디찾기");
          $('#modal-body').html("입력하신 정보와 일치하는 아이디가 없습니다.");
          $('#myModal').modal();
          
        } else{
          var memid = data.memid;
          
          msg = "회원님의 아이디는 ";
          msg += memid;
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
  
  
function find_mempasswd() {
  if($.cookie('code_send1') == 'TRUE' && $.cookie('code_confirm1') == 'TRUE') {
    var memid = $('#memid1').val();
    var memname = $('#memname1').val();
    var mememail = $('#mememail1').val();

    $.ajax({
      type: 'GET',
      url: '/study/nonuser/login/find_mempasswd.do',
      cache: false,
      data: "memname=" + memname + "&mememail=" + mememail + "&memid=" + memid,       
      dataType: 'JSON',        
      success: function(data){
      // alert(data.cnt_id);
        if(data.memberno == "none"){
          $('#modal-title').html("비밀번호 찾기");
          $('#modal-body').html("입력하신 정보와 일치하는 비밀번호가 없습니다.");
          $('#myModal').modal();
          
        } else{
          memberno = data.memberno;
          // alert("회원번호: " + memberno);
          
          msg = "<label style='float: left; margin: 5px 0px 5px 5px;'><strong>새 비밀번호</strong></label>";
          msg += '<input type="password" id="modal_pwd" name="modal_pwd" value="" style="padding: 6px; margin-left: 37px; width:50%;" >';
          msg += "<label style='float: left; margin: 5px 0px 5px 5px;'><strong>새 비밀번호 확인</strong></label>";
          msg += '<input type="password" id="modal_pwd_c" name="modal_pwd_c" value="" style="padding: 6px; margin-left: 5px; width:50%;" >';
          msg += '<div id="modal_msg" style= "text-align: center; font-weight: 900;"></div>';
          
          $('#modal-title').html("새 비밀번호 생성");
          $('#modal-body').html(msg);
          $('#modal-footer').html('<button class="btn" onclick="mempasswd_change();">저장</button> <button class="btn" data-dismiss="modal">닫기</button>');
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


function mempasswd_change() {
  var mempasswd = $('#modal_pwd').val();
  var mempasswd_c = $('#modal_pwd_c').val();
  
  var limitpw = /^[a-z0-9]{6,12}$/;
  
  if(limitpw.test(mempasswd) == false) {
    msg = "<span style='color:red;'>비밀번호는 영어나 숫자 조합 6자리 이상입니다.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.mempasswd;
    return false;
  }     
  
  if(/(\w)\1\1\1/.test(mempasswd)) {
    msg = "<span style='color:red;'>같은문자를 4번 이상 사용하실 수 없습니다.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.mempasswd;
    return false;
  }     
  
  if (mempasswd_c == "" || mempasswd_c == null) {
    msg = "<span style='color:red;'>비밀번호 확인을 입력하세요.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.mempasswd_c;
    return false;
  }
  
  if (mempasswd != mempasswd_c) {
    msg = "<span style='color:red;'>비밀번호와 비밀번호 확인란이 다릅니다.</span>";
    $('#modal_msg').html(msg); 
    $('#modal_msg').show();
    focus.mempasswd_c;
    return false;
  }
  
  $.ajax({
    type: 'GET',
    url: '/study/nonuser/login/mempasswd_change.do',
    cache: false,
    data: "memberno=" + memberno + "&mempasswd=" + mempasswd,       
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
<DIV class='content' style='width: 50%; margin: 0px auto;'>  

  <div class="modal fade modal-sm" id="myModal" role="dialog" style='position: absolute; top:50%; left:60%; width: 25%; display: none;'>
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
    
  <fieldset class="fieldset_basic" style="border: solid 2px #dad8d7; font-size: 15px; width: 100%; padding: 20px; margin-bottom: 10%;">
    <ul style="margin: 0px auto;">
    
      <li class="li_none">
        <div style="line-height: 0px">
          <span style="font-weight: bold; margin-right: 20px;">아이디 찾기</span>
        </div>
        <HR style="color: #EEEEEE;">
      </li>
    
      <li class="li_none">
        <div style="width: 100%; margin-top: 30px; margin-bottom: 30px;">
        
          <div class="form-group" style= 'padding-bottom:20px;'>
            <label for="memname" class="col-md-1 control-label" style='padding: 5px;'>이름</label>    
            <div class="col-md-10" style='padding: 0px; padding-bottom: 10px;'>
              <input type='text' class="form-control input-md" name='memname' id='memname' value='' required="required" style='width: 39%;' placeholder="이름" autofocus="autofocus">
            </div>
          </div>
          <div class="form-group" style= 'padding-bottom:20px;'>
            <label for="mememail" class="col-md-1 control-label" style='padding: 5px;'>이메일</label>    
            <div class="col-md-4" style='padding: 0px;'>
              <input type='text' class="form-control input-md" name='mememail' id='mememail' value='' required="required" style='padding-right: 0px;' placeholder="이메일" autofocus="autofocus">
            </div>
            <div class="col-md-2" style='padding: 0px; text-align: left; margin-bottom: 10px;'>
              <button type="button" class="btn" id="code_btn" style='margin-left: 10px;'><strong>코드 전송</strong></button>
            </div>
            <div class="col-md-4"  id="panel_code" style='padding: 5px;'>
            </div>
          </div>
          <div class="form-group" style='padding-top: 30px;'>
            <label for="mailcode" class="col-md-1 control-label" style='padding: 5px;'>인증 코드</label> 
            <div class="col-md-4" style='padding: 0px;'>
              <input type='text' class="form-control input-md" name='mailcode' id='mailcode' value='' required="required" style='padding-right: 0px;' placeholder="인증 코드" autofocus="autofocus">
            </div>
            <div class="col-md-2" style='padding: 0px; text-align: left;'>
              <button type="button" class="btn" id="codecf_btn" style='margin-left: 10px;'><strong>인증 확인</strong></button>
            </div>
            <div class="col-md-4"  id="panel_confirm" style='padding: 5px;'>
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-offset-5 col-md-6" style='padding: 5px; margin-top: 20px;'>
              <button onclick="find_memid();">아이디 찾기</button>                      
            </div>
          </div>
          
        </div>
      </li>
    </ul>
  </fieldset> 
  
  <fieldset class="fieldset_basic" style="border: solid 2px #dad8d7; font-size: 15px; width: 100%; padding: 20px; margin-bottom: 10%;">
    <ul style="margin: 0px auto;">
    
      <li class="li_none">
        <div style="line-height: 0px">
          <span style="font-weight: bold; margin-right: 20px;">비밀번호 찾기</span>
        </div>
        <HR style="color: #EEEEEE;">
      </li>
    
      <li class="li_none">
        <div style="width: 100%; margin-top: 30px; margin-bottom: 30px;">
        
          <div class="form-group" style= 'padding-bottom:20px;'>
            <label for="memname" class="col-md-1 control-label" style='padding: 5px;'>이름</label>    
            <div class="col-md-10" style='padding: 0px; padding-bottom: 10px;'>
              <input type='text' class="form-control input-md" name='memname1' id='memname1' value='' required="required" style='width: 39%;' placeholder="이름" autofocus="autofocus">
            </div>
          </div>
          <div class="form-group" style= 'padding-bottom:20px;'>
            <label for="memid" class="col-md-1 control-label" style='padding: 5px;'>아이디</label>    
            <div class="col-md-10" style='padding: 0px; padding-bottom: 10px;'>
              <input type='text' class="form-control input-md" name='memid1' id='memid1' value='' required="required" style='width: 39%;' placeholder="아이디" autofocus="autofocus">
            </div>
          </div>
          <div class="form-group" style= 'padding-bottom:30px;'>
            <label for="mememail" class="col-md-1 control-label" style='padding: 5px;'>이메일</label>    
            <div class="col-md-4" style='padding: 0px;'>
              <input type='text' class="form-control input-md" name='mememail1' id='mememail1' value='' required="required" style='padding-right: 0px;' placeholder="이메일" autofocus="autofocus">
            </div>
            <div class="col-md-2" style='padding: 0px; text-align: left; margin-bottom: 10px;'>
              <button type="button" class="btn" id="code_btn1" style='margin-left: 10px;'><strong>코드 전송</strong></button>
            </div>
            <div class="col-md-4"  id="panel_code1" style='padding: 5px;'>
            </div>
          </div>
          <div class="form-group" style= 'padding-top:35px;'>
            <label for="mailcode" class="col-md-1 control-label" style='padding: 5px;'>인증 코드</label> 
            <div class="col-md-4" style='padding: 0px;'>
              <input type='text' class="form-control input-md" name='mailcode1' id='mailcode1' value='' required="required" style='padding-right: 0px;' placeholder="인증 코드" autofocus="autofocus">
            </div>
            <div class="col-md-2" style='padding: 0px; text-align: left;'>
              <button type="button" class="btn" id="codecf_btn1" style='margin-left: 10px;'><strong>인증 확인</strong></button>
            </div>
            <div class="col-md-4"  id="panel_confirm1" style='padding: 5px;'>
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-offset-5 col-md-6" style='padding: 5px; margin-top: 20px;'>
              <button onclick="find_mempasswd();">비밀번호 찾기</button>                      
            </div>
          </div>
          
        </div>
      </li>
    </ul>
  </fieldset> 

  
</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>