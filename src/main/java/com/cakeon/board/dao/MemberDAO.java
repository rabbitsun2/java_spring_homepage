package com.cakeon.board.dao;

import com.cakeon.board.model.MemberDTO;

public interface MemberDAO {

	public MemberDTO selectMember(MemberDTO member);
	
}