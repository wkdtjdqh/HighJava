package home.mvc.service;

import java.util.List;

import home.mvc.dao.BoardDaoImp;
import home.mvc.dao.IBoardDao;
import home.mvc.vo.BoardVO;

public class BoardServiceImp implements IBoardService{
	private IBoardDao dao = new BoardDaoImp();

	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public BoardVO viewBoard(int boardNo) {
		return dao.viewBoard(boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public BoardVO searchTitle(String boardTitle) {
		return dao.searchTitle(boardTitle);
	}
	
	@Override
	public List<BoardVO> searchAll() {
		return dao.searchAll();
	}

	@Override
	public int updateCnt(int boardNo) {
		return dao.updateCnt(boardNo);
	}

}
