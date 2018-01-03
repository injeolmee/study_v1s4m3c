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
</head>  

<link href="./css/msg_admin_style.css" rel='Stylesheet' type='text/css'>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 

<script type="text/javascript">
$(document).ready(function(){
  
  var timeStamp=new Date();
  var now=timeStamp.getFullYear()+"-"+(timeStamp.getMonth()+1)+"-"+timeStamp.getDate();  // 오늘 날짜 
   
  $("tbody > tr").mouseover(function(){
    $("td", this).css("background-color", "rgb(222, 213, 213)");
  });
  
  $("tbody > tr").mouseout(function(){
    $("td", this).css("background-color", "#ffffff");
  });
   
  $('#all_check').click(function(){
    if($('#all_check').prop("checked")){
      $("input:checkbox[id=checked]").prop("checked",true);
    }else{ 
      $("input:checkbox[id=checked]").prop("checked",false);
    }
  });
 
  $("#paging_change").change(function(){ 
    var page_num=$("#paging_change").val(); 
    location.replace("./message_admin.do?page_num="+page_num+"&nowPage=1&first_day=${first_day }&second_day=${second_day }&search_condition=${search_condition }&msgword=${msgword }");
  }); 
});

function search_control(){
  var first_day_val=$("#first_day").val();
  var second_day_val=$("#second_day").val();
  
  $("#page_num").val($("#paging_change").val()); 
  
  // 시작 날짜가 없으면 전체기간을 검색한다.
  if(first_day_val == ""){
    $("#second_day").val(now); 
  }
}

function message_del_modal(){
  $("#modal_common").modal();
}
 
function message_del_admin(){
  var arr=new Array();  // Object를 배열로 저장할 Array
  
  for(var i=1; i<$('input:checkbox').length; i++){
    /* console.log($('input:checkbox')[i]); */
    if($('input:checkbox:eq('+i+')').prop("checked")==true){
      arr.push($('input:checkbox:eq('+i+')').attr("data-msg_no"));
    }
  } 
  
//아무것도 선택 안했을 때.
  if(arr.length==0){
    alert("삭제하고자 하는 쪽지를 선택하세요.");
    return false;
  }
  
  jQuery.ajaxSettings.traditional = true;   // 이 설정이 없으면 recuritno[] : 1 의 형태로 전송이 된다.
  $.ajax({ 
    url: "./message_del_admin.ajax",
    type: "POST",
    cache: false,    // 일반적으로 false
    dataType: "json", // or json 
    data: {msg_no:arr, first_day:"${first_day }", second_day:"${second_day }", search_condition:"${search_condition }", msgword:"${msgword }"},
    success: function(data){
      if(data.result=="OK"){   // 삭제 완료
        alert("삭제 처리 성공!!");
        var nowPage=${nowPage };   // EL을 ""안에 명시하면 문자열 형태, ""없이 하면 정수형 
        if(data.after_count % $("#paging_change").val() == 0){
          nowPage=nowPage-1;
          //console.log("페이징 변경 되야함.");
          if(nowPage<=0){
            nowPage=1;
          } 
        }
        //alert("nowPage:"+nowPage); 
        //console.log(nowPage);
        location.replace("./message_admin.do?page_num=${page_num }&nowPage="+nowPage+"&first_day=${first_day }&second_day=${second_day }&search_condition=${search_condition }&msgword=${msgword }");
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
<DIV class='content' style="text-align: center;">

  <!-- 공통 모달창  -->
  <div class="modal fade" id="modal_common" role="dialog" style="display: none;">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h3 class="modal-title text-error" id='modal_title'>[관리자]쪽지 삭제</h3>
      </div>
       
      <div class="modal-body" id="modal-body">
        <span class="text-warning" style="font-size: 0.7em;">*해당 쪽지를 삭제하면 회원의 <span style="color: red; font-weight: bolder;">받은쪽지함/보낸쪽지함</span>에서도 삭제됩니다.</span><br>
      </div> 
      
      <div class="modal-footer" id="modal-footer">
        <button type="button" class="btn btn-info" onclick="message_del_admin();" data-dismiss="modal">삭제</button>
        <button type="button" class="btn btn-info" data-dismiss="modal">취소</button> 
      </div>
    </div> 
  </div>
  <!-- 공통 모달 종료 -->
  
  <DIV style="margin: 0px auto; position: relative; bottom: 0;"> 
    <h5>쪽지함 관리자용</h5>
  </DIV>
   
  <DIV id='tool_bar'>
    <DIV style="float: left;">
      <button class='btn btn-danger btn-xs' onclick="message_del_modal(); ">삭제</button>
      <button class='btn btn-info btn-xs' onclick="location.href='./message_admin.do'">전체보기</button>
      
      <select id="paging_change" style="font-size: 10px; height: 25px;"> 
        <option value="10" <c:if test="${page_num eq '' }">selected</c:if> >모아보기</option> 
        <option value="1" <c:if test="${page_num eq '1' }">selected</c:if> >1개씩 보기(test)</option>
        <option value="5" <c:if test="${page_num eq '5' }">selected</c:if> >5개씩 보기</option>
        <option value="10" <c:if test="${page_num eq '10' }">selected</c:if> >10개씩 보기(기본)</option>  
        <option value="20" <c:if test="${page_num eq '20' }">selected</c:if> >20개씩 보기</option>
        <option value="30" <c:if test="${page_num eq '30' }">selected</c:if> >30개씩 보기</option> 
      </select>
    </DIV>
    
    <DIV style="float: right;">
      <form id='search_msg' name='search_msg' onsubmit="return search_control();" action="./message_admin.do">
        <fieldset style="text-align: right;">
          <input type="hidden" id="page_num" name="page_num" value="">
          <input type="hidden" id="nowPage" name="nowPage" value="${param.nowPage }">

          <label for="first_day" style="font-size: 10px;">검색기간</label>
          <input id="first_day" name="first_day" style="font-size: 11px; margin-bottom: 0px; width: 105px;" type="date" value="${first_day }">
          <span style="font-weight: bolder;">~</span>
          <input id="second_day" name="second_day" style="font-size: 11px; margin-bottom: 0px; width: 105px;" type="date" value="${second_day }"> 
          
          <select style="font-size: 10px;" id='search_condition' name="search_condition">
            <option value="none" <c:if test="${search_condition eq '' }">selected</c:if> >선택(조건)</option>
            <option value="search_id" <c:if test="${search_condition eq 'search_id' }">selected</c:if> >아이디</option>
            <option value="msg_title" <c:if test="${search_condition eq 'msg_title' }">selected</c:if> >제목</option>
            <option value="msg_content" <c:if test="${search_condition eq 'msg_content' }">selected</c:if> >내용</option>
          </select> 
           
          <input class="form-search" style="margin-bottom: 0px; font-size: 10px;" type="text" id='msgword' name='msgword' value="${msgword }" placeholder="쪽지검색">
          <button type="submit" class='btn btn-link' style="font-size: 10px; padding-left: 0px;"><img src='./images/search.png'>검색</button>
        </fieldset>
      </form>
    </DIV>
  </DIV>
  
  <DIV> 
    <TABLE class='table table-striped' style='border-radius:10px;'>
      <colgroup id='list_colgroup'>
        <col style='width: 3%;'/>
        <col style='width: 8%;'/>
        <col style='width: 8%;'/>
        <col style='width: 13%;'/>
        <col style='width: 32%;'/>
        <col style='width: 12%;'/>
        <col style='width: 12%;'/>
      </colgroup>
      <thead style="font-size: 0.7em; border: 1px solid #ddd;">  
        <TR id='list_tr'>  
          <TH style='text-align: center ;'><input type="checkbox" id='all_check'></TH>
          <TH style='text-align: center ;'>보낸이</TH>
          <TH style="text-align: center ;">받는이</TH> 
          <TH style='text-align: center ;'>제목</TH>
          <TH style='text-align: center ;'>내용</TH>
          <TH style='text-align: center ;'>보낸날짜</TH>
          <TH style='text-align: center ;'>확인날짜</TH>
        </TR> 
      </thead>
      <tbody style="font-size: 0.7em;" id='recv_list_tbody'>
        <c:forEach var="list" items="${msg_list }">
          <TR style="border: 0.3px solid #ddd;">
            <td><input type="checkbox" id='checked' data-msg_no='${list.msg_no }'></td>
            <td style='text-align: center ;'>${list.sender_id}(${list.sender_name })</td>
            <td style='text-align: center ;'>${list.receiver_id }(${list.receiver_name })</td>
            <td style='text-align: center ;'>${list.msg_title }</td>
            <td style='text-align: center ;'>${list.msg_content }</td>
            <td style='text-align: center ;'>${list.msg_date }</td>
            <c:choose>
              <c:when test="${list.msg_rev_date == null }">
                <td style='text-align: center ;'>미확인</td>
              </c:when>
               
              <c:otherwise>
                <td style='text-align: center ;'>${list.msg_rev_date }</td>
              </c:otherwise>
            </c:choose>
          </TR>
        </c:forEach> 
      </tbody>
    </TABLE>
  </DIV>
  
  <DIV>
    ${paging }
  </DIV>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>