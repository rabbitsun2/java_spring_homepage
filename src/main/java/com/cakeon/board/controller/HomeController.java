package com.cakeon.board.controller;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cakeon.board.dao.BoardDAO;
import com.cakeon.board.dao.BoardDAOImpl;
import com.cakeon.board.model.BoardDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
    private DataSource dataSource; 
	
	@Inject
	private BoardDAO dao;
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public String sample(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );

        if ( dataSource != null ) {
             logger.info("참1");
             
        }else {
        	logger.info("거짓1");
        }

		try(
				Connection con = dataSource.getConnection();
				SqlSession session = sqlSessionFactory.openSession();
		){
			
			logger.info("con=" + con);
			logger.info("session=" + session);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

        if ( dao != null ) {
             logger.info("참2");
             logger.info(dao.getTime());
             
             List<BoardDTO> dto = dao.selectAllBoard("hello");
             
             for (BoardDTO boardDTO:dto) {
            	 
            	 logger.info(String.valueOf( boardDTO.getId()) );
            	 logger.info( boardDTO.getSubject() );
            	 
             }
             
             //logger.info( String.valueOf( dao.selectAllCount("hello") ) );
             
             
        }else {
        	logger.info("거짓2");
        }
		return "home";
	}
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
	
		return "website/index";
	}
		
}
