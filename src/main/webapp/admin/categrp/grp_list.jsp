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
    

  
  $('#create_send').click(function(){
   
    var grpno = "grpno=" + $('#grpno').val();
    
    $.ajax({    
      url: "./grpno_check.do",
      type: "get",
      cache: false,
      dataType: "json", // or html
      data: grpno,
      success: function(data){
        /* alert("data.cnt: " + data.cnt); */
        if(data.cnt=="1"){
          msg = "<span style='color:red;'>중복된 카테그룹 번호입니다.</span><br>";
          $('#cateno_check').html(msg); 
          $('#cateno_check').show();
          return false;
        }else{
          $("#frm_create").submit();
        }
      }
    });
    
  });

  });  

  function update(grpno){
    $('#panel_update').show();    
    $('#panel_create').hide();
    
    $.ajax({
      url: "./grp_update.do",
      type: "GET",
      cache: false,
      dataType: "json", // or html
      data: 'grpno=' + grpno,
      success: function(data){
        var frm_update = $('#frm_update'); // id 검색
        $('#grpno', frm_update).val(data.grpno);        
        $('#grpname', frm_update).val(data.grpname);        
        $('#grpseqno', frm_update).val(data.grpseqno);  
        // select-option
        $('#grpvisible', frm_update).val(data.grpvisible).prop("selected", true);

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
    $('#grpname', frm_create).val('');            
    $('#grpseqno', frm_create).val('');            
    $('#grpvisible', frm_create).val('');     
        
    $('#panel_create').show();
    $('#panel_update').hide();
    
    $('#grpname', frm_create).focus(); // show() 호출후 선언.   
  }
  
  function deleteOne(grpno) {
    var msg = "카테고리를 삭제합니다 <br><br>";
    msg += "삭제하면 복구할수 없습니다.<br><br>";

    var panel = "";
    panel += "<DIV id='main_panel' class='popup1'>";
    panel += msg;
    
    var frm_delete = $('#frm_delete');
    $('#grpno', frm_delete).val(grpno);
    
    // panel += "<br>[<A href='./delete.do?cateno="+cateno+"' style= 'color:#d9d9d9;'>삭제 진행</A>]";
    panel += "<br>[<A href='javascript: frm_delete.submit()' style= 'color:#d9d9d9;'>삭제 진행</A>]";
    panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\" style= 'color:#d9d9d9;'>CLOSE</A>]";
    panel += "</DIV>";    
    
    $('#main_panel').html(panel);      
    $('#main_panel').show();
    
  }
  
  
  function seqnoUp(grpno) {
    var frm_seqno = $('#frm_seqno');
    frm_seqno.attr('action', './grpseqno_up.do');
    $('#grpno', frm_seqno).val(grpno);
    frm_seqno.submit();
    
  }
  
  
  function seqnoDown(grpno) {
    var frm_seqno = $('#frm_seqno');
    frm_seqno.attr('action', './grpseqno_down.do');
    $('#grpno', frm_seqno).val(grpno);
    frm_seqno.submit();

  }
  

</script>
 
</head> 
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>

<DIV class='content' style='padding-bottom: 50px;'>

  <DIV id='main_panel'></DIV>

  <DIV class='title_line' style='margin-bottom: 50px;'>카테그룹</DIV>
 
   <div id="cateno_check" style= "width: 200px; text-align: center; margin: 10px auto;"></div>
 
<DIV id='panel_create' style='padding: 10px; background-color: #DDDDDD; width: 100%; text-align: center;'>
<FORM name='frm_create' id='frm_create' method='POST' action='./grp_create.do'>

  <label for='cateno'>카테그룹 번호</label>
  <input type='number' name='grpno' id='grpno' size='5' value='' required="required" style='width: 3%;'>  

  <label for='catename'>카테그룹 이름</label>
  <input type='text' name='grpname' id='grpname' size='15' value='' required="required" style='width: 20%;'>
 
  <label for='cateseqno'>출력 순서</label>
  <input type='number' name='grpseqno' id='grpseqno' value='' required="required" style='width: 3%;'>
  
  <label for='catevisible'>출력 형식</label>
  <!-- <input type='text' name='visible' id='visible' value='Y' required="required" style='width: 5%;'> -->
  <select name= 'grpvisible' id= 'grpvisible' style='width: 5%;'>
    <option value= 'Y' selected= 'selected'>Y</option>
    <option value= 'N'>N</option>
  </select>

  <button type="button" id="create_send" name="create_send">등록</button>
  <button type="button" onclick="create_update_cancel()">취소</button>
 
</FORM>
</div>



<DIV id='panel_update' style='padding: 10px; background-color: #DDDDDD; width: 100%; text-align: center;'>
<FORM name='frm_update' id='frm_update' method='POST' action='./grp_update.do'>
<input type='hidden' name='grpno' id=grpno value='${categrpVO.grpno }'>

  <label for='grpno'>카테그룹 번호</label>
  <input type='number' name='grpno' id='grpno' size='5' value='' required="required" style='width: 3%;'>  

  <label for='grpname'>카테그룹 이름</label>
  <input type='text' name='grpname' id='grpname' size='15' value='' required="required" style='width: 20%;'>
 
  <label for='grpseqno'>출력 순서</label>
  <input type='number' name='grpseqno' id='grpseqno' value='' required="required" style='width: 3%;'>
  
  <label for='grpvisible'>출력 형식</label>
  <!-- <input type='text' name='visible' id='visible' value='Y' required="required" style='width: 5%;'> -->
  <select name= 'grpvisible' id= 'grpvisible' style='width: 5%;'>
    <option value= 'Y' selected= 'selected'>Y</option>
    <option value= 'N'>N</option>
  </select>
 
  <button type="submit" id='submit'>저장</button>
  <button type="button" onclick="create_update_cancel()">취소</button>
</FORM>
</div>
  
  
  
  <form id= 'frm_delete' name= 'frm_delete' method= 'POST' action='./grp_delete.do'>
    <input type='hidden' name='grpno' id='grpno' value=''>
  
  </form>
  
  
  <form id= 'frm_seqno' name= 'frm_seqno' method= 'POST' action=''>
    <input type='hidden' name='grpno' id='grpno' value=''>
  
  </form>


 
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 30%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/> 
  </colgroup>
  <thead>  
  <TR>   
    <TH style='text-align: center ;'>grpno</TH>
    <TH style='text-align: center ;'>grpname</TH>
    <TH style='text-align: center ;'>grpseqno</TH>
    <TH style='text-align: center ;'>grpvisible</TH>
    <TH style='text-align: center ;'>기타</TH>
    
  </TR>
  </thead>
  <tbody>    
    <c:forEach var= "categrpVO" items="${grp_list}">
    <TR>      
      <TD style='text-align: center;'>${categrpVO.grpno }</TD>
      <TD style='text-align: center;'><a href='<%=root %>/admin/category/cate_list.do?grpno=${categrpVO.grpno}'>
      ${categrpVO.grpname }</a>
      </TD>      
      <TD style='text-align: center;'>${categrpVO.grpseqno }</TD>      
      <TD style='text-align: center;'>${categrpVO.grpvisible }</TD>

      <TD style='text-align: center;'>
        <A href="javascript:update(${categrpVO.grpno })"><IMG src='./jecss/images/update.png' title='수정'></A>
        <A href="javascript:deleteOne(${categrpVO.grpno })"><IMG src='./jecss/images/delete.png' title='삭제'></A>
        <A href="javascript:seqnoUp(${categrpVO.grpno })"><IMG src='./jecss/images/up.png' title='우선 순위 높임'></A>
        <A href="javascript:seqnoDown(${categrpVO.grpno })"><IMG src='./jecss/images/down.png' title='우선 순위 감소'></A>
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