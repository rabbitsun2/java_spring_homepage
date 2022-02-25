package com.cakeon.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cakeon.board.dao.BoardDAO;
import com.cakeon.board.model.BoardDTO;
import com.cakeon.board.model.BoardFileInfoDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> selectAllBoard(String boardname) {
		
		return dao.selectAllBoard(boardname);
	}

	@Override
	public List<BoardDTO> selectPagingBoard(String boardname, long startnum, long endnum) {
		return dao.selectPagingBoard(boardname, startnum, endnum);
	}

	@Override
	public List<BoardDTO> selectSubjectPagingBoard(String boardname, long startnum, long endnum, String subject) {

		return dao.selectSubjectPagingBoard(boardname, startnum, endnum, subject);
	}
	
	@Override
	public int selectAllCount(String boardname) {
		
		return dao.selectAllCount(boardname);
		
	}
	
	@Override
	public int selectAllSubjectCount(String boardname, BoardDTO dto) {

		return dao.selectAllSubjectCount(boardname, dto);
	}

	@Override
	public BoardDTO selectBoard(String boardname, long id) {
		
		return dao.selectBoard(boardname, id);
	}

	@Override
	public BoardDTO selectArticle(String boardname, BoardDTO dto) {

		return dao.selectArticle(boardname, dto);
		
	}

	@Override
	public int insertBoard(String boardname, BoardDTO dto) {

		return dao.insertBoard(boardname, dto);
	}

	@Override
	public int updateBoard(String boardname, BoardDTO dto) {
		
		return dao.updateBoard(boardname, dto);
	}

	@Override
	public int deleteBoard(String boardname, BoardDTO dto) {
		
		return dao.deleteBoard(boardname, dto);
	}

	@Override
	public int insertBoardFileInfo(String boardname, BoardFileInfoDTO dto) {

		return dao.insertBoardFileInfo(boardname, dto);
		
	}

	@Override
	public int deleteBoardFileInfo(String boardname, BoardFileInfoDTO dto) {
		
		return dao.deleteBoardFileInfo(boardname, dto);
	}

	@Override
	public List<BoardFileInfoDTO> selectRngBoardFileInfo(String boardname, BoardFileInfoDTO dto) {
		
		return dao.selectRngBoardFileInfo(boardname, dto);
	}

	@Override
	public BoardFileInfoDTO selectOneBoardFileInfo(String boardname, BoardFileInfoDTO dto) {

		return dao.selectOneBoardFileInfo(boardname, dto);
	}

	@Override
	public BoardFileInfoDTO getIdBoardFileInfo(String boardname, BoardFileInfoDTO dto) {
		
		return dao.getIdBoardFileInfo(boardname, dto);
	}

}
