<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>스터디룸</title> 
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="${pageContext.request.contextPath}/nonuser/room/hidcss/style.css" rel="Stylesheet" type="text/css">
<!--------------------------J Query Part ------------------------------>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/javascript">
$(function(){

  
  var option1 = "${roomVO.option1}";
  var option2 = "${roomVO.option2}";
  var option3 = "${roomVO.option3}";
  var option4 = "${roomVO.option4}";
  var option5 = "${roomVO.option5}";
  
  $("#option1").each(function(){
    if(option1 == "노트북 대여 가능,") {
      $('#option1').prop("checked", true );
    }
  });  
  $("#option2").each(function(){
    if(option2 == "빔 프로젝터 사용 가능,") {
      $('#option2').prop("checked", true );
    }
  });
  $("#option3").each(function(){
    if(option3 == "인쇄/복사 가능,") {
      $('#option3').prop("checked", true );
    }
  });
  $("#option4").each(function(){
    if(option4 == "와이파이 가능,") {
      $('#option4').prop("checked", true );
    }
  });
  $("#option5").each(function(){
    if(option5 == "화이트보드 사용 가능,") {
      $('#option5').prop("checked", true );
    }
  });

  reviewlist(1);
});

  //<div id='file1Panel'> 태그의 width에 맞추어 자동 축소
  function imgResize() {
    loading = loading + 1;
    var rofile1 = $('#rofile1');
    var width = file1.width();
    // alert(width);
    
    if (file1 != null) {
      // 이미지 width가 화면의 70%보다 크다면
      if (width > screen.width-(screen.width * 0.3)) {  
        // file1.width(600); // 이미지 축소
        $('#file1Panel').attr('width', '100%');
        file1.css('width', '100%'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
      } else {
        // 작은 이미지는 그대로 출력
      }
    }
    
    var timer = setInterval(imgResize, 100); // 0.1 초
    
    if (loading == 2) {
      clearInterval(timer) // 타이머 종료, 함수 실행 종료
    }
 
  }
  
  
  // 리뷰 글 리스트 출력
  function reviewlist(nowPage){
    var rono = '${roomVO.rono }'
    var params = 'rono='+rono+"&nowPage="+nowPage;
    var admin = '${sessionScope.admauth}'
    var semem = '${sessionScope.memberno}'
    
    $.ajax({
      url : "/study/nonuser/room/list2.do",
      type : "GET",
      cache : false,
      dataType : "json", // or html
      data : params, 
      success : function(data) {
       
        
        var rvgood = '';
        var rvup = 0;
        var rvcont = '';
        var memberno = '';
        var rvdate = '';
        var rvfile1 = '';
        var rvthumb = '';
        var rvno = '';

        var li = '';
        
        if(data.list2.length > 0){
          for(var i=0; i<data.list2.length; i++){
            rvgood = data.list2[i].rvgood;
            rvup = data.list2[i].rvup;
            rvcont = data.list2[i].rvcont;
            memberno = data.list2[i].memberno;
            rvmemname = data.list2[i].rvmemname;
            rvdate = data.list2[i].rvdate;
            rvfile1 = data.list2[i].rvfile1;
            console.log(rvfile1);
            rvthumb = data.list2[i].rvthumb;
            console.log(rvthumb);
            rvno = data.list2[i].rvno;
            count = data.list2[i].count;
            
            
            li += "<tr>";
            li += "<td style='padding: 8px; text-align: center;'>";
            if(rvgood == 1) {
              li += "<span class='star-output'><span class='out'><span id='a1'></span><label for='a1'>1</label></span></span>";
              }
            if(rvgood == 2) {
              li += "<span class='star-output'><span class='out'><span id='a2'></span><label for='a2'>2</label></span></span>";
              }
            if(rvgood == 3) {
              li += "<span class='star-output'><span class='out'><span id='a3'></span><label for='a3'>3</label></span></span>";
              }
            if(rvgood == 4) {
              li += "<span class='star-output'><span class='out'><span id='a4'></span><label for='a4'>4</label></span></span>";
              }
            if(rvgood == 5) {
              li += "<span class='star-output'><span class='out'><span id='a5'></span><label for='a5'>5</label></span></span>";
              }
            
            li += "</td>";
            li += "<td style='padding: 8px; text-align: right;'><span style='text-decoration: underline;'>"+rvmemname+"</span>&nbsp;&nbsp;(";
            li += rvdate.substring(0, 16);
            /* ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ */
        /*     if(count == 0){
             li +="<a href='javascript: good("+rvno+");'><img src = './images/goodup.png' id='good"+rvno+"'></a>&nbsp;" //★★★★★★★★★★★★★★★★★★★★★
            }else{
              li +="<a href='javascript: good("+rvno+");'><img src = './images/gooddown.png' id='good"+rvno+"'></a>&nbsp;"
            } */
             if(admin == "M" || admin == "A" || semem == memberno) {
              li = li + "&nbsp;<a href='javascript: PopupWindowrvedit("+rvno+")'><IMG src='./images/edit.png' style='width: 15px; height: 15px;' title='수정'  border='0'></a>";
              /* li = li + "&nbsp;<IMG src='./images/delete.png' style='width: 15px; height: 15px;' title='삭제'  border='0' onclick='alert_delete_check("+rvno+")'>"; */
              li = li + "&nbsp;<a href='javascript: alert_rvdelete_form("+rvno+")'><IMG src='./images/delete.png' style='width: 15px; height: 15px;' title='삭제'  border='0'></a>";
             } else {
               
             }
            li += "</td>";
            li += "</tr>";
            li += "<tr>";
            
            if(rvfile1 != '') {
              li = li + "<td style='padding: 8px; text-align: left; border-bottom: 0px solid #ddd;'><IMG id='rvfile1' src='${pageContext.request.contextPath}/user/review/storage/" + rvfile1 + "'></td>"; 
              li = li + "<td style='padding: 8px; text-align: left; border-bottom: 0px solid #ddd;'>" + rvcont +"</td>";
            }
            else {
              li = li + "<td colspan='2' style='padding: 8px; text-align: left; border-bottom: 0px solid #ddd;'>" + rvcont +"</td>";
            }
            
            li += "</tr>";
            li +="<tr>";
            li +="<td colspan='2' style='padding: 8px; text-align: left; border-bottom: 1px solid #ddd;'> 이 리뷰가 도움이 되셨나요?&nbsp;&nbsp;"
              /* ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ */
              if(count == 0){
               li +="<a href='javascript: like("+rvno+");'><img src = './images/goodup.png' style='vertical-align: top;' id='like"+rvno+"'></a>&nbsp;&nbsp;"+rvup+"</td>&nbsp;"
              }else{
                li +="<a href='javascript: like("+rvno+");'><img src = './images/gooddown.png' style='vertical-align: top;' id='like"+rvno+"'></a>&nbsp;&nbsp;"+rvup+"</td>&nbsp;"
              }
            li +="</tr>";
          }    
          
          $('#review2').html(li);
        }
        
        $('#paging').html(data.paging);
      }
    })
  }
  
  
//************* 리뷰 등록 새창*************
  function PopupWindow()
  {
     window.open("/study/user/review/create.do?rono=${roomVO.rono }","popup", "width=700, height=750, left=300, top=0, scrollbars=no,titlebar=no,status=no,resizable=no,fullscreen=no");
  }
//************************************************************************
  
//************* 리뷰 등록 수정 새창 *************
  function PopupWindowrvedit(rvno)
  {
     window.open('/study/user/review/update.do?rono=${roomVO.rono }&rvno='+rvno, "popup", "width=700, height=750, left=300, top=0, scrollbars=no,titlebar=no,status=no,resizable=no,fullscreen=no");
  }
//************************************************************************


//********************** 리뷰 삭제버튼을 누르면 실행되는 alert ******************
function alert_rvdelete_form(rvno){
  
  var check = confirm("리뷰를 삭제하시겠습니까? 삭제 하면 복구 할 수 없습니다.");
  
  if (check == true) { // 댓글 삭제 확인을 눌렀을 경우
      rvdelete_check(rvno);
  } else {                 // 댓글 삭제 취소를 눌렀을 경우
    alert("취소되었습니다.");
  }
};

//************************************************************************

  
  
//********************** 리뷰 삭제 버튼 클릭시 이벤트 처리 ******************
  function rvdelete_check(rvno) {
    
    var param = "rvno=" + rvno;
    var rono = '${param.rono}';
    
    $.ajax({
      url: "/study/user/review/delete.do",
      type: "POST",
      data: param,
      dataType: "JSON",
      success: function (data) {
        
        if (data.count == 1) {
          alert("리뷰가 삭제되었습니다.");
          document.location.href = "/study/nonuser/room/read.do?rono="+rono;
        } else {
          alert("오류가 발생하여 게시글을 삭제하지 못했습니다. 다시 시도해주십시오.");
        }
      }
    }); 
  }
//************************************************************************

function like(rvno){
   var like = $("#like"+rvno+"");
   var str = like.attr('src');

  // console.log(str); 

    if(str == './images/gooddown.png'){
      
          like.attr("src", "./images/goodup.png");
          likecnt_down(rvno);
      
    }else if( str == './images/goodup.png'){
      if( ${sessionScope.memberno == null} ){ // 거짓 에러가 뜸
       alert("로그인해주세요")
      }else{
          like.attr("src", "./images/gooddown.png");
          likecnt_up(rvno);
          }
      }
    
 };

 function likecnt_up(rvno) {
   
   var param = "rvno=" + rvno;
  
   $.ajax({
     url: "/study/user/rvlike/like_up.do",
     type: "GET",
     data: param,
     dataType: "JSON",
     success: function (data) {
       if(data.count == 1){
         alert("좋아요가 반영되었습니다.")
         location.reload();
       }
     }
   }); 
 }
 
 function likecnt_down(rvno) {
   
   var param = "rvno=" + rvno;
  
   $.ajax({
     url: "/study/user/rvlike/like_down.do",
     type: "GET",
     data: param,
     dataType: "JSON",
     success: function (data) {
       
       if(data.count == 1){
         alert("좋아요가 반영 해제되었습니다.")
         location.reload();
       }
     }
   }); 
 }
 
//********************** 스터디룸 글 삭제버튼을 누르면 실행되는 alert ******************
 function alert_rodelete_form(rono){
   $.ajax({
     url: "/study/admin/room/delete.do",
     type: "GET",
     caghe: false,
     dataType: "json",
     data: 'rono='+rono,
     success: function(data) {
       var check = confirm("리뷰 " +data.count+"건이 함께 삭제 됩니다. 삭제 하면 복구 할 수 없습니다. 삭제하시겠습니까?");
       
       if (check == true) { // 글 삭제 확인을 눌렀을 경우
           rodelete_check(rono);
       } else {                 // 글 삭제 취소를 눌렀을 경우
         alert("취소되었습니다.");
       }
       }
   })  
 };

 //************************************************************************
 
//********************** 스터디룸 글 삭제 버튼 클릭시 이벤트 처리 ******************
 function rodelete_check(rono) {
   
   var param = "rono=" + rono;
   
   $.ajax({
     url: "/study/admin/room/delete.do",
     type: "POST",
     data: param,
     dataType: "JSON",
     success: function (data) {
       
       if (data.count == 1) {
         alert("글이 삭제되었습니다.");
         document.location.href = "/study/nonuser/room/list.do"
       } else {
         alert("오류가 발생하여 글을 삭제하지 못했습니다. 다시 시도해주십시오.");
       }
     }
   }); 
 }
//************************************************************************
 
 

</script>
</head>
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 80%;'>
  <div style='clear: both; width: 100%;'></div>   
  <div> 
  <ASIDE style='float: left;'>
    <A href='./list.do?word=${param.word}' style='font-size: 36px; font-weight: bold;'><IMG src='./images/view.png' height="50px" width="50px" style='vertical-align: middle;'> 스터디룸</A>
  </ASIDE>
  
  <ASIDE style='float: right; padding-top: 2%; margin-bottom: 8px;' >
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href='./list.do?ronalo=${param.ronalo}&nowPage=${roomVO.nowPage }'>목록</A>
    <c:choose>
      <c:when test="${sessionScope.admauth == 'M' or sessionScope.admauth == 'A' }">
        <span class='menu_divide' >│</span>
        <A href='${pageContext.request.contextPath}/admin/room/create.do'>등록</A>
        <span class='menu_divide' >│</span>
        <A href='${pageContext.request.contextPath}/admin/room/update.do?rono=${roomVO.rono }'>수정</A>
        <span class='menu_divide' >│</span> 
        <A href="javascript: alert_rodelete_form(${roomVO.rono})">삭제</A>
      </c:when>
      <c:otherwise></c:otherwise>
    </c:choose>
      
  </ASIDE> 
  </div> 
  
  <div class='menu_line' style='clear:both;'></div>
      <fieldset class="fieldset">
        <ul>
        <div>
          <li class="li_none" style="float: left; width: 45%;">
            <c:choose>
            <c:when test="${roomVO.rothumb != ''}">
              <IMG id='rofile1' src='${pageContext.request.contextPath}/admin/room/storage/${roomVO.rofile1}' style='width: 90%; height: 260px; margin: 0px 0px 10px 10px;'> <!-- 이미지 파일명 출력 -->
            </c:when>
            <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->  
              <IMG id='thumb' src='${pageContext.request.contextPath}/admin/room/images/none1.jpg' style='width: 90%; height: 260px; margin: 0px 0px 10px 10px;'>      
            </c:otherwise>      
          </c:choose>               
          <br>
          
          <table summary="스터디룸 정보" style="width: 100%; margin: 0px 0px 0px 20px;">  
            <colgroup>  
              <col width="10%">     
              <col width="90%">  
            </colgroup>
            <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
              <tr>
                <th class="th">카&nbsp;&nbsp;&nbsp;페</th>
                <td class="td" >${roomVO.roname}</td>  
              </tr>
              <tr>
                <th class="th">지&nbsp;&nbsp;&nbsp;역</th>
                <td class="td">${roomVO.rolocation}</td>
              </tr>
              <tr>
                <th class="th">전&nbsp;&nbsp;&nbsp;화</th>    
                <td class="td">
                  <c:choose>
                    <c:when test="${roomVO.rotel == null}">
                      -
                    </c:when>
                    <c:otherwise>
                    ${roomVO.rotel}
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </tbody>
          </table>
          
          <table summary="스터디룸 옵션" style="width: 100%; margin: 0px 0px 0px 20px;">
            <colgroup>  
              <col width="10%">     
              <col width="30%">    
              <col width="12%">
              <col width="40%">  
            </colgroup>
            <tbody>
              <tr>
                <th class="th">사이트</th>
                <td class="td">
                  <c:choose>
                    <c:when test="${roomVO.rosite == null}">  
                      -
                    </c:when>
                    <c:otherwise>
                    ${roomVO.rosite}
                    </c:otherwise>
                  </c:choose>
                </td>
                <th class="th">운영시간</th>
                <td class="td">
                  <c:choose>
                    <c:when test="${roomVO.rorunday == null}">
                      -
                    </c:when>
                    <c:otherwise>
                    ${roomVO.rorunday}
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
              <tr>
                <th class="th">비&nbsp;&nbsp;&nbsp;용</th>
                <td class="td">
                  <c:choose>
                    <c:when test="${roomVO.rocost == null}">
                      -
                    </c:when>
                    <c:otherwise>
                    ${roomVO.rocost}
                    </c:otherwise>
                  </c:choose>
                  </td>      
                <th class="th">룸&nbsp;&nbsp;&nbsp;정보</th>   
                <td class="td">
                  <c:choose>
                    <c:when test="${roomVO.rocount == null}">
                      -
                    </c:when>
                    <c:otherwise>
                    ${roomVO.rocount}
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </tbody>
          </table>
          
          <table summary="스터디룸 옵션" style="width: 100%; margin: 0px 0px 0px 20px;">
            <colgroup>  
              <col width="10%">     
              <col width="90%">
            </colgroup>
            <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
              <tr>
                <th class="th">옵&nbsp;&nbsp;&nbsp;션</th> 
                <td class="td">
                  <label>
                    <input type="checkbox"  name='option1' id='option1' value='노트북 대여 가능,' disabled="disabled" ><span style="font-size: 10px">노트북 대여&nbsp;</span>
                    <input type="checkbox"  name='option2' id='option2' value='빔 프로젝터 사용 가능,' disabled="disabled" ><span style="font-size: 10px">빔 프로젝터&nbsp;</span>
                    <input type="checkbox"  name='option3' id='option3' value='인쇄/복사 가능,' disabled="disabled" ><span style="font-size: 10px">인쇄/복사&nbsp;</span>
                    <input type="checkbox"  name='option4' id='option4' value='와이파이 가능,' disabled="disabled" ><span style="font-size: 10px">와이파이&nbsp;</span>
                    <input type="checkbox"  name='option5' id='option5' value='화이트보드 사용 가능,' disabled="disabled" ><span style="font-size: 10px">화이트보드</span>
                  </label>
                </td>
              </tr>  
            </tbody>
          </table>
          </li>
          <li class="li_none" style="float: left; width: 30%">
          <span> ${roomVO.romap}</span>      
          </li>
          </div>   
 <div style="clear: both;"></div>           
<div class="container" style="margin: 70px 0px 0px 0px; clear: both;">     
  <ul class="nav nav-tabs" style="height: 50px; ">  
    <li class="active" style="width: 15%; height: 50px; text-align: center;"><a href="#home" style="font-size: 20px; width: 100%; height: 50px; text-align: center; line-height: 50px; background-color: #ffc107">상세정보</a></li>
    <li style="width: 15%; height: 50px; text-align: center; "><a href="#review" style="font-size: 20px; width: 100%; height: 50px; text-align: center; line-height: 50px;">스터디룸 리뷰</a></li>   
  </ul>
   
  <div class="tab-content" >      
    <div id="home" class="tab-pane fade in active">
      <h3>내용</h3>      
      <p style="color: #000000">${roomVO.rocontent}
    </div>
    
    
    <div id="review" class="tab-pane fade"> 
    
    <c:choose>
      <c:when test="${sessionScope.memauth == 'U'}">
        <button type='button' onclick='javascript:PopupWindow()' class="btn btn-default" style="float:right;">리뷰 등록</button>      
      </c:when>
      <c:otherwise></c:otherwise>
    </c:choose>
      <table style='width: 80%; border-collapse: collapse; margin: 10px auto;'>
        <colgroup>
          <col style="width: 10%;"></col>   
          <col style="width: 84%;"></col>
        </colgroup>    
        <tbody id='review2'>
        </tbody>
      </table>

  <br><br>
        <DIV class='bottom_menu' id='paging'></DIV>
    </div> 
      
  </div>
</div>     

<script>
$(document).ready(function(){    
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
        $(this).css("background-color", "#ffc107");  
        $(this).parent().siblings().children().css("background-color", "#ffffff");
        $(this).parent().siblings().children().css("border-bottom", "1px solid #ddd");
        $(this).parent().addClass("active");
        $(this).parent().siblings().removeClass("active");
    });
}); 
</script>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html>