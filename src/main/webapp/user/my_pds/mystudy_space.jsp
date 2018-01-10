<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% 
String root = request.getContextPath();
int stdlist_no=Integer.parseInt(request.getParameter("stdlist_no")); 
%>
 
<!DOCTYPE html>
<html lang="ko">
<head> 
<meta charset="UTF-8">
<title>Study Matching Web Site</title>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript" src="../../js/jquery.cookie.js"></script>  <!-- jQuery를 통한 쿠키 관리 -->

<link href="/study/user/my_pds/css/my_pds_style.css" rel='Stylesheet' type='text/css'>
 
<script type="text/javascript"> 
 
$(document).ready(function(){
  
  var cateno=$.cookie('cateno');
  
  if($.cookie('nowpage')=='' && $.cookie('nowpage2')=='' && $.cookie('nowpage3')==''){
    set_nowpage_cookie(cateno, <%=stdlist_no%>, 1);  // 최초 홈페이지 실행시 동작
  }else{
    if(cateno==2){
      set_nowpage_cookie(cateno, <%=stdlist_no%>, $.cookie('nowpage'));
    }else if(cateno==3){
      set_nowpage_cookie(cateno, <%=stdlist_no%>, $.cookie('nowpage2'));
    }else{
      set_nowpage_cookie(cateno, <%=stdlist_no%>, $.cookie('nowpage3'));
    }
  }
  
  /* 공지사항, 자유게시판, 자료실 탭 클릭시 동작 */
  $("#tabs > li > a").click(function(){
    $(this).css("background-color", "#ffc107");
    $(this).parent().siblings().children().css("background-color", "#f1f1f1");
    $(this).parent().addClass("active");
    $(this).parent().siblings().removeClass("active");
    console.log($(this).attr('id'));
    
    var id=$(this).attr('id');
    $("#tab_div").children().filter("#"+id).addClass("active");
    $("#tab_div").children().filter("#"+id).siblings().removeClass("active");
    
  });
  
  /*탭 메뉴 포커스를 cateno를 통해 유지한다. */
  $("#tabs > #li_tab"+cateno).addClass("active");
  $("#tabs > #li_tab"+cateno+" > a").css("background-color", "#ffc107");
  $("#tab_div > #tab"+cateno).addClass("active");
  
});
 
function meminfo(memberno) {
  window.open("/study/user/member/mem_read_info.do?memberno="+memberno, "a", "resizable, width=450, height=500, left=500, top=100");
}

/* 버튼 드롭다운 */
function dropdown(pdsno, event){
  var dropdown_menu=$('#dropdown_menu'+pdsno);
  
  var sWidth = window.innerWidth;
  var sHeight = window.innerHeight;
  
  var oWidth = dropdown_menu.width();
  var oHeight = dropdown_menu.height();
  
  //레이어가 나타날 위치를 셋팅한다.
  var divLeft = event.clientX + 10;
  var divTop = event.clientY + 5;
  
  //레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
  if( divLeft + oWidth > sWidth ) divLeft -= oWidth;
  if( divTop + oHeight > sHeight ) divTop -= oHeight;

  // 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
  if( divLeft < 0 ) divLeft = 0;
  if( divTop < 0 ) divTop = 0;
  
  //console.log(pdsno); 
    
  //$('#dropdown'+pdsno).toggleClass('open'); --> 오히려 이 구문이 open의 추가를 막고 있었음. 
   
  dropdown_menu.css("position","fixed"); 
  dropdown_menu.css("display", "");
  dropdown_menu.css("left", divLeft);  // x위치 + 80px (우로 이동)
  dropdown_menu.css("top", divTop);  // y위치 - 200px(위로 이동)
  
}

/* 미리보기 화면 출력 */
function hello(event){  
  x = event.pageX;  // 마우스 포인터의 x위치
  y = event.pageY;  // 마우스 포인터의 y위치
  
  // 포인터가 있는 위치의 속성값(data-blogno)을 가져온다.
  var pdsno=event.target.getAttribute("data-pdsno");
  
  // 가져온 data-pdsno를 preview에 각각 더한다.
  var pre=$('#preview'+pdsno);
  
  //console.log(blogno);
  var sWidth = window.innerWidth;
  var sHeight = window.innerHeight;
  
  var oWidth = pre.width();
  var oHeight = pre.height();
  
  //레이어가 나타날 위치를 셋팅한다.
  var divLeft = event.clientX + 10;
  var divTop = event.clientY + 5;
  
  //레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
  if( divLeft + oWidth > sWidth ) divLeft -= oWidth;
  if( divTop + oHeight > sHeight ) divTop -= oHeight;

  // 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
  if( divLeft < 0 ) divLeft = 0;
  if( divTop < 0 ) divTop = 0;
  
  // preview의 속성을 변경한다.
  pre.css("position","fixed");
  pre.css("display", "");
  pre.css("left", divLeft);  // x위치 + 80px (우로 이동)
  pre.css("top", divTop);  // y위치 - 200px(위로 이동)
   
  $('.title_content').mouseout(function(event){
    var pdsno=event.target.getAttribute("data-pdsno");
    var pre=$('#preview'+pdsno);
    pre.css("display", "none");
  });
}

/* 쪽지 폼 출력
 * memberno_send : 보내는 사람 ID
 * memberno_rev : 받는 사람 ID
 */
function message(memberno_send, memberno_recv){
  var modal=$('#modal_common');
  var message_form="";
  
  message_form+="<DIV>";
  message_form+="  <label>제목</label>";
  message_form+="  <input type='text' id='msg_title' name='msg_title' placeholder='제목'><br>";
  message_form+="  <label>내용</label>"; 
  message_form+="  <textarea style='resize:none; overflow:scroll; height:200px; width:99%;' id='msg_content' name='msg_content' placeholder='내용'></textarea><br>";
  message_form+="  <DIV style='text-align:center;'><button type='submit' class='btn btn-info' onclick=tranfer_msn("+memberno_send+","+memberno_recv+");>전송</button>";
  message_form+="  <button type='button' class='btn btn-danger' data-dismiss='modal'>취소</button></DIV>";
  message_form+="</DIV>";
    
  $('#modal_title', modal).html("<img src='/study/my_pds/images/message.png'>[쪽지보내기]");
  $('#modal-body', modal).html(message_form);
  modal.modal();
}

/* 쪽지 AJAX 전송 처리  
 * 전송 성공시 : result=1
        실패시 : result=0
 */
function tranfer_msn(memberno_send, memberno_recv){
  var modal=$('#modal_common');
  var msg_title=$('#msg_title').val();
  var msg_content= $('#msg_content').val(); 
  
  var param="memberno_send="+memberno_send+"&memberno_recv="+memberno_recv+"&msg_title="+msg_title+"&msg_content="+msg_content;
   
  if(msg_title==''){
    alert('쪽지 제목을 입력하세요.');
    return false;
  }else if(msg_content==''){
    alert('쪽지 내용을 입력하세요.');
    return false;
  }
  
  $.ajax({
    url: "./msn.do",
    type: "POST",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: param,
    success: function(data){
      if(data.result=="OK"){  // 메세지 전송 성공
        alert("쪽지 전송 완료!");
        
        modal.modal('hide'); // 모달창 닫기.
        
      }else{                // 메세지 전송 실패
        alert("쪽지 전송에 실패했습니다.\n 다시 시도해주세요.\n 에러:"+data.result);
      }
    },
    error: function (request, status, error){
      
    }
  });
}

/* 검색 쿠키 초기화. */
function reset(no, cateno, stdlist_no){
  console.log(cateno, stdlist_no);
  $.cookie('pdsword'+no,'', {path:'/'});   // 쿠키 초기화.
  $('#pdsword'+no).val('');      // 검색창 값 초기화.
  notice(cateno, stdlist_no);  // 초기화하고 다시 로딩.
}

/* 페이징 notice 호출 경로 */
function set_nowpage_cookie(cateno, stdlist_no, nowpage){
  
  if(cateno==2){
    $.cookie('nowpage', nowpage, {path:'/'})
  }else if(cateno==3){
    $.cookie('nowpage2', nowpage, {path:'/'})
  }else{
    $.cookie('nowpage3', nowpage, {path:'/'})
  }
  //$.cookie('nowpage', nowpage, {path:'/'}); // 현재 페이지 쿠키 셋팅
  notice(cateno, stdlist_no);
}

function notice(cateno, stdlist_no){  // 페이지을 위해 start_page, end_page를 추가할 예정.
  
  $.cookie('cateno', cateno, {path:'/'});  // cateno 쿠키 설정
  var pdsword=""; 
  
  if(cateno==2){
    pdsword=$('#pdsword1').val();     // 검색창에 입력된 값을 가져옴.
    if(pdsword == ''){                // 검색어가 없으면
      pdsword=$.cookie('pdsword1');   // 쿠키 값을 pdsword에 입력
      $('#pdsword1').val(pdsword);    // pdsword를 검색창에 입력
    }else{                            // 검색창에 검색어 있으면
      $.cookie('pdsword1', pdsword, {path:'/'});    // 쿠키 <- 검색어
    }
    nowpage=$.cookie('nowpage');
  }else if(cateno==3){
    pdsword=$('#pdsword2').val();     // 검색창에 입력된 값을 가져옴.
    if($('#pdsword2').val() == ''){   // 검색어가 없으면
      pdsword=$.cookie('pdsword2');   // 쿠키 값을 pdsword에 입력
      $('#pdsword2').val(pdsword); 
    }else{                            // 검색창에 검색에 있으면
      $.cookie('pdsword2', pdsword, {path:'/'});    // 쿠키 <- 검색어
    }
    nowpage=$.cookie('nowpage2');
  }else{ 
    pdsword=$('#pdsword3').val();     // 검색창에 입력된 값을 가져옴.
    if($('#pdsword3').val() == ''){   // 검색어가 없으면
      pdsword=$.cookie('pdsword3');   // 쿠키 값을 pdsword에 입력 
      $('#pdsword3').val(pdsword); 
    }else{                            // 검색창에 검색에 있으면
      $.cookie('pdsword3', pdsword, {path:'/'});    // 쿠키 <- 검색어
    }
    nowpage=$.cookie('nowpage3');
  } 
  
  // 페이지 정보 가져오기
  $('.span_box1').click(function(){
    nowpage=$(this).attr('[data-nowpage]'); 
  });
  
  $.ajax({ 
    url: "./pds_notice.do",
    type: "GET",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: "cateno="+cateno+"&stdlist_no="+stdlist_no+"&pdsword="+pdsword+"&nowpage="+nowpage, 
    success: function(data){
      
      console.log("ajax 성공");
      console.log(data);
      
      // json에서 받은 값(data 객체)을 jquery를 사용하여 대입한다.
      
      var value="";
      
      value+="<TABLE class='table table-striped' style='margin-top: 1%; border-radius:10px;'>"
      value+="  <colgroup>"; 
      value+="    <col style='width: 5%;'/>"; 
      value+="    <col style='width: 30%;'/>";
      value+="    <col style='width: 10%;'/>";
      value+="    <col style='width: 10%;'/>";
      value+="    <col style='width: 15%;'/>";
      value+="    <col style='width: 10%;'/>";
      value+="    <col style='width: 10%;'/>";
      value+="  </colgroup> "; 
      
      value+="  <thead style='color:white;'>";
      value+="    <TR class='label-success' style='background-color: #2d2d30;'>";
      value+="    <TH style='text-align: center ;'>번호</TH>";
      value+="    <TH style='text-align: center ;'><img src='/study/user/my_pds/images/title.png'>제목</TH>";
      value+="    <TH style='text-align: center ;'><img style='width:15%;' src='/study/user/my_pds/images/like_after.png'>글 추천수</TH>";
      value+="    <TH style='text-align: center ;'><img src='/study/user/my_pds/images/cnt.png'>글 조회수</TH>";
      value+="    <TH style='text-align: center ;'><img src='/study/user/my_pds/images/member2.png'>작성자</TH>";
      value+="    <TH style='text-align: center ;'><img src='/study/user/my_pds/images/time.png'>작성일</TH>";
      value+="    <TH style='text-align: center ;'><img style='width:15%;' src='/study/user/my_pds/images/tool.png'>관리</TH>";
      value+="  </TR>";   
      value+="</thead>";
      value+="  <tbody id='meminfo_tbody'>";
      if(data.length>1){
        for(var i=0; i<data.length-1; i++){ 
          value+="  <tr id='meminfo_tr'>";
          value+="    <td style='text-align: center;'>"+(i+1)+"</td>";
          value+="    <td style='text-align: center;'><a data-pdsno='"+data[i].pdsno+"' class='title_content' onmouseover='hello(event);' href='./read.do?stdlist_no="+<%=stdlist_no%>+"&pdsno="+data[i].pdsno+"'>"+data[i].pdstitle+"</a></td>";
          value+="    <td style='text-align: center;'>"+data[i].pdslike+"</td>";
          value+="    <td style='text-align: center;'>"+data[i].pdscnt+"</td>";
          
          /* 회원 버튼 클릭시 드롭다운 메뉴 */
          value+="    <td style='text-align: center;'>";  
          value+="    <div id='dropdown"+data[i].pdsno+"' class='dropdown'>"; 
          value+="      <button onclick='dropdown("+data[i].pdsno+", event);' class='btn btn-link' type='button' data-memberno='"+data[i].memberno+"' data-toggle='dropdown'><span style='font-size:0.8em';>"+data[i].memid+"("+data[i].memname+")</span><span class='caret'></span></button>";
          value+="      <ul id='dropdown_menu"+data[i].pdsno+"' class='dropdown-menu'>";    
          value+="      <li style='text-align:left;'><a onclick='javascript:meminfo("+data[i].memberno+");'><img src='/study/user/my_pds/images/mem_info.png'>회원정보</a></li>";
          value+="      <li style='text-align:left;'><a onclick='javascript:message("+${sessionScope.memberno}+" ,"+data[i].memberno+");'><img src='/study/user/my_pds/images/message_add.png'>쪽지보내기</a></li>";
          //value+="      <li style='text-align:left;'><a onclick='javascript:wait();'>그외 기능</a></li>";  
          value+="      </ul>"; 
          value+="    </div>";
          value+="    </td>";
          
          value+="    <td style='text-align: center;'>"+data[i].pdsdate.substring(0,10)+"</td>";
          value+="    <td style='text-align: center;'>";

          if(("${sessionScope.memberno}"==data[i].memberno) && cateno!=2){
            value+="<button class='btn btn-link' onclick=\"location.href='./update.do?stdlist_no="+data[i].stdlist_no+"&pdsno="+data[i].pdsno+"&cateno="+data[i].cateno+"'\">";
            value+="<img src='/study/user/my_pds/images/update.png' title='수정'></button>";
            value+="<button class='btn btn-link' onclick='javascript:deletePds("+data[i].stdlist_no+","+data[i].pdsno+","+data[i].cateno+")'>";
            value+="<img src='/study/user/my_pds/images/delete.png' title='삭제'></button>";
          } 
          
          if(("${sessionScope.std_auth}"=="L") && cateno==2){
            value+="<button class='btn btn-link' onclick=\"location.href='./update.do?stdlist_no="+data[i].stdlist_no+"&pdsno="+data[i].pdsno+"&cateno="+data[i].cateno+"'\">";
            value+="<img src='/study/user/my_pds/images/update.png' title='수정'></button>";
            value+="<button class='btn btn-link' onclick='javascript:deletePds("+data[i].stdlist_no+","+data[i].pdsno+","+data[i].cateno+")'>";
            value+="<img src='/study/user/my_pds/images/delete.png' title='삭제'></button>";
          } 
          
          value+="    </td>";
          value+="  </tr>";
          /* 미리보기 DIV */
          value+="  <DIV id='preview"+data[i].pdsno+"' style='display: none; height: 250px; width: 400px; background-color: #000000; z-index: 1; border-radius:10px; text-align:center;'>;";
          
          /* 미리보기 - 제목 */
          value+="    <div style='text-align:left;'>";
          value+="      <span style='color: #ffffff; font-size:0.8em;'>제목:"+data[i].pdstitle+"</span><br>";
          value+="    </div>";
          
          /* 파일이 있는지 없는지 검사하여 이미지 출력 */
          if(data[i].pdsfile1==null || data[i].pdsfile1==""){
            value+="  <img style='width: 200px; height:100px;' src='/study/user/my_pds/images/none.png'>";
          }else{
            value+="  <img style='width: 200px; height:100px;' src='/study/user/my_pds/storage/"+data[i].pdsfile1+"'>";
          }
          
          /* 파일이 있는지 없는지 검사하여 파일명 출력 */
          value+="    <div style='text-align:left;'>";
          if(data[i].pdsfile1==null || data[i].pdsfile1==""){
          value+="      <span style='color: #ffffff; font-size:0.8em;'>첨부파일: 없음</span><br>";
          }else{
          value+="      <span style='color: #ffffff; font-size:0.8em;'>첨부파일: "+data[i].pdsfile1+"</span><br>";
          }
          value+="    </div>";
          value+="  </DIV>";
        }
        value+="  </tbody>";
        value+="</TABLE>";
        
        /* 페이징 출력 부분*/
        value+=data[data.length-1].paging;
        
      }else{
        // 아무 글도 없을 때.
        value+="  </tbody>";
        value+="</TABLE>";
        value+="    <div style='text-align:center'>";
        value+="      <img src='/study/user/my_pds/images/none_content.gif'>";
        value+="    </div>";
      } 
      
      /* 클릭하여 넘겨받은 cateno의 값에 따라 다른 DIV 출력 */
      if(cateno==2){
        // 검색을 하였을 때 -> pdsword가 공백이 아닐 때.
        if($('#pdsword1').val() != ''){
          if(data.length>1){  // 검색 결과가 있을 때.
            $('#search1').css('display','');
            $('#search1').html("[검색결과]"+data[0].search_count+"건 조회됨");
          }else{  // 검색 결과가 없을 때.
            $('#search1').css('display','');
            $('#search1').html("조회된 글 없음");
          }
        }else{ // 검색하지 않는 상황
          $('#search1').css('display','none');
        }
        $("#table_space_1").html(value); 
        $("#table_space_2").html("");
        $("#table_space_3").html("");
      }else if(cateno==3){
        // 검색을 하였을 때 -> pdsword가 공백이 아닐 때.
        if($('#pdsword2').val() != ''){
          if(data.length>1){  // 검색 결과가 있을 때.
            $('#search2').css('display','');
            $('#search2').html("[검색결과]");
            $('#search2').html("[검색결과]"+data[0].search_count+"건 조회됨");
          }else{  // 검색 결과가 없을 때.
            $('#search2').css('display','');
            $('#search2').html("조회된 글 없음");
          }
        }else{ // 검색하지 않는 상황
          $('#search2').css('display','none');
        }
        $("#table_space_2").html(value);
        $("#table_space_1").html("");
        $("#table_space_3").html("");
      }else{
     // 검색을 하였을 때 -> pdsword가 공백이 아닐 때.
        if($('#pdsword3').val() != ''){
          if(data.length>1){  // 검색 결과가 있을 때.
            $('#search3').css('display','');
            $('#search3').html("[검색결과]"+data[0].search_count+"건 조회됨");
          }else{  // 검색 결과가 없을 때.
            $('#search3').css('display','');
            $('#search3').html("조회된 글 없음");
          }
        }else{ // 검색하지 않는 상황 
          $('#search3').css('display','none');
        }
        $("#table_space_3").html(value);
        $("#table_space_1").html("");
        $("#table_space_2").html("");
      }
    },
    // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
    error: function (request, status, error){  
      var msg = "ERROR<br><br>"
      msg +="에러가 발생 했습니다."+"<br>";
      msg +="다시 시도해주세요."+"<br>";
      msg += request.status + "<br>" + request.responseText + "<br>" + error;
      
      var panel="";
      panel+="<DIV id='panel' class='popup1' style='height:150px; '>";
      panel+=msg;
      panel+="<br>[<A href=\"javascript:($('#panel_update').hide())\">CLOSE</A>]";
      panel+="</DIV>";
      
      $('#panel_update').html(panel);
      $('#panel_update').show();
    }
  });
}

$(document).ready(function(){
  $('[data-toggle="popover"]').mouseover().popover();
});

/**
 * deletePds는 stdlist_no, pdsno, cateno를 전달받으며, 이 값들은 Ajax를 통해 받은 값이다.
 * 삭제 버튼을 누르면 [삭제 모달창]이 출력되고 동시에 [삭제 모달창]의 footer 영역의 속성[data-pdsno]을 추가한다.
 * [삭제 모달창]내에 있는 "삭제 전 글 확인하러 가기"의 링크를 설정하기 위해 attr()를 사용하여 onclick 속성값을 정의한다.
 */
function deletePds(stdlist_no, pdsno, cateno){
  var del_modal=$('#delete_Modal');
  
  del_modal.modal();
  $(':password').focus();  // 비밀번호 입력창에 포커스
  
  $('#modal_footer', del_modal).attr("data-pdsno",pdsno);
  $('#cateno', del_modal).attr("value", cateno);
  $('#confirm_content', del_modal).attr("onclick", "location.href='./read.do?stdlist_no="+stdlist_no+"&pdsno="+pdsno+"&cateno="+cateno+"'");   // "삭제 전 글 확인하러 가기" 의 속성값을 추가.
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
        msg+="<button class='btn btn-link'><img src='/study/user/my_pds/images/ask.png'>패스워드 분실 - [관리자에게 쪽지 보내기]</button>"+"<br><br><br>";
        msg+="<DIV style='text-align:center;'><button type='button' class='btn btn-info' data-dismiss='modal'>확인</button></DIV>";
        $('#modal_title').html("<img src='/study/user/my_pds/images/warning.png'>패스워드 불일치");
        $('#modal-body').html(msg);  
        $('#modal_common').modal();
         
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
      $('#modal_common').modal();
    }
  });
  
  /* 위의 AJAX 처리가 끝난 후 form의 동작여부를 결정한다. */
  if(check_count==1){ // 패스워드 일치 -> form 동작
    return true;
  }else{              // 패스워드 불일치 -> form 동작 x
    return false;
  }

}

</script>


<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>
 
  <!-- 삭제 확인 Modal --> 
  <div class="modal fade" id="delete_Modal" role="dialog" style="display: none;">
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
        <button id="confirm_content" class="btn btn-info" style="font-weight: bolder;">게시글 확인하러 가기</button>
      </div>
      <div id="modal_footer" class="modal-footer">
        <form id="delete_frm" name="delete_frm" style="margin: 0px;" method="POST" action="./delete.do" onsubmit="return send();">
          <input type="hidden" name="stdlist_no" id="stdlist_no" value="${param.stdlist_no}">
          <input type="hidden" name="cateno" id="cateno" value="">
          <input type="hidden" name="pdsno" id="pdsno" value="">
           
          <span style="font-weight: bolder;"><img src='/study/user/my_pds/images/passwd.png'>비밀번호</span> 
          <input type="password" id="pdspasswd" name="pdspasswd" placeholder="비밀번호 입력" maxlength="4" required="required" style="width: 90px; margin-bottom: 0px;">
          <button type="submit" class="btn btn-danger">삭제</button>
          <button type="button" class="btn btn-success" data-dismiss="modal">취소</button>
        </form>
      </div>
    </div>
  </div>
  
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
    </div>
  </div>
  <!-- 비밀번호 검사 결과 Modal END --> 
 
  <DIV>    
    <button class="btn btn-success btn-xs" style="float: left; margin-top: 10px;" onclick="location.href='/study/user/mystudy/mystudy.do'"><i class="icon-left-open"></i>스터디 목록</button>
    <h4 style="text-align: right;"><img style="width: 1.5%;" src="/study/user/my_pds/images/pencile.png">스터디 공간</h4> 
    <hr style="color: #000000; border: solid 2px #000000; clear: both;">
  </DIV>

  <!-- 팝 오버 -->
  <!-- <a href="#" data-toggle="popover" title="안녕하세요 여러분!" data-content="저희 스터디에 오신 것을 환영 합니다.">스터디장의 인삿말</a> -->
    
    <div class="tabbable"> <!-- 왼쪽과 오른쪽 탭에만 필요 -->
    <ul class="nav nav-tabs" id="tabs" style="text-align: center; font-weight: bolder;">
      <li id="li_tab2" class="" style="width: 33.3%;">
        <a id="tab2" href="javascript:void(0);" onclick="javascript:set_nowpage_cookie(2, ${param.stdlist_no}, ${cookie.nowpage.value });"  data-toggle="tab" style="background-color: #f1f1f1; ">공지사항</a>
      </li>
      
      <li id="li_tab3" class="" style="width: 33.3%;">                                                <!-- 쿠키가 처음에 로드된 값으로 고정됨. -->
        <a id="tab3" href="javascript:void(0);" onclick="javascript:set_nowpage_cookie(3, ${param.stdlist_no}, ${cookie.nowpage2.value });" data-toggle="tab" style="background-color: #f1f1f1">자유게시판</a>
      </li>
       
      <li id="li_tab4" class="" style="width: 33.4%;">
        <a id="tab4" href="javascript:void(0);" onclick="javascript:set_nowpage_cookie(4, ${param.stdlist_no}, ${cookie.nowpage3.value });" data-toggle="tab" style="background-color: #f1f1f1">자료실</a>
      </li>
      
    </ul>
    
    <!-- 전체 탭 시작 -->
    <div class="tab-content well well-xs" id="tab_div">
      <!-- ======공지사항 탭 시작====== -->
      <div class="tab-pane" id="tab2" style="text-align: center;">
        <ASIDE style='margin-bottom: 10px; width: 100%; float: right; text-align: center;'>
          <DIV style="float: left">
            <span id='search1' style="font-size: 15px; font-weight:bolder; display: none">[검색결과]<span id='search_count1'>0</span>건 조회됨</span>
          </DIV> 
          <div class="input-append" style="margin-bottom: 0px; float:right">
            <button class="btn" type="button" onclick="reset(1, 2, ${param.stdlist_no});">전체보기</button>
            <div class="btn-group">
              <select>
                <option>키워드</option>
              </select> 
            </div>
            <input style="margin-bottom: 0px; width: 30%; font-size: 12px;" type="text" id="pdsword1" name="pdsword1" placeholder="검색어 입력" required="required">
            <button class="btn" type="button" onclick="set_nowpage_cookie(2, ${param.stdlist_no}, 1)">검색</button>
            <button class="btn" onclick="location.href='./create.do?stdlist_no=${param.stdlist_no}'"><img src='/study/user/my_pds/images/add_content.png'>등록</button>
          </div>
        </ASIDE> 
        <DIV id="table_space_1">
        </DIV>
      </div>
      <!-- ======공지사항 탭 종료 ====== -->
      
      <!-- ====== 자유게시판 탭 시작 =====-->
      <div class="tab-pane" id="tab3">
        <ASIDE style='margin-bottom: 10px; width: 100%; float: right; text-align: center;'>
          <DIV style="float: left">
            <span id='search2' style="font-size: 15px; font-weight:bolder; display: none">[검색결과]<span id='search_count2'>0</span>건 조회됨</span>
          </DIV> 
          <div class="input-append" style="margin-bottom: 0px; float:right;">
            <button class="btn" type="button" onclick="reset(2, 3, ${param.stdlist_no});">전체보기</button>
            <div class="btn-group">
              <select>
                <option>키워드</option>
                <!-- <option>제목</option>
                <option>작성자</option> --> 
              </select>  
            </div>  
            
            <input style="margin-bottom: 0px; width: 30%;" type="text" id="pdsword2" name="pdsword2" placeholder="검색어 입력" required="required">
            <button class="btn" type="button" onclick="notice(3,${param.stdlist_no} )">검색</button>
            <button class="btn" onclick="location.href='./create.do?stdlist_no=${param.stdlist_no}&cateno=${cookie.cateno.value }'"><img src='/study/user/my_pds/images/add_content.png'>등록</button>
          </div> 
        </ASIDE>
        <DIV id="table_space_2"> 
        </DIV>
      </div>
      <!-- ====== 자유게시판 탭 종료 =====-->
      
      <!-- ====== 자료실 탭 시작 ===== --> 
      <div class="tab-pane" id="tab4">
        <ASIDE style='margin-bottom: 10px; width: 100%; float: right; text-align: center;'>
          <DIV style="float: left">
            <span id='search3' style="font-size: 15px; font-weight:bolder; display: none">[검색결과]<span id='search_count3'>0</span>건 조회됨</span>
          </DIV>
          <div class="input-append" style="margin-bottom: 0px; float:right;">
            <button class="btn" type="button" onclick="reset(3, 4, ${param.stdlist_no});">전체보기</button>
            <div class="btn-group">
              <select>
                <option>키워드</option>
                <option>제목</option>
                <option>작성자</option>
              </select> 
            </div>
            <input style="margin-bottom: 0px; width: 30%;" type="text" id="pdsword3" name="pdsword3" placeholder="검색어 입력" required="required">
            <button class="btn" type="button" onclick="notice(4,${param.stdlist_no} )">검색</button>
            <button class="btn" onclick="location.href='./create.do?stdlist_no=${param.stdlist_no}&cateno=${cookie.cateno.value }'"><img src='/study/user/my_pds/images/add_content.png'>등록</button>
          </div>
        </ASIDE>
        <DIV id="table_space_3">
        </DIV> 
      </div>
      <!-- ====== 자료실 탭 종료 ===== -->
    </div>
  </div>
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>