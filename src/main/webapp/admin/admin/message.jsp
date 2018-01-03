<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="./jecss/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>


<script type="text/javascript">
  $(function() {

  });
</script>

</head>
<body>
  <DIV class='container'>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <DIV class='content'>
      <DIV class='title_line'>알림</DIV>
      <DIV class='message'>
        <fieldset class='fieldset_basic'>
          <ul>
            <li class='li_none'>
              <c:forEach var="msg" items="${msgs }">
                ${msg }
              </c:forEach>
            </li>
            <li class='li_none'>
              <c:forEach var="link" items="${links }">
                ${link }
              </c:forEach>
            </li>

          </ul>
        </fieldset>

      </DIV>

    </DIV>
    <!-- content END -->
    <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV>
  <!-- container END -->
</body>

</html>
