
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>

<%
String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
    


<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<style>

*{
color: #000000;
font-size: 16px;
}

.li_none{
    list-style: none; /* 블렛 기호 생략 */
    margin: 10px 10px; /* 위아래 좌우 */
}
</style>

</head>
<script type="text/javascript">
$(function(){
  
  var CATE1 = ['토익','토익스피킹','토플','오픽','텝스','텝스','인적성','면접','자기소개서','대외활동','공모전','기타'];
  var CATE2 = ['영어','일본어','중국어','스페인어','독일어','프랑스어','아랍어','러시아어','이탈리아어','기타'];
  var CATE3 = ['주식','부동산','경매','재테크','경제','회계','기타'];
  var CATE4 = ['JAVA','C/C++','Python','Ruby','Android','Objectice-C','LINUX','웹프로그램밍','게임프로그래밍','시스템프로그래밍','임베디드','데이터베이스','빅데이터','소프트웨어공학','기타'];
  var CATE5 = ['발표','자격증','악기','바리스타','베이킹','번역','기타'];
  var CATE6 = ['독서','요리','카메라','미술','와인','천문학','기타'];
  var CATE7 = ['초등학생','중학생','고등학생','대학생','일반','기타'];
  var CATE8 = ['사법','행정','외무','CPA','공무원','임용','감정평가사','공인노무사','변리사','세무사','기타'];
  var CATE9 = ['이민','입시','기타'];
   
 /* 분야별 select option */
  $('#topic').change(function(){
    
    var li="";
    li += "<option value=''>세부카테고리</option>";
    
     if($("#topic").val()=="취업"){
         $("#selected_topic").empty(); 
        for(var i=0; i<CATE1.length; i++){
               li+="<option value="+CATE1[i]+">"+CATE1[i]+"</option>";
          }
           $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="어학"){
         $("#selected_topic").empty();
       for(var i=0; i<CATE2.length; i++){
               li+="<option value="+CATE2[i]+">"+CATE2[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="금융"){
      $("#selected_topic").empty();
      for(var i=0; i<CATE3.length; i++){
              li+="<option value="+CATE3[i]+">"+CATE3[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="프로그래밍"){
      $("#selected_topic").empty();
      for(var i=0; i<CATE4.length; i++){
              li+="<option value="+CATE4[i]+">"+CATE4[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="자기계발"){
      $("#selected_topic").empty();
      for(var i=0; i<CATE5.length; i++){
              li+="<option value="+CATE5[i]+">"+CATE5[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="취미"){
      $("#selected_topic").empty();
      for(var i=0; i<CATE6.length; i++){
              li+="<option value="+CATE6[i]+">"+CATE6[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="학생"){
      $("#selected_topic").empty();
      for(var i=0; i<CATE7.length; i++){
              li+="<option value="+CATE7[i]+">"+CATE7[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="고시"){
      $("#selected_topic").empty();
      for(var i=0; i<CATE8.length; i++){
              li+="<option value="+CATE8[i]+">"+CATE8[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }else if($("#topic").val()=="기타"){
      $("#selected_topic").empty();
      for(var i=0; i<CATE9.length; i++){
              li+="<option value="+CATE9[i]+">"+CATE9[i]+"</option>";
          }
      $("#selected_topic").append(li);  /* 목표 태그안에 삽입 */
    }
 });
  
  $('#topic').change(function(){
    search_topic(1);
  });
  
  $('#selected_topic').change(function(){
    search_topic(1);
  });
  
  $('#search_go').click(function(){
    search_topic(1);
  });
  
  search_topic(1);

  rank_top5();
});

function search_topic(nowPage){
  
  var topic = $("#topic").val();
  var selected_topic = $("#selected_topic").val();
  var word = $("#word").val();
  var search = $("#search").val();
  
   
  params = "topic="+topic+"&selected_topic="+selected_topic+"&word="+word+"&search="+search+"&nowPage="+nowPage;
  
  $.ajax({
    url : '/study/nonuser/studylist/search.do',
    type: "get",
    data : params,
    dataType : 'JSON',
    success : function(data){
      
      
        /*  alert(data);  */                                                 
        
        var stdlist_no ='';
        var memberno ='';
        var stdlist_title ='';
        var stdlist_topic ='';
        var stdlist_area ='';
        var stdlist_dow ='';
        var stdlist_time ='';
        var stdlist_tot_num ='';
        var stdlist_curr_num ='';
        var stdlist_cnt ='';
        
      var li ='';
    if(data.searchlist.length > 0){
      for(var i=0; i<data.searchlist.length; i++){
        stdlist_no = data.searchlist[i].stdlist_no;
        memberno = data.searchlist[i].memberno;
        stdlist_title = data.searchlist[i].stdlist_title;
        stdlist_topic = data.searchlist[i].stdlist_topic;
        stdlist_area = data.searchlist[i].stdlist_area;
        stdlist_dow = data.searchlist[i].stdlist_dow;
        stdlist_time = data.searchlist[i].stdlist_time;
        stdlist_tot_num = data.searchlist[i].stdlist_tot_num;
        stdlist_curr_num = data.searchlist[i].stdlist_curr_num;
        stdlist_goodcnt = data.searchlist[i].stdlist_goodcnt;
        stdlist_cnt = data.searchlist[i].stdlist_cnt;
        
        li += "<tr>";
        li += "<td style='text-align:center;'>"+stdlist_topic+"</td>";
        li += "<td><a href='./read.do?stdlist_no="+stdlist_no+"&memberno="+memberno+"'>";
        
        if(i<3){
          li += "<img src='./images/crown.png'>  ";
        }
        
        li += stdlist_title+"</a></td>";
        li += "<td style='text-align:center;'>"+stdlist_area+"</td>";
        li += "<td style='text-align:center;'>"+stdlist_dow+"</td>";
        li += "<td style='text-align:center;'>"+stdlist_time+"</td>";
        li += "<td style='text-align:center;'><span class='label label-primary'>"+stdlist_tot_num+"</span></td>";
        li += "<td style='text-align:center;'><span class='label label-primary'>"+stdlist_curr_num+"</span></td>";
        li += "<td>"
        li += "<img src='./images/yellow_star.png'>  "+stdlist_goodcnt;
        li += "</td>"
        li += "<td style='text-align:center;'>";

        if(stdlist_tot_num == stdlist_curr_num){
          
          li +=  "<span class='label label-success'> 모집마감</span>";
        }else{
          
          li +=  "<span class='label label-success'> 모집중</span>";
        }
        
        
        li += "</td>";        
        li += "<td style='text-align:center;'><span class='label label-warning'>"+stdlist_cnt+"</span></td>";        
        li += "</tr>";        
        
      }
      $("#list > tbody").empty();
      $("#list > tbody").append(li);
      
      $("#paging").html(data.paging);
      
    }else{
      alert("검색결과가 없습니다.");
    }
        
      }
  })
  
};


</script>
<body>
<jsp:include page="/menu/top.jsp" flush='false'/>
<DIV class='container'>
<DIV class='content'>



 <DIV style='width: 80%; margin: 0px auto;'>
 
    <div class="form-group">
      <div class="col-md-1" style='padding-right:0;'>
        <select class='form-control' id="search" name="search" style="width:100%; height:35px; margin-bottom: 10px;">
          <option value='stdlist_title'>제목</option>
          <option value='stdlist_area'>지역</option>
          <option value='stdlist_topic'>분야</option>
          <option value='stdlist_dow'>요일</option>
          <option value='stdlist_time'>시간</option>
        </select>
      </div>
      <input type="text" id='word' class="col-md-4" placeholder="Search for..." style='height:25px;'>
      <div class="input-append">
        <button class="btn btn-default" type="button" id='search_go' style='height:35px;'>Go!</button>
      </div>
    </div>
    
    <div class="form-group" style='padding:10px; margin:0;'>
      <div class="col-md-2">
       <select class='form-control' id="topic" name="topic" style=" height:35px; margin-bottom: 10px;">
            <option value='' >직접선택</option>
            <option value='취업'>취업</option>
            <option value='어학'>어학</option>
            <option value='금융'>금융</option>
            <option value='프로그래밍'>프로그래밍</option>
            <option value='자기계발'>자기계발</option>
            <option value='학생'>학생</option>       
            <option value='고시'>고시</option>       
            <option value='기타'>기타</option>        
        </select>
      </div>
      <div class="col-md-6" style='margin-left:-25px;'>
         <select class='form-control' id="selected_topic" name="selected_topic" title='selected_topic'
          style="width: 30%; margin-bottom: 10px; height:35px; ">
            <!-- 선택한 지역의 하위 지역  -->
         </select> 
      </div>
    </div>


 <table class="table table-hover" id='list'>
    <colgroup>
      <col style='width:15%; '>
      <col style='width:25%; '>
      <col style='width:20%; '>
      <col style='width:5%; '>
      <col style='width:5%; '>
      <col style='width:5%; '>
      <col style='width:5%; '>
      <col style='width:9%; '>
      <col style='width:5%; '>
      <col style='width:5%; '>
    </colgroup>
    <thead>
      <tr>
        <th style='text-align:center;'>분야</th>
        <th style='text-align:center;'>제목</th>
        <th style='text-align:center;'>지역</th>
        <th style='text-align:center;'>요일</th>
        <th style='text-align:center;'>시간</th>
        <th style='text-align:center;'>모집인원</th>
        <th style='text-align:center;'>현재인원</th>
        <th style='text-align:center;'>좋아요</th>
        <th style='text-align:center;'>상태</th>
        <th style='text-align:center;'>조회수</th>
      </tr>
    </thead>
    <tbody>

    </tbody>
 </table>
    
    <div id = "paging" style='text-align:center;'>
    </div>
 
    <div class="form-group">
        <button type="button" onclick="location.href='<%=root%>/user/studylist/create.do'" class="btn btn-primary btn-md">등록하기</button>
    </div>    
    
 </DIV>
 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
 <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>