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
<title>Job Information</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
  var edu = "${jobVO.edu}";
  var career = "${jobVO.career}";
  var jobEmploy = "${jobVO.jobEmploy}";
  var jobWork = "${jobVO.jobWork}";
  
  $("#career1").each(function() {
    if (career = "신입") {
      $('#career1').prop("checked", true);
    }
  });
  
  $("#career2").each(function() {
    if (career = "무관") {
      $('#career2').prop("checked", true);
    }
  });
  
  $("#career3").each(function() {
    if (career = "경력") {
      $('#career3').prop("checked", true);
    }
  });
  
  $("#edu1").each(function() {
    if (edu = "초졸이하") {
      $('#edu1').prop("checked", true);
    }
  });
  
  $("#edu2").each(function() {
    if (edu = "중졸") {
      $('#edu2').prop("checked", true);
    }
  });
  
  $("#edu3").each(function() {
    if (edu = "고졸") {
      $('#edu3').prop("checked", true);
    }
  });
  
  $("#edu4").each(function() {
    if (edu = "대졸 (2~3년)") {
      $('#edu4').prop("checked", true);
    }
  });
  
  $("#edu5").each(function() {
    if (edu = "대졸 (4년)") {
      $('#edu5').prop("checked", true);
    }
  });
  
  $("#edu6").each(function() {
    if (edu = "석사") {
      $('#edu6').prop("checked", true);
    }
  });
  
  $("#edu7").each(function() {
    if (edu = "박사") {
      $('#edu7').prop("checked", true);
    }
  });
  
  $("#edu8").each(function() {
    if (edu = "학력무관") {
      $('#edu8').prop("checked", true);
    }
  });
  
  $("#jobEmploy1").each(function() {
    if (jobEmploy = "기간의 정함이 없는 근로 계약") {
      $('#jobEmploy1').prop("checked", true);
    }
  });
  
  $("#jobEmploy2").each(function() {
    if (jobEmploy = "기간의 정함이 없는 근로 계약(시간제)") {
      $('#jobEmploy2').prop("checked", true);
    }
  });
  
  $("#jobEmploy3").each(function() {
    if (jobEmploy = "기간의 정함이 있는 근로 계약") {
      $('#jobEmploy3').prop("checked", true);
    }
  });
  
  $("#jobEmploy4").each(function() {
    if (jobEmploy = "기간의 정함이 있는 근로 계약(시간제)") {
      $('#jobEmploy4').prop("checked", true);
    }
  });
  
  $("#jobEmploy5").each(function() {
    if (jobEmploy = "파견 근로") {
      $('#jobEmploy5').prop("checked", true);
    }
  });
  
  $("#jobEmploy6").each(function() {
    if (jobEmploy = "무관") {
      $('#jobEmploy6').prop("checked", true);
    }
  });
  
  $("#jobWork1").each(function() {
    if (jobWork = "주1일") {
      $('#jobWork1').prop("checked", true);
    }
  });
  $("#jobWork2").each(function() {
    if (jobWork = "주2일") {
      $('#jobWork2').prop("checked", true);
    }
  });
  $("#jobWork3").each(function() {
    if (jobWork = "주3일") {
      $('#jobWork3').prop("checked", true);
    }
  });
  $("#jobWork4").each(function() {
    if (jobWork = "주4일") {
      $('#jobWork4').prop("checked", true);
    }
  });
  $("#jobWork5").each(function() {
    if (jobWork = "주5일") {
      $('#jobWork5').prop("checked", true);
    }
  });
  $("#jobWork6").each(function() {
    if (jobWork = "주6일") {
      $('#jobWork6').prop("checked", true);
    }
  });
  $("#jobWork7").each(function() {
    if (jobWork = "주7일") {
      $('#jobWork7').prop("checked", true);
    }
  });
  $("#jobWork8").each(function() {
    if (jobWork = "무관") {
      $('#jobWork8').prop("checked", true);
    }
  });
  
  var list=['서울시', '경기도', '인천', '강원도', '경남', '경북', '광주', '대구', '대전', '부산광역시', '세종', '울산', '전남', '전북', '제주', '충남', '충북'];
  var seoul =['강남구', '강동구', '강북구', '강서구', '관악구', '광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구', '서대문구', '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구', '은평구', '종로구', '중구', '중랑구'];
  var gyeonggi =['가평군', '고양시덕양구', '고양시일산동구', '고양시일산서구', '과천시', '광명시', '광주시', '구리시', '군포시', '김포시', '남양주시', '동두천시', '부천시소사구', '부천시오정구', '부천시원미구', '성남시분당구', '성남시수정구', '성남시중원구', '수원시영통구', '수원시권선구', '수원시장안구', '수원시팔달구', '시흥시', '안산시단원구', '안산시상록구', '안성시', '안양시동안구', '안양시만안구', '양주시', '양평군', '여주군', '연천군', '오산시', '용인시기흥구', '용인시수지구', '용인시처인구', '의왕시', '의정부시', '이천시', '파주시', '평택시', '포천시', '하남시', '화성시'];
  var incheon = ['강화군', '계양구', '남구', '남동구', '동구', '부평구', '서구', '연수구', '옹진군', '중구'];
  var kangwon = ['고성군', '동해시', '삼척시', '속초시', '양구군', '양양군', '영월군', '원주시', '인제군', '정선군', '철원군', '춘천시', '태백시', '평창군', '홍천군', '화천군', '횡성군'];
  var gyeongnam = ['거제시', '거창군', '고성군', '김해시', '남해군', '밀양시', '사천시', '산청군', '양산시', '의령군', '진주시', '창녕군', '창원시', '마산합포구', '창원시', '마산회원구', '창원시', '성산구', '창원시', '의창구', '창원시', '진해구', '통영시', '하동군', '함안군', '함양군', '합천군'];
  var gyeongbuk = ['경산시', '경주시', '고령군', '구미시', '군위군', '김천시', '문경시', '봉화군', '상주시', '성주군', '안동시', '영덕군', '영양군', '영주시', '영천시', '예천군', '울릉군', '울진군', '의성군', '청도군', '청송군', '칠곡군', '포항시남구', '포항시북구'];
  var gwangju =['광산구', '남구', '동구', '북구', '서구'];
  var daegu = ['남구', '달서구', '달성군', '동구', '북구', '서구', '수성구', '중구'];
  var daejeon = ['대덕구', '동구', '서구', '유성구', '중구'];
  var busan = ['강서구', '금정구', '기장군', '남구', '동구', '동래구', '부산진구', '북구', '사상구', '사하구', '서구', '수영구', '연제구', '영도구', '중구', '해운대구'];
  var sejong = ['세종시'];
  var ulsan = ['남구', '동구', '북구', '울주군', '중구'];
  var jeonnam = ['강진군', '고흥군', '곡성군', '광양시', '구례군', '나주시', '담양군', '목포시', '무안군', '보성군', '순천시', '신안군', '여수시', '영광군', '영암군', '완도군', '장성군', '장흥군', '진도군', '함평군', '해남군', '화순군'];
  var jeonbuk = ['고창군', '군산시', '김제시', '남원시', '무주군', '부안군', '순창군', '완주군', '익산시', '임실군', '장수군', '전주시', '덕진구', '전주시', '완산구', '정읍시', '진안군'];
  var jeju = ['서귀포시', '제주시'];
  var chungnam = ['계룡시', '공주시', '금산군', '논산시', '당진시', '보령시', '부여군', '서산시', '서천군', '아산시', '예산군', '천안시동남구', '천안시서북구', '청양군', '태안군', '홍성군'];
  var chungbuk = ['괴산군', '단양군', '보은군', '영동군', '옥천군', '음성군', '제천시', '증평군', '진천군', '청원군', '청주시', '상당구', '청주시', '흥덕구', '충주시'];

  $('#jcity option').each(function(){ //지역 값 select option 주기
    if($(this).val()=="${jobVO.jcity}"){
      $(this).prop("selected","selected"); // attr적용안될경우 prop으로 
    }
  });
  
  $('#jcity option:selected').each(function(){      
    // alert($("#area option:selected").val());
    
    var li="";
    /* 서울이 선택되었을 때. */
    /* 처음 시작은 서울임. */
    if($("#jcity").val()=="서울시"){
      $("#jgu").empty(); /* 태크안의 내용을 삭제. */
      for(var i=0; i<seoul.length; i++){
        li+="<option value="+seoul[i]+">"+seoul[i]+"</option>";
      }
      $("#jgu").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#jcity").val()=="경기도"){
      $("#jgu").empty();
      for(var i=0; i<gyeonggi.length; i++){
        li+="<option value="+gyeonggi[i]+">"+gyeonggi[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="인천"){
      $("#jgu").empty();
      for(var i=0; i<incheon.length; i++){
        li+="<option value="+incheon[i]+">"+incheon[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="강원도"){
      $("#jgu").empty();
      for(var i=0; i<kangwon.length; i++){
        li+="<option value="+kangwon[i]+">"+kangwon[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="경남"){
      $("#jgu").empty();
      for(var i=0; i<gyeongnam.length; i++){
        li+="<option value="+gyeongnam[i]+">"+gyeongnam[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="경북"){
      $("#jgu").empty();
      for(var i=0; i<gyeongbuk.length; i++){
        li+="<option value="+gyeongbuk[i]+">"+gyeongbuk[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="광주"){
      $("#jgu").empty();
      for(var i=0; i<gwangju.length; i++){
        li+="<option value="+gwangju[i]+">"+gwangju[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="대구"){
      $("#jgu").empty();
      for(var i=0; i<daegu.length; i++){
        li+="<option value="+daegu[i]+">"+daegu[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="대전"){
      $("#jgu").empty();
      for(var i=0; i<daejeon.length; i++){
        li+="<option value="+daejeon[i]+">"+daejeon[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="부산광역시"){
      $("#jgu").empty();
      for(var i=0; i<busan.length; i++){
        li+="<option value="+busan[i]+">"+busan[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="세종시"){
      $("#jgu").empty();
      for(var i=0; i<sejong.length; i++){
        li+="<option value="+sejong[i]+">"+sejong[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="울산"){
      $("#jgu").empty();
      for(var i=0; i<ulsan.length; i++){
        li+="<option value="+ulsan[i]+">"+ulsan[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="전남"){
      $("#jgu").empty();
      for(var i=0; i<jeonnam.length; i++){
        li+="<option value="+jeonnam[i]+">"+jeonnam[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="전북"){
      $("#jgu").empty();
      for(var i=0; i<jeonbuk.length; i++){
        li+="<option value="+jeonbuk[i]+">"+jeonbuk[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="제주"){
      $("#jgu").empty();
      for(var i=0; i<jeju.length; i++){
        li+="<option value="+jeju[i]+">"+jeju[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="충남"){
      $("#jgu").empty();
      for(var i=0; i<chungnam.length; i++){
        li+="<option value="+chungnam[i]+">"+chungnam[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="충북"){
      $("#jgu").empty();
      for(var i=0; i<chungbuk.length; i++){
        li+="<option value="+chungbuk[i]+">"+chungbuk[i]+"</option>";
      }
      $("#jgu").append(li);
    }

    $('#jgu option').each(function(){
      if($(this).val()=="${jobVO.jgu}"){
        $(this).prop("selected","selected"); // attr적용안될경우 prop으로 
      } else {
        $(this).prop("selected", false);
      }
    });
    
  }); 

  $('#jcity').change(function(){
    var li="";
    /* 서울이 선택되었을 때. */
    /* 처음 시작은 서울임. */
    if($("#jcity").val()=="서울시"){
      $("#jgu").empty(); /* 태크안의 내용을 삭제. */
      for(var i=0; i<seoul.length; i++){
        li+="<option value="+seoul[i]+">"+seoul[i]+"</option>";
      }
      $("#jgu").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#jcity").val()=="경기도"){
      $("#jgu").empty();
      for(var i=0; i<gyeonggi.length; i++){
        li+="<option value="+gyeonggi[i]+">"+gyeonggi[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="인천"){
      $("#jgu").empty();
      for(var i=0; i<incheon.length; i++){
        li+="<option value="+incheon[i]+">"+incheon[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="강원도"){
      $("#jgu").empty();
      for(var i=0; i<kangwon.length; i++){
        li+="<option value="+kangwon[i]+">"+kangwon[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="경남"){
      $("#jgu").empty();
      for(var i=0; i<gyeongnam.length; i++){
        li+="<option value="+gyeongnam[i]+">"+gyeongnam[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="경북"){
      $("#jgu").empty();
      for(var i=0; i<gyeongbuk.length; i++){
        li+="<option value="+gyeongbuk[i]+">"+gyeongbuk[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="광주"){
      $("#jgu").empty();
      for(var i=0; i<gwangju.length; i++){       
        li+="<option value="+gwangju[i]+">"+gwangju[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="대구"){
      $("#jgu").empty();
      for(var i=0; i<daegu.length; i++){
        li+="<option value="+daegu[i]+">"+daegu[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="대전"){
      $("#jgu").empty();
      for(var i=0; i<daejeon.length; i++){
        li+="<option value="+daejeon[i]+">"+daejeon[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="부산광역시"){
      $("#jgu").empty();
      for(var i=0; i<busan.length; i++){
        li+="<option value="+busan[i]+">"+busan[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="세종시"){
      $("#jgu").empty();
      for(var i=0; i<sejong.length; i++){
        li+="<option value="+sejong[i]+">"+sejong[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="울산"){
      $("#jgu").empty();
      for(var i=0; i<ulsan.length; i++){
        li+="<option value="+ulsan[i]+">"+ulsan[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="전남"){
      $("#jgu").empty();
      for(var i=0; i<jeonnam.length; i++){
        li+="<option value="+jeonnam[i]+">"+jeonnam[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="전북"){
      $("#jgu").empty();
      for(var i=0; i<jeonbuk.length; i++){
        li+="<option value="+jeonbuk[i]+">"+jeonbuk[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="제주"){
      $("#jgu").empty();
      for(var i=0; i<jeju.length; i++){
        li+="<option value="+jeju[i]+">"+jeju[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="충남"){
      $("#jgu").empty();
      for(var i=0; i<chungnam.length; i++){
        li+="<option value="+chungnam[i]+">"+chungnam[i]+"</option>";
      }
      $("#jgu").append(li);
    }else if($("#jcity").val()=="충북"){
      $("#jgu").empty();
      for(var i=0; i<chungbuk.length; i++){
        li+="<option value="+chungbuk[i]+">"+chungbuk[i]+"</option>";
      }
      $("#jgu").append(li);
    }
  });
});

function update_thumb_frm(jobNo) {
  var frm_thumb = $('#frm_thumb');
  $.ajax({
    url: "/study/admin/jobinfo/update_thumb.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'jobNo=' + jobNo,
    success: function(data) {
      var jobThumb = "<IMG src='/study/admin/jobinfo/storage/" + data.jobFile1 + "'>";
      $("#jobFile1", frm_thumb).val(data.jobFile1);
      $('#jobFile1').html(data.jobFile1);
      $("#jobThumb", frm_thumb).val(data.jobFile1);
      $('#jobThumb').html(jobThumb);
      $("#myModal").modal();
    }
  });
}

function update_file_frm(jobNo) {
  var frm_file = $('#frm_file');
  $.ajax({
    url: "/study/admin/jobinfo/update_file.do",
    type: "GET",
    cache: false,
    dataType: "json",
    data: 'jobNo=' + jobNo,
    success: function(data) {
      $("#jobFile2", frm_file).val(data.jobFile2);
      $('#jobFile2').html(data.jobFile2);
      $("#myModal2").modal();
    }
  });
}

</script>

</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>
<DIV class='content' style='margin-bottom: 50px;'>
  <div style="width: 80%; padding: 20px 0 40px 0; text-align: center; margin-left: 16%;">
    <div class="info_title">채용정보 수정하기</div>
    <div class="cb t10"></div>
    <FORM name='frm' method='POST' action='/study/admin/jobinfo/update.do'
               enctype="multipart/form-data" class="form-horizontal" style='float: left; text-align: center; margin: 0px auto; width: 100%;'> 
      <input type="hidden" name="jobNo" id = "jobNo" value="${param.jobNo }">
      <!-- <input type='hidden' name='memberno' id='memberno' value='1'> -->
      
      <DIV class = "form-group form-group-sm">
        <label for='comName' class='col-md-2 control-label' style='width: 6%;'>회사명</label>
        <DIV class="col-md-3" style='width: 8%;'>
          <input type='text' class="form-control input-sm" name='comName' id='comName' value='${jobVO.comName }' required="required">
        </DIV>
        <label for='bossName' class='col-md-2 control-label' style='width: 8%;'>대표자명</label>
        <DIV class="col-md-3" style='width: 8%;'>
          <input type='text' class="form-control input-sm" name='bossName' id='bossName' value='${jobVO.bossName }' required="required">
        </DIV>
        <label for='jobWord' class='col-md-3 control-label' style='width: 9%; padding-left: 1%;'>검색 키워드</label>
        <DIV class="col-md-3" style='width: 10%;'>
          <input type='text' class="form-control input-lg" name='jobWord' id='jobWord' value='${jobVO.jobWord }' required="required">
        </DIV>
        <label for='labCnt' class='col-md-2 control-label' style='width: 7%;'>근로자수</label>
        <DIV class="col-md-5" style='width: 4%;'>
          <input type='text' class="form-control input-sm" name='labCnt' id='labCnt' value='${jobVO.labCnt }' required="required">
        </DIV>
      </DIV>
      
      <DIV style="clear: both; padding-top: 5px !important;"></DIV>
      
      <DIV class = "form-group form-group-sm">
        <label for='jobStart' class='col-md-2 control-label' style='width: 6%;'>시작일</label>
        <DIV class="col-md-6" style='width: 10%;'>
          <input type='date' class="form-control input-lg" name='jobStart' id='jobStart' value='${jobVO.jobStart }' required="required">
        </DIV>
        <label for='jobEnd' class='col-md-2 control-label' style='width: 6%;'>종료일</label>
        <DIV class="col-md-6" style='width: 10%;'>
          <input type='date' class="form-control input-lg" name='jobEnd' id='jobEnd' value='${jobVO.jobEnd }' required="required">
        </DIV>
        <DIV class="col-md-6" style='width: 42%;'>
          <A href="javascript: update_thumb_frm(${jobVO.jobNo });" type="button" class="btn btn-default btn-lg" id="myBtn">Thumb Replace</A>
          <A href="javascript: update_file_frm(${jobVO.jobNo });" type="button" class="btn btn-default btn-lg" id="myBtn3" style="margin-left: 20px;">Resume Replace</A>
        </DIV>
      </DIV>
      
      <DIV style="clear: both; padding-top: 5px !important;"></DIV>
      
      <!-- <DIV class = "form-group">
        <label for='file1MF' class='col-md-2 control-label' style='width: 6%;'>포스터</label>
        
        <label for='file2MF' class='col-md-2 control-label' style='width: 6.5%;'>첨부파일</label>
        
      </DIV> -->
      
      <DIV style="clear: both; padding-top: 5px !important;"></DIV>
      
      <DIV class="form-group form-group-sm">
        <label for="jobStatus" class="col-md-1 control-label" style="width: 6.5%;">직무내용</label>
        <DIV class="col-md-3" style="width: 26%;">
          <input type='text' class="form-control input-sm" name='jobStatus' id='jobStatus' value='${jobVO.jobStatus }' required="required">
        </DIV>
        <label for="homepage" class="col-md-1 control-label" style="width: 7%;">홈페이지</label>
        <DIV class="col-md-3" style="width: 30%;">
          <input type='text' class="form-control input-sm" name='homepage' id='homepage' value='${jobVO.homepage }' required="required">
        </DIV>
      </DIV>
      
      <DIV style="clear: both; padding-top: 10px !important;"></DIV>
      
      <div class="form-group">   
        <label for="jobLocal" class="col-md-2 control-label" style="width: 6.5%;">근무지역</label> 
        <div class="col-md-3" style="width: 10%;">
          <select class='form-control' id="jcity" name="jcity" title='직접선택'>
            <option value="서울시">서울</option>
            <option value="경기도">경기</option>
            <option value="인천">인천</option>
            <option value="강원도">강원</option>
            <option value="경남">경남</option>
            <option value="경북">경북</option>
            <option value="광주">광주</option>
            <option value="대구">대구</option>
            <option value="대전">대전</option>
            <option value="부산광역시">부산</option>
            <option value="세종시">세종</option>
            <option value="울산">울산</option>
            <option value="전남">전남</option>
            <option value="전북">전북</option>
            <option value="제주">제주</option>    
            <option value="충남">충남</option>
            <option value="충북">충북</option>
          </select>
        </div>
          
        <div class="col-md-3" style="width: 10%;">
          <select class='form-control' id="jgu" name="jgu">
           <!-- 선택한 지역의 하위 지역  -->
          </select>
        </div>
          
        <div class="col-md-3" style='width: 43.5%;'>
          <input type='text' class="form-control input-sm" name='jdong' id='jdong' value='${jobVO.jdong }' placeholder="나머지 주소"  style='width: 100%;'>  
        </div>    
      </div>
      
      <DIV style="clear: both; padding-top: 10px !important;"></DIV>
      
      <DIV class = "form-group form-group-sm">
        <label for="comAddr" class="col-md-1 control-label" style="width: 6.5%;">회사주소</label>
        <DIV class="col-md-9">
          <textarea class="form-control input-sm" rows='5' name='comAddr' id='comAddr' placeholder="Daum map" style="width: 90%; height: 100%;">${jobVO.comAddr }</textarea>
        </DIV>
      </DIV>
 
      <DIV style="clear: both; padding-top: 8px !important;"></DIV>
      
      <DIV style="padding: 0 0 10px 15px; text-align: left;
                  letter-spacing: -1px; margin-left: 123px; width: 70%;
                  background: url(/study/admin/jobinfo/images/bul_exclamation.png) no-repeat;
                  color: #777; font-size: 11px; line-height: 1; vertical-align: top;">
        <a href="http://map.daum.net/" target="_blank">http://map.daum.net/</a>　(순서 : 주소검색 -> 상세보기 -> 우측상단 깃발 클릭 -> 지도크기 선택시 사용자 지정을 선택 후 690 * 300으로 설정 -> 소스코드생성)            
      </DIV>
      
      <DIV style="clear: both; padding-top: 3px !important;"></DIV>
      
      <DIV class = "form-group form-group-sm">
        <label for="jobCont" class="col-md-1 control-label" style="width: 6.5%;">추가사항</label>
        <DIV class="col-md-9">
          <textarea class="form-control input-sm" rows='5' name='jobCont' id='jobCont' placeholder="추가 사항" style="width: 90%; height: 100%;">${jobVO.jobCont }</textarea>
        </DIV>
      </DIV>
      
      <DIV style="clear: both; padding-top: 10px !important;"></DIV>
      
      <DIV class = "form-group form-group-sm" style="margin: 0 0 0 10%; text-align: left;">
        <h1 style="font-size: 20px;">경력 / 학력　　　　　　　　　　　　　　　고용 형태</h1>
        <span class="col-md-5" style="width: 34%; border: 1px solid gray; font-size: 13px;"><br><br>
          <!-- <span style="width: 50%; border-bottom: 1px solid gray; padding-top: 5px;">경력 / 학력</span><br><br> -->
          <input type="radio" name='career' id='career1' value="신입">신입&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name='career' id='career2' value="무관">무관&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name='career' id='career3' value="경력">경력&nbsp;<br><br>
          <span style="width: 30%;">============================</span><br><br>
          <input type="radio" name='edu' id='edu1' value="초졸이하">초졸이하&nbsp;&nbsp;
          <input type="radio" name='edu' id='edu2' value="중졸">중졸&nbsp;&nbsp;
          <input type="radio" name='edu' id='edu3' value="고졸">고졸<br><br>
          <input type="radio" name='edu' id='edu4' value="대졸(2~3년)">대졸 (2~3년)&nbsp;
          <input type="radio" name='edu' id='edu5' value="대졸(4년)">대졸 (4년)<br><br>
          <input type="radio" name='edu' id='edu6' value="석사">석사&nbsp;&nbsp;
          <input type="radio" name='edu' id='edu7' value="박사">박사&nbsp;&nbsp;
          <input type="radio" name='edu' id='edu8' value="학력무관">학력무관<br><br>
        </span>
        <span class="col-md-5" style="width: 34%; border: 1px solid gray; margin-left: 30px; font-size: 13px;"><br>
          <!-- <span style="width: 50%; border-bottom: 1px solid gray; padding-top: 5px;">고용 형태</span><br><br> -->
          <input type="radio" name='jobEmploy' id='jobEmploy1' value="기간의 정함이 없는 근로 계약">기간의 정함이 없는 근로 계약&nbsp;&nbsp;<br><br>
          <input type="radio" name='jobEmploy' id='jobEmploy2' value="기간의 정함이 없는 근로 계약(시간제)">기간의 정함이 없는 근로 계약(시간제)<br><br>
          <input type="radio" name='jobEmploy' id='jobEmploy3' value="기간의 정함이 있는 근로 계약">기간의 정함이 있는 근로 계약&nbsp;&nbsp;<br><br>
          <input type="radio" name='jobEmploy' id='jobEmploy4' value="기간의 정함이 있는 근로 계약(시간제)">기간의 정함이 있는 근로 계약(시간제)<br><br>
          <input type="radio" name='jobEmploy' id='jobEmploy5' value="파견 근로">파견 근로<br><br>
          <input type="radio" name='jobEmploy' id='jobEmploy6' value="무관">무관<br><br>
        </span>
      </div>
      
      <DIV style="clear: both; padding-top: 10px !important;"></DIV>
      
      <DIV class = "form-group form-group-sm" style="text-align: center;">
        <h1 style="font-size: 20px; text-align: left; margin-left: 35%;">근무 형태 / 임금 형태</h1>
        <span class="col-md-5" style="margin: 0 0 0 10%; width: 66.6%; border: 1px solid gray; font-size: 13px;"><br>
        <!-- <span style="width: 50%; border-bottom: 1px solid gray; padding-top: 5px;">근무 형태 / 최저 희망 임금</span><br><br> -->
          <input type="radio" name="jobWork" id="jobWork1" value="주1일">주1일&nbsp;&nbsp;
          <input type="radio" name="jobWork" id="jobWork2" value="주2일">주2일&nbsp;&nbsp;
          <input type="radio" name="jobWork" id="jobWork3" value="주3일">주3일&nbsp;&nbsp;
          <input type="radio" name="jobWork" id="jobWork4" value="주4일">주4일&nbsp;&nbsp;
          <input type="radio" name="jobWork" id="jobWork5" value="주5일">주5일&nbsp;&nbsp;
          <input type="radio" name="jobWork" id="jobWork6" value="주6일">주6일&nbsp;&nbsp;
          <input type="radio" name="jobWork" id="jobWork7" value="주7일">주7일&nbsp;&nbsp;
          <input type="radio" name="jobWork" id="jobWork8" value="무관">무관<br>
          <span style="width: 30%;">============================================================</span><br><br>
          <select id="jobPay" name="jobPay" title='직접선택' style="width: 15%; height: 30%;">
            <option value="연봉">연봉</option>
            <option value="월급">월급</option>    
            <option value="일급">일급</option>
            <option value="시급">시급</option>
          </select>&nbsp;&nbsp;
          <input type="text" name="payLow" id="payLow" value="${jobVO.payLow }" style="width: 10%;">만(원) ~ 
          <input type="text" name="payHigh" id="payHigh" value="${jobVO.payHigh }" style="width: 10%;">만(원)<br><br>
        </span>
      </DIV>
      
      <DIV style="clear: both; padding-top: 10px !important;"></DIV>
      
      <div class="form-group form-group-sm">    
        <DIV style='text-align: center; width:90%; padding-top: 10px;'>
          <button class='btn btn-default' type="submit">저장</button>
          <button class='btn btn-default' type="button" onclick="location.href='javascript: history.back()'">취소[목록]</button>
        </DIV>
      </div>
    </FORM>
    
    <div class="modal fade" id="myModal" role="dialog" style="font-size: 18px; display: none;">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" style="font-weight: bold; text-align: center;">썸네일 수정</h4>
      </div>
      <div class="modal-body" style='width: 95%; height: 420px; text-align: center; margin: 0px auto;'>
        <FORM name = 'frm_thumb' id = 'frm_thumb' method="POST" action="/study/admin/jobinfo/update_thumb.do" enctype="multipart/form-data">
          <input type="hidden" name="jobNo" id="jobNo" value='${param.jobNo }'>
          <DIV class = "form-group form-group-sm">
            <label for="jobThumb" class="col-md-6 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">현재 썸네일</label>
            <DIV class="col-md-6" style='width: 43%;'>
              <span id="jobFile1"></span>
            </DIV>
          </DIV>
          <DIV style="clear: both; padding-top: 10px !important;"></DIV>
          <div id='jobFile1Panel' class="form-group form-group-sm">
            <label class="col-md-1 control-label"></label>
            <div class="col-md-5" style='padding-bottom: 10px; width: 360px; height: 100px;'>
              <div><span id = "jobThumb"></span></div>
            </div>
          </div>
          
          <DIV style="clear: both; padding-top: 170px !important;"></DIV>
          
          <DIV class = "form-group form-group-sm">
            <label for="jobThumb" class="col-md-4 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">새로운 썸네일</label>
            <DIV class="col-md-6" style='width: 63%;'>
              <input type = "file" name = "file1MF" id = "file1MF" size="50">
            </DIV>
          </DIV>
          
          <DIV style="clear: both; padding-top: 10px !important; border-bottom: 1px solid #CACACA;"></DIV>
          
          <div class="form-group form-group-sm" style="margin: 0px auto; text-align: center;">
            <DIV style="clear: both; padding-top: 10px !important;"></DIV>
            <button type = "submit" class="btn btn-default">썸네일 교체</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </FORM>
      </div>
      <!-- <div class="modal-footer" style="margin: 0px auto; text-align: center;">
        <button type = "submit" class="btn btn-default">썸네일 교체</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div> -->
    </div>
  </div>
<!-- Modal END -->

<div class="modal fade" id="myModal2" role="dialog" style="font-size: 18px; display: none;">
  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title" style="font-weight: bold; text-align: center;">파일 수정</h4>
    </div>
    <div class="modal-body" style='width: 95%; height: 150px; text-align: center; margin: 0px auto;'>
      <FORM name = 'frm_file' id = 'frm_file' method="POST" action="/study/admin/jobinfo/update_file.do" enctype="multipart/form-data">
        <input type="hidden" name="jobNo" id="jobNo" value='${param.jobNo }'>
        <DIV class = "form-group form-group-sm">
          <label for="jobFstor2" class="col-md-6 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">현재 파일</label>
          <DIV class="col-md-6" style='width: 43%;'>
            <span id="jobFile2"></span>
            <%-- <input type = "text" value="${fn:toLowerCase(contestVO.conFile3)}"> --%>
          </DIV>
        </DIV>
        
        <DIV style="clear: both; padding-top: 10px !important;"></DIV>
        
        <DIV class = "form-group form-group-sm">
          <label for="jobFstor2" class="col-md-4 control-label" style="width: 22%; padding-left: 1%; line-height: 30px;">새로운 파일</label>
          <DIV class="col-md-6" style='width: 63%;'>
            <input type = "file" name = "file2MF" id = "file2MF" size="50">
          </DIV>
        </DIV>
        
        <DIV style="clear: both; padding-top: 10px !important; border-bottom: 1px solid #CACACA;"></DIV>
        
        <div class="form-group form-group-sm" style="margin: 0px auto; text-align: center;">
          <DIV style="clear: both; padding-top: 10px !important;"></DIV>
          <button type = "submit" class="btn btn-default">파일 교체</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </FORM>
    </div>
  </div>
</div>
<!-- Modal END -->
    
  </div>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>