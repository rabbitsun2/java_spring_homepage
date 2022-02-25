package com.cakeon.board.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.cakeon.board.model.BoardDTO;
import com.cakeon.board.model.BoardFileInfoDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.cakeon.mapper.BoardMapper";
	
	@Override
	public String getTime() {
		
		if ( sqlSession != null ) {
			System.out.println("참3");
		}else {
			System.out.println("거짓3");
		}
		
		return sqlSession.selectOne(ns + ".getTime");
	}

	@Override
	public List<BoardDTO> selectAllBoard(String boardname) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardname", boardname);
		
		return sqlSession.selectList(ns + ".selectAllBoard", paramMap);
	}

	@Override
	public List<BoardDTO> selectPagingBoard(String boardname, long startnum, long endnum) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		// 게시판명
		paramMap.put("boardname", boardname);
		
		//	오라클 페이징
		//paramMap.put("startnum", startnum);		
		//paramMap.put("endnum", endnum);			
		
		// mariaDB 페이징
		if ( startnum == 1) {
			paramMap.put("startnum", 0);
		}
		else {
			paramMap.put("startnum", startnum - 1);
		}
		
		paramMap.put("limnum", ( endnum - startnum) + 1);			
		
		return sqlSession.selectList(ns + ".selectPagingBoard", paramMap);
		
	}

	@Override
	public List<BoardDTO> selectSubjectPagingBoard(String boardname, long startnum, long endnum, String subject) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("boardname", boardname);
		
		// 오라클 페이징
		//paramMap.put("startnum", startnum);
		//paramMap.put("endnum", endnum);
		

		// mariaDB 페이징
		if ( startnum == 1) {
			paramMap.put("startnum", 0);
		}
		else {
			paramMap.put("startnum", startnum - 1);
		}
		
		paramMap.put("limnum", ( endnum - startnum) + 1);	
		paramMap.put("subject", subject);
		
		return sqlSession.selectList(ns + ".selectSubjectPagingBoard", paramMap);
	}
	
	@Override
	public int selectAllCount(String boardname) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardname", boardname);
		
		return sqlSession.selectOne(ns + ".selectAllCount", paramMap);
	}

	@Override
	public int selectAllSubjectCount(String boardname, BoardDTO dto) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardname", boardname);
		paramMap.put("subject", dto.getSubject() );
		
		return sqlSession.selectOne(ns + ".selectAllSubjectCount", paramMap);
	}
	
	@Override
	public BoardDTO selectBoard(String boardname, long id) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardname", boardname);
		paramMap.put("id", id);
		
		return sqlSession.selectOne(ns + ".selectBoard", paramMap);
	}
	
	@Override
	public BoardDTO selectArticle(String boardname, BoardDTO dto) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	// 날짜 양식
		
		paramMap.put("boardname", boardname);
		paramMap.put("id", dto.getId());
		paramMap.put("author", dto.getAuthor());
		paramMap.put("passwd", dto.getPasswd());
		paramMap.put("subject", dto.getSubject());
		paramMap.put("email", dto.getEmail());
		paramMap.put("memo", dto.getMemo());
		paramMap.put("regidate",  format1.format( dto.getRegidate() ) );
		paramMap.put("ip", dto.getIp());
		paramMap.put("cnt", dto.getCnt());

		return sqlSession.selectOne(ns + ".selectArticle", paramMap);
	}

	@Override
	public int insertBoard(String boardname, BoardDTO dto) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	// 날짜 양식
		
		paramMap.put("boardname", boardname);
		paramMap.put("id", dto.getId());
		paramMap.put("author", dto.getAuthor());
		paramMap.put("passwd", dto.getPasswd());
		paramMap.put("subject", dto.getSubject());
		paramMap.put("email", dto.getEmail());
		paramMap.put("memo", dto.getMemo());
		paramMap.put("ip", dto.getIp());
		paramMap.put("regidate", format1.format( dto.getRegidate() ) );
		paramMap.put("cnt", dto.getCnt());
		
		return sqlSession.insert(ns + ".insertBoard", paramMap);
	}

	@Override
	public int updateBoard(String boardname, BoardDTO dto) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("boardname", boardname);
		paramMap.put("id", dto.getId());
		paramMap.put("author", dto.getAuthor());
		paramMap.put("passwd", dto.getPasswd());
		paramMap.put("subject", dto.getSubject());
		paramMap.put("email", dto.getEmail());
		paramMap.put("memo", dto.getMemo());
		paramMap.put("ip", dto.getIp());
		
		return sqlSession.update(ns + ".updateBoard", paramMap);
		
	}

	@Override
	public int deleteBoard(String boardname, BoardDTO dto) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("boardname", boardname);
		paramMap.put("id", dto.getId());
		
		return sqlSession.delete(ns + ".deleteBoard", paramMap);
	}

	@Override
	public int insertBoardFileInfo(String boardname, BoardFileInfoDTO dto) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	// 날짜 양식
				
		paramMap.put("boardname", boardname);
		paramMap.put("article_id", dto.getArticle_id());
		paramMap.put("saveFolder", dto.getSaveFolder());
		paramMap.put("originFile", dto.getOriginFile());
		paramMap.put("saveFile", dto.getSaveFile());
		paramMap.put("ext", dto.getExt());
		paramMap.put("ip", dto.getIp());
		paramMap.put("regidate", format1.format( dto.getRegidate() ) );
		
		return sqlSession.insert(ns + ".insertBoardFileInfo", paramMap);
	}

	@Override
	public int deleteBoardFileInfo(String boardname, BoardFileInfoDTO dto) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
				
		paramMap.put("boardname", boardname);
		paramMap.put("id", dto.getId() );
		paramMap.put("article_id", dto.getArticle_id() );
		
		return sqlSession.delete( ns + ".deleteBoardFileInfo", paramMap);
		
	}

	@Override
	public List<BoardFileInfoDTO> selectRngBoardFileInfo(String boardname, BoardFileInfoDTO dto) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
				
		paramMap.put("boardname", boardname);
		paramMap.put("article_id", dto.getArticle_id() );
		
		return sqlSession.selectList( ns + ".selectRngBoardFileInfo", paramMap);
		
	}

	@Override
	public BoardFileInfoDTO selectOneBoardFileInfo(String boardname, BoardFileInfoDTO dto) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("boardname", boardname );
		paramMap.put("id", dto.getId() );
		paramMap.put("originFile", dto.getOriginFile() );
		
		return sqlSession.selectOne(ns + ".selectOneBoardFileInfo", paramMap);
		
	}

	@Override
	public BoardFileInfoDTO getIdBoardFileInfo(String boardname, BoardFileInfoDTO dto) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("boardname", boardname);
		paramMap.put("id", dto.getId());
		
		return sqlSession.selectOne(ns + ".getIdBoardFileInfo", paramMap);
	}

}