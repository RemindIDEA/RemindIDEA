<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	 <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/main.css"/>
    <script type="text/javascript" src="script/main.js"></script>
</head>

<body>
    <div class="loginlogo">
		<a href="index.do"> 
			<img src="img/logo.png" alt="로고이미지" style="width: 350px; height: 100px;" />
		</a>
	</div>
    <div class="login border border-warning">
        <form name="frm" action="login.do" method="post">
            <div class="form-group logingroup">
                <br>
                <label for="id">
                    <h3>아 이 디</h3>
                </label>
                <input type="text" name="id" class="form-control loginform border border-warning" placeholder="아이디를 입력하세요"
                    id="id" required>
            </div>
            <div class="form-group logingroup">
                <label for="password">
                    <h3>비 밀 번 호</h3>
                </label>
                <input type="password" name="pw" class="form-control loginform border border-warning" placeholder="비밀번호를 입력하세요"
                    id="password" required>
            </div>
            <br>
            <button type="submit" class="btn btn-primary border border-warning  loginbtn" onclick="return loginCheck()">로그인</button>
            <div class="findgroup">
                <button type="button" class="btn" style="width: 100px;position: relative;left: 60px;top: 30px;text-decoration: underline;" >
                	<a href="loginForm.do">회원가입</a>
                </button>
                <button type="button" class="btn" style="width: 120px; height: 38px; position: relative; left: 70px; top: 30px; text-decoration: underline;">
                	<a href="findId.do">아이디찾기</a>
                </button>
                <button type="submit" class="btn" style="width: 130px;height: 38px;position: relative;left: 70px;top: 30px;text-decoration: underline;">
                	<a href="findPw.do">비밀번호찾기</a>
                </button>
            </div>
        </form>
        <br>
        <div style="text-align:center;"> ${message}</div>
    </div>
</body>

</html>