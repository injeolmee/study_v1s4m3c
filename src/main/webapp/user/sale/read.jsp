<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="${pageContext.request.contextPath}/user/sale/gnacss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!---------------------------------------------------------------------->
<script type="text/javascript">
$(function() {
  imgResize(); 
  $('#frm_reply').show();
  $('#frm_update').hide();
  
//************** 댓글 수정 버튼에 대한 이벤트 등록 **************
  $('#update_reply_btn').click(function() { 
    var reply_data = $('#frm_update').serialize();
    update_reply_post(reply_data);   
  });
//*******************************************************************  
});

//************** 출력할 이미지 사이즈에 대한 변환 ***************
function imgResize() {
  var file = $('#file');
  var width = file.width();
  
  if (width > screen.width-(screen.width * 0.3)) { // 이미지의 width가 화면의 50%보다 크다면  
    // file.width(600); // 이미지 축소
    $('#filePanel').attr('width', '100%');
    file.css('width', '100%'); // <div id='filePanel'> 태그의 width에 맞추어 자동 축소 처리
  } else {
   // 작은 이미지는 그대로 출력
  }
}
//*******************************************************************  

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
function reply_update(sreplyno) { 
  
  $('#frm_reply').hide();     
  $('#frm_update').show();
  
  var params  = "sreplyno=" +  sreplyno;
  // alert("params: " + params);
  
  $.ajax({
    url : "/study/user/sale/reply_update.do",
    type: "GET",
    data: params,
    dataType: 'JSON',
    success: function (data) {
      var reply_update = $('#frm_update');
      var sreplyno = data.sreplyno;
      var sreplycontent = data.sreplycontent;
      
      $("#sreplyno", reply_update).val(sreplyno);
      $("#sreplycontent", reply_update).val(sreplycontent);
    }
  }); 
}
//*******************************************************************

//********************* 댓글 수정을 위한 처리 *********************
function update_reply_post(reply_data) {
  
  $.ajax({
    url: "/study/user/sale/reply_update.do",
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
function alert_delete_form(sreplyno, seqno){
  
  // alert("sreplyno: " + sreplyno + "seqno: " + seqno);
  
  var check = confirm("댓글을 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");
  
  if (check == true) { // 댓글 삭제 확인을 눌렀을 경우
    // alert("seqno_check: " + seqno);
    // alert("sreplyno: " + sreplyno);
  
    if (seqno == 0) { // 부모 댓글일 경우
      // alert("부모 댓글 처리를 시작합니다.");
      update_parent(sreplyno);
    } 
  
    if (seqno == 1) { // 하위 댓글이 전혀 없는 마지막 자식 댓글일 경우
      // alert("일반 댓글 처리를 시작합니다.");
      delete_reply(sreplyno);
    }

  } else {                 // 댓글 삭제 취소를 눌렀을 경우
    alert("취소되었습니다.");
  }
};
//********************************************************************

//*********************** 댓글 일반 삭제 처리 ***********************
function delete_reply(sreplyno) {
  
  var nowPage = ${saleVO.nowPage};
  var param = "sreplyno=" + sreplyno + "&nowPage=" + nowPage;
  
  $.ajax({
    url: "/study/user/sale/reply_delete.do",
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

//***** seqno가 0인 부모 댓글에 대한 delete를 update로 처리********
function update_parent(sreplyno) {
  
  var nowPage = ${saleVO.nowPage}
  var param = "sreplyno=" + sreplyno + "&nowPage=" + nowPage;
  
  $.ajax({
    url: "/study/user/sale/update_parent.do",
    type: "POST",
    data: param,
    dataType: "JSON",
    success: function (data) {
      if (data.count == 1) {       // 부모 댓글 삭제와 관련된 수정 처리가 성공했을 경우
        alert("댓글이 삭제되었습니다.");
        window.location.reload();
      } else {                          // 부모 댓글 삭제와 관련된 수정 처리가 실패했을 경우
        alert("오류가 발생하여 댓글 삭제에 실패하였습니다. 다시 시도해주십시오.");
      }
    }
  });
};
//************************************************************************

//********************** 삭제 버튼 클릭시 이벤트 처리 ******************
function delete_check(saleno) {
  
  var param = "saleno=" + saleno;
  
  var check = confirm("게시글을 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");
  
  if (check == true) {
    $.ajax({
      url: "/study/user/sale/delete.do",
      type: "POST",
      data: param,
      dataType: "JSON",
      success: function (data) {
        
        if (data.count == 1) {
          alert("게시글이 삭제되었습니다.");
          document.location.href = "/study/user/sale/list.do";
        } else {
          alert("오류가 발생하여 게시글을 삭제하지 못했습니다. 다시 시도해주십시오.");
        }
      }
    }); 
  } else {
    
  }

}
//************************************************************************

</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 50%; margin: 0px auto;'>  

  <FORM name='frm_read' id='frm_read' method='GET' action='./read.do' style="text-align: left; width: 100%;">
    <input type="hidden" name="saleno" id="saleno" value="${saleVO.saleno }">        

    <!----------------------- ASIDE 메뉴 시작 --------------------------------------->
    <DIV style="width: 105%; padding-bottom: 50px;">
    <c:set var ="memberno" value="${sessionScope.memberno }"/>
      <ASIDE style='float: left; '>
      <c:choose>
        <c:when test="${fn:contains(saleVO.saleno, saleVO.post_num)}">
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
        </c:when>
        <c:when test="${fn:contains(saleVO.saleno, saleVO.pre_num)}">
           <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
        </c:when>
        <c:otherwise>
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/read.do?saleno=${saleVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
        </c:otherwise>
      </c:choose> 
      </ASIDE> 
      <ASIDE style='float: right;'>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/list.do?word=${param.word}&search=${param.search}'">목록형</button>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/list_grid.do?word=${param.word}&search=${param.search}'">앨범형</button>
       <!-- ⓐ 접속한 회원이 작성자일 경우 수정 / 삭제가 보임 -->
        <c:if test="${fn:contains(memberno, saleVO.memberno)}">
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/update.do?saleno=${saleVO.saleno}&word=${param.word}&search=${param.search}'">수정</button>
          <button type="button" class="btn btn-default" onclick="delete_check(${saleVO.saleno })">삭제</button>
        </c:if>
        <!----------------------------------------------------------->
        <!------- ⓑ 관리자일 경우 수정 / 삭제가 모두 보임 ------->
        <c:if test="${sessionScope.adminno != null }">
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/sale/update.do?saleno=${saleVO.saleno}&word=${param.word}&search=${param.search}'">수정</button>
          <button type="button" class="btn btn-default" onclick="delete_check(${saleVO.saleno })">삭제</button>
        </c:if>
        <!----------------------------------------------------------->
      </ASIDE>      
    </DIV>
    <!----------------------- ASIDE 메뉴 종료 --------------------------------------->
    
    <fieldset class="fieldset_basic" style="border: solid 2px #dad8d7; font-size: 15px; width: 100%; margin: 0px auto;">
      <ul style="margin: 0px auto;">
        <!----------------------------------- sale 게시판의 게시글 read 시작 ----------------------------------->
        <li class="li_none">
          <div style="line-height: 0px">
            <span style="font-weight: bold; margin-right: 20px;">${saleVO.saletitle }</span>
          </div>
          <div style="text-align: right; font-size: 0.8em; line-height: 0px;"> ${saleVO.saledate } | 조회수 ${saleVO.salecnt}</div>
          <HR style="color: #EEEEEE;">
        </li>
        
        <li class="li_none">
          <div style="width: 100%; margin-top: 3px; margin-bottom: 10px; font-weight: bold;">
            <IMG src="${pageContext.request.contextPath}/user/sale/images/user_gray.png"> ${saleVO.salename } 
            <div style="float: right; font-size: 0.8em; ">
            <c:if test="${saleVO.salesize > 0}">
              파일: <A href='${pageContext.request.contextPath}/download?dir=${pageContext.request.contextPath}/user/sale/storage&filename=${saleVO.salefstor}&downname=${saleVO.salefile}'>${saleVO.salefile}</A> 
              (${saleVO.salesizeLabel})
            </c:if>
          </div>
          </div>
        </li>
        
        <li class="li_none">
          <div style="width: 100%; margin-top: 15px; margin-bottom: 15px;">
            <div id='filePanel' style="width: 130px; padding-right: 10%; float: left; height: 150px; line-height: 150px; vertical-align: middle; text-align: center;">
              <!-- 이미지 확장자별로 검사하여 불러오기 시작 -->
              <c:set var='file' value="${fn:toLowerCase(saleVO.salefile)}" />              
              <c:choose>  
                <c:when test="${fn:endsWith(file, '.jpg')}">
                  <IMG id='file' src='${pageContext.request.contextPath}/user/sale/storage/${saleVO.salefile}' >
                </c:when>
                <c:when test="${fn:endsWith(file, '.gif')}">
                  <IMG id='file'  src='${pageContext.request.contextPath}/user/sale/storage/${saleVO.salefile}' >
                </c:when>
                <c:when test="${fn:endsWith(file, '.png')}">
                  <IMG id='file'  src='${pageContext.request.contextPath}/user/sale/storage/${saleVO.salefile}' >
                </c:when>
                <c:when test="${file ne null}">
                  <IMG id='file' src='${pageContext.request.contextPath}/user/sale/images/none.png' style="height: 130px;" >
                </c:when>
              </c:choose> 
              <!-- 이미지 확장자별로 검사하여 불러오기 종료 -->
            </div>
            <div style="padding-top: 30px;">
            <span style="font-weight: bold;">가격</span> ${saleVO.saleprice } 원 <br>
            <span style="font-weight: bold;">주소</span> ${saleVO.saleaddress} <br>
            <span style="font-weight: bold;">연락처</span> ${saleVO.saletel } <br>
            <span style="font-weight: bold;">이메일</span> ${saleVO.saleemail } <br>
            <span style="font-weight: bold;">상태</span>
              <c:choose>
                <c:when test="${saleVO.saleseqno == 0}"> <!-- 출력 순서가 0이면 판매중 이미지 -->
                  판매중인 상품입니다.
                  <IMG id='saleseqno' src='${pageContext.request.contextPath}/user/sale/images/ing.png' > <br>
                </c:when>
                <c:when test="${saleVO.saleseqno == 1}"> <!-- 출력 순서가 1이면 판매완료 이미지 -->
                  판매가 완료된 상품입니다. 
                  <IMG id='saleseqno' src='${pageContext.request.contextPath}/user/sale/images/end.png' > <br>
                </c:when>
              </c:choose>
              <br>
            </div>
          </div>
          <HR style="color: #EEEEEE;">
        </li>
      
        <li class="li_none">
          <div style="width: 100%; margin-top: 30px; margin-bottom: 30px;">
            ${saleVO.salecontent }
          </div>
        </li>
              
        <li class="li_none">
          <div style="width: 100%; margin-top: 5px; line-height: 0;">
          </div>
          <HR style="color: #EEEEEE;">
        </li>
        </FORM> 
        <!----------------------------------- sale 게시판의 게시글 read 종료 ----------------------------------->
        
        <!-------------------------------------- 댓글 목록창 시작 -------------------------------------->
        <DIV style="padding: 0px 15px 15px 15px;"> 
          <c:forEach var="salereplyVO" items="${list_reply }">
          
          <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 시작 ----------->
          <c:if test="${salereplyVO.seqno != 3}">
          <DIV style="border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px;">
            <span style="font-weight: bold; line-height: 30px;">
              <c:choose>
                <c:when test="${salereplyVO.sreplyansnum == 0 }"> <!-- 부모글인경우 -->
                </c:when>
                <c:when test="${salereplyVO.sreplyansnum > 0}"> <!--  답변글인경우 -->
                  <img src='${pageContext.request.contextPath}/user/sale/images/white.jpg' style='padding-left:${salereplyVO.sreplyindent * 15}px; opacity: 0.0;'>
                  <img src='${pageContext.request.contextPath}/user/sale/images/reply.jpg'>
                </c:when>
              </c:choose>
              <IMG src="${pageContext.request.contextPath}/user/sale/images/user.png" style="padding-right: 1%;">
              ${salereplyVO.sreplyname }
            </span>
            <span style="font-size: 0.7em; color: #999999; padding-left: 2%;">
              ${salereplyVO.sreplydate } 
            </span>
            <span style="font-size: 0.8em; padding-left: 2%;">
              <IMG src="${pageContext.request.contextPath}/user/sale/images/rereply.png"><A href="javascript:reply_start(${salereplyVO.sreplyno });" id ='reply_panel${salereplyVO.sreplyno }'>답변</A>
            </span>
            <div style='float: right;'>
              <!--------- ⓐ 댓글의 작성자일 경우 수정 / 삭제 출력 ----------------->
              <c:if test="${fn:contains(memberno, salereplyVO.memberno)}">
                <a href='javascript:reply_update("+${salereplyVO.sreplyno }+")' style='font-size: 0.8em; color: #999999;' >수정</a>
                <a href='javascript:alert_delete_form(${salereplyVO.sreplyno },${salereplyVO.seqno })' style='font-size: 0.8em; color: #999999;'> | 삭제</a>
              </c:if>
              <!-------------------------------------------------------------------->
              <!--------- ⓑ 관리자일 경우 무조건 수정 / 삭제 출력 ----------------->
              <c:if test="${sessionScope.adminno ne null}">
                <a href='javascript:reply_update("+${salereplyVO.sreplyno }+")' style='font-size: 0.8em; color: #999999;' >수정</a>
                <a href='javascript:alert_delete_form(${salereplyVO.sreplyno },${salereplyVO.seqno })' style='font-size: 0.8em; color: #999999;'> | 삭제</a>
              </c:if>
              <!-------------------------------------------------------------------->
            </div>
            <br>
             <c:choose>
                <c:when test="${salereplyVO.sreplyansnum == 0 }">
                <span style="margin-bottom: 10px;">
                  ${salereplyVO.sreplycontent}
                </span>
                </c:when>
                <c:when test="${salereplyVO.sreplyansnum > 0}">
                <DIV style="padding-left: 5%;">
                  <span style="padding-left:${salereplyVO.sreplyindent * 2.5}%; margin-bottom: 10px;">
                  ${salereplyVO.sreplycontent}
                  </span>
                </DIV>
                </c:when>
              </c:choose>
            </DIV>
            </c:if>
            <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 종료 ----------->
            
            <!------------------ ② 삭제된 댓글의 경우 출력 List 시작 ----------------->
            <!-- 이때 삭제된 댓글이 하위 댓글을 가지고 있는 경우가 됨 -->
            
            <c:if test="${salereplyVO.seqno == 3}">
            <DIV style="border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px;">
              <span style="line-height: 30px; color: #ff9999;">
              <c:choose>
                <c:when test="${salereplyVO.sreplyansnum == 0 }"> 
                 <!-- 삭제할 댓글이 일반 댓글로 등록되었던 경우 -->
                  ${salereplyVO.sreplycontent}
                </c:when> 
                <c:when test="${salereplyVO.sreplyansnum > 0}">
                  <!-- 삭제할 댓글이 답변 댓글로 등록되었던 경우 -->
                  <img src='${pageContext.request.contextPath}/user/sale/images/white.jpg' style='padding-left:${salereplyVO.sreplyindent * 15}px; opacity: 0.0;'>
                  <img src='${pageContext.request.contextPath}/user/sale/images/reply.jpg'>  
                  ${salereplyVO.sreplycontent}
                </c:when>
              </c:choose>
              </span>
              <br>
              </DIV>
            </c:if>
            
            <!------------------ ② 삭제된 댓글의 경우 출력 List 종료 ----------------->
            
            <!---------------------------- 댓글 등록창 시작 (댓글의 댓글(대댓글)이 입력되는 창) ---------------------------->
            <form name="frm_rereply" id="frm_rereply" method="POST" action="./rereply.do">
              <DIV id="reply${salereplyVO.sreplyno }" style='display: none; padding: 10px 20px;'>    
                <c:if test="${sessionScope.memberno ne null}">
                  <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
                  <input type='hidden' name='sreplyname' id='sreplyname' value=${sessionScope.memname }>
                </c:if>
                <c:if test="${sessionScope.adminno ne null}">
                   <input type='hidden' name='adminno' id='adminno' value=${sessionScope.adminno }>
                  <input type='hidden' name='sreplyname' id='sreplyname' value=${sessionScope.admname }>
                </c:if>
                <input type='hidden' name='sreplygrpno' id='sreplygrpno' value=${salereplyVO.sreplygrpno }>
                <input type='hidden' name='sreplyindent' id='sreplyindent' value=${salereplyVO.sreplyindent }>
                <input type='hidden' name='sreplyansnum' id='sreplyansnum' value=${salereplyVO.sreplyansnum }>
                <input type='hidden' name='saleno' id='saleno' value= ${salereplyVO.saleno }>
                <input type='hidden' name='sreplyno' id='sreplyno' value= ${salereplyVO.sreplyno }>
                <input type='hidden' name='nowPage' id='nowPage' value= '${salereplyVO.nowPage }'>

                <textarea name= "sreplycontent" id="sreplycontent" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 88%; height: 76px; margin-left: 10px;"></textarea>
                <button type="submit" style="width: 7%; float: right; height: 80px;">등록</button>
              </DIV>
            </form>
            <!---------------------------- 댓글 등록창 종료 (댓글의 댓글(대댓글)이 입력되는 창) ---------------------------->
            
          
          </c:forEach>
        </DIV>
        <!-------------------------------------- 댓글 목록창 종료 -------------------------------------->
      </ul>
      
  <!---------------------------- 페이징 처리 시작 ---------------------------->
  <form name="frm_read_paging" id="frm_read_paging" action="./read_paging.do" method="get" style="text-align: center;">
    <input type="hidden" name="saleno" id="saleno" value="${saleVO.saleno }">
    <DIV class="bottom_menu" style="padding-bottom: 20px; margin: 0px auto; display: table;">
      ${paging }
    </DIV>
  </form>
  <!---------------------------- 페이징 처리 종료 ---------------------------->

  <!---------------------------- 댓글 등록창 시작 (새로운 댓글이 등록되는 창) ---------------------------->
  <FORM name='frm_reply' id='frm_reply' method='POST' action='./reply.do' style="text-align: left; width: 90%; margin: 0px auto;">
    <c:if test="${sessionScope.memberno ne null}">
      <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
      <input type='hidden' name='sreplyname' id='sreplyname' value=${sessionScope.memname }>
    </c:if>
    <c:if test="${sessionScope.adminno ne null}">
       <input type='hidden' name='adminno' id='adminno' value=${sessionScope.adminno }>
       <input type='hidden' name='sreplyname' id='sreplyname' value=${sessionScope.admname }>
    </c:if>
    <input type='hidden' name='saleno' id='saleno' value= ${saleVO.saleno }>
    <input type='hidden' name='nowPage' id='nowPage' value= '${saleVO.nowPage }'>
    
    <textarea name= "sreplycontent" id="sreplycontent" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 90%; height: 76px;"></textarea>
    <button type="submit" style="width: 7%; height:80px; float: right;">등록</button> 
  </FORM>
  <!---------------------------- 댓글 등록창 종료 (새로운 댓글이 등록되는 창) ---------------------------->
  
  <!---------------------------- 댓글 수정창 시작 (기존 댓글이 수정되는 창) ---------------------------->
  <FORM name='frm_update' id='frm_update' method='POST' action='' style="text-align: left; width: 90%; margin: 0px auto;">
    <input type='hidden' name='sreplyno' id='sreplyno' value= ''>
    <input type='hidden' name='nowPage' id='nowPage' value= '${saleVO.nowPage}'>
    <input type='hidden' name='saleno' id='saleno' value= '${saleVO.saleno}'>
    
    <textarea name= "sreplycontent" id="sreplycontent" rows="100" cols="50" style="width: 90%; height: 76px;"></textarea>
    <button type="button" name="update_reply_btn" id="update_reply_btn" onclick="" style="width: 7%; height:80px; float: right;">수정</button> 
  </FORM>
  <!---------------------------- 댓글 수정창 종료 (기존 댓글이 수정되는 창) ---------------------------->
              
  </fieldset> 


</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>