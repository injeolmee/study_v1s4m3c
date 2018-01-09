<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>리뷰 등록</title> 
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
  
  //*********************** 게시글 등록 이벤트 처리 *********************** 
  function create_check() {
     
    var formData = new FormData();
    
    formData.append("rvmemname", $("input[name=rvmemname]").val());
    formData.append("memberno", $("input[name=memberno]").val());
    formData.append("rono", $("input[name=rono]").val());
    formData.append("rvdate", $("input[name=rvdate]").val());
    formData.append("rvgood", $("input[name=rvgood]:checked").val());
    formData.append("rvcont", $("textarea[name=rvcont]").val());
    // formData.append("rvup", $("input[name=rvup]").val());

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
   
  if($("input[name=file1MF]")[0].files[0] == null) { // 파일 업로드 X의 경우
    //alert("그냥업로드 실행"); 
  
    var frm = $('#frm').serialize(); // 위의 코드 사용하지 않고 파일 제외한 모든 데이터를 serialize
    create_nom(frm);
    
  } else { // 파일 업로드하는 경우
    //alert("파일업로드 실행");
  
    formData.append("file1MF", $("input[name=file1MF]")[0].files[0]); // 파일 업로드까지 추가
   
    create_file(formData);
    }
  }
 //***************************************************************************** 
 
 //************************ 파일까지 포함한 create.do ***********************
 function create_file(formData) { 
   alert("rono(file) "+formData.get("rono"))
   alert("rono(file1MF) "+formData.get("file1MF"))
   $.ajax({
     url: "/study/user/review/create.do",
     type: "POST",
     data: formData,
     dataType: "JSON",
     enctype : "multipart/form-data",
     processData: false,      // 필수 코드 1
     contentType: false,      // 필수 코드 2
     success: function (formData) {
       alert("rono3="+rono);
       if (formData.count == 1) { // 등록 처리가 성공한 경우
         alert("게시글을 등록하였습니다.");
         window.opener.document.location.href = window.opener.document.URL;    // 부모창 새로고침​
         self.close();        // 팝업창 닫기
        //window.opener = window.location.href; self.close();
        /*  document.location.href = "/study/room/read.do?rono=${roomVO.rono}"; */
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
     url: "/study/user/review/create.do",
     type: "POST",
     data: data,
     dataType: "JSON",
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("게시글을 등록하였습니다.")
         window.opener.document.location.href = window.opener.document.URL;    // 부모창 새로고침​
         self.close();        // 팝업창 닫기
         /* document.location.href = "/study/room/read.do?rono=${roomVO.rono}"; */
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 게시글을 등록하지 못했습니다. 다시 시도해주십시오.");
       } 
     }
   });
 }
 //********************************************************************************
  
  function rvcreate(){

    var params =$('#frm').serialize();
    params += "&file1MF="+$('#file1MF').val();
    
    alert(params);
    $.ajax({
      url : "/study/user/review/create.do",
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
  <DIV class='content' style="width: =100%">
  
  <form id='frm' name='frm' action='' method='POST' enctype="multipart/form-data" class="form-horizontal">
  <input type="hidden" id="rono" name="rono" value="${param.rono }">
  <input type="hidden" id="memberno" name="memberno" value=${sessionScope.memberno }>  
  <input type="hidden" id="rvmemname" name="rvmemname" value='${sessionScope.memname }'>  

    <fieldset class='fieldset_basic'>
      <legend style='font-size: 20px; font-weight: 700; margin: 0px;'>
        <IMG src='./images/view.png' height="30px" width="30px" style='vertical-align: middle;'>&nbsp;스터디룸 후기&nbsp;
      </legend>

      <div>
        <li class="li_none">    
          <div id='rofile1Panel' style='margin: 0px auto 10px auto; text-align:center;'>
            <!-- EL 값을 JSTL 변수에 할당 -->
            <c:set var='rofile1' value="${fn:toLowerCase(roomVO.rofile1)}" />
            <c:choose>
              <c:when test="${fn:endsWith(rofile1, '.jpg')}">
                <IMG id='rofile1' src='/study/admin/room/storage/${roomVO.rofile1}'  width="420px" height="280px">
              </c:when>  
              <c:when test="${fn:endsWith(rofile1, '.gif')}">
                <IMG id='rofile1'  src='/study/admin/room/storage/${roomVO.rofile1}' width="300px" height="200px">
              </c:when>
              <c:when test="${fn:endsWith(rofile1, '.png')}">
                <IMG id='rofile1'  src='/study/admin/room/storage/${roomVO.rofile1}'' width="300px" height="200px">
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
            <span style="vertical-align:middle; font-size: 20px;">평점을 등록해주세요!&nbsp;&nbsp;&nbsp; </span> 
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
              <output for="star-input" style='padding:0px 0px 0px 7px; text-align:left;'><b style='font-size: 20px; font-weight: 550;'>0</b>점</output>           
            </span> 
          </li>
        </div>
      </div>
      
 
      
      <div style='clear: both; width: 100%; margin: 0px auto 10px auto; border: 0.4px solid #ddd'></div>
       
      <TEXTAREA class="form-control" name='rvcont' id='rvcont' rows='8' style='width: 99%; margin-bottom:10px;' placeholder="내용" required="required"></textarea><BR>
      
      <div class="form-group"'>   
        <label for="file1MF" class="col-md-1 control-label" style='font-size: 16px;  font-weight: 700; text-align: left; '>첨부 파일</label>
        <div class="col-md-2">
          <input class='from-control input-md' type="file" name='file1MF' id='file1MF' >
        </div>
        <div class="col-md-2">
         <button type='button' onclick='javascript:create_check()' class="btn btn-default" >등록</button> 
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