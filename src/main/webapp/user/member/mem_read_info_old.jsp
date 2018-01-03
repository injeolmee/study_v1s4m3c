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

 
<DIV class='title_line' style='margin: 50px auto; font-size: 20px;'>회원 정보</DIV>

  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 아이디</strong></div>
    <div class="col-md-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="memid" name="memid" value="${memberVO.memid }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 이메일</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="mememail" name="mememail" value="${memberVO.mememail }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 이름</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="memname" name="memname" value="${memberVO.memname }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
    </div>  
  </div>    
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 생년월일</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
    <c:choose>
      <c:when test="${memberVO.mbirthvb == 'Y'}">
        <input type="text" class="form-control" id="membirth" name="membirth" maxlength="8" value="${memberVO.membirth }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
      </c:when>
      <c:otherwise> 
        <input type="text" class="form-control" id="membirth" name="membirth" maxlength="8" value="비공개" style='padding: 6px; margin: 0px; width:50%; font-weight: bolder; color: #a9a9a9' disabled="disabled">
      </c:otherwise> 
    </c:choose>
    </div>  
  </div>    
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 성별</strong></div>
    <div class="col-sm-6" style='padding-left: 10px; text-align: left;'>
    <c:choose>
      <c:when test="${memberVO.mgendervb == 'Y'}">
        <input type="text" class="form-control" id="memgender" name="memgender" value="${memberVO.memgender }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
      </c:when>
      <c:otherwise>
        <input type="text" class="form-control" id="memgender" name="memgender" value="비공개" style='padding: 6px; margin: 0px; width:50%; font-weight: bolder; color: #a9a9a9;' disabled="disabled">
      </c:otherwise>
    </c:choose>
    </div> 
  </div> 
     
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 주소</strong></div>
    <div class="col-sm-6" style='padding-left: 10px; text-align: left;'>
    <c:choose>
      <c:when test="${memberVO.maddressvb == 'Y'}">
        <input type="text" class="form-control" id="memaddress" name="memaddress" value="${memberVO.memaddress }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
      </c:when>
      <c:otherwise>
         <input type="text" class="form-control" id="memaddress" name="memaddress" value="비공개" style='padding: 6px; margin: 0px; width:50%; font-weight: bolder; color: #a9a9a9;' disabled="disabled">
      </c:otherwise>
    </c:choose>
    </div>  
  </div>   

  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>전화번호</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
    <c:choose>
      <c:when test="${memberVO.mphonevb == 'Y'}">
        <input type="text" class="form-control" id="memphone" name="memphone" value="${memberVO.memphone }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
      </c:when>
      <c:otherwise>
        <input type="text" class="form-control" id="memphone" name="memphone" value="비공개" style='padding: 6px; margin: 0px; width:50%; font-weight: bolder; color: #a9a9a9;' disabled="disabled">
      </c:otherwise>
    </c:choose>
    </div>   
  </div>    
       
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>SNS</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
    <c:choose>
      <c:when test="${memberVO.msnsvb == 'Y'}">
        <input type="text" class="form-control" id="memsns" name="memsns" value="${memberVO.memsns }" style='padding: 6px; margin: 0px; width:50%;' disabled="disabled">
      </c:when>
      <c:otherwise>
        <input type="text" class="form-control" id="memsns" name="memsns" value="비공개" style='padding: 6px; margin: 0px; width:50%; font-weight: bolder; color: #a9a9a9;' disabled="disabled">
      </c:otherwise>
    </c:choose>
    </div>  
  </div>    
   
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>자기소개</strong></div>
    <div class="col-sm-6" style='padding-left: 10px;'>
    <c:choose>
      <c:when test="${memberVO.mintrovb == 'Y'}">
        ${memberVO.memintro }
      </c:when>
      <c:otherwise>
        <span>비공개</span>
      </c:otherwise>
    </c:choose>
    </div>  
  </div>  
 
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-3 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>사진</strong></div>
    <div class="col-sm-4" style='padding: 5px 0px 5px 10px;'>
    <c:set var='memphoto_th' value="${fn:toLowerCase(memberVO.memphoto_t)}" /> 
      <c:choose>
      <c:when test="${memberVO.mphotovb == 'Y'}">
          <c:choose>
            <c:when test="${fn:endsWith(memphoto_th, '.jpg')}">
              <IMG id='memphoto_t' src='<%=root %>/nonuser/login/storage/${memberVO.memphoto_t}' >
            </c:when>
            <c:when test="${fn:endsWith(memphoto_th, '.gif')}">
              <IMG id='memphoto_t'  src='<%=root %>/nonuser/login/storage/${memberVO.memphoto_t}' >
            </c:when>
            <c:when test="${fn:endsWith(memphoto_th, '.png')}">
              <IMG id='memphoto_t'  src='<%=root %>/nonuser/login/storage/${memberVO.memphoto_t}'>
            </c:when>
            <c:otherwise>
              <div style='width:110px; height:120px;'>          
                <IMG id='memphoto_t' src="<%=root %>/jeimages/none2.png">
              </div>         
            </c:otherwise>
          </c:choose>
      </c:when>
      <c:otherwise>
         <div style='width:110px; height:120px;'>          
           <IMG id='memphoto_t' src="<%=root %>/jeimages/none2.png">
         </div>  
      </c:otherwise>
    </c:choose>
    </div> 
  </div>  

  <div class="form-group" style='padding: 30px;'>        
    <div class="col-sm-offset-5 col-sm-5" style='padding: 30px; margin-bottom: 150px; margin-top: 50px;'>
      <%-- <button type="button" class="btn btn-success" onclick="location.href='<%=root %>/user/member/mem_update.do?memberno=${memberVO.memberno}'">정보 수정하기</button> --%>
    </div>
  </div>
 


 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 