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
  var auth = '${sessionScope.admauth}';
  var memberno_session = '${sessionScope.memberno}';
  var memberno_VO = '${memberVO.memberno}';
  if(memberno_session != memberno_VO) {
    if(auth == 'M' || auth == 'A') {
    } else {
      alert("유효한 접근이 아닙니다.");
      location.href ='<%=root %>/main/index.do';
    }
  }
  
  $('#delete').click(function(){  
    // alert("탈퇴버튼 클릭");
    
    $.cookie('check_email', 'KEEP');
    $.cookie('code_send', 'KEEP');
    $.cookie('code_confirm', 'KEEP');
    
    var agree1 = $("input[name='agree1']:checked").val();
    var agree2 = $("input[name='agree2']:checked").val();
    var agree3 = $("input[name='agree3']:checked").val();
/*     alert("agree1: " + agree1);
    alert("agree2: " + agree2);
    alert("agree3: " + agree3); */
    
    if(agree1 == "Y") {
      $.cookie('agree1', 'TRUE');
    } else {
      $.cookie('agree1', 'FALSE');
    }
    if(agree2 == "Y") {
      $.cookie('agree2', 'TRUE');
    } else {
      $.cookie('agree2', 'FALSE');
    }
    if(agree3 == "Y") {
      $.cookie('agree3', 'TRUE');
    } else {
      $.cookie('agree3', 'FALSE');
    }
    
    if ($.cookie('agree1') == 'TRUE' && $.cookie('agree2') == 'TRUE' && $.cookie('agree3') == 'TRUE') {

      if($("#mempasswd").val() == null || $("#mempasswd").val() == '') {
        alert("비밀번호를 입력해주세요.");
      }
      var mempasswd_d = "mempasswd_d=" + $("#mempasswd").val();
      $.ajax({
        url : '/study/user/member/mem_delete.do',
        type: "POST",
        data : mempasswd_d, 
        dataType : 'JSON',
        success : function(data){
          // alert(data.login_cnt);
          if(data.cnt_mempasswd==1){
            if(data.cnt_withdraw==1) {
              alert("회원 탈퇴가 완료하였습니다.\n그동안 이용해 주셔서 감사합니다.");
              location.href ='<%=root %>/main/index.do';
            } else {
              alert("회원 탈퇴가 실패하였습니다.\n다시 시도해주세요.");
            }
            
          } else {
            alert("패스워드가 다릅니다.\n다시 시도해주세요.");
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
      
    } else {
      alert("유의사항에 동의해주세요.");
    }
    
    
  });


}); // function 끝
</script>
</head>

<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container' style='margin-bottom: 5%;'>
<DIV class='content' style='width: 90%; margin: 0px auto;'>

    <div class="logmod__container" style='width: 50%; margin: 0px auto; padding-top: 30px;'>
      <ul class="logmod__tabs">
        <li data-tabtar="lgm-1"><a href="#">회원 탈퇴</a></li>
      </ul>
      <div class="logmod__tab-wrapper">
      <div class="logmod__tab lgm-1">
        <div class="logmod__heading">
          <span class="logmod__heading-subtitle">회원탈퇴에 앞서 <strong>유의사항 및 안내</strong>를 반드시 읽고 진행해 주세요.</span>
        </div>
        <div class="logmod__form">
          <form accept-charset="utf-8" name='frm_delete' id='frm_delete' method='POST' class="simform">
          
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" style='font-size: 16px;'><strong>ID 재사용 및 복구 불가 안내</strong></label>
                <span style='font-size: 14px;'>회원탈퇴 진행 시 본인을 포함한 타인 모두 ID 재사용이나 복구가 불가능합니다.<br>
                신중히 선택하신 후 결정해주세요.</span>
              </div>
              <div style='padding-bottom: 5px; float: right;'>
                <label for="agree1"><input type='checkbox' id='agree1' name='agree1' value='Y'>
                <span style='margin-left: 5px; font-size: 13px;'>동의</span></label>
              </div>
            </div>
            
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" style='font-size: 16px;'><strong>내정보 및 서비스 이용 기록 삭제 안내</strong></label>
                <span style='font-size: 14px;'>내정보 및 개인형 서비스 이용기록이 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.<br>
                삭제되는 서비스를 확인하시고, 필요한 데이터는 미리 백업을 해주세요.</span>
              </div>
              <div style='padding-bottom: 5px; float: right;'>
                <label for="agree2"><input type='checkbox' id='agree2' name='agree2' value='Y'>
                <span style='margin-left: 5px; font-size: 13px;'>동의</span></label>
              </div>
            </div>
            
            <div class="sminputs" style='height: 120px;'>
              <div class="input full">
                <label class="string optional" style='font-size: 16px;'><strong>게시판형 서비스에 등록한 게시글 삭제 불가 안내</strong></label>
                <span style='font-size: 14px;'>삭제를 원하는 게시글이 있다면 반드시 회원탈퇴 전 비공개 처리하거나 삭제하시기 바랍니다.<br>
                탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, <br>
                게시글을 임의로 삭제해드릴 수 없습니다.</span>
              </div>
              <div style='padding-bottom: 5px; float: right;'>
                <label for="agree3"><input type='checkbox' id='agree3' name='agree3' value='Y'>
                <span style='margin-left: 5px; font-size: 13px;'>동의</span></label>
              </div>
            </div>
            
            <div class="sminputs">
              <div class="logmod__heading">
                <span class="logmod__heading-subtitle" style='line-height: 30px;'>비밀번호 확인 후 ID는 즉시 탈퇴됩니다.<br>
                탈퇴 후에는 동일 ID로 다시 가입할 수 없으며 ID와 데이터는 복구할 수 없습니다.</span>
              </div>
            </div>
            
            <div class="sminputs">        
              <div class="input full">
                <label class="string optional" for="user-name">* ID</label>
                <input class="string optional" style='margin-bottom: 0px; ' maxlength="255" value='${memberVO.memid }' type="text" size="50" disabled="disabled"/>
              </div> 
            </div>
            
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">Password *</label>
                <input class="string optional" maxlength="255" id="mempasswd" name="mempasswd" value='' placeholder="Password" type="password" size="50" />
                            <span class="hide-password">Show</span>
              </div>
            </div>
            
            <div class="simform__actions">
              <input class="sumbit" id="delete" name="delete" type="button" value="회원 탈퇴" style='width: 100px;' />
              <input class="sumbit" onclick="location.href='<%=root%>/main/index.do' " type="button" value="취소" style='border-right: solid; width: 100px;' />
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