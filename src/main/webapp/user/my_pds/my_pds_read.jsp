<%@page import="nation.web.tool.Tool"%>
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

<script type="text/javascript">

function deletePds(stdlist_no, pdsno){
  var del_modal=$('#delete_Modal');
  del_modal.modal();
  
  $(':password').focus(); // 비밀번호 입력창에 포커스
  $('#modal_footer', del_modal).attr("data-pdsno",pdsno);
  $('#confirm_content', del_modal).attr("onclick", "location.href='./read.do?stdlist_no="+stdlist_no+"&pdsno="+pdsno+"'");   // "삭제 전 글 확인하러 가기" 의 속성값을 추가.
}

/**
 * [삭제 모달창]이 출력된 후 패스워드 입력 후 form의 동작을 callback하는 메소드.
 * 
 * [패스워드] 창에 입력된 값을 pdspasswd에 저장.
 * 
 * pdsno는 위에서 deletePds에서의 footer의 data-pdsno 속성 값을 가져온다.
 * 
 */
function send(){
  var pdspasswd=$("#pdspasswd").val(); // 입려된 패스워드 값
  var check_count=0;                   // 패스워드 일치 여부 변수 (0:불일치, 1:일치)
  
  var del_modal=$('#delete_Modal');    // 삭제 모달창 객체 정의
  var pdsno=$('#modal_footer', del_modal).attr("data-pdsno"); // 삭제 모달창이 가지고 있는 data-pdsno 속서 값 조회.
  var delete_frm=$('#delete_frm');                            
  $('#pdsno', delete_frm).attr('value',pdsno);                // 삭제 모달에 input hidden에 pdsno를 넣는다.
  
  /* 
     [패스워드 일치여부 검사]
     AJAX 통신 시작
     form의 전송과 AJAX의 비동기 방식이 순차적으로 되기 위해
     동기 방식으로 변경 -> 옵션[async:false]
  */
  $.ajax({
    url: "./check_pdspasswd.do",
    type: "GET",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    async: false,
    data: "pdsno="+pdsno+"&pdspasswd="+pdspasswd,
    success: function(data){
      
      check_count=data.passwd_check; // 컨트롤러로 부터 넘겨받은 값을 AJAX 외부에서 사용하기 위해
      var msg="";                   // 모달창 내용
      if(data.passwd_check != 1){   // 패스워드가 불일치 할 때만 모달창을 띄운다.
        
        msg+="패스워드가 불일치 합니다."+"<br>";
        msg+="다시 확인 후 시도해주세요."+"<br>";
        msg+="<button class='btn btn-link'><img src='/study/my_pds/images/ask.png'>패스워드 분실 - [관리자에게 쪽지 보내기]</button>"+"<br>";
        $('#modal_title').html("패스워드 검사");
        $('#modal_content').html(msg);
        $('#modal_pwcheck').modal();
        
        $('#pdspasswd').val('');  /* 패스워드 값을 초기화하고 포커스를 준다. */
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
  
  /* 위의 AJAX 처리가 끝난 후 form의 동작여부를 결정한다. */
  if(check_count==1){ // 패스워드 일치 -> form 동작
    return true;
  }else{              // 패스워드 불일치 -> form 동작 x
    return false;
  }
}

// 글 추천수 증가 AJAX
// 글의 추천은 한번만 가능 -> 테이블이 따로 필요할 듯.
function like(pdsno){
  $.ajax({
    url: "./like.do",
    type: "GET",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: "pdsno="+pdsno,
    success: function(data){
      if(data.like_count==1){
        // 좋아요 증가 성공 -> 화면의 하트를 빨간색으로 변경, 카운트 +1
        $("#like_img").attr("src","/study/my_pds/images/like_after.png");  // "좋아요" 버튼을 누르면 이미지 변경.
        
        var like_cnt=Number($("#like_cnt").text());   // id가 like_cnt인 태그의 값을 조회한 후 Number형으로 변환.
        $("#like_cnt").text(like_cnt+1);              // 1증가하여 다시 입력
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
}

</script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>

  <!-- 삭제 확인 Modal -->
  <div class="modal fade" id="delete_Modal" role="dialog">
    <!-- 삭제 확인 Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h3 class="modal-title text-error"><img src='/study/user/my_pds/images/warning.png'>게시글 삭제</h3>
      </div> 
      <div class="modal-body"> 
        <!-- 모달 본문 -->
        <span class="text-error" style="font-weight: bolder;"><img src='/study/user/my_pds/images/warn_message.png'>삭제하면 복구가 불가 합니다.</span><br>
        <span class="text-error" style="font-weight: bolder;">정말로 삭제하시겠습니까?</span><br>
      </div>
      <div id="modal_footer" class="modal-footer">
        <form id="delete_frm" name="delete_frm" style="margin: 0px;" method="POST" action="./delete.do" onsubmit="return send();">
          <input type="hidden" name="stdlist_no" id="stdlist_no" value="${param.stdlist_no}">
          <input type="hidden" name="cateno" id="cateno" value="${cookie.cateno.value }">
          <input type="hidden" name="pdsno" id="pdsno" value="">
          
          <span><img src='/study/user/my_pds/images/passwd.png'>비밀번호:</span> 
          <input type="password" id="pdspasswd" name="pdspasswd" placeholder="비밀번호 입력" maxlength="4" required="required" style="width: 90px; margin-bottom: 0px;">
          <button type="submit" class="btn btn-danger">삭제</button>
          <button type="button" class="btn btn-success" data-dismiss="modal">취소</button>
        </form>
      </div> 
    </div>
  </div>
  
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
      
      <div class="modal-footer">
        <button type="button" class="btn btn-info" data-dismiss="modal">확인</button>
      </div>
      
    </div>
  </div>
  <!-- 비밀번호 검사 결과 Modal END -->
   
  <DIV>
    <button class="btn btn-success" style="float: left; margin-top: 10px;" onclick="location.href='/study/user/mystudy/mystudy_space.do?stdlist_no=${param.stdlist_no}'"><i class="icon-left-open"></i>My Study</button>
    <h3 style="float: right"><img src="./images/pencile.png">글</h3>
    <hr style="color: #000000; border: solid 2px #000000; clear: both;">
  </DIV> 
  
  <DIV style="border: solid 1px #000000; margin-bottom: 20px; padding: 20px;">
  
    <DIV style="position: fixed; right: 10px; top: 200px;">
      <button class="side_btngrp btn btn-success btn-small" style="width: 100%;" onclick="location.href='./create.do?stdlist_no=${param.stdlist_no}'">등록</button><br><br>
      <button class="side_btngrp btn btn-warning btn-small" style="width: 100%;" onclick="location.href='./update.do?stdlist_no=${param.stdlist_no}&pdsno=${param.pdsno }'">수정</button><br><br>
      <button class="side_btngrp btn btn-danger btn-small" style="width: 100%;" onclick="deletePds('${param.stdlist_no}', '${param.pdsno}')">삭제</button><br><br>
      <button class="side_btngrp btn btn-info btn-small" style="width: 100%;" onclick="like(${param.pdsno });"><img id="like_img" src='/study/user/my_pds/images/like_after.png'></button>
    </DIV>
 
    <DIV style="margin-top: 20px;">
      <ul>
        <li class="li_none">
          <DIV style="background-color: #eee;"> 
            <div>
              <span style="float: right;"><img src='/study/user/my_pds/images/member2.png'>작성자:${My_pdsVO.memname }</span>
              <span style="float: right;"><img id="like_img" src='/study/user/my_pds/images/like_before.png'>좋아요:<span id="like_cnt">${My_pdsVO.pdslike }</span>&nbsp;&nbsp;&nbsp;</span>
              <span style="float: right;"><img src='/study/user/my_pds/images/cnt.png'>조회수:${My_pdsVO.pdscnt }&nbsp;&nbsp;&nbsp;</span>
              <span><img src='/study/user/my_pds/images/icon.png'>작성일:${My_pdsVO.pdsdate.substring(0,10) }</span>
            </div> 
          </DIV>
        </li>
      
        <li class="li_none" style="background-color: #eee">
          <span><img src='/study/user/my_pds/images/icon.png'>제목:</span>
          <span style="width: 90%; background-color: #eee" id="pdstitle">${My_pdsVO.pdstitle }</span>
        </li>
         
        <li class="li_none" style="background-color: #eee">
          <span><img src='/study/user/my_pds/images/icon.png'>[본문 내용]</span><br>
          <hr style="color: #000000; border: solid 1px; margin-top: 10px; margin-bottom: 10px;">
          <span>${My_pdsVO.pdscontent }</span>
        </li>
        
        <li class="li_none"> 
          <span><img src='/study/user/my_pds/images/file.png'>첨부파일:</span>
          <c:choose>
            <c:when test="${My_pdsVO.pdsfile1 != null}">
              <A href='${pageContext.request.contextPath}/download?dir=/my_pds/storage&filename=${My_pdsVO.pdsfile1}'>${My_pdsVO.pdsfile1}</A> (${My_pdsVO.pdsfilesize}KB)
            </c:when>
            <c:otherwise>
              <span>등록된 파일 없음</span>
            </c:otherwise>
          </c:choose>
          <!-- 파일명만 나오게 해서 다운로드 받을 수 있도록 한다. -->
          <!-- 1단계 : 단일 파일 -->
          <!-- 2단계 : 복수 파일 -->
        </li>
        
      </ul> 
    </DIV>
  </DIV>
  
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>