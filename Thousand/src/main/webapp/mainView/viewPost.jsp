<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>포스팅 상세보기</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	 <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="script/main.js"></script>

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
	

	<div id="viewcontent">
		<div class="viewtitlecontent">
			<div class="viewmainimg">
				<img src="img/${postList.mainimg}" />
			</div>
			<div class="viewnickname">
				<div>
					<div style="width: 500px; height: 45px; text-align: center;">
						<h4>${postList.id}</h4>
					</div>
				</div>
			</div>
			<div class="viewtitle">
				<div>
					<div style="width: 500px; height: 45px; text-align: center;">
						<h2>${postList.title}</h2>
					</div>
				</div>
			</div>
			<div class="viewsummary">
				<div>
					<div style="width: 500px; height: 45px; text-align: center;">${postList.summary}</div>
				</div>
			</div>

			<div class="viewcategory form-inline">
				<div class="form-inline container-fluid">
					<table class="table ">
						<thead>
							<tr>
								<th>조리법별</th>
								<th>지역별</th>
								<th>재료별</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${category.recipe}</td>
								<td>${category.local}</td>
								<td>${category.item}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="viewmaincontent">
			<div class="viewcookitem">
				<div>
					<div class="viewstep">
						<!--  step 순서 아이콘-->
						<button class="viewstepbtn rounded-lg border border-warning">
							<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
								fill="orange" class="bi bi-1-square" viewBox="0 0 16 16">
                                <path
									d="M9.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383z" />
                                <path
									d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
						</button>
						<p class="viewtext">${postList.content[0] }</p>

					</div>

					<div class="viewstep">
						<button class="viewstepbtn rounded-lg border border-warning">
							<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
								fill="orange" class="bi bi-2-square" viewBox="0 0 16 16">
                                <path
									d="M6.646 6.24v.07H5.375v-.064c0-1.213.879-2.402 2.637-2.402 1.582 0 2.613.949 2.613 2.215 0 1.002-.6 1.667-1.287 2.43l-.096.107-1.974 2.22v.077h3.498V12H5.422v-.832l2.97-3.293c.434-.475.903-1.008.903-1.705 0-.744-.557-1.236-1.313-1.236-.843 0-1.336.615-1.336 1.306" />
                                <path
									d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
						</button>
						<p class="viewtext">${postList.content[1] }</p>
						<div class="stepimg">
							<img src="img/${postList.produceImg[0]}" alt="그림" />
						</div>
					</div>
					<c:if test="${not empty postList.content[2]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-3-square" viewBox="0 0 16 16">
                                <path
										d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318" />
                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
							</button>
							<p class="viewtext">${postList.content[2] }</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[1]}" alt="그림" />
							</div>
						</div>
					</c:if>

					<c:if test="${not empty postList.content[3]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-4-square" viewBox="0 0 16 16">
                                <path
										d="M7.519 5.057q.33-.527.657-1.055h1.933v5.332h1.008v1.107H10.11V12H8.85v-1.559H4.978V9.322c.77-1.427 1.656-2.847 2.542-4.265ZM6.225 9.281v.053H8.85V5.063h-.065c-.867 1.33-1.787 2.806-2.56 4.218" />
                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
							</button>
							<p class="viewtext">${postList.content[3]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[2]}" alt="그림" />
							</div>
						</div>
					</c:if>

					<c:if test="${not empty postList.content[4]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-5-square" viewBox="0 0 16 16">
                                <path
										d="M7.994 12.158c-1.57 0-2.654-.902-2.719-2.115h1.237c.14.72.832 1.031 1.529 1.031.791 0 1.57-.597 1.57-1.681 0-.967-.732-1.57-1.582-1.57-.767 0-1.242.45-1.435.808H5.445L5.791 4h4.705v1.103H6.875l-.193 2.343h.064c.17-.258.715-.68 1.611-.68 1.383 0 2.561.944 2.561 2.585 0 1.687-1.184 2.806-2.924 2.806Z" />
                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
							</button>
							<p class="viewtext">${postList.content[4]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[3]}" alt="그림" />
							</div>
						</div>
					</c:if>

					<c:if test="${not empty postList.content[5]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-6-square" viewBox="0 0 16 16">
                                <path
										d="M8.21 3.855c1.612 0 2.515.99 2.573 1.899H9.494c-.1-.358-.51-.815-1.312-.815-1.078 0-1.817 1.09-1.805 3.036h.082c.229-.545.855-1.155 1.98-1.155 1.254 0 2.508.88 2.508 2.555 0 1.77-1.218 2.783-2.847 2.783-.932 0-1.84-.328-2.409-1.254-.369-.603-.597-1.459-.597-2.642 0-3.012 1.248-4.407 3.117-4.407Zm-.099 4.008c-.92 0-1.564.65-1.564 1.576 0 1.032.703 1.635 1.558 1.635.868 0 1.553-.533 1.553-1.629 0-1.06-.744-1.582-1.547-1.582" />
                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
							</button>
							<p class="viewtext">${postList.content[5]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[4]}" alt="그림" />
							</div>
						</div>
					</c:if>

					<c:if test="${not empty postList.content[6]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-7-square" viewBox="0 0 16 16">
                                <path
										d="M5.37 5.11V4.001h5.308V5.15L7.42 12H6.025l3.317-6.82v-.07H5.369Z" />
                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
							</button>
							<p class="viewtext">${postList.content[6]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[5]}" alt="그림" />
							</div>
						</div>
					</c:if>
					
					<c:if test="${not empty postList.content[7]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-8-square" viewBox="0 0 16 16">
                                <path
										d="M10.97 9.803c0 1.394-1.218 2.355-2.988 2.355-1.763 0-2.953-.955-2.953-2.344 0-1.271.95-1.851 1.647-2.003v-.065c-.621-.193-1.33-.738-1.33-1.781 0-1.225 1.09-2.121 2.66-2.121s2.654.896 2.654 2.12c0 1.061-.738 1.595-1.336 1.782v.065c.703.152 1.647.744 1.647 1.992Zm-4.347-3.71c0 .739.586 1.255 1.383 1.255s1.377-.516 1.377-1.254c0-.733-.58-1.23-1.377-1.23s-1.383.497-1.383 1.23Zm-.281 3.645c0 .838.72 1.412 1.664 1.412.943 0 1.658-.574 1.658-1.412 0-.843-.715-1.424-1.658-1.424-.944 0-1.664.58-1.664 1.424" />
                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
							</button>
							<p class="viewtext">${postList.content[7]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[6]}" alt="그림" />
							</div>
						</div>
					</c:if>
					
					<c:if test="${not empty postList.content[8]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-9-square" viewBox="0 0 16 16">
                                <path
										d="M7.777 12.146c-1.593 0-2.425-.89-2.52-1.798h1.296c.1.357.539.72 1.248.72 1.36 0 1.88-1.353 1.834-3.023h-.076c-.235.627-.873 1.184-1.934 1.184-1.395 0-2.566-.961-2.566-2.666 0-1.711 1.242-2.731 2.87-2.731 1.512 0 2.971.867 2.971 4.014 0 2.836-1.02 4.3-3.123 4.3m.118-3.972c.808 0 1.535-.528 1.535-1.594s-.668-1.676-1.56-1.676c-.838 0-1.517.616-1.517 1.659 0 1.072.708 1.61 1.54 1.61Z" />
                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
                            </svg>
							</button>
							<p class="viewtext">${postList.content[8]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[7]}" alt="그림" />
							</div>
						</div>
					</c:if>
					
					<c:if test="${not empty postList.content[9]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-10-square" viewBox="0 0 16 16">
	                                <path
										d="M7.988 12.158c-1.851 0-2.941-1.57-2.941-3.99V7.84c0-2.408 1.101-3.996 2.965-3.996 1.857 0 2.935 1.57 2.935 3.996v.328c0 2.408-1.101 3.99-2.959 3.99M8 4.951c-1.008 0-1.629 1.09-1.629 2.895v.31c0 1.81.627 2.895 1.629 2.895s1.623-1.09 1.623-2.895v-.31c0-1.8-.621-2.895-1.623-2.895" />
	                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
	                            </svg>
							</button>
							<p class="viewtext">${postList.content[9]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[8]}" alt="그림" />
							</div>
						</div>
					</c:if>
					
					<c:if test="${not empty postList.content[10]}">
						<div class="viewstep">
							<button class="viewstepbtn rounded-lg border border-warning">
								<svg xmlns="http://www.w3.org/2000/svg" width="70" height="70"
									fill="orange" class="bi bi-10-square" viewBox="0 0 16 16">
	                                <path
										d="M7.988 12.158c-1.851 0-2.941-1.57-2.941-3.99V7.84c0-2.408 1.101-3.996 2.965-3.996 1.857 0 2.935 1.57 2.935 3.996v.328c0 2.408-1.101 3.99-2.959 3.99M8 4.951c-1.008 0-1.629 1.09-1.629 2.895v.31c0 1.81.627 2.895 1.629 2.895s1.623-1.09 1.623-2.895v-.31c0-1.8-.621-2.895-1.623-2.895" />
	                                <path
										d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z" />
	                            </svg>
							</button>
							<p class="viewtext">${postList.content[10]}</p>
							<div class="stepimg">
								<img src="img/${postList.produceImg[9]}" alt="그림" />
							</div>
						</div>
					</c:if>
					
					<div class="viewmainimg2">
						<img src="img/${postList.mainimg}" />
					</div>


					<div class="viewinsertbtn">
						<div>
							<a href="updatePosting.do?pno=${postList.pno}">
							<input type="button" class="viewbtn posting rounded-lg border border-warning" value="수정"></a> 
							<a href="main.do"><input type="button" class="viewbtn posting rounded-lg border border-warning" value="목록"></a> 
							<input type="button" class="viewbtn posting rounded-lg border border-warning" onclick="deletecheckpw('postDelete.do?pno=${postList.pno}')" value="삭제">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="footer">
		<div class="footcontent ">
			<br>
			<p>서울시 관악구 남부순환로 1820,에그엘로우14층 전화번호 : 02-6020-0055 팩스번호 :
				02-3285-0012</p>
			<p>Copyright 2017 (주)천개의 레시피. ALL RIGHTS RESERVED.</p>
		</div>
	</div>
</body>

</html>