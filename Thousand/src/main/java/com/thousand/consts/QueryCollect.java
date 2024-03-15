package com.thousand.consts;

public class QueryCollect {
	public static final String SELECT_CATEGORY = "select * from category where categorycode=?";
	public static final String INSERT_CATEGORY = "insert into category values(category_seq.nextval,?,?,?)";
	public static final String UPDATE_CATEGORY = "update category set recipe=?,local=?,item=? where categorycode=?";
	public static final String DELETE_CATEGORY = "delete category where categorycode=?";
	public static final String CHOICE_CATEGORYCODE = "insert into category values(category_seq.nextval,?,?,?)";
	
	public static final String SELECT_POSTS_ALL1 = "select *  from ( select t.*, rownum rnum from ( select * from post ";
	public static final String SELECT_POSTS_ALL2 = "		order by pno desc  ) t  where rownum <= ?) where rnum >=?";
	public static final String SELECT_MY_POST = "select *  from ( select t.*, rownum rnum from(  select * from post where id=? order by pno desc ) t where rownum <= ?)  where rnum >=?";
	public static final String SELECT_ONE_POST = "select * from post where pno=?";
	public static final String PLUS_READCOUNT = "update post set readcount=readcount+1 where pno=?";
	public static final String CHECK_PNO_ID = "select * from post where pno=? and id=?";
	public static final String SELECT_COUNT = "select count(*) from post";
	public static final String SELECT_COUNT_ID = "select count(*) from post where id=?";
	public static final String INSERT_POST = "insert into post(pno, id, title,summary,categorycode,mainimg"
			+ ",readcount,content1,content2,content3,content4,content5,content6,content7,"
			+ " content8, content9, content10, content11, produceimg2, produceimg3, produceimg4"
			+ ", produceimg5, produceimg6, produceimg7, produceimg8, produceimg9, produceimg10, produceimg11)"
			+ " values(post_seq.nextval,?,?,?,?,?,0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_POST = "update post set title=?, summary=?, categorycode=?, mainimg=?, "
			+ "content1=?, content2=?, content3=?, content4=?, content5=?, content6=?, "
			+ "content7=?, content8=?, content9=?, content10=?, content11=?, "
			+ "produceimg2 =?, produceimg3 =?, produceimg4 =?, produceimg5 =?, produceimg6 =?, "
			+ "produceimg7 =?, produceimg8 =?, produceimg9 =?, produceimg10 =?, produceimg11 =? " + "where pno=?";
	public static final String DELETE_POST = "delete post where pno=?";
	public static final String DELETE_POST_ID = "delete post where id=?";
	public static final String SELECT_INSERTING_POST = "select pno from post where categorycode=?";

	
	//login
	public static final String SELECT_MEMBER = "select * from member where id=?";
	public static final String CREATE_MEMBER = "insert into member(id,pw,email,nickname,joindate) values(?,?,?,?,sysdate)";
	public static final String CONFIRM_ID = "select id from member where id=?";
	public static final String CONFIRM_NICKNAME = "select nickname from member where nickname=?";
	public static final String GET_MEMBER = "select * from member where id=?";
	public static final String UPDATE_MEMBER = "update member set pw=?,email=?,nickname=? where id=?";
	public static final String DELETE_MEMBER = "delete member where id = ?";

}
