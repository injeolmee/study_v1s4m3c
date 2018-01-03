<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<title>Job Information</title>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
  
});

/* function deleteOne(jobNo) {
  var msg = "채용정보 게시글을 삭제합니다. <br><br>";
  msg += "삭제하면 복구 할 수 없습니다.<br><br>";
  msg += "계속 삭제하시겠습니까???????";
  
  var panel="";
  panel += "<DIV id='panel' class='popup1' style='height: 250px;'>";
  panel += msg;
  
  var fmr_delete = $('#frm_delete');
  $('#jobNo', frm_delete).val(jobNo);
  
  panel += "<br>[<A href='javascript: frm_delete.submit();' class='menu_link' >삭제 진행</A>]";
  panel += "[<A href=\"javascript: $('#main_panel').hide()\" class='menu_link' >CLOSE</A>]";
  panel += "</DIV>";
  
  $('#main_panel').html(panel);      
  $('#main_panel').show();
} */

function deleteOne(jobNo) {
  var frm_delete = $('#frm_delete');
  $.ajax({
    url: "/study/admin/jobinfo/delete.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'jobNo=' + jobNo,
    success: function(data) {
      $('#jobNo', frm_delete).val(jobNo);
      $("#myModal").modal();
    }
  });
}

/* $(document).ready(function(){
  $("#myBtn").click(function(){
      $("#myModal").modal();
  });
});


function deleteOne(conNo) {
  var frm_delete = $('#frm_delete');
  $.ajax({
    url: "./delete.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'conNo=' + conNo,
    success: function(data) {
      $("#myModal_delete").modal();
    }
  });
} */
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
  <DIV style="width: 73%; margin: 0px auto;">
    <h4 style="clear: both; margin-bottom: 6px; padding-left: 13px; background: url(./images/bul_donut_blue.png) no-repeat left 3px; color: #0075B0; line-height: 15px;">인기 채용정보</h4>
  </DIV>
  <DIV style="clear: both; padding-top: 10px !important;"></DIV>
  <DIV style="width: 73%; margin: 0px auto;">
  <c:forEach var="jobVO" items="${list_by_count }">
    <DIV style="float: left; width: 200px; margin-right: 30px;">
   <!-- <DIV style="width: 100%; background: url(./images/cont_img21.png) repeat-y; padding: 0 6px 0 6px;"> -->
          <DIV style="width: 200px; border: 1px solid #dfdfdf;">
            <c:choose>
              <c:when test="${jobVO.jobThumb != ''}">
                <img id= 'jobThumb' src= '/study/nonuser/jobinfo/storage/${jobVO.jobThumb }' style= 'width: 100%; height: 123px; margin: 0px auto;'> <!-- 이미지 파일명 출력 -->
              </c:when>
              <%-- <c:when test="${contestVO.conFile1 != ''}">
              ${contestVO.conFile1 } <!-- 일반 파일명 출력 -->
              </c:when> --%>
              <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->                
                <img id= 'jobThumb' src= '/study/nonuser/jobinfo/images/none1.jpg' style= 'width: 100%; height: 123px; margin: 0px auto;'>
              </c:otherwise>
            </c:choose>
          </DIV>
          <DIV style="width: 200px; padding-top: 15px !important; padding-left: 5px !important;">
            <DIV style="color: #666666; font-size: 12px; line-height: 12px; font-weight: bold; height: 30px;">
              <a href="/study/nonuser/jobinfo/read.do?jobNo=${jobVO.jobNo }">${jobVO.comName}</a>
            </DIV>
            <DIV style="color: #888888; font-size: 11px; line-height: 18px;">
                        기간<br>
            ${jobVO.jobStart } ~ ${jobVO.jobEnd }
            </DIV>
          </DIV>
        <!-- </DIV> -->
      <DIV><IMG src="/study/nonuser/jobinfo/images/grid_bottom.png" style="width: 200px;"></DIV>
    </DIV>
  </c:forEach>
  </DIV>
  <DIV style="clear: both; padding-top: 20px !important;"></DIV>
  
  <DIV style="width: 920px; padding: 20px 0 40px 0; margin: 0px auto; text-align: center;">
    <form name="frm" id="frm" method="get" action="/study/nonuser/jobinfo/list_all_jobinfo.do">
    <DIV style="float: left;">
      <IMG src="/study/nonuser/jobinfo/images/jobinfo.png" alt="채용정보">
    </DIV>
    <div style="float: right; width: 29%;">
      <c:choose>
        <c:when test="${jobWord != '' }">
          <input type="text" name="jobWord" id="jobWord" value="${jobWord }" style="width: 40%; margin-bottom: 0px;">
        </c:when>
        <c:otherwise>
          <input type="text" name="jobWord" id="jobWord" value="" style="width: 40%; margin-bottom: 0px;">
        </c:otherwise>
      </c:choose>
      <button type="submit" class="btn btn-primary btn-sm">검색</button>
      <c:choose>
        <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A'}">
        <A href="/study/admin/jobinfo/create.do">
          <img src="/study/admin/jobinfo/images/create.png" alt="등록하기" style="height: 30px;">
        </A>
        </c:when>
        <c:otherwise></c:otherwise>
      </c:choose>
    </div>
    </form>
    <DIV class="cb t10"></DIV>
    <table summary="채용정보" class="table3">
      <colgroup>
        <col width="10%">
        <col width="58%">
        <col width="10%">
        <col width="10%">
        <col width="12%">
      </colgroup>  
      <thead style="vertical-align: middle; border-color: inherit; background-color: #6699ff; color: #FFFFFF; line-height: 40px;">
        <tr>
          <th scope="col" style="text-align: center;">현황</th>
          <th scope="col" style="text-align: center;">채용정보명</th>
          <th scope="col" style="text-align: center;">회사</th>
          <th scope="col" style="text-align: center;">조회수</th>
          <c:choose>
            <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A'}">
              <th scope="col" style="text-align: center;">수정/삭제</th>
            </c:when>
            <c:otherwise></c:otherwise>
          </c:choose>
        </tr>
      </thead>
      <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
        <%-- <c:set var="remain"><fmt:formatDate value="${end} - ${today }" pattern="yyyyMMdd" /></c:set> --%>
        <c:forEach var="jobVO" items="${list }">
          <tr style="background-color: #fcfcfc; border-bottom: 1px solid #dedede;">
            <td style="vertical-align: middle; text-align: center; padding: 15px 0 15px 0;">
              <c:choose>
                <c:when test="${jobVO.jobRemain_e < 0}">
                  <IMG src="/study/nonuser/jobinfo/images/apply_end.png">
                </c:when>
                <c:when test="${jobVO.jobRemain_s > 0}">
                  <IMG src="/study/nonuser/jobinfo/images/apply_before.png">
                </c:when>
                <c:otherwise>
                  <IMG src="/study/nonuser/jobinfo/images/apply.png">
                </c:otherwise>
              </c:choose>
            </td>
            <td style="padding-left: 10px !important;">
              <div style="text-align: left !important; font-weight: bold !important; padding-bottom: 5px !important;">
                <a href="/study/nonuser/jobinfo/read.do?jobNo=${jobVO.jobNo }">${jobVO.jobStatus}</a>
              </div>
            </td>
            <td style="text-align: center;">
              <span style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">
              ${jobVO.comName }
              </span>
            </td>
            <td style="text-align: center;">
              <div style="color: #888888; font-size: 11px; line-height: 18px;">${jobVO.jobCnt}</div>
            </td>
            <c:choose>
            <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A'}">
            <td style="text-align: center;">
              <A type="button" class="btn btn-default btn-sm" onclick="location.href='./update.do?jobNo=${jobVO.jobNo}'">
                <IMG src='/study/admin/jobinfo/images/edit.png' style='width: 15px; height: 15px; line-height: 18px;'>
              </A>
              <A href="javascript: deleteOne(${jobVO.jobNo});" type="button" class="btn btn-default btn-sm" id="myBtn">
                <IMG src='/study/admin/jobinfo/images/trash.png' style='width: 15px; height: 15px; line-height: 18px;'>
              </A>
            </td>
            </c:when>
            <c:otherwise></c:otherwise>
            </c:choose>
            <%-- <td style="text-align: center;">
              <button onclick="location.href='./update.do?conNo=${contestVO.conNo}&cateno=${contestVO.cateno}'">
                <IMG src='./images/edit.png' style='width: 15px; height: 15px; line-height: 18px;'>
              </button>
              <button onclick="location.href='./delete.do?conNo=${contestVO.conNo}&cateno=${contestVO.cateno}'">
                <IMG src='./images/trash.png' style='width: 15px; height: 15px; line-height: 18px;'>
              </button>
            </td> --%>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <DIV class="cb t10"></DIV>
    <div class= "bottom_menu">${paging }</div>
  </DIV>
  
  <div class="modal fade" id="myModal" role="dialog" style="font-size: 18px; display: none;">
  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title" style="font-weight: bold; text-align: center; padding-left: 40px;">채용정보 게시글 삭제</h4>
    </div>
    <div class="modal-body" style='width: 95%; height: 210px; text-align: center; margin: 0px auto;'>
      <FORM name = 'frm_delete' id = 'frm_delete' method="POST" action="/study/admin/jobinfo/delete.do">
        <input type="hidden" name="jobNo" id="jobNo" value='${jobVO.jobNo }'>
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