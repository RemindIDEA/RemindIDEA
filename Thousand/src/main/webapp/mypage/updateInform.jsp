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
    <link rel="stylesheet" href="css/main.css" />
     <script type="text/javascript" src="script/main.js"></script>
</head>

<body>
     <div id="header">
        <div id="top">
            <div class="logo">
                <a href="main.do"><img src="img/logo.png" alt="로고"></a>
            </div>
            <div class="search">
                <div class="input-group">
                    <input type="text" class="form-control" size="50" placeholder="검색어를 입력하세요." required>
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-danger">Search</button>
                    </div>
                </div>
            </div>
            <div class="mypage">
            	
                <div class="icon1" >
                	<div class="dropdown">
	                	<button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
	                		<i class="fa-solid fa-user-large" style="font-size: 50px"></i>
	                	</button>
		               	<div class="dropdown-menu">
					   		<a class="dropdown-item" href="checkMyPw.do">회원정보수정</a>
					    	<a class="dropdown-item" href="myPost.do">내 게시글</a>
					      	<a class="dropdown-item" href="logout.do">로그아웃</a>
					    </div>
			    	</div>
                </div>
               
                <div class="icon2"><a href="posting.do"><i class="fa-regular fa-pen-to-square" style="font-size: 50px"></i></a></div>
            </div>
        </div>
    </div>

    <div id="nav">
        <div id="navbar">
            <ul class="nav nav-pills">
                <li class="nav-item menu1 border border-2">
                    <a class="nav-link" href="main.do"><h3 style="color:white; text-align:center;">전체게시물</h3></a>
                </li>
                <li class="nav-item menu2 border border-right-1">
                    <a class="nav-link"  href="#">
                    <h3 style="color:white; text-align:center;">추천게시물</h3>
                    </a>
                </li>
                <li class="nav-item menu3 border border-right-1">
                    <a class="nav-link"  href="#">
                    	<h3 style="color:white; text-align:center;">인기게시물</h3>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="updatemyinform border border-warning">
        <br>
        <h2 style="text-align: center;">내정보 수정</h2>

        <form action="updateMyInform.do" method="post" name="frm">
            <div class="form-group form-inline updatemyinformngroup">
                <label style="width:100px;" for="id">아 이 디</label>
                <input type="text" name="id" style="width:270px;" class="form-control border border-warning"
                    value="${member.id}" id="id" readonly>
            </div>
            <div class="form-group form-inline updatemyinformngroup">
                <label style="width:100px;" for="password">비 밀 번 호</label>
                <input type="password" name="pw" style="width:270px;" class="form-control border border-warning"
                    value="${member.pw}" id="password" required>
            </div>
            <div class="form-group form-inline updatemyinformngroup">
                <label style="width:100px; font-size:15px;" for="password">비밀번호 확인</label>
                <input type="password" style="width:270px;" class="form-control border border-warning"
                    value="${member.pw}" id="password2" required>
            </div>
            <div class="form-group form-inline updatemyinformngroup">
                <label style="width:100px;" for="password">이 메 일</label>
                <input type="email" name="email" style="width:270px;" class="form-control border border-warning"
                    value="${member.email}" id="email" required>
            </div>
            <div class="form-group form-inline joingroup">
                <label style="width:100px;" for="password">닉 네 임</label>
                <input type="text" name="nickname" style="width:270px;" class="form-control border border-warning"
                    value="${member.nickname }" id="password" required>
                &nbsp;&nbsp;<input type="button" class="btn btn-primary border border-warning" name="checknickname"
                    onclick="nicknameCheck()" value="중복체크" />
            </div>
            <br>
            <button type="submit" class="btn btn-primary border border-warning  updatemyinformbtn">나 의 정 보 수 정</button>
            <button type="button" class="btn btn-primary border border-warning updatemyinformcancelbtn"><a href="main.do">취소</a></button>
            <input type="button" class="btn btn-primary rounded-lg border border-warning" style="position : relative; top :10px; left: 190px;width: 150px;height: 40px;background-color: #FF6800;text-align: center;color: white;font-size: 20px;" onclick="deleteInform('deleteInform.do?id=${member.id}')" value="회원탈퇴">
        </form>
    </div>
</body>

</html>