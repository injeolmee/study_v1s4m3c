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
<title></title> 
 
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
 
<body>

<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content' style='width: 70%;'>

  <ASIDE style='float: left;'>
      
  </ASIDE>
  <ASIDE style='float: right;'>
  <form name='frm' id='frm' method="get" action="./admin_list.do">
    <div class="form-group" style= 'padding: 20px;'>
      <div style='padding-right:0px;'>
        <select class='col-md-1' id="search" name="search" style="width:25%; margin-bottom: 10px;">
          <option value='adminno' ${param.search eq "adminno" ? "selected" :""}>번호</option>
          <option value='admid' ${param.search eq "admid" ? "selected" :""}>ID</option>
          <option value='admname' ${param.search eq "admname" ? "selected" :""}>이름</option>
        </select>
      </div>
      <div class="col-md-5" style='padding: 0px;'>
    <c:choose>
      <c:when test="${param.word != '' }">
        <input type='text' name='word' id='word' value='${param.word }' placeholder="Search for...">
      </c:when>
      <c:otherwise>
        <input type='text' name='word' id='word' value='' placeholder="Search for...">
      </c:otherwise>
    </c:choose>      
      </div>
      <div class="col-md-1">
        <button class="btn btn-default" type="submit" id='search_go'>Go!</button>
      </div> 
      <div class="col-md-1">  
        <A style='margin-left:10px;' href="javascript:location.reload();"><img src="<%=root%>/jeimages/refresh.png"></A>
      </div>
    </div>
  </form>
  </ASIDE>
 
<DIV class='title_line'>관리자 목록</DIV>
 
<TABLE class='table'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
  </colgroup>
  <TR>
    <TH class='th'>NO</TH>
    <TH class='th'>ID</TH>
    <TH class='th'>EMAIL</TH>
    <TH class='th'>이름</TH>
    <TH class='th'>권한</TH>
    <TH class='th'>가입일</TH>
    <TH class='th'>기타</TH>
  </TR>
 
  <c:forEach var="adminVO" items="${admin_list }">
    <c:set var="adminno" value ="${adminVO.adminno}" /> 
  <TR>
    <TD class='td'>${adminno}</TD>
    <TD class='td'>${adminVO.admid}</TD>
    <TD class='td'>${adminVO.admemail}</TD>
    <TD class='td'>${adminVO.admname}</TD>
    <TD class='td'>${adminVO.admauth}</TD>
    <TD class='td'>${adminVO.admdate}</TD> <!-- 년월일 -->
    <TD class='td'>
      <A href="<%=root %>/master/admin_read_master.do?adminno=${adminno}"><IMG src='<%=root %>/jeimages/read.png' title='조회'></A>
      <A href="<%=root %>/admin/admin/admin_update.do?adminno=${adminno}"><IMG src='<%=root %>/jeimages/update.png' title='수정'></A>
      <A href="<%=root %>/admin/admin/admin_delete.do?adminno=${adminno}"><IMG src='<%=root %>/jeimages/delete.png' title='삭제'></A>
    </TD>
    
  </TR>
  </c:forEach>
  
</TABLE>
 
<DIV class='bottom_menu'>
    <button type='button' onclick="location.href='<%=root%>/master/admin_list.do'">전체 목록</button>
  <div class= "bottom_menu">${paging }</div>
</DIV>
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>