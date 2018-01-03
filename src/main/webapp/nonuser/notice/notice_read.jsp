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
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!---------------------------------------------------------------------->
<script type="text/javascript">
$(function(){
  
  $('#frm_reply_create').show();
  $('#frm_reply_update').hide();
  
  var noticeno = ${noticeVO.noticeno};
  
  //댓글 리스트 업로드
  reply_List(noticeno, 1);
  
//댓글 입력 버튼 클릭 event
  $('#reply_but_create').click(function(){
    var reply = $("#frm_reply_create").serialize();
    // alert(reply);
    reply_insert(reply);
   
   });    
  
  //댓글 수정 버튼 클릭 event
  $('#reply_but_update').click(function(){
    var reply = $("#frm_reply_update").serialize();
      /* alert(reply);   */
    reply_update(reply);
   
   });
  
}); // $(function(){ 끝

  
  //댓글 입력에 대한 ajax   
  function reply_insert(reply){
     /* alert(reply);  */
    $.ajax({
      url : '/study/user/notice_reply/nrep_create.do',
      type: "POST",
      data : reply,
      dataType : 'JSON',
      success : function(data){
              var noticeno = data.noticeno;
              //댓글입력이 성공하면 댓글 리스트 ajax호출 
              if(data.count==1){
                reply_List(noticeno, 1);
              }      
          }
     });
    }
    
  
//댓글 수정에 대한 ajax   
  function reply_update(reply){
   /* alert("replyupdate");  */
    $.ajax({
      url : '/study/user/notice_reply/nrep_update.do',
      type: "POST",
      data : reply,
      dataType : 'JSON',
      success : function(data){
              var noticeno = data.noticeno;
              var nowPage = data.nowPage;
              //댓글수정이 성공하면 댓글 리스트 ajax호출 
              if(data.count==1){
                reply_List(noticeno, nowPage);
                $('#frm_reply_create').show();
                $('#frm_reply_update').hide();
                
              }      
          }
     });
    }

  //댓글 리스트 ajax
  // 댓글에 대한 출력으로 기존의 스터디그룹 번호와  현재페이지
 function reply_List(noticeno, nowPage){
   $.ajax({
       url : '/study/nonuser/notice_reply/nrep_list.do',
       type : 'get',
       data :  "noticeno="+noticeno+"&nowPage="+nowPage,
       dataType : 'JSON',
       success : function(data){
         /* alert("data:"+data); */
          var reply = "";
          var nrepcont = "";
          var nrepdate = "";
          var nrepname = "";
          var noticeno ;
          var nrepno ;
          
          //댓글 수정을 위한 페이지 유지 nowpage값
          var nowPage = data.nowPage;
          
         /*  alert("nowPage :" +nowPage); */
          
          for(var i=0; i<data.reply.length; i++){
            /* console.log(data.length); */
            nrepcont = data.reply[i].nrepcont;
            nrepdate = data.reply[i].nrepdate;
            nrepname = data.reply[i].nrepname;
            noticeno = data.reply[i].noticeno;
            nrepno = data.reply[i].nrepno;
  
            reply+=  "<DIV style='border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px; height:50px;'>";
            reply+=  " <span style='font-weight: bold; line-height: 30px;'>";
            reply+=  " <IMG src='<%=root%>/jeimages/user.png' style='padding-right: 1%;'>";
            reply+=  nrepname+"</span>";      
            reply+=  " <span style='font-size: 0.7em; color: #999999; padding-left: 2%;'>";
            reply+=  nrepdate+"</span>";
            
            reply+= "<div style='float: right;'>";
            reply+=  "<a style='font-size: 0.8em; color: #999999;' onclick='javascript:check_memberno_reply("+noticeno+","+nrepno+"," + 1 +" )'>수정</a>";
            reply+=  "<a style='font-size: 0.8em; color: #999999;' onclick='javascript:check_memberno_reply("+noticeno+","+nrepno+"," + 2 +" )'> | 삭제</a>";
            reply+= "</div>";
            reply+= "<div>";
            reply+=  "<span style='margin-bottom: 10px;'>"+nrepcont+"</span>";
            reply+= "</div>";
            reply+= "</div>";
            } 
          
         //댓글 수정에서 페이지 유지를 위한 현재페이지 값 전달
          $("#nowPage", frm_reply_update).val(nowPage);
          $("#paging").html(data.paging);
          $("#reply_list").html(reply);
         }
     });
  }
  
  
  //댓글을 수정하거나 삭제하려고 할 때 작성자가 맞는지 검사.
   function check_memberno_reply(noticeno, nrepno, str){
     var params = "noticeno="+noticeno+"&nrepno="+nrepno+"&str="+str;
     $.ajax({
       url : '/study/user/notice_reply/nrep_ck_memberno.do',
       type: "GET",
       data : params,
       dataType : 'JSON',
       success : function(data){
        alert(data.count + "/" + data.str);
        var noticeno = data.noticeno
        var nrepno =  data.nrepno
         if(data.count == 1){
           if(data.str == 1){
             notice_reply_update(noticeno, nrepno);
           }else if(data.str == 2){
             notice_reply_delete(noticeno, nrepno);
           }
         }else{
           alert("수정 및 삭제는 작성자만 가능 합니다.");
         }
       }
     
     });
   }
  
   
   // 댓글 삭제에 대해서 y/n alert 창 출력
   function notice_reply_delete(noticeno, nrepno){
     var r = confirm("삭제하시겠습니까?")
     if (r == true) {
       reply_delete(noticeno, nrepno);
     } else {
       alert("취소되었습니다.");
     }
     
   }
   
   
  //댓글 삭제 처리에 대한 ajax
  function reply_delete(noticeno, nrepno){
    
    var params = "noticeno="+noticeno+"&nrepno="+nrepno;
    
    $.ajax({
      url : '/study/user/notice_reply/nrep_delete.do',
      type: "GET",
      data : params,
      dataType : 'JSON',
      success : function(data){
        var noticeno = data.noticeno;
        if(data.count > 0){
          alert("삭제완료");
          reply_List(noticeno, 1);
        }else{
          alert("삭제가 완료되지 않았습니다.");
        }
        
      }
    });
    
  } 
   
  
  //작성자 본인이 댓글 수정시
  //수정 버튼을 클릭하면 댓글에 대한 값 읽어서  reply_update form에 출력
  function notice_reply_update(noticeno, nrepno){
    $('#frm_reply_create').hide();
    $('#frm_reply_update').show();
    var params = "noticeno="+noticeno+"&nrepno="+nrepno;
    /* alert("params:"+params); */
    $.ajax({
      url : '/study/user/notice_reply/nrep_read.do',
      type: "GET",
      data : params,
      dataType : 'JSON',
      success : function(data){
        var reply_update = $("#frm_reply_update");
        var nrepcont = data.nrepcont;
        var nrepname = data.nrepname;
        var nrepno = data.nrepno;

        $("#nrepcont", reply_update).val(nrepcont);
        $("#nrepname", reply_update).val(nrepname);
        $("#nrepno", reply_update).val(nrepno);
      }
    
    });
  }
  


</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 50%; margin: 0px auto;'>  
    
<%--   <DIV style="width: 105%; padding-bottom: 50px;">
  <c:set var ="memberno" value="${sessionScope.memberno }"/>
    <ASIDE style='float: left; '>
      <c:choose>
        <c:when test="${fn:contains(saleVO.saleno, saleVO.post_num)}">
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/read.do?saleno=${saleVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
        </c:when>
        <c:when test="${fn:contains(saleVO.saleno, saleVO.pre_num)}">
           <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/read.do?saleno=${saleVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
        </c:when>
        <c:otherwise>
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/read.do?saleno=${saleVO.pre_num}&word=${param.word}&search=${param.search}'">이전글</button>
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/read.do?saleno=${saleVO.post_num}&word=${param.word}&search=${param.search}'">다음글</button>
        </c:otherwise>
      </c:choose> 
      </ASIDE> 
      <ASIDE style='float: right;'>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/list.do?word=${param.word}&search=${param.search}'">목록형</button>
        <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/list_grid.do?word=${param.word}&search=${param.search}'">앨범형</button>
        <c:if test="${fn:contains(memberno, saleVO.memberno)}">
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/update.do?saleno=${saleVO.saleno}&word=${param.word}&search=${param.search}'">수정</button>
          <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/sale/delete.do?saleno=${saleVO.saleno }&word=${param.word}&search=${param.search}'">삭제</button>
        </c:if>
      </ASIDE>      
  </DIV> --%>
    
    <fieldset class="fieldset_basic" style="border: solid 2px #dad8d7; font-size: 15px; width: 100%; padding: 10px; margin: 0px auto;">
      <ul style="margin: 0px auto;">
      
        <li class="li_none">
          <div style="line-height: 0px">
            <span style="font-weight: bold; margin-right: 20px;">${noticeVO.ntitle }</span><span style="font-size: 0.8em;">| 출력순서 ${noticeVO.nseqno } </span>
          </div>
          <div style="text-align: right; font-size: 0.8em; line-height: 0px;">작성자 [${noticeVO.nname }]  | ${noticeVO.ndate } | 조회수 ${noticeVO.ncnt} </div>
          <HR style="color: #EEEEEE;">
        </li>
      
        <li class="li_none">
          <div style="width: 100%; margin-top: 30px; margin-bottom: 30px;">
            ${noticeVO.ncontent }
          </div>
        </li>
      </ul>
      <HR style="color: #EEEEEE;">

  
  <div id='reply_list'style='margin-top:70px; width: 70%; margin: 0px auto;'></div>
  <div id="paging" style='text-align: center; margin: 20px;'></div>
  
  <!---------------------------- 댓글 등록창 시작 (새로운 댓글이 등록되는 창) ---------------------------->
  <FORM name='frm_reply_create' id='frm_reply_create' style="text-align: left; width: 70%; margin: 0px auto; margin-bottom: 40px;">
    <input type='hidden' name='noticeno' id='noticeno' value= ${nrepVO.noticeno }>
    <input type='hidden' name='nowPage' id='nowPage' value= '${nrepVO.nowPage }'>
    
    <textarea name= "nrepcont" id="nrepcont" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 90%; height: 76px;"></textarea>
    <button type="button" id="reply_but_create" name= "reply_but_create" style="width: 7%; height:80px; float: right;">등록</button> 
  </FORM>
  <!---------------------------- 댓글 등록창 종료 (새로운 댓글이 등록되는 창) ---------------------------->
  
  <!---------------------------- 댓글 수정창 시작 (기존 댓글이 수정되는 창) ---------------------------->
  <FORM name='frm_reply_update' id='frm_reply_update' action='' style="text-align: left; width: 70%; margin: 0px auto; margin-bottom: 40px;">
    <input type='hidden' name='nrepno' id='nrepno' value= ''>
    <input type='hidden' name='nowPage' id='nowPage' value= '${nrepVO.nowPage}'>
    <input type='hidden' name='noticeno' id='noticeno' value= '${nrepVO.noticeno}'>
    
    <textarea name= "nrepcont" id="nrepcont" rows="100" cols="50" style="width: 90%; height: 76px;"></textarea>
    <button type="button" name="reply_but_update" id="reply_but_update" onclick="" style="width: 7%; height:80px; float: right;">수정</button> 
  </FORM>
  <!---------------------------- 댓글 수정창 종료 (기존 댓글이 수정되는 창) ---------------------------->

  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='<%=root%>/nonuser/notice/notice_list.do?nowPage=${nowPage }&word=${noticeVO.word }&search=${noticeVO.search }'">목록</button>
    <c:if test="${sessionScope.admauth == 'M' || sessionScope.admauth == 'A'}">
    <button type='button' onclick="location.href='<%=root%>/admin/notice/notice_update.do?noticeno=${noticeVO.noticeno }&nowPage=${nowPage }&word=${noticeVO.word }&search=${noticeVO.search }'">수정</button>
    </c:if>
  </DIV>
  
  

  </fieldset> 

  
</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>