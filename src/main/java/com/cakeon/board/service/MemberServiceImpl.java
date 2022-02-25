package com.cakeon.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cakeon.board.dao.MemberDAO;
import com.cakeon.board.model.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public MemberDTO selectMember(MemberDTO member) {
		
		return dao.selectMember(member);
	}
	
}
