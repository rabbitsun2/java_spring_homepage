package com.cakeon.board.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cakeon.board.model.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.cakeon.mapper.MemberMapper";
	
	@Override
	public MemberDTO selectMember(MemberDTO member) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
				
		paramMap.put("email", member.getEmail() );
		paramMap.put("passwd", member.getPasswd() );
		
		return sqlSession.selectOne( ns + ".selectMember", paramMap);
		
	}
	
}
