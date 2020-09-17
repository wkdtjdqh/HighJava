package kr.or.ddit.mvc.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	private static MemberDaoImpl singleMdi;
	Connection conn = null;
	PreparedStatement ps = null;
	private SqlMapClient smc;	// ibatis용 SqlMapClient객체 변수 선언
	// 생성자에서 ibatis환경 설정을 진행한다.
	private MemberDaoImpl() {
		try {
			// 1-1. 문자 인코딩 케릭터 셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경 설정파일 읽어오기
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을 완성한 후
			// 		SQL문을 호출해서 실행할 수 있는 객체를 생성한다.
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();	// 스트림 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDaoImpl getInstance(){
		if(singleMdi == null) singleMdi = new MemberDaoImpl();
		return singleMdi;
	}
	
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("mymember.insertMember", memVo);
			if (obj == null) cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memid) {
		int cnt = 0;
		try {
			cnt = smc.delete("mymember.deleteMember", memid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		try {
			cnt = smc.update("mymember.updateMember", memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memVOList = null;
		try {
			memVOList = smc.queryForList("mymember.getAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memVOList;
	}
	
	@Override
	public MemberVO getMember(String memid) {
		MemberVO memVO = new MemberVO();
		try {
			memVO = (MemberVO)smc.queryForObject("mymember.getSelect");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memVO;
	}

	@Override
	public int getMemberCount(String memid) {
		int cnt = 0;
		
		try {
			MemberVO memVO = (MemberVO)smc.queryForObject("mymember.checked");
			if (memVO == null) {
				return cnt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 정보 ==> 회원ID : memid, 수정할 컬럼명 : field, 수정할 데이터 : data
		int cnt = 0;
		
		try {
			cnt = smc.update("mymember.updateMember2", paramMap);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
