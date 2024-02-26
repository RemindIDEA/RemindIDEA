<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>닉네임 중복체크</title>
      <script type="text/javascript" src="script/main.js"></script>
   </head>
   <body>
      <h2>닉네임 중복확인</h2>
      <form action="checkNickname.do" method="get" name="frm">
         닉네임 <input type="text" name="nickname" value="${nickname}"> 
         <input type="submit" value="중복체크"><br>
         <c:if test="${result==1}">
            <script>
                opener.document.frm.nickname.value=""; //부모창의 userid 초기화
             </script>   
             ${nickname}는 이미 사용중인 닉네임입니다.    
         </c:if>
         <c:if test="${result==-1}">
            ${nickname}는 사용가능 합니다. 
            <input type="button" value="사용" class="cancel" onclick="nicknameok('${nickname}')">
         </c:if>
      </form>
   </body>
</html>