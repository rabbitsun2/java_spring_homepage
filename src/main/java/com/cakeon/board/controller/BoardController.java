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
import com.cakeon.board.model.PageCriteria;
import com.cakeon.board.service.BoardService;
import com.cakeon.board.service.DbmsService;
import com.cakeon.board.util.Paging;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@Inject 
	private DbmsService dbmsService;
	
	private final String realPath = "uploadFiles"; 
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "write/{boardname}", method = RequestMethod.GET)
	public ModelAndView write(Locale locale, 
								   Model model,
								   @PathVariable("boardname")String boardname) {
		
		logger.info("Welcome board! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/write");
		mav.addObject("boardname", boardname);
		
		return mav;
		
	}

	@RequestMapping(value = "write_ok", method = RequestMethod.POST)
	public ModelAndView write_ok(Locale locale, 
								   Model model, 
								   BoardDTO dto,
								   String boardname,
								   String passwd1,
								   String passwd2,
								   @RequestParam("multiFile") List<MultipartFile> multiFileList,
								   HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		
		Date curTime = new Date();
		List<Map<String, String>> fileList = null;
		boolean status = false;
		
		logger.info("Welcome board! The client locale is {}.", locale);
		logger.info("????????????1:" + passwd1 + "/ ????????????2:" + passwd2);
		logger.info("multiFileList(size):" + multiFileList.size() );
		
		// ?????? ?????????
		fileList = fileUpload(multiFileList, req);	// ?????? ?????????
		
		BoardDTO currentNode = null;
		
		// ?????? ????????? ???
		if ( fileList != null ) {
			status = true;			
		}
		
		// ??? ?????? ??????
		logger.info("????????????" + boardname);
		
		dto.setRegidate( curTime );
		service.insertBoard(boardname, dto);
		currentNode = service.selectArticle(boardname, dto);	// ?????? ??? ????????????
		
		logger.info("??? ????????????:" + currentNode.getId());
						
		// ?????? ?????? ??????
		if ( status == true ) {
			
			for (Map<String, String> curFileInfo  : fileList ) {

				BoardFileInfoDTO tmpFileInfo = new BoardFileInfoDTO();
				
				tmpFileInfo.setArticle_id( currentNode.getId() );
				tmpFileInfo.setSaveFolder( curFileInfo.get("saveFolder") );
				tmpFileInfo.setOriginFile( curFileInfo.get("originFile") );
				tmpFileInfo.setSaveFile( curFileInfo.get("saveFile") );
				tmpFileInfo.setExt( curFileInfo.get("ext") );
				tmpFileInfo.setIp("");
				tmpFileInfo.setRegidate( curTime );
				
				service.insertBoardFileInfo(boardname, tmpFileInfo);
				
			}
			
		}
			
		
		mav.setViewName("redirect:list/" + boardname);
		
		return mav;
		
	}
	
	private List<Map<String, String>> fileUpload(List<MultipartFile> multiFileList,
										 HttpServletRequest request) {
		
		//Map<Integer, Object>   
		
		// path ????????????
		String path = request.getSession().getServletContext().getRealPath("resources");
		String root = path + "/" + realPath;
		
		logger.info("RootDir(??????????????????):" + path );
		
		// ?????? ?????? ??????
		File filePath = new File(root);
		
		// ?????? ?????? ??????
		if ( !filePath.exists() ) {
			
			try {
				filePath.mkdir();
				logger.info("????????? ?????????????????????.");
			}
			catch(Exception e) {
				e.getStackTrace();
			}
			
		}else {
			logger.info("????????? ?????? ???????????? ????????????.");
		}
		
		
		List<Map<String, String>> fileList = new ArrayList<>();	// ???????????????
		

		// ?????? ?????????(??????)
		try {
			
			// HashMap ?????? ?????? ??????
			for(int i = 0; i < multiFileList.size(); i++) {
				
				// ?????? ?????? ????????????
				if (multiFileList.get(i).isEmpty() != true ){
				
					String originFile = multiFileList.get(i).getOriginalFilename();
					String ext = originFile.substring(originFile.lastIndexOf("."));
					String saveFile = UUID.randomUUID().toString() + ext;
					
					
					Map<String, String> map = new HashMap<>();
					map.put("saveFolder", realPath);
					map.put("originFile", originFile);
					map.put("saveFile", saveFile);
					map.put("ext", ext);
					
					
					fileList.add(map);
					
				}
			}
			
		}
		catch(Exception e) {

			logger.info("?????? ?????????");
			logger.info("??????:"+ e.getMessage());
		}
			
		String uploadPath = "";
		
		// ?????? ?????????(??????)
		try {
			
			for(int i = 0; i < multiFileList.size(); i++) {
				uploadPath = root + "/" + fileList.get(i).get("saveFile");
				File uploadFile = new File(uploadPath);
				multiFileList.get(i).transferTo(uploadFile);
			}
			
			logger.info("?????? ?????? ????????? ??????!");
			
		} catch (Exception e1) {
			logger.info("?????? ?????? ????????? ??????!");
			
			// ?????? ????????? ???????????? ?????? ??????
			try {
			
				for(int i = 0; i < multiFileList.size(); i++) {
					
					uploadPath = root + "\\" + fileList.get(i).get("saveFile");
					new File(uploadPath).delete();
					
				}
			
			}catch(Exception e2) {
				logger.info("????????? ?????? ?????? - ?????? ??????!");	
				logger.info("??????:"+ e2.getMessage());
			}
			
			fileList.clear();	// ?????? ????????? ?????????
			fileList = null;
			logger.info("??????:"+ e1.getMessage());
			
		}
		
		if ( fileList == null ) {
			logger.info("fileList == null" );
		}else {
			logger.info("fileList != null" + fileList.size());
		}
		
		return fileList;	
		
	}
	

	//HttpServletRequest req,
	//HttpServletResponse res,
	@RequestMapping(value = "list/{boardname}",
									method = RequestMethod.GET)
	public ModelAndView list(Locale locale, 
											  Model model,
											  PageCriteria cri,
											  @PathVariable("boardname")String boardname,
											  String keyword
			) {
		
		logger.info("Welcome board! The client locale is {}.", locale);
		
		boolean status = false;
		
		ModelAndView mav = new ModelAndView();
		Paging paging = new Paging();
		
		List<BoardDTO> boardList = null ;
		int total_count = -1;
		
		//logger.info("?????? ?????????:" + String.valueOf( cri.getPage() ));
		//logger.info("?????? ?????????:" + String.valueOf( cri.getPerPageNum() ));
		//logger.info("????????????:" + boardname );
		
		status = dbmsService.isTableName(boardname);
		//logger.info("????????? ??????:" + status);

		// ????????? ???????????? ????????? ???
		if ( status == false ) {
			mav.addObject("message", "failed");
			mav.setViewName("board/ajaxMessage");
			return mav;
		}
			
		// ????????? ??????
		if ( keyword != null ) {
			BoardDTO criDTO = new BoardDTO();
			criDTO.setSubject(keyword);
			total_count = service.selectAllSubjectCount(boardname, criDTO);
		}
		else {
			total_count = service.selectAllCount(boardname); 
		}
	
		
		//logger.info("??? ?????????:" + total_count );
		
		int page_no = cri.getPage();	// ?????? ?????????
		int page_size = cri.getPerPageNum();	// ?????? ?????????
		
		paging.setPageNo(page_no);
		paging.setPageSize(page_size);
		paging.setTotalCount(total_count);
		
		long startnum = paging.getDbStartNum();
		long endnum = paging.getDbEndNum();
		
		// ????????? ??????
		if ( keyword == null ) {
			boardList = service.selectPagingBoard(boardname, (int)startnum, (int)endnum);
			mav.addObject("boardPagingUrl", boardname + "?");
		}else {
			boardList = service.selectSubjectPagingBoard(boardname, (int) startnum, (int) endnum, keyword);
			mav.addObject("boardPagingUrl", boardname + "?keyword=" + keyword + "&");
		}
		
		mav.addObject("boardList", boardList);
		mav.addObject("boardPaging", paging);
		mav.setViewName("board/list");
		
		return mav;
		
	}
	
	@RequestMapping(value="view/{boardname}", method=RequestMethod.GET)
	public ModelAndView view(Locale locale, Model model,
												@PathVariable("boardname")String boardname, 
												@RequestParam int id) {

		logger.info("Welcome board! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		
		BoardDTO boardDTO = service.selectBoard(boardname, id);
		BoardFileInfoDTO tmpFileInfo = new BoardFileInfoDTO();
		tmpFileInfo.setArticle_id(id);
		List<BoardFileInfoDTO> boardFileInfo = service.selectRngBoardFileInfo(boardname, tmpFileInfo);
		
		mav.addObject("id", id);
		mav.addObject("boardDTO", boardDTO);
		mav.addObject("boardFileInfo", boardFileInfo);
		mav.setViewName("board/view");
		
		return mav;
		
	}
	
	@RequestMapping(value="modify/{boardname}", method=RequestMethod.GET)
	public ModelAndView modify(Locale locale, Model model,
												@PathVariable("boardname")String boardname, 
												@RequestParam int id) {

		logger.info("Welcome board! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		
		BoardDTO boardDTO = service.selectBoard(boardname, id);
		BoardFileInfoDTO tmpFileInfo = new BoardFileInfoDTO();
		tmpFileInfo.setArticle_id(id);
		List<BoardFileInfoDTO> boardFileInfo = service.selectRngBoardFileInfo(boardname, tmpFileInfo);
		
		mav.addObject("id", id);
		mav.addObject("boardDTO", boardDTO);
		mav.addObject("boardFileInfo", boardFileInfo);
		mav.addObject("token", "helloworld");
		mav.setViewName("board/modify");
		
		return mav;
		
	}

	@RequestMapping(value = "modify_ok", method = RequestMethod.POST)
	public ModelAndView modify_ok(Locale locale, 
								   Model model, 
								   BoardDTO dto,
								   String boardname) {
		
		logger.info("Welcome board! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		Date time = new Date();
		dto.setRegidate(time);
		
		service.updateBoard(boardname, dto);
		
		mav.setViewName("redirect:list/" + boardname);
		
		return mav;
		
	}

	@RequestMapping(value="delete/{boardname}", method=RequestMethod.GET)
	public ModelAndView delete(Locale locale, Model model,
												@PathVariable("boardname")String boardname, 
												@RequestParam int id) {

		logger.info("Welcome board! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("id", id);
		mav.setViewName("board/delete");
		
		return mav;
		
	}
	
	@RequestMapping(value = "delete_ok", method = RequestMethod.POST)
	public ModelAndView delete_ok(Locale locale, 
								   Model model, 
								   BoardDTO dto,
								   String boardname) {
		
		logger.info("Welcome board! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		Date time = new Date();
		dto.setRegidate(time);
		
		service.deleteBoard(boardname, dto);
		
		mav.setViewName("redirect:list/" + boardname);
		
		return mav;
		
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET) 
	public ModelAndView download(HttpServletRequest request, 
											  HttpServletResponse response,
											  BoardFileInfoDTO boardFileInfo,
											  String boardname) {

		ModelAndView mav = new ModelAndView();
		
		boolean status = false;
		BoardFileInfoDTO dbBoardFileInfo = service.selectOneBoardFileInfo(boardname, boardFileInfo);
		
		if ( dbBoardFileInfo != null ) {
			logger.info("???");
			status = true;
		}else {
			logger.info("db ????????? ?????? ??? ??????");
		}
		
		if ( status == true ) {
			fileDownload(request, response, dbBoardFileInfo, boardname);
			mav.setViewName("");
		}else {
			mav.setViewName("redirect:list/" + boardname);
		}
		
		return mav;
		
	}
	
	private void fileDownload(HttpServletRequest request, 
											  HttpServletResponse response,
											  BoardFileInfoDTO boardFileInfo,
											  String boardname) {
		

		String path = request.getSession().getServletContext().getRealPath("resources");
		String saveDir = path + "/" + realPath;
		
		String realName = boardFileInfo.getOriginFile();
		logger.info("??????:" + boardFileInfo.getId());
		logger.info("?????????:" + realName);
		
		String srcName = boardFileInfo.getSaveFile();
		
		File file = new File( saveDir + "/" + srcName ); 
		
		FileInputStream fis = null; 
		BufferedInputStream bis = null; 
		ServletOutputStream sos = null; 
		
		try { 
			
			fis = new FileInputStream(file); 
			bis = new BufferedInputStream(fis); 
			sos = response.getOutputStream(); 
			
			String reFilename = ""; 
			
			// MS Internet Explorer ??????
			boolean isMSIE = request.getHeader("user-agent").indexOf("MSIE") != -1 || 
					request.getHeader("user-agent").indexOf("Trident") != -1; 
			
			if(isMSIE) { 
				reFilename = URLEncoder.encode( realName, "utf-8"); 
				reFilename = reFilename.replaceAll("\\+", "%20"); 
			}
			else { 
				reFilename = new String( realName.getBytes("utf-8"), "ISO-8859-1"); 
			} 
			
			response.setContentType("application/octet-stream;charset=utf-8"); 
			response.addHeader("Content-Disposition", "attachment;filename=\""+ reFilename +"\""); 
			response.setContentLength((int)file.length()); 
			
			int read = 0; 
			
			while((read = bis.read()) != -1) {
				sos.write(read); 
			} 
						
		}catch(IOException e) { 
			logger.info("??????1:" + e.getMessage());
			
		}finally { 
			
			try { 
				if ( sos != null ) {
					sos.close();
				}
				
				if ( bis != null ) {
					bis.close(); 
				}
				
			}catch (IOException e) {
				logger.info("??????2:" + e.getMessage()); 
			} 
			
		}
		
	}
	

	@RequestMapping(value = "fileDelete", method = RequestMethod.GET) 
	public ModelAndView fileDelete(HttpServletRequest request, 
											  HttpServletResponse response,
											  BoardFileInfoDTO criteria,
											  String boardname, 
											  String key) {

		String TOKEN_AUTH = "helloworld";
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/ajaxMessage");
		boolean status = false;
		
		BoardFileInfoDTO tmpFileInfo = service.getIdBoardFileInfo(boardname, criteria);
		
		// ?????? ?????? ??????
		if(tmpFileInfo != null) {
			status = true;
			logger.info("????????????1: ??????O");
		}else {
			status = false;
			logger.info("????????????1: ??????X");
		}
		
		// ?????? ??????
		if( key.equals( TOKEN_AUTH ) == true &&
				status == true ) {
			logger.info("????????????2: ??????O");
			status = fileDelete(request, response, tmpFileInfo);
			
		}else {
			status = false;
			logger.info("????????????2: ??????X");
		}
		
		// ?????? ?????? ??????
		if ( status == false ) {
			mav.addObject("message", "failed");
		}
		
		// ?????? ?????? ??????
		if ( status == true ) {
			
			service.deleteBoardFileInfo(boardname, tmpFileInfo);
			mav.addObject("message", "success");
		}
		
		return mav;
		
	}
	
	private boolean fileDelete(HttpServletRequest request, 
			  							HttpServletResponse response, 
			  							BoardFileInfoDTO boardFileInfo) {
		
		boolean status = false;
		
		String path = request.getSession().getServletContext().getRealPath("resources");
		String saveDir = path + "/" + realPath;
		
		logger.info("??????????????????:" + saveDir );
		
		String realName = boardFileInfo.getOriginFile();
		String srcName = boardFileInfo.getSaveFile();
		
		File fileIo = new File(saveDir + "/" + srcName);
		
		// ?????? ?????? ??????
		if ( fileIo.exists() ) {
			
			if ( fileIo.delete() ) {
				logger.info("?????? ??????!");
				status = true;
			}else {
				logger.info("?????? ??????!");
			}
			
		}
		else {
			logger.info("????????? ???????????? ??????");
		}
		
		return status;
		
	}

}
