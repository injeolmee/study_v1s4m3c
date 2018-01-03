<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
  String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="UTF-8">
<title></title>
<link href="./css/style.css" rel='Stylesheet' type='text/css'>

<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAuKbPMccNHjVr8PDmG5zRjxZEManXR_MI&libraries=places"></script>
<style>
* {
  color: #000000;
  font-size: 16px;
}

#map {
  height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

.title_line {
  color: #333333;
  width: 20%;
  margin: 20px auto;
  padding: 3px;
  border-bottom: solid 2px #555555;
  text-align: center;
}
</style>

</head>
<script type="text/javascript">

$(function(){
  
  
  $('#reply_create').show();
  $('#reply_update').hide();
  
  //스터디 리스트 등록자의 memberno
  var stdlist_no = $("#stdlist_no").val();
  
 /*  alert("memberno:"+memberno);
  alert("stdlist_no:"+stdlist_no);   */
  
  //댓글 리스트 업로드
  reply_List(stdlist_no, 1);
  
  //댓글 입력 버튼 클릭 event
  $('#reply_but_create').click(function(){
    
    var reply = $("#reply_create").serialize();
    /* alert(reply); */
    replyinsert(reply);
   
   });    
  
  //댓글 수정 버튼 클릭 event
  $('#reply_but_update').click(function(){
    
    
    var reply = $("#reply_update").serialize();
      /* alert(reply);   */
    replyupdate(reply);
   
   });    
  
  
  //신청하기 버튼 클릭 event
  $("#join").click(function(){
    
    //이미 신청 되어있는지 검사
    check_memberno(stdlist_no);
    
  });
  
 
  
  //스터디리스트 수정 버튼에 대한 클릭 이벤트 updata_form 으로 이동
  $("#update_form").click(function(){
    
    var url = "/study/user/studylist/update.do?stdlist_no="+${readVO.stdlist_no};
    $(location).attr('href',url);
    
  });
  
  
 var stdlist_area =  '${readVO.stdlist_area}';
  //지도 출력
  map(stdlist_area);
  
  heart();
  
});
  
  //신청 버튼에 대한 event
  function alert_join(stdlist_no){
    
    var r = confirm("신청하시겠습니까?")

    if (r == true) {
      join(stdlist_no);
        
    } else {
      
      alert("취소되었습니다.");
    }
    
  };
  
  //신청이 성공하게 되면 "신청완료" 출력
  function join(stdlist_no){
    
    $.ajax({
      url : '/study/user/recruit/join.do',
      type: "get",
      data : "stdlist_no="+stdlist_no,
      dataType : 'JSON',
      success : function(data){
        if(data.count == 1){
          alert("신청완료!!");
          }
        }
    })
  };
  
  //댓글 입력에 대한 ajax   
  function replyinsert(reply){
    
     /* alert(reply);  */
    
    $.ajax({
      url : '/study/user/reply/create.do',
      type: "POST",
      data : reply,
      dataType : 'JSON',
      success : function(data){
                    
        
              var stdlist_no = data.stdlist_no;
              
              //댓글입력이 성공하면 댓글 리스트 ajax호출 
              if(data.count==1){
                             
                reply_List(stdlist_no, 1);
              }      
          }
     })
    };
    
  //댓글 수정에 대한 ajax   
    function replyupdate(reply){
      
     /* alert("replyupdate");  */
      
      $.ajax({
        url : '/study/user/reply/update.do',
        type: "POST",
        data : reply,
        dataType : 'JSON',
        success : function(data){
                              
                var stdlist_no = data.stdlist_no;
                var nowPage = data.nowPage;
                //댓글수정이 성공하면 댓글 리스트 ajax호출 
                if(data.count==1){
                               
                  reply_List(stdlist_no, nowPage);
                  
                  $('#reply_create').show();
                  $('#reply_update').hide();
                  
                  
                }      
            }
       })
      };
  
    //댓글 리스트 ajax
    // 댓글에 대한 출력으로 기존의 스터디그룹 번호와  현재페이지
   function reply_List(stdlist_no,nowPage){
       
     $.ajax({
         url : '/study/nonuser/studylist/ajaxread1.do',
         type : 'get',
         data :  "stdlist_no="+stdlist_no+"&nowPage="+nowPage,
         dataType : 'JSON',
         success : function(data){
           /* alert("data:"+data); */
            var reply = "";
            var std_repcont = "";
            var std_repdate = "";
            var std_repname = "";
            var stdlist_no ;
            var std_repno ;
            
            //댓글 수정을 위한 페이지 유지 nowpage값
            var nowPage = data.nowPage;
            
           /*  alert("nowPage :" +nowPage); */
            
            for(var i=0; i<data.reply.length; i++){
              /* console.log(data.length); */
              std_repcont = data.reply[i].std_repcont;
              std_repdate = data.reply[i].std_repdate;
              std_repname = data.reply[i].std_repname;
              stdlist_no = data.reply[i].stdlist_no;
              std_repno = data.reply[i].std_repno;
    
              reply+=  "<DIV style='border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: #999999; padding: 10px 0px 10px 0px; height:50px;'>";
              reply+=  " <span style='font-weight: bold; line-height: 30px;'>";
              reply+=  " <IMG src='./images/user.png' style='padding-right: 1%;'>";
              reply+=  std_repname+"</span>";      
              reply+=  " <span style='font-size: 0.7em; color: #999999; padding-left: 2%;'>";
              reply+=  std_repdate+"</span>";
              
              reply+= "<div style='float: right;'>";
              reply+=  "<a style='font-size: 0.8em; color: #999999;' onclick='javascript:check_memberno_reply("+stdlist_no+","+std_repno+"," + 1 +" )'>수정</a>";
              reply+=  "<a style='font-size: 0.8em; color: #999999;' onclick='javascript:check_memberno_reply("+stdlist_no+","+std_repno+"," + 2 +" )'> | 삭제</a>";
              reply+= "</div>";
              reply+= "<div>";
              reply+=  "<span style='margin-bottom: 10px;'>"+std_repcont+"</span>";
              reply+= "</div>";
              reply+= "</div>";
              } 
            
           //댓글 수정에서 페이지 유지를 위한 현재페이지 값 전달
            $("#nowPage",reply_update).val(nowPage);
            
            $("#paging").html(data.paging);
            $("#reply_list").html(reply);
           }
       })
   };

   //신청 버튼에 대하여 같은 스터디 그룹에 이미 신청되어있는지 검사 ajax
   function check_memberno(stdlist_no){
     
     var params = "stdlist_no="+stdlist_no
     /* alert("params:"+params);  */
     
     $.ajax({
       url : '/study/user/recruit/check_memberno.do',
       type: "GET",
       data : params,
       dataType : 'JSON',
       success : function(data){
         
               /* alert(data.count); */
    
               var stdlist_no = data.stdlist_no;
         
               if(data.count==0){
                              
                 alert_join(stdlist_no);
                 
               }else{
                 
                 alert("이미 신청되었습니다.");
               }    
               
           }
      })
     };
   
     //댓글을 수정하거나 삭제하려고 할 때 작성자가 맞는지 검사.
     function check_memberno_reply(stdlist_no, std_repno, str){
       
       var params = "stdlist_no="+stdlist_no+"&std_repno="+std_repno+"&str="+str;
       
       $.ajax({
         url : '/study/user/reply/check_memberno.do',
         type: "GET",
         data : params,
         dataType : 'JSON',
         success : function(data){
           /* alert(data); */
           
          var std_repno = data.stdlist_no
          var std_repno =  data.std_repno
           
           if(data.count == 1){
             
             if(data.str == 1){
               
               std_reply_update(stdlist_no,std_repno);
               
             }else if(data.str == 2){
               
               std_reply_delete(stdlist_no,std_repno);
             }
               
           }else{
             
             alert("수정 및 삭제는 작성자만 가능 합니다.");
           }
         }
       
       })
     };
   
     
     // 댓글 삭제에 대해서 y/n alert 창 출력
     function std_reply_delete(stdlist_no,std_repno){
             
       var r = confirm("삭제하시겠습니까?")

       if (r == true) {
         replydelete(stdlist_no,std_repno);
           
       } else {
         
         alert("취소되었습니다.");
       }
       
     };
     
     
    //댓글 삭제 처리에 대한 ajax
    function replydelete(stdlist_no,std_repno){
      
      var params = "stdlist_no="+stdlist_no+"&std_repno="+std_repno;
      
      $.ajax({
        url : '/study/user/reply/delete.do',
        type: "GET",
        data : params,
        dataType : 'JSON',
        success : function(data){
          
          var stdlist_no = data.stdlist_no;
          
          if(data.count > 0){
            
            alert("삭제완료");
            reply_List(stdlist_no, 1);
            
          }else{
            
            
          }
          
        }
      })
      
    } ;
     
    
    //작성자 본인이 댓글 수정시
    //수정 버튼을 클릭하면 댓글에 대한 값 읽어서  reply_update form에 출력
    function std_reply_update(stdlist_no,std_repno){
      
      $('#reply_create').hide();
      $('#reply_update').show();
      
      
      var params = "stdlist_no="+stdlist_no+"&std_repno="+std_repno;
      
      /* alert("params:"+params); */
      
      $.ajax({
        url : '/study/user/reply/read.do',
        type: "GET",
        data : params,
        dataType : 'JSON',
        success : function(data){
          
          var reply_update = $("#reply_update");
          var std_repcont = data.std_repcont;
          var std_repname = data.std_repname;
          var std_repno = data.std_repno;
          
          /* alert("std_repcont :"+std_repcont);
          alert("std_repname :"+std_repname); */
          
          $("#std_repcont", reply_update).val(std_repcont);
          $("#std_repname", reply_update).val(std_repname);
          $("#std_repno", reply_update).val(std_repno);
        }
      
      })
    };
     
    //스터디그룹 삭제 처리
    function delete_stdlist(stdlist_no){
      
      params ='stdlist_no='+stdlist_no;
      
      $.ajax({
        url : '/study/user/studylist/delete.do',
        type: "GET",
        data : params,
        dataType : 'JSON',
        success : function(data){
          
          if(data.count > 0){
            
            location.href="/study/nonuser/studylist/list.do";
          }
                  
        }     
    })
   };
   
   //스터디그룹 삭제에 대한 alertform
   function alert_delete_form(){
     
     var r = confirm("스터디그룹을 삭제하시겠습니까?  삭제 하면 복구 할 수 없습니다.")

     
     if (r == true) {
     
      var stdlist_no =  ${readVO.stdlist_no};
       
       delete_stdlist(stdlist_no);
         
     } else {
       
       alert("취소되었습니다.");
     }
     
   };
   
/*-----------------------------  좋아요  --------------------------------------- */   
 
   function heart(){
     
     var stdlist_no = ${readVO.stdlist_no};
     var params = "stdlist_no="+stdlist_no;
     
     
     $.ajax({
       url : '/study/nonuser/std_recom/heart.do',
       type: "GET",
       data : params,
       dataType : 'JSON',
       success : function(data){
         
         var src='';
         /* alert(data.count); */
         
         if(data.count == 0){ //회원이 이 글에 대해서 체크를 안했다면
           
             src = "<img id='good' onclick='heart_Y();' src='./images/star.png'>";
           }else if(data.count == 1 || data.count == 2){ //로그인을 하지 않았거나 체크를 했었다면
             
             src = "<img id='good' onclick='heart_Y();' src='./images/yellow_star.png'>";
           }
         
          $("#heart").html(src);
         }
       })
   };
   
   function heart_Y(){
     
    var good = $("#good");
    var str = good.attr('src');
    /* console.log(str); */
    
    if( str == './images/star.png'){
      good.attr("src", "./images/yellow_star.png");
      heart_up();
    }else if( str == './images/yellow_star.png'){
      good.attr("src", "./images/star.png");
      heart_down();
    }
   };
   
   function heart_up(){
     
     var stdlist_no = ${readVO.stdlist_no};
     var params = "stdlist_no="+stdlist_no;
     
     $.ajax({
       url : '/study/user/std_recom/heart_up.do',
       type: "GET",
       data : params,
       dataType : 'JSON',
       success : function(data){
         
         if(data.count ==1 ){
           alert("좋아요가 반영되었습니다.");
         }
         
       }
     })
   };
   
   function heart_down(){
     
     var stdlist_no = ${readVO.stdlist_no};
     var params = "stdlist_no="+stdlist_no;
   
     $.ajax({
       url : '/study/user/std_recom/heart_down.do',
       type: "GET",
       data : params,
       dataType : 'JSON',
       success : function(data){
         
         if(data.count ==1 ){
           alert("좋아요가 반영해제되었습니다.");
         }
         
       }     
     })
   };
   
/*-----------------------------  좋아요  --------------------------------------- */   
   
/*-----------------------지도 출력---------------------------  */
  function map(address){
    // DBMS 주소: 서울 종로구 종로12길 15 (코아빌딩1)
    // 주소에 소괄호 '(', ')'가 들어가면 지도 검색이 안됨. 
    // (: (
    // ): )
    address = address.replace(/\(/gi, '(');
    address = address.replace(/\)/gi, ')');
    // alert('address () 변환: ' + address); return;
    
    var url = 'http://maps.googleapis.com/maps/api/geocode/json';
    var params = 'address=' + address;
    $.get(url, params, response_map, 'json');// GET 방식 Ajax 요청
  };  
  
  function response_map(data){
    // lat:latitude, 위도(북극 남극간 위치)
    // lng: langitude, 경도(날짜 구분선을 따라가는 동서간 위치)
    // alert(data.results[0].geometry.location.lat + ' / ' + data.results[0].geometry.location.lng); return;
 
    // 전송된 위도, 경도를 설정
    var latlng = new google.maps.LatLng(data.results[0].geometry.location.lat, data.results[0].geometry.location.lng)
    
    // 지도 출력
    
    map = new google.maps.Map(document.getElementById('map'), {
          center: latlng,
          zoom: 15
          
        });
   

    var geocoder = new google.maps.Geocoder();
      
    infowindow = new google.maps.InfoWindow();
    var service = new google.maps.places.PlacesService(map);
    service.nearbySearch({
      location: latlng,
      radius: 500,
      type: ['cafe']
    }, callback);
    
  };


  function callback(results, status) {
    if (status === google.maps.places.PlacesServiceStatus.OK) {
      for (var i = 0; i < results.length; i++) {
           createMarker(results[i]);
          }
        }
      };

  function createMarker(place) {
      var placeLoc = place.geometry.location;
      var marker = new google.maps.Marker({
        map: map,
        animation: google.maps.Animation.DROP,
        position: place.geometry.location
    });

      google.maps.event.addListener(marker, 'click', function() {
      
      infowindow.setContent(place.name);
      infowindow.open(map, this);
      });
   };
   

/*-----------------------지도 출력---------------------------  */   
   
</script>

<body>
  <jsp:include page="/menu/top.jsp" flush='false' />

  <DIV class='container'>
    <DIV class='content'>


      <DIV style='width: 80%; margin: 0px auto;'>


        <div style='border-bottom: solid 1px; margin: 90px;'>
          <div class="form-group"
            style='float: left; width: 50%; margin: 30px;'>
            <label for="stdlist" class="col-md-3 control-label">
              <a href="/study/user/recruit/recruit_list.do?stdlist_no=${readVO.stdlist_no}">
                신청 리스트</a>
            </label>
            <div class="col-md-6" style='text-align: left;'>

              <c:choose>
                <c:when
                  test="${readVO.stdlist_curr_num == readVO.stdlist_tot_num }">
                  <span class="label label-default">신청이 완료되었습니다.</span>
                </c:when>
                <c:otherwise>
                  <button type='button' id='join'>신청하기</button>
                </c:otherwise>

              </c:choose>
            </div>
          </div>
          
          <div style='float: right; margin-right: 30px; margin-top: 30px;'>

            <div id='heart' style='float: left; margin-left: 10px; margin-right:20px;'>
            </div>
            <button type='button'
              onclick="location.href='<%=root%>/nonuser/studylist/list.do'"
              class="btn btn-primary btn-md">목록</button>
            <button type='button' class="btn btn-primary btn-md"
              id='update_form'>수정</button>
            <button type='button' class="btn btn-primary btn-md"
              onclick='javascript:alert_delete_form()'>삭제</button>
          </div>
        </div>

        <div style='float: left; margin-left: 10%;'>
          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_title" class="col-md-2 control-label">제목</label>
            <div class="col-md-8" style='text-align: left;'>
              <span>${readVO.stdlist_title}</span>
            </div>
          </div>

          <div class="form-group"
            style='padding: 20px; margin-botton: 2px;width:400px'>
            <label for="stdlist_topic" class="col-md-2 control-label">분야</label>
            <div class="col-md-8" style='text-align: left;'>
              <span>${readVO.stdlist_topic}</span>
            </div>
          </div>

          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_area" class="col-md-2 control-label">지역</label>
            <div class="col-md-8" style='text-align: left;'>
              <span>${readVO.stdlist_area}</span>
            </div>
          </div>

          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_dow" class="col-md-2 control-label">요일</label>
            <div class="col-md-8" style='text-align: left;'>
              <span>${readVO.stdlist_dow}</span>
            </div>
          </div>

          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_time" class="col-md-2 control-label">시간</label>
            <div class="col-md-6" style='text-align: left;'>
              <span>${readVO.stdlist_time}</span>
            </div>
          </div>

          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_content" class="col-md-2 control-label">내용</label>
            <div class="col-md-7" style='text-align: left;'>
              <span>${readVO.stdlist_content}</span>
            </div>
          </div>


          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_memname" class="col-md-2 control-label">이름</label>
            <div class="col-md-6" style='text-align: left;'>
              <span>${readVO.memname}</span>
            </div>
          </div>

          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_mememail" class="col-md-3 control-label">이메일</label>
            <div class="col-md-7" style='text-align: left;'>
              <span>${readVO.mememail}</span>
            </div>
          </div>

          <div class="form-group" style='padding: 20px; margin: 0; width:400px'>
            <label for="stdlist_memphone" class="col-md-3 control-label">핸드폰번호</label>
            <div class="col-md-7" style='text-align: left;'>
              <span>${readVO.memphone}</span>
            </div>
          </div>
        </div>

        <div id="map"
          style='width: 300px; height: 300px; margin: 0px auto;'></div>

        <div style='margin-top: 10%'>
        
          
      <FORM name='reply' id='reply_update' action="" style="text-align: left; width: 50%; height:40px; padding-bottom:5%; margin: 0px auto;">
        <input type='hidden' name='stdlist_no' id='stdlist_no'value='${readVO.stdlist_no}'>
        <input type='hidden' name='std_repno' id='std_repno' value=''>
        <input type='hidden' name='nowPage' id='nowPage' value=''>
        <textarea name= "std_repcont" id="std_repcont" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 90%; height: 60px;"></textarea>
        <button type="button" id='reply_but_update' name='reply_but_update' style="width: 7%; height:70px; float: right;">수정</button> 
      </FORM>          

      <FORM name='reply' id='reply_create' action="" style="text-align: left; width: 50%; height:40px; padding-bottom: 5%; margin: 0px auto;">
        <input type='hidden' name='stdlist_no' id='stdlist_no'value='${readVO.stdlist_no}'>
        <textarea name= "std_repcont" id="std_repcont" rows="100" cols="50" placeholder="댓글을 입력해주세요." style="width: 90%; height: 60px;"></textarea>
        <button type="button" id='reply_but_create' name='reply_but_create' style="width: 7%; height:70px; float: right;">등록</button> 
      </FORM>
  
          
        </div>


        <div id='reply_list'style='margin-top:70px; width: 60%; margin: 0px auto;'></div>

        <div id="paging" style='text-align: center;'></div>

      </DIV>
    </DIV>
    <!-- content END -->
  </DIV>
  <!-- container END -->
  <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>