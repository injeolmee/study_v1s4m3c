
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
<link href="${pageContext.request.contextPath }/user/free/gnacss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!---------------------------------------------------------------------->
 <script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/ckfinder/ckfinder.js"></script>
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('freecontent');  // <TEXTAREA>태그 id 값


    //**************등록 버튼에 대한 이벤트 등록 **************
    $('#create_btn').click(function() { 
      var create_data = $('#frm').serialize();
      var freecontent = CKEDITOR.instances.freecontent.getData(); // value값 호출
      create_check(create_data, freecontent);
    });
    //************************************************************
  };
  
  //*********************** 게시글 등록 이벤트 처리 *********************** 
  function create_check (create_data, freecontent) {
    
    var freetitle = $('#freetitle').val();
    var new_data = create_data + freecontent; // form의 내용 + ckeditor 내용
    
    // alert("freecontent: " + freecontent);
    // alert("new_data: " + new_data);

     //********** NULL 값 Check해  입력안했으면 실행하지 못하도록 함 *****************
    if (freetitle == null || freetitle == '') {
      alert("제목을 입력해주세요.");
      return false;
    }
    
    if (CKEDITOR.instances.freecontent.getData().length < 1) {
      alert("내용을 입력해주세요.");
      return false;
    }
    //***************************************************************************************
  
    $.ajax({
      url: "/study/user/free/create.do",
      type: "POST",
      data: new_data,
      dataType: "JSON",
      success: function(data) {
        
        if (data.count == 1) { // 등록 처리가 성공한 경우
          alert("게시글을 등록하였습니다.")
          document.location.href = "/study/nonuser/free/list.do";
        } else { // 등록 처리가 실패한 경우
          alert("오류가 생겨 게시글을 등록하지 못했습니다. 다시 시도해주십시오.");
        }
      }  
    });
  }
  //***************************************************************************** 
  
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container" style='margin-bottom: 100px;'>
<DIV class='content' style='width: 60%; margin: 0px auto; '>  

  <DIV class="title_line"> 게시글 등록 </DIV>
  
  <DIV style="text-align: center; width: 100%;">
  
  <FORM name='frm' id='frm' method='POST' action='' class="form-horizontal" style="text-align: left;">
     <c:if test="${sessionScope.memberno ne null }">
       <input type='hidden' name='memberno' id='memberno' value=${sessionScope.memberno }>
       <input type='hidden' name='freename' id='freename' value=${sessionScope.memname }>
     </c:if> 
     <c:if test="${sessionScope.adminno ne null }">
       <input type='hidden' name='adminno' id='adminno' value=${sessionScope.adminno }>
       <input type='hidden' name='freename' id='freename' value=${sessionScope.admname }>
     </c:if>
  
    <div class="form-group">   
      <div class="col-md-10" style="width: 100%; margin-top: 30px;">
      <label for="title" class="control-label" style="margin-right: 30px;">제목</label>
      <input type='text' class="form-control input-md" name='freetitle' id='freetitle' value='' required="required" style='width: 60%;'>
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
        <button class="btn btn-default" type="button" id="create_btn" onclick="" >등록</button>
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