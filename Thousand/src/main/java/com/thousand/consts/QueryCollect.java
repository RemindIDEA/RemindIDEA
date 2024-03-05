package com.thousand.consts;

public class QueryCollect {
	public static final String SELECT_CATEGORY = "select * from category where categorycode=?";
	public static final String INSERT_CATEGORY = "insert into category values(category_seq.nextval,?,?,?)";
	public static final String UPDATE_CATEGORY = "update category set recipe=?,local=?,item=? where categorycode=?";
	public static final String DELETE_CATEGORY = "delete category where categorycode=?";
	public static final String CHOICE_CATEGORYCODE = "insert into category values(category_seq.nextval,?,?,?)";
}
