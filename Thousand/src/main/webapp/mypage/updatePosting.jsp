<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
               
                <div class="icon2">
                	<a href="posting.do"><i class="fa-regular fa-pen-to-square" style="font-size: 50px"></i></a>
                </div>
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

	<form action="updatePosting.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pno" value="${postList.pno}">
    <div id="postcontent">
        <div class="postmaincontent">
            <div class="postitle">
                <div><button class="postbtn rounded-lg border border-warning">제목</button> &nbsp;&nbsp;
                    <input style="width:500px; height: 45px;" name="title" type="text" value="${postList.title}" required>
                </div>
            </div>
            <div class="postsummary">
                <div><button class="postbtn rounded-lg border border-warning">소개글</button> &nbsp;&nbsp;
                    <input style="width:500px; height: 45px;" type="text" name="summary" value="${postList.summary }" required>
                </div>
            </div>
            
            <div class="postcategory form-inline">
                <button class="postbtn rounded-lg border border-warning">카테고리</button>&nbsp;&nbsp;&nbsp;
                <input type="hidden" name="categorycode" value="${category.categorycode}">
                <div class="form-group form-inline">
                    <label for="sel1"></label>
                    <select class="form-control" name="recipe" style="width:150px; height: 45px;" id="sel1">
                        <option>${category.recipe}</option>
                        <option>볶음</option>
                        <option>탕</option>
                        <option>튀김</option>
                        <option>조림</option>
                        <option>찜</option>
                        <option>구이</option>
                        <option>기타</option>
                    </select>
                </div>
                &nbsp;&nbsp;
                <div class="form-group form-inline">
                    <label for="sel2"></label>
                    <select class="form-control" id="sel2" name="local" style="width:150px; height: 45px;">
                        <option>${category.local }</option>
                        <option>한식</option>
                        <option>일식</option>
                        <option>양식</option>
                        <option>중식</option>
                        <option>기타</option>
                    </select>
                </div>
                &nbsp;&nbsp;
                <div class="form-group form-inline">
                    <label for="sel3"></label>
                    <select class="form-control" id="sel3" name="item" style="width:150px; height: 45px;">
                        <option>${category.item }</option>
                        <option>소고기</option>
                        <option>돼지고기</option>
                        <option>닭고기</option>
                        <option>양고기</option>
                        <option>해산물</option>
                        <option>가공식품</option>
                        <option>채소</option>
                        <option>곡류</option>
                    </select>
                </div>
            </div>
            
            <div class="postmainimg">
                <div class="upload" style="width: 300px; height: 300px; overflow: hidden;">
                    <div class="image-preview"><img id="nonmainimg" src="img/${postList.mainimg}"/></div>
                    <input type="hidden" name="nonmainimg" value="${postList.mainimg}"/>
                </div>
                <input style="position: absolute; top:-0px;"  type="file" name="mainimg" onchange="setThumbnail(event);" id="mainimg" accept="image/*">
                 <script>
			      function setThumbnail(event) {
			        var reader = new FileReader();
			        reader.onload = function(event) {
			          const preimg = document.getElementById("nonmainimg");
			          preimg.remove();
			          var img = document.createElement("img");
			          img.setAttribute("src", event.target.result);
			          img.setAttribute("id", "nonmainimg");
			          document.querySelector(".image-preview").appendChild(img);
			        };
			        reader.readAsDataURL(event.target.files[0]);
			      }
			    </script>
            </div>
            
            <div class="postcookitem">
                <div><button class="postbtn rounded-lg border border-warning">조리순서</button> &nbsp;&nbsp;
                    <input style="width:500px; height: 100px;" name="content1" type="text" value="${postList.content[0]}">
                </div>
                <div class="cookstep1">
                    <input class="step" name="content2" type="text" value="${postList.content[1]}">
                    <input class="plusstep1" type="button" value="+">
                    <div class="poststepimg">
                        <div id="preimg" class="image-preview2"><img id="preimg2" src="img/${ postList.produceImg[0]}"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg2" value="${postList.produceImg[0]}"/>
	                <input   style="position: absolute; top:0px; left:650px;" type="file" name="produceImg2" onchange="setThumbnail1(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail1(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				          const preimg = document.getElementById("preimg2");
					      preimg.remove();
				          var img = document.createElement("img");
				          img.setAttribute("src", event.target.result);
				          img.setAttribute("id", "preimg2");
				          document.querySelector(".image-preview2").appendChild(img);
				        };
				        reader.readAsDataURL(event.target.files[0]);
				      }
				    </script>
                </div>

                <div class="cookstep2 on">
                    <input class="step" name="content3" type="text" value="${postList.content[2]}">
                    <input class="plusstep2" type="button" value="+">
                    <input class="minusstep2" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview3"><img id="preimg3" src="img/${postList.produceImg[1]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg3" value="${postList.produceImg[1]}"/>
	                <input  style="position: absolute; top:0px; left:650px;"  type="file" name="produceImg3" onchange="setThumbnail2(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail2(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg3");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg3");
				          document.querySelector(".image-preview3").appendChild(img);
				        };
				        reader.readAsDataURL(event.target.files[0]);
				      }
				    </script>
                </div>
                <c:if test="${empty postList.content[2]}">
                <script>
                	document.querySelector(".cookstep2").classList.remove('on');
              	</script>
                </c:if>
               
                <div class="cookstep3 on"> 
                    <input class="step" name="content4" type="text" value="${postList.content[3]}">
                    <input class="plusstep3" type="button" value="+">
                    <input class="minusstep3" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview4"><img id="preimg4" src="img/${postList.produceImg[2]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg4" value="${postList.produceImg[2]}"/>
	                <input  style="position:relative; top:-200px; left:650px;" type="file" name="produceImg4" onchange="setThumbnail3(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail3(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg4");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg4");
				          document.querySelector(".image-preview4").appendChild(img);
				        };
				        reader.readAsDataURL(event.target.files[0]);
				      }
				    </script>
                </div>
                 <c:if test="${empty postList.content[3]}">
               <script>
               	document.querySelector(".cookstep3").classList.remove('on');
              	</script>
                </c:if>
                
                
                <div class="cookstep4 on">
                    <input class="step" name="content5" type="text" value="${postList.content[4]}">
                    <input class="plusstep4" type="button" value="+">
                    <input class="minusstep4" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview5"><img id="preimg5" src="img/${postList.produceImg[3]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg5" value="${postList.produceImg[3]}"/>
	                <input style="position:relative; top:-200px; left:650px;" type="file" name="produceImg5" onchange="setThumbnail4(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail4(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg5");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg5");
				          document.querySelector(".image-preview5").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <c:if test="${empty postList.content[4]}">
                <script>
               		document.querySelector(".cookstep4").classList.remove('on');
              	</script>
                </c:if>
                
                
                <div class="cookstep5 on">
                    <input class="step" name="content6" type="text" value="${postList.content[5]}">
                    <input class="plusstep5" type="button" value="+">
                    <input class="minusstep5" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview6"><img id="preimg6" src="img/${postList.produceImg[4]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg6" value="${postList.produceImg[4]}"/>
	                <input style="position:relative; top:-200px; left:650px;" type="file" name="produceImg6" onchange="setThumbnail5(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail5(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg6");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg6");
				          document.querySelector(".image-preview6").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
              <c:if test="${empty postList.content[5]}">
                <script>
                	document.querySelector(".cookstep5").classList.remove('on');
              	</script>
                </c:if>
                
                <div class="cookstep6 on">
                    <input class="step" name="content7" type="text" value="${postList.content[6]}">
                    <input class="plusstep6" type="button" value="+">
                    <input class="minusstep6" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview7"><img id="preimg7" src="img/${postList.produceImg[5]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg7" value="${postList.produceImg[5]}"/>
	                <input style="position:relative; top:-200px; left:650px;" type="file" name="produceImg7" onchange="setThumbnail6(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail6(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg7");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg7");
				          document.querySelector(".image-preview7").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				        
				      }
				    </script>
                </div>
                <c:if test="${empty postList.content[6]}">
                <script>
                	document.querySelector(".cookstep6").classList.remove('on');
              	</script>
                </c:if>
                <div class="cookstep7 on">
                    <input class="step" name="content8" type="text" value="${postList.content[7]}">
                    <input class="plusstep7" type="button" value="+">
                    <input class="minusstep7" type="button" value="-">
                    <div class="poststepimg">
	                      <div id="preimg" class="image-preview8">
	                      		<img id="preimg8" src="img/${postList.produceImg[6]}" alt="순서사진"/>
	                      </div>
	                </div>
	                <input type="hidden" name="nonproduceImg8" value="${postList.produceImg[6]}"/>
	                <input style="position:relative; top:-200px; left:650px;" type="file" name="produceImg8" onchange="setThumbnail7(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail7(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg8");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg8");
				          document.querySelector(".image-preview8").appendChild(img);
				        };
				
				        reader.readAsDataURL(event.target.files[0]);
				      }
				    </script>
                </div>
      
                 <c:if test="${empty postList.content[7]}">
               <script>
               		document.querySelector(".cookstep7").classList.remove('on');
              	</script>
                </c:if>
               
                
                <div class="cookstep8 on">
                    <input class="step" name="content9" type="text" value="${postList.content[8]}">
                    <input class="plusstep8" type="button" value="+">
                    <input class="minusstep8" type="button" value="-">
                   <div class="poststepimg">
                      <div id="preimg" class="image-preview9"><img id="preimg9" src="img/${postList.produceImg[7]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg9" value="${postList.produceImg[7]}"/>
	                <input style="position:relative; top:-200px; left:650px;" type="file" name="produceImg9" onchange="setThumbnail8(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail8(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg9");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg9");
				          document.querySelector(".image-preview9").appendChild(img);
				        };
				        reader.readAsDataURL(event.target.files[0]);
				      }
				    </script>
                </div>
                <c:if test="${empty postList.content[8]}">
                <script>
                	document.querySelector(".cookstep8").classList.remove('on');
              	</script>
                </c:if>
                
               
                <div class="cookstep9 on">
                    <input class="step" name="content10" type="text" value="${postList.content[9]}">
                    <input class="plusstep9" type="button" value="+">
                    <input class="minusstep9" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview10"><img id="preimg10" src="img/${postList.produceImg[8]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg10" value="${postList.produceImg[8]}"/>
	                <input style="position:relative; top:-200px; left:650px;" type="file" name="produceImg10" onchange="setThumbnail9(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail9(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg10");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg10");
				          document.querySelector(".image-preview10").appendChild(img);
				        };
				        reader.readAsDataURL(event.target.files[0]);
				      }
				    </script>
                </div>
             	 <c:if test="${empty postList.content[9]}">
                <script>
                	document.querySelector(".cookstep9").classList.remove('on');
              	</script>
              	</c:if>
             
                
                <div class="cookstep10 on">
                    <input class="step" name="content11" type="text"  value="${postList.content[10]}">
                    <input class="minusstep10" type="button" value="-">
                    <div class="poststepimg">
                      <div id="preimg" class="image-preview11"><img id="preimg11" src="img/${postList.produceImg[9]}" alt="순서사진"/></div>
	                </div>
	                <input type="hidden" name="nonproduceImg11" value="${postList.produceImg[9]}"/>
	                <input style="position:relative; top:-200px; left:650px;" type="file" name="produceImg11" onchange="setThumbnail10(event);" id="daio" accept="image/*">
	                <script>
				      function setThumbnail10(event) {
				        var reader = new FileReader();
				
				        reader.onload = function(event) {
				        	 const preimg = document.getElementById("preimg11");
						      preimg.remove();
					          var img = document.createElement("img");
					          img.setAttribute("src", event.target.result);
					          img.setAttribute("id", "preimg11");
				          document.querySelector(".image-preview11").appendChild(img);
				        };
				        reader.readAsDataURL(event.target.files[0]);
				      }
				    </script>
                </div>
                <c:if test="${empty postList.content[10]}">
              	<script>
              		document.querySelector(".cookstep10").classList.remove('on');
              	</script>
              	</c:if>
              	
                <div class="postinsertbtn">
                    <div>
                        <input type="submit" 
                            class="postbtn posting rounded-lg border border-warning" value="등록">
                        <a href="main.do"><input type="button" 
                            class="postbtn posting rounded-lg border border-warning" value="취소"></a>
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