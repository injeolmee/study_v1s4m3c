<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="${pageContext.request.contextPath}/user/shared/gnacss/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('sharedcontent');  // <TEXTAREA>태그 id 값
      
    //*********** CKEDITOR에 대한 내용을 받아와 출력 *************
    var content = '${sharedVO.sharedcontent}';
    CKEDITOR.instances.sharedcontent.setData(content);
    //****************************************************************
  };
  
  //*********************** 게시글 수정 이벤트 처리 *********************** 
  function update_check() {
    
    var ck_sharedcontent = CKEDITOR.instances.sharedcontent.getData();
     
    // 기존 파일을 불러오기 위해 관련 변수 받아오기
    var sharedfstor = $('#sharedfstor').val(); 
    var sharedtum = $('#sharedtum').val();
    var sharedsize = $('#sharedsize').val();
    var sharedfile = $('#sharedfile').val();
    
    // alert("파일명 받아오는지 확인: " + salefstor);
    var formData = new FormData();
    
    formData.append("memberno", $("input[name=memberno]").val());
    formData.append("sharedname", $("input[name=sharedname]").val());
    formData.append("sharedtitle", $("input[name=sharedtitle]").val());
    formData.append("sharedyoutube", $("textarea[name=sharedyoutube]").val());
    formData.append("sharedcontent", ck_sharedcontent);
    formData.append("sharedno", $("input[name=sharedno]").val());
    formData.append("sharedfstor", sharedfstor);
    formData.append("sharedtum", sharedtum);
    formData.append("sharedsize", sharedsize);
    formData.append("sharedfile", sharedfile);
    
    //********** NOT NULL 대해 입력안했으면 실행하지 못하도록 함 *****************
    if($("input[name=sharedtitle]").val() == null || $("input[name=sharedtitle]").val() == '') {
      alert("제목을 입력해주세요.");
      return false;
    }
    
    if(ck_sharedcontent.length < 1) {
      alert("내용을 입력해주세요.");
      return false;
    }
    //**********************************************************************************
   
    if($("input[name=file1MF")[0].files[0] == null) { // 파일 업로드 X의 경우
   
      // alert("그냥업로드 실행"); 
      var frm = $('#frm').serialize(); // 위의 코드 사용하지 않고 파일 제외한 모든 데이터를 serialize
      var frm_new = replaceAll(frm, "sharedcontent", ""); // ★★ URL 중복 제거를 위한 처리 ★★
      
      var ck_data = "&sharedcontent=" + ck_sharedcontent;
      var new_frm = frm_new + ck_data; // frm의 내용과 ckeditor의 내용 결합
      // alert("frm의 serialize: " + frm);
      // alert("결합한 소스: " + new_frm);

      update_nom(new_frm);
    
    } else { // 파일 업로드하는 경우
      // alert("파일업로드 실행");
      formData.append("file1MF", $("input[name=file1MF")[0].files[0]); // 파일 업로드까지 추가
      update_file(formData);
    }
  }
  //***************************************************************************** 
 
  //************************ 파일까지 포함한 update.do ***********************
  function update_file(formData) { 
   // alert("파일 업로드 실행합니다.");
   
    $.ajax({
      url: "/study/user/shared/update.do",
      type: "POST",
      data: formData,
      dataType: "JSON",
      processData: false,      // 필수 코드 1
      contentType: false,      // 필수 코드 2
      success: function (data) {
       
        if (data.count == 1) { // 수정 처리가 성공한 경우
          alert("게시글을 수정하였습니다.")
          var check = confirm("변경된 게시글을 확인하시겠습니까?");
         
          if (check == true) { // 확인 버튼을 누를 경우
            document.location.href = "/study/user/shared/read.do?sharedno=" + data.sharedno;
          } else {                // 취소 버튼을 누를 경우
            document.location.href = "/study/user/shared/list.do";
          }
        } else { // 수정 처리가 실패한 경우
         alert("오류가 생겨 게시글을 수정하지 못했습니다. 다시 시도해주십시오.");
        } 
      }
    });
  }
  //*******************************************************************************
 
  //************************** 파일을 제외한 update.do *************************
  function update_nom(data) {
    // alert("그냥 업로드 실행합니다.")
   
    $.ajax({
      url: "/study/user/shared/update.do",
      type: "POST",
      data: data,
      dataType: "JSON",
      success: function (data) {
       
        if (data.count == 1) { // 수정 처리가 성공한 경우
          alert("게시글을 수정하였습니다.")
          var check = confirm("변경된 게시글을 확인하시겠습니까?");
         
          if (check == true) { // 확인 버튼을 누를 경우
            document.location.href = "/study/user/shared/read.do?sharedno=" + data.sharedno;
          } else {                // 취소 버튼을 누를 경우
            document.location.href = "/study/user/shared/list.do";
          }

        } else { // 수정 처리가 실패한 경우
         alert("오류가 생겨 게시글을 수정하지 못했습니다. 다시 시도해주십시오.");
        } 
      }
    });
  }
 //********************************************************************************
 
 //************** URL 조합을 위한 Replace 함수 ***************************
  function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);   
   }
  //**************************************************************************
     
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container" style='margin-bottom: 100px;'>
<DIV class='content' style='width: 60%; margin: 0px auto; '>  

  <DIV class="title_line"> 게시글 수정 </DIV>
  
  <DIV style="text-align: center; width: 100%;">
  
    <FORM name='frm' id='frm' method='POST' action='' enctype="multipart/form-data" class="form-horizontal" style="text-align: left;">
      <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
      <input type='hidden' name='sharedname' id='sharedname' value=${sessionScope.memname }>
      <input type='hidden' name='sharedno' id='sharedno' value=${sharedVO.sharedno }>
      <input type='hidden' name='sharedfstor' id='sharedfstor' value=${sharedVO.sharedfstor }>
      <input type='hidden' name='sharedtum' id='sharedtum' value=${sharedVO.sharedtum }>
      <input type='hidden' name='sharedfile' id='sharedfile' value=${sharedVO.sharedfile }>
      <input type='hidden' name='sharedsize' id='sharedsize' value=${sharedVO.sharedsize }>
  
      <div class="form-group">   
        <div class="col-md-10" style="width: 100%; margin-top: 30px;">
          <label for="title" class="control-label" style="margin-right: 30px;">제목</label>
          <input type='text' class="form-control input-md" name='sharedtitle' id='sharedtitle' value='${sharedVO.sharedtitle }' 
                   required="required" style='width: 60%;'>
        </div>
      </div>
    
      <div class="form-group">   
        <div class="col-md-10" style="text-align: center; width: 100%; margin-top: 15px; margin-bottom: 15px;">
          <textarea class="form-control input-md" name='sharedcontent' id='sharedcontent' rows='20' cols='70'></textarea>
        </div>
      </div>
    
      <div class="form-group" style="display: -webkit-box; width: 500px;"> 
        <label for="title" class="control-label" style="margin-right: 18px;">기존 업로드 파일</label>
        <div class="col-md-10" style="width: 100%;  padding-top: 7px;">
          <c:set var='sharedfile' value="${fn:toLowerCase(sharedVO.sharedfile)}" /> <!-- 파일명을 소문자로 변경 -->
          <!-- 소문자로 변경된 파일명이 이미지인지 검사 -->
          <c:choose>
            <c:when test="${fn:endsWith(sharedfile, '.jpg')}">
              <IMG id='sharedfile' src='${pageContext.request.contextPath}/user/shared/storage/${sharedVO.sharedfile}' style='width: 20%;'>
            </c:when>
            <c:when test="${fn:endsWith(sharedfile, '.gif')}">
              <IMG id='sharedfile'  src='${pageContext.request.contextPath}/user/shared/storage/${sharedVO.sharedfile}' style='width: 20%;'>
            </c:when>
            <c:when test="${fn:endsWith(sharedfile, '.png')}">
              <IMG id='sharedfile'  src='${pageContext.request.contextPath}/user/shared/storage/${sharedVO.sharedfile}' style='width: 20%;'>
            </c:when>
            <c:when test="${sharedVO.sharedfile.length() > 0}">
              ${sharedVO.sharedfile }  <!-- 이미지가 아니면서 파일이 존재하는 경우 파일명 출력 -->
            </c:when>
            <c:otherwise>
              존재하는 파일이 없습니다.
            </c:otherwise>
          </c:choose>
        </div>
      </div>
      
      <div class="form-group" style="display: -webkit-box; width: 500px;"> 
        <label for="file1MF" class="col-md-2 control-label" style="width: 90px; padding-top: 2px; padding-right: 30px;">업로드 파일  </label>
        <div class="col-md-10" style="text-align: center; width: 100%;">
          <input type="file" class="input-md" name='file1MF' id='file1MF' value="파일 선택" size='40' style="border: none; line-height: 0px;">
        </div>
      </div>
    
      <div class="form-group">  
        <div class="col-md-10" style="width: 100%; display: -webkit-box;"> 
          <label for="title" class="control-label" style="margin-right: 50px; ">YOUTUBE</label>
          <textarea class="form-control input-md" name="sharedyoutube" id="sharedyoutube" rows="3" style="width: 60%;">${sharedVO.sharedyoutube}</textarea>
        </div>
      </div>      
    
      <div class="form-group">       
        <div class="col-md-10"style="width: 100%;"> 
          <div style="float: right;">
            <button class="btn btn-default" type="button" onclick="update_check()" >수정</button>
            <button class="btn btn-default" type="button" onclick="history.go(-1);">취소</button>
          </div>
        </div>
      </div>
      
    </FORM>
  </DIV>

</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>