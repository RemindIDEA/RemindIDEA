<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글쓰기페이지</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
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
    
	<form action="posting.do" method="post" name="frm" enctype="multipart/form-data">
    <div id="postcontent">
        <div class="postmaincontent">
            
            <div class="postitle">
                <div><button class="postbtn rounded-lg border border-warning">제목</button> &nbsp;&nbsp;
                    <input style="width:500px; height: 45px;" name="title" type="text" placeholder="제목을 입력하세요" required>
                </div>
            </div>
            <div class="postsummary">
                <div><button class="postbtn rounded-lg border border-warning">소개글</button> &nbsp;&nbsp;
                    <input style="width:500px; height: 45px;" type="text" name="summary" placeholder="소개글을 입력하세요" required>
                </div>
            </div>
            
            <div class="postcategory form-inline">
                <button class="postbtn rounded-lg border border-warning">카테고리</button>&nbsp;&nbsp;&nbsp;
                <div class="form-group form-inline">
                    <label for="sel1"></label>
                    <select class="form-control" name="recipe" style="width:150px; height: 45px;" id="sel1">
                        <option>볶음</option>
                        <option>탕</option>
                        <option>튀김</option>
                        <option>조림</option>
                        <option>구이</option>
                        <option>찜</option>
                        <option>기타</option>
                    </select>
                </div>
                &nbsp;&nbsp;
                <div class="form-group form-inline">
                    <label for="sel2"></label>
                    <select class="form-control" id="sel2" name="local" style="width:150px; height: 45px;">
                        <option>한식</option>
                        <option>일식</option>
                        <option>중식</option>
                        <option>양식</option>
                        <option>기타</option>
                    </select>
                </div>
                &nbsp;&nbsp;
                <div class="form-group form-inline">
                    <label for="sel3"></label>
                    <select class="form-control" id="sel3" name="item" style="width:150px; height: 45px;">
                        <option>소고기</option>
                        <option>돼지고기</option>
                        <option>닭고기</option>
                        <option>해산물</option>
                        <option>가공식품</option>
                        <option>채소</option>
                        <option>곡류</option>
                        <option>기타</option>
                    </select>
                </div>
            </div>
            
            <div class="postmainimg">
                <div class="upload" style="width: 300px; height: 300px z-index:10 ; overflow: hidden;">
                    <div class="image-preview"></div>
                </div>
                <input style="position: absolute; top:-0px;" type="file" name="mainimg" onchange="setThumbnail(event);" id="mainimg" accept="image/*">
                 <script>
			      function setThumbnail(event) {
			        var reader = new FileReader();
			
			        reader.onload = function(event) {
			          var img = document.createElement("img");
			          img.setAttribute("src", event.target.result);
			          document.querySelector(".image-preview").appendChild(img);
			          document.querySelector(".image-preview").appendChild(img);
			        };
			        reader.readAsDataURL(event.target.files[0]);
			      }
			    </script>
            </div>
            
            <div class="postcookitem">
                <div><button class="postbtn rounded-lg border border-warning">조리순서</button> &nbsp;&nbsp;
                    <input style="width:500px; height: 100px;" name="content1" type="text" placeholder=" 재료를 입력하세요.">
                </div>
                
                <div class="cookstep1">
                    <input class="step" name="content2" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep1" type="button" value="+">
                    <div class="poststepimg">
                        <div id="preimg"  class="image-preview2"></div>
	                </div>
	                <input  style="position: absolute; top:0px; left:650px; " type="file" name="produceImg2" onchange="setThumbnail1(event);" id="produceImg2" accept="image/*">
	                <script>
				      function setThumbnail1(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview2").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                
                <div class="cookstep2">
                    <input class="step" name="content3" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep2" type="button" value="+">
                    <input class="minusstep2" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview3"></div>
	                </div>
	                <input style="position: absolute; top:0px; left:650px;" type="file" name="produceImg3" onchange="setThumbnail2(event);" id="produceImg3" accept="image/*">
	                <script>
				      function setThumbnail2(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview3").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                
                <div class="cookstep3">
                    <input class="step" name="content4" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep3" type="button" value="+">
                    <input class="minusstep3" type="button" value="-">
                     <div class="poststepimg">
                        <div id="preimg"  class="image-preview4"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg4" onchange="setThumbnail3(event);" id="produceImg4" accept="image/*">
	                <script>
				      function setThumbnail3(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview4").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="cookstep4">
                    <input class="step" name="content5" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep4" type="button" value="+">
                    <input class="minusstep4" type="button" value="-">
                    <div class="poststepimg">
                        <div id="preimg"  class="image-preview5"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg5" onchange="setThumbnail4(event);" id="produceImg5" accept="image/*">
	                <script>
				      function setThumbnail4(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview5").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="cookstep5">
                    <input class="step" name="content6" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep5" type="button" value="+">
                    <input class="minusstep5" type="button" value="-">
                    <div class="poststepimg">
                        <div id="preimg"  class="image-preview6"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg6" onchange="setThumbnail5(event);" id="produceImg6" accept="image/*">
	                <script>
				      function setThumbnail5(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview6").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="cookstep6">
                    <input class="step" name="content7" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep6" type="button" value="+">
                    <input class="minusstep6" type="button" value="-">
                     <div class="poststepimg">
                        <div id="preimg"  class="image-preview7"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg7" onchange="setThumbnail6(event);" id="produceImg7" accept="image/*">
	                <script>
				      function setThumbnail6(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview7").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="cookstep7">
                    <input class="step" name="content8" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep7" type="button" value="+">
                    <input class="minusstep7" type="button" value="-">
                      <div class="poststepimg">
                        <div id="preimg"  class="image-preview8"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg8" onchange="setThumbnail7(event);" id="produceImg8" accept="image/*">
	                <script>
				      function setThumbnail7(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview8").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="cookstep8">
                    <input class="step" name="content9" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep8" type="button" value="+">
                    <input class="minusstep8" type="button" value="-">
                     <div class="poststepimg">
                        <div id="preimg"  class="image-preview9"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg9" onchange="setThumbnail8(event);" id="produceImg9" accept="image/*">
	                <script>
				      function setThumbnail8(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview9").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="cookstep9">
                    <input class="step" name="content10" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="plusstep9" type="button" value="+">
                    <input class="minusstep9" type="button" value="-">
                      <div class="poststepimg">
                        <div id="preimg"  class="image-preview10"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg10" onchange="setThumbnail9(event);" id="produceImg10" accept="image/*">
	                <script>
				      function setThumbnail9(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview10").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="cookstep10">
                    <input class="step" name="content11" type="text" placeholder=" 조리 순서를 입력하세요.">
                    <input class="minusstep10" type="button" value="-">
                      <div class="poststepimg">
                        <div id="preimg"  class="image-preview11"></div>
	                </div>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg11" onchange="setThumbnail10(event);" id="produceImg11" accept="image/*">
	                <script>
				      function setThumbnail10(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          document.querySelector(".image-preview11").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <div class="postinsertbtn">
                    <div>
                        <input type="submit" class="postbtn posting rounded-lg border border-warning" value="등록">
                        <a href="main.do">
                        <input type="button" class="postbtn posting rounded-lg border border-warning" value="취소"></a>
                    </div>
                </div>
            </div>
        


        </div>

    </div>
	</form>
    <div id="footer">
        <div class="footcontent ">
            <br>
            <p>서울시 관악구 남부순환로 1820,에그엘로우14층 전화번호 : 02-6020-0055 팩스번호 : 02-3285-0012</p>
            <p>Copyright 2017 (주)천개의 레시피. ALL RIGHTS RESERVED.</p>
        </div>
    </div>


</body>

</html>