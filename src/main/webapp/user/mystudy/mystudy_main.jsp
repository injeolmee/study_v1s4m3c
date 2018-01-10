<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<% 
String root = request.getContextPath(); // 절대 경로 지정
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>

<link href="./css/my_study_style.css" rel='Stylesheet' type='text/css'>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/javascript" src="../../js/jquery.cookie.js"></script>

<script type="text/javascript">
$(document).ready(function(){ 
  
  $("tbody > tr").mouseover(function(){
    $("td", this).css("background-color", "rgb(222, 213, 213)");
    $("td", this).css("cursor", "pointer");
  });
  
  $("tbody > tr").mouseout(function(){
    $("td", this).css("background-color", "#ffffff");
  });
  
  $('#all_recv_check').click(function(){
    if($('#all_recv_check').prop("checked")){
      $("input:checkbox").prop("checked",true);
    }else{
      $("input:checkbox").prop("checked",false);
    }
  });
  
  /* 쿠키를 select 할 때는 #을 사용한다.
  아이디를 이용해 태그를 검색하는 것과 동일하다.
  
  값을 대입할 때도 val() 함수를 사용한다. (동일함)
  */
  
  $.cookie('pdsword1', '', {path:'/'}); // Cookie 생성
  $.cookie('pdsword2', '', {path:'/'}); // Cookie 생성
  $.cookie('pdsword3', '', {path:'/'}); // Cookie 생성
  
  $.cookie('nowpage', 1, {path:'/'}); // 현재 페이지 저장 cookie
  $.cookie('nowpage2', 1, {path:'/'}); // 현재 페이지 저장 cookie
  $.cookie('nowpage3', 1, {path:'/'}); // 현재 페이지 저장 cookie
  
  $.cookie('cateno', 2, {path:'/'});  // 현재 cateno 저장. defualt:2
   
});

function load(stdlist_no){ 
  location.href="/study/user/mystudy/mystudy_space.do?stdlist_no="+stdlist_no;
}

/** 
 * 현재 내가 신청한 스터디 정보와 상태
 */
function my_apply_list(){
  $.ajax({
    url: "./my_apply_list.ajax",  
    type: "GET",   
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    success: function(data){
      console.log(data);
      var str="";
      var modal=$("#my_apply_list");
      if(data.result.length>0){
        $("#no_data").css("display","none");
        for(var i=0; i<data.result.length; i++){
          str+="<tr>";
          str+="  <td><input type='checkbox' data-applyno="+data.result[i].recruitno+"></td>";
          str+="  <td style='text-align: center; font-size: 12px;'>"+data.result[i].stdlist_title+"</td>";
          str+="  <td style='text-align: center; font-size: 12px;'>"+data.result[i].stdlist_topic+"</td>";
          str+="  <td style='text-align: center; font-size: 12px;'>"+data.result[i].stdlist_area+"</td>";
          str+="  <td style='text-align: center; font-size: 12px;'>"+data.result[i].stdlist_dow+"</td>";
          str+="  <td style='text-align: center; font-size: 12px;'>"+data.result[i].stdlist_curr_num+"</td>";
          str+="  <td style='text-align: center; font-size: 12px;'>"+data.result[i].stdlist_tot_num+"</td>";
          str+="  <td style='text-align: center; font-size: 12px;'>"+data.result[i].confirm+"</td>";
          str+="</tr>";
        }
      }else{
        $("#data_exist").css("display", "none");
        $("#no_data").css("display","");
      }
      
      $("#my_apply_list_tbody", modal).html(str);
      modal.modal();
    },
    error: function (request, status, error){
    
    }
  });
}

// 참여 신청 취소 하기
function cancel_apply(){
  
  var arr=new Array();  // Object를 배열로 저장할 Array
  console.log($('input:checkbox').length);
  
  // 전체 checkbox에서 선택된 항목의 data-applyno를 arr에 push한다. 
  for(var i=1; i<$('input:checkbox').length; i++){
    /* console.log($('input:checkbox')[i]); */
    if($('input:checkbox:eq('+i+')').prop("checked")==true){ 
      arr.push($('input:checkbox:eq('+i+')').attr("data-applyno"));
    }
  }
  
  // 아무것도 선택 안했을 때.
  if(arr.length==0){
    alert("삭제하고자 하는 신청 내역을 선택하세요.");
    return false; 
  }
  
  jQuery.ajaxSettings.traditional = true;   // 이 설정이 없으면 recuritno[] : 1 의 형태로 전송이 된다.
  $.ajax({ 
    url: "./cancel_apply.ajax",
    type: "POST",      
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    data: {recuritno:arr},
    success: function(data){
      if(data.result=="OK"){   // 삭제 완료
        my_apply_list();
        alert("삭제 처리 성공!!");
      }else{
        alert("삭제 처리 실패!");
      }
    },
    error: function (request, status, error){
      alert("AJAX 실패");
    }
  });
}

</script>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class="content">
  <DIV style="text-align: center;">
    <h3 style="font-weight: bolder; text-align: right;"><img src='./images/my_study.png'>내가 참여 중인 스터디 현황</h3>
    <hr style="color: #000000; border: solid 2px #000000; clear: both;"> 
  </DIV> 
  <!-- 내가 참여중인 스터디 내역 -->
  <DIV class="well well-xs" style="border: solid 1px #000000; margin-top: 1%; height: 50%;">
    <DIV>
      <button style="float: right; margin-bottom: 10px;" type="button" class="btn btn-info btn-xs" onclick="my_apply_list();">내 신청 현황</button>
    </DIV> 
    <c:set var="mystudylist" value="${mystudylist}"/> 
    <c:choose>
      <c:when test="${fn:length(mystudylist) == 0}"> 
        <h1>참여중인 스터디가 없습니다.</h1>
        <button onclick="location.href='/study/studylist/create.do'">스터디 참여하러 가기</button>
      </c:when>
      <c:when test="${fn:length(mystudylist) != 0}">
        <TABLE class='table table-striped' style="margin-top: 1%;">
          <colgroup>
            <col style='width: 20%;'/>
            <col style='width: 10%;'/>
            <col style='width: 20%;'/> 
            <col style='width: 10%;'/> 
            <col style='width: 10%;'/>
            <col style='width: 10%;'/>
            <col style='width: 20%;'/> 
          </colgroup> 
         
          <thead> 
            <TR style="background-color: #2d2d30;"> 
              <TH style='text-align: center; color: #fda21a;'>스터디 이름</TH>
              <TH style='text-align: center; color: #fda21a;'>주제</TH>
              <TH style='text-align: center; color: #fda21a;'>활동지역</TH>
              <TH style='text-align: center; color: #fda21a;'>모임일</TH>
              <TH style='text-align: center; color: #fda21a;'>모임시간</TH>
              <TH style='text-align: center; color: #fda21a;'>팀장명</TH>
              <TH style='text-align: center; color: #fda21a;'>참여한 날짜</TH>
            </TR>
          </thead>
          
          <!-- 컨트롤러에서 mav객체에 list를 추가하지 않아도 여기서 에러 발견 x -->
          <tbody id="meminfo_tbody">
            <c:forEach var="mystudylist" items="${mystudylist}">
            <tr id="info" onclick="load('${mystudylist.stdlist_no}')">
              <td style='text-align: center;'>${mystudylist.stdlist_title}</td>
              <td style='text-align: center;'>${mystudylist.stdlist_topic}</td>
              <td style='text-align: center;'>${mystudylist.stdlist_area }</td>
              <td style='text-align: center;'>${mystudylist.stdlist_time}</td>
              <td style='text-align: center;'>${mystudylist.stdlist_dow}</td>
              <td style='text-align: center;'>홍길동</td>
              <td style='text-align: center;'>${mystudylist.stdlist_date}</td>
            </tr>
            </c:forEach>
          </tbody>
        </TABLE>
      </c:when>
    </c:choose>
  </DIV>
  
  <!-- 내 신청 현황 모달창 --> 
  <div class="modal fade" id="my_apply_list" role="dialog" style="display: none; left:40%; width: 60%;">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">내 스터디 신청 현황</h4>
      </div>
      
      <div id="my_apply_list_body" class="modal-body">
        <DIV id='data_exist'>
          <!-- 신청 리스트 삭제 동작 -->
          <DIV>
            <button class='btn btn-danger btn-xs' onclick="cancel_apply();">신청 취소하기</button>
          </DIV> 
          
          <TABLE class='table table-striped' style="margin-top: 1%;">
            <colgroup>
              <col style='width: 3%;'/>
              <col style='width: 20%;'/>
              <col style='width: 10%;'/>
              <col style='width: 20%;'/> 
              <col style='width: 10%;'/>
              <col style='width: 12.5%;'/> 
              <col style='width: 12.5%;'/>
              <col style='width: 15%;'/>
            </colgroup>
           
            <thead>
              <TR style="background-color: #2d2d30;"> 
                <TH><input type="checkbox" id='all_recv_check'></TH>
                <TH style='text-align: center ; font-size: 14px; color: #fda21a;'>스터디 이름</TH>
                <TH style='text-align: center ; font-size: 14px; color: #fda21a;'>주제</TH>
                <TH style='text-align: center ; font-size: 14px; color: #fda21a;'>활동지역</TH>
                <TH style='text-align: center ; font-size: 14px; color: #fda21a;'>모임일</TH> 
                <TH style='text-align: center ; font-size: 14px; color: #fda21a;'>현재인원</TH>
                <TH style='text-align: center ; font-size: 14px; color: #fda21a;'>총 인원</TH>
                <TH style='text-align: center ; font-size: 14px; color: #fda21a;'>승낙여부</TH>
              </TR>
            </thead>
            <!-- AJAX를 통해서 데이터 삽입 -->
            <tbody id="my_apply_list_tbody"></tbody>
          </TABLE>
        </DIV> 
        
        <DIV id='no_data'>
          <span>신청한 스터디가 없습니다.</span>
        </DIV> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div> 
  
  <DIV style="text-align: center;">
    <DIV class="well well-xs" style="border: solid 1px #000000; margin-top: 1%; width: 45%; height:200px; display:inline-block;">
      <h1>공모전 요약 정보들</h1>
    </DIV>
    
    <DIV class="well well-xs" style="border: solid 1px #000000; margin-top: 1%; width: 45%; height:200px; display: inline-block;">
      <h1>취업정보 요약 정보들</h1>
    </DIV>
  </DIV>
  
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>