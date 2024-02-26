<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	 <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/main.css" />
    <script type="text/javascript" src="script/main.js"></script>
</head>

<body>
    <div class="loginlogo">
        <img src="img/logo.png" alt="로고이미지" style="width:350px; height:100px;" />
    </div>
    <div class="join border border-warning">
        <br>
        <h2 style="text-align: center;">회 원 가 입</h2>

        <form name="frm" action="loginForm.do" method="post" >
            <div class="form-group form-inline joingroup">
                <label style="width:100px;" for="id">아 이 디</label>
                <input type="text" name="id" style="width:270px;" class="form-control border border-warning"
                    placeholder="아이디를 입력하세요" id="id" required>
                &nbsp;&nbsp;<input type="button" class="btn btn-primary border border-warning" name="checkid"
                    onclick="idCheck()" value="중복체크" />
            </div>
            <div class="form-group form-inline joingroup">
                <label style="width:100px;" for="password">비 밀 번 호</label>
                <input type="password" name="pw" style="width:270px;" class="form-control border border-warning"
                    placeholder="비밀번호를 입력하세요" id="password" required>
            </div>
            <div class="form-group form-inline joingroup">
                <label style="width:100px; font-size:15px;" for="password">비밀번호 확인</label>
                <input type="password" name="pw2" style="width:270px;" class="form-control border border-warning"
                    placeholder="비밀번호를 입력하세요" id="password" required>
            </div>
            <div class="form-group form-inline joingroup">
                <label style="width:100px;" for="email">이 메 일</label>
                <input type="email" name="email" style="width:270px;" class="form-control border border-warning"
                    placeholder="이메일을 입력하세요" id="email" required>
            </div>
            <div class="form-group form-inline joingroup">
                <label style="width:100px;" for="password">닉 네 임</label>
                <input type="text" name="nickname" style="width:270px;" class="form-control border border-warning"
                    placeholder="비밀번호를 입력하세요" id="password" required>
                &nbsp;&nbsp;<input type="button" class="btn btn-primary border border-warning" name="checknickname"
                    onclick="nicknameCheck()" value="중복체크" />
            </div>
            <br>
            <button type="submit" class="btn btn-primary border border-warning  joinformbtn" onclick="return joincheck()">회 원 가 입</button>
            <button type="button" class="btn btn-primary border border-warning joincancelbtn"><a href="login.do">취소</a></button>
        </form>
    </div>
</body>

</html>