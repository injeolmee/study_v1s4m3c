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
<link href="${pageContext.request.contextPath }/user/free/gnacss/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
window.onload=function(){
  CKEDITOR.replace('freecontent');  // <TEXTAREA>태그 id 값
  
  //*********** CKEDITOR에 대한 내용을 받아와 출력 *************
  var content = '${freeVO.freecontent}';
  CKEDITOR.instances.freecontent.setData(content);
  //****************************************************************

  //**************수정 버튼에 대한 이벤트 등록 **************
  $('#update_btn').click(function() { 
    var update_data = $('#frm').serialize();
    var ck_freecontent = CKEDITOR.instances.freecontent.getData();
    update_check(update_data, ck_freecontent);
  });
  //************************************************************
};
  
  //********************* 수정 처리 이벤트 등록  **********************
  function update_check(update_data, ck_freecontent) {
    
    var freetitle = $('#freetitle').val();  // NOT NULL 데이터 Check    
    var new_data = update_data + ck_freecontent; // 두 데이터(serialize와 ckeditor)를 결합한 새로운 데이터
    
    // alert(ck_freecontent);
    // alert(new_data);
    
    if (freetitle == null || freetitle == '') {
      alert("제목을 입력해주세요.");
      return false;
    }
    
    if (ck_freecontent.length < 1) {
      alert("내용을 입력해주세요.");
      return false;
    }
    
    // alert("실행합니다.")
    // alert("update_data: " + update_data);
    
    $.ajax({
      url: "/study/user/free/update.do",
      type: "POST",
      data: new_data,
      dataType: "JSON",
      success: function (data) {
        
        if (data.count == 1) { // 수정 처리가 성공한 경우
          alert("게시글이 수정되었습니다.");
          var check = confirm("변경된 게시글을 확인하시겠습니까?");
          
          if (check == true) { // 확인 버튼을 누를 경우
            document.location.href = "/study/nonuser/free/read.do?freeno=" + data.freeno;
          } else {                // 취소 버튼을 누를 경우
            document.location.href = "/study/nonuser/free/list.do";
          }
          
        } else { // 수정 처리가 실패한 경우
          alert("오류가 생겨 게시글을 수정하지 못했습니다. 다시 시도해주십시오.");
        }
      }
    }); 
  }
//********************************************************************
  
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 60%; margin: 0px auto; '>  

  <DIV class="title_line"> 게시글 수정 </DIV>
  
  <DIV style="text-align: center; width: 100%;">
  
    <FORM name='frm' id='frm' method='POST' action='./update.do'
                             enctype="multipart/form-data" class="form-horizontal" style="text-align: left;">
       <c:if test = "${sessionScope.adminno ne null }">
         <input type='hidden' name='adminno' id='adminno' value='${sessionScope.adminno }'>
       </c:if>
       <c:if test = "${sessionScope.memberno ne null }">
         <input type='hidden' name='memberno' id='memberno' value='${sessionScope.memberno }'>
       </c:if>
       <input type="hidden" name='freeno' id='freeno' value='${freeVO.freeno }'>
  
      <div class="form-group">   
        <div class="col-md-10" style="width: 100%; margin-top: 30px;">
          <label for="title" class="control-label" style="margin-right: 30px;">제목</label>
          <input type='text' class="form-control input-md" name='freetitle' id='freetitle' value='${freeVO.freetitle}' 
           required="required" style='width: 60%;'>
        </div>
      </div>
    
      <div class="form-group">   
        <div class="col-md-10" style="text-align: center; width: 100%; margin-top: 15px; margin-bottom: 15px;">
          <textarea class="form-control input-md" name='freecontent' id='freecontent' rows='20' cols='70'></textarea>
        </div>
      </div>
    
      <div class="form-group">       
        <div class="col-md-10"style="width: 100%;"> 
          <div style="float: right;">
            <button class="btn btn-default" type="button" id="update_btn" onclick="" >변경</button>
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