package home.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import home.mvc.vo.BoardVO;
import home.util.DBUtil;

public class BoardDaoImp implements IBoardDao{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int insertBoard(BoardVO boardVo) {
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "INSERT INTO JDBC_BOARD(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT) "
							+ "VALUES(BOARD_SEQ.NEXTVAL, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBOARD_TITLE());
			pstmt.setString(2, boardVo.getBOARD_WRITER());
			pstmt.setString(3, boardVo.getBOARD_CONTENT());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ try{ pstmt.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return result;
	}

	@Override
	public BoardVO viewBoard(int boardNo) {
		BoardVO boardVo = new BoardVO();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT, BOARD_DATE, BOARD_CNT "
					+ "FROM JDBC_BOARD "
					+ "WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardVo.setBOARD_NO(rs.getInt("BOARD_NO"));
				boardVo.setBOARD_TITLE(rs.getString("BOARD_TITLE"));
				boardVo.setBOARD_WRITER(rs.getString("BOARD_WRITER"));
				boardVo.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				boardVo.setBOARD_DATE(rs.getString("BOARD_DATE"));
				boardVo.setBOARD_CNT(rs.getInt("BOARD_CNT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
			if(pstmt != null){ try{ pstmt.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return boardVo;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		int result = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "UPDATE JDBC_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBOARD_TITLE());
			pstmt.setString(2, boardVo.getBOARD_CONTENT());
			pstmt.setInt(3, boardVo.getBOARD_NO());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ try{ pstmt.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return result;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int result = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ try{ pstmt.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return result;
	}

	@Override
	public BoardVO searchTitle(String boardTitle) {
		BoardVO boardVo = new BoardVO();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT, BOARD_DATE, BOARD_CNT "
						+ "FROM JDBC_BOARD "
						+ "WHERE BOARD_TITLE LIKE ?";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + boardTitle + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				boardVo.setBOARD_NO(rs.getInt("BOARD_NO"));
				boardVo.setBOARD_TITLE(rs.getString("BOARD_TITLE"));
				boardVo.setBOARD_WRITER(rs.getString("BOARD_WRITER"));
				boardVo.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				boardVo.setBOARD_DATE(rs.getString("BOARD_DATE"));
				boardVo.setBOARD_CNT(rs.getInt("BOARD_CNT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
			if(pstmt != null){ try{ pstmt.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return boardVo;
	}

	@Override
	public int updateCnt(int boardNo) {
		int result = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "UPDATE JDBC_BOARD SET BOARD_CNT = (SELECT MAX(BOARD_CNT) + 1 FROM JDBC_BOARD WHERE BOARD_NO = ?) "
					+ "WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ try{ pstmt.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return result;
	}

	@Override
	public List<BoardVO> searchAll() {
		List<BoardVO> boardVoList = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT, BOARD_DATE, BOARD_CNT "
					+ "FROM JDBC_BOARD "
					+ "ORDER BY BOARD_NO DESC";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				
				boardVo.setBOARD_NO(rs.getInt("BOARD_NO"));
				boardVo.setBOARD_TITLE(rs.getString("BOARD_TITLE"));
				boardVo.setBOARD_WRITER(rs.getString("BOARD_WRITER"));
				boardVo.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				boardVo.setBOARD_DATE(rs.getString("BOARD_DATE"));
				boardVo.setBOARD_CNT(rs.getInt("BOARD_CNT"));
				
				boardVoList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
			if(pstmt != null){ try{ pstmt.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		
		return boardVoList;
	}
}
