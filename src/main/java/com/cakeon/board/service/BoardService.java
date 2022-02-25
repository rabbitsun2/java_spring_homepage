package com.cakeon.board.service;

import java.util.List;

import com.cakeon.board.model.BoardDTO;
import com.cakeon.board.model.BoardFileInfoDTO;

public interface BoardService {

	public List<BoardDTO> selectAllBoard(String boardname);
	
	public List<BoardDTO> selectPagingBoard(String boardname, 
																		long startnum,
																		long endnum);
	
	public List<BoardDTO> selectSubjectPagingBoard(String boardname, 
																						long startnum,
																						long endnum,
																						String subject);
	
	public int selectAllCount(String boardname);

	public int selectAllSubjectCount(String boardname, BoardDTO dto);

	public BoardDTO selectBoard(String boardname, long id);
	
	public BoardDTO selectArticle(String boardname, BoardDTO dto);
	
	public int insertBoard(String boardname, BoardDTO dto);
	
	public int updateBoard(String boardname, BoardDTO dto);
	
	public int deleteBoard(String boardname, BoardDTO dto);
	
	public int insertBoardFileInfo(String boardname, BoardFileInfoDTO dto);
	
	public int deleteBoardFileInfo(String boardname, BoardFileInfoDTO dto);
	
	public List<BoardFileInfoDTO> selectRngBoardFileInfo(String boardname, BoardFileInfoDTO dto);
	
	public BoardFileInfoDTO selectOneBoardFileInfo(String boardname, BoardFileInfoDTO dto);
	
	public BoardFileInfoDTO getIdBoardFileInfo(String boardname, BoardFileInfoDTO dto);
	
}
