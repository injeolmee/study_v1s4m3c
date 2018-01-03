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
    CKEDITOR.replace('sharedcontent');  // <TEXTAREA>태그 id 값을 받아와 ckeditor 형식으로 변환
  };
   
  //*********************** 게시글 등록 이벤트 처리 *********************** 
  function create_check() {
     
    var formData = new FormData(); // 파일을 보내기 위한 새로운 폼 형식 생성
    
    var ck_sharedcontent = CKEDITOR.instances.sharedcontent.getData(); // 전송을 위해 ck 데이터 내용을 가져옴
    // alert("ck 내용 출력: " + ck_sharedcontent);
    
    formData.append("memberno", $("input[name=memberno]").val());
    formData.append("sharedname", $("input[name=sharedname]").val());
    formData.append("sharedtitle", $("input[name=sharedtitle]").val());
    formData.append("sharedyoutube", $("textarea[name=sharedyoutube]").val());
    formData.append("sharedcontent", ck_sharedcontent);
    
    //********** NULL 값 Check해  입력안했으면 실행하지 못하도록 함 *****************
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
     //  alert("그냥업로드 실행"); 
  
      var frm = $('#frm').serialize(); // 위의 코드 사용하지 않고 파일 제외한 모든 데이터를 serialize
     
      var frm_new = replaceAll(frm, "sharedcontent", ""); // ★★ url 조합을 위해 중복 제거 ★★
      var ck_data = "&sharedcontent=" + ck_sharedcontent; // ckeditor 내용을 get 방식으로 보내기 위한 결합 방식
      
      var new_frm = frm_new + ck_data; // frm의 내용과 ckeditor의 내용 결합
      // alert("원래 폼으로부터의 serialize: " + frm);
      // alert("결합한 소스: " + new_frm);
      
      create_nom(new_frm);
    
  } else { // 파일 업로드하는 경우
    // alert("파일업로드 실행");
    formData.append("file1MF", $("input[name=file1MF")[0].files[0]); // 파일 업로드까지 추가
    create_file(formData);
    }
  }
 //***************************************************************************** 
 
 //************************ 파일까지 포함한 create.do ***********************
 function create_file(formData) { 
   $.ajax({
     url: "/study/user/shared/create.do",
     type: "POST",
     data: formData,
     dataType: "JSON",
     processData: false,      // 필수 코드 1
     contentType: false,      // 필수 코드 2
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("게시글을 등록하였습니다.")
         document.location.href = "/study/user/shared/list.do";
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 게시글을 등록하지 못했습니다. 다시 시도해주십시오.");
       } 
     }
   });
 }
//*******************************************************************************
 
 //************************** 파일을 제외한 create.do *************************
 function create_nom(data) {
   $.ajax({
     url: "/study/user/shared/create.do",
     type: "POST",
     data: data,
     dataType: "JSON",
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("게시글을 등록하였습니다.")
         document.location.href = "/study/user/shared/list.do";
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 게시글을 등록하지 못했습니다. 다시 시도해주십시오.");
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

  <DIV class="title_line"> 게시글 등록 </DIV>
  
  <DIV style="text-align: center; width: 100%;">
  
    <FORM name='frm' id='frm' method='POST' action='' enctype="multipart/form-data" class="form-horizontal" style="text-align: left;">
       <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
       <input type='hidden' name='sharedname' id='sharedname' value=${sessionScope.memname }>
     
       <div class="form-group">   
         <div class="col-md-10" style="width: 100%; margin-top: 30px;">
         <label for="title" class="control-label" style="margin-right: 30px;">제목</label>
         <input type='text' class="form-control input-md" name='sharedtitle' id='sharedtitle' value='' 
                  required="required" style='width: 60%;'>
         </div>
       </div>
    
       <div class="form-group">   
         <div class="col-md-10" style="text-align: center; width: 100%; margin-top: 15px; margin-bottom: 15px;">
           <textarea class="form-control input-md" name='sharedcontent' id='sharedcontent' rows='20' cols='70'></textarea>
         </div>
       </div>
    
       <div class="form-group" style="width: 500px; display: flex;"> 
         <label for="file1MF" class="col-md-2 control-label" style="width: 150px; padding-top: 2px;">업로드 파일  </label>
         <div class="col-md-10" style="text-align: center; width: 100%;">
           <input type="file" class="input-md" name='file1MF' id='file1MF' value="파일 선택" size='40' style="border: none;">
         </div>
       </div>
    
       <div class="form-group">  
         <div class="col-md-10" style="width: 100%; display: -webkit-box;"> 
           <label for="title" class="control-label" style="margin-right: 30px; ">YOUTUBE</label>
           <textarea class="form-control input-md" name="sharedyoutube" id="sharedyoutube"  rows="3" style="width: 60%;"></textarea>
         </div>
       </div>   
    
       <div class="form-group">       
         <div class="col-md-10"style="width: 100%;"> 
           <div style="float: right;">
             <button class="btn btn-default" type="button" onclick="create_check()">등록</button>
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