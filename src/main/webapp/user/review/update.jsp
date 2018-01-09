<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>리뷰 수정</title> 
 
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="${pageContext.request.contextPath}/user/review/hidcss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>




<script type="text/javascript">
$(function() {
  $('#rvgood').keyup(function (e){
      var rvgood = $(this).val();
      $('#rvgood_cnt').html(rvgood.length);
  });
  
  $('#rvgood_cnt').keyup();
});

  var loading = 0;
  
  $(document).ready(function(){ // window.onload = function() { ... }
    imgResize()
  });
 
  //<div id='rofile1Panel'> 태그의 width에 맞추어 자동 축소
  function imgResize() {
    loading = loading + 1;
    var rofile1 = $('#rofile1');
    var width = rofile1.width();
    // alert(width);
    
    if (rofile1 != null) {
      // 이미지 width가 화면의 70%보다 크다면
      if (width > screen.width-(screen.width * 0.3)) {  
        // rofile1.width(600); // 이미지 축소
        $('#rofile1Panel').attr('width', '100%');
        rofile1.css('width', '100%'); // <div id='rofile1Panel'> 태그의 width에 맞추어 자동 축소
      } else {
        // 작은 이미지는 그대로 출력
      }
    }
    
    var timer = setInterval(imgResize, 100); // 0.1 초
    
    if (loading == 2) {
      clearInterval(timer) // 타이머 종료, 함수 실행 종료
    }
  }
  
  
//*********************** 리뷰글 수정 이벤트 처리 *********************** 
  function update_check() {
    
   // 기존 파일 로딩을 위한 변수 받아오기
   var rvfile1 = $('#rvfile1').val();
   var rvsize1 = $('#rvsize1').val();
   var rvthumb = $('#rvthumb').val();
   //alert("파일명 받아오는지 확인: " + rvfile1);
   //--------------------------------------------
   
   var formData = new FormData();
    
    formData.append("memberno", $("input[name=memberno]").val());
    formData.append("rono", $("input[name=rono]").val());
    formData.append("rvno", $("input[name=rvno]").val());
    //alert("기초 rono: " + $("input[name=rono]").val());
    
    // alert($("input[name=memberno]").val());
    formData.append("rvdate", $("input[name=rvdate]").val());
    formData.append("rvgood", $("input[name=rvgood]").val());
    formData.append("rvcont", $("textarea[name=rvcont]").val());
    formData.append("rvup", $("input[name=rvup]").val());
    formData.append("rvfile1", rvfile1);
    formData.append("rvsize1", rvsize1);
    formData.append("rvthumb", rvthumb);

    //********** NULL 값 Check해  입력안했으면 실행하지 못하도록 함 *****************
    if($("input[name=rvgood]").val() == null || $("input[name=rvgood]").val() == '') {
      alert("평점을 등록해주세요.");
      return false;
    }
    
    if ($("textarea[name=rvcont]").val() == null || $("textarea[name=rvcont]").val() == '') {
      alert("내용을 입력해주세요.");
      return false;
    }
    
    
  //**********************************************************************************
   
  if($("input[name=file1MF")[0].files[0] == null) { // 파일 업로드 X의 경우
    //alert("그냥업로드 실행"); 
  
    var frm = $('#frm').serialize(); // 위의 코드 사용하지 않고 파일 제외한 모든 데이터를 serialize
    //alert("frm:" + frm);
    update_nom(frm);
    
  } else { // 파일 업로드하는 경우
    //alert("파일업로드 실행");
  
    formData.append("file1MF", $("input[name=file1MF")[0].files[0]); // 파일 업로드까지 추가
    
    //alert("파일 있는지 확인 :" + $("input[name=file1MF")[0].files[0]);
    
    //alert("rono: " + $("input[name=rono]").val());
    //alert("memno: " + $("input[name=memberno]").val());
    
    
    update_file(formData);
    }
  }
 //***************************************************************************** 
 
 //************************ 파일까지 포함한 update.do ***********************
 function update_file(formData) {
   console.log("formData:"+formData);
   $.ajax({ 
     url: "/study/user/review/update.do",
     type: "POST",
     data: formData,
     dataType: "JSON",
     processData: false,      // 필수 코드 1
     contentType: false,      // 필수 코드 2
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("리뷰를 수정하였습니다.");
         window.opener = window.location.href; 
         self.close();
         document.location.href = "/study/nonuser/room/read.do?rono=${roomVO.rono}";
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 게시글을 수정하지 못했습니다. 다시 시도해주십시오.");
         window.opener = window.location.href;
         self.close(); 
         document.location.href = "/study/nonuser/room/read.do?rono=${roomVO.rono}";
       } 
     }
   });
 }
//*******************************************************************************
 
 //************************** 파일을 제외한 update.do *************************
 function update_nom(data) {
   console.log("data:"+data);
   $.ajax({
     url: "/study/user/review/update.do",
     type: "POST",
     data: data,
     dataType: "JSON",
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("리뷰를 수정하였습니다.")
         window.opener = window.location.href;
         self.close();
         document.location.href = "/study/nonuser/room/read.do?rono=${roomVO.rono}";
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 리뷰를 수정하지 못했습니다. 다시 시도해주십시오.");
         window.opener = window.location.href;
         self.close();
         document.location.href = "/study/nonuser/room/read.do?rono=${roomVO.rono}";
       } 
     }
   });
 }
 //********************************************************************************
  
  function rvupdate(){

    var params =$('#frm').serialize();
    params += "&file1MF="+$('#file1MF').val();
    
    alert(params);
    $.ajax({
      url : "/study/user/review/update.do",
      type : "POST",
      cache : false,
      data : params,
      dataType : "json", // or html
      success : function(data) {
        alert(data.alert);
      }
    })
  }
    
</script>
 
</head> 
 
<body>
<DIV class='container'>    
  <DIV class='content' style="width: 100%">
  
  <form id='frm' name='frm' method='POST' enctype="multipart/form-data" class="form-horizontal">
  <input type="hidden" id="rono" name="rono" value="${reviewVO.rono }">
  <input type="hidden" id="memberno" name="memberno" value="${reviewVO.memberno }">
  <input type="hidden" id="rvno" name="rvno" value="${reviewVO.rvno }">
  <input type="hidden" id="rvfile1" name="rvfile1" value="${reviewVO.rvfile1 }">
  <input type="hidden" id="rvsize1" name="rvsize1" value="${reviewVO.rvsize1 }">
  <input type="hidden" id="rvthumb" name="rvthumb" value="${reviewVO.rvthumb }">

    <fieldset class='fieldset_basic'>
      <legend style='font-size: 20px; font-weight: 700; margin: 0px;'>
        <IMG src='./images/view.png' height="30px" width="30px" style='vertical-align: middle;'>&nbsp;스터디룸 후기 수정&nbsp;
      </legend>

      <div>
        <li class="li_none">    
          <div id='rofile1Panel' style='margin: 0px auto 10px auto; text-align:center;'>
            <!-- EL 값을 JSTL 변수에 할당 -->
            <c:set var='rofile1' value="${fn:toLowerCase(roomVO.rofile1)}" />
            <c:choose>
              <c:when test="${fn:endsWith(rofile1, '.jpg')}">
                <IMG id='rofile1' src='../room/storage/${roomVO.rofile1}'  width="300px" height="200px">
              </c:when>  
              <c:when test="${fn:endsWith(rofile1, '.gif')}">
                <IMG id='rofile1'  src='../room/storage/${roomVO.rofile1}' width="300px" height="200px">
              </c:when>
              <c:when test="${fn:endsWith(rofile1, '.png')}">
                <IMG id='rofile1'  src='../room/storage/${roomVO.rofile1}'' width="300px" height="200px">
              </c:when>
            </c:choose>
          </div>
        </li>
        
        <div style='margin: 0px 0px 10px 0px;'>     
          <li class="li_none">
            <span style='font-size: 20px; font-weight: 550; line-height: 40px;'>
             스터디룸 : ${roomVO.roname}
            </span><br>
            <span style='font-size: 20px; font-weight: 550; line-height: 10px;'>
            위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;치 : ${roomVO.rolocation}
            </span>
          </li>
          
          <li class="li_none" style='font-size: 20px; font-weight: 550; line-height: 30px; margin: 14px 0px 0px 5px;'>
            <span style="vertical-align:middle; font-size: 20px;">평점을 재등록해주세요!</span>
            <span class="star-input" style='padding:0px;'>
              <span class="input">
                <input type="radio" name="rvgood" value="1" id="p1"> 
                <label for="p1">1</label>
                <input type="radio" name="rvgood" value="2" id="p2">
                <label for="p2">2</label>
                <input type="radio" name="rvgood" value="3" id="p3">
                <label for="p3">3</label>
                <input type="radio" name="rvgood" value="4" id="p4">
                <label for="p4">4</label>
                <input type="radio" name="rvgood" value="5" id="p5">
                <label for="p5">5</label>
              </span>
              <output for="star-input" style='padding:0px 0px 0px 7px; text-align:left;''><b style='font-size: 20px; font-weight: 550;'>0</b>점</output>           
            </span> 
          </li>
        </div>
      </div>
      
 
      
      <div style='clear: both; width: 100%; margin: 0px auto 10px auto; border: 0.4px solid #ddd'></div>
       
      <TEXTAREA class="form-control" name='rvcont' id='rvcont' rows='8' style='width: 99%; margin-bottom:10px;' placeholder="내용">${reviewVO.rvcont }</textarea><BR>
      
      <div class="form-group" style='margin-bottom: 10px;margin-top: 15px;float: left;width: 92%; '>
        <div class="col-md-9" style='padding-left: 1.4%; ' >
        <label class="col-md-2 control-label" style='font-size: 16px;  font-weight: 700; text-align: center; margin-right: 88px; '>기존 파일</label>
          <!-- 파일명을 소문자로 변경 -->
          <c:set var='rvfile1' value="${fn:toLowerCase(reviewVO.rvfile1)}" />
          <!-- 소문자로 변경된 파일명이 이미지인지 검사 -->
          <c:choose>
            <c:when test="${fn:endsWith(rvfile1, '.jpg')}">
              <IMG id='rvfile1' src='../review/storage/${reviewVO.rvfile1}' style='width: 20%;'>
            </c:when>
            <c:when test="${fn:endsWith(rvfile1, '.gif')}">
              <IMG id='rvfile1'  src='../review/storage/${reviewVO.rvfile1}' style='width: 20%;'>
            </c:when>
            <c:when test="${fn:endsWith(rvfile1, '.png')}">
              <IMG id='rvfile1'  src='../review/storage/${reviewVO.rvfile1}' style='width: 20%;'>
            </c:when>
            <c:when test="${reviewVO.rvfile1.length() > 0}">
              ${reviewVO.rvfile1 }  <!-- 이미지가 아니면서 파일이 존재하는 경우 파일명 출력 -->
            </c:when>
          </c:choose>
        </div>
      </div>   
       
      <div class="form-group"'>   
        <label for="file1MF" class="col-md-2 control-label" style='width: 22%; padding-top: 2px; font-size: 16px;  font-weight: 700; text-align: left; '>첨부 파일</label>
        <div class="col-md-10">
          <input type="file" class="input-md" name='file1MF'  id='file1MF' value="파일 선택" size='40' style="border: none;">
        </div>
      </div>  
      
      <div class="form-group">
        <div class="col-md-10">
         <button type='button' onclick='javascript:update_check()' class="btn btn-default" >수정</button> 
         <button type='reset' class="btn btn-default">취소</button> 
         </div>    
      </div>

    </fieldset>
  </form>
  

  </DIV> <!-- content END -->
  <script src="./js/star.js"></script>
</DIV> <!-- container END -->
</body>
 
</html> 