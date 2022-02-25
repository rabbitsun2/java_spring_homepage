package com.cakeon.board;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.test.context.ContextConfiguration;

import com.cakeon.board.dao.BoardDAO;

import jdk.internal.org.jline.utils.Log;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class Test {
	
	@Inject
	private BoardDAO dao;
	
	@Inject
	private DataSource dataSource;

	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	@org.junit.jupiter.api.Test
	void test() {
		
		if ( dao != null ) {
			System.out.println("참1");
			System.out.println(dao.getTime());
		}
		else {
			System.out.println("거짓1");
		}
		fail("Not yet implemented");
	}
	
	@org.junit.jupiter.api.Test
	void test2() {
		
        if ( dataSource != null ) {
        	System.out.println("참2");
             
        }else {
        	System.out.println("거짓2");
        }
		
	}

	@org.junit.jupiter.api.Test
	void test3() {
		
		try(
				Connection con = dataSource.getConnection();
				SqlSession session = sqlSessionFactory.openSession();
			){
				
			System.out.println("con=" + con);
			System.out.println("session=" + session);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

}
