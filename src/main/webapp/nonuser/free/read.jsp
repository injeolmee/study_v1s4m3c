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
<link href="${pageContext.request.contextPath}/nonuser/free/gnacss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!---------------------------------------------------------------------->
<script type="text/javascript">
$(function() {
  $('#frm_reply').show();
  $('#frm_update').hide();
  good();
  
  //************** 댓글 수정 버튼에 대한 이벤트 등록 **************
  $('#update_reply_btn').click(function() { 
    var reply_data = $('#frm_update').serialize();
    update_reply_post(reply_data);   
  });
  //*******************************************************************  
});

//********************************************************************************
//                        좋아요와 관련된 처리 시작
//*********************************************************************************

  //******* ① 회원이 글을 read하면 자동으로 회원과 관련된 체크 ********
  function good(){
  
    var freeno = ${freeVO.freeno};
    var params = "freeno="+freeno;
  
    $.ajax({
      url : '/study/user/freelike/like.do',
      type: "GET",
      data : params,
      dataType : 'JSON',
      success : function(data){
      
        var src=""; // 상황에 따라 좋아요 아이콘을 다르게 출력하기 위한 변수
      
        if(data.count == 0){ //회원이 이 글에 대해서 체크를 안했다면
          src = "<img id='like' onclick='like_y();' src='/study/nonuser/free/images/star.png'>";
        } else if(data.count == 1 || data.count == 2){ //로그인을 하지 않았거나 체크를 했었다면
          src = "<img id='like' onclick='like_y();' src='/study/nonuser/free/images/yellow_star.png'>";
        }
      
        $("#like_").html(src); // 다르게 적용된 아이콘을 "like_" 태그에 출력
      }
    });
  };
  //*************************************************************************

  //***** ② 좋아요의 체크 여부에 따라 변경되는 이미지 처리 시작 ******
  function like_y(){
    
    var like = $("#like");
    var str = like.attr('src');
 
    if (str == '/study/nonuser/free/images/star.png') { // 좋아요가 해제된 이미지의 경우
      like.attr('src', '/study/nonuser/free/images/yellow_star.png');
      like_up(); // 좋아요 체크 실행
    } else if( str == '/study/nonuser/free/images/yellow_star.png'){ // 좋아요가 체크된 이미지의 경우
      like.attr("src", '/study/nonuser/free/images/star.png');
      like_down(); // 좋아요 해제 실행
    }
  };
  //*************************************************************************

  //******************* ③ 좋아요를 체크하는 처리 시작 *******************
  function like_up(){
    
    var freeno = ${freeVO.freeno};
    var params = "freeno=" + freeno;
    
    $.ajax({
      url : '/study/user/freelike/like_up.do',
      type: "GET",
      data : params,
      dataType : 'JSON',
      success : function(data){
        if (data.count ==1 ) {
          alert("게시글에 대하여 좋아요가 적용되었습니다.");
          window.location.reload();
        }
      }
    });
  };
  //*************************************************************************
  
  //******************* ④ 좋아요를 해제하는 처리 시작 *******************
  function like_down(){
    
    var freeno = ${freeVO.freeno};
    var params = "freeno=" + freeno;
    
    $.ajax({
      url : '/study/user/freelike/like_down.do',
      type: "GET",
      data : params,
      dataType : 'JSON',
      success : function(data){
        if(data.count ==1 ){
          alert("게시글에 대하여 좋아요가 해제되었습니다.");
          window.location.reload();
        }
      }     
    });
  };
  //*************************************************************************
  
//********************************************************************************
//                        좋아요와 관련된 처리 종료
//*********************************************************************************

//**************************  답변 등록창 ON ********************* 
function reply_start(no) { 
  $('#reply' + no).show(); 
  $("#reply_panel"+ no).off("click");
  $ ('#reply_panel' + no).html('답변 취소');
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
function reply_update(freplyno) { 
  
  $('#frm_reply').hide();     
  $('#frm_update').show();
  
  var params  = "freplyno=" +  freplyno;
  // alert("params: " + params);
  
  $.ajax({
    url : "/study/user/free/reply_update.do",
    type: "GET",
    data: params,
    dataType: 'JSON',
    success: function (data) {
      var reply_update = $('#frm_update');
      var freplyno = data.freplyno;
      var freplycontent = data.freplycontent;
      
      $("#freplyno", reply_update).val(freplyno);
      $("#freplycontent", reply_update).val(freplycontent);
    }
  }); 
}
//*******************************************************************

//********************* 댓글 수정을 위한 처리 *********************
function update_reply_post(reply_data) {
  
  $.ajax({
    url: "/study/user/free/reply_update.do",
    type: "POST",
    data: reply_data,
    dataType: "JSON",
    success: function (data) {
      
      if (data.count == 1) {
        alert("댓글이 수정되었습니다.");
        window.location.reload(); // 캐시 및 서버에서 받아와서 새로고침
      } else {
        alert("오류가 발생하여 댓글 수정에 실패하였습니다. 다시 시도해주십시오.");
      }
    }
  }); 
}
//********************************************************************

//************* 댓글 삭제버튼을 누르면 실행되는 alert *************
function alert_delete_form(freplyno, seqno){
  
  // alert("freplyno: " + freplyno + "seqno: " + seqno);
  
  var check = confirm("댓글을 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");
  
  if (check == true) { // 댓글 삭제 확인을 눌렀을 경우
    // alert("seqno_check: " + seqno);
    // alert("freplyno: " + freplyno);
  
    if (seqno == 0) { // 부모 댓글일 경우
      // alert("부모 댓글 처리를 시작합니다.");
      update_parent(freplyno);
    } 
  
    if (seqno == 1) { // 하위 댓글이 전혀 없는 마지막 자식 댓글일 경우
      // alert("일반 댓글 처리를 시작합니다.");
      delete_reply(freplyno);
    }

  } else {                 // 댓글 삭제 취소를 눌렀을 경우
    alert("취소되었습니다.");
  }
};
//********************************************************************

//*********************** 댓글 일반 삭제 처리 ***********************
function delete_reply(freplyno) {
  
  var nowPage = ${freeVO.nowPage};
  var param = "freplyno=" + freplyno + "&nowPage=" + nowPage;
  
  $.ajax({
    url: "/study/user/free/reply_delete.do",
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
function update_parent(freplyno) {
  
  var nowPage = ${freeVO.nowPage}
  var param = "freplyno=" + freplyno + "&nowPage=" + nowPage;
  
  $.ajax({
    url: "/study/user/free/update_parent.do",
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
function delete_check(freeno) {
  
  var param = "freeno=" + freeno;
  
  var check = confirm("게시글을 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");

  if (check == true) {
    $.ajax({
      url: "/study/user/free/delete.do",
      type: "POST",
      data: param,
      dataType: "JSON",
      success: function (data) {
        
        if (data.count == 1) {
          alert("게시글이 삭제되었습니다.");
          document.location.href = "/study/nonuser/free/list.do";
        } else {
          alert("오류가 발생하여 게시글을 삭제하지 못했습니다. 다시 시도해주십시오.");
        }
      }
    });  
  } else {
    // alert("삭제 취소시 확인")
  }
  
};
//************************************************************************

</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 50%; margin: 0px auto;'>  

<FORM name='frm_like' id='frm_like' method='POST' action=''>
    <input type='hidden' name='freeno' id='freeno' value=''>
</FORM>

<FORM name='frm_read' id='frm_read' method='GET' action='./read.do' style="text-align: left; width: 100%;">
  <c:set var ="memberno" value="${sessionScope.memberno }"/>
  <DIV style="width: 105%; padding-bottom: 50px;">
  <ASIDE style='float: left; '>
    <c:choose>
      <c:when test="${fn:contains(freeVO.freeno, freeVO.post_num)}">
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
      </c:when>
      <c:when test="${fn:contains(freeVO.freeno, freeVO.pre_num)}">
         <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
      </c:when>
      <c:otherwise>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/nonuser/free/read.do?freeno=${freeVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
      </c:otherwise>
    </c:choose> 
    </ASIDE> 
    <ASIDE style='float: right;'>
      <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/nonuser/free/list.do?word=${param.word}&search=${param.search}'">목록</button>
      <c:if test="${fn:contains(memberno, freeVO.memberno)}">
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/free/update.do?freeno=${freeVO.freeno}&word=${param.word}&search=${param.search}&freecontent=${freeVO.freecontent}'">수정</button>
        <button type="button" class="btn btn-default" onclick="delete_check(${freeVO.freeno})">삭제</button>
      </c:if>
    </ASIDE>      
  </DIV>
    
    <!---------------------------------------------------------------------------------------------------------------------------> 
    <!--                                              Free 게시판의 read 시작                                                               -->   
    <!--------------------------------------------------------------------------------------------------------------------------->
    <fieldset class="fieldset_basic" style="border: solid 2px #dad8d7; font-size: 15px; width: 100%; margin: 0px auto;">
      <ul style="margin: 0px auto;">
      
        <li class="li_none">
          <div style="line-height: 0px">
            <span style="font-weight: bold; margin-right: 20px;">${freeVO.freetitle }</span>
          </div>
          <div style="text-align: right; font-size: 0.8em; line-height: 0px;"> ${freeVO.freedate } </div>
          <HR style="color: #EEEEEE;">
        </li>
        
        <li class="li_none">
          <div style="width: 100%; margin-top: 3px; margin-bottom: 10px; font-weight: bold;">
            <IMG src="${pageContext.request.contextPath}/nonuser/free/images/user_gray.png"> ${freeVO.freename } 
          </div>
        </li>
        
        <li class="li_none">
          <div style="width: 100%; margin-top: 30px; margin-bottom: 30px;">
            ${freeVO.freecontent }
          </div>
        </li>
              
        <li class="li_none">
          <div style="width: 100%; margin-top: 5px; line-height: 0;">
             <div style="padding-top: 30px;">조회수 ${freeVO.freecnt} | 좋아요 
               <div style="border: 1px solid #ccc; display: inline-block; padding-top: 2px; height: 18px; width: 55px; text-align: center;">
               <div id='like_' style="height: 14px; display: -webkit-inline-box;"></div> + ${freeVO.freelike }
               </div>
             </div>  
          </div>
          <HR style="color: #EEEEEE;">
        </li>
        
        </FORM>
        <!---------------------------------------------------------------------------------------------------------------------------> 
        <!--                                              Free 게시판의 read 시작                                                               -->   
        <!--------------------------------------------------------------------------------------------------------------------------->
      
       <!---------------------------------------------------------------------------------------------------------------------------> 
       <!--                                                   댓글 목록 시작                                                                      -->   
       <!--------------------------------------------------------------------------------------------------------------------------->
       <DIV style="padding: 0px 15px 15px 15px;"> 
          <c:forEach var="freereplyVO" items="${list_reply }">
          
            <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 시작 ----------->
            <c:if test="${freereplyVO.seqno != 3}">
            <DIV style="border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px;">
              <span style="font-weight: bold; line-height: 30px;">
              <c:choose>
                <c:when test="${freereplyVO.freplyansnum == 0 }"> 
                </c:when>
                <c:when test="${freereplyVO.freplyansnum > 0}">
                  <img src='${pageContext.request.contextPath}/nonuser/free/images/white.jpg' style='padding-left:${freereplyVO.freplyindent * 15}px; opacity: 0.0;'>
                  <img src='${pageContext.request.contextPath}/nonuser/free/images/reply.jpg'>
                </c:when>
              </c:choose>
              <IMG src="${pageContext.request.contextPath}/nonuser/free/images/user.png" style="padding-right: 1%;">
              ${freereplyVO.freplyname }
            </span>
            <span style="font-size: 0.7em; color: #999999; padding-left: 2%;">
              ${freereplyVO.freplydate } 
            </span>
            <span style="font-size: 0.8em; padding-left: 2%;">
              <IMG src="${pageContext.request.contextPath}/nonuser/free/images/rereply.png"><A href="javascript:reply_start(${freereplyVO.freplyno });" id ='reply_panel${freereplyVO.freplyno}'>답변</A>
            </span>
            <div style='float: right;'>
              <c:if test="${fn:contains(memberno, freereplyVO.memberno)}">
              <a href='javascript:reply_update("+${freereplyVO.freplyno }+")' style='font-size: 0.8em; color: #999999;' >수정</a>
              <a href='javascript:alert_delete_form(${freereplyVO.freplyno },${freereplyVO.seqno })' style='font-size: 0.8em; color: #999999;'> | 삭제</a>
              </c:if>
            </div>
            <br>
             <c:choose>
                <c:when test="${freereplyVO.freplyansnum == 0 }">
                <span style="margin-bottom: 10px;">
                  ${freereplyVO.freplycontent}
                </span>
                </c:when>
                <c:when test="${freereplyVO.freplyansnum > 0}">
                <DIV style="padding-left: 5%;">
                  <span style="padding-left:${freereplyVO.freplyindent * 2.5}%; margin-bottom: 10px;">
                  ${freereplyVO.freplycontent}
                  </span>
                </DIV>
                </c:when>
              </c:choose>
            </DIV>
            </c:if> 
            <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 종료 ----------->
              
            <!------------------ ② 삭제된 댓글의 경우 출력 List 시작 ----------------->
            <!----------- 이때 삭제된 댓글이 하위 댓글을 가지고 있는 경우가 됨 ----->
            
            <c:if test="${freereplyVO.seqno == 3}">
            <DIV style="border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px;">
              <span style="line-height: 30px; color: #ff9999;">
              <c:choose>
                <c:when test="${freereplyVO.freplyansnum == 0 }"> 
                 <!-- 삭제할 댓글이 일반 댓글로 등록되었던 경우 -->
                  ${freereplyVO.freplycontent}
                </c:when> 
                <c:when test="${freereplyVO.freplyansnum > 0}">
                  <!-- 삭제할 댓글이 답변 댓글로 등록되었던 경우 -->
                  <img src='${pageContext.request.contextPath}/nonuser/free/images/white.jpg' style='padding-left:${freereplyVO.freplyindent * 15}px; opacity: 0.0;'>
                  <img src='${pageContext.request.contextPath}/nonuser/free/images/reply.jpg'>  
                  ${freereplyVO.freplycontent}
                </c:when>
              </c:choose>
              </span>
              <br>
              </DIV>
            </c:if>
            <!------------------ ② 삭제된 댓글의 경우 출력 List 종료 ----------------->
 
             <!---------------------------- 댓글 등록창 시작 (댓글의 댓글(대댓글)이 입력되는 창) ---------------------------->
            <form name="frm_rereply" id="frm_rereply" method="POST" action="${pageContext.request.contextPath}/user/free/rereply.do">
              <DIV id="reply${freereplyVO.freplyno}" style='display: none; padding: 10px 20px;'>    
                <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
                <input type='hidden' name='freplyname' id='freplyname' value=${sessionScope.memname }>
                <input type='hidden' name='freplygrpno' id='freplygrpno' value=${freereplyVO.freplygrpno }>
                <input type='hidden' name='freplyindent' id='freplyindent' value=${freereplyVO.freplyindent }>
                <input type='hidden' name='freplyansnum' id='freplyansnum' value=${freereplyVO.freplyansnum }>
                <input type='hidden' name='freeno' id='freeno' value= ${freereplyVO.freeno }>
                <input type='hidden' name='freplyno' id='freplyno' value= ${freereplyVO.freplyno }>
                <input type='hidden' name='nowPage' id='nowPage' value= '${freeVO.nowPage}' >

                <textarea name= "freplycontent" id="freplycontent" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 88%; height: 76px; margin-left: 10px;"></textarea>
                <button type="submit" style="width: 7%; float: right; height: 80px;">등록</button>
              </DIV>
            </form>
            <!---------------------------- 댓글 등록창 종료 (댓글의 댓글(대댓글)이 입력되는 창) ---------------------------->
            
          </c:forEach>
        </DIV>
        <!---------------------------------------------------------------------------------------------------------------------------> 
         <!--                                                   댓글 목록 종료                                                                      -->   
         <!--------------------------------------------------------------------------------------------------------------------------->
      
  <!---------------------------- 페이징 처리 시작 ---------------------------->
  <form name="frm_read_paging" id="frm_read_paging" action="./read_paging.do" method="get" style="text-align: center;">
    <input type="hidden" name="freeno" id="freeno" value="${freeVO.freeno }">
    <input type='hidden' name='nowPage' id='nowPage' value= '${freeVO.nowPage}' >
    <DIV class="bottom_menu" style="padding-bottom: 20px; margin: 0px auto; display: table;">
      ${paging }
    </DIV>
  </form>
  <!---------------------------- 페이징 처리 종료 ---------------------------->

  <!---------------------------- 댓글 등록창 시작 (새로운 댓글이 등록되는 창) ---------------------------->
  <FORM name='frm_reply' id='frm_reply' method='POST' action='${pageContext.request.contextPath}/user/free/reply.do' style="text-align: left; width: 90%; margin: 0px auto;">
    <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
    <input type='hidden' name='freplyname' id='freplyname' value=${sessionScope.memname }>
    <input type='hidden' name='freeno' id='freeno' value= ${freeVO.freeno }>
    <input type='hidden' name='nowPage' id='nowPage' value= '${freeVO.nowPage}' >
    
    <textarea name= "freplycontent" id="freplycontent" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 90%; height: 76px;"></textarea>
    <button type="submit" style="width: 7%; height:80px; float: right;">등록</button> 
  </FORM>
  <!---------------------------- 댓글 등록창 종료 (새로운 댓글이 등록되는 창) ---------------------------->
  
  <!---------------------------- 댓글 수정창 시작 (기존 댓글이 수정되는 창) ---------------------------->
  <FORM name='frm_update' id='frm_update' method='POST' action='' style="text-align: left; width: 90%; margin: 0px auto;">
    <input type='hidden' name='freplyno' id='freplyno' value= ''>
    <input type='hidden' name='nowPage' id='nowPage' value= '${freeVO.nowPage}'>
    <input type='hidden' name='freeno' id='freeno' value= '${freeVO.freeno}'>
    
    <textarea name= "freplycontent" id="freplycontent" rows="100" cols="50" style="width: 90%; height: 76px;"></textarea>
    <button type="button" name="update_reply_btn" id="update_reply_btn" onclick="" style="width: 7%; height:80px; float: right;">수정</button> 
  </FORM>
  <!---------------------------- 댓글 수정창 종료 (기존 댓글이 수정되는 창) ---------------------------->
              
  </fieldset>
    
</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>