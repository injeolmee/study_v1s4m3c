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
<link href="${pageContext.request.contextPath}/user/sale/gnacss/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
   
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('salecontent');  // <TEXTAREA>태그 id 값을 받아와 ckeditor 형식으로 변환
  };
  
  //*********************** 게시글 등록 이벤트 처리 *********************** 
  function create_check() {
     
    var formData = new FormData();
    
    var ck_salecontent = CKEDITOR.instances.salecontent.getData();
    
    // alert("내용 출력: " + ck_salecontent);
    
    formData.append("memberno", $("input[name=memberno]").val());
    formData.append("salename", $("input[name=salename]").val());
    formData.append("saleseqno", $("input[name=saleseqno]").val());
    formData.append("saletitle", $("input[name=saletitle]").val());
    formData.append("saletname", $("input[name=saletname]").val());
    formData.append("saleprice", $("input[name=saleprice]").val());
    formData.append("saletel", $("input[name=saletel]").val());
    formData.append("saleaddress", $("input[name=saleaddress]").val());
    formData.append("saleemail", $("input[name=saleemail]").val());
    formData.append("salecontent", ck_salecontent);
    

    //********** NULL 값 Check해  입력안했으면 실행하지 못하도록 함 *****************
    if($("input[name=saletitle]").val() == null || $("input[name=saletitle]").val() == '') {
      alert("제목을 입력해주세요.");
      return false;
    }
    
    if ($("input[name=saletname]").val() == null || $("input[name=saletname]").val() == '') {
      alert("상품명을 입력해주세요.");
      return false;
    }
    
    if($("input[name=saleprice]").val() == null || $("input[name=saleprice]").val() == '') {
      alert("가격을 입력해주세요.");
      return false;
    }
    
    if(ck_salecontent.length < 1) {
      alert("내용을 입력해주세요.");
      return false;
    }
    
  //**********************************************************************************
   
  if($("input[name=file1MF")[0].files[0] == null) { // 파일 업로드 X의 경우
    // alert("그냥업로드 실행"); 
  
    var frm = $('#frm').serialize(); // 위의 코드 사용하지 않고 파일 제외한 모든 데이터를 serialize
    // alert("frm의 serialize: " + frm);
    var new_frm = frm + ck_salecontent; // frm의 내용과 ckeditor의 내용 결합
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
     url: "/study/user/sale/create.do",
     type: "POST",
     data: formData,
     dataType: "JSON",
     processData: false,      // 필수 코드 1
     contentType: false,      // 필수 코드 2
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("게시글을 등록하였습니다.")
         document.location.href = "/study/user/sale/list.do";
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
     url: "/study/user/sale/create.do",
     type: "POST",
     data: data,
     dataType: "JSON",
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("게시글을 등록하였습니다.")
         document.location.href = "/study/user/sale/list.do";
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 게시글을 등록하지 못했습니다. 다시 시도해주십시오.");
       } 
     }
   });
 }
 //********************************************************************************
 
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 60%; margin: 0px auto; '>  

  <DIV class="title_line"> 게시글 등록 </DIV>
  
  <DIV style="text-align: center; width: 100%;">
  
  <FORM name='frm' id="frm" method='POST'
                           enctype="multipart/form-data" class="form-horizontal" style="text-align: left;">
     <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
     <input type='hidden' name='salename' id='salename' value=${sessionScope.memname }>
     <input type='hidden' name='saleseqno' id='saleseqno' value=0>
  
    <div class="form-group">   
      <div class="col-md-10" style="width: 100%; margin-top: 40px;">
        <label for="title" class="control-label" style="margin-right: 30px;">제목</label>
        <input type='text' class="form-control input-md" name='saletitle' id='saletitle' value='' 
          required="required" style='width: 60%;'>
      </div>
    </div>
    
    <div class="form-group">
      <div class="col-md-10" style="width: 100%; margin-top: 30px;">
        <label for="name" class="control-label" style="margin-right: 17px;">상품명</label>
        <input type="text" class="form-control input-md" name='saletname' id='saletname' style='width: 30%;'>
      </div>
    </div>
    
    <div class="form-group">   
      <div class="col-md-10" style="width: 100%; margin-top: 30px;">
        <label for="title" class="control-label" style="margin-right: 30px;">가격</label>
        <input type='number' class="form-control input-md" name='saleprice' id='saleprice' value= 30000 style='width: 20%;'>
        <label for="tel" class="control-label" style="margin-right: 30px; margin-left: 22%;">전화번호</label>
        <input type="text" class="form-control input-md" name='saletel' id='saletel' style='width: 30%;'>
      </div>
    </div>

    <div class="form-group">   
      <div class="col-md-10" style="width: 100%; margin-top: 30px;">
        <label for="address" class="control-label" style="margin-right: 30px;">주소</label>
        <input type='text' class="form-control input-md" name='saleaddress' id='saleaddress' value= "서울시 양천구" style='width: 35%;'>
        <label for="email" class="control-label" style="margin-right: 43px; margin-left: 7%;">이메일</label>
        <input type="text" class="form-control input-md" name='saleemail' id='saleemail' value="min@na.com" style='width: 30%;'>
      </div>
    </div>
    
    <div class="form-group">   
      <div class="col-md-10" style="text-align: center; width: 100%; margin-top: 15px; margin-bottom: 15px;">
        <textarea class="form-control input-md" name='salecontent' id='salecontent' rows='20' cols='70'></textarea>
      </div>
    </div>
    
    <div class="form-group" style="width: 500px; display: flex;"> 
      <label for="file1MF" class="col-md-2 control-label" style="width: 22%; padding-top: 2px;">첨부 파일 </label>
      <div class="col-md-10" style="text-align: center; width: 100%;">
         <input type="file" class="input-md" name='file1MF' id='file1MF' value="파일 선택" size='40' style="border: none; line-height: 0px;">
        </div>
    </div>
        
    <div class="form-group">       
      <div class="col-md-10"style="width: 100%;"> 
        
       <div style="float: right;">
        <button class="btn btn-default" type="button" onclick="create_check()" >등록</button>
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