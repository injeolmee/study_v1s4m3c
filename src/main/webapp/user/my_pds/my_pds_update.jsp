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

<link href="/study/user/my_pds/css/my_pds_style.css" rel='Stylesheet' type='text/css'>
 
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">  
  window.onload=function(){
    CKEDITOR.replace('pdscontent');  // <TEXTAREA>태그 id 값
    
    $(':text').first().focus();
  };
  
  
  function send(pdsno){
    
    var pdspasswd=$("#pdspasswd").val();
    var check_count=0;
    
    /* AJAX가 비동기 방식이기에 check_count의 값이 결정되기 전에 form이 submit되어 버린다. 
       그렇기에 AJAX를 동기방식으로 사용하여야 한다.
       
       옵션 중에 async를 false로 하여 동기방식으로 변경한다.
    */
    $.ajax({
      url: "./check_pdspasswd.do",
      type: "GET",
      cache: false,    // 일반적으로 false
      dataType: "json", // or json
      async: false,
      data: "pdsno="+pdsno+"&pdspasswd="+pdspasswd,
      success: function(data){
        
        //console.log(data);
        
        check_count=data.passwd_check;
        
        //console.log(check_count);
        
        // json에서 받은 값(data 객체)을 jquery를 사용하여 대입한다.
        var msg="";
        
        console.log($('#modal_pwcheck').css("display"));
        
        // 패스워드가 불일치 할 때만 모달창을 띄운다.
        if(data.passwd_check != 1){
          
          msg+="패스워드가 불일치 합니다."+"<br>";
          msg+="다시 확인 후 시도해주세요."+"<br><br>";
          msg+="<button class='btn btn-link'><img src='/study/my_pds/images/ask.png'>패스워드 분실 - [관리자에게 쪽지 보내기]</button>"+"<br>";
          $('#modal_title').html("<img src='/study/user/my_pds/images/warning.png'>패스워드 불일치");
          $('#modal_content').html(msg); 
          $('#modal_footer').html("<button type='button' class='btn btn-info' data-dismiss='modal'>확인</button>");
          $('#modal_pwcheck').modal();
          
          //console.log($('#modal_pwcheck').css("display"));
          
          /* 패스워드 값을 초기화하고 포커스를 준다. */
          $('#pdspasswd').val('');
          $('#pdspasswd').focus();
        }
        
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error: function (request, status, error){
        var msg = "ERROR<br><br>"
        msg +="에러가 발생 했습니다."+"<br>";
        msg +="다시 시도해주세요."+"<br>";
        msg +="[관리자 문의]☎ 010-0000-000, Tel:02-0000-0000"+"<br>";
        //msg += request.status + "<br>" + request.responseText + "<br>" + error;
        
        $('#modal_title').html("시스템 에러");
        $('#modal_content').html(msg);
        $('#modal_pwcheck').modal();
      }
    });
    
    if(check_count==1){
      return true;
    }else{
      return false;
    }
  }
  
  function passwd_ck(pdsno, filename, thumb){
    console.log(pdsno);
    console.log(filename);
    console.log(thumb);
    var pdspasswd=$("#passwd").val();
    $.ajax({
      url: "./check_pdspasswd.do",
      type: "GET",
      cache: false,    // 일반적으로 false
      dataType: "json", // or json
      async: false,
      data: "pdsno="+pdsno+"&pdspasswd="+pdspasswd,
      success: function(data){
        
        var msg="";
        
        if(data.passwd_check ==1){
          deletefile(pdsno, filename, thumb);
        }else{
          msg+="패스워드가 불일치 합니다."+"<br>";
          msg+="다시 확인 후 시도해주세요."+"<br><br>"; 
          msg+="<button class='btn btn-link'><img src='/study/user/my_pds/images/ask.png'>패스워드 분실 - [관리자에게 쪽지 보내기]</button>"+"<br>";
          $('#modal_title').html("<img src='/study/user/my_pds/images/warning.png'>패스워드 불일치");
          $('#modal_content').html(msg);
          $('#modal_footer').html("<button type='button' class='btn btn-info' data-dismiss='modal'>확인</button>");
          $('#modal_pwcheck').modal();
          
          $('#passwd').val('');
          $('#passwd').focus();
        }
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error: function (request, status, error){
        var msg = "ERROR<br><br>"
        msg +="에러가 발생 했습니다."+"<br>";
        msg +="다시 시도해주세요."+"<br>";
        msg +="[관리자 문의]☎ 010-0000-000, Tel:02-0000-0000"+"<br>";
        //msg += request.status + "<br>" + request.responseText + "<br>" + error;
        
        $('#modal_title').html("시스템 에러");
        $('#modal_content').html(msg);
        $('#modal_footer').html("<button type='button' class='btn btn-info' data-dismiss='modal'>확인</button>");
        $('#modal_pwcheck').modal();
      }
    });
  }
  
  
  function deletefile(pdsno, filename, thumb){
    $.ajax({
      url: "./del_file.do",
      type: "GET",
      cache: false,    // 일반적으로 false
      dataType: "json", // or json
      async: false,
      data: "pdsno="+pdsno+"&filename="+filename+"&thumb="+thumb,
      success: function(data){
        // 저장 폴더에서 삭제되고, DB에서도 삭제되었을 때.
        
        if(data.result==1){
          $('#file_exist').text("등록된 파일 없음");
          $('#del_file_btn').hide();
          
          $('#modal_title').html("<img src='/study/user/my_pds/images/warning.png'>파일 삭제 완료");
          $('#modal_content').html("삭제 완료");
          $('#modal_footer').html("<button type='button' class='btn btn-info' data-dismiss='modal'>확인</button>");
        }
      },
      error: function (request, status, error){
        var msg = "ERROR<br><br>"
          msg +="에러가 발생 했습니다."+"<br>";
          msg +="다시 시도해주세요."+"<br>";
          msg +="[관리자 문의]☎ 010-0000-000, Tel:02-0000-0000"+"<br>";
          //msg += request.status + "<br>" + request.responseText + "<br>" + error;
          
          $('#modal_title').html("시스템 에러");
          $('#modal_content').html(msg);
          $('#modal_footer').html("<button type='button' class='btn btn-info' data-dismiss='modal'>확인</button>");
          $('#modal_pwcheck').modal();
        }
      });
  }
  
  function file_delete(pdsno, filename, thumb){
    var msg="";
    var footer="";
    
    console.log(pdsno);
    console.log(filename);
    console.log(thumb);
    
    msg+="파일을 삭제하면 복구할 수 없습니다.";
    msg+="<button class='btn btn-link'><img src='/study/user/my_pds/images/ask.png'>패스워드 분실 - [관리자에게 쪽지 보내기]</button>"+"<br>";
    
    footer+='<span style="font-weight: bolder;"><img src="/study/user/my_pds/images/passwd.png">비밀번호</span> ';
    footer+='<input type="password" id="passwd" name="passwd" placeholder="비밀번호 입력" maxlength="4" required="required" style="width: 90px; margin-bottom: 0px;">';
    footer+='<button type="button" class="btn btn-danger" onclick="passwd_ck(\''+pdsno+'\',\''+filename+'\',\''+thumb+'\')">삭제</button>';
    footer+='<button type="button" class="btn btn-success" data-dismiss="modal">취소</button>';
    
    $('#modal_title').html("<img src='/study/user/my_pds/images/warning.png'>파일 삭제");
    $('#modal_content').html(msg);
    $('#modal_footer').html(footer); 
    $('#modal_pwcheck').modal();
    
  }
  
</script>

</head>

<!--

     request로 받은 파라미터 : pdsno 
     
     컨틀롤러로 부터 받은 파라미터 : My_pdsVO
-->
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>

  <DIV>
    <button class="btn btn-success" style="float: left; margin-top: 10px;" onclick="history.go(-1);"><i class="icon-left-open"></i>이전</button>
    <h3 style="float: right"><img src="./images/pencile.png">글 수정하기</h3>
    <hr style="color: #000000; border: solid 2px #000000; clear: both;">
  </DIV>
  
  <!-- 비밀번호 검사 결과 Modal -->
  <div class="modal fade" id="modal_pwcheck" role="dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h3 class="modal-title text-error" id='modal_title'></h3>
      </div>
      <div class="modal-body"> 
        <span id='modal_content' class="text-error" style="font-weight: bolder;"></span>
      </div>
      <div id="modal_footer" class="modal-footer">
      </div>
    </div>
  </div>
  <!-- 비밀번호 검사 결과 Modal END -->
  
  
  <!-- 비밀번호 검사 필요 -->
  <DIV style="border: solid 1px #000000; margin-bottom: 20px; padding: 20px;">
    <DIV style="margin-top: 20px;">
      <FORM name="update_frm" id="update_frm" method="POST" action="./update.do" class="form-horizontal" enctype="multipart/form-data" onsubmit="return send(${param.pdsno});">
        
        <!-- 넘겨받은 pdsno 매개변수 -->
        <input type="hidden" name="pdsno" id="pdsno" value="${param.pdsno }">
        <input type="hidden" name="mylistno" id="mylistno" value="${My_pdsVO.mylistno }">
        
        <div class="control-group"> 
          <label class="control-label" for="title" style="width: 10%; text-align: center;"><img src='/study/user/my_pds/images/icon.png'>제목</label>
          <div class="controls" style="margin-left: 15px;">
            <input style="width: 70%;" type="text" id="pdstitle" name="pdstitle" value="${My_pdsVO.pdstitle }" required="required">
            <span style="width: 20%; text-align: right; float: right;"><img src='/study/user/my_pds/images/member2.png'>작성자:${My_pdsVO.memname }</span>
          </div>
        </div> 
        
        <!-- 본문 내용 -->
        <div class="control-group">
          <div class="controls" style="margin-left: 15px;">
            <textarea style="width: 90%; resize:none; height: 500px;" name="pdscontent" id="pdscontent">${My_pdsVO.pdscontent}</textarea>
          </div>
        </div> 
        
        <!-- 등록되어 있는 파일 -->
        <div class="control-group">  
          <label class="control-label" for="file" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/file.png'>현재파일</label>
          <div class="controls" style="margin-left: 15px;">
            <c:choose>
              <c:when test="${My_pdsVO.pdsfile1 != null}">
                <span id="file_exist">${My_pdsVO.pdsfile1 }</span>
                <button id="del_file_btn" class="btn btn-warning btn-mini" onclick="file_delete('${My_pdsVO.pdsno }', '${My_pdsVO.pdsfile1 }', '${My_pdsVO.pdsthumb }')"
                <%-- onclick="deletefile('${param.pdsno}', '${My_pdsVO.pdsfile1 }', '${My_pdsVO.pdsthumb }')" --%>>파일 삭제</button>
              </c:when>
              <c:otherwise>
                <span>등록된 파일 없음</span>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        
        <div class="control-group"> 
          <label class="control-label" for="file" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/file_add.png'>첨부파일</label>
          <div class="controls" style="margin-left: 15px;">
            <input type="file" name='file1MF' id='file1MF'>
          </div>
        </div>
        
        <div class="control-group">  
          <label class="control-label" for="word" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/word.png'>검색어</label>
          <div class="controls" style="margin-left: 15px;">
            <input type="text" name='pdsword' id='pdsword' value="${My_pdsVO.pdsword }">
          </div>
        </div>
        
        <div class="control-group"> 
          <label class="control-label" for="passwd" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/passwd.png'>비밀번호</label>
          <div class="controls" style="margin-left: 15px;">
            <input type="password" id="pdspasswd" name="pdspasswd" placeholder="비밀번호 입력" maxlength="4" required="required" style="width: 90px;">
          </div>
        </div>
        
        <div class="control-group" style="text-align: center;">
          <button class="btn btn-success" type="submit">수정</button>
          <button class="btn btn-danger" type="button" onclick="history.go(-1);">취소</button>
        </div>
      </FORM> 
    </DIV>
  </DIV>
  
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>