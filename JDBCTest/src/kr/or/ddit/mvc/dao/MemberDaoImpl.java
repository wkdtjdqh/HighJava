package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	private static MemberDaoImpl singleMdi;
	Connection conn = null;
	PreparedStatement ps = null;
	
	private MemberDaoImpl() {}
	
	public static MemberDaoImpl getInstance(){
		if(singleMdi == null) singleMdi = new MemberDaoImpl();
		return singleMdi;
	}
	
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER(MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) VALUES(?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memVo.getMem_id());
			ps.setString(2, memVo.getMem_name());
			ps.setString(3, memVo.getMem_tel());
			ps.setString(4, memVo.getMem_addr());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memid) {
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memid);
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? WHERE MEM_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memVo.getMem_name());
			ps.setString(2, memVo.getMem_tel());
			ps.setString(3, memVo.getMem_addr());
			ps.setString(4, memVo.getMem_id());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ ps.close(); } catch(SQLException e){ } }
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		ResultSet rs = null;
		List<MemberVO> memVOList = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR FROM MYMEMBER";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			memVOList = new ArrayList<>();
			
			while(rs.next()){
				MemberVO memVO = new MemberVO();
				memVO.setMem_id(rs.getString(1));
				memVO.setMem_name(rs.getString(2));
				memVO.setMem_tel(rs.getString(3));
				memVO.setMem_addr(rs.getString(4));
				memVOList.add(memVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ ps.close(); } catch(SQLException e){ } }
		}
		return memVOList;
	}
	
	@Override
	public MemberVO getMember(String memid) {
		ResultSet rs = null;
		MemberVO memVO = new MemberVO();
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR FROM MYMEMBER WHERE MEM_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memid);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				memVO.setMem_id(rs.getString(1));
				memVO.setMem_name(rs.getString(2));
				memVO.setMem_tel(rs.getString(3));
				memVO.setMem_addr(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ ps.close(); } catch(SQLException e){ } }
		}
		return memVO;
	}

	@Override
	public int getMemberCount(String memid) {
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT COUNT(MEM_ID) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memid);
			
			rs = ps.executeQuery();
			while(rs.next()){
				cnt = rs.getInt("CNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 정보 ==> 회원ID : memid, 수정할 컬럼명 : field, 수정할 데이터 : data
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET " + paramMap.get("field") + " = ? WHERE MEM_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, paramMap.get("data"));
			ps.setString(1, paramMap.get("memid"));
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ ps.close(); } catch(SQLException e){ } }
		}
		return cnt;
	}

}
