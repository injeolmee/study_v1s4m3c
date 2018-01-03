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
<title>회원 조회</title> 
 
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script type="text/javascript">
var msg = "";

$(function(){   

  
}); // function 끝

  
</script>

</head> 
 
<body>

<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'>

  <ASIDE style='float: left;'>
      
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE>
  
  <div id="main_panel"></div>


<!--   <div class="modal fade modal-sm" id="myModal" role="dialog" style='position: absolute; top:35%; left:58%;'>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">패스워드 검사</h4>
      </div>
      <div class="modal-body" style='text-align: center; height: 70px;'>
        <label style='float: left; margin: 5px 0px 5px 5px;'><strong>패스워드</strong></label>
        <input type="password" id="modal_pwd" name="modal_pwd" value="" style='padding: 6px; margin: 0px; width:50%;' >
        <input type="text" style="display: none;">
        <div id="panel_pwd" style= "text-align: center; padding: 15px; font-weight: 900;"></div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" id="update_btn" onclick="location href=''">확인</button>
        <button class="btn" data-dismiss="modal">닫기</button>    
      </div>
    </div>
  </div> -->


 
<DIV class='title_line' style='margin: 50px auto; font-size: 20px;'>관리자 정보</DIV>

  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 아이디</strong></div>
    <div class="col-md-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admid1" name="admid" value="${adminVO.admid }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 이메일</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admemail" name="admemail" value="${adminVO.admemail }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 이름</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admname" name="admname" value="${adminVO.admname }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>  
  </div>     
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 권한</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="admauth" name="admauth" value="${adminVO.admauth }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>  
  </div>     

  <div class="form-group" style='padding: 30px;'>        
    <div class="col-sm-offset-5 col-sm-5" style='padding: 30px; margin-bottom: 150px; margin-top: 50px;'>
      <%-- <button type="button" class="btn btn-success" onclick="location.href='<%=root %>/user/member/mem_update.do?memberno=${memberVO.memberno}'">정보 수정하기</button> --%>
      <button type="button" class="btn btn-success" onclick="location.href='<%=root %>/admin/admin/admin_update.do?adminno=${adminVO.adminno}'">정보 수정하기</button>
    </div>
  </div>
  
 


 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 