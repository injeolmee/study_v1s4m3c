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
<title>Job Information</title>

<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  
<script type="text/javascript">
$(function(){
  
});

/* function MM_findObj(n, d) { //v4.01
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
} */

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

</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content' style="margin-left: 20%;">

  <DIV style="margin-top: 10px; height: 75px; padding: 0px 15px 0px 20px; background: url(/study/nonuser/jobinfo/images/bg_dtlH3.png) left top no-repeat;">
    <h3 style="color: #e7780f; font-size: 20px; font-weight: bold; padding: 13px 0 0 0; background: none; margin: 40px 0 25px 0; line-height: 1.0em;">${jobVO.getJobStatus() }</h3>
    <p style="padding-top: 8px; text-align: right;">
      <b style="font-weight: bold;">조회수</b>${jobVO.getJobCnt() } 
    </p>
  </DIV>
  <DIV style="float: left; width: 61%; margin-bottom: 30px; padding: 20px 24px; background: url(/study/nonuser/jobinfo/images/bg_dtlCoinfo.png) no-repeat left bottom;">
    <DIV style="float: left; width: 24%;">
      <c:choose>
          <c:when test="${jobVO.jobThumb != ''}">
            <img id= 'jobThumb' src= '/study/admin/jobinfo/storage/${jobVO.jobThumb }' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;'> <!-- 이미지 파일명 출력 -->
          </c:when>
          <%-- <c:when test="${contestVO.conFile1 != ''}">
          ${contestVO.conFile1 } <!-- 일반 파일명 출력 -->
          </c:when> --%>
          <c:otherwise> <!-- 파일이 존재하지 않는 경우 -->                
            <img id= 'jobThumb' src= '/study/admin/jobinfo/images/none1.jpg' style= 'width: 100%; height: 123px; margin: 0px 0px 6px 0px;'>
          </c:otherwise>
        </c:choose>
      <!-- <IMG src="./images/babythor.jpg" style="width: 186px; height: 110px; border: 1px solid #ddd;"> -->
    </DIV>
    <DIV style="float: left; width: 76%;">
    <table style="width: 92%; border: none; border-spacing: 0; color: #444; margin-left: 10px; border-collapse: collapse;">
      <colgroup style="display: table-column-group;">
        <col width="15%">
        <col width="*">
        <col width="15%">
        <col width="*">
      </colgroup>
      <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px; background: url(/study/nonuser/jobinfo/images/bul_squ_black.png) no-repeat 10px 12px;">회사명</th>
          <td colspan="3" style="height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            <strong style="color: #1469b3; font-size: 14px; vertical-align: middle;">${jobVO.getComName() }</strong>
          </td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px; background: url(/study/nonuser/jobinfo/images/bul_squ_black.png) no-repeat 10px 12px;">대표자명</th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            ${jobVO.getBossName() }
          </td>
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px; background: url(/study/nonuser/jobinfo/images/bul_squ_black.png) no-repeat 10px 12px;">근로자수</th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            ${jobVO.getLabCnt() }
          </td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px; background: url(/study/nonuser/jobinfo/images/bul_squ_black.png) no-repeat 10px 12px;">근무지역</th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            ${jobVO.getJobLocal() }
          </td>
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px;"></th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            　　　　　　　　　　　　　　
          </td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px; background: url(/study/nonuser/jobinfo/images/bul_squ_black.png) no-repeat 10px 12px;">홈페이지</th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            ${jobVO.getHomepage() }
          </td>
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px;"></th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            　　　　　　　　　　　　　　
          </td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px; background: url(/study/nonuser/jobinfo/images/bul_squ_black.png) no-repeat 10px 12px;">접수기간</th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            ${jobVO.getJobStart() } ~ ${jobVO.getJobEnd() }
            <DIV>
            <c:choose>
              <c:when test="${jobVO.jobRemain_e < 0}">
                <IMG src="/study/nonuser/jobinfo/images/apply_end.png">
              </c:when>
              <c:when test="${jobVO.jobRemain_s > 0}">
                <IMG src="/study/nonuser/jobinfo/images/apply_before.png">
              </c:when>
              <c:when test="${jobVO.jobRemain_e == 0}">
                <IMG src="/study/nonuser/jobinfo/images/apply.png"> 
                <span style="font-size: 12px;">D-DAY</span>
              </c:when>
              <c:otherwise>
                <IMG src="/study/nonuser/jobinfo/images/apply.png"> 
                <span style="font-size: 12px;">D-DAY ${jobVO.jobRemain_e }일전</span>
              </c:otherwise>
            </c:choose>
            </DIV>
          </td>
          <th scope="row" style="height: 29px; vertical-align: top; padding-top: 8px; border-bottom: 1px solid #ddd; padding-left: 18px;"></th>
          <td style="font-size: 12px; height: 29px; padding-left: 10px; border-bottom: 1px solid #ddd; text-align: left;">
            　　　　　　　　　　　　　　
          </td>
        </tr>
      </tbody>
    </table>
  </DIV>
  <DIV style="float: left; margin-top: 20px; width: 863px; background: url(/study/nonuser/jobinfo/images/bg_interestBox_top.png) 0 0 no-repeat;">
    <DIV style="float: left; padding: 11px 0 14px 16px; width: 833px;">
      <ul>
        <li style="list-style: none; float: left; width: 220px;">
          <strong style="display: block; padding-left: 10px; line-height: 21px; background: url(/study/nonuser/jobinfo/images/bul_squ_gray4.png) 0 center no-repeat; font-size: 14px; color: #000; font-size: 18px;">지원 자격</strong>
          <dl style="overflow: hidden; padding: 0 0 5px 10px; vertical-align: middle; display: block; -webkit-margin-before: 1em; -webkit-margin-after: 1em; -webkit-margin-start: 0px; -webkit-margin-end: 0px; margin: 0px auto; font-size: 13px;">
            <dt style="width: 40px; line-height: 20px; color: #333; float: left;">경력</dt>
            <dd style="width: 170px; float: left; line-height: 20px; color: #777; vertical-align: middle;">${jobVO.getCareer() }</dd>
            <dt style="width: 40px; line-height: 20px; color: #333; float: left;">학력</dt>
            <dd style="width: 170px; float: left; line-height: 20px; color: #777; vertical-align: middle;">${jobVO.getEdu() }</dd>
          </dl>
        </li>
        <li style="float: left; width: 288px; list-style: none;">
          <strong style="display: block; padding-left: 10px; line-height: 21px; background: url(/study/nonuser/jobinfo/images/bul_squ_gray4.png) 0 center no-repeat; font-size: 14px; color: #000; font-size: 18px;">근무조건</strong>
          <dl style="overflow: hidden; padding: 0 0 5px 10px; vertical-align: middle; display: block; -webkit-margin-before: 1em; -webkit-margin-after: 1em; -webkit-margin-start: 0px; -webkit-margin-end: 0px; margin: 0px auto; font-size: 13px;">
            <dt style="width: 60px; line-height: 20px; color: #333; float: left;">근무지역</dt>
            <dd style="width: 200px; float: left; line-height: 20px; color: #777; vertical-align: middle;">${jobVO.getJobLocal() }</dd>
            <dt style="width: 40px; line-height: 20px; color: #333; float: left;">임금</dt>
            <dd style="width: 170px; float: left; line-height: 20px; color: #777; vertical-align: middle;">${jobVO.getJobPay()} ${jobVO.getPayLow() }만원 ~ ${jobVO.getPayHigh() }만원</dd>
          </dl>
        </li>
        <li style="float: left; width: 300px; list-style: none;">
          <strong style="display: block; padding-left: 10px; line-height: 21px; background: url(/study/nonuser/jobinfo/images/bul_squ_gray4.png) 0 center no-repeat; font-size: 14px; color: #000; font-size: 18px;">고용형태</strong>
          <dl style="overflow: hidden; padding: 0 0 5px 10px; vertical-align: middle; display: block; -webkit-margin-before: 1em; -webkit-margin-after: 1em; -webkit-margin-start: 0px; -webkit-margin-end: 0px; margin: 0px auto; font-size: 13px;">
            <dt style="width: 60px; line-height: 20px; color: #333; float: left;">고용형태</dt>
            <dd style="width: 200px; float: left; line-height: 20px; color: #777; vertical-align: middle;">${jobVO.getJobEmploy() }</dd>
            <dt style="width: 60px; line-height: 20px; color: #333; float: left;">근무형태</dt>
            <dd style="width: 170px; float: left; line-height: 20px; color: #777; vertical-align: middle;">${jobVO.getJobWork() }</dd>
          </dl>
        </li>
      </ul>
    </DIV>
  </DIV>
  </DIV>
  
  <DIV class="cb t5"></DIV>
  
  <DIV style="width: 60%; border-top: 2px solid #0f7ed4; height: 24px; margin-bottom: 3px; color: #FFF; font-size: 13px; text-align: center; letter-spacing: -0.5px; margin-left: 2%;"></DIV>
  
  <DIV style="width: 695px; margin-left: 9%;">
    <H4 style="clear: both; margin-bottom: 6px; padding-left: 13px; background: url(/study/nonuser/jobinfo/images/bul_donut_blue.png) no-repeat left 3px; color: #0075B0;">모집요강</H4>
    <table style="width: 100%; border: none; border-spacing: 0; color: #444; margin: 0 0 10px 0px; table-layout: fixed;">
      <colgroup>
        <col width="17%">
        <col width="*">
      </colgroup>
      <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit; height: 26px;">
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">직무내용</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">　${jobVO.getJobStatus() }</td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">경력조건</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">　${jobVO.getCareer() }</td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">학력조건</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">　${jobVO.getEdu() }</td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">고용형태</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">　${jobVO.getJobEmploy() }</td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">근무지역</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">　${jobVO.getJobLocal() }</td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">임금조건</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">　${jobVO.getJobPay() }&nbsp;&nbsp;${jobVO.getPayLow() }만원 이상 ~ ${jobVO.getPayHigh() }만원 이하</td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">근무형태</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">　${jobVO.getJobWork() }</td>
        </tr>
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">양식파일</th>
          <td style="border: 1px solid #DDD; text-align: left; border-left: none; line-height: 1.5;">
          <A href="${pageContext.request.contextPath}/download?dir=/study/admin/jobinfo/storage&filename=${jobVO.jobFile2}&downname=${jobVO.jobFile2}">　${jobVO.getJobFile2() }</A>
          </td>
        </tr>
      </tbody>
    </table>
    
    <DIV class="cb t10"></DIV>
    
    <H4 style="clear: both; margin-bottom: 6px; padding-left: 13px; background: url(/study/nonuser/jobinfo/images/bul_donut_blue.png) no-repeat left 3px; color: #0075B0;">추가사항</H4>
    <table style="margin: 0 0 10px 0px; table-layout: fixed; margin-bottom: 30px !important; width: 100%; border: none; border-spacing: 0; color: #444;">
      <thead style="display: table-header-group; vertical-align: middle; border-color: inherit;">
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="text-align: center; color: #1469B3; background: #DEEBF5; border: 1px solid #AFC5E3; font-weight: bold;">더 추가 할 구인조건</th>
        </tr>
      </thead>
      <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <td style="text-align: left; padding: 20px; line-height: 1.5; border: 1px solid #DDD; color: #444; height: 35px;">${jobVO.getJobCont() }</td>
        </tr>
      </tbody>
    </table>
    
  </DIV>
  
  <DIV style="width: 82%; margin: 0px auto;">
    <H4 style="clear: both; margin-bottom: 6px; padding-left: 13px; background: url(/study/nonuser/jobinfo/images/bul_donut_blue.png) no-repeat left 3px; color: #0075B0;">회사주소</H4>
    ${jobVO.getComAddr() }
  </DIV>
  
  <DIV class="cb t10"></DIV>
  
  <DIV style="width: 60%; border-top: 2px solid #0f7ed4; height: 24px; margin-bottom: 3px; color: #FFF; font-size: 13px; text-align: center; letter-spacing: -0.5px; margin-left: 3%;"></DIV>
  
  <DIV style="float: left; margin-left: 3%;">
      <DIV style="float: left; padding-right: 3px !important;">
        <c:choose>
          <c:when test="${contestVO.jobNo == mini}">
            <script>alert("가장 처음 게시물입니다.")</script>
            <%-- <A href="./read.do?cateno=${contestVO.cateno }&conNo=${contestVO.conNo}">
              <IMG src="./images/preview.png" alt="이전">
            </A> --%>
          </c:when>
          <c:otherwise>
            <A href="/study/nonuser/jobinfo/read.do?jobNo=${jobVO.jobNo - 1}">
              <IMG src="/study/nonuser/jobinfo/images/preview.png" alt="이전">
            </A>          
          </c:otherwise>
        </c:choose>
      </DIV>
      <DIV style="float: left;">
        <c:choose>
          <c:when test="${jobVO.jobNo == max }">
            <script>alert("가장 마지막 게시물입니다.")</script>
          </c:when>
          <c:otherwise>
            <A href="/study/nonuser/jobinfo/read.do?jobNo=${jobVO.jobNo + 1}">
              <IMG src="/study/nonuser/jobinfo/images/next.png" alt="다음">
            </A> 
          </c:otherwise>
        </c:choose>
      </DIV>
      <DIV style="float: left; padding-right: 2px !important;">
        <A href="/study/nonuser/jobinfo/list_all_jobinfo.do">
          <IMG src="/study/nonuser/jobinfo/images/list.png" alt="리스트">
        </A>
      </DIV>
    </DIV>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>