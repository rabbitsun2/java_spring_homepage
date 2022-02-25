package com.cakeon.board.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class DbmsDAOImpl implements DbmsDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.cakeon.mapper.DbmsMapper";
	
	@Override
	public boolean isTableName(String tablename) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tablename", tablename.toUpperCase());
		
		int sw = sqlSession.selectOne(ns + ".IsSelectTableName", paramMap);
		
		switch(sw)
		{
			case 1:
				return true;
			
			default:
				return false;
		}
		
	}
	
}
