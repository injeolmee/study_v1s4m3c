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
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="<%=root%>/nonuser/login/jecss/login.css" rel="Stylesheet" type="text/css">
<link href="<%=root%>/nonuser/login/jecss/login.scss" rel="Stylesheet" type="text/css">
<style>

</style>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=root%>/nonuser/login/login.js"></script>

<script type="text/javascript">

$(function(){   
    
  
}); // function 끝

  
</script>

</head> 
 
<body>

<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content' style='width: 90%; margin: 0px auto;'>

<div class="logmod__container" style='width: 30%; margin: 0px auto; padding-top: 30px;'>
  <ul class="logmod__tabs">
    <li data-tabtar="lgm-1"><a href="#">회원 정보</a></li>
  </ul>
  <div class="logmod__tab-wrapper">
  <div class="logmod__tab lgm-1">
  
    <div class="sminputs">        
      <div class="input full">
        <label class="string optional" for="user-name">* ID</label>
        <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="memid" name="memid" value='${memberVO.memid }' type="text" size="50" disabled="disabled"/>
      </div> 
    </div>
 
    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">* EMAIL</label>
        <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="mememail" name="mememail" value='${memberVO.mememail }' type="email" size="50" disabled="disabled"/>
        <span id="panel_email" style='font-size: 15px;'></span>
      </div>
    </div>
  
    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">* NAME</label>
        <input class="string optional" maxlength="255" id="memname" name="memname" value='${memberVO.memname }' type="text" size="50" disabled="disabled"/>
      </div>
    </div>

    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">* BIRTH</label>
        <c:choose>
          <c:when test="${memberVO.mbirthvb == 'Y'}">
            <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="membirth" name="membirth" value='${memberVO.membirth }' type="text" size="50" disabled="disabled"/>
          </c:when>
          <c:otherwise>
            <span style='font-weight: bolder; color: #a9a9a9; font-size:15px;'>[비공개]</span>
          </c:otherwise> 
        </c:choose>
      </div>
    </div>  
    
    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">* GENDER</label>
      <c:choose>
        <c:when test="${memberVO.mgendervb == 'Y'}">
          <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="memgender" name="memgender" value='${memberVO.memgender }' type="text" size="50" disabled="disabled"/>
        </c:when>
        <c:otherwise>
          <span style='font-weight: bolder; color: #a9a9a9; font-size:15px;'>[비공개]</span>
        </c:otherwise>
      </c:choose>
      </div>
    </div>  
    
    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">* ADDRESS</label>
        <c:choose>
          <c:when test="${memberVO.maddressvb == 'Y'}">
           <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="memaddress" name="memaddress" value="${memberVO.memaddress }" type="text" size="50" disabled="disabled"/>
          </c:when>
          <c:otherwise>
            <span style='font-weight: bolder; color: #a9a9a9; font-size:15px;'>[비공개]</span>
          </c:otherwise>
        </c:choose>
      </div>
    </div>  
    
    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">PHONE</label>
        <c:choose>
          <c:when test="${memberVO.mphonevb == 'Y'}">
            <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="memphone" name="memphone" value="${memberVO.memphone }" type="text" size="50" disabled="disabled"/>
          </c:when>
          <c:otherwise>
            <span style='font-weight: bolder; color: #a9a9a9; font-size:15px;'>[비공개]</span>
          </c:otherwise>
        </c:choose>
      </div>
    </div>  
    
    <div class="sminputs">
      <div class="input full">
        <label class="string optional" for="user-pw">SNS</label>
        <c:choose>
          <c:when test="${memberVO.msnsvb == 'Y'}">
            <input class="string optional" style='margin-bottom: 0px;' maxlength="255" id="memsns" name="memsns" value="${memberVO.memsns }" type="text" size="50" disabled="disabled"/>
          </c:when>
          <c:otherwise>
            <span style='font-weight: bolder; color: #a9a9a9; font-size:15px;'>[비공개]</span>
          </c:otherwise>
        </c:choose>
      </div>
    </div>  
    
    <div class="sminputs" style='height: 150px;'>
      <div class="input full">
        <label class="string optional" for="user-pw">self-introduction</label>
        <div>
        <c:choose>
          <c:when test="${memberVO.mintrovb == 'Y'}">
            ${memberVO.memintro }
          </c:when>
          <c:otherwise>
            <span style='font-weight: bolder; color: #a9a9a9; font-size:15px;'>[비공개]</span>
          </c:otherwise>
        </c:choose>
        </div>
      </div>
    </div>  
    
    <div class="sminputs" style='height: 180px;'>
      <div class="input full">
        <label class="string optional" for="user-pw">PHOTO</label>
        <div>
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
    </div>  
  
 
  </div>
</div>
</div>
  
 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 