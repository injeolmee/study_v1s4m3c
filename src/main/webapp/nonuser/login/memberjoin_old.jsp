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
<!-------------------------- Web Logo Part -------------------------->
<link rel="shortcut icon" href="<%=root%>/menu/images/ico/Short Logo.png">
<!---------------------------------------------------------------------->
<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/jquery.cookie.js"></script>

<script type="text/javascript">
var copy_code = ""; // 이메일로 보내진 인증 코드
var msg = "";

$(function(){     
 
  
  /* -------------------------------- 아이디 중복 검사 클릭 시작-----------------------------------  */ 
  $('#id_btn').click(function(){    
    $.cookie('check_id', 'FALSE');// Cookie 생성
    var memid = $('#memid').val();
    var limitid =  /^[a-z0-9_-]{6,15}$/;
    if(memid== '' || memid==null) {
      msg = "<span style='color: red;'>아이디를 등록해주세요.</span>";
      $('#panel_id').html(msg); 
      $('#panel_id').show();
    } else {      
      if(limitid.test(memid) == false) {
        msg = "<span style='color: red;'>영어소문자 숫자 _ - (6~15자)</span>";
        $('#panel_id').html(msg); 
        $('#panel_id').show();
      } else { 
        memid = "memid="+ $('#memid').val();      
        // alert(memid);  
      
        $.ajax({
          type: 'GET',
          url: '/study/nonuser/login/check_id.do',
          cache: false,
          data: memid,       
          dataType: 'JSON',        
          success: function(data){
          // alert(data.cnt_id);
            if(data.cnt_id==0){
              msg = "<span style='color:green;'>사용 가능한 아이디입니다.</span>";
              $('#panel_id').html(msg); 
              $('#panel_id').show();
             
              $.cookie('check_id', 'TRUE'); // Cookie 값 변경
            } else{
              msg = "<span style='color:red;'>중복된 아이디입니다.</span>";
              $('#panel_id').html(msg); 
              $('#panel_id').show();
              }

          },
          // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
          error: function (request, status, error){  
            var msg = "에러가 발생했습니다. <br><br>";
            msg += "다시 시도해주세요.<br><br>";
            msg += "request.status: " + request.status + "<br>";
            msg += "request.responseText: " + request.responseText + "<br>";
            msg += "status: " + status + "<br>";
            msg += "error: " + error;

            $('#modal_title').html("에러");   
            $('#modal_content').html(msg); 
            $('#modal_panel').modal(); 
          }
        });  //end ajax   
        
        }           
      } // end else 

  });    // end id_btn
  /* -------------------------------- 아이디 중복 검사 클릭 종료----------------------------------  */  
  
  /* -------------------------------- 이메일 중복 검사 클릭 시작-----------------------------------  */ 
  $('#email_btn').click(function(){    
    $.cookie('check_email', 'FALSE'); // Cookie 생성
    var mememail = $('#mememail').val();
    var limitmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    if(mememail== '' || mememail==null) {
      msg = "<span style='color: red;'>이메일을 등록해주세요.</span>";
      $('#panel_email').html(msg); 
      $('#panel_email').show();
    } else {      
      if(limitmail.test(mememail) == false) {
        msg = "<span style='color: red;'>이메일 형식에 맞지 않습니다.</span>";
        $('#panel_email').html(msg); 
        $('#panel_email').show();
      } else { 
        mememail = "mememail="+ $('#mememail').val();      
        // alert(mememail);  
      
        $.ajax({
          type: 'GET',
          url: '/study/nonuser/login/check_email.do',
          cache: false,
          data: mememail,       
          dataType: 'JSON',        
          success: function(data){
          // alert(data.cnt_email);
            if(data.cnt_email==0){
              msg = "<span style='color:green;'>사용 가능한 이메일입니다.</span>";
              $('#panel_email').html(msg); 
              $('#panel_email').show();
             
              $.cookie('check_email', 'TRUE');  // Cookie 값 변경
            } else{
              msg = "<span style='color:red;'>중복된 이메일입니다.</span>";
              $('#panel_email').html(msg); 
              $('#panel_email').show();
              }

          },
          // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
          error: function (request, status, error){  
            var msg = "에러가 발생했습니다. <br><br>";
            msg += "다시 시도해주세요.<br><br>";
            msg += "request.status: " + request.status + "<br>";
            msg += "request.responseText: " + request.responseText + "<br>";
            msg += "status: " + status + "<br>";
            msg += "error: " + error;

            $('#modal_title').html("에러");   
            $('#modal_content').html(msg); 
            $('#modal_panel').modal(); 
          }
        });  //end ajax   
        
        }           
      } // end else 

  });    // end email_btn
  /* -------------------------------- 이메일 중복 검사 클릭 종료----------------------------------  */  
  
  
  /* -------------------------------- 코드 전송 클릭 시작-----------------------------------  */ 
  $('#code_btn').click(function(){    
    $.cookie('code_send', 'FALSE'); // Cookie 생성
    
    $('#panel_code').html("<span style='color:green;'>메일을 전송중입니다.</span>"); 
    $('#panel_code').show();
    
    var tomail = $('#mememail').val();
    tomail = tomail.trim();
    var title = "Study Desk 이메일 인증 코드입니다";
    var content = "스터디 데스크 이메일 인증 코드입니다.<br>인증 완료 후 회원가입이 가능합니다.<br>인증하기를 눌러주세요.<br>인증코드 : ";
    
    if ($.cookie('check_email') != 'TRUE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_code').html(msg); 
      $('#panel_code').show();
      return false;
    } else {  // 중복 검사 확인 완료
        
      $.ajax({
        type: 'POST',
        url: '/study/nonuser/login/mailSending.do',
        data: "tomail=" + tomail + "&title=" + title + "&content=" + content,
        dataType: 'JSON',        
        success: function(data){
          // alert("data.codesend: " + data.codesend);
          // alert("data.code: " + data.code); 
          if(data.codesend == "성공") {                       
            copy_code = data.code;
            // alert("copy_code: " + copy_code); 
            msg = "<span style='color:green;'>이메일에 코드가 전송되었습니다.</span>";
            $('#panel_code').html(msg); 
            $('#panel_code').show();
            
            $.cookie('code_send', 'TRUE'); // Cookie 값 변경
          } else {            
            msg = "<span style='color:red;'>코드 전송을 실패했습니다.</span>";
            $('#panel_code').html(msg); 
            $('#panel_code').show();
          } 

        },
        // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
        error: function (request, status, error){  
          var msg = "에러가 발생했습니다. <br><br>";
          msg += "다시 시도해주세요.<br><br>";
          msg += "request.status: " + request.status + "<br>";
          msg += "request.responseText: " + request.responseText + "<br>";
          msg += "status: " + status + "<br>";
          msg += "error: " + error;

          $('#modal_title').html("에러");   
          $('#modal_content').html(msg); 
          $('#modal_panel').modal();   
        }
      });    // end ajax   
    }  // end else        
  });  // end code_btn
  /* -------------------------------- 코드 전송 클릭 종료-----------------------------------  */ 
  
  
  /* -------------------------------- 인증 확인 클릭 시작-----------------------------------  */ 
  $('#codecf_btn').click(function(){        
    $.cookie('code_confirm', 'FALSE'); // Cookie 생성
    // alert("copy_code2: " + copy_code); 
    if ($.cookie('check_email') != 'TRUE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    }
    if ($.cookie('code_send') != 'TRUE') { //  코드 전송 실패
      msg = "<span style='color:red;'>코드 전송이 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    } 
    var mailcode = $('#mailcode').val(); // 이메일에 전송된 코드 복사되는 곳
    // alert("mailcode: " + mailcode); 
    if (copy_code == mailcode) { // 복사한 코드와 이메일로 전송된 코드가 같을 때 
      msg = "<span style='color:green;'>인증 확인 되었습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();

      $.cookie('code_confirm', 'TRUE'); // Cookie 값 변경
    } else {
      msg = "<span style='color:red;'>인증 코드가 다릅니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    }        
    
       
  });  // end codecf_btn
  /* -------------------------------- 인증 확인 클릭 종료-----------------------------------  */   
  


   /* ---------------------------------------------------- 주소 시작------------------------------------------------------------- */
  
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
      }else if($("#area").val()=="부산"){
        $("#selected_area").empty();
        for(var i=0; i<busan.length; i++){
          li+="<option value="+busan[i]+">"+busan[i]+"</option>";
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
    /* ---------------------------------------------------- 주소 종료------------------------------------------------------------- */
    
    
    /* ------------------------------------------------ 사진 업로드 시작--------------------------------------------------------- */   
    
    $('#file1MF').on('change', function() {
        
        ext = $(this).val().split('.').pop().toLowerCase(); //확장자
        
        //배열에 추출한 확장자가 존재하는지 체크
        if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
            resetFormElement($(this)); //폼 초기화
            alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
        } else {
            file = $('#file1MF').prop("files")[0];
            blobURL = window.URL.createObjectURL(file);
            $('#image_preview img').attr('src', blobURL);
            $('#image_preview').slideDown(); //업로드한 이미지 미리보기 
            $(this).slideUp(); //파일 양식 감춤
        }
    });
    
    /**
    onclick event handler for the delete button.
    It removes the image, clears and unhides the file input field.
    */
    $('#image_preview button').bind('click', function() {
        resetFormElement($('#file1MF')); //전달한 양식 초기화
        $('#file1MF').slideDown(); //파일 양식 보여줌
        $(this).parent().slideUp(); //미리 보기 영역 감춤
        return false; //기본 이벤트 막음
    });
    
    /** 
     * 폼요소 초기화 
     * Reset form element
     * 
     * @param e jQuery object
     */
     function resetFormElement(e) {
         e.wrap('<form>').closest('form').get(0).reset(); 
         //리셋하려는 폼양식 요소를 폼(<form>) 으로 감싸고 (wrap()) , 
         //요소를 감싸고 있는 가장 가까운 폼( closest('form')) 에서 Dom요소를 반환받고 ( get(0) ),
         //DOM에서 제공하는 초기화 메서드 reset()을 호출
         e.unwrap(); //감싼 <form> 태그를 제거
     }
 
     /* ------------------------------------------------ 사진 업로드 종료--------------------------------------------------------- */   
    
    
  $('#join').click(function(){  
    var memid = $('#memid').val();
    var mememail = $('#mememail').val();
    var mempasswd = $('#mempasswd').val();
    var mempasswd_c = $('#mempasswd_c').val();
    var memname = $('#memname').val();
    var membirth = $('#membirth').val();
    var memgender = $('input:radio[name=memgender]:checked').val();
    var memphone = $('#memphone').val();
    var memsns = $('#memsns').val();
    var memintro = $('#memintro').val(); 

    var limitpw = /^[a-z0-9]{6,12}$/;
    
    if (memid == "" || memid == null) {
      alert('아이디를 입력하세요');
      focus.memid;
      return false;
    }    

    if ($.cookie('check_id') != 'TRUE') {
      alert('중복검사가 완료되지 않았습니다');
      focus.memid;
      return false;    
    }
    if (mememail == "" || mememail == null) {
      alert('이메일을 입력하세요');
      focus.mememail;
      return false;
    }    

    if ($.cookie('check_email') != 'TRUE') {
      alert('중복검사가 완료되지 않았습니다');
      focus.mememail;
      return false;    
    }
    
    if ($.cookie('code_send') != 'TRUE') {
      alert('코드전송이 완료되지 않았습니다.');
      focus.mememail;
      return false;    
    }
    
    if ($.cookie('code_confirm') != 'TRUE') {
      alert('인증코드가 확인되지 않았습니다.');
      focus.mailcode;
      return false;    
    }
    
    if (mempasswd == "" || mempasswd == null) {
      alert('패스워드를 입력하세요');
      focus.mempasswd;
      return false;
    }    
    
    if(limitpw.test(mempasswd) == false) {
      alert("비밀번호는 영어나 숫자 조합 6자리 이상입니다.");   
      focus.mempasswd;
      return false;
    }     
    
    if(/(\w)\1\1\1/.test(mempasswd)) {
      alert("같은문자를 4번 이상 사용하실 수 없습니다.");  
      focus.mempasswd;
      return false;
    }     
    
    if (mempasswd_c == "" || mempasswd_c == null) {
      alert('패스워드 확인을 입력하세요');
      focus.mempasswd_c;
      return false;
    }
    
    if (mempasswd != mempasswd_c) {
      alert('패스워드와 패스워드 확인란이 다릅니다');
      focus.mempasswd_c;
      return false;
    }
    
    if (memname == '' || memname == null) {
      alert('이름을 입력하세요');
      focus.memname;
      return false;
    }
    
    if (memname.length < 2) {
      alert('이름을 2자 이상 입력해주세요.');
      focus.memname;
      return false;
    }
    
    if (membirth == '' || membirth == null) {
      alert('생년월일을 입력해주세요');
      focus.membirth;
      return false;
    } 
    
    if (isNaN(membirth)) {
      alert('생년월일은 숫자만 들어갈 수 있습니다');
      focus.membirth;
      return false;
    }
    
    if (membirth.length != 8) {
      alert('생년월일을 8자리로 입력해주세요');
      focus.membirth;
      return false;
    }
    
    if ($.cookie('check_id') != 'TRUE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_id').html(msg); 
      $('#panel_id').show();
      return false;
    }
    
    if ($.cookie('check_email') != 'TRUE') { // 중복 검사 실패
      msg = "<span style='color:red;'>중복 검사가 완료되지 않았습니다.</span>";
      $('#panel_email').html(msg); 
      $('#panel_email').show();
      return false;
    }
    
    if ($.cookie('code_send') != 'TRUE') { //  코드 전송 실패
      msg = "<span style='color:red;'>코드 전송이 완료되지 않았습니다.</span>";
      $('#panel_code').html(msg); 
      $('#panel_code').show();
      return false;
    } 
    
    if ($.cookie('code_confirm') != 'TRUE') { //  코드 확인 실패
      msg = "<span style='color:red;'>코드 인증이 완료되지 않았습니다.</span>";
      $('#panel_confirm').html(msg); 
      $('#panel_confirm').show();
      return false;
    } 
    
  return true;  
  });
    
  
}); // function 끝
  
</script>

</head> 
 
<body>

<jsp:include page="/menu/top.jsp" flush="false" />
<DIV class='container'>

<DIV class='content' style='width: 90%; margin: 0px auto; text-align: center;'>

  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="<%=root%>/nonuser/login/admin_create.do">관리자가입</A>
  </ASIDE>


<!-- <div id="modal_panel" class="modal fade" role="dialog">
  <div class="modal-dialog">

    Modal content
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><span id="modal_title"></span></h4>
      </div>
      <div class="modal-body">
        <p id="modal_content"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>  --> 
 
<DIV class='title_line' style='margin: 50px auto; font-size: 20px;'>회원 가입</DIV>
 
<FORM name='frm_join' id='frm_join' method='POST' action='./memberjoin.do' enctype="multipart/form-data" class="form-horizontal">  
<input type="hidden" id="memconfirm" name="memconfirm" value="Y">
<input type="hidden" id="memauth" name="memauth" value="U">


  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 아이디</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="memid" name="memid" placeholder="영어 대소문자, 숫자, _ , - 가능합니다" style='margin:0px;'>
    </div>
    <div class="col-md-1" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="id_btn"><strong>중복 검사</strong></button>
    </div>
    <div class="col-md-3" id="panel_id" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div>
  
    <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 비밀번호</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="password" class="form-control" id="mempasswd" name="mempasswd" placeholder="Password 영어나 숫자(6 ~ 12자)">
    </div>
    <div class="col-md-4" id="panel_pwd" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 비밀번호 확인</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="password" class="form-control" id="mempasswd_c" name="mempasswd_c" placeholder="Password 확인">
    </div>
    <div class="col-md-4" id="panel_pwd_c" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div> 
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align:center; padding: 8px;'><strong>* 이메일</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="mememail" name="mememail" placeholder="이메일  @ 이메일.com">
    </div>
    <div class="col-md-1" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="email_btn"><strong>중복 검사</strong></button>
    </div>
    <div class="col-md-3" id="panel_email" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 이메일 인증</strong></div>
    <div class="col-md-4" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="code_btn" style='margin-left: 10px;'><strong>코드 전송</strong></button>
    </div>
    <div class="col-md-3" id="panel_code" style= "text-align: left; margin-left:35px; padding: 6px 0px 15px 0px; font-weight: 900;"></div>     
  </div>
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 인증 코드</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="mailcode" name="mailcode" placeholder="이메일에서 인증 코드를 복사하여 붙여주세요.">
    </div>
    <div class="col-md-1" style='padding: 0px; text-align: left;'>
      <button type="button" class="btn" id="codecf_btn"><strong>인증 확인</strong></button>
    </div>
    <div class="col-md-3" id="panel_confirm" style= "text-align: left; padding: 6px 0px 15px 10px; font-weight: 900;"></div>     
  </div> 
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 이름</strong></div>
    <div class="col-md-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="memname" name="memname" placeholder="홍길동">
    </div>
    <div class="col-md-offset-1 col-md-3" id="panel_pwd_c" style= "text-align: left; padding: 20px; font-weight: 900;"></div>     
  </div>    
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 생년월일</strong></div>
    <div class="col-md-7" style='padding-left: 10px; text-align: left;'>
      <input type="text" class="form-control" id="membirth" name="membirth" maxlength="8" placeholder="예) 19990101" style='width:50%;'>
      <label>
        <input type="checkbox" id="mbirthvb" name="mbirthvb" value="N" style='margin: 5px;'><strong>비공개</strong>
      </label>
    </div>   
  </div>    
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 성별</strong></div>
    <div class="col-sm-7" style='padding: 10px 0px 5px 10px; text-align: left;'>
      <label class="radio-inline" style= 'padding:0px 0px 5px; margin:0px 30px;'><input type="radio" name="memgender" id="male" value="남" checked> 남자</label>        
      <label class="radio-inline" style= 'padding:0px 0px 5px;'><input type="radio" name="memgender" id="female" value="여" > 여자</label>
      <label>
        <input type="checkbox" id="mgendervb" name="mgendervb" value="N" style= "margin:0px 5px 5px 20px;"><strong>비공개</strong>
      </label>
    </div>
  </div> 
        
  <div class="form-group" style= 'padding: 20px;'>
  <input type="hidden" id="memaddress" name="memaddress" value="">
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>* 주소</strong></div>
    <div class="col-sm-2" style='padding-left: 10px; text-align: left;'>
      <select class="form-control" id="area" name="area" title='직접선택'>
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
    <div class="col-sm-2" style='text-align: left; padding-left: 0px;'>    
      <select class="form-control" id="selected_area" name="selected_area" title='selected_area'></select>  <!-- 선택한 지역의 하위 지역  -->
    </div>
    <div class="col-sm-3" style='text-align: left; padding: 10px 0px 5px 0px;'>
      <label>
        <input type="checkbox" id="maddressvb" name="maddressvb" value="N" style= "margin:5px;"><strong>비공개</strong>
      </label>
    </div>   
  </div>   
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>전화번호</strong></div>
    <div class="col-sm-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="memphone" name="memphone" maxlength="11" placeholder="예) 01012345678">
    </div>
    <div class="col-sm-4" style='text-align: left; padding: 10px 0px 5px 0px;'>
      <label>
        <input type="checkbox" id="mphonevb" name="mphonevb" value="N" style= "margin:0px 5px 5px 20px;"><strong>비공개</strong>
      </label>
    </div>   
  </div>    
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>SNS</strong></div>
    <div class="col-sm-3" style='padding-left: 10px;'>
      <input type="text" class="form-control" id="memsns" name="memsns" placeholder="예) 트위터 홍길동">
    </div>
    <div class="col-sm-4" style='text-align: left; padding: 10px 0px 5px 0px;'>
      <label>
        <input type="checkbox" id="msnsvb" name="msnsvb" value="N" style= "margin:0px 5px 5px 20px;"><strong>비공개</strong>
      </label>
    </div>   
  </div>   
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>자기소개</strong></div>
    <div class="col-sm-5" style='padding-left: 10px;'>
      <textarea class= 'form-control' rows="3" id="memintro" name="memintro" placeholder="자기소개를 해주세요"></textarea>
    </div>
    <div class="col-sm-2" style='text-align: left; padding: 10px 0px 5px 0px;'>
      <label>
        <input type="checkbox" id="mintrovb" name="mintrovb" value="N" style= "margin:0px 5px 5px 20px;"><strong>비공개</strong>
      </label>
    </div>   
  </div>   
     
  <div class="form-group" style= 'padding: 50px 20px 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>사진 첨부</strong></div>              
    <div class="col-sm-4" style='padding: 5px 0px 5px 10px;'>
      <input type="file" id="file1MF" name="file1MF">
    </div> 
    <div class="col-sm-3" style='text-align: left; padding: 10px 0px 5px 0px;'>
      <label>
        <input type="checkbox" id="mintrovb" name="mintrovb" value="N" style= "margin:0px 5px 5px 20px;"><strong>비공개</strong>
      </label>
    </div>   
  </div>  
  
  <div class="form-group" style= 'padding: 20px;'>
    <div class="col-md-offset-2 col-md-2" style='background-color: #e4e4e4; text-align: center; padding: 8px;'><strong>미리보기</strong></div>
    <div class="col-sm-7" id="image_preview" style='padding: 5px 0px 5px 10px; text-align:left;'>
      <img src="<%=root %>/jeimages/none2.png" style='width: 110px; height:120px;'>
      <button type="button" class="btn" id="file_remove" style='margin-left: 40px;'><strong>REMOVE</strong></button>
    </div>
  </div>

  <div class="form-group" style='padding: 20px;'>        
    <div class="col-md-offset-5 col-md-2" style= 'padding: 50px;'>
      <button type="submit" id="join" name="join" class="btn btn-success">가입하기</button>
    </div>    
  </div>
  
 

</FORM>
 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 