<%@ page contentType="text/html; charset=UTF-8" %>
 
<%
String root = request.getContextPath();
%> 
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="./css/style.css" rel='Stylesheet' type='text/css'>

<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<style>
*{
 font-size: 16px;
}

</style>

</head>

<script type="text/javascript">
$(function(){
  
  //이메일 select option에 따른 text 값 변경
  $('#change_email').change(function(){
    $('#change_email option:selected').each(function() {
      if($(this).val() == 'self'){
        $('#email2').val('');
        $('#email2').attr("disabled", false);
      } else {
        $('#email2').val($(this).val());
        $('#email2').attr("disabled", false);
      }
    });
  });
 

  //지역과 지역별 세부카테고리 배열
    var list=['서울', '경기', '인천', '강원', '경남', '경북', '광주', '대구', '대전', '부산', '세종', '울산', '전남', '전북', '제주', '충남', '충북'];
    
    var seoul =['강남구', '강동구', '강북구', '강서구', '관악구', '광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구', '서대문구', '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구', '은평구', '종로구', '중구', '중랑구'];
    var gyeonggi =['가평군', '고양시덕양구', '고양시일산동구', '고양시일산서구', '과천시', '광명시', '광주시', '구리시', '군포시', '김포시', '남양주시', '동두천시', '부천시소사구', '부천시오정구', '부천시원미구', '성남시분당구', '성남시수정구', '성남시중원구', '수원시권선구', '수원시장안구', '수원시팔달구', '시흥시', '안산시단원구', '안산시상록구', '안성시', '안양시동안구', '안양시만안구', '양주시', '양평군', '여주군', '연천군', '오산시', '용인시기흥구', '용인시수지구', '용인시처인구', '의왕시', '의정부시', '이천시', '파주시', '평택시', '포천시', '하남시', '화성시'];
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
    
    /* 홈페이지 시작 시 서울이 디폴트 */
    
      var li="";
      for(var i=0; i<seoul.length; i++){
        li+="<option value="+seoul[i]+">"+seoul[i]+"</option>";
      }
      $("#selected_area").append(li);  /* 목표 태그안에 삽입 */
    
    //area 지역 값이 변경되게 되면 지역값에 따라 선택된 지역 배열값이 세부카테고리 값으로 append 
    $('#area').change(function(){
      var li="";
      /* 서울이 선택되었을 때. */
      /* 처음 시작은 서울임. */
      if($("#area").val()=="서울"){
        $("#selected_area").empty(); /* 태크안의 내용을 삭제. */
        for(var i=0; i<seoul.length; i++){
          li+="<option value="+seoul[i]+">"+seoul[i]+"</option>";
        }
        $("#selected_area").append(li);  /* 목표 태그안에 삽입 */
      }else if($("#area").val()=="경기"){
        $("#selected_area").empty();
        for(var i=0; i<gyeonggi.length; i++){
          li+="<option value="+gyeonggi[i]+">"+gyeonggi[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="인천"){
        $("#selected_area").empty();
        for(var i=0; i<incheon.length; i++){
          li+="<option value="+incheon[i]+">"+incheon[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="강원"){
        $("#selected_area").empty();
        for(var i=0; i<kangwon.length; i++){
          li+="<option value="+kangwon[i]+">"+kangwon[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="경남"){
        $("#selected_area").empty();
        for(var i=0; i<gyeongnam.length; i++){
          li+="<option value="+gyeongnam[i]+">"+gyeongnam[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="경북"){
        $("#selected_area").empty();
        for(var i=0; i<gyeongbuk.length; i++){
          li+="<option value="+gyeongbuk[i]+">"+gyeongbuk[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="광주"){
        $("#selected_area").empty();
        for(var i=0; i<gwangju.length; i++){
          li+="<option value="+gwangju[i]+">"+gwangju[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="대구"){
        $("#selected_area").empty();
        for(var i=0; i<daegu.length; i++){
          li+="<option value="+daegu[i]+">"+daegu[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="대전"){
        $("#selected_area").empty();
        for(var i=0; i<daejeon.length; i++){
          li+="<option value="+daejeon[i]+">"+daejeon[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="세종"){
        $("#selected_area").empty();
        for(var i=0; i<sejong.length; i++){
          li+="<option value="+sejong[i]+">"+sejong[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="울산"){
        $("#selected_area").empty();
        for(var i=0; i<ulsan.length; i++){
          li+="<option value="+ulsan[i]+">"+ulsan[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="전남"){
        $("#selected_area").empty();
        for(var i=0; i<jeonnam.length; i++){
          li+="<option value="+jeonnam[i]+">"+jeonnam[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="전북"){
        $("#selected_area").empty();
        for(var i=0; i<jeonbuk.length; i++){
          li+="<option value="+jeonbuk[i]+">"+jeonbuk[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="제주"){
        $("#selected_area").empty();
        for(var i=0; i<jeju.length; i++){
          li+="<option value="+jeju[i]+">"+jeju[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="충남"){
        $("#selected_area").empty();
        for(var i=0; i<chungnam.length; i++){
          li+="<option value="+chungnam[i]+">"+chungnam[i]+"</option>";
        }
        $("#selected_area").append(li);
      }else if($("#area").val()=="충북"){
        $("#selected_area").empty();
        for(var i=0; i<chungbuk.length; i++){
          li+="<option value="+chungbuk[i]+">"+chungbuk[i]+"</option>";
        }
        $("#selected_area").append(li);
      }
    });

  
  });

  function check_submit(){
    
    
      var stdlist_topic = $("#stdlist_topic").val();
      
      /* alert(stdlist_topic); */
      
      if(stdlist_topic == '직접선택'){
        
        alert("분야를 선택해주세요!");
        
        return false;
      }
      
      
    var ischecked = [false,false,false,false,false,false,false];
    
     ischecked[0] = $("#dow1").is(":checked");
     ischecked[1] = $("#dow2").is(":checked");
     ischecked[2] = $("#dow3").is(":checked");
     ischecked[3] = $("#dow4").is(":checked");
     ischecked[4] = $("#dow5").is(":checked");
     ischecked[5] = $("#dow6").is(":checked");
     ischecked[6] = $("#dow7").is(":checked");
     

   
     for(var i = 0; i < ischecked.length; i++){
      
       if(ischecked[i] == true){
         break;
       }
       
       if( i == 6){
         
         if(ischecked[i] == false){
         alert("요일을 체크해주세요!");
         return false;    
         }
       }
     }
       
       
    var stdlist_content = $("#stdlist_content").val();
    
    //alert(stdlist_content);
    
    if(stdlist_content == ""){
      alert("내용을 입력해주세요!");
      return false;
    }
    
  };
   
  </script>
    
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>

<DIV class='content'>

<DIV class='menu_line' ><h2>스터디 그룹 등록</h2></DIV>
 
 <DIV style='width: 60%; margin: 0px auto;'>
 
<FORM name='frm' id='studylist_create' method='POST' action='./create.do' class="form-horizontal" onSubmit="return check_submit()">
    
      <div class="form-group" style='padding:30px; margin:0;'>   
        <label for="stdlist_title" class="col-md-1 control-label">제목</label> 
        <div class="col-md-10">
          <input type='text' class="form-control input-md" name='stdlist_title' id='stdlist_title' value='' autofocus="autofocus" required="required" placeholder="제목" style="width:70%; height:35px;">
        </div>
      </div>    
      
      <div class="form-group" style='padding:30px; margin:0;'>   
        <label for="email" class="col-md-1 control-label">이메일</label> 
        <div class="col-md-10">
            <input type="text" class="form-control" id="email1" name="email1"  value="" required="required" placeholder="이메일아이디" style="width:25%;height:35px;" /> @
            <input type="text" class="form-control" id="email2" name="email2"  value="" required="required" placeholder="이메일주소" style="width:25%;height:35px;" />
         <div class="input-append">
              <select name="change_email" class="form-control" id="change_email" title="직접선택" style="height:35px;">
                <option value="self">직접입력</option>
                <option value="naver.com">naver.com</option>
                <option value="hanmail.net">hanmail.net</option>
                <option value="nate.com">nate.com</option>
                <option value="daum.net">daum.net</option>
                <option value="paran.com">paran.com</option>
                <option value="gmail.com">gmail.com</option>
                <option value="hotmail.com">hotmail.com</option>
                <option value="yahoo.co.kr">yahoo.co.kr</option>
                <option value="empal.com">empal.com</option>
                <option value="lycos.co.kr">lycos.co.kr</option>
                <option value="korea.com">korea.com</option>
                <option value="dreamwiz.com">dreamwiz.com</option>
                <option value="freechal.com">freechal.com</option>
              </select>          
        </div>
       </div>
      </div>          

      <div class="form-group" style='padding:30px; margin:0;'>   
        <label for="stdlist_topic" class="col-md-1 control-label">분야</label> 
        <div class="col-md-10">
            <select class='form-control' id="stdlist_topic" name="stdlist_topic" title='직접선택'  style="width:40%; height:35px; margin-bottom: 10px;">
            <option selected disabled>직접선택</option>
            <optgroup label="-- 취업 --">
              <option value="취업/토익">토익</option>
              <option value="취업/토익스피킹">토익스피킹</option>
              <option value="취업/토플">토플</option>
              <option value="취업/오픽">오픽</option>
              <option value="취업/텝스">텝스</option>
              <option value="취업/인적성">인적성</option>
              <option value="취업/면접">면접</option>
              <option value="취업/자기소개서">자기소개서</option>
              <option value="취업/대외활동">대외활동</option>
              <option value="취업/공모전">공모전</option>
              <option value="취업/기타">기타</option>
            </optgroup>
             <optgroup label="-- 어학 --">
              <option value="어학/영어">영어</option>
              <option value="어학/일본어">일본어</option>
              <option value="어학/중국어">중국어</option>
              <option value="어학/스페인어">스페인어</option>
              <option value="어학/독일어">독일어</option>
              <option value="어학/프랑스어">프랑스어</option>
              <option value="어학/아랍어">아랍어</option>
              <option value="어학/러시아어">러시아어</option>
              <option value="어학/이탈리아어">이탈리아어</option>
              <option value="어학/기타">기타</option>
            </optgroup>
            <optgroup label="-- 금융 --">
              <option value="금융/주식">주식</option>
              <option value="금융/부동산">부동산</option>
              <option value="금융/경매">경매</option>
              <option value="금융/재테크">재테크</option>
              <option value="금융/경제">경제</option>
              <option value="금융/회계">회계</option>
              <option value="금융/기타">기타</option>
            </optgroup>
            <optgroup label="-- 프로그래밍 --">
              <option value="프로그래밍/JAVA">JAVA</option>
              <option value="프로그래밍/C/C++">C/C++</option>
              <option value="프로그래밍/Python">Python</option>
              <option value="프로그래밍/Ruby">Ruby</option>
              <option value="프로그래밍/Android">Android</option>
              <option value="프로그래밍/Objective-C">Objectice-C</option>
              <option value="프로그래밍/LINUX">LINUX</option>
              <option value="프로그래밍/웹프로그래밍">웹프로그램밍</option>
              <option value="프로그래밍/게임프로그래밍">게임프로그래밍</option>
              <option value="프로그래밍/시스템프로그래밍">시스템프로그래밍</option>
              <option value="프로그래밍/임베디드">임베디드</option>
              <option value="프로그래밍/데이터베이스">데이터베이스</option>
              <option value="프로그래밍/빅데이터">빅데이터</option>
              <option value="프로그래밍/소프트웨어공학">소프트웨어공학</option>
              <option value="프로그래밍/기타">기타</option>
            </optgroup>
            <optgroup label="-- 자기계발 --">
              <option value="자기계발/발표">발표</option>
              <option value="자기계발/자격증">자격증</option>
              <option value="자기계발/악기">악기</option>
              <option value="자기계발/바리스타">바리스타</option>
              <option value="자기계발/베이킹">메이킹</option>
              <option value="자기계발/번역">번역</option>
              <option value="자기계발/번역/기타">기타</option>
            </optgroup>
            <optgroup label="-- 취미 --">
              <option value="취미/독서">독서</option>
              <option value="취미/요리">요리</option>
              <option value="취미/카메라">카메라</option>
              <option value="취미/미술">미술</option>
              <option value="취미/와인">와인</option>
              <option value="취미/천문학">천문학</option>
              <option value="취미/기타">기타</option>
            </optgroup>
            <optgroup label="-- 학생 --">
              <option value="학생/초등학생">초등학생</option>
              <option value="학생/중학생">중학생</option>
              <option value="학생/고등학생">고등학생</option>
              <option value="학생/대학생">대학생</option>
              <option value="학생/일반">일반</option>
              <option value="학생/기타">기타</option>
            </optgroup>
            <optgroup label="-- 고시 --">
              <option value="고시/사법">사법</option>
              <option value="고시/행정">행정</option>
              <option value="고시/외무">외무</option>
              <option value="고시/CPA">CPA</option>
              <option value="고시/공무원">공무원</option>
              <option value="고시/임용">임용</option>
              <option value="고시/감정평가사">감정평가사</option>
              <option value="고시/공인노무사">노무사</option>
              <option value="고시/변리사">변리사</option>
              <option value="고시/세무사">세무사</option>
              <option value="고시/기타">기타</option>
            </optgroup>
            <optgroup label="-- 기타 --">
              <option value="기타/이민">이민</option>
              <option value="기타/입시">입시</option>
              <option value="기타/기타">기타</option>
            </optgroup>
          </select>       
        </div>
      </div>      
      
      <div class="form-group" style='padding:30px; margin:0;'>   
        <label for="area" class="col-md-1 control-label">지역</label> 
          <div class="col-md-2">
           <select class='form-control' id="area" name="area" title='직접선택' style="height:35px; margin-bottom: 10px;">
            <option value="서울">서울</option>
            <option value="경기">경기</option>
            <option value="인천">인천</option>
            <option value="강원">강원</option>
            <option value="경남">경남</option>
            <option value="경북">경북</option>
            <option value="광주">광주</option>
            <option value="대구">대구</option>
            <option value="대전">대전</option>
            <option value="부산">부산</option>
            <option value="세종">세종</option>
            <option value="울산">울산</option>
            <option value="전남">전남</option>
            <option value="전북">전북</option>
            <option value="제주">제주</option>    
            <option value="충남">충남</option>
            <option value="충북">충북</option>
          </select> 
          </div>
          <div class="col-md-6">
            <select class='form-control' id="selected_area" name="selected_area" title='selected_area' 
             style="width: 30%; margin-bottom: 10px; height:35px; ">
             <!-- 선택한 지역의 하위 지역  -->
            </select> 
           </div>
          <!--  <div class="col-md-7" style='padding: 0px; text-align: left; padding: 8px 0px; margin-left: -15px; visibility:hidden;'></div> -->       
      </div>      
  
  <div class="form-group" style='padding:30px; margin:0;'>
    <label for='dow' class="col-md-1 control-label">요일</label>
      <div class="col-md-9">
          <input type="checkbox" name='dow1' id='dow1' value='월' style="margin-bottom: 20px;">월 &nbsp;&nbsp;
          <input type="checkbox" name='dow2' id='dow2' value='화' style="margin-bottom: 20px;">화 &nbsp;&nbsp; 
          <input type="checkbox" name='dow3' id='dow3' value='수' style="margin-bottom: 20px;">수 &nbsp;&nbsp;
          <input type="checkbox" name='dow4' id='dow4' value='목' style="margin-bottom: 20px;">목 &nbsp;&nbsp;
          <input type="checkbox" name='dow5' id='dow5' value='금' style="margin-bottom: 20px;">금 &nbsp;&nbsp;
          <input type="checkbox" name='dow6' id='dow6' value='토' style="margin-bottom: 20px;">토 &nbsp;&nbsp;
          <input type="checkbox" name='dow7' id='dow7' value='일' style="margin-bottom: 20px;">일     
      </div>
  </div>      

  <div class="form-group" style='padding:30px; margin:0;'>
    <label for='stdlist_time' class="col-md-1 control-label">시간</label>
      <div class="col-md-10">
        <input type="text" class="form-control" name='stdlist_time' id='stdlist_time' value='오전' style="width:10%; height:35px; margin-bottom: 10px;">
      
      </div> 
  </div>   
      
  <div class="form-group" style='padding:30px; margin:0;'>
    <label for='stdlist_tot_num' class="col-md-1 control-label">총인원</label>      
    <div class="col-md-10">     
      <input type ='number' class="form-control" name='stdlist_tot_num' id='stdlist_tot_num' min='4' max='10' step='1' value='4' style='width:30%; height:35px; margin-bottom:10px;'>
    </div>   
  </div>
  
  <div class="form-group" style='padding:30px; margin:0;'>
    <label for='stdlist_content' class="col-md-1 control-label">내용</label>        
    <div class="col-md-10">
     <textarea class="form-control" rows="30" name='stdlist_content' id="stdlist_content" style='margin-bottom:10px;'></textarea>
    </div>
  </div>  
  
  <div class="form-group" style='padding:30px; margin:0;'>  
    <div style='text-align:center; margin-top:10px;'>
        <button type="submit"  class="btn btn-default">등록</button>
        <button type="button"  class="btn btn-default" onclick="location.href='/study/nonuser/studylist/list.do'">목록</button>
    </div>
  </div>  

</FORM>
 
</DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
 <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>