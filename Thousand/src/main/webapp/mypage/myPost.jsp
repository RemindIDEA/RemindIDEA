<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <div id="content">
        <div class="maincontent">
			<c:forEach var="postList" items="${postList}">
		        <div class="pic${postList.rnum%6} card border border-warning">
	            	<a href="viewPost.do?pno=${postList.pno}">
		                <img class="card-img-top" src="img/${postList.mainimg}" alt="test">
	            	</a>
		                <div class="card-body" style="padding:0px; text-align:center;">
		                    <h5 class="container">${postList.title}</h5>
		                    <p>작성자 : ${postList.id}</p>
		                    <p>조회수 : ${postList.readcount}</p>
		                </div>
		        </div>
			</c:forEach>	


            <div class="paging">
           		${map.pagingString}	
            </div>
            <div class="mainback"></div>

        </div>

        <div id="footer">
            <div class="footcontent ">
                <br>
                <p>서울시 관악구 남부순환로 1820,에그엘로우14층 전화번호 : 02-6020-0055 팩스번호 : 02-3285-0012</p>
                <p>Copyright 2017 (주)천개의 레시피. ALL RIGHTS RESERVED.</p>
            </div>
        </div>

    </div>

</body>

</html>