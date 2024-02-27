package com.thousand.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thousand.dto.CategoryDTO;
import com.thousand.dto.LikepostDTO;
import com.thousand.dto.MemberDTO;
import com.thousand.dto.PostDTO;

import util.DBManager;

public class ThousandDAO {

	private ThousandDAO() {
	}

	private static ThousandDAO instance = new ThousandDAO();

	public static ThousandDAO getInstance() {
		return instance;
	}

	/* 포스팅 ***********************************************/
	// 전체글 수 조회
	public int selectCount() {
		int totalCount=0;
		String sql = "select count(*) from post";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return totalCount;
	}
	
	// 해당아이디 글 조회
		public int selectCount(String id) {
			int totalCount=0;
			String sql = "select count(*) from post where id=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totalCount = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return totalCount;
		}
	
	// 전체글 불러오기
	public List<PostDTO> selectPostsAll(Map<String,Object> map) {
		String sql = "select *  from "
				+" ( "
				+" select t.*, rownum rnum from( "
				+"	select * from post ";
		if(map.get("searchWord") != null) {
			sql += "where " + map.get("searchField")
			+ " like '%" + map.get("searchWord") + "%' ";
		}
		sql += "		order by pno desc "
				+" 		) t"
				+"   where rownum <= ?) "
				+"where rnum >=?";


		String[] content = new String[11];
		String[] produceImg = new String[10];
		List<PostDTO> list = new ArrayList<PostDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("end").toString());
			pstmt.setString(2, map.get("start").toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDTO pDto = new PostDTO();
				pDto.setPno(rs.getInt("pno"));
				pDto.setId(rs.getString("id"));
				pDto.setTitle(rs.getString("title"));
				pDto.setSummary(rs.getString("summary"));
				pDto.setCategorycode(rs.getInt("categorycode"));
				pDto.setMainimg(rs.getString("mainimg"));
				pDto.setReadcount(rs.getInt("readcount"));
				// 바로 집어넣을수 없어서 임시 배열에 값 넣기
				for (int i = 0; i < 11; i++) {
					content[i] = rs.getString("content" + Integer.toString(i+1));
				}
				for (int i = 0; i < 10; i++) {
					produceImg[i] = rs.getString("produceImg" + Integer.toString(i+2));
				}
				// 임시배열값으로 세팅하기
				pDto.setContent(content);
				pDto.setProduceImg(produceImg);
				pDto.setRnum(rs.getInt("rnum"));
				list.add(pDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	// 내가 작성한글 조회
	public List<PostDTO> selectMyPost(String id, Map<String,Object> map) {
		// 작성자 id 값으로 작성한 글 불러오기, post 테이블 member 테이블 조인해서 작성자 닉네임 값 불러오기
		String sql = "select *  from "+
				"( select t.*, rownum rnum from( "+ 
				" select * from post where id=? order by pno desc ) t where rownum <= ?) " + 
				" where rnum >=?";
		// 조회된 post 넣어줄 배열생성
		String[] content = new String[11];
		String[] produceImg = new String[10];
		List<PostDTO> list = new ArrayList<PostDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, map.get("end").toString());
			pstmt.setString(3, map.get("start").toString());
			rs = pstmt.executeQuery();
			// 작성한 글이 없을 때 까지 조회
			while (rs.next()) {
				PostDTO pDto = new PostDTO();
				pDto.setPno(rs.getInt("pno"));
				pDto.setId(rs.getString("id"));
				pDto.setTitle(rs.getString("title"));
				pDto.setSummary(rs.getString("summary"));
				pDto.setCategorycode(rs.getInt("categorycode"));
				pDto.setMainimg(rs.getString("mainimg"));
				pDto.setReadcount(rs.getInt("readcount"));
				// 바로 집어넣을수 없어서 임시 배열에 값 넣기
				for (int i = 0; i < 11; i++) {
					content[i] = rs.getString("content" + Integer.toString(i+1));
				}
				for (int i = 0; i < 10; i++) {
					produceImg[i] = rs.getString("produceImg" + Integer.toString(i+2));
				}
				// 임시배열값으로 세팅하기
				pDto.setContent(content);
				pDto.setProduceImg(produceImg);
				pDto.setRnum(rs.getInt("rnum"));
				list.add(pDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}


	// 글 작성
	public void insertPost(PostDTO pDTO) {
		// 받아온 데이터 게시글 추가하기
		String sql = "insert into post(pno, id, title,summary,categorycode,mainimg"
				+ ",readcount,content1,content2,content3,content4,content5,content6,content7,"
				+ " content8, content9, content10, content11, produceimg2, produceimg3, produceimg4"
				+ ", produceimg5, produceimg6, produceimg7, produceimg8, produceimg9, produceimg10, produceimg11)"
				+ " values(post_seq.nextval,?,?,?,?,?,0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pDTO.getId());
			pstmt.setString(2, pDTO.getTitle());
			pstmt.setString(3, pDTO.getSummary());
			pstmt.setInt(4, pDTO.getCategorycode());
			pstmt.setString(5, pDTO.getMainimg());
			for (int i = 0; i < 11; i++) {
				// 컨텐츠 내용이 존재할 시에는 정보 넣고 아니면 null값 넣기
				if (pDTO.getContent()[i] != null) {
					pstmt.setString(i + 6, pDTO.getContent()[i]);
				} else {
					pstmt.setString(i + 6, null);
				}
				if (i == 10) {// index가 11일때 밑에 img코드는 생략
					break;
				}
				// 사진 내용이 존재할 시에는 정보 넣고 아니면 null값 넣기
				if (pDTO.getProduceImg()[i] != null) {
					pstmt.setString(i + 17, pDTO.getProduceImg()[i]);
				} else {
					pstmt.setString(i + 17, null);
				}
			}
			pstmt.executeUpdate();
			// 입력한 후에 바로 입력한 카테고리 코드 확인해서 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 카테고리로 해당 글번호 조회하기
	public int selectInsertingPost(int categorycode) {
		int pno = 0;
		String sql = "select pno from post where categorycode=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categorycode);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 받아온 자료가 있을 시
				pno = rs.getInt(1); // 첫번째 값만 받아오면 됨. 내림차순이기에 최신글은 가장 높은 숫자.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pno;
	}

	// 글 조회 (글번호로 해당 글 조회)
	public PostDTO selectOnePost(int pno) {
		// 리턴 해줄 dto 생성
		PostDTO pdto = new PostDTO();
		// 글번호로 글 가져올 쿼리문
		String sql = "select * from post where pno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// content랑 produceimg에 정보 넣기위한 배열
		String[] content = new String[11];
		String[] produceImg = new String[10];
		try {
			// db 연결 밑 ?값 셋팅
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 받아온 자료가 있을 시
				// 생성한 객체에 해당 자료 전부 집어넣기.
				pdto.setPno(rs.getInt("pno"));
				pdto.setId(rs.getString("id"));
				pdto.setTitle(rs.getString("title"));
				pdto.setSummary(rs.getString("summary"));
				pdto.setCategorycode(rs.getInt("categorycode"));
				pdto.setMainimg(rs.getString("mainimg"));
				pdto.setReadcount(rs.getInt("readcount"));
				// 바로 집어넣을수 없어서 임시 배열에 값 넣기
				for (int i = 0; i < 11; i++) {
					content[i] = rs.getString("content" + Integer.toString(i+1));
				}
				for (int i = 0; i < 10; i++) {
					produceImg[i] = rs.getString("produceImg" + Integer.toString(i+2));
				}
				// 임시배열값으로 세팅하기
				pdto.setContent(content);
				pdto.setProduceImg(produceImg);
				pdto.setPostdate(rs.getTimestamp("postdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pdto;
	}

	// 글 수정
	public void updatePost(PostDTO pDTO) {
		// 받아온 데이터 게시글 수정하기
		String sql = "update post set title=?, summary=?, categorycode=?, mainimg=?, "
				+ "content1=?, content2=?, content3=?, content4=?, content5=?, content6=?, "
				+ "content7=?, content8=?, content9=?, content10=?, content11=?, "
				+ "produceimg2 =?, produceimg3 =?, produceimg4 =?, produceimg5 =?, produceimg6 =?, "
				+ "produceimg7 =?, produceimg8 =?, produceimg9 =?, produceimg10 =?, produceimg11 =? " + "where pno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pDTO.getTitle());
			pstmt.setString(2, pDTO.getSummary());
			pstmt.setInt(3, pDTO.getCategorycode());
			pstmt.setString(4, pDTO.getMainimg());
			for (int i = 0; i < 11; i++) {
				// 컨텐츠 내용이 존재할 시에는 정보 넣고 아니면 null값 넣기
				if (pDTO.getContent()[i] != null) {
					pstmt.setString(i + 5, pDTO.getContent()[i]);
				} else {
					pstmt.setString(i + 5, null);
				}
				if (i == 10) {// index가 11일때 밑에 img코드는 생략
					break;
				}
				// 사진 내용이 존재할 시에는 정보 넣고 아니면 null값 넣기
				if (pDTO.getProduceImg()[i] != null) {
					pstmt.setString(i + 16, pDTO.getProduceImg()[i]);
				} else {
					pstmt.setString(i + 16, null);
				}
			}
			pstmt.setInt(26, pDTO.getPno());
			pstmt.executeUpdate();
			// 입력한 후에 바로 입력한 카테고리 코드 확인해서 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 글 삭제
	public void deletePost(int pno) {
		String sql = "delete post where pno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			// 삭제 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	// 글 삭제
		public void deletePost(String id) {
			String sql = "delete post where id=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				// 삭제 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}


	public void plusReadCount(int pno) {
		String sql = "update post set readcount=readcount+1 where pno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			// 조회수 +1 기록
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 좋아요 조회수 top 50 조회
	public List<PostDTO> popularPost(String likeit) {
		List<PostDTO> popularList = new ArrayList<PostDTO>();
		String sql = "select * from post order by ? desc limit 50 ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, likeit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostDTO pDto = new PostDTO();
				pDto.setPno(rs.getInt("pno"));
				pDto.setMainimg(rs.getString("mainimg"));
				pDto.setTitle(rs.getString("title"));
				pDto.setSummary(rs.getString("summary"));
				pDto.setId(rs.getString("nickname"));
				pDto.setReadcount(rs.getInt("readcount"));
				popularList.add(pDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return popularList;
	}

	/* 포스팅.end ***********************************************/

	/* 멤버 ***********************************************/
	// 회원 가입
	// 회원 가입
	public int createMember(MemberDTO mDTO) {
		int result = -1;
		String sql = "insert into member(id,pw,email,nickname,joindate) values(?,?,?,?,sysdate)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDTO.getId());
			pstmt.setString(2, mDTO.getPw());
			pstmt.setString(3, mDTO.getEmail());
			pstmt.setString(4, mDTO.getNickname());
			result = pstmt.executeUpdate();// 영향을 받은 행의 수 리턴.insert하면 1행이 추가되므로 1을 리턴.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 회원 조회
	public int selectMember(String id, String pw) {
		int result = -1; // result 기본값 -1
		String sql = " select * from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// db연결
			conn = DBManager.getConnection();
			// 동적 쿼리 생성
			pstmt = conn.prepareStatement(sql);
			// 동적쿼리에 id값 세팅
			pstmt.setString(1, id);
			// 쿼리문 실행
			rs = pstmt.executeQuery();
			// id 값 존재하면
			if (rs.next()) {
				// 비밀번호 값이 null이 아니다 그리고 입력한 pw워드가 맞다면
				if (rs.getString("pw") != null && rs.getString("pw").equals(pw)) {
					result = 1;
					// 비밀번호가 틀리면
				} else {
					result = 0;
				}
				// id가 존재하지 않으면
			} else {
				result = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		// 1:pw일치, 0:pw불일치, -1:id없음
		return result;
	}


	// 회원 정보 수정
	public int updateMember(MemberDTO mDTO) {
		int result = -1;
		String sql = "update member set pw=?,email=?,nickname=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDTO.getPw());
			pstmt.setString(2, mDTO.getEmail());
			pstmt.setString(3, mDTO.getNickname());
			pstmt.setString(4, mDTO.getId());
			result = pstmt.executeUpdate();// 영향을 받은 행의 수 리턴.update하면 1행이 변경되므로 1을 리턴.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	// 회원 탈퇴
	public void deleteMember(MemberDTO mDTO) {
		String sql = "delete from member where id = ?";      //db에서 id를 기준으로 삭제 할 member테이블 데이터 찾기
		//이것만 지우고 나머지 likepost, post 테이블의 데이터는 해당 db에 테이블 - 제약조건 - 삭제시 종속삭제 설정하면 삭제 됨
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDTO.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	// 회원 탈퇴
	public void deleteMember(String id) {
		String sql = "delete from member where id = ?";      //db에서 id를 기준으로 삭제 할 member테이블 데이터 찾기
		//이것만 지우고 나머지 likepost, post 테이블의 데이터는 해당 db에 테이블 - 제약조건 - 삭제시 종속삭제 설정하면 삭제 됨
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// id 중복 체크
	public int confirmId(String id) {
		int result = -1;
		String sql = "select id from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // id가 있는 경우
				result = 1;
			} else { // id가 없는 경우
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// pw 중복체크
	public void confirmPw(MemberDTO mDTO) {
	}

	// 마이페이지 접근 시, pw 확인
	public int checkPw(String id, String pw) {
		int result = -1; // 결과값 초기화: 실패로 설정
		String sql = "select * from member where id=? and pw=?"; // SQL 쿼리문: 입력받은 비밀번호와 일치하는 레코드를 회원 테이블에서 찾기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection(); // db연결
			pstmt = conn.prepareStatement(sql); // 동적쿼리생성
			pstmt.setString(1, id); // 동적 쿼리에 비밀번호 매개변수 설정
			pstmt.setString(2, pw); // 동적 쿼리에 비밀번호 매개변수 설정
			rs = pstmt.executeQuery(); // 쿼리 실행 및 결과셋 획득
			if (rs.next()) { // 비밀번호 성공실패
				result = 1; // 성공은 1
			} else { // 실패는 -1
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	// 비밀번호 찾기
	public String searchPw(String id, String nickname) {
		String password =null;
		String sql = "select pw from member where id=? and nickname=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				password=rs.getString("pw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return password;
	}

	// id 찾기 ///////////
	public String searchId(String nickname) {
		String id = null;
		String sql = "select id from member where nickname=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id=rs.getString("id");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return id;
	}

	// 회원 정보
	public MemberDTO getMember(String id) {
		MemberDTO mDto = null;
		String sql = "select * from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mDto = new MemberDTO();
				mDto.setId(rs.getString("id"));
				mDto.setPw(rs.getString("pw"));
				mDto.setEmail(rs.getString("email"));
				mDto.setNickname(rs.getString("nickname"));
				mDto.setJoindate(rs.getTimestamp("joindate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mDto;
	}

	// nickname 중복 체크
	public int confirmNickname(String nickname) {
		int result = -1;
		String sql = "select nickname from member where nickname=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) { // nickname이 있는 경우
				result = 1;
			} else { // nickname이 없는 경우
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/* 멤버.end ***********************************************/

	/* 카테고리 ***********************************************/
	// 카테고리 가져오기
	public CategoryDTO selectCategory(int categorycode) {
		CategoryDTO cDto =new CategoryDTO();
		String sql = "select * from category where categorycode=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categorycode);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 받아온 자료가 있을 시
				cDto.setCategorycode(categorycode);
				cDto.setRecipe(rs.getString("recipe"));
				cDto.setLocal(rs.getString("local"));
				cDto.setItem(rs.getString("item"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cDto;
	}
	// 카테고리 입력
	public void insertCategory(CategoryDTO cDTO) {
		// 분류 3가지 집어넣기
		String sql = "insert into category values(category_seq.nextval,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cDTO.getRecipe());
			pstmt.setString(2, cDTO.getLocal());
			pstmt.setString(3, cDTO.getItem());
			pstmt.executeUpdate();
			// 입력한 후에 바로 입력한 카테고리 코드 확인해서 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 카테고리 번호 가져오기(게시글 입력시 마지막 번호 리턴해주기)
	public int choiceCategoryCode() {
		int categoryCode = -1; // 반환할 카테고리코드 확인
		String sql = "select categorycode from category order by categorycode desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 받아온 자료가 있을 시
				categoryCode = rs.getInt(1); // 첫번째 값만 받아오면 됨. 내림차순이기에 최신글은 가장 높은 숫자.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return categoryCode;
	}

	// 카테고리 수정
	public void updateCategory(CategoryDTO cDTO) {
		// 분류 3가지 수정 정보 넣어주기
		String sql = "update category set recipe=?,local=?,item=? where categorycode=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cDTO.getRecipe());
			pstmt.setString(2, cDTO.getLocal());
			pstmt.setString(3, cDTO.getItem());
			pstmt.setInt(4, cDTO.getCategorycode());
			pstmt.executeUpdate();
			// 입력한 후에 바로 입력한 카테고리 코드 확인해서 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	/* 카테고리.end ***********************************************/

	/* 좋아요 ***********************************************/
	// 좋아요 눌렀는지 체크 함수
	public int checkLike(String id, int pno) {
		int check = 0;
		String sql = "select likeit from likepost where id =? && and pno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, pno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 좋아요를 눌렀으면
				if (rs.getInt("likeit") == 1) {
					check = 1;
					// 좋아요를 취소 했으면
				} else if (rs.getInt("likeit") == 0)
					check = 0;
				// 좋아요를 누른적이 없으면
			} else {
				check = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return check; // 좋아요 1 , 좋아요 취소 0 , 누른적없음 -1
	}

	// 좋아요 조회 -> counting 하기
	public int selectLike(int pno) {
		int likeit = 0;
		String sql = "select count(likeit) from likepost where pno=? and likeit==1 ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 첫번쨰 컬럼 (count된 좋아요수) likeit에 저장
				likeit = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return likeit;
	}

	// 좋아요 정보 넣기
	public void insertLike() {

	}

	// 좋아요 수정
	public void updateLike(LikepostDTO lpDTO) {
	}
	/* 좋아요.end ***********************************************/

	/* 당일 조회수 ***********************************************/
	// 당일 조회수 기록하기
	public void insertTodayReadcount() {
	}

	// 당일 조회수 불러오기
	public void selectTodayReadcount() {
	}
	/* 당일 조회수.end ***********************************************/

	/* 최근 본 목록 *******************************************/
	public class RecentlyViewList {

		public String[] posts;
		public int maxSize; // 최대 항목 개수. 4로 할 예정
		public int currentView; // 현재 인덱스

		public RecentlyViewList(int maxSize) {
			this.posts = new String[maxSize];
			this.maxSize = maxSize;
			this.currentView = 0;
		}

		public void addPost(String post) {
			if (currentView < maxSize) { // currentView가 maxSize보다 작으면
				posts[currentView] = post; // posts의 currentView 인덱스에 post를 추가
				currentView++; // currentView 1 증가
			} else { // currentView가 maxSize보다 크거나 같으면
				for (int i = 0; i < maxSize - 1; i++) {
					posts[i] = posts[i + 1]; // 오른쪽 게시물을 왼쪽으로 한칸씩 이동
				}
				posts[maxSize - 1] = post; // 새 게시물을 배열의 마지막 칸에 넣음
			}
		}
	}

	private RecentlyViewList recentlyViewList;

	public RecentlyViewList getRecentlyViewList() {
		return recentlyViewList;
	}

	/* 최근 본 목록. 미완성 *******************************************/

	/* 오늘의 검색 *******************************************/
	public List<PostDTO> todayTopSearch() {
		String sql = "select  * " + "from post inner join member" + " on post.id= member.id " + "ORDER BY readcount DESC "
				+ "WHERE ROWNUM <= 10";
		String[] content = new String[11];
		String[] produceImg = new String[10];
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PostDTO> todayTopSearch = new ArrayList<PostDTO>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO pDto = new PostDTO();
				pDto.setPno(rs.getInt("pno"));
				pDto.setId(rs.getString("id"));
				pDto.setTitle(rs.getString("title"));
				pDto.setSummary(rs.getString("summary"));
				pDto.setCategorycode(rs.getInt("category"));
				pDto.setMainimg(rs.getString("mainimg"));
				pDto.setReadcount(rs.getInt("readcount"));
				// 바로 집어넣을수 없어서 임시 배열에 값 넣기
				for (int i = 0; i < 11; i++) {
					content[i] = rs.getString("content" + Integer.toString(i));
				}
				for (int i = 0; i < 10; i++) {
					produceImg[i] = rs.getString("produceImg" + Integer.toString(i));
				}
				// 임시배열값으로 세팅하기
				pDto.setContent(content);
				pDto.setProduceImg(produceImg);
				todayTopSearch.add(pDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return todayTopSearch;
	
	}
	/* 오늘의 검색.end *******************************************/

	/* 메뉴별 추천 *******************************************/
	public List<PostDTO> menuRecommand() {
		String sql = "SELECT" + "FROM (" + "  SELECT post.*, likepost.likeit,"
				+ "  ROW_NUMBER() OVER (PARTITION BY post.categorycode ORDER BY likepost.likeit DESC) as rn"
				+ "  FROM post INNER JOIN likepost" + "  ON post.pno = likepost.pno" + ")" + "WHERE rn <= 10";

		String[] content = new String[11];
		String[] produceImg = new String[10];
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PostDTO> menuRecommand = new ArrayList<PostDTO>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO pDto = new PostDTO();
				pDto.setPno(rs.getInt("pno"));
				pDto.setId(rs.getString("id"));
				pDto.setTitle(rs.getString("title"));
				pDto.setSummary(rs.getString("summary"));
				pDto.setCategorycode(rs.getInt("category"));
				pDto.setMainimg(rs.getString("mainimg"));
				pDto.setReadcount(rs.getInt("readcount"));
				// 바로 집어넣을수 없어서 임시 배열에 값 넣기
				for (int i = 0; i < 11; i++) {
					content[i] = rs.getString("content" + Integer.toString(i));
				}
				for (int i = 0; i < 10; i++) {
					produceImg[i] = rs.getString("produceImg" + Integer.toString(i));
				}
				// 임시배열값으로 세팅하기
				pDto.setContent(content);
				pDto.setProduceImg(produceImg);
				menuRecommand.add(pDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return menuRecommand;
	}
	/* 메뉴별 추천.end *******************************************/

}
