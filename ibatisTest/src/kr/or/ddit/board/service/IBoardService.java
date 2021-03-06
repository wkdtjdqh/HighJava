package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;


/**
 * Service객체는 Dao에 설정된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 결과자료를 Controller에게 보내주는 역할을 한다.
 * 
 * @author wkdtj
 *
 */
public interface IBoardService {
	/**
	 * BoardVO에 담겨진 자료를 DB에 입력한다. (새 글작성)
	 * 
	 * @param boardVo : DB에 입력할 자료가 저장된 BoardVO객체
	 * @return 
	 */
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * 게시판 번호를 매개변수로 받아서 해당 게시판의 내용을 확인한다. (게시글 보기)
	 * 
	 * @param boardNo : 내용을 확인할 게시판 번호
	 * @return
	 */
	public BoardVO viewBoard(int boardNo);
	
	/**
	 * BoardVO에 담겨진 자료를 통해 DB에 update한다. (게시글 보기 - 수정)
	 * 
	 * @param boardVo : update할 회원 정보가 담겨있는 BoardVO객체
	 * @return
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * 게시판 번호를 매개변수로 받아서 해당 게시판을 삭제한다. (게시글 보기 - 삭제)
	 * 
	 * @param boardNo : 삭제할 게시판 번호
	 * @return
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 게시판 제목을 매개변수로 받아서 해당 게시글 목록을 보여준다. (검색)
	 * 
	 * @param boardTitle : 목록에 띄울 게시판 제목
	 * @return
	 */
	public List<BoardVO> searchTitle(String boardTitle);
	
	/**
	 * DB에 저장된 모든 게시글을 보여준다.
	 * 
	 * @return
	 */
	public List<BoardVO> searchAll();
	
	/**
	 * 게시판 번호를 매개변수로 받아서 해당 게시판의 조회수를 올린다. (게시글 보기)
	 * @param boardNo
	 * @return
	 */
	public int updateCnt(int boardNo);
	
}
