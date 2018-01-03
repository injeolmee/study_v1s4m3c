<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>CONTEST</title>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  
<script type="text/javascript">
$(function(){
  good();
});

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
  d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
  if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
  obj.visibility=v; }
}

//<div id='file2Panel'> 태그의 width에 맞추어 자동 축소
function imgResize() {
  loading = loading + 1;
  var file2 = $('#file2');
  var width = file2.width();
  // alert(width);
  
  if (file2 != null) {
    // 이미지 width가 화면의 70%보다 크다면
    if (width > screen.width-(screen.width * 0.3)) {  
      // file1.width(600); // 이미지 축소
      $('#file2Panel').attr('width', '100%');
      file2.css('width', '100%'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
    } else {
      // 작은 이미지는 그대로 출력
    }
  }
  
  var timer = setInterval(imgResize, 100); // 0.1 초
  
  if (loading == 2) {
    clearInterval(timer) // 타이머 종료, 함수 실행 종료
  }
}

function good(){
  
  var conNo = ${contestVO.conNo};
  var params = "conNo="+conNo;
  
  $.ajax({
    url : '/study/conlike/good.do',
    type: "GET",
    data : params,
    dataType : 'JSON',
    success : function(data){
      
      var src='';
      /* alert(data.count); */
      
      if(data.count == 0){ //회원이 이 글에 대해서 체크를 안했다면
          src = "<img id='good' onclick='good_y();' src='./images/star.png'>";
        }else if(data.count == 1 || data.count == 2){ //로그인을 하지 않았거나 체크를 했었다면
          src = "<img id='good' onclick='good_y();' src='./images/yellow_star.png'>";
        }
       $("#good_").html(src);
      }
    })
};

function good_y(){
 var good = $("#good");
 var str = good.attr('src');
 console.log(str); 
 
 if(str == './images/star.png'){
   good.attr("src", "./images/yellow_star.png");
   good_up();
 }else if( str == './images/yellow_star.png'){
   good.attr("src", "./images/star.png");
   good_down();
 }
};

function good_up(){
  var conNo = ${contestVO.conNo};
  var params = "conNo="+conNo;
  $.ajax({
    url : '/study/conlike/good_up.do',
    type: "GET",
    data : params,
    dataType : 'JSON',
    success : function(data){
      if(data.count ==1 ){
        alert("추천되었습니다.");
      }
    }
  })
};

function good_down(){
  var conNo = ${contestVO.conNo};
  var params = "conNo="+conNo;
  $.ajax({
    url : '/study/conlike/good_down.do',
    type: "GET",
    data : params,
    dataType : 'JSON',
    success : function(data){
      if(data.count ==1 ){
        alert("추천이 해제되었습니다.");
      }
    }     
  })
};

</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content'>

  <DIV style="width: 700px; padding: 20px 0 40px 0; margin: 0px auto;">
    <DIV class="info_tit">
      ${contestVO.conTitle }
    </DIV>
    <DIV class="cb t35"></DIV>
    
    <DIV class="info_img">
      <DIV>
        <c:choose>
          <c:when test="${contestVO.conThumb != ''}">
            <img id= 'conThumb' src= './storage/${contestVO.conThumb }' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;'> <!-- 이미지 파일명 출력 -->
          </c:when>
          <%-- <c:when test="${contestVO.conFile1 != ''}">
          ${contestVO.conFile1 } <!-- 일반 파일명 출력 -->
          </c:when> --%>
          <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->                
            <img id= 'conThumb' src= './images/none1.jpg' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;;'>
          </c:otherwise>
        </c:choose>
      </DIV>
      <div style="float: right; padding-top: 5px !important;">
        <img src="./images/zoom.png" alt="" onclick="MM_showHideLayers('pup_bg','','show','pup_cont','','show')" style="cursor:pointer;">
      </div>
      <div id='good_' style='float: left;'></div>
    </DIV>
    
    <DIV class="info_right">
      <table summary="전체 공모전" class="table2">
        <colgroup>
          <col width="30%">
          <col width="70%">
        </colgroup>
        <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
          <tr style="border-bottom: 1px solid #D9D9D9;">
            <th class="th">주최</th>
            <td class="td">${contestVO.conHost } [ ${contestVO.cdate } ]</td>
          </tr>
          <tr style="border-bottom: 1px solid #D9D9D9;">
            <th class="th">기간</th>
            <td class="td">
              ${contestVO.conStart } ~ ${contestVO.conEnd }
              <c:choose>
                <c:when test="${contestVO.conRemain_e < 0}">
                  <IMG src="./images/apply_end.png">
                </c:when>
                <c:when test="${contestVO.conRemain_s > 0}">
                  <IMG src="./images/apply_before.png">
                </c:when>
                <c:when test="${contestVO.conRemain_e == 0}">
                  <div style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">
                    <IMG src="./images/apply.png"> D-DAY
                  </div>
                </c:when>
                <c:otherwise>
                  <div style="color: #55398C; font-size: 11px; line-height: 18px; font-weight: bold;">
                    <IMG src="./images/apply.png"> D-DAY ${contestVO.conRemain_e }일전
                  </div>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
          <tr style="border-bottom: 1px solid #D9D9D9;">
            <th class="th">홈페이지</th>
            <td class="td">
              <a href="#">${contestVO.conUrl }</a>
            </td>
          </tr>
          <tr style="border-bottom: 1px solid #D9D9D9;">
            <th class="th">YouTube</th>
            <td class="td">
              <a href="#">${contestVO.conYou }</a>
            </td>
          </tr>
          <tr style="border-bottom: 1px solid #D9D9D9;">
            <th class="th">공모전 포스터</th>
            <td class="td">
              <c:if test="${contestVO.conSize2 > 0}">
               <A href='${pageContext.request.contextPath}/download?dir=/contest/storage&filename=${contestVO.conFile2}&downname=${contestVO.conFile2}'>${contestVO.conFile2}</A> 
               (${contestVO.size2Label})
              </c:if>
            </td>
          </tr>
          <tr style="border-bottom: 1px solid #D9D9D9;">
            <th class="th">조회수</th>
            <td class="td">${contestVO.conCnt }</td>
          </tr>
        </tbody>
      </table>
    </DIV>
    
    <DIV class="cb t30"></DIV>
    
    <DIV class="info_bott">
      <DIV class="tap_bg1">
        <IMG src="./images/cont_img04.png" alt="모집요강">
      </DIV>
      <DIV class="info_c" style="line-height: 23px;">
      </DIV>
    </DIV>
    <DIV style="border-bottom: 1px solid #CACACA;">
      <DIV class="cb t10"></DIV>
        <DIV id="file2Panel">
          <c:set var='file2' value="${fn:toLowerCase(contestVO.conFile2)}" />              
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              <IMG id='file2' src='./storage/${contestVO.conFile2}' >
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              <IMG id='file2' src='./storage/${contestVO.conFile2}' >
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
              <IMG id='file2' src='./storage/${contestVO.conFile2}'>
            </c:when>
          </c:choose>
        </DIV>
        ${contestVO.conCont }
        <DIV class="cb t10"></DIV>
        <DIV style="text-align: center;">
          <DIV id="id_0" class="button_example" style="cursor:pointer;" onclick="location.href='${pageContext.request.contextPath}/download?dir=/contest/storage&filename=${contestVO.conFile3}&downname=${contestVO.conFile3}'">
            <!-- <DIV class="glyphicon glyphicon-download-alt"></DIV> -->
            밀어서 다운로드 (${contestVO.size3Label})
          </DIV>
        </DIV>
        <DIV class="cb t10"></DIV>
      <DIV class="cb t10"></DIV>
    </DIV>
    <DIV class="cb t10"></DIV>
    
    <DIV style="float: right;">
      <DIV style="float: left; padding-right: 3px !important;">
        <c:choose>
          <c:when test="${contestVO.conNo == min}">
            <script>alert("가장 처음 게시물입니다.")</script>
            <%-- <A href="./read.do?cateno=${contestVO.cateno }&conNo=${contestVO.conNo}">
              <IMG src="./images/preview.png" alt="이전">
            </A> --%>
          </c:when>
          <c:otherwise>
            <A href="./read.do?cateno=${contestVO.cateno }&conNo=${contestVO.conNo - 1}">
              <IMG src="./images/preview.png" alt="이전">
            </A>          
          </c:otherwise>
        </c:choose>
      </DIV>
      <DIV style="float: left;">
        <c:choose>
          <c:when test="${contestVO.conNo == max }">
            <script>alert("가장 마지막 게시물입니다.")</script>
          </c:when>
          <c:otherwise>
            <A href="./read.do?cateno=${contestVO.cateno }&conNo=${contestVO.conNo + 1}">
              <IMG src="./images/next.png" alt="다음">
            </A> 
          </c:otherwise>
        </c:choose>
      </DIV>
      <DIV style="float: left; padding-right: 2px !important;">
        <A href="./list_all_contest.do">
          <IMG src="./images/list.png" alt="리스트">
        </A>
      </DIV>
    </DIV>
    
    <DIV class="cb t10"></DIV>
  </DIV>
  
  <DIV id="pup_cont" style="visibility: hidden;">
    <DIV class="pup_btn">
      <img src="./images/close.png" alt="끄기" onclick="MM_showHideLayers('pup_bg','','hide','pup_cont','','hide')" style="cursor:pointer;">
    </DIV>
    <DIV style="display: block;">
      <c:choose>
          <c:when test="${contestVO.conThumb != ''}">
            <img id= 'conThumb' src= './storage/${contestVO.conThumb }' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;'> <!-- 이미지 파일명 출력 -->
          </c:when>
          <%-- <c:when test="${contestVO.conFile1 != ''}">
          ${contestVO.conFile1 } <!-- 일반 파일명 출력 -->
          </c:when> --%>
          <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->                
            <img id= 'conThumb' src= './images/none1.jpg' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;;'>
          </c:otherwise>
        </c:choose>
    </DIV>
    <DIV class="cb"></DIV>
  </DIV>
  <DIV id="pup_bg" style="visibility: hidden;"></DIV>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>