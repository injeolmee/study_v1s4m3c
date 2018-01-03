<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>QnA</title>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">
function deleteOne(qnano) {
  var frm_delete = $('#frm_delete');
  $.ajax({
    url: "./delete.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'qnano=' + qnano,
    success: function(data) {
      $('#qnano', frm_delete).val(qnano);
      $("#myModal").modal();
    }
  });
}

$(function() {
  
  $('#frm_reply').show();
  $('#frm_update').hide();
  
//************** 댓글 수정 버튼에 대한 이벤트 등록 **************
  $('#update_reply_btn').click(function() { 
    var reply_data = $('#frm_update').serialize();
    update_reply_post(reply_data);   
  });
//*******************************************************************   
});

//**************************  답변 등록창 ON ********************* 
function reply_start(no) {
  $('#reply' + no).show(); 
  $("#reply_panel"+ no).off("click");
  $('#reply_panel' + no).html('답변 취소');
  $('#reply_panel' + no).attr('href', 'javascript:reply_end(' + no + ');');
}
//*******************************************************************

//**************************  답변 등록창 OFF ********************* 
function reply_end(no) {
  $('#reply' + no).hide(); 
  $("#reply_panel"+ no).off("click");
  $('#reply_panel' + no).html('답변'); 
  $('#reply_panel' + no).attr('href', 'javascript:reply_start(' + no + ');');
}
//*******************************************************************

//************* 댓글 수정을 위한 댓글 내용 불러오기 **************
function reply_update(qrno) { 
  
  $('#frm_reply').hide();     
  $('#frm_update').show();
  
  var params  = "qrno=" +  qrno;
  // alert("params: " + params);
  
  $.ajax({
    url : "/study/user/qnaboard/reply_update.do",
    type: "GET",
    data: params,
    dataType: 'JSON',
    success: function (data) {
      var reply_update = $('#frm_update');
      var qrno = data.qrno;
      var qrcont = data.qrcont;
      
      $("#qrno", reply_update).val(qrno);
      $("#qrcont", reply_update).val(qrcont);
    }
  }); 
}
//*******************************************************************

//********************* 댓글 수정을 위한 처리 *********************
function update_reply_post(reply_data) {
  
  $.ajax({
    url: "/study/user/qnaboard/reply_update.do",
    type: "POST",
    data: reply_data,
    dataType: "JSON",
    success: function (data) {
      
      if (data.count == 1) {
        alert("댓글이 수정되었습니다.");
        window.location.reload();
      } else {
        alert("오류가 발생하여 댓글 수정에 실패하였습니다. 다시 시도해주십시오.");
      }
    }
  }); 
}
//********************************************************************

//************* 댓글 삭제버튼을 누르면 실행되는 alert *************
function alert_delete_form(qrno){
  
  alert("qrno: " + qrno);
  
  var check = confirm("댓글을 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");
  
  if (check == true) { // 댓글 삭제 확인을 눌렀을 경우
    delete_reply(qrno);
    // alert("seqno_check: " + seqno);
    // alert("sreplyno: " + shreplyno);
  /* 
    if (seqno == 0) { // 부모 댓글일 경우
      // alert("부모 댓글 처리를 시작합니다.");
      update_parent(qrno);
    } 
  
    if (qrseqno == 1) { // 하위 댓글이 전혀 없는 마지막 자식 댓글일 경우
      // alert("일반 댓글 처리를 시작합니다.");
      delete_reply(qrno);
    } */

  } else {                 // 댓글 삭제 취소를 눌렀을 경우
    alert("취소되었습니다.");
  }
};
//********************************************************************

//*********************** 댓글 일반 삭제 처리 ***********************
function delete_reply(qrno) {
  
  var nowPage = ${qnaVO.nowPage};
  var param = "qrno=" + qrno + "&nowPage=" + nowPage;
  
  $.ajax({
    url: "/study/user/qnaboard/reply_delete.do",
    type: "POST",
    data: param,
    dataType: "JSON",
    success: function(data) {
      
      if (data.count == 1) { // 댓글 삭제 처리가 성공했을 경우
        alert("댓글이 삭제되었습니다.");
        window.location.reload(); // 캐시 및 서버에서 받아와서 새로고침
      } else { // 댓글 삭제 처리가 실패했을 경우
        alert("오류가 발생하여 댓글 삭제에 실패하였습니다. 다시 시도해주십시오.");
      }
    } 
  })
};
//***********************************************************************

//********************** 삭제 버튼 클릭시 이벤트 처리 ******************
function delete_check(qnano) {
  
  alert("삭제를 하면 복구할 수 없습니다. 삭제하시겠습니까?");
  
  var param = "qnano=" + qnano;
  
  $.ajax({
    url: "/study/user/qnaboard/delete.do",
    type: "POST",
    data: param,
    dataType: "JSON",
    success: function (data) {
      
      alert("data.count ---> " + data.count);
      
      if (data.count == 1) {
        alert("게시글이 삭제되었습니다.");
        document.location.href = "/study/nonuser/qnaboard/list_all_qna.do";
      } else {
        alert("오류가 발생하여 게시글을 삭제하지 못했습니다. 다시 시도해주십시오.");
      }
    }
  }); 
}
//************************************************************************

</script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>
  <DIV style="overflow: auto; width: 810px; height: 155px; margin: 0px auto; padding-left: 20%; background: url(./images/sub_bg01.gif) top right no-repeat; display: inline; float: left;">
    <H3 style="margin-top: 30px; margin-bottom: 20px; line-height: 34px; font-size: 34px;">QnA 게시판</H3>
    <p style="line-height: 20px; font-size: 13px; text-align: left; color: #000;">
      궁금하신 내용을 검색해 주세요.<br>
    잘 살펴보시고 더 궁금한 점이 있으시면 문의하기를 이용해주세요.
    </p>
  </DIV>
  <DIV class="cb t10"></DIV>
  <table style="border-collapse: collapse; width: 55%; margin-left: 22.6%; border-spacing: 30px;">
    <tbody>
      <tr style="line-height:35px">
        <th style="font-size: 20px; color: #333333; line-height: 15px;">질문</th>
        <td>${qnaVO.qnatitle }</td>
      </tr>
      <tr style="line-height:35px">
        <th style="font-size: 20px; color: #333333; line-height: 15pt;">분류</th>
        <td>${qnaVO.qnagrp }</td>
        <th style="font-size: 20px; color: #333333; line-height: 15pt;">등록일</th>
        <td>${qnaVO.qdate }</td>
        <th style="font-size: 20px; color: #333333; line-height: 15pt;">조회수</th>
        <td>${qnaVO.qnacnt }</td>
      </tr>
      <tr style="line-height:35px">
        <th style="font-size: 20px; color: #333333; line-height: 15pt;">첨부파일</th>
        <td colspan="5">
          <A href="${pageContext.request.contextPath}/download?dir=/qnaboard/storage&filename=${qnaVO.qnafile1}&downname=${qnaVO.qnafile1}">
            ${qnaVO.qnafile1 }
          </A>
        </td>
      </tr>
      <tr style="line-height:35px">
        <th style="font-size: 20px; color: #333333; line-height: 15pt;">질문내용</th>
        <td colspan="5">
          <textarea id="qnacont" cols="30" rows="6" wrap="soft" style="width: 98%; height: 20%;" readonly="readonly">${qnaVO.qnacont }</textarea>
        </td>
      </tr>
    </tbody>
  </table>
  
  <DIV class="cb t10"></DIV>
  
  <table style="border-collapse: collapse; width: 62%; margin-left: 19.6%;">
    <tbody>
      <tr>
        <td style="width: 640px; text-align: left; font-size: 12px; color: #333333; line-height: 15pt; border-top: 1px solid #ddd;">
          <IMG src="./images/bul_squ_black.png">
          더 궁금하신 내용은 계속해서 문의 해주시면 신속하게 답변해 드리겠습니다.
          <%-- <c:choose>
          <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A' or sessionScope.memberno}">
          <button type="button" class="btn btn-default btn-sm" style="line-height: 15px;" onclick="location.href='./update.do?qnano=${qnaVO.qnano}'">수정하기</button>
          <A href="javascript: delete_check(${qnaVO.qnano});" type="button" class="btn btn-default btn-sm">삭제하기</A>
          </c:when>
          <c:otherwise></c:otherwise>
          </c:choose> --%> 
          <!-- <A style="float: right;"> | 수정 | </A>
          <A style="float: right;"> | 삭제  &nbsp;</A> -->
          <button type="button" class="btn btn-default btn-sm" style="line-height: 15px; float: right;" onclick="location.href='/study/user/qnaboard/update.do?qnano=${qnaVO.qnano}'">수정하기</button>
          <button type="button" class="btn btn-default btn-sm" onclick="location.href='javascript: delete_check(${qnaVO.qnano});'" style="line-height: 15px; float: right;">삭제하기</button>
          <button type="button" class="btn btn-default btn-sm" onclick="location.href='/study/nonuser/qnaboard/list_all_qna.do'" style="line-height: 15px; float: right;">목록</button>
        </td>
      </tr>
    </tbody>
  </table>
  
  <!-------------------------------------- 댓글 목록창 시작 -------------------------------------->
  <DIV style="padding: 0px 15px 15px 15px; width: 62%; margin-left: 18%;"> 
     <c:forEach var="qnareplyVO" items="${list_reply }">
     
     <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 시작 ----------->

     <DIV style="border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px;">
       <span style="font-weight: bold; line-height: 30px;">
         <IMG src="${pageContext.request.contextPath}/nonuser/qnaboard/images/user.png" style="padding-right: 1%;">
         ${qnareplyVO.qrname }
       </span>
       <span style="font-size: 0.7em; color: #999999; padding-left: 2%;">
         ${qnareplyVO.qrdate } 
       </span>
       <div style='float: right;'>
         <c:if test="${fn:contains(memberno, qnareplyVO.memberno)}">
         <a href='javascript:reply_update("+${qnareplyVO.qrno}+")' style='font-size: 0.8em; color: #999999;' >수정</a>
         <a href='javascript:alert_delete_form(${qnareplyVO.qrno})' style='font-size: 0.8em; color: #999999;'> | 삭제</a>
         </c:if>
       </div>
       <br>
           <span style="margin-bottom: 10px;">
             ${qnareplyVO.qrcont}
           </span>
         </DIV>
         <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 종료 ----------->
    </c:forEach>
  </DIV>
  <!-------------------------------------- 댓글 목록창 종료 -------------------------------------->
      
  <!---------------------------- 페이징 처리 시작 ---------------------------->
  <form name="frm_read_paging" id="frm_read_paging" action="./read_paging.do" method="get" style="text-align: center;">
    <input type="hidden" name="qnano" id="qnano" value="${qnaVO.qnano }">
    <DIV class="bottom_menu" style="padding-bottom: 40px; margin: 0px auto; display: table;">
      ${paging }
    </DIV>
  </form>
  <!---------------------------- 페이징 처리 종료 ---------------------------->

  <!---------------------------- 댓글 등록창 시작 (새로운 댓글이 등록되는 창) ---------------------------->
  <FORM name='frm_reply' id='frm_reply' method='POST' action='/study/user/qnaboard/reply.do' style="text-align: left; width: 63%; margin: 0px auto;">
    <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
    <input type='hidden' name='qrname' id='qrname' value=${sessionScope.memname }>
    <input type='hidden' name='qnano' id='qnano' value= ${qnaVO.qnano }>
    
    <textarea name="qrcont" id="qrcont" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 90%; height: 76px;"></textarea>
    <button type="submit" style="width: 7%; height:80px; float: right;">등록</button> 
  </FORM>
  <!---------------------------- 댓글 등록창 종료 (새로운 댓글이 등록되는 창) ---------------------------->
   
  <!---------------------------- 댓글 수정창 시작 (기존 댓글이 수정되는 창) ---------------------------->
  <FORM name='frm_update' id='frm_update' method='POST' action='' style="text-align: left; width: 63%; margin: 0px auto;">
    <input type='hidden' name='qrno' id='qrno' value= ''>
    <input type='hidden' name='nowPage' id='nowPage' value= '${qnaVO.nowPage}'>
    <input type='hidden' name='qnano' id='qnano' value= '${qnaVO.qnano}'>
    
    <textarea name= "qrcont" id="qrcont" rows="100" cols="50" style="width: 90%; height: 76px;">
    </textarea>
    <button type="button" name="update_reply_btn" id="update_reply_btn" onclick="" style="width: 7%; height:80px; float: right;">수정</button> 
  </FORM>
  <!---------------------------- 댓글 수정창 종료 (기존 댓글이 수정되는 창) ---------------------------->
  
  <div class="modal fade" id="myModal" role="dialog" style="font-size: 18px; display: none;">
  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title" style="font-weight: bold; text-align: center; padding-left: 40px;">질문 글 삭제</h4>
    </div>
    <div class="modal-body" style='width: 95%; height: 210px; text-align: center; margin: 0px auto;'>
      <FORM name = 'frm_delete' id = 'frm_delete' method="POST" action="./delete.do">
        <input type="hidden" name="qnano" id="qnano" value='${qnaVO.qnano}'>
        <DIV class = "form-group form-group-sm">
          <P style="color: red; font-weight: bold; font-size: 25px;">
            <IMG src="./images/warning.png" style="width: 20px; height: 20px;">
             <span>게시글을 삭제 합니다.</span>
          </P>
          <P style="color: red;">한번 삭제된 게시글은 복구가 불가능합니다.</P>
          <P style="color: red;">계속 삭제를 진행하시겠습니까????</P>
        </DIV>
        
        <DIV style="clear: both; padding-top: 10px !important; border-bottom: 1px solid #CACACA;"></DIV>
        
        <div class="form-group form-group-sm" style="margin: 0px auto; text-align: center;">
          <DIV style="clear: both; padding-top: 10px !important;"></DIV>
          <button type = "submit" class="btn btn-default">Delete</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </FORM>
    </div>
  </div>
</div>
<!-- Modal END -->
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>