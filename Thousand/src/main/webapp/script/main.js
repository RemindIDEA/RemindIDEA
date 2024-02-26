//id,pw validation
function loginCheck(){
   if(document.frm.id.value.length==0){
      alert("id를 입력하세요");
      frm.id.focus(); // 포커스
      return false;
   }
   if(document.frm.pw.value==""){
      alert("pw를 입력하세요");
      frm.pw.focus(); // 포커스
      return false;
   }
}

//id중복체크창
function idCheck(){
   if(document.frm.id.value==""){
      alert("id를 입력해 주세요");
      document.form.id.focus();
      return;
   }
   var url="idcheck.do?id="+document.frm.id.value;
   window.open(url,"blank1","resizable=no,width=450,height=200");
}

function nicknameCheck(){
   if(document.frm.nickname.value==""){
      alert("nickname를 입력해 주세요");
      document.form.nickname.focus();
      return;
   }
   var url="checkNickname.do?nickname="+document.frm.nickname.value;
   window.open(url,"blank1","resizable=no,width=450,height=200");
}

//사용가능한id선택
function idok(id){
  	opener.frm.id.value=document.frm.id.value;
	window.close(); 
}
//사용가능한 nickname선택
function nicknameok(nickname){
  	opener.frm.nickname.value=document.frm.nickname.value;
	window.close(); 
}

//회원가입정보 validation
function joinCheck(){
   if(document.frm.id.value.length==0){
      alert("id를 입력하세요");
      frm.id.focus();
      return false;
   }
   if(document.frm.id.value.length<3){
      alert("아이디는 3글자이상이어야 합니다.");
      frm.id.select(); //수정시 select()
      return false;
   }   
   if(document.frm.pw.value==""){
      alert("비밀번호를 입력하세요");
      frm.pw.focus();
      return false;
   }
   if(document.frm.pw.value!=document.frm.pw_check.value){
      alert("비밀번호가 일치하지 않습니다.");
      frm.pw_check.select(); //수정시 select()
      return false;
   }
   if(document.frm.id.value!=document.frm.reid.value){
      alert("중복 확인 해주세요");
      frm.id.focus();
      return false;
   }
   if(document.frm.email.value==""){
      alert("email을 입력해주세요");
      frm.email.focus();
      return false;
   }
   if(document.frm.nikname.value==""){
      alert("닉네임을 입력하세요");
      frm.nickname.focus();
      return false;
   }
}

function deletecheckpw(url){
	var result = confirm("정말로 삭제하시겠습니까?");
	if(result){
		alert("삭제되었습니다.");
		location=url;
	}else{
		alert("취소되었습니다.");
		return false;
	}
	
}
function deleteInform(url){
	var result = confirm("정말로 삭제하시겠습니까?");
	if(result){
		alert("삭제되었습니다.");
		location=url;
	}else{
		alert("취소되었습니다.");
		return false;
	}
	
}

let count=0;
// 혹시 포스팅 할 떄 알림창 필요할까 해서 넣어둠
function postCheck(){
   if(document.frm.title.value==""){
      alert("제목을 입력하세요");
      document.frm.title.focus(); // focus 주기
      return false;
   }
   if(document.frm.summary.value==""){
      alert("부 제목을 입력하세요");
      document.frm.summary.focus(); // focus 주기
      return false;
   }
   if(document.frm.mainimg.value==""){
      alert("대표사진을 넣어주세요");
      return false;
   }
   // 콘텐츠 사진이 0개 일때 사진을 넣어주세요 몇개 이상알림
   // 콘텐츠 내용이 0개 일때 내용을 입력해주세요 몇개 이상 알림 코드...?/
   
   //  ㅠㅠ 머지..
   // document.frm.content[].length <2 //  땡
   
   // let count();  > 조리순서가 채워지면 카운트 추가
   // 그래서 카운트가 2개 이상이 된다면 ok 라는 로직
   if(document.frm.content.value==""){
      alert("조리순서를 2개 이상 넣어주세요");
      document.frm.content.focus(); // focus 주기
      return false;
   }
   if(document.frm.produceImg.value==""){
      alert("조리순서 사진을 2개 이상 넣어주세요");
      return false;
   }
}
window.onload=function(){
	let button1 = document.querySelector(".plusstep1");
	button1.addEventListener("click",function(){
		document.querySelector(".cookstep2").classList.add('on');
	})
	let button2 = document.querySelector(".plusstep2");
	button2.addEventListener("click",function(){
		document.querySelector(".cookstep3").classList.add('on');
	})
	let button3 = document.querySelector(".minusstep2");
	button3.addEventListener("click",function(){
		document.querySelector(".cookstep2").classList.remove('on');
	})
	let button4 = document.querySelector(".plusstep3");
	button4.addEventListener("click",function(){
		document.querySelector(".cookstep4").classList.add('on');
	})
	let button5 = document.querySelector(".minusstep3");
	button5.addEventListener("click",function(){
		document.querySelector(".cookstep3").classList.remove('on');
	})
	let button6 = document.querySelector(".plusstep4");
	button6.addEventListener("click",function(){
		document.querySelector(".cookstep5").classList.add('on');
	})
	let button7 = document.querySelector(".minusstep4");
	button7.addEventListener("click",function(){
		document.querySelector(".cookstep4").classList.remove('on');
	})	
	let button8 = document.querySelector(".plusstep5");
	button8.addEventListener("click",function(){
		document.querySelector(".cookstep6").classList.add('on');
	})
	let button9 = document.querySelector(".minusstep5");
	button9.addEventListener("click",function(){
		document.querySelector(".cookstep5").classList.remove('on');
	})	
	let button10 = document.querySelector(".plusstep6");
	button10.addEventListener("click",function(){
		document.querySelector(".cookstep7").classList.add('on');
	})
	let button11= document.querySelector(".minusstep6");
	button11.addEventListener("click",function(){
		document.querySelector(".cookstep6").classList.remove('on');
	})	
	let button12 = document.querySelector(".plusstep7");
	button12.addEventListener("click",function(){
		document.querySelector(".cookstep8").classList.add('on');
	});
	let button13 = document.querySelector(".minusstep7");
	button13.addEventListener("click",function(){
		document.querySelector(".cookstep7").classList.remove('on');
	})	
	let button14 = document.querySelector(".plusstep8");
	button14.addEventListener("click",function(){
		document.querySelector(".cookstep9").classList.add('on');
	});
	let button15 = document.querySelector(".minusstep8");
	button15.addEventListener("click",function(){
		document.querySelector(".cookstep8").classList.remove('on');
	})
	let button16 = document.querySelector(".plusstep9");
	button16.addEventListener("click",function(){
		document.querySelector(".cookstep10").classList.add('on');
	});
	let button17 = document.querySelector(".minusstep9");
	button17.addEventListener("click",function(){
		document.querySelector(".cookstep9").classList.remove('on');
	})
	let button18 = document.querySelector(".minusstep10");
	button18.addEventListener("click",function(){
		document.querySelector(".cookstep10").classList.remove('on');
	})
}
	