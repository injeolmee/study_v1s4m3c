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
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    

<script type="text/javascript">
  $(function(){
    var msg = "";
    $('#panel_create').show();
    $('#panel_update').hide();  


  });  // function 끝

  function update(cateno, grpno){
    $('#panel_update').show();    
    $('#panel_create').hide();

    // alert("cateno: " + cateno);
    var data = 'cateno=' + cateno + '&grpno=' + grpno;
    
    $.ajax({
      url: "./cate_update.do",
      type: "GET",
      cache: false,
      dataType: "json", // or html
      data: data,
      success: function(data){
        var frm_update = $('#frm_update'); // id 검색
        $('#grpno', frm_update).val(data.grpno);        
        $('#cateno', frm_update).val(data.cateno);        
        $('#catename', frm_update).val(data.catename);        
        $('#cateseqno', frm_update).val(data.cateseqno);  
        // select-option
        $('#catevisible', frm_update).val(data.catevisible).prop("selected", true);

      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error: function (request, status, error){  
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;
 
        var panel = "";
        panel += "<DIV id='main_panel' class='popup1'>";
        panel += msg;
        panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);      
        $('#main_panel').show();
      }
    });    
  
  }
  
  function create_update_cancel(){
    var frm_create = $('#frm_create');
    $('#grpno', frm_create).val('');               
    $('#cateno', frm_create).val('');               
    $('#catename', frm_create).val('');            
    $('#cateseqno', frm_create).val('');            
    $('#catevisible', frm_create).val('');     
        
    $('#panel_create').show();
    $('#panel_update').hide();
    
    $('#catename', frm_create).focus(); // show() 호출후 선언.
   
  }
  
  function deleteOne(cateno, grpno) {
    var msg = "카테고리를 삭제합니다 <br><br>";
    msg += "삭제하면 복구할수 없습니다.<br><br>";

    var panel = "";
    panel += "<DIV id='main_panel' class='popup1'>";
    panel += msg;
    
    var frm_delete = $('#frm_delete');
    $('#cateno', frm_delete).val(cateno);
    $('#grpno', frm_delete).val(grpno);
    
    // panel += "<br>[<A href='./delete.do?cateno="+cateno+"' style= 'color:#d9d9d9;'>삭제 진행</A>]";
    panel += "<br>[<A href='javascript: frm_delete.submit()' style= 'color:#d9d9d9;'>삭제 진행</A>]";
    panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\" style= 'color:#d9d9d9;'>CLOSE</A>]";
    panel += "</DIV>";    
    
    $('#main_panel').html(panel);      
    $('#main_panel').show();
    
  }
  
  
  function seqnoUp(cateno, grpno) {
    var frm_seqno = $('#frm_seqno');
    frm_seqno.attr('action', './cateseqno_up.do');
    $('#cateno', frm_seqno).val(cateno);
    $('#grpno', frm_seqno).val(grpno);
    frm_seqno.submit();
    
  }
  
  
  function seqnoDown(cateno, grpno) {
    var frm_seqno = $('#frm_seqno');
    frm_seqno.attr('action', './cateseqno_down.do');
    $('#cateno', frm_seqno).val(cateno);
    $('#grpno', frm_seqno).val(grpno);
    frm_seqno.submit();

  }
  

</script>
 
</head> 
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>

<DIV class='content' style='padding-bottom: 50px;'>

  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='<%=root %>/admin/categrp/grp_list.do'>카테 그룹</A>
  </ASIDE>
  
  <DIV id='main_panel'></DIV>

  <DIV class='title_line' style='margin-bottom: 50px;'>카테고리</DIV>

<DIV id='panel_create' style='padding: 10px; background-color: #DDDDDD; width: 100%; text-align: center;'>
<FORM name='frm_create' id='frm_create' method='POST' action='./cate_create.do'>
<input type='hidden' name='grpno' id='grpno' value='${grpno}'>

  <label for='catename'>카테고리 이름</label>
  <input type='text' name='catename' id='catename' size='15' value='' required="required" style='width: 20%;'>
 
  <label for='cateseqno'>출력 순서</label>
  <input type='number' name='cateseqno' id='cateseqno' value='' required="required" style='width: 3%;'>
  
  <label for='catevisible'>출력 형식</label>
  <!-- <input type='text' name='visible' id='visible' value='Y' required="required" style='width: 5%;'> -->
  <select name= 'catevisible' id= 'catevisible' style='width: 5%;'>
    <option value= 'Y' selected= 'selected'>Y</option>
    <option value= 'N'>N</option>
  </select>

  <button type="submit" id="create_send" name="create_send">등록</button>
  <button type="button" onclick="create_update_cancel()">취소</button>
 
</FORM>
</div>



<DIV id='panel_update' style='padding: 10px; background-color: #DDDDDD; width: 100%; text-align: center;'>
<FORM name='frm_update' id='frm_update' method='POST' action='./cate_update.do'>
<input type='hidden' name='grpno' id='grpno' value=''>
<input type='hidden' name='cateno' id='cateno' value=''>

  <label for='catename'>카테고리 이름</label>
  <input type='text' name='catename' id='catename' size='15' value='' required="required" style='width: 20%;'>
 
  <label for='cateseqno'>출력 순서</label>
  <input type='number' name='cateseqno' id='cateseqno' value='' required="required" style='width: 3%;'>
  
  <label for='catevisible'>출력 형식</label>
  <!-- <input type='text' name='visible' id='visible' value='Y' required="required" style='width: 5%;'> -->
  <select name= 'catevisible' id= 'catevisible' style='width: 5%;'>
    <option value= 'Y' selected= 'selected'>Y</option>
    <option value= 'N'>N</option>
  </select>
 
  <button type="submit" id='submit'>저장</button>
  <button type="button" onclick="create_update_cancel()">취소</button>
</FORM>
</div>
  
  
  
  <form id= 'frm_delete' name= 'frm_delete' method= 'POST' action='./cate_delete.do'>
    <input type='hidden' name='cateno' id='cateno' value=''>
    <input type='hidden' name='grpno' id='grpno' value=''>
  
  </form>
  
  
  <form id= 'frm_seqno' name= 'frm_seqno' method= 'POST' action=''>
    <input type='hidden' name='cateno' id='cateno' value=''>
    <input type='hidden' name='grpno' id='grpno' value=''>
  
  </form>


 
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 30%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/> 
  </colgroup>
  <thead>  
  <TR>   
    <TH style='text-align: center ;'>grpno</TH>
    <TH style='text-align: center ;'>cateno</TH>
    <TH style='text-align: center ;'>catename</TH>
    <TH style='text-align: center ;'>cateseqno</TH>
    <TH style='text-align: center ;'>catevisible</TH>
    <TH style='text-align: center ;'>기타</TH>
    
  </TR>
  </thead>
  <tbody>    
    <c:forEach var= "categoryVO" items="${cate_list}">
    <TR>      
      <TD style='text-align: center;'>${grpno}</TD>
      <TD style='text-align: center;'>${categoryVO.cateno }</TD>
      <TD style='text-align: center;'>${categoryVO.catename }</TD>      
      <TD style='text-align: center;'>${categoryVO.cateseqno }</TD>      
      <TD style='text-align: center;'>${categoryVO.catevisible }</TD>

      <TD style='text-align: center;'>
        <A href="javascript:update(${categoryVO.cateno }, ${grpno})"><IMG src='./jecss/images/update.png' title='수정'></A>
        <A href="javascript:deleteOne(${categoryVO.cateno }, ${grpno})"><IMG src='./jecss/images/delete.png' title='삭제'></A>
        <A href="javascript:seqnoUp(${categoryVO.cateno }, ${grpno})"><IMG src='./jecss/images/up.png' title='우선 순위 높임'></A>
        <A href="javascript:seqnoDown(${categoryVO.cateno }, ${grpno})"><IMG src='./jecss/images/down.png' title='우선 순위 감소'></A>
      </TD>
    </TR>
    </c:forEach>    
  </tbody>
  
 
 
</TABLE>
 
 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 