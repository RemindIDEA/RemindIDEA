<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>id중복체크</title>
      <script type="text/javascript" src="script/main.js"></script>
   </head>
   <body>
      <h2>id중복확인</h2>
      <form action="idcheck.do" method="get" name="frm">
         id <input type="text" name="id" value="${id}"> 
         <input type="submit" value="중복체크"><br>
         <c:if test="${result==1}">
            <script>
                opener.document.frm.userid.value=""; //부모창의 userid 초기화
             </script>   
             ${id}는 이미 사용중인 id입니다.    
         </c:if>
         <c:if test="${result==-1}">
            ${id}는 사용가능 합니다. 
            <input type="button" value="사용" class="cancel" onclick="idok('${id}')">
         </c:if>
      </form>
   </body>
</html>