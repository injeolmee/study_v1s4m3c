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
<link href="${pageContext.request.contextPath}/user/shared/gnacss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!---------------------------------------------------------------------->
<script type="text/javascript">
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
  function reply_update(shreplyno) { 
  
    $('#frm_reply').hide();     
    $('#frm_update').show();
    
    var params  = "shreplyno=" +  shreplyno;
    // alert("params: " + params);
  
    $.ajax({
      url : "/study/user/shared/reply_update.do",
      type: "GET",
      data: params,
      dataType: 'JSON',
      success: function (data) {
        var reply_update = $('#frm_update');
        var shreplyno = data.shreplyno;
        var shreplycontent = data.shreplycontent;
      
        $("#shreplyno", reply_update).val(shreplyno);
        $("#shreplycontent", reply_update).val(shreplycontent);
      }
    }); 
  }
  //*******************************************************************

  //********************* 댓글 수정을 위한 처리 *********************
  function update_reply_post(reply_data) {
  
    $.ajax({
      url: "/study/user/shared/reply_update.do",
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
  function alert_delete_form(shreplyno, seqno){
  
    // alert("shreplyno: " + shreplyno + "seqno: " + seqno);
  
    var check = confirm("댓글을 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");
  
    if (check == true) { // 댓글 삭제 확인을 눌렀을 경우
      // alert("seqno_check: " + seqno);
      // alert("sreplyno: " + shreplyno);
  
      if (seqno == 0) { // 부모 댓글일 경우 => 부모댓글 처리로 이동!
        // alert("부모 댓글 처리를 시작합니다.");
        update_parent(shreplyno);
      } 
  
      if (seqno == 1) { // 하위 댓글이 전혀 없는 마지막 자식 댓글일 경우 => 자식댓글 처리로 이동!
        // alert("일반 댓글 처리를 시작합니다.");
        delete_reply(shreplyno);
      }

    } else {                 // 댓글 삭제 취소를 눌렀을 경우
      alert("취소되었습니다.");
    }
  };
  //********************************************************************

  //*********************** 댓글 일반 삭제 처리 ***********************
  function delete_reply(shreplyno) {
  
    var nowPage = ${sharedVO.nowPage};
    var param = "shreplyno=" + shreplyno + "&nowPage=" + nowPage;
  
    $.ajax({
      url: "/study/user/shared/reply_delete.do",
      type: "POST",
      data: param,
      dataType: "JSON",
      success: function(data) {
      
        if (data.count == 1) { // 댓글 삭제 처리가 성공했을 경우
          alert("댓글이 삭제되었습니다.");
          window.location.reload();
        } else { // 댓글 삭제 처리가 실패했을 경우
          alert("오류가 발생하여 댓글 삭제에 실패하였습니다. 다시 시도해주십시오.");
        }
      } 
    })
  };
  //***********************************************************************

  //***** seqno가 0인 부모 댓글에 대한 delete를 update로 처리********
  function update_parent(shreplyno) {
  
    var nowPage = ${sharedVO.nowPage}
    var param = "shreplyno=" + shreplyno + "&nowPage=" + nowPage;
  
    $.ajax({
      url: "/study/user/shared/update_parent.do",
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
  function delete_check(sharedno) {
  
    var param = "sharedno=" + sharedno;
    var check = confirm("게시글을 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");
  
    if (check == true) {
      $.ajax({
        url: "/study/user/shared/delete.do",
        type: "POST",
        data: param,
        dataType: "JSON",
        success: function (data) {
        
          if (data.count == 1) {
            alert("게시글이 삭제되었습니다.");
            document.location.href = "/study/user/shared/list.do";
          } else {
            alert("오류가 발생하여 게시글을 삭제하지 못했습니다. 다시 시도해주십시오.");
          }
        }
      });
    } else {
    
    }
  };
  //************************************************************************

</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container" style='margin-bottom: 5%;'>
<DIV class='content' style='width: 50%; margin: 0px auto;'>  

  <FORM name='frm_like' id='frm_like' method='POST' action=''>
    <input type='hidden' name='sharedno' id='sharedno' value=''>
  </FORM>

  <FORM name='frm_read' id='frm_read' method='GET' action='./read.do' style="text-align: left; width: 100%;">
    <input type="hidden" name="sharedno" id="sharedno" value=${sharedVO.sharedno }>        
          
    <!--------------------------------------- ASIDE 메뉴 시작 --------------------------------------->   
    <DIV style="width: 105%; padding-bottom: 50px;">
    <c:set var ="memberno" value="${sessionScope.memberno }"/>
  
      <ASIDE style='float: left; '>
        <c:choose>
          <c:when test="${fn:contains(sharedVO.sharedno, sharedVO.post_num)}">
            <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/read.do?sharedno=${sharedVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
          </c:when>
          <c:when test="${fn:contains(sharedVO.sharedno, sharedVO.pre_num)}">
            <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/read.do?sharedno=${sharedVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
          </c:when>
          <c:otherwise>
            <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/read.do?sharedno=${sharedVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
            <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/read.do?sharedno=${sharedVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
          </c:otherwise>
        </c:choose> 
      </ASIDE> 
    
      <ASIDE style='float: right;'>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/list.do?word=${param.word}&search=${param.search}'">목록형</button>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/list_grid.do?word=${param.word}&search=${param.search}'">앨범형</button>
        <!-- ⓐ 접속한 회원이 작성자일 경우 수정 / 삭제가 보임 -->
        <c:if test="${fn:contains(memberno, sharedVO.memberno)}">
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/update.do?sharedno=${sharedVO.sharedno}&word=${param.word}&search=${param.search}'">수정</button>
          <button type="button" class="btn btn-default" onclick="delete_check(${sharedVO.sharedno })">삭제</button>
        </c:if>
        <!----------------------------------------------------------->
        <!------- ⓑ 관리자일 경우 수정 / 삭제가 모두 보임 ------->
        <c:if test="${sessionScope.adminno != null }">
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/user/shared/update.do?sharedno=${sharedVO.sharedno}&word=${param.word}&search=${param.search}'">수정</button>
          <button type="button" class="btn btn-default" onclick="delete_check(${sharedVO.sharedno })">삭제</button>
        </c:if>
        <!----------------------------------------------------------->
      </ASIDE>    
      
    </DIV>
    <!--------------------------------------- ASIDE 메뉴 종료 --------------------------------------->
    
    <fieldset class="fieldset_basic" style="border: solid 2px #dad8d7; font-size: 15px; width: 100%; margin: 0px auto;">
      <ul style="margin: 0px auto;">
    
      <!----------------------------------- shared 게시판의 게시글 read 시작 ----------------------------------->
        <li class="li_none">
          <div style="line-height: 0px">
            <span style="font-weight: bold; margin-right: 20px;">${sharedVO.sharedtitle }</span>
          </div>
          <div style="text-align: right; font-size: 0.8em; line-height: 0px;"> ${sharedVO.shareddate } | 조회수 ${sharedVO.sharedcnt}</div>
          <HR style="color: #EEEEEE;">
        </li>
          
        <li class="li_none">
          <div style="width: 100%; margin-top: 3px; margin-bottom: 10px; font-weight: bold;">
            <IMG src="${pageContext.request.contextPath}/user/shared/images/user_gray.png"> ${sharedVO.sharedname } 
            <div style="float: right; font-size: 0.8em; ">
              <c:if test="${sharedVO.sharedsize > 0}">
                파일: <A href='${pageContext.request.contextPath}/download?dir=/user/shared/storage&filename=${sharedVO.sharedfstor}&downname=${sharedVO.sharedfile}'>${sharedVO.sharedfile}</A> 
                (${sharedVO.sharedsizeLabel})
              </c:if>
            </div>
          </div>     
        </li>
        
        <li class="li_none">
          <div style="width: 100%; margin-top: 30px; margin-bottom: 30px;">
            <!-- YOUTUBE가 등록되어 있다면 같이 출력 -->
            <c:if test="${sharedVO.sharedyoutube != null }">
              ${sharedVO.sharedyoutube } <br><br>
            </c:if>
            <!----------------------------------------------->
            ${sharedVO.sharedcontent }
          </div>
        </li>
              
        <li class="li_none">
          <div style="width: 100%; margin-top: 5px; line-height: 0;">
          </div>
          <HR style="color: #EEEEEE;">
        </li>
        <!----------------------------------- shared 게시판의 게시글 read 종료 ----------------------------------->
        
  </FORM>
      
      <!------------------------------------------------------------------------------------------------------------->
      <!--                                             댓글 목록창 시작                                                         -->
      <!------------------------------------------------------------------------------------------------------------->
      <DIV style="padding: 0px 15px 15px 15px;"> 
        <c:forEach var="sharedreplyVO" items="${list_reply }">
          
        <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 시작 ----------->
          <c:if test="${sharedreplyVO.seqno != 3}">
            <DIV style="border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px;">
            
              <span style="font-weight: bold; line-height: 30px;">
                <c:choose>
                  <c:when test="${sharedreplyVO.shreplyansnum == 0 }"> 
                  </c:when>
                  <c:when test="${sharedreplyVO.shreplyansnum > 0}">
                    <img src='${pageContext.request.contextPath}/user/shared/images/white.jpg' style='padding-left:${sharedreplyVO.shreplyindent * 15}px; opacity: 0.0;'>
                    <img src='${pageContext.request.contextPath}/user/shared/images/reply.jpg'>
                  </c:when>
                </c:choose>
                <IMG src="${pageContext.request.contextPath}/user/shared/images/user.png" style="padding-right: 1%;">
                ${sharedreplyVO.shreplyname }
              </span>
            
              <span style="font-size: 0.7em; color: #999999; padding-left: 2%;">
                ${sharedreplyVO.shreplydate } 
              </span>
            
              <span style="font-size: 0.8em; padding-left: 2%;">
                <IMG src="${pageContext.request.contextPath}/user/shared/images/rereply.png"><A href="javascript:reply_start(${sharedreplyVO.shreplyno });" id ='reply_panel${sharedreplyVO.shreplyno}'>답변</A>
              </span>
            
              <div style='float: right;'>
                <!--------- ⓐ 댓글의 작성자일 경우 수정 / 삭제 출력 ----------------->
                <c:if test="${fn:contains(memberno, sharedreplyVO.memberno)}">
                  <a href='javascript:reply_update("+${sharedreplyVO.shreplyno }+")' style='font-size: 0.8em; color: #999999;' >수정</a>
                  <a href='javascript:alert_delete_form(${sharedreplyVO.shreplyno },${sharedreplyVO.seqno })' style='font-size: 0.8em; color: #999999;'> | 삭제</a>
                </c:if>
                <!------------------------------------------------------------------------>
                <!--------- ⓑ 관리자일 경우 무조건 수정 / 삭제 출력 ----------------->
                <c:if test="${sessionScope.adminno ne null}">
                  <a href='javascript:reply_update("+${sharedreplyVO.shreplyno }+")' style='font-size: 0.8em; color: #999999;' >수정</a>
                  <a href='javascript:alert_delete_form(${sharedreplyVO.shreplyno },${sharedreplyVO.seqno })' style='font-size: 0.8em; color: #999999;'> | 삭제</a>
                </c:if>
                <!----------------------------------------------------------------------->
                
              </div> <br>
            
              <c:choose>
                <c:when test="${sharedreplyVO.shreplyansnum == 0 }">
                  <span style="margin-bottom: 10px;">
                    ${sharedreplyVO.shreplycontent}
                  </span>
                </c:when>
                <c:when test="${sharedreplyVO.shreplyansnum > 0}">
                  <DIV style="padding-left: 5%;">
                    <span style="padding-left:${sharedreplyVO.shreplyindent * 2.5}%; margin-bottom: 10px;">
                      ${sharedreplyVO.shreplycontent}
                    </span>
                  </DIV>
                </c:when>
              </c:choose>
           </DIV>
         </c:if>
         <!----------- ① 삭제되지 않은 일반 댓글의 경우 출력 List 종료 ----------->
              
         <!------------------ ② 삭제된 댓글의 경우 출력 List 시작 ----------------->
         <!--------- 이때 삭제된 댓글이 하위 댓글을 가지고 있는 경우가 됨 -------->
          <c:if test="${sharedreplyVO.seqno == 3}">
            <DIV style="border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px;">
              <span style="line-height: 30px; color: #ff9999;">
                <c:choose>
                  <c:when test="${sharedreplyVO.shreplyansnum == 0 }"> 
                  <!-- 삭제할 댓글이 일반 댓글로 등록되었던 경우 -->
                    ${sharedreplyVO.shreplycontent}
                  </c:when> 
                  <c:when test="${sharedreplyVO.shreplyansnum > 0}">
                  <!-- 삭제할 댓글이 답변 댓글로 등록되었던 경우 -->
                    <img src='${pageContext.request.contextPath}/user/shared/images/white.jpg' style='padding-left:${sharedreplyVO.shreplyindent * 15}px; opacity: 0.0;'>
                    <img src='${pageContext.request.contextPath}/user/shared/images/reply.jpg'>  
                    ${sharedreplyVO.shreplycontent}
                  </c:when>
                </c:choose>
              </span><br>
            </DIV>
          </c:if>
          <!------------------ ② 삭제된 댓글의 경우 출력 List 종료 ----------------->

          <!---------------------------- 댓글 등록창 시작 (댓글의 댓글(대댓글)이 입력되는 창) ---------------------------->
          <form name="frm_rereply" id="frm_rereply" method="POST" action="./rereply.do">
            <DIV id="reply${sharedreplyVO.shreplyno}" style='display: none; padding: 10px 20px;'>
              <c:if test="${sessionScope.memberno ne null}">
                <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
                <input type='hidden' name='shreplyname' id='shreplyname' value=${sessionScope.memname }>
              </c:if>    
              <c:if test="${sessionScope.adminno ne null}">
                <input type='hidden' name='adminno' id='adminno' value=${sessionScope.adminno }>
                <input type='hidden' name='shreplyname' id='shreplyname' value=${sessionScope.admname }>
              </c:if>    
              <input type='hidden' name='shreplygrpno' id='shreplygrpno' value=${sharedreplyVO.shreplygrpno }>
              <input type='hidden' name='shreplyindent' id='shreplyindent' value=${sharedreplyVO.shreplyindent }>
              <input type='hidden' name='shreplyansnum' id='shreplyansnum' value=${sharedreplyVO.shreplyansnum }>
              <input type='hidden' name='sharedno' id='sharedno' value= ${sharedreplyVO.sharedno }>
              <input type='hidden' name='shreplyno' id='shreplyno' value= ${sharedreplyVO.shreplyno }>
              <input type='hidden' name='nowPage' id='nowPage' value= ${sharedreplyVO.nowPage }>

              <textarea name= "shreplycontent" id="shreplycontent" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 88%; height: 76px; margin-left: 10px;"></textarea>
              <button type="submit" style="width: 7%; float: right; height: 80px;">등록</button>
            </DIV>
          </form>
          <!---------------------------- 댓글 등록창 종료 (댓글의 댓글(대댓글)이 입력되는 창) ---------------------------->

        </c:forEach>
      </DIV>
      <!------------------------------------------------------------------------------------------------------------->
      <!--                                             댓글 목록창 종료                                                         -->
      <!------------------------------------------------------------------------------------------------------------->
      </ul>
      
      <!---------------------------- 페이징 처리 시작 ---------------------------->
      <form name="frm_read_paging" id="frm_read_paging" action="./read_paging.do" method="get" style="text-align: center;">
        <input type="hidden" name="sharedno" id="sharedno" value="${sharedVO.sharedno }">
        <DIV class="bottom_menu" style="padding-bottom: 20px; margin: 0px auto; display: table;">
          ${paging }
        </DIV>
      </form>
      <!---------------------------- 페이징 처리 종료 ---------------------------->

      <!---------------------------- 댓글 등록창 시작 (새로운 댓글이 등록되는 창) ---------------------------->
      <FORM name='frm_reply' id='frm_reply' method='POST' action='./reply.do' style="text-align: left; width: 90%; margin: 0px auto;">
        <c:if test="${sessionScope.memberno ne null}">
          <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
          <input type='hidden' name='shreplyname' id='shreplyname' value=${sessionScope.memname }>
        </c:if>    
        <c:if test="${sessionScope.adminno ne null}">
          <input type='hidden' name='adminno' id='adminno' value=${sessionScope.adminno }>
          <input type='hidden' name='shreplyname' id='shreplyname' value=${sessionScope.admname }>
        </c:if>
        <input type='hidden' name='sharedno' id='sharedno' value= ${sharedVO.sharedno }>
    
        <textarea name= "shreplycontent" id="shreplycontent" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 90%; height: 76px;"></textarea>
        <button type="submit" style="width: 7%; height:80px; float: right;">등록</button> 
      </FORM>
      <!---------------------------- 댓글 등록창 종료 (새로운 댓글이 등록되는 창) ---------------------------->
   
      <!---------------------------- 댓글 수정창 시작 (기존 댓글이 수정되는 창) ---------------------------->
      <FORM name='frm_update' id='frm_update' method='POST' action='' style="text-align: left; width: 90%; margin: 0px auto;">
        <input type='hidden' name='shreplyno' id='shreplyno' value= ''>
        <input type='hidden' name='nowPage' id='nowPage' value= '${sharedVO.nowPage}'>
        <input type='hidden' name='sharedno' id='sharedno' value= '${sharedVO.sharedno}'>
    
        <textarea name= "shreplycontent" id="shreplycontent" rows="100" cols="50" style="width: 90%; height: 76px;"></textarea>
        <button type="button" name="update_reply_btn" id="update_reply_btn" onclick="" style="width: 7%; height:80px; float: right;">수정</button> 
      </FORM>
      <!---------------------------- 댓글 수정창 종료 (기존 댓글이 수정되는 창) ---------------------------->

  </fieldset>
  
</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>