<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href=" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	 <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/main.css" />
</head>

<body>
    <div class="loginlogo">
        <img src="img/logo.png" alt="로고이미지" style="width:350px; height:100px;" />
    </div>
    <div class="checkpw border border-warning">
        <br>
        <h2 style="text-align: center;">비밀번호 확인</h2>

        <form action="checkMyPw.do" method="post" name="frm">
            <div class="form-group checkpwgroup">
                <br>
                <label for="password">
                    <h3>비 밀 번 호</h3>
                </label>
                <input type="password" name="pw" class="form-control checkpwform border border-warning" placeholder="비밀번호를 입력하세요"
                    id="password">
            </div>
            <br>
            <button type="submit" class="btn btn-primary border border-warning  checkpwbtn">회원 정보수정</button>
            <button type="button" class="btn btn-primary border border-warning  checkpwcancelbtn"><a href="main.do">취소</a></button>
        </form>
       	<div style="text-align:center;">${message}</div>
    </div>
</body>

</html>