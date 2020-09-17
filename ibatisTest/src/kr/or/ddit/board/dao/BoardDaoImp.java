package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.util.BuildSqlMapClient;
import kr.or.ddit.board.vo.BoardVO;


public class BoardDaoImp implements IBoardDao{
	private SqlMapClient smc;
	
	public BoardDaoImp() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		int result = 0;
		try {
			Object obj = smc.insert("myboard.insertBoard", boardVo);
			if (obj == null) result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BoardVO viewBoard(int boardNo) {
		BoardVO boardVo = new BoardVO();
		try {
			boardVo = (BoardVO)smc.queryForObject("myboard.viewBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return boardVo;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		int result = 0;
		try {
			result = smc.update("myboard.updateBoard", boardVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int result = 0;
		try {
			result = smc.delete("myboard.deleteBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BoardVO> searchTitle(String boardTitle) {
		List<BoardVO> boardList = null;
		try {
			boardList = smc.queryForList("myboard.searchTitle", boardTitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int updateCnt(int boardNo) {
		int result = 0;
		
		try {
			result = smc.update("myboard.updateCnt", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BoardVO> searchAll() {
		List<BoardVO> boardVoList = new ArrayList<>();
		
		try {
			boardVoList = smc.queryForList("myboard.searchAll");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return boardVoList;
	}
}
