<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>

<style>

*{
color: #000000;
}

</style>

<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">

$(function(){
  

});

 //회원 승인에 대한 alert
 function alert_confirm_Y(stdlist_no, memberno){
   
   var r = confirm("승인하시겠습니까?")

   if (r == true) {
     confirm_Y(stdlist_no, memberno);
       
   } else {
     
     alert("취소되었습니다.");
   }
   
 };
 
 //회원 거절에 대한 alert
 function alert_confirm_N(stdlist_no, memberno){
   
   var r = confirm("거절하시겠습니까?")

   if (r == true) {
     confirm_N(stdlist_no, memberno);
       
   } else {
     
     alert("취소되었습니다.");
   }
 };
 
 // 회원 승인에 대한 ajax 
 //  스터디그룹 인원수 증가 
 function confirm_Y(stdlist_no, memberno){
   
   var params = "stdlist_no="+stdlist_no+"&memberno="+memberno;
   
   $.ajax({
     url : '/study/user/recruit/confirm_Y.do',
     type: "get",
     data : params,
     dataType : 'JSON',
     success : function(data){
       if(data.count == 1){
         alert("승인했습니다.");
         }
        location.href="/study/user/recruit/recruit_list.do?stdlist_no="+data.stdlist_no;  
       }
   })
   
 };
 
 // 회원 거절에 대한 ajax
 function confirm_N(stdlist_no, memberno){
   
   var params = "stdlist_no="+stdlist_no+"&memberno="+memberno;
   
   $.ajax({
     url : '/study/user/recruit/confirm_N.do',
     type: "get",
     data : params,
     dataType : 'JSON',
     success : function(data){
       if(data.count == 1){
         alert("거절했습니다.");
         }
       location.href="/study/user/recruit/recruit_list.do?stdlist_no="+data.stdlist_no;  
       }
   })
   
 };

</script>

<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'>

 <DIV style='width: 60%; margin: 0px auto; text-align:center;'>

    <table class="table table-hover">
        <thead>
          <tr>
            <th>모집번호</th>
            <th>회원이름</th>
            <th>성별</th>
            <th>이메일</th>
            <th>승인여부</th>
            <th>권한</th>
            <th>스터디 번호</th>
            <th>승인</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="recruit_list" items="${recruit_list}">
          <tr>
            <td>${recruit_list.recruitno} </td>
            <td>${recruit_list.memname}</td>
            <td>${recruit_list.memgender}</td>
            <td>${recruit_list.mememail}</td>
            <td>${recruit_list.confirm}</td>
            <td>${recruit_list.std_auth}</td>
            <td>${recruit_list.stdlist_no}</td>
            <td> 
              <c:choose>   
               <c:when test= "${recruit_list.std_auth.equals('U') }">
                <button type='button' class="btn btn-default" onclick="alert_confirm_Y(${recruit_list.stdlist_no},${recruit_list.memberno})">승인</button>
                <button type='button' class="btn btn-default" onclick="alert_confirm_N(${recruit_list.stdlist_no},${recruit_list.memberno})">거절</button>
               </c:when>
              </c:choose>
            </td>
          </tr>      
          </c:forEach>
       </tbody>
     </table>

</DIV>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>