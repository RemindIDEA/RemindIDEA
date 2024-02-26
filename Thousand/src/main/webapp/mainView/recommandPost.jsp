<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="main.css" />
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

    <div id="recomcontent">
        <div class="recommaincontent">

            <div class="recomlike">
                <span class="border border-warning">좋아요</span>
                <span class="border border-warning">조회수</span>
            </div>
            <div class="divcarousel">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ul class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ul>

                    <div class="carousel-inner">
                        <h2 style="text-align: center;">메뉴별 추천 게시물</h2>
                        <div class="carousel-item active">
                            <div class="row">
                                <div class="pic1 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            첫번째 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic2 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            첫번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic3 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            첫번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 추가적인 아이템들 -->
                        <div class="carousel-item">
                            <div class="row">
                                <div class="pic1 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            두번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic2 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            두번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic3 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            두번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- 추가적인 아이템들 -->
                        <div class="carousel-item">
                            <div class="row">
                                <div class="pic1 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            세번쨰 케러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic2 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            세번째 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic3 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            세번째 캐러셀
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>

                </div>

            </div>
            <div class="divcarousel">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ul class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ul>

                    <div class="carousel-inner">
                        <h2 style="text-align: center;">오늘의 추천게시물</h2>
                        <div class="carousel-item active">
                            <div class="row">
                                <div class="pic1 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            첫번째 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic2 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            첫번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic3 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            첫번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 추가적인 아이템들 -->
                        <div class="carousel-item">
                            <div class="row">
                                <div class="pic1 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            두번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic2 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            두번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic3 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            두번쨰 캐러셀
                                        </p>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- 추가적인 아이템들 -->
                        <div class="carousel-item">
                            <div class="row">
                                <div class="pic1 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            세번쨰 케러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic2 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            세번째 캐러셀
                                        </p>
                                    </div>
                                </div>


                                <div class="pic3 card border border-warning ">
                                    <img class="card-img-top" src="article01.jpg" alt="test">
                                    <div class="card-body">
                                        <p class="card-text">
                                            세번째 캐러셀
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>

                </div>

            </div>

        </div>
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