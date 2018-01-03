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
<title>CONTEST</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">
//<div id='file2Panel'> 태그의 width에 맞추어 자동 축소
function imgResize() {
  loading = loading + 1;
  var file2 = $('#file2');
  var width = file2.width();
  // alert(width);
  
  if (file2 != null) {
    // 이미지 width가 화면의 70%보다 크다면
    if (width > screen.width-(screen.width * 0.3)) {  
      // file1.width(600); // 이미지 축소
      $('#file2Panel').attr('width', '100%');
      file2.css('width', '100%'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
    } else {
      // 작은 이미지는 그대로 출력
    }
  }
  
  var timer = setInterval(imgResize, 100); // 0.1 초
  
  if (loading == 2) {
    clearInterval(timer) // 타이머 종료, 함수 실행 종료
  }
}

function update_thumb_frm(conNo) {
  var frm_thumb = $('#frm_thumb');
  $.ajax({
    url: "/study/admin/contest/update_thumb.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'conNo=' + conNo,
    success: function(data) {
      var conThumb = "<IMG src='/study/admin/contest/storage/" + data.conFile1 + "'>";
      $("#conFile1", frm_thumb).val(data.conFile1);
      $('#conFile1').html(data.conFile1);
      $("#conThumb", frm_thumb).val(data.comFile1);
      $('#conThumb').html(conThumb);
      $("#myModal").modal();
    }
  });
}

function update_img_frm(conNo) {
  var frm_img = $('#frm_img');
  $.ajax({
    url: "/study/admin/contest/update_img.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'conNo=' + conNo,
    success: function(data) {
      var conFstor2 = "<IMG src='/study/admin/contest/storage/" + data.conFile2 + "'>";
      $("#conFile2", frm_img).val(data.conFile2);
      $('#conFile2').html(data.conFile2);
      $("#conFstor2", frm_img).val(data.conFile2);
      $('#conFstor2').html(conFstor2);
      $("#myModal2").modal();
    }
  });
}

function update_file_frm(conNo) {
  var frm_file = $('#frm_file');
  $.ajax({
    url: "/study/admin/contest/update_file.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'conNo=' + conNo,
    success: function(data) {
      $("#conFile3", frm_file).val(data.conFile3);
      $('#conFile3').html(data.conFile3);
      /* $("#conFstor3", frm_file).val(data.comFile3);
      $('#conFstor3').html(conFstor3); */
      $("#myModal3").modal();
    }
  });
}

</script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>

<DIV style='width: 60%; margin: 0px auto; text-align: center;'>
  <ASIDE style='float: left;'>
    <button type='button' class='btn btn-default' onclick="location.href='/study/nonuser/contest/list_all_contest.do'">공모전 목록</button>
  </ASIDE>
  
  <ASIDE style='float: right; width: 40%;'>
    <!-- <select id='keyWord' name='keyWord' style='margin-bottom: 10px; height: 32px;'>
      <option value='all' selected="selected">전 체</option>
      <option value='conCont'>내 용</option>
      <option value='memname'>작성자</option>
    </select> -->
    <c:choose>
      <c:when test="${param.conWord != '' }">
        <input type='text' name='conWord' id='conWord' value='${param.conWord}' style='width: 50%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='conWord' id='conWord' value='' style='width: 50%;'>
      </c:otherwise>
    </c:choose>
    <button class="btn btn-success" type='submit' style='margin-bottom: 10px;'>검색</button>
    <button type='button' class='btn btn-info' onclick="location.href='javascript:location.reload();'" style='margin-bottom: 10px;'>
        새로고침
    </button>
  </ASIDE> 
  </DIV>
  <div class='menu_line' style='margin: 0px auto; text-align: center; width: 60%; clear: both;'></div>
  
  <DIV style='color: #333333; width: 20%; margin: 20px auto; padding: 3px; border-bottom: solid 2px #555555; text-align: center;'>
  ${contestVO.conTitle } 수정
  </DIV>
  
  <FORM id="frm" name='frm' method='POST' action='/study/admin/contest/update.do'
               enctype="multipart/form-data" class="form-horizontal" style='text-align: center; margin: 0px auto; width: 50%;'>
      <input type='hidden' name='conNo' id='conNo' value='${contestVO.conNo}'>
      
   <DIV class = "form-group form-group-sm">
    <label for='conTitle' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>제 목</label>
    <DIV class="col-md-6" style='width: 65%;'>
      <input type='text' class="form-control input-lg" name='conTitle' id='conTitle' value='${contestVO.conTitle }' required="required">
    </DIV>
  </DIV>
  <!-- <DIV style="clear: both; padding-top: 10px !important;"></DIV> -->
  <DIV class = "form-group form-group-sm">
    <label for='conUrl' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>홈페이지</label>
    <DIV class="col-md-6" style='width: 65%;'>
      <input type='text' class="form-control input-lg" name='conUrl' id='conUrl' value='${contestVO.conUrl }' required="required">
    </DIV>
  </DIV>
  
  <DIV class = "form-group form-group-sm">
    <label for='conUrl' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>YouTube</label>
    <DIV class="col-md-6" style='width: 65%;'>
      <input type='text' class="form-control input-lg" name='conYou' id='conYou' value='${contestVO.conYou }' required="required">
    </DIV>
  </DIV>
  <!-- <DIV style="clear: both; padding-top: 10px !important;"></DIV> -->
  <DIV class = "form-group form-group-sm">
    <label for='conHost' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>주 관</label>
    <DIV class="col-md-6" style='width: 25%;'>
      <input type='text' class="form-control input-lg" name='conHost' id='conHost' value='${contestVO.conHost }' required="required">
    </DIV>
    <label for="conWord" class="col-md-6 control-label" style='width: 8.6%; padding-left: 1%;'>검색어</label>
    <div class="col-md-6" style='width: 24%;'>
      <input type='text' class="form-control input-lg" name='conWord' id='conWord' value='${contestVO.conWord }'>
    </div>
  </DIV>  
  <!-- <DIV style="clear: both; padding-top: 10px !important;"></DIV> -->
  <DIV class = "form-group form-group-sm">
    <label for='conStart' class='col-md-4 control-label' style='width: 13%; padding-left: 1%;'>시작일</label>
    <DIV class="col-md-6" style='width: 25%;'>
      <input type='date' class="form-control input-lg" name='conStart' id='conStart' value='${contestVO.conStart }' required="required">
    </DIV>
    <label for='conEnd' class='col-md-4 control-label' style='width: 8.6%; padding-left: 1%;'>종료일</label>
    <DIV class="col-md-6" style='width: 24%;'>
      <input type='date' class="form-control input-lg" name='conEnd' id='conEnd' value='${contestVO.conEnd }' required="required">
    </DIV>
  </DIV>
  
  <div class="form-group">   
    <label for="conCont" class="col-md-6 control-label" style='width: 13%; padding-left: 1%;'>내 용</label>
    <div class="col-md-6" style='width: 64.5%;'>
      <textarea class="form-control input-md" name='conCont' id='conCont' rows='12' cols='12'>${contestVO.conCont }</textarea>
      <br>
    </div>
  </div>
  
  <DIV class="form-group" style="width: 100%; margin: 0px auto; padding-left: 15px;">
  <!-- Modal -->
    <!-- Trigger the modal with a button -->
    <A href="javascript: update_thumb_frm(${param.conNo });" type="button" class="btn btn-default btn-lg" id="myBtn">Thumb Replace</A>
  <!-- Modal -->
  
  <!-- Modal -->
    <!-- Trigger the modal with a button -->
    <A href="javascript: update_img_frm(${param.conNo });"  type="button" class="btn btn-default btn-lg" id="myBtn2">IMG Replace</A>
  <!-- Modal -->
  
  <!-- Modal -->
    <!-- Trigger the modal with a button -->
    <A href="javascript: update_file_frm(${param.conNo });" type="button" class="btn btn-default btn-lg" id="myBtn3">Resume Replace</A>
  <!-- Modal -->
  </DIV>
  
  <DIV style="clear: both; padding-top: 10px !important;"></DIV>
  
  <DIV style="clear: both; padding-top: 10px !important;"></DIV>
  
  <DIV style="clear: both; padding-top: 10px !important;"></DIV>
   
  <DIV style='width: 500px; text-align: center; margin: 0px auto;'>
    <button class='btn btn-default' type="submit">저장</button>
    <button class='btn btn-default' type="button" onclick="location.href='/study/nonuser/contest/list_all_contest.do?conNo=${contestVO.conNo}'">취소[목록]</button>
  </DIV>
</FORM>

  <div class="modal fade" id="myModal" role="dialog" style="font-size: 18px; display: none;">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" style="font-weight: bold; text-align: center;">썸네일 수정</h4>
      </div>
      <div class="modal-body" style='width: 95%; height: 420px; text-align: center; margin: 0px auto;'>
        <FORM name = 'frm_thumb' id = 'frm_thumb' method="POST" action="/study/admin/contest/update_thumb.do" enctype="multipart/form-data">
          <input type="hidden" name="conNo" id="conNo" value='${param.conNo }'>
          <input type="hidden" name="cateno" id="cateno" value='${param.cateno }'>
          <DIV class = "form-group form-group-sm">
            <label for="conThumb" class="col-md-6 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">현재 썸네일</label>
            <DIV class="col-md-6" style='width: 43%;'>
              <span id="conFile1"></span>
              <%-- <input type = "text" value="${fn:toLowerCase(contestVO.conFile1)}"> --%>
            </DIV>
          </DIV>
          <DIV style="clear: both; padding-top: 10px !important;"></DIV>
          <div id='conFile1Panel' class="form-group form-group-sm">
            <label class="col-md-1 control-label"></label>
            <div class="col-md-5" style='padding-bottom: 10px; width: 360px; height: 100px;'>
              <div><span id = "conThumb"></span></div>
            </div>
          </div>
          
          <DIV style="clear: both; padding-top: 170px !important;"></DIV>
          
          <DIV class = "form-group form-group-sm">
            <label for="conThumb" class="col-md-4 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">새로운 썸네일</label>
            <DIV class="col-md-6" style='width: 63%;'>
              <input type = "file" name = "file1MF" id = "file1MF" size="50">
            </DIV>
          </DIV>
          
          <DIV style="clear: both; padding-top: 10px !important; border-bottom: 1px solid #CACACA;"></DIV>
          
          <div class="form-group form-group-sm" style="margin: 0px auto; text-align: center;">
            <DIV style="clear: both; padding-top: 10px !important;"></DIV>
            <button type = "submit" class="btn btn-default">썸네일 교체</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </FORM>
      </div>
      <!-- <div class="modal-footer" style="margin: 0px auto; text-align: center;">
        <button type = "submit" class="btn btn-default">썸네일 교체</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div> -->
    </div>
  </div>
<!-- Modal END -->

<div class="modal fade" id="myModal2" role="dialog" style="font-size: 18px; display: none;">
  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title" style="font-weight: bold; text-align: center;">이미지 수정</h4>
    </div>
    <div class="modal-body" style='width: 95%; height: 420px; text-align: center; margin: 0px auto;'>
      <FORM name = 'frm_img' id = 'frm_img' method="POST" action="/study/admin/contest/update_img.do" enctype="multipart/form-data">
        <input type="hidden" name="conNo" id="conNo" value='${param.conNo }'>
        <input type="hidden" name="cateno" id="cateno" value='${param.cateno }'>
        <DIV class = "form-group form-group-sm">
          <label for="conFstor2" class="col-md-6 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">현재 이미지</label>
          <DIV class="col-md-6" style='width: 43%;'>
            <span id="conFile2"></span>
            <%-- <input type = "text" value="${fn:toLowerCase(contestVO.conFile2)}"> --%>
          </DIV>
        </DIV>
        
        <DIV style="clear: both; padding-top: 10px !important;"></DIV>
        
        <div id='conFile2Panel' class="form-group form-group-sm">
          <label class="col-md-1 control-label"></label>
          <div class="col-md-5" style='padding-bottom: 10px; width: 360px; height: 100px;'>
            <span id="conFstor2"></span>
          </div>
        </div>
        
        <DIV style="clear: both; padding-top: 170px !important;"></DIV>
        
        <DIV class = "form-group form-group-sm">
          <label for="conFstor2" class="col-md-4 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">새로운 이미지</label>
          <DIV class="col-md-6" style='width: 63%;'>
            <input type = "file" name = "file2MF" id = "file2MF" size="50">
          </DIV>
        </DIV>
        
        <DIV style="clear: both; padding-top: 10px !important; border-bottom: 1px solid #CACACA;"></DIV>
        
        <div class="form-group form-group-sm" style="margin: 0px auto; text-align: center;">
          <DIV style="clear: both; padding-top: 10px !important;"></DIV>
          <button type = "submit" class="btn btn-default">이미지 교체</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </FORM>
    </div>
  </div>
</div>
<!-- Modal END -->

<div class="modal fade" id="myModal3" role="dialog" style="font-size: 18px; display: none;">
  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title" style="font-weight: bold; text-align: center;">파일 수정</h4>
    </div>
    <div class="modal-body" style='width: 95%; height: 150px; text-align: center; margin: 0px auto;'>
      <FORM name = 'frm_file' id = 'frm_file' method="POST" action="/study/admin/contest/update_file.do" enctype="multipart/form-data">
        <input type="hidden" name="conNo" id="conNo" value='${param.conNo }'>
        <input type="hidden" name="cateno" id="cateno" value='${param.cateno }'>
        <DIV class = "form-group form-group-sm">
          <label for="conFstor3" class="col-md-6 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">현재 파일</label>
          <DIV class="col-md-6" style='width: 43%;'>
            <span id="conFile3"></span>
            <%-- <input type = "text" value="${fn:toLowerCase(contestVO.conFile3)}"> --%>
          </DIV>
        </DIV>
        
        <DIV style="clear: both; padding-top: 10px !important;"></DIV>
        
        <DIV class = "form-group form-group-sm">
          <label for="conFstor3" class="col-md-4 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">새로운 파일</label>
          <DIV class="col-md-6" style='width: 63%;'>
            <input type = "file" name = "file3MF" id = "file3MF" size="50">
          </DIV>
        </DIV>
        
        <DIV style="clear: both; padding-top: 10px !important; border-bottom: 1px solid #CACACA;"></DIV>
        
        <div class="form-group form-group-sm" style="margin: 0px auto; text-align: center;">
          <DIV style="clear: both; padding-top: 10px !important;"></DIV>
          <button type = "submit" class="btn btn-default">파일 교체</button>
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