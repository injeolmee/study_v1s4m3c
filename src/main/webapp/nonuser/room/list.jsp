<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="${pageContext.request.contextPath}/nonuser/room/hidcss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!---------------------------------------------------------------------->

<script type="text/javascript">

function create() {
  $('#panel_create').show();
}


//********************** 스터디룸 글 삭제버튼을 누르면 실행되는 alert ******************
function alert_delete_form(rono){
  $.ajax({
    url: "/study/admin/room/delete.do",
    type: "GET",
    caghe: false,
    dataType: "json",
    data: 'rono='+rono,
    success: function(data) {
      var check = confirm("리뷰 " +data.count+"건이 함께 삭제 됩니다. 삭제 하면 복구 할 수 없습니다. 삭제하시겠습니까?");
      
      if (check == true) { // 글 삭제 확인을 눌렀을 경우
          delete_check(rono);
      } else {                 // 글 삭제 취소를 눌렀을 경우
        alert("취소되었습니다.");
      }
      }
  })  
};

//************************************************************************

  
  
//********************** 스터디룸 글 삭제 버튼 클릭시 이벤트 처리 ******************
  function delete_check(rono) {
    
    var param = "rono=" + rono;
    
    $.ajax({
      url: "/study/admin/room/delete.do",
      type: "POST",
      data: param,
      dataType: "JSON",
      success: function (data) {
        
        if (data.count == 1) {
          alert("글이 삭제되었습니다.");
          document.location.href = "/study/nonuser/room/list.do"
        } else {
          alert("오류가 발생하여 글을 삭제하지 못했습니다. 다시 시도해주십시오.");
        }
      }
    }); 
  }
//************************************************************************

</script>
 
<script type="text/javascript">
</script>
</head>
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
  <DIV class='content' style='width: 80%;'>  
    <form name='frm'  id='frm' action='${pageContext.request.contextPath}/nonuser/room/list.do'>
<!--     <input type='hidden' name='adminno' id='adminno' value='1'> -->
  
    <div style='clear: both; width: 100%;'></div>
    <DIV>  
      <ASIDE style='float: left; font-size: 36px; font-weight: bold;'><IMG src='./images/view.png' height="50px" width="50px" style='vertical-align: middle;'> 스터디룸</ASIDE>
      <ASIDE style='float: right;'>
        <A href="javascript:location.reload();">새로고침</A>  
        <span class='menu_divide' >│</span>
        <A href='${pageContext.request.contextPath}/nonuser/room/list.do'>전체목록</A>  
        <span class='menu_divide' >│</span>
        <c:choose>
          <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A' }">
            <A href='${pageContext.request.contextPath}/admin/room/create.do' >등록</A>
            <span class='menu_divide' >│</span>
          </c:when>
          <c:otherwise></c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${ronalo != '' }">
            <input type='text' name='ronalo' id='ronalo' value='${ronalo }'' placeholder="카페명 또는 위치 검색" >
          </c:when>
          <c:otherwise>
            <input type='text' name='ronalo' id='ronalo' value='' '>
          </c:otherwise>
        </c:choose>
        <button type='submit' class="btn btn-default">검색</button>  
      </ASIDE>
    </DIV>
    </form> 
      
    <div class='menu_line' style='clear: both; width: 100%; border: none;'></div>                
    <div style='position: center; width: 98%; margin: 0px auto;'>
      <c:forEach var="roomVO" items="${list }">
        <div class='div_grid_img' style="width: 27%; height: 300px; margin: 30px 3% 0px 3%; background-color:#ffffff; "> <!-- lavender left: 22px -->
          <c:choose>
            <c:when test="${roomVO.rothumb != ''}">
              <IMG id='rothumb' src='${pageContext.request.contextPath}/admin/room/storage/${roomVO.rothumb}' style='width: 100%; height: 200px; margin: 0px 0px 6px 0px;'> <!-- 이미지 파일명 출력 -->
            </c:when>
            <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->
              <IMG id='thumb' src='${pageContext.request.contextPath}/admin/room/images/none1.jpg' style='width: 100%; height: 200px; margin: 0px 0px 6px 0px;'>
            </c:otherwise>
          </c:choose>
        <div> 
          
          <A href="${pageContext.request.contextPath}/nonuser/room/read.do?rono=${roomVO.rono}">
            <span style='font-size: 20px; font-weight: 550; margin: 0px 0px 0px 3px;'>${roomVO.roname}</span>
          </A>

        <c:choose>
          <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A' }">
            <span style='font-size: 12px; float: right; margin: 0px 10px 0px 10px;'>
              <A href="javascript: alert_delete_form(${roomVO.rono})">
                <IMG src='./images/delete.png' style='width: 15px; height: 15px;'>
              </A>
            </span>
            <span style='font-size: 12px; float: right;'>
              <A href="${pageContext.request.contextPath}/admin/room/update.do?rono=${roomVO.rono}">
                <IMG src='./images/edit.png' style='width: 15px; height: 15px;'>
              </A>
            </span>
          </c:when>
          <c:otherwise></c:otherwise>
        </c:choose>
          
        </div>  
        <div style='border-bottom: dotted 1px #777777; margin: 4px 0px 6px 0px;'></div>
        <div style='margin-bottom: 6px;'> 
          <span style='font-size: 16px; font-weight: 550; line-height: 20px;'>전화번호&nbsp;&nbsp;</span>  
          <span style='font-size: 16px; line-height: 20px;'>${roomVO.rotel}</span>
        </div>
        <span style='font-size: 16px; font-weight: 550; margin: 0px 0px 20px 0px;'>위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;치&nbsp;</span>
        <span style='font-size: 16px; margin: 0px 0px 20px 0px;'>${roomVO.rolocation}</span>
        </div>
      </c:forEach>
    </div>     
      
    <DIV class='bottom_menu' >${paging }</DIV>  
  </DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>