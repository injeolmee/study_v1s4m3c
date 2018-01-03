<%@ page contentType="text/html; charset=UTF-8"%>
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
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/javascript">

$(function(){
   
});

function seqnoUp(noticeno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', '/study/admin/notice/nseqno_up.do');
  $('#noticeno', frm_seqno).val(noticeno);
  frm_seqno.submit();
  
}


function seqnoDown(noticeno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', '/study/admin/notice/nseqno_down.do');
  $('#noticeno', frm_seqno).val(noticeno);
  frm_seqno.submit();

}

function notice_delete(noticeno) {
  $('#noticeno', frm_delete).val(noticeno);
  $('#myModal').modal();
}


</script>
 
<script type="text/javascript">
</script>
</head>
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content' style='width: 70%;'> 

  <div class="modal fade modal-sm" id="myModal" role="dialog" style='position: absolute; top:35%; left:58%; display: none;'>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">공지사항 삭제</h4>
      </div>
      <div class="modal-body" style='text-align: center; height: 70px;'>
        <span>삭제하면 복구할 수 없습니다.</span>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" id="delete_btn" onclick="javascript: $('#frm_delete').submit();">삭제</button>
        <button class="btn" data-dismiss="modal">닫기</button>    
      </div>
    </div>
  </div>

<DIV id='main_panel'></DIV>

<DIV class='title_line' style='font-size: 20px;'>공지사항</DIV>

  <ASIDE style='float: right;'>
  <form name='frm' id='frm' method="get" action="./notice_list.do">
    <div class="form-group" style= 'padding: 20px;'>
      <div style='padding-right:0px;'>
        <select class='col-md-1' id="search" name="search" style="width:30%; padding: 0px; margin-bottom: 10px;">
          <option value='ntitle' ${noticeVO.search eq "ntitle" ? "selected" :""}>제목</option>
          <option value='nname' ${noticeVO.search eq "nname" ? "selected" :""}>작성자</option>
          <option value='ncontent' ${noticeVO.search eq "ncontent" ? "selected" :""}>내용</option>
          <option value='ntitle_ncontent' ${noticeVO.search eq "ntitle_ncontent" ? "selected" :""}>제목+내용</option>
        </select>
      </div>
      <div class="col-md-5" style='padding: 0px;'>
    <c:choose>
      <c:when test="${noticeVO.word != '' }">
        <input type='text' name='word' id='word' value='${noticeVO.word }' placeholder="Search for...">
      </c:when>
      <c:otherwise>
        <input type='text' name='word' id='word' value='' placeholder="Search for...">
      </c:otherwise>
    </c:choose>      
      </div>
      <div class="col-md-1">
        <button class="btn btn-default" type="submit" id='search_go'>Go!</button>
      </div> 
      <div class="col-md-1" style='padding: 0px;'>  
        <A style='margin-left:10px;' href="javascript:location.reload();"><img src="<%=root%>/jeimages/refresh.png"></A>
      </div>
    </div>
  </form>
  </ASIDE>
  

         
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 40%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <c:if test="${sessionScope.admauth == 'M' || sessionScope.admauth == 'A'}">
          <col style="width: 10%;"></col>
        </c:if>        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <TH class='th'>번호</th>
          <TH class='th'>출력번호</th>
          <TH class='th'>제목</th>
          <TH class='th'>작성자</th>
          <TH class='th'>등록일</th>
          <TH class='th'>조회수</th>
          <c:if test="${sessionScope.admauth == 'M' || sessionScope.admauth == 'A'}">
            <TH class='th'>출력순서</th>
          </c:if>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="noticeVO" items="${notice_list }">
          <tr>            
            <TD class='td'>${noticeVO.noticeno }</td>     
            <TD class='td'>${noticeVO.nseqno }</td>     
            <TD class='td'>
              <a href="<%=root%>/nonuser/notice/notice_read.do?noticeno=${noticeVO.noticeno}&word=${noticeVO.word}&search=${noticeVO.search}&nowPage=${nowPage}">${noticeVO.ntitle}</a>
            </td> 
            <TD class='td'>${noticeVO.nname }</td>         
            <TD class='td'>${noticeVO.ndate.substring(0, 16)}</td>
            <TD class='td'>${noticeVO.ncnt}</td>
            <c:if test="${sessionScope.admauth == 'M' || sessionScope.admauth == 'A'}">
            <TD class='td'>
              <A href="<%=root%>/admin/notice/notice_update.do?noticeno=${noticeVO.noticeno }&nowPage=${nowPage }&word=${noticeVO.word }&search=${noticeVO.search }"><IMG src='<%=root %>/jeimages/update.png' title='수정'></A>
              <A href="javascript:notice_delete(${noticeVO.noticeno })"><IMG src='<%=root%>/jeimages/delete.png' title='삭제'></A>
              <A href="javascript:seqnoUp(${noticeVO.noticeno })"><IMG src='<%=root%>/jeimages/up.png' title='출력순서 높임'></A>
              <A href="javascript:seqnoDown(${noticeVO.noticeno })"><IMG src='<%=root%>/jeimages/down.png' title='출력순서 감소'></A>
            </td>
            </c:if>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
  </div>
  
  <form id= 'frm_seqno' name= 'frm_seqno' method= 'POST' action=''>
    <input type='hidden' name='noticeno' id='noticeno' value=''>
  </form>
  
  <form id= 'frm_delete' name= 'frm_delete' method= 'POST' action='/study/admin/notice/notice_delete.do'>
    <input type='hidden' name='noticeno' id='noticeno' value=''>
  </form>
    
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='<%=root%>/nonuser/notice/notice_list.do'">전체 목록</button>
    <c:if test="${sessionScope.admauth == 'M' || sessionScope.admauth == 'A'}">
      <button type='button' onclick="location.href='<%=root%>/admin/notice/notice_create.do'">등록</button>
    </c:if>
    <div class= "bottom_menu">${paging }</div>
  </DIV>
  
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html>
 
 