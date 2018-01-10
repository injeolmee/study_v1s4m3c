<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Study Matching Web Site</title>
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="/study/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>  
<link href="${pageContext.request.contextPath}/admin/room/hidcss/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('rocontent');  // <TEXTAREA>태그 id 값을 받아와 ckeditor 형식으로 변환
 
  };
  //*********************** 게시글 등록 이벤트 처리 *********************** 
  function create_check() {
     
    var formData = new FormData();
    
    var ck_rocontent = CKEDITOR.instances.rocontent.getData();
    
    formData.append("adminno", $("input[name=adminno]").val());
    formData.append("roname", $("input[name=roname]").val());
    formData.append("rocity", $("select[name=rocity]").val());
    formData.append("rogu", $("select[name=rogu]").val());
    formData.append("rodong", $("input[name=rodong]").val());
    formData.append("romap", $("textarea[name=romap]").val());
    formData.append("rocontent", ck_rocontent);
    formData.append("rotel", $("input[name=rotel]").val());
    formData.append("rosite", $("input[name=rosite]").val());
    formData.append("rorunday", $("input[name=rorunday]").val());
    formData.append("rocost", $("input[name=rocost]").val());
    formData.append("rocount", $("input[name=rocount]").val());
    /* formData.append("option1", $("input[name=option1]:checkbox:checked").val());
    formData.append("option2", $("input[name=option2]:checkbox:checked").val());
    formData.append("option3", $("input[name=option3]:checkbox:checked").val());
    formData.append("option4", $("input[name=option4]:checkbox:checked").val());
    formData.append("option5", $("input[name=option5]:checkbox:checked").val());  */
    
    if($("input[name='option1']:checked").val()){
      formData.append("option1", $("input[name=option1]").val());
    }
    if($("input[name='option2']:checked").val()){
      formData.append("option2", $("input[name=option2]").val());
    }
    if($("input[name='option3']:checked").val()){
      formData.append("option3", $("input[name=option3]").val());
    }
    if($("input[name='option4']:checked").val()){
      formData.append("option4", $("input[name=option4]").val());
    }
    if($("input[name='option5']:checked").val()){
      formData.append("option5", $("input[name=option5]").val());
    }
    

    //********** NULL 값 Check해  입력안했으면 실행하지 못하도록 함 *****************
    if($("input[name=roname]").val() == null || $("input[name=roname]").val() == '') {
      alert("스터디룸 상호명을 입력해주세요.");
      return false;
    }
    
    if ($("input[name=rodong]").val() == null || $("input[name=rodong]").val() == '') {
      alert("주소를 입력해주세요.");
      return false;
    }
    
    if($("textarea[name=romap]").val() == null || $("textarea[name=romap]").val() == '') {
      alert("지도 소스를 입력해주세요.");
      return false;
    }
    
    if(ck_rocontent.length < 1) {
      alert("내용을 입력해주세요.");
      return false;
    }
    
  //**********************************************************************************
   
    if($("input[name=file1MF]")[0].files[0] == null) { // 파일 업로드 X의 경우
      //alert("그냥업로드 실행"); 
    
      var frm = $('#frm').serialize(); // 위의 코드 사용하지 않고 파일 제외한 모든 데이터를 serialize
      // alert("frm의 serialize: " + frm);
      var frm_new = replaceAll(frm, "rocontent", "");
      var ck_data = "&rocontent=" + ck_rocontent;
      var new_frm = frm_new + ck_data; // frm의 내용과 ckeditor의 내용 결합
      // alert("결합한 소스: " + new_frm);
      create_nom(new_frm);
      
    } else { // 파일 업로드하는 경우
      //alert("파일업로드 실행");
    
      formData.append("file1MF", $("input[name=file1MF]")[0].files[0]); // 파일 업로드까지 추가
      create_file(formData);
      }
    }
 //***************************************************************************** 
 
 //************************ 파일까지 포함한 create.do ***********************
 function create_file(formData) { 
   $.ajax({
     url: "/study/admin/room/create.do",
     type: "POST",
     data: formData,
     dataType: "JSON",
     processData: false,      // 필수 코드 1
     contentType: false,      // 필수 코드 2
     success: function (formData) {
       
       if (formData.count == 1) { // 등록 처리가 성공한 경우
         alert("스터디룸 게시글을 등록하였습니다.")
         document.location.href = "/study/nonuser/room/list.do";
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 게시글을 등록하지 못했습니다. 다시 시도해주십시오.");
       } 
     }
   });
 }
//*******************************************************************************
 
 //************************** 파일을 제외한 create.do *************************
 function create_nom(data) {
   $.ajax({
     url: "/study/admin/room/create.do",
     type: "POST",
     data: data,
     dataType: "JSON",
     success: function (data) {
       
       if (data.count == 1) { // 등록 처리가 성공한 경우
         alert("게시글을 등록하였습니다.")
         document.location.href = "/study/nonuser/room/list.do";
       } else { // 등록 처리가 실패한 경우
         alert("오류가 생겨 게시글을 등록하지 못했습니다. 다시 시도해주십시오.");
       } 
     }
   });
 }
 //********************************************************************************
 
  //************** URL 조합을 위한 Replace 함수 ***************************
 function replaceAll(str, searchStr, replaceStr) {
   return str.split(searchStr).join(replaceStr);   
 }
 //**************************************************************************
 
  
  //*********************** 주소 함수 *********************** 
  $(function(){
    
    
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
  
    var li="";
    for(var i=0; i<seoul.length; i++){
      li+="<option value="+seoul[i]+">"+seoul[i]+"</option>";
    }
    $("#rogu").append(li);  /* 목표 태그안에 삽입 */
  
    $('#rocity').change(function(){
      var li="";
      /* 서울이 선택되었을 때. */
      /* 처음 시작은 서울임. */
      if($("#rocity").val()=="서울시"){
        $("#rogu").empty(); /* 태크안의 내용을 삭제. */
        for(var i=0; i<seoul.length; i++){
          li+="<option value="+seoul[i]+">"+seoul[i]+"</option>";
        }
        $("#rogu").append(li);  /* 목표 태그안에 삽입 */
      } else if($("#rocity").val()=="경기도"){
        $("#rogu").empty();
        for(var i=0; i<gyeonggi.length; i++){
          li+="<option value="+gyeonggi[i]+">"+gyeonggi[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="인천"){
        $("#rogu").empty();
        for(var i=0; i<incheon.length; i++){
          li+="<option value="+incheon[i]+">"+incheon[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="강원도"){
        $("#rogu").empty();
        for(var i=0; i<kangwon.length; i++){
          li+="<option value="+kangwon[i]+">"+kangwon[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="경남"){
        $("#rogu").empty();
        for(var i=0; i<gyeongnam.length; i++){
          li+="<option value="+gyeongnam[i]+">"+gyeongnam[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="경북"){
        $("#rogu").empty();
        for(var i=0; i<gyeongbuk.length; i++){
          li+="<option value="+gyeongbuk[i]+">"+gyeongbuk[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="광주"){
        $("#rogu").empty();
        for(var i=0; i<gwangju.length; i++){        
          li+="<option value="+gwangju[i]+">"+gwangju[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="대구"){
        $("#rogu").empty();
        for(var i=0; i<daegu.length; i++){
          li+="<option value="+daegu[i]+">"+daegu[i]+"</option>";
        }
        $("#rogu").append(li);
      }else if($("#rocity").val()=="대전"){
        $("#rogu").empty();
        for(var i=0; i<daejeon.length; i++){
          li+="<option value="+daejeon[i]+">"+daejeon[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="부산광역시"){
        $("#rogu").empty();
        for(var i=0; i<busan.length; i++){
          li+="<option value="+busan[i]+">"+busan[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="세종"){
        $("#rogu").empty();
        for(var i=0; i<sejong.length; i++){
          li+="<option value="+sejong[i]+">"+sejong[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="울산"){
        $("#rogu").empty();
        for(var i=0; i<ulsan.length; i++){
          li+="<option value="+ulsan[i]+">"+ulsan[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="전남"){
        $("#rogu").empty();
        for(var i=0; i<jeonnam.length; i++){
          li+="<option value="+jeonnam[i]+">"+jeonnam[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="전북"){
        $("#rogu").empty();
        for(var i=0; i<jeonbuk.length; i++){
          li+="<option value="+jeonbuk[i]+">"+jeonbuk[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="제주"){
        $("#rogu").empty();
        for(var i=0; i<jeju.length; i++){
          li+="<option value="+jeju[i]+">"+jeju[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="충남"){
        $("#rogu").empty();
        for(var i=0; i<chungnam.length; i++){
          li+="<option value="+chungnam[i]+">"+chungnam[i]+"</option>";
        }
        $("#rogu").append(li);
      } else if($("#rocity").val()=="충북"){
        $("#rogu").empty();
        for(var i=0; i<chungbuk.length; i++){
          li+="<option value="+chungbuk[i]+">"+chungbuk[i]+"</option>";
        }
        $("#rogu").append(li);
      }
    });
    
  });
  //**********************************************************************************
  
  
  
  //************************************ 옵션 함수 ************************************
  $(function check_submit(){
    
  var ischecked = [false,false,false,false,false];
  
   ischecked[0] = $("#option1").is(":checked");
   ischecked[1] = $("#option2").is(":checked");
   ischecked[2] = $("#option3").is(":checked");
   ischecked[3] = $("#option4").is(":checked");
   ischecked[4] = $("#option5").is(":checked");
 
   for(var i = 0; i < ischecked.length; i++){
    
     if(ischecked[i] == true){
       break;
      }
    };
  });
  //***********************************************************************************
  
  
  
 
 
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush="false" />
<div class="container">
<DIV class='content' style='width: 60%; margin: 0px auto; '>  

  <DIV class="title_line" style='width: 50%; margin: 50px auto; font-size: 20px; font-weight: bold;'> 스터디룸 등록 </DIV>
  
  <DIV style="text-align: center; width: 100%;">
  
  <FORM name='frm' id="frm" method='POST' enctype="multipart/form-data" class="form-horizontal" style="text-align: left;">
     <input type='hidden' name='adminno' id='adminno' value=1>
  
    <div class="form-group">   
      <div class="col-md-10" style="width: 100%; margin-top: 40px;">
        <label for="title" class="control-label" style="font-size: 16px; margin-right: 17px; width: 6%; float: left;">상호명</label>  
        <input type='text' class="form-control input-md" name='roname' id='roname' value='솔데스크 24시' 
          required="required" style='width: 85%; float: left;'>
      </div>
    </div>
    
    <div style='clear: both'> </div>
    
    <div class="form-group">
      <div class="col-md-10" style="width: 100%; margin-top: 10px;">
        <label for="name" class="control-label" style="font-size: 16px; margin-right: 17px; width: 6%; float: left;">주소</label>
          <select class='form-control input-md' id="rocity" name="rocity" title='직접선택' style="width:20%; float: left; margin-right: 5px;">
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
          <select class='form-control input-md'  id="rogu" name="rogu" style="width:20%; float: left; margin-right: 5px;">
           <!-- 선택한 지역의 하위 지역  -->  
          </select>
          <input type='text' class="form-control input-md" name='rodong' id='rodong'  placeholder="나머지 주소"  style='width: 44%; float: left;'>  
      </div>
    </div>  
    
    <div style='clear: both'> </div>
    
    <div class="form-group" style="margin-bottom: 0px;">
      <div class="col-md-10" style="width: 100%; margin-top: 10px;">
        <label for="romap" class="control-label" style="font-size: 16px; margin-right: 17px; width: 6%; float: left; text-align: right;">지도</label>
        <TEXTAREA class="form-control input-md" name='romap' id='romap' rows='5' placeholder="Daum map" style='width: 84%; float: left;'></textarea>
      </div>
      <DIV style="padding: 3px 0px 10px 15px; text-align: left;
                  letter-spacing: -1px; margin-left: 7%; width: 84%;    
                  background: url(./images/bul_exclamation.png) no-repeat;
                  color: #777; font-size: 11px; line-height: 1; vertical-align: top; clear: both;">
          <a href="http://map.daum.net/" target="_blank">http://map.daum.net/</a>　(순서 : 주소검색 -> 상세보기 -> 우측상단 깃발 클릭 -> 지도크기 선택시 사용자 지정을 선택 후 520 * 300으로 설정 -> 소스코드생성)            
        </DIV>
    </div>
    
    <div style='clear: both'> </div>
    
    <div class="form-group">   
      <div class="col-md-10" style="text-align: center; width: 95%; margin-top: 0px;">
        <textarea class="form-control input-md" name='rocontent' id='rocontent' rows='20' cols='70'></textarea>
      </div>
    </div> 
    
      
    <div class="form-group form-group-sm" style='margin-bottom: 10px;'>
    <div class="col-md-10" style="width: 100%; margin-top: 0px;">
      <label for="rotel" class="col-md-2 control-label" style="font-size: 16px; margin-right: 17px; width: 6%; float: left; text-align: right;">정보</label>   
      <input type='text' class="form-control input-lg" placeholder="전화번호" name='rotel' id='rotel' style='width: 26.3%; float: left; margin-right: 6px;' >
      <input type='text' class="form-control input-lg" placeholder="홈페이지" name='rosite' id='rosite' style='width: 26.3%; float: left; margin-right: 6px;' >
      <input type='text' class="form-control input-lg" placeholder="운영시간" name='rorunday' id='rorunday'  style='width: 26.3%; float: left;' >
    </div>
    </div> 
    
    <div style='clear: both'> </div>
    
    <div class="form-group form-group-sm" style='margin-bottom: 10px;'>
    <div class="col-md-10" style="width: 100%; margin-top: 0px;">
      <label for="rotel" class="col-md-2 control-label" style="font-size: 16px; margin-right: 17px; width: 6%; float: left; text-align: right;"></label>   
      <input type='text' class="form-control input-lg" placeholder="비용: 예) 1인당 1주문" name='rocost' id='rocost' style='width: 41%; float: left; margin-right: 6px;' >
      <input type='text' class="form-control input-lg" placeholder="룸 정보: 예) 1호실 4명" name='rocount' id='rocount' style='width: 41%; float: left;' >
    </div>
    </div> 
    
    <div style='clear: both'> </div>
    
    <div class="form-group form-group-sm" style='margin-bottom: 10px;'>
      <div class="col-md-10" style="width: 100%; margin-top: 0px;">
        <label for="rooption" class="col-md-2 control-label" style='font-size: 16px; margin-right: 17px; width: 6%; float: left; text-align: right;'>옵션</label>   
        <input type="checkbox"  name='option1' id='option1' value='노트북 대여 가능,'>노트북 대여&nbsp;&nbsp;
        <input type="checkbox"  name='option2' id='option2' value='빔 프로젝터 사용 가능,'>빔 프로젝터&nbsp;&nbsp;
        <input type="checkbox"  name='option3' id='option3' value='인쇄/복사 가능,'>인쇄/복사&nbsp;&nbsp;
        <input type="checkbox"  name='option4' id='option4' value='와이파이 가능,'>와이파이&nbsp;&nbsp;
        <input type="checkbox"  name='option5' id='option5' value='화이트보드 사용 가능,'>화이트보드
        </div>
      </div>
    
    <div class="form-group" style="width: 500px; display: flex;"> 
      <label for="file1MF" class="col-md-2 control-label" style="width: 22%; padding-top: 2px;">첨부 파일 </label>
      <div class="col-md-10" style="text-align: center; width: 100%;">
         <input type="file" class="input-md" name='file1MF' id='file1MF' value="파일 선택" size='40' style="border: none;">
        </div>
    </div>
        
    <div class="form-group">       
      <div class="col-md-10"style="width: 100%;"> 
        
       <div style="float: right;">
        <button class="btn btn-default" type="button" onclick="create_check()" >등록</button>
        <button class="btn btn-default" type="button" onclick="history.go(-1);">취소</button>
      </div>
      </div>
  </div>
      
  </FORM>
  
  </DIV>

</DIV> <!-- content END -->
</div> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush="false" />
</body>
</html>