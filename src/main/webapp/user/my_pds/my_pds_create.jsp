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
<title>Study Matching Web Site</title>

<link href="/study/user/my_pds/css/my_pds_style.css" rel='Stylesheet' type='text/css'>
 
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript"> 
  window.onload=function(){
    CKEDITOR.replace('pdscontent');  // <TEXTAREA>태그 id 값
    
    $(':text').first().focus();  // 가장 맨처음 input text에 포커스
  };
</script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>

  <DIV>
    <button class="btn btn-success" style="float: left; margin-top: 10px;" onclick="location.href='/study/user/mystudy/mystudy_space.do?stdlist_no=${param.stdlist_no}'"><i class="icon-left-open"></i>My Study</button>
    <h3 style="float: right"><img src="./images/pencile.png">글 등록하기</h3>
    <hr style="color: #000000; border: solid 2px #000000; clear: both;">
  </DIV>
  
  <DIV style="border: solid 1px #000000; margin-bottom: 20px; padding: 20px;">
    <DIV style="margin-top: 20px;">
      <FORM name="create_frm" id="create_frm" method="POST" action="./create.do" class="form-horizontal" enctype="multipart/form-data">
      
        <!-- 세션에 있는 회원 값 넣어야 함. -->
        <input type="hidden" name="memberno" id="memberno" value="${sessionScope.memberno }">
        <input type="hidden" name="cateno" id="cateno" value=${cookie.cateno.value }>
        <input type="hidden" name="stdlist_no" id="stdlist_no" value=${param.stdlist_no }>
        
        <div class="control-group">
          <label class="control-label" for="title" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/icon.png'>제목</label>
          <div class="controls" style="margin-left: 15px;">
            <input style="width: 90%;" type="text" id="pdstitle" name="pdstitle" placeholder="제목입력" required="required">
          </div>
        </div>
        
        <div class="control-group">
          <div class="controls" style="margin-left: 15px;">
            <textarea style="width: 90%; resize:none; height: 500px;" name="pdscontent" id="pdscontent" placeholder="내용입력"></textarea>
          </div>
        </div> 
        
        <div class="control-group"> 
          <label class="control-label" for="file" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/file_add.png'>첨부파일</label>
          <div class="controls" style="margin-left: 15px;">
            <input type="file" name='file1MF' id='file1MF'>
          </div>
        </div> 
        
        <div class="control-group"> 
          <label class="control-label" for="word" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/word.png'>검색어</label>
          <div class="controls" style="margin-left: 15px;">
            <input type="text" name='pdsword' id='pdsword' placeholder="검색어 입력"> 
          </div>
        </div>
        
        <div class="control-group">
          <label class="control-label" for="passwd" style="width: 10%; text-align: left;"><img src='/study/user/my_pds/images/passwd.png'>비밀번호</label>
          <div class="controls" style="margin-left: 15px;">
            <input type="password" id="pdspasswd" name="pdspasswd" placeholder="비밀번호 입력" maxlength="4" required="required" style="width: 90px;">
          </div>
        </div>
         
        <div class="control-group" style="text-align: center;">
          <button class="btn btn-success" type="submit">등록</button>
          <button class="btn btn-danger" type="button" onclick='history.go(-1);'>취소</button>
        </div>
      </FORM>
    </DIV>
  </DIV>
  
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>