package com.cakeon.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.cakeon.board.dao.DbmsDAO;

@Service
public class DbmsServiceImpl implements DbmsService {

	@Inject
	private DbmsDAO dao;
	
	@Override
	public boolean isTableName(String tablename) {
		
		return dao.isTableName(tablename);
		
	}
	
}
