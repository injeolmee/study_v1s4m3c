<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>CONTEST</title>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
  
});

$(document).ready(function(){
  $("#myBtn").click(function(){
      $("#myModal").modal();
  });
});


function deleteOne(conNo) {
  var frm_delete = $('#frm_delete');
  $('#conNo', frm_delete).val(conNo);
  $('#target_del').html(conNo + "번 게시글을 삭제합니다.");
  $.ajax({
    url: "/admin/contest/delete.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'conNo=' + conNo,
    success: function(data) {
      $('#conNo', frm_delete).val(conNo);
      $("#myModal").modal();
    }
  });
}
/* function update(conNo) {
  var frm_update = $('#frm_update');
  $.ajax({
    url: "./update.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'conNo=' + conNo,
    success: function(data) {
      $("#conHost", frm_update).val(data.conHost);
      $('#conHost').html(data.conHost);
      $('#conTitle', frm_update).val(data.conTitle);
      $('#conTitle').html(data.conTitle);
      $('#conStart', frm_update).val(data.conStart);
      $('#conStart').html(data.conStart);
      $('#conEnd', frm_update).val(data.conEnd);
      $('#conEnd').html(data.conEnd);
      $('#conRemain_e', frm_update).val(data.conRemain_e);
      $('#conRemain_e').html(data.conRemain_e);
      $('#conRemain_s', frm_update).val(data.conRemain_s);
      $('#conRemain_s').html(data.conRemain_s);
      $('#conCont', frm_update).val(data.conCont);
      $('#conCont').html(data.conCont);
      $('#conUrl', frm_update).val(data.conUrl);
      $('#conUrl').html(data.conUrl);
      $('#conYou', frm_update).val(data.conYou);
      $('#conYou').html(data.conYou);
      $('#conWord', frm_update).val(data.conWord);
      $('#conWord').html(data.conWord);
      $("myModal").modal();
    }
  })
}

function resize(obj) {
  obj.style.height = "1px";
  obj.style.height = (20+obj.scrollHeight) + "px";
} */

</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>
  <DIV style="width: 920px; padding: 20px 0 40px 0; margin: 0px auto;">
    <DIV style="float: left;">
      <IMG src="/study/nonuser/contest/images/cont_img01.png" alt="인기 공모전">
    </DIV>
    <DIV style="clear: both; padding-top: 10px !important;"></DIV>
    <DIV>
    <c:forEach var="contestVO" items="${list_by_count }">
      <DIV style="float: left; width: 200px; margin-right: 30px;">
        <DIV><IMG src="/study/nonuser/contest/images/grid_top.png" style="width: 200px;"></DIV>
     <!-- <DIV style="width: 100%; background: url(./images/cont_img21.png) repeat-y; padding: 0 6px 0 6px;"> -->
            <DIV style="width: 200px; border: 1px solid #dfdfdf;">
              <c:choose>
                <c:when test="${contestVO.conThumb != ''}">
                  <img id= 'conThumb' src= '/study/admin/contest/storage/${contestVO.conThumb }' style= 'width: 100%; height: 123px; margin: 0px auto;'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <%-- <c:when test="${contestVO.conFile1 != ''}">
                ${contestVO.conFile1 } <!-- 일반 파일명 출력 -->
                </c:when> --%>
                <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->                
                  <img id= 'conThumb' src= '/study/nonuser/contest/images/none1.jpg' style= 'width: 100%; height: 123px; margin: 0px auto;'>
                </c:otherwise>
              </c:choose>
            </DIV>
            <DIV style="width: 200px; padding-top: 15px !important; padding-left: 5px !important;">
              <DIV style="color: #666666; font-size: 12px; line-height: 12px; font-weight: bold; height: 30px;">
                <a href="/study/nonuser/contest/read.do?conNo=${contestVO.conNo }">${contestVO.conTitle}</a>
              </DIV>
              <DIV style="color: #888888; font-size: 11px; line-height: 18px;">
                          기간<br>
              ${contestVO.conStart } ~ ${contestVO.conEnd }
              </DIV>
            </DIV>
          <!-- </DIV> -->
        <DIV><IMG src="/study/nonuser/contest/images/grid_bottom.png" style="width: 200px;"></DIV>
      </DIV>
    </c:forEach>
    </DIV>
    <DIV style="clear: both; padding-top: 20px !important;"></DIV>
    <DIV style="float: left;">
      <IMG src="/study/nonuser/contest/images/cont_img02.png" alt="추천 공모전">
    </DIV>
    <DIV style="clear: both; padding-top: 10px !important;"></DIV>
    <DIV>
    <DIV>
    <c:forEach var="contestVO" items="${list_by_good }">
      <DIV style="float: left; width: 200px; margin-right: 30px;">
        <DIV><IMG src="/study/nonuser/contest/images/grid_top.png" style="width: 200px;"></DIV>
            <DIV style="width: 200px; border: 1px solid #dfdfdf;">
              <c:choose>
                <c:when test="${contestVO.conThumb != ''}">
                  <img id= 'conThumb' src= '/study/admin/contest/storage/${contestVO.conThumb }' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:when test="${contestVO.conFile1 != ''}">
                ${contestVO.conFile1 } <!-- 일반 파일명 출력 -->
                </c:when>
                <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->                
                  <img id= 'conThumb' src= '/study/admin/contest/images/none1.jpg' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;;'>
                </c:otherwise>
              </c:choose>
            </DIV>
            <DIV style="width: 200px; padding-top: 15px !important; padding-left: 5px !important;">
              <DIV style="color: #666666; font-size: 12px; line-height: 12px; font-weight: bold; height: 30px;">
                <a href="/study/nonuser/contest/read.do?conNo=${contestVO.conNo }">${contestVO.conTitle}</a>
              </DIV>
              <DIV style="color: #888888; font-size: 11px; line-height: 18px;">
                          기간<br>
              ${contestVO.conStart } ~ ${contestVO.conEnd }
              </DIV>
            </DIV>
        <DIV><IMG src="/study/nonuser/contest/images/grid_bottom.png" style="width: 200px;"></DIV>
      </DIV>
    </c:forEach>
      </DIV>
    </DIV>
    <DIV style="clear: both; padding-top: 20px !important;"></DIV>
    <!-- <input type="date"> -->
    <DIV style="width: 920px; padding: 20px 0 40px 0; margin: 0px auto;">
    <DIV style="float: left;">
      <IMG src="./images/cont_img03.png" alt="전체 공모전">
    </DIV>
    <div style="float: right;">
      <c:choose>
        <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A'}">
        <A href="/study/admin/contest/create.do" style="padding-right: 40px;">
          <img src="/study/nonuser/contest/images/create.png" alt="등록하기">
        </A>
        </c:when>
        <c:otherwise></c:otherwise>
      </c:choose>
    </div>
    <DIV style="clear: both; padding-top: 10px !important;"></DIV>
    <DIV style="width: 865px; height: 30px; background-color: #55398C; padding: 0 10px 0 10px;">
      <DIV style="float: left; padding-top: 5px !important;">
        <SPAN style="color: #ffffff; font-size: 12px; line-height: 20px;">전체</SPAN>
        <SPAN style="color: #cbdb2a; font-size: 12px; line-height: 20px;">[${total_count }]</SPAN>
        <SPAN style="color: #ffffff; font-size: 12px; line-height: 20px;"> / 진행중</SPAN>
        <SPAN style="color: #cbdb2a; font-size: 12px; line-height: 20px;">[${day_count }]</SPAN>
      </DIV>
    </DIV>
    
    <table style="width: 885px; border-top: 1px solid #55398C;">
      <colgroup>
        <col width = "10%"></col>
        <col width = "60%"></col>
        <col width = "7%"></col>
        <col width = "7%"></col>
        <col width = "6%"></col>
        <col width = "10%"></col>
      </colgroup>
      <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
        <%-- <c:set var="remain"><fmt:formatDate value="${end} - ${today }" pattern="yyyyMMdd" /></c:set> --%>
        <c:forEach var="contestVO" items="${list }">
          <tr style="background-color: #fcfcfc; border-bottom: 1px solid #dedede;">
            <td style="vertical-align: middle; text-align: center; padding: 15px 0 15px 0;">
              <c:choose>
                <c:when test="${contestVO.conRemain_e < 0}">
                  <IMG src="/study/nonuser/contest/images/apply_end.png">
                </c:when>
                <c:when test="${contestVO.conRemain_s > 0}">
                  <IMG src="/study/nonuser/contest/images/apply_before.png">
                </c:when>
                <c:otherwise>
                  <IMG src="/study/nonuser/contest/images/apply.png">
                </c:otherwise>
              </c:choose>
            </td>
            <td style="padding-left: 10px !important;">
              <div style="text-align: left !important; font-weight: bold !important; padding-bottom: 5px !important;">
                <a href="/study/nonuser/contest/read.do?conNo=${contestVO.conNo }">${contestVO.conTitle}</a>
              </div>
              <div style="text-align: left; color: #888888; font-size: 11px; line-height: 18px;">
                          주최 : ${contestVO.conHost } / 
                          접수 기간 : ${contestVO.conStart} ~ ${contestVO.conEnd }
              </div>  
            </td>
            <td style="text-align: center;">
              <span style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">
              <c:choose>
                <c:when test="${contestVO.conRemain_e < 0}">
                                 마감됨
                </c:when>
                <c:when test="${contestVO.conRemain_e == 0}">
                  <div style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">D-DAY</div>
                </c:when>
                <c:when test="${contestVO.conRemain_s > 0}">
                  <div style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">접수예정</div>
                </c:when>
                <c:otherwise>
                  <div style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">D-DAY</div>
                  <div style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">${contestVO.conRemain_e }일전</div>
                </c:otherwise>
              </c:choose>
              </span>
            </td>
            <td style="text-align: center;">
              <div style="color: #888888; font-size: 11px; line-height: 18px;">조회수</div>
              <div style="color: #888888; font-size: 11px; line-height: 18px;">${contestVO.conCnt}</div>
            </td>
            <td style="text-align: center;">
              <div style="color: #888888; font-size: 11px; line-height: 18px;">추천수</div>
              <div style="color: #888888; font-size: 11px; line-height: 18px;">${contestVO.conGood}</div>
            </td>
            <c:choose>
              <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A'}">
              <td style="text-align: center;">
                <button onclick="location.href='/study/admin/contest/update.do?conNo=${contestVO.conNo}'">
                  <IMG src='./images/edit.png' style='width: 15px; height: 15px; line-height: 18px;'>
                </button>
                <A href="javascript: deleteOne(${contestVO.conNo});" type="button" class="btn btn-default btn-sm" id="myBtn">
                  <IMG src='/study/nonuser/contest/images/trash.png' style='width: 15px; height: 15px; line-height: 18px;'>
                </A>
              </td>
              </c:when>
              <c:otherwise></c:otherwise>
            </c:choose>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class= "bottom_menu">${paging }</div>
    </DIV>
    
  <div class="modal fade" id="myModal" role="dialog" style="font-size: 18px; display: none;">
  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title" style="font-weight: bold; text-align: center; padding-left: 40px;">채용정보 게시글 삭제</h4>
    </div>
    <div class="modal-body" style='width: 95%; height: 260px; text-align: center; margin: 0px auto;'>
      <FORM name = 'frm_delete' id = 'frm_delete' method="POST" action="/study/admin/contest/delete.do">
        <input type="hidden" name="conNo" id="conNo" value=''>
        <DIV class = "form-group form-group-sm">
          <p id="target_del" style="color: black; font-size: 25px;"></p>
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
    
  </DIV>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>