<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>회원 가입</title> 
 
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 

<script type="text/javascript">
var button = $('#btn1');
button.onclick = function() {  
}

/* $(function(){
  $.removeCookie('ch_email'); // 기존 쿠키값 삭제


  }); */
  
  
  function ch_email() {
    var params = 'mememail=' + $('#mememail').val();
    if($("#mememail").val() == '' || $("#mememail").val()==null)
    {
      alert("이메일을 입력하세요");
      return false;
    }
    $.ajax({
      url: "/ch_email.do",
      type: "GET",
      cache: false,
      async: false,
      dataType: "text",
      success: function(response) {
        if(response == '0') {
          count = 1;
          alert("아이디가 중복되지 않습니다. 쓰셔도 됩니다")
        } else {
          alert("이이디가 중복됩니다 다시 입력해 주세요")
          return false;
        }
      },
      error: function(request, status, error) {
        if(request.status != '0') {
          alert("code : " + request.status + "\r\nmessage : "
              + request.reponseText + "\r\nerror : " + error);
        }
      }
    })
/*     $.post('./ch_email.do', params, ch_email_res, 'json');
    console.log("mememail: ", mememail);
    // ----- 요청주소, 전달값, 응답처리함수, 전송받는방식
    alert('버튼: ' + mememail);   */
    
  };
  
  function form_check(){
    var mememail = $('#mememail').val();
    
    if (mememail.value == '' || mememail.vlaue == null) {
      alert('이메일을 입력하세요');
      focus.mememail;
      return false;
    }
    if(count == 0) {
      alert("중복검사를 눌러주세요");
      return false
    } else {
      save();
    }
 
  }
  
  function init() {
    count = 0;
  }
  
  function save() {
    var join = $('#join').val();
    join.submit();
    alert("가입이 완료되었습니다");
  }
  
  function count_check() {
    if(count == 1) {
      count = 0;
    }
  }
  
/*   
  function ch_email_res(data) {
    console.log('data: ' + data);  
    if(data.cnt == 0) {
      $('#panel_email_pas').show();
      $.cookie('ch_email', 'PASS'); // 쿠키 생성     
    } else if(data.cnt == 1) {
      $('#panel_email_un').show();
      $('#mememail').focus();
    }
    // alert('cookie: ' + $.cookie('ch_email'));    
  }
  
  
  function panel_code_email() {
    var check = $.cookie('ch_email');
    if (check != 'PASS') {
      $('#panel_code_email').css('color', '#ff0000');
      $('#panel_code_email').html('이메일에서 코드를 인증해주세요.');
      return false;
    } else {
      rreturn true;
    }
  }
  
  
  function send() {
    var check = $.cookie('ch_email');
    if (check != 'PASS') {
      $('#panel_email').css('color', '#ff0000');
      $('#panel_email').html('이메일 중복 검사를 해주세요.');
      $('#mememail').focus();
      return false;
    } else {
      rreturn true;
    }
  }
   */
    
  
 </script>
</head> 
 
<body>

<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>

<DIV class='content' style='width: 70%; margin: 0px auto; text-align: center;'>
 
<DIV class='title_line' style='width: 60%; margin: 50px 150px 50px; font-size: 20px;'>회원 가입</DIV>
 
<FORM name='join' id = 'join' method='POST' action='./create.do' enctype="multipart/form-data" class="form-horizontal">  

  <div style= 'background-color: #f2f2f2; padding: 30px 5px 10px;'>
  <div class="input-prepend input-append">
  <span class="add-on" style="width:110px; margin-left: -160px">* 이메일</span>
     <input type="text" id="mememail" name="mememail" autofocus="autofocus" placeholder="이메일@이메일.com" style="width:300px;" value="" title="이메일"
     onkeydown="count_check();" onkeypress="count_check();"> 
     <!-- <span class="add-on"><a href= './ch_email.do'>중복확인</a></span> -->
     <button type="button" class="btn" id="btn1" onclick="ch_email()">중복 검사</button>
     <div id="panel_email_pas" style= "width: 180px; margin: -25px 0px 5px 565px; text-align: left; color: green; display: none;">사용 가능한 이메일</div>          
     <div id="panel_email_un" style= "width: 180px; margin: -25px 0px 5px 565px; text-align: left; color: green; display: none;">중복된 이메일 입니다.</div>          
  </div>
  </div>
  
  <div style= 'background-color: #f2f2f2; padding: 10px;'> 
  <div class="input-prepend">
  <span class="add-on" style="width:110px; margin-left: -470px;">* 이메일 인증</span>
    <button type="button" class="btn" onclick="javascript: panel_code_email();">코드 발송</button>      
    <div id="panel_code_email" style= "width: 270px; margin: -25px 0px 5px 480px; text-align: left; color: red;">이메일에 코드가 발송되었습니다.</div>   
  </div>
  </div>
    

    
    <div style= 'background-color: #f2f2f2; padding: 5px 5px 30px;'>
      <button type="submit" class="btn btn-success">가입하기</button>
    </div>    
 

</FORM>
 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 