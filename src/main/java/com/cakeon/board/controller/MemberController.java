package com.cakeon.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cakeon.board.model.BoardDTO;
import com.cakeon.board.model.BoardFileInfoDTO;
import com.cakeon.board.model.MemberDTO;
import com.cakeon.board.model.PageCriteria;
import com.cakeon.board.service.BoardService;
import com.cakeon.board.service.MemberService;
import com.cakeon.board.service.DbmsService;
import com.cakeon.board.util.Paging;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private BoardService service;
	
	@Inject
	private MemberService memberService;
	
	@Inject 
	private DbmsService dbmsService;
		
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, 
								   				Model model) {
		
		logger.info("Welcome board! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		
		return mav;
		
	}

	@RequestMapping(value = "login_ok", method = RequestMethod.POST)
	public ModelAndView login_ok(Locale locale, 
								   Model model,
								   MemberDTO dto,
								   HttpSession session) {

		boolean status = false;
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		MemberDTO res = memberService.selectMember(dto);
		
		
		// ?????? ????????? ?????? ?????? ???
		if ( res != null ) {
			status = true;
			logger.info("?????????:" + res.getEmail() + "/ ????????????:" + res.getPasswd() );
		}else {
			logger.info("?????? ????????? ?????? ??? ??????");
			mav.addObject("message", "??????????????? ?????? ??? ??????");
		}
		
		
		if ( status == true ) {

			// ?????? ??????
			session.setAttribute("res", res);
			mav.setViewName("redirect:/member/logon");
		}
		
		return mav;
		
	}
	

	@RequestMapping(value = "logon", method = RequestMethod.GET)
	public ModelAndView logon(Locale locale, 
								   Model model,
								   MemberDTO dto,
								   HttpSession session) {

		ModelAndView mav = new ModelAndView();
		
		logger.info( "??????:" + session.getAttribute("res") );
		
		if ( session.getAttribute("res") != null ) {
			logger.info("????????? ???????????? ??????");
			mav.setViewName("member/logon");
		}else{
			logger.info("????????? ???????????? ??????");
			mav.setViewName("redirect:login");
		}
		
		return mav;
		
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(Locale locale, 
								   Model model,
								   MemberDTO dto,
								   HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		
		HttpSession session = req.getSession();		
		session.invalidate();	// ?????? ?????????
		
		mav.setViewName("redirect:/");	// ????????? ????????????
		
		return mav;
		
	}
}
