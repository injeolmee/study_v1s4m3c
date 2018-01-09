
$(document).ready(function(){
  
  $("tbody > tr").mouseover(function(){
    $("td", this).css("background-color", "rgb(222, 213, 213)");
  });
   
  $("tbody > tr").mouseout(function(){
    $("td", this).css("background-color", "#ffffff");
  });
  
  $('#all_recv_check').click(function(){
    if($('#all_recv_check').prop("checked")){
      $("input:checkbox[id=recv_check]").prop("checked",true);
    }else{
      $("input:checkbox[id=recv_check]").prop("checked",false);
    }
  });
  
  $('#all_send_check').click(function(){
    if($('#all_send_check').prop("checked")){
      $("input:checkbox[id=send_check]").prop("checked",true);
    }else{
      $("input:checkbox[id=send_check]").prop("checked",false);
    }
  });
  
  $('#all_repo_check').click(function(){
    if($('#all_repo_check').prop("checked")){
      $("input:checkbox[id=repo_check]").prop("checked",true);
    }else{
      $("input:checkbox[id=repo_check]").prop("checked",false);
    } 
  });
  
  $('#all_self_check').click(function(){
    if($('#all_self_check').prop("checked")){ 
      $("input:checkbox[id=self_check]").prop("checked",true);
    }else{
      $("input:checkbox[id=self_check]").prop("checked",false);
    }
  });
  
  /* 좌측 사이드 메뉴 백그라운드 컬러 효과. */
  $('.msg_menu').click(function(){
    $(this).siblings().removeClass('active');
    $(this).addClass('active'); 
  });
  
  msg_recv_list(1);
  
  /* 1분 마다 리스트 reload */
  /*setInterval(function(){
    msg_recv_list(1)
  }, 60*1000); */
  
  $('input:text[id=memid]').keydown(function(){ 
    alert('!!!');
  });
  
});

$(window).bind("popstate", function(event){
  var data=event.originalEvent.state;
  
  if(data){
    var splitter = $("#layout-vertical").data("kendoSplitter");
    splitter.ajaxRequest("#article-pane",data); 
  }
});

/* 버튼 드롭다운 */
function dropdown(msg_no, event){
  var dropdown_menu=$('#dropdown_menu'+msg_no);
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

  // 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치
  if( divLeft < 0 ) divLeft = 0;
  if( divTop < 0 ) divTop = 0;
   
  dropdown_menu.css("position","fixed"); 
  dropdown_menu.css("display", "");
  dropdown_menu.css("left", divLeft);  // x위치 + 80px (우로 이동)
  dropdown_menu.css("top", divTop);  // y위치 - 200px(위로 이동)
}
  

/*
 * 검색 선 처리 구간
 * index=0 -> 받은 쪽지 리스트
 * index=1 -> 보낸 쪽지 리스트
 * index=2 -> 쪽지 보관함
 * index=3 -> 내게 쓴 쪽지 리스트 (예정)
 */ 
function search_control(){
  
  var search_area=$('#search_area').val();
  var search_condition=$("#search_condition").val();  // 선택한 조건
  var msgword=$("#msgword").val();                   //검색창에 입력된 검색어
  
  if(search_area=='none'){ 
    alert("검색영역을 선택하세요.");
    return false; 
  }
  
  if(search_condition=='none'){
    alert("검색조건을 선택하세요.");
    return false;
  }
  
  if(msgword==''){
    alert("검색어를 입력하세요.");
    return false; 
  }
  
  $('.msg_menu').siblings().removeClass('active'); // 사이드 메뉴 active 모두 해제
  
  if(search_area==0){
    msg_recv_list(1);
    $('#msg_recv_title_li').addClass('active');  // 받은 쪽지함 active 적용
  }else if(search_area==1){
    msg_send_list(1);
    $('#msg_send_title_li').addClass('active');  // 보낸 쪽지함 active 적용
  }else if(search_area==2){
    msg_repo_list(1);
    $('#msg_repo_title_li').addClass('active');  // 쪽지보관함 active 적용
  }else{
    msg_self_list(1);
    $('#msg_self_title_li').addClass('active');  // 쪽지보관함 active 적용
  }
}

/**
 * 검색 조건 초기화.
 * index=0 -> 받은 쪽지 리스트
 * index=1 -> 보낸 쪽지 리스트
 * index=2 -> 쪽지 보관함 리스트 
 * index=3 -> 내게 쓴 쪽지 리스트 (예정)
 */
function search_clear(index){ 
  $('#search_area option:eq(0)').prop("selected", true);      // 첫번째 옵션으로
  $('#search_condition option:eq(0)').prop("selected", true);   // 첫번째 옵션으로
  $('#msgword').val('');                                        // 검색창 clear
  
  console.log($("#recv_list_tbody").text());
  
  if(index==0){
    msg_recv_list(1);
  }else if(index==1){
    msg_send_list(1);
  }else if(index==2){
    msg_repo_list(1);
  }else{
    msg_self_list(1);
  }
}

/**
 * 받은 쪽지 리스트
 * memberno : 회원의 번호 (= 받는 사람의 번호) 
 * data[0] --> list_info,  data[1] --> count_info
 */
function msg_recv_list(nowpage){
  var search_condition=$("#search_condition").val();  // 선택한 조건
  var msgword=$("#msgword").val();                   //검색창에 입력된 검색어
   
  history.pushState("msg_recv_list.do", null, "./message_home.do#test"); 
  
  $.ajax({ 
    url: "./msg_recv_list.do",
    type: "GET",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: "search_condition="+search_condition+"&msgword="+msgword+"&nowpage="+nowpage,
    success: function(data){
      var add_tr=""; // tbody에 끼워넣을 tr 정보들.
      
      if(search_condition=="search_id"){
        real_condition="아이디";
      }else if(search_condition=="msg_title"){
        real_condition="제목"; 
      }else{
        real_condition="내용"; 
      }
      
      if(search_condition!='none'){  
        $('#category').html("<img src='./images/message.png'>받은쪽지함▶검색결과("+data[1].recv_search_count+"개)&nbsp;<span style='font-size:12px;'>검색조건:"+real_condition+"</span>&nbsp;<span style='font-size:12px;'>검색어:"+msgword+"</span>");
      }else{
        $('#category').html("<img src='./images/message.png'>받은쪽지함");  
      }
      
      $('#read_msg').css("display","none");
      $('#msg_recv_list').css("display","");          // 받은 쪽지함 테이블 출력.
      $('#msg_send_list').css("display","none");      // 보낸 쪽지함 테이블 감추기.
      $('#msg_repo_list').css("display","none");      // 쪽지 보관함 테이블 감추기.
      $('#msg_self_list').css("display","none");
      
      $('#send_list_tbody').html("");
      $('#repo_list_tbody').html("");
      $('#self_list_tbody').html("");
       
      $('#msg_recv_title').html("받은쪽지함("+data[1].recv_all_count+")");
      $('#msg_send_title').html("보낸쪽지함("+data[1].send_all_count+")");
      $('#msg_repo_title').html("쪽지보관함("+data[1].repo_all_count+")");  // 쪽지 보관함 갯수
      $('#msg_self_title').html("내게쓴 쪽지함("+data[1].self_all_count+")");
      
      var status="";
      if(data[0].list_info.length>0){  // 메세지 전송 성공 
        for(var i=0; i<data[0].list_info.length; i++){ 
          if(data[0].list_info[i].msg_confirm=="N"){
            status='<img src="./images/before_read.png">';
          }else{ 
            status='<img src="./images/after_read.png">';
          }
          add_tr+='<tr id="msg_info">'; 
          add_tr+='<td><input type="checkbox" id="recv_check" value='+data[0].list_info[i].msg_no+'></td>';
          add_tr+='<td id="status">'+status+'<button style="margin-left: 20px;" class="btn btn-xs" onclick="msg_create(\''+data[0].list_info[i].memid+'\')">답장</button></td>';
          add_tr+='<td style="text-align: center;">';
          add_tr+='  <div id="dropdown'+data[0].list_info[i].msg_no+'" class="dropdown">';
          add_tr+='    <button onclick="dropdown('+data[0].list_info[i].msg_no+', event);" class="btn btn-link" type="button" data-toggle="dropdown">'+data[0].list_info[i].memid+'('+data[0].list_info[i].memname+')<span class="caret"></span></button>';
          add_tr+='    <ul id="dropdown_menu'+data[0].list_info[i].msg_no+'" class="dropdown-menu">';
          add_tr+='      <li style="text-align:left;"><a onclick="javascript:meminfo();"><img src="/study/my_pds/images/mem_info.png">회원정보</a></li>';
          add_tr+='      <li style="text-align:left;"><a onclick="javascript:msg_create(\''+data[0].list_info[i].memid+'\');"><img src="/study/my_pds/images/message_add.png">쪽지보내기</a></li>';
          add_tr+='      <li style="text-align:left;"><a onclick="javascript:wait();">그외 기능</a></li>';
          add_tr+='    </ul>';
          add_tr+='  </div>';  
          add_tr+='</td>';    
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 0)'><a>"+data[0].list_info[i].msg_title+"</a></td>";
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 0)'><a>"+data[0].list_info[i].msg_content+"</a></td>"; 
          add_tr+="<td style='text-align: center;'>"+data[0].list_info[i].msg_date+"</td>";
          add_tr+="</tr>";
        }
        
        $('#all_recv_check').prop("checked", false);
        $('#paging').css("display","");
        $('#paging').html(data[2].paging);  
        $("#read_msg").css('display', 'none');          // 읽기 DIV 감추기.
        $('#msg_none').html("");                        // 쪽지 리스트 없을시 출력되는 DIV
        $('#recv_list_tbody').html(add_tr);             // 끼워넣기
      }else{ // 메세지 전송 실패
        $('#paging').html(""); 
        $('#recv_list_tbody').html("");
        $('#msg_none').html("<img src='./images/none_recv_msg.jpg' style='width:50%;'>");
      }
    },
    error: function (request, status, error){
      alert("실패");
    }
  });
}

function meminfo() {
  window.open("/study/user/member/mem_read_info.do?memberno=3", "a", "resizable, width=450, height=500, left=500, top=100");
}

/**
 * 보낸 쪽지함 리스트
 */
function msg_send_list(nowpage){
  var search_condition=$("#search_condition").val();  // 선택한 조건
  var msgword=$("#msgword").val();                   //검색창에 입력된 검색어
  
  history.pushState("msg_send_list.do", null, "./message_home.do#test2");
  $.ajax({  
    url: "./msg_send_list.do",
    type: "GET",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    data: "search_condition="+search_condition+"&msgword="+msgword+"&nowpage="+nowpage,
    success: function(data){
      var add_tr=""; // tbody에 끼워넣을 tr 정보들. 
      var status=""; // 상태 타이틀
      var real_condition=""; 
      
      if(search_condition=="search_id"){
        real_condition="아이디";
      }else if(search_condition=="msg_title"){
        real_condition="제목"; 
      }else{
        real_condition="내용"; 
      }
      
      if(search_condition!='none'){
        $('#category').html("<img src='./images/msg_send.png'>보낸쪽지함▶검색결과("+data[1].send_search_count+"개)&nbsp;<span style='font-size:12px;'>검색조건:"+real_condition+"</span>&nbsp;<span style='font-size:12px;'>검색어:"+msgword+"</span>");
      }else{
        $('#category').html("<img src='./images/msg_send.png'>보낸쪽지함");
      }
      
      $('#read_msg').css("display","none");           // 쪽지 읽기 영역 감추기
      $('#msg_recv_list').css("display","none");      // 받은 쪽지함 테이블 감추기
      $('#msg_send_list').css("display","");          // 보낸 쪽지함 테이블 출력
      $('#msg_repo_list').css("display","none");          // 쪽지 보관함 테이블 감추기
      $('#msg_self_list').css("display","none");
      
      $('#recv_list_tbody').html("");
      $('#repo_list_tbody').html("");
      $('#self_list_tbody').html("");
      
      $('#msg_recv_title').html("받은쪽지함("+data[1].recv_all_count+")");
      $('#msg_send_title').html("보낸쪽지함("+data[1].send_all_count+")");
      $('#msg_repo_title').html("쪽지보관함("+data[1].repo_all_count+")");  // 쪽지 보관함 갯수
      $('#msg_self_title').html("내게쓴 쪽지함("+data[1].self_all_count+")");
      
      if(data[0].list_info.length>0){  // 보낸 쪽지 있음
        for(var i=0; i<data[0].list_info.length; i++){
          if(data[0].list_info[i].msg_confirm=="N"){
            status='<img src="./images/before_read.png">미확인';
          }else{
            status='<img src="./images/after_read.png">확인';
          }
          add_tr+='<tr id="msg_info">';
          add_tr+='<td><input type="checkbox" id="send_check" value='+data[0].list_info[i].msg_no+'></td>'; 
          add_tr+='  <td id="status" style="text-align: center;">'+status; 
          add_tr+='  <td style="text-align: center;">';
          add_tr+='  <div id="dropdown'+data[0].list_info[i].msg_no+'" class="dropdown">';
          add_tr+='    <button onclick="dropdown('+data[0].list_info[i].msg_no+', event);" class="btn btn-link" type="button" data-toggle="dropdown">'+data[0].list_info[i].memid+'('+data[0].list_info[i].memname+')<span class="caret"></span></button>';
          add_tr+='    <ul id="dropdown_menu'+data[0].list_info[i].msg_no+'" class="dropdown-menu">';
          add_tr+='      <li style="text-align:left;"><a onclick="javascript:meminfo();"><img src="/study/my_pds/images/mem_info.png">회원정보</a></li>';
          add_tr+='      <li style="text-align:left;"><a onclick="javascript:msg_create(\''+data[0].list_info[i].memid+'\');"><img src="/study/my_pds/images/message_add.png">쪽지보내기</a></li>';
          add_tr+='      <li style="text-align:left;"><a onclick="javascript:wait();">그외 기능</a></li>';
          add_tr+='    </ul>';
          add_tr+='  </div>';
          add_tr+='</td>';  
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 1)'><a>"+data[0].list_info[i].msg_title+"</a></td>";
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 1)'><a>"+data[0].list_info[i].msg_content+"</a></td>";
          add_tr+="<td style='text-align: center;'>"+data[0].list_info[i].msg_date+"</td>"; 
          add_tr+="<td style='text-align: center;'>"+data[0].list_info[i].msg_rev_date+"</td>"; 
          add_tr+="</tr>";
        }
        
        $('#all_send_check').prop("checked", false);
        $('#paging').css("display",""); 
        $('#paging').html(data[2].paging);
        $("#read_msg").css('display', 'none'); // 읽기 DIV 감추기.
        $('#msg_none').html("");
        $('#send_list_tbody').html(add_tr);  // 끼워넣기
      }else{ // 보낸 쪽지 없음
        $('#paging').html("");           // 공백
        $('#send_list_tbody').html("");  // 공백
        $('#msg_none').html("<img src='./images/none_send_msg.jpg' style='width:50%;'>");
      }
    },
    error: function (request, status, error){
      alert("실패");
    }
  });
}

/**
 * 쪽지 보관함 리스트 출력
 */
function msg_repo_list(nowpage){
  var search_condition=$("#search_condition").val();  // 선택한 조건
  var msgword=$("#msgword").val();                   //검색창에 입력된 검색어
  
  history.pushState("msg_repo_list.do", null, "./message_home.do#test3");
  $.ajax({  
    url: "./msg_repo_list.do",
    type: "GET",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: "search_condition="+search_condition+"&msgword="+msgword+"&nowpage="+nowpage,
    success: function(data){ 
      var add_tr=""; // tbody에 끼워넣을 tr 정보들. 
      
      if(search_condition=="search_id"){
        real_condition="아이디";
      }else if(search_condition=="msg_title"){
        real_condition="제목"; 
      }else{
        real_condition="내용"; 
      } 
       
      if(search_condition!='none'){  
        $('#category').html("<img src='./images/msg_repo.png'>쪽지보관함▶검색결과("+data[1].repo_search_count+"개)&nbsp;<span style='font-size:12px;'>검색조건:"+real_condition+"</span>&nbsp;<span style='font-size:12px;'>검색어:"+msgword+"</span>");
      }else{ 
        $('#category').html("<img src='./images/msg_repo.png'>쪽지보관함");  
      }
      
      $('#read_msg').css("display","none");           // 쪽지 읽기 영역 감추기
      $('#msg_send_list').css("display","none");      // 보낸 쪽지함 테이블 감추기.
      $('#msg_recv_list').css("display","none");      // 받은 쪽지함 테이블 감추기.
      $('#msg_repo_list').css("display","");          // 쪽지 보관함 테이블 출력
      $('#msg_self_list').css("display","none");
      
      $('#recv_list_tbody').html("");
      $('#send_list_tbody').html("");  
      $('#self_list_tbody').html("");
      
      $('#msg_recv_title').html("받은쪽지함("+data[1].recv_all_count+")");  // 받은 쪽지함 갯수
      $('#msg_send_title').html("보낸쪽지함("+data[1].send_all_count+")");  // 보낸 쪽지함 갯수
      $('#msg_repo_title').html("쪽지보관함("+data[1].repo_all_count+")");  // 쪽지 보관함 갯수
      $('#msg_self_title').html("내게쓴 쪽지함("+data[1].self_all_count+")");
      
      var status="";
      if(data[0].list_info.length>0){  // 쪽지 데이터 있을 때
        for(var i=0; i<data[0].list_info.length; i++){  
          add_tr+='<tr id="msg_info">'; 
          add_tr+='<td><input type="checkbox" id="repo_check" value='+data[0].list_info[i].msg_no+'></td>';
          // 보낸 사람
          add_tr+='<td style="text-align: center;">'+data[0].list_info[i].sender_id+'<span>('+data[0].list_info[i].sender_name+')</span></td>';
          // 받는사람
          add_tr+='<td style="text-align: center;">'+data[0].list_info[i].receiver_id+'<span>('+data[0].list_info[i].receiver_name+')</span></td>';
          
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 2)'><a>"+data[0].list_info[i].msg_title+"</a></td>";   // 제목
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 2)'><a>"+data[0].list_info[i].msg_content+"</a></td>"; // 내용
          add_tr+="<td style='text-align: center;'>"+data[0].list_info[i].msg_date+"</td>"; // 보낸 날짜 
          add_tr+="<td style='text-align: center;'>"+data[0].list_info[i].msg_rev_date+"</td>"; // 받은 날짜
          add_tr+="</tr>";
        } 
        
        $('#all_repo_check').prop("checked", false);
        $('#paging').css("display",""); 
        $('#paging').html(data[2].paging);
        $("#read_msg").css('display', 'none');          // 읽기 DIV 감추기.
        $('#msg_none').html("");                        // 쪽지 리스트 없을시 출력되는 DIV
        $('#repo_list_tbody').html(add_tr);             // 끼워넣기
      }else{ // 쪽지 데이터 없을 때
        $('#paging').html("");
        $('#repo_list_tbody').html("");
        $('#msg_none').html("<img src='./images/none_recv_msg.jpg' style='width:50%;'>");
      }
    },
    error: function (request, status, error){
      alert("실패");
    }
  });
}

/**
 * 내게쓴 쪽지함 리스트 출력
 * @param nowpage
 */
function msg_self_list(nowpage){
  var search_condition=$("#search_condition").val();  // 선택한 조건
  var msgword=$("#msgword").val();                   //검색창에 입력된 검색어
  
  $.ajax({ 
    url: "./msg_self_list.do",
    type: "GET",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: "search_condition="+search_condition+"&msgword="+msgword+"&nowpage="+nowpage,
    success: function(data){
      var add_tr=""; // tbody에 끼워넣을 tr 정보들.
      
      if(search_condition=="search_id"){
        real_condition="아이디";
      }else if(search_condition=="msg_title"){
        real_condition="제목"; 
      }else{
        real_condition="내용"; 
      }
      
      if(search_condition!='none'){  
        $('#category').html("<img src='./images/message.png'>내게쓴 쪽지함▶검색결과("+data[1].self_search_count+"개)&nbsp;<span style='font-size:12px;'>검색조건:"+real_condition+"</span>&nbsp;<span style='font-size:12px;'>검색어:"+msgword+"</span>");
      }else{
        $('#category').html("<img src='./images/message.png'>내게쓴 쪽지함");
      }
      
      $('#read_msg').css("display","none");
      $('#msg_recv_list').css("display","none");          // 받은 쪽지함 테이블 출력.
      $('#msg_send_list').css("display","none");      // 보낸 쪽지함 테이블 감추기.
      $('#msg_repo_list').css("display","none");      // 쪽지 보관함 테이블 감추기.
      $('#msg_self_list').css("display","");
       
      $('#recv_list_tbody').html("");
      $('#send_list_tbody').html("");
      $('#repo_list_tbody').html("");
      
      $('#msg_recv_title').html("받은쪽지함("+data[1].recv_all_count+")");
      $('#msg_send_title').html("보낸쪽지함("+data[1].send_all_count+")");
      $('#msg_repo_title').html("쪽지보관함("+data[1].repo_all_count+")");  // 쪽지 보관함 갯수
      $('#msg_self_title').html("내게쓴 쪽지함("+data[1].self_all_count+")");
      
      var status="";
      if(data[0].list_info.length>0){  // 메세지 전송 성공 
        for(var i=0; i<data[0].list_info.length; i++){ 
          if(data[0].list_info[i].msg_confirm=="N"){
            status='<img src="./images/before_read.png">';
          }else{ 
            status='<img src="./images/after_read.png">';
          }
          add_tr+='<tr id="msg_info">'; 
          add_tr+='<td><input type="checkbox" id="self_check" value='+data[0].list_info[i].msg_no+'></td>';
          add_tr+='<td id="status">'+status+'</td>'; 
          add_tr+='<td style="text-align: center;">';
          add_tr+='    <span class="text-info" style="font-weight:bolder;">'+data[0].list_info[i].memid+'(나)</span>';
          add_tr+='</td>'; 
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 3)'><a>"+data[0].list_info[i].msg_title+"</a></td>";
          add_tr+="<td style='text-align: center; cursor:pointer;' onclick='read("+data[0].list_info[i].msg_no+", 3)'><a>"+data[0].list_info[i].msg_content+"</a></td>"; 
          add_tr+="<td style='text-align: center;'>"+data[0].list_info[i].msg_date+"</td>";
          add_tr+="</tr>";
        }
        
        $('#all_self_check').prop("checked", false);
        $('#paging').css("display","");
        $('#paging').html(data[2].paging);  
        $("#read_msg").css('display', 'none');          // 읽기 DIV 감추기.
        $('#msg_none').html("");                        // 쪽지 리스트 없을시 출력되는 DIV
        $('#self_list_tbody').html(add_tr);             // 끼워넣기
      }else{ // 메세지 전송 실패
        $('#paging').html("");  
        $('#self_list_tbody').html("");
        $('#msg_none').html("<img src='./images/none_recv_msg.jpg' style='width:50%;'>");
      }
    },
    error: function (request, status, error){
      alert("실패");
    }
  });
}

/**
 * 쪽지 보내기 폼
 */
function msg_create(memid){
  var modal=$('#modal_common');
  
  var body="";
  if(memid==null){
    $('#modal_title', modal).html("<span>쪽지 보내기</span>");
    body+="<label style='font-size:0.8em;'>받는이</label><br>"; 
    body+="<input type='text' style='margin-bottom:0px;' id='memid' placeholder='받는이 ID'>";
    body+="<button class='btn btn-sm btn-success' onclick='search_target();'>찾기</button><span style='font-size:0.7em;'>(ID검사 후 전송 가능)</span><br>";
  }else{
    $('#modal_title', modal).html("<span>내게 쓰기</span>");
    body+="<label style='font-size:0.8em;'>받는이:</label>";
    body+="<span style='margin-bottom:0px;' id='memid'>"+memid+"</span><br>";
  }
  
  body+="<label style='font-size:0.8em;'>제목</label><br>";
  body+="<input type='text' style='width:98%;' id='msg_title' placeholder='제목입력'><br>"; 
  body+="<label style='font-size:0.8em;'>내용</label>";
  body+="<textarea id='msg_content' style='resize:none; font-size:0.7em; width:98%; height:200px;' placeholder='내용 입력'>";
  $('#modal-body', modal).html(body); 
  
  var footer="";
  if(memid==null){
    footer+='<button type="button" id="send_btn" class="btn btn-info" onclick="msg_create_proc();" disabled>전송</button>';
  }else{ 
    footer+='<button type="button" id="send_btn" class="btn btn-info" onclick="msg_create_proc(\''+memid+'\');">전송</button>'; 
  }
  footer+='<button type="button" class="btn btn-info" data-dismiss="modal">취소</button>';
  $('#modal-footer', modal).html(footer);
  
  modal.modal();
  $('#memid', modal).focus(); 
}

/**
 * 보낼 회원 찾기(회원의 유무를 판단)
 */
function search_target(send_memid){ 
  var memid=$('#memid').val();   // 받는이 아이디 값 
  $.ajax({ 
    url: "./check_memid.do",
    type: "POST",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json
    data: "memid="+memid,
    success: function(data){
      if(data.result=="success"){  // 메세지 전송 성공
        alert("유효한 ID 입니다."); 
        $('#send_btn').prop('disabled', false);    // 버튼 활성화.
        
      }else{                                     // 메세지 전송 실패
        alert("존재하지 않는 ID 입니다.");
        $('#send_btn').prop('disabled', true);    // 버튼 활성화. 
        $('#memid').val("");                      // ID 입력부 초기화
        $('#memid').focus();                      // ID 입력부 포커스
      } 
    },
    error: function (request, status, error){
      
    }
  });
}


/**
 * 
 * 쪽지 삭제 확인 
 * */   
function remove(index){
  // 체크 된 checkBox들의 값을 구해야 한다. --> ex) check1, check10, check20 ...
  // 1, 10, 20은 msg_no와 같다.
  // JSON.stringify(); ==> JSON을 String화 한다.(직렬화)
  // JSON.parse(); ==> String을 JSON형태로 변환한다.
  // JSON 형태 : {key:"value", key:"value"} 
  var modal=$('#modal_common');
  $('#modal_title', modal).html("<span>쪽지 삭제</span>");
  
  $('#modal-body', modal).html("삭제 합니까?");
  
  var footer=""; 
  footer+='<button type="button" class="btn btn-info" onclick="remove_proc('+index+');" data-dismiss="modal">삭제</button>';
  footer+='<button type="button" class="btn btn-info" data-dismiss="modal">취소</button>';
  $('#modal-footer', modal).html(footer); 
  
  modal.modal(); 
}

/**
 * 쪽지 삭제 처리 
 */
function remove_proc(index){  // index=0 -> 받은 쪽지함
                                // index=1 -> 보낸 쪽지함
                                // index=2 -> 쪽지 보관함
  var arr=new Array();  // Object를 배열로 저장할 Array
  var obj=new Object(); // key, value형태로 저장할 Object  
  
  var url="";
  
  if(index==0){  // 받은 쪽지함에서 체크된 쪽지 삭제.
    for(var i=0; i<$('input:checkbox[id=recv_check]').length; i++){
      /* console.log($('input:checkbox')[i]); */
      if($('input:checkbox[id=recv_check]:eq('+i+')').prop("checked")==true){
        obj=new Object(); 
        obj.value=$('input:checkbox[id=recv_check]:eq('+i+')').val(); 
        arr.push($('input:checkbox[id=recv_check]:eq('+i+')').val());
      }
    }
    url="./msg_recv_remove.do";
  }else if(index==1){  // 보낸 쪽지함에서 체크된 쪽지 삭제.
    for(var i=0; i<$('input:checkbox[id=send_check]').length; i++){
      /* console.log($('input:checkbox')[i]); */
      if($('input:checkbox[id=send_check]:eq('+i+')').prop("checked")==true){
        obj=new Object(); 
        obj.value=$('input:checkbox[id=send_check]:eq('+i+')').val(); 
        arr.push($('input:checkbox[id=send_check]:eq('+i+')').val());
      } 
    }
    url="./msg_send_remove.do";
  }else if(index==2){
    for(var i=0; i<$('input:checkbox[id=repo_check]').length; i++){
      /* console.log($('input:checkbox')[i]); */
      if($('input:checkbox[id=repo_check]:eq('+i+')').prop("checked")==true){
        obj=new Object(); 
        obj.value=$('input:checkbox[id=repo_check]:eq('+i+')').val(); 
        arr.push($('input:checkbox[id=repo_check]:eq('+i+')').val());
      } 
    }
    url="./msg_repo_remove.do";
  }else{
    for(var i=0; i<$('input:checkbox[id=self_check]').length; i++){
      /* console.log($('input:checkbox')[i]); */
      if($('input:checkbox[id=self_check]:eq('+i+')').prop("checked")==true){
        obj=new Object();
        obj.value=$('input:checkbox[id=self_check]:eq('+i+')').val(); 
        arr.push($('input:checkbox[id=self_check]:eq('+i+')').val());
      }
    } 
    url="./msg_self_remove.do";
  }
   
  /* 선택한 쪽지가 없을 경우 */
  if(arr.length==0){
    alert("삭제하고자하는 쪽지를 선택하세요.");  
    return false;
  }
  var nowpage=$('#nowpage_value').attr("data-nowpage");
  
  /* 정상 처리 시작 */
  jQuery.ajaxSettings.traditional = true;
  $.ajax({ 
    url: url,
    type: "POST",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    data: {checked:arr},  
    success: function(data){
      if(data.result=="ok"){   // 삭제 완료
        var after_count=data.after_count; // 삭제 처리 후 데이터 값
        
        if(after_count%5==0){
          nowpage=nowpage-1;
        }
        
        if(index==0){
          msg_recv_list(nowpage);    // 받은 쪽지 리스트 로드
        }else if(index==1){
          msg_send_list(nowpage);    // 보낸 쪽지 리스트 로드 
        }else if(index==2){
          msg_repo_list(nowpage);   
        }else{
          msg_self_list(nowpage);
        }
        alert("쪽지가 삭제되었습니다.");
      }else{
        alert("삭제 처리 실패!");
      }
    },
    error: function (request, status, error){
      alert("AJAX 실패");
    }
  });
}

/**
 * 쪽지 보관함 이동
 */
function move_repo(index){
  var modal=$('#modal_common');
  $('#modal_title', modal).html("<span>쪽지 보관함</span>");
  
  $('#modal-body', modal).html("보관 합니까?");
  
  var footer="";
  footer+='<button type="button" class="btn btn-info" onclick="move_repo_proc('+index+');" data-dismiss="modal">보관하기</button>';
  footer+='<button type="button" class="btn btn-info" data-dismiss="modal">취소</button>';
  $('#modal-footer', modal).html(footer); 
  
  modal.modal();
}

/**
 * 쪽지 보관 처리
 * index=1 : 받은 쪽지함에서
 * index=2 : 보낸 쪽지함에서
 */
function move_repo_proc(index){
  var arr=new Array();  // Object를 배열로 저장할 Array
  var obj=new Object(); // key, value형태로 저장할 Object  
  
  if(index==0){  // 받은 쪽지함에서 체크된 쪽지 삭제.
    for(var i=0; i<$('input:checkbox[id=recv_check]').length; i++){
      /* console.log($('input:checkbox')[i]); */
      if($('input:checkbox[id=recv_check]:eq('+i+')').prop("checked")==true){
        obj=new Object(); 
        obj.value=$('input:checkbox[id=recv_check]:eq('+i+')').val(); 
        arr.push($('input:checkbox[id=recv_check]:eq('+i+')').val());
      }
    } 
  }else if(index==1){  // 보낸 쪽지함에서 체크된 쪽지 삭제.
    for(var i=0; i<$('input:checkbox[id=send_check]').length; i++){
      /* console.log($('input:checkbox')[i]); */
      if($('input:checkbox[id=send_check]:eq('+i+')').prop("checked")==true){
        obj=new Object(); 
        obj.value=$('input:checkbox[id=send_check]:eq('+i+')').val(); 
        arr.push($('input:checkbox[id=send_check]:eq('+i+')').val());
      } 
    }
  }
  
  /* 선택한 쪽지가 없을 경우 */
  if(arr.length==0){
    alert("보관하고자하는 쪽지를 선택하세요."); 
    return false;
  }
  
  /* 정상 처리 시작 */
  jQuery.ajaxSettings.traditional = true;
  $.ajax({ 
    url: "./move_repo.do",
    type: "POST",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    data: {checked:arr, index:index},  
    success: function(data){
      
      if(data.result=="ok"){   // 보관 완료
        if(index==0){
          msg_recv_list(1);    // 받은 쪽지 리스트 로드
        }else{
          msg_send_list(1);    // 보낸 쪽지 리스트 로드 
        }
        alert("쪽지가 보관되었습니다.");
      }else{
        alert("보관 처리 실패!");
      }
    },
    error: function (request, status, error){
      alert("보관 AJAX 실패");
    }
  });
}

/**
 * 쪽지 읽기
 * index값으로 보낸쪽지함 읽기인지, 받은 쪽지함 읽기인지 구분.
 * 구분에 따라 수신여부의 Proc 동작여부 결정.
 * 하나의 메소드로 두 동작을 처리하기 위해 index를 사용함. 
 */
function read(msg_no, index){
  $.ajax({ 
    url: "./read.do",
    type: "POST",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    data: "msg_no="+msg_no+"&index="+index,
    success: function(data){
      $('#paging').css("display","none"); // 페이징 감추기
      $('#msg_recv_list').css("display","none");  // 받은 쪽지함 테이블 감추기 
      $('#msg_send_list').css("display","none");  // 보낸 쪽지함 테이블 감추기
      $('#msg_repo_list').css("display","none");  // 쪽지 보관함 테이블 감추기
      $('#msg_self_list').css("display","none");  // 내게쓴 보관함 테이블 감추기
       
      var read_msg="";       // 읽기 폼
      if(data.length!=""){   // 선택한 쪽지의 정보가 있을 때.
        console.log(data);
        
        // alert(data.msg_content);
        /*
        var msg_content_str = data.msg_content;
        msg_content_str = msg_content_str.replace(/<br>/g, '&lt;BR&gt;');
        alert(msg_content_str);
        $('#msg_content').text(msg_content_str);
        */
         
        // textarea에 입력된 엔터(\n)을 <br>태그로 변환한다.
        // 애초에 DB에 저장되어질 때 \n으로 저장됨. 변환되어 저장된게 아님.
        // 그러므로 Proc에서도 별다른 Convert가 필요없고 Javascript에서 변환을 한다.
        // 특수문자같은 경우 쪽지를 입력할 때 URI 엔코딩을 통해 변환하여 저장한다.
        /*alert(data.msg_content);*/
         
        data.msg_content=data.msg_content.replace(/\n/g, "<br />");
        
        /*
         * 읽기 동작 후 전 페이지로 돌아가기 위하 방법이 필요함.
         */ 
        var nowpage=$('#nowpage_value').attr("data-nowpage");
        
        if(index==0){ // 받은 쪽지 함 읽기
          $('#category').html("<img src='./images/after_read.png'>받은쪽지함 <img src='./images/next.png'> 읽기");
          read_msg+="<button onclick='msg_recv_list("+nowpage+")' class='btn btn-xs btn-info'>받은쪽지 목록</button>";
          read_msg+='<button style="margin-left:5px;" onclick="msg_create(\''+data.sender_id+'\')" class="btn btn-xs btn-success"><img src="./images/msg_reply.png">답장</button>';
        }else if(index==1){       // 보낸 쪽지 함 읽기  
          $('#category').html("<img src='./images/msg_send.png'>보낸쪽지함 <img src='./images/next.png'> 읽기");
          read_msg+="<button style='margin-left:5px;' onclick='msg_send_list("+nowpage+")' class='btn btn-xs btn-info'>보낸쪽지 목록</button>";
        }else if(index==2){  // 쪽지 보관함 읽기 상태
          $('#category').html("<img src='./images/msg_repo.png'>쪽지보관함 <img src='./images/next.png'> 읽기");
          read_msg+="<button style='margin-left:5px;' onclick='msg_repo_list("+nowpage+")' class='btn btn-xs btn-info'>쪽지보관함 목록</button>";
        }else{
          $('#category').html("<img src='./images/self_msg.png'>내게쓴 쪽지함 <img src='./images/next.png'> 읽기");
          read_msg+="<button style='margin-left:5px;' onclick='msg_self_list("+nowpage+")' class='btn btn-xs btn-info'>내게쓴 쪽지함 목록</button>";
        } 
        
        read_msg+="<ul>"; 
        if(index==2){  
          read_msg+="  <li class='li_msg'><label class='label_msg'>보낸사람</label><span style='margin-right:20px;'> [나]("+data.sender_name+")</span>";
        }else{
          read_msg+="  <li class='li_msg'><label class='label_msg'>보낸사람</label><span style='margin-right:20px;'>"+data.sender_id+"("+data.sender_name+")</span>"; 
        }
        
        read_msg+="  <label class='label_msg'>받는사람</label><span>"+data.receiver_id+"("+data.receiver_name+")</span></li>";
        read_msg+="  <li class='li_msg'><label class='label_msg'>받은시간</label><span>"+data.msg_date+"</span></li>";
        read_msg+="  <li class='li_msg'><label class='label_msg'>제목</label><span>"+data.msg_title+"</span></li>";
        read_msg+="  <li class='li_none'><DIV id='msg_content'>"+data.msg_content+"</DIV></li>";
        read_msg+="</ul>";
         
        $("#read_msg").html(read_msg);       // 읽기 폼 추가
        $("#read_msg").css('display', '');   // 읽기 폼 출력
      }else{
        alert("읽기 실패!");
      }
    },
    error: function (request, status, error){
      alert("AJAX 실패");
    }
  });
}
