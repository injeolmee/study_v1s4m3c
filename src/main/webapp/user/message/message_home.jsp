<%@page import="nation.web.tool.Tool"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
<%
  String root = request.getContextPath();
  String memid= (String)session.getAttribute("memid");
%>
  
<!DOCTYPE html> 
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root %>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
</head>
 
<link href="./css/msg_style.css" rel='Stylesheet' type='text/css'>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="./js/msg_js.js"></script>  <!-- 쪽지 JavaScript 파일 --> 

<script type="text/javascript">
var websocket;
$(function(){
  websocket=new WebSocket("ws://172.16.7.19:9090/study/chat-ws?id="+"<%=memid %>");
  
  websocket.onopen=function(){  
    console.log("서버 연결");
    websocket.send("<%=memid %>"+"/connect"); 
  }
  
  websocket.onclose=function(){
    console.log("서버 연결 해제");
  }
  
  websocket.onmessage=onMessage; 
  
  function onMessage(){
    console.log("소켓 메세지 수신");
    update_recv_count();
    $("#new_msg").fadeIn();
    
    var i=0; 
    setInterval(function(){
      if(i==0){
        $("#new_msg_img").prop("src","./images/after_read.png");
        i=1;
      }else{
        $("#new_msg_img").prop("src","./images/message.png");
        i=0; 
      }
    }, 1000);
    
    // 현재 보고 있는 화면이 "받은 쪽지함"이면 다시 로드.
    if($("#recv_list_tbody").text()!=""){
      search_clear(0); // 받은 쪽지함 다시 로드. 
    }
    
  }
});
 
function close_popup(target, action){
  $(target).parent().fadeOut();
  
  if(action==0){
    $('.msg_menu').siblings().removeClass('active'); // 사이드 메뉴 active 모두 해제
    $('#msg_recv_title_li').addClass('active');  // 받은 쪽지함 active 적용 
    search_clear(0);
  }
}

function update_recv_count(){
  $.ajax({
    url: "./update_recv_msg_count.do",
    type: "POST",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    success: function(data){
      if(data.recv_cnt!=""){  // 메세지 전송 성공
        $('#msg_recv_title').html("받은쪽지함("+data.recv_cnt+")");
      }else{                // 메세지 전송 실패
        alert("데이터 에러");
      }
    },
    error: function (request, status, error){
      alert("Ajax 에러");
    }
  });
}

/** 
 * 쪽지 보내기 처리
 * (회원 아이디 검증 후 "전송 버튼 활성화")
 */  
function msg_create_proc(send_memid){
  var memid="";
  if(send_memid==null){
    memid=$('#memid').val();   // 받는이 아이디 값
  }else{
    memid=send_memid;
  }
  
  var modal=$('#modal_common');
  var msg_title=$('#msg_title').val();  // 쪽지 제목 
  var msg_content=$('#msg_content').val();  // 쪽지 내용
  if(memid =='' || msg_title=='' || msg_content==''){
    alert('모든 입력사항을 입력하세요.');
    return false;
  }
  
  /*  [URL 인코딩]
   *  - URL은 사용가능한 문자가 제한되어 있다.
   *  - 한글 : UTF-8 사용
   *  encodeURI() : 특수문자를 제외한 나머지 문자 인코딩 URL전체 인코딩 시에 사용을 권고함.
   *  encodeURIComponent() : 알파벳, 숫자, Alphanumeric Characters 외의 대부분의 문자를 인코딩한다.
   *
   *  [URL 디코딩]
   *  decodeURIComponent() : encodeURIComponent()로 이스케이핑된 문자열을 정상적인 문자열로 되돌린다.
   *  한글 -> encode하면 이스케이핑되어 %+숫자의 형태가 된다. 이를 정상 문자열로 변환할 수 있다.
   */
  
  msg_title=encodeURIComponent(msg_title);
  
  var param="memid="+memid+"&msg_title="+msg_title+"&msg_content="+msg_content;
  $.ajax({
    url: "./msg_create.do",
    type: "POST",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: param,
    success: function(data){
      if(data.result=="OK"){  // 메세지 전송 성공
        modal.modal('hide'); // 모달창 닫기.
        $('.msg_menu').siblings().removeClass('active'); // 사이드 메뉴 active 모두 해제
        $('#msg_send_title_li').addClass('active');  // 보낸 쪽지함 active 적용  
        msg_send_list(1); 
        websocket.send(memid+"/msg_send");   // 소켓 서버로 쪽지 전송 정보 보내기 
        alert("쪽지 전송 완료!");
      }else if(data.result=="Self OK"){
        modal.modal('hide'); // 모달창 닫기.
        $('.msg_menu').siblings().removeClass('active'); // 사이드 메뉴 active 모두 해제
        $('#msg_self_title_li').addClass('active');  // 보낸 쪽지함 active 적용
        msg_self_list(1); 
        alert("내게 쓰기 완료!"); 
      }else{                // 메세지 전송 실패
        alert("쪽지 전송에 실패했습니다.\n 다시 시도해주세요.");
      }
    },
    error: function (request, status, error){
      //에러시
    }
  });
}

</script>


<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container' style="position: sticky; bottom: 40px; top: 115px; left: 0; right: 0; width: 100%;">
<DIV class='content'>
  
  <DIV id="new_msg" class="popup1" style="display: none;">
    <div style="width: 250px; float: left;">  
      <span id="new_msg_text" style="font-size: 0.7em;"><img id="new_msg_img" src='./images/message.png'/> 새로운 쪽지가 도착 했습니다.</span>
    </div>   
    <button class="btn btn-xs btn-inverse" onclick="close_popup(this, 0);">보러가기</button>  
    <button class="btn btn-xs btn-inverse" onclick="close_popup(this);">&times;</button> 
  </DIV>
     
  <!-- 공통 모달창  --> 
  <div class="modal fade" id="modal_common" role="dialog" style="display: none;">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h3 class="modal-title text-error" id='modal_title'></h3>
      </div>
      <div class="modal-body" id="modal-body">
      </div>
      <div class="modal-footer" id="modal-footer">
      </div>
    </div> 
  </div>
  <!-- 공통 모달 종료 -->
  
  <!-- 회원 정보 모달 시작  --> 
  <div class="modal fade" id="modal_meminfo" role="dialog" style="display: none;">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h3 class="modal-title text-error" id='modal_title'></h3>
      </div>
      <div class="modal-body" id="modal-body">
        
      </div>
      <div class="modal-footer" id="modal-footer">
      </div>
    </div> 
  </div>
  <!-- 공회원 정보 모달 종료 -->
   
  <DIV style=" width: 190px; position: absolute; bottom: 0; left: 0; right: 0; top: 0; border: 0.5px solid #eee; padding-top: 15px;">
    <DIV style="text-align:center; width:100%;"> 
      <button class='btn btn-success' onclick='msg_create();'>쪽지 쓰기</button>
      <button class="btn btn-success" onclick="msg_create('${userid }');">내게 쓰기</button> 
    </DIV>
     
    <DIV style="position: absolute; top: 60px; height: 250px; width: 100%;">
      <ul id='msg_menu_grp' style="margin-left: 5px;">
        <li id='msg_recv_title_li' class='li_none msg_menu active' style="font-size: 0.9em;" onclick=search_clear(0);><img id='recv_msg_img' src='./images/message.png'><span id='msg_recv_title'>받은쪽지함</span></li>
        <li id='msg_send_title_li' class='li_none msg_menu' style="font-size: 0.9em;" onclick=search_clear(1);><img src='./images/msg_send.png'><span id='msg_send_title'>보낸쪽지함</span></li>
        <li id='msg_repo_title_li' class='li_none msg_menu' style="font-size: 0.9em;" onclick=search_clear(2);><img src='./images/msg_repo.png'><span id='msg_repo_title'>쪽지보관함</span></li>
        <li id='msg_self_title_li' class='li_none msg_menu' style="font-size: 0.9em;" onclick=search_clear(3);><img src='./images/self_msg.png'><span id='msg_self_title'>내게쓴 쪽지함</span></li> 
      </ul>
    </DIV>
  </DIV>
  
  <DIV>
    <DIV>
      <DIV style="left:190px; top:0; right:0; position:absolute; overflow: scroll; height: 480px;">
        <DIV id='aside_menu' style="position: relative; border-bottom: 0.5px solid #eee;">
          <DIV style="padding: 10px; border: 0.5px solid #eee; height: 28px;">
            <DIV>
              <!-- 현재 위치 출력 (공통) -->
              <span id='category' style='float: left;'>받은 쪽지함</span>
              
              <!-- 쪽지 검색 창 (공통) -->
              <form id='search_msg' name='search_msg'> 
                <fieldset style="text-align: right;">
                  <select style="font-size: 14px;" id='search_area'>
                    <option value='none'>선택(영역)</option>
                    <option value='0'>받은쪽지</option> 
                    <option value='1'>보낸쪽지</option>
                    <option value='2'>쪽지보관함</option>
                    <option value='3'>내게쓴쪽지함</option> 
                  </select>
                  
                  <select style="font-size: 14px;" id='search_condition'>
                    <option value="none">선택(조건)</option>
                    <option value="search_id">아이디</option>
                    <option value="msg_title">제목</option>
                    <option value="msg_content">내용</option>
                  </select>
                  
                  <input class="form-search" style="margin-bottom: 0px;" type="text" id='msgword' name='msgword' placeholder="쪽지검색">
                  <button type="button" class='btn btn-link' onclick='search_control();'><img src='./images/search.png'>검색</button> 
                </fieldset>
              </form>
            </DIV>
          </DIV>
        </DIV>
        
        <!-- 받은 쪽지 테이블 --> 
        <DIV id="msg_recv_list" style="position: relative; display: none;">
          <DIV id='task_btn' style="position: relative;"> 
            <button class='btn btn-xs btn-danger' onclick='remove(0);' style="border-radius:10px;"><img src='./images/msg_remove.png' title="삭제">삭제</button>
            <button class='btn btn-xs btn-info' onclick='move_repo(0);' style="border-radius:10px;"><img src='./images/msg_remove.png' title="보관">보관</button>
          </DIV> 
          <TABLE class='table table-striped' style='border-radius:10px;'>
            <colgroup id='list_colgroup'>
              <col style='width: 3%;'/>
              <col style='width: 8%;'/> 
              <col style='width: 15%;'/> 
              <col style='width: 15%;'/>
              <col style='width: 40%;'/> 
              <col style='width: 7%;'/>
            </colgroup>
            <thead style="font-size: 0.9em;">
              <TR id='list_tr'>
                <TH><input type="checkbox" id='all_recv_check'></TH>
                <TH style='text-align: center ;'></TH>
                <TH style="text-align: center ;">보낸사람</TH>
                <TH style='text-align: center ;'>제목</TH>
                <TH style='text-align: center ;'>내용</TH>
                <TH style='text-align: center ;'>받은날짜</TH>
              </TR>
            </thead>
            <tbody style="font-size: 0.8em;" id='recv_list_tbody'>
            <!-- AJAX를 통해 <tr>태그 삽입 -->
            </tbody>
          </TABLE>
          
        </DIV>
        
        <!-- 보낸 쪽지 테이블 -->
        <DIV id="msg_send_list" style="position: relative; display: none;">
          <DIV id='task_btn' style="position: relative;">
            <button class='btn btn-xs btn-danger' onclick='remove(1);' style="border-radius:10px;"><img src='./images/msg_remove.png' title="삭제">삭제</button>
            <button class='btn btn-xs btn-info' onclick='move_repo(1);' style="border-radius:10px;"><img src='./images/msg_remove.png' title="보관">보관</button>
          </DIV>
          <TABLE class='table table-striped' style='border-radius:10px;'>
            <colgroup id='list_colgroup'>
              <col style='width: 3%;'/>
              <col style='width: 8%;'/>
              <col style='width: 15%;'/> 
              <col style='width: 23%;'/> 
              <col style='width: 35%;'/>
              <col style='width: 7%;'/> 
              <col style='width: 7%;'/>   
            </colgroup>
            <thead style="font-size: 0.9em;"> 
              <TR id='list_tr'>
                <TH><input type="checkbox" id='all_send_check'></TH> 
                <TH style='text-align: center ;'>수신여부</TH>
                <TH style="text-align: center ;">받는사람</TH>
                <TH style='text-align: center ;'>제목</TH>
                <TH style='text-align: center ;'>내용</TH>
                <TH style='text-align: center ;'>보낸날짜</TH>
                <TH style='text-align: center ;'>수신날짜</TH>
              </TR>
            </thead>
            <tbody style="font-size: 0.8em;" id='send_list_tbody'>
            <!-- AJAX를 통해 삽입 -->
            </tbody>
          </TABLE>
        </DIV>
        
        <!-- 쪽지 보관함 테이블 -->
        <DIV id="msg_repo_list" style="position: relative; display: none;">
          <DIV id='task_btn' style="position: relative;"> 
            <button class='btn btn-xs btn-danger' onclick='remove(2);' style="border-radius:10px;"><img src='./images/msg_remove.png' title="삭제">삭제</button>
          </DIV>
          <TABLE class='table table-striped' style='border-radius:10px;'>
            <colgroup id='list_colgroup'>
              <col style='width: 3%;'/>
              <col style='width: 10%;'/>
              <col style='width: 10%;'/> 
              <col style='width: 13%;'/>  
              <col style='width: 50%;'/>
              <col style='width: 7%;'/>
              <col style='width: 7%;'/> 
            </colgroup>
            <thead style="font-size: 0.9em;"> 
              <TR id='list_tr'>
                <TH><input type="checkbox" id='all_repo_check'></TH> 
                <TH style='text-align: center ;'>보낸사람</TH>
                <TH style="text-align: center ;">받는사람</TH>
                <TH style='text-align: center ;'>제목</TH>
                <TH style='text-align: center ;'>내용</TH>
                <TH style='text-align: center ;'>보낸날짜</TH>
                <TH style='text-align: center ;'>확인날짜</TH> 
              </TR> 
            </thead>
            <tbody style="font-size: 0.8em;" id='repo_list_tbody'>
            <!-- AJAX를 통해 삽입 -->
            </tbody>
          </TABLE>
        </DIV>
        
        <!-- 내게쓴 보관함 테이블 -->
        <DIV id="msg_self_list" style="position: relative; display: none;">
          <DIV id='task_btn' style="position: relative;"> 
            <button class='btn btn-xs btn-danger' onclick='remove(3);' style="border-radius:10px;"><img src='./images/msg_remove.png' title="삭제">삭제</button>
          </DIV>
          <TABLE class='table table-striped' style='border-radius:10px;'>
            <colgroup id='list_colgroup'> 
              <col style='width: 3%;'/>
              <col style='width: 5%;'/>
              <col style='width: 10%;'/> 
              <col style='width: 18%;'/>  
              <col style='width: 57%;'/>
              <col style='width: 7%;'/>
            </colgroup>
            <thead style="font-size: 0.9em;"> 
              <TR id='list_tr'>
                <TH><input type="checkbox" id='all_self_check'></TH>
                <TH style="text-align: center ;"></TH>
                <TH style='text-align: center ;'>보낸사람</TH>
                <TH style='text-align: center ;'>제목</TH>
                <TH style='text-align: center ;'>내용</TH>
                <TH style='text-align: center ;'>보낸날짜</TH>
              </TR> 
            </thead>
            <tbody style="font-size: 0.8em;" id='self_list_tbody'>
            <!-- AJAX를 통해 삽입 -->
            </tbody>
          </TABLE>
        </DIV>
        
        <DIV id='msg_none' style="text-align: center;">
          <!-- AJAX를 통해 삽입 -->
        </DIV>
        
        <DIV id='paging' style="text-align: center; height:50px;">
          <!-- Ajax를 통해 삽입 -->
        </DIV>
        
        <DIV id='read_msg' style="display: none; padding: 30px; margin:10px; border: 0.2px solid;">
          <!-- AJAX를 통해 삽입 -->
        </DIV>
      </DIV>
    </DIV>
  </DIV>
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>