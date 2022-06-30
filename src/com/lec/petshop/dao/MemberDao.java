package com.lec.petshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.petshop.dto.MemberDto;

public class MemberDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int LEAVE_MEMBER = 2;

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	private MemberDao() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// 로그인 확인
	public int loginCheck(String mid, String mpw) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER " + "    WHERE MID= ? AND MPW = ? ";
		try {
			int leaveResult = leaveMemberCheck(mid, mpw);
			if (leaveResult != LEAVE_MEMBER) {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				pstmt.setString(2, mpw);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = LOGIN_SUCCESS;
					System.out.println("로그인 성공");
				} else {
					result = LOGIN_FAIL;
					System.out.println("아이디와 비밀번호를 확인해주세요.");
				}
			} else {
				result = LEAVE_MEMBER;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// 로그인 탈퇴회원 걸러내기
	private int leaveMemberCheck(String mid, String mpw) {
		int result = LEAVE_MEMBER;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER " + "    WHERE MID= ? AND MPW = ?  AND MWITHD=0";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = LEAVE_MEMBER;
				System.out.println("탈퇴처리된 회원입니다.");
			} else {
				result = LOGIN_SUCCESS;
				System.out.println("탈퇴회원은 아님");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// 로그인 성공시 dto 가져오기
	public MemberDto loginSuccess(String mid) {
		MemberDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER " + "  		  WHERE MID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mpw = rs.getString("mpw");
				String mname = rs.getString("mname");
				String mtel = rs.getString("mtel");
				String mbirth = rs.getString("mbirth");
				String memail = rs.getString("memail");
				String maddress = rs.getString("maddress");
				String mgender = rs.getString("mgender");
				Date mrdate = rs.getDate("mrdate");
				int mwithd = rs.getInt("mwithd");
				dto = new MemberDto(mid, mpw, mname, mtel, mbirth, memail, maddress, mgender, mrdate, mwithd);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + dto);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}

	// 3. 회원가입
	public int joinMember(MemberDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER (MID, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MADDRESS, MGENDER ) "
				+ "    VALUES ( ? , ? , ? , ? , " + "                TO_DATE( ? ,'RRMMDD'), ? , "
				+ "                                    ? , ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getMpw());
			pstmt.setString(3, dto.getMname());
			pstmt.setString(4, dto.getMtel());
			pstmt.setString(5, dto.getMbirth()); // 주번 앞자리 6 자리로 받을거라서 String
			pstmt.setString(6, dto.getMemail());
			pstmt.setString(7, dto.getMaddress());
			pstmt.setString(8, dto.getMgender());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "회원가입성공" : "회원가입실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("회원가입 실패 : " + dto);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// 정보 수정
	public int updateMember(MemberDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MPW =? ," + 
				"                    MTEL=?," + 
				"                     MEMAIL= ? ," + 
				"                      MADDRESS= ? " + 
				"            WHERE MID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMpw());
			pstmt.setString(2, dto.getMtel());
			pstmt.setString(3, dto.getMemail());
			pstmt.setString(4, dto.getMaddress());
			pstmt.setString(5, dto.getMid()); 
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "회원 정보 수정 성공" : "회원 정보 수정 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("회원정보수정 실패 : " + dto);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// 회원 탈퇴
	public int leaveMember(String mid, String mpw) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MWITHD = 0 " + 
				"            WHERE MID= ? AND MPW= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "회원 탈퇴 성공" : "회원 탈퇴 실패 비밀번호 틀림");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// 정상 회원 목록 출력
	public ArrayList<MemberDto> listMember(int startRow, int endRow){
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT ROWNUM RN, A.* FROM " + 
				"                    (SELECT * FROM MEMBER ORDER BY MRDATE DESC) A)" + 
				"        WHERE MWITHD != 0 " + 
				"            AND RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String mid = rs.getString("mid");
				String mpw = rs.getString("pw");
				String mname = rs.getString("name");
				String mtel = rs.getString("mtel");
				String mbirth = rs.getString("mbirth");
				String memail = rs.getString("memail");
				String maddress = rs.getString("maddress");
				String mgender = rs.getString("mgender");
				Date mrdate = rs.getDate("mrdate");
				int mwithd = rs.getInt("mwithd");
				dtos.add(new MemberDto(mid, mpw, mname, mtel, mbirth, memail, maddress, mgender, mrdate, mwithd));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	// 탈퇴 회원 출력
	public ArrayList<MemberDto> leaveMember(int startRow, int endRow){
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT ROWNUM RN, A.* FROM " + 
				"                    (SELECT * FROM MEMBER ORDER BY MRDATE DESC) A)" + 
				"        WHERE MWITHD != 1 " + 
				"            AND RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String mid = rs.getString("mid");
				String mpw = rs.getString("pw");
				String mname = rs.getString("name");
				String mtel = rs.getString("mtel");
				String mbirth = rs.getString("mbirth");
				String memail = rs.getString("memail");
				String maddress = rs.getString("maddress");
				String mgender = rs.getString("mgender");
				Date mrdate = rs.getDate("mrdate");
				int mwithd = rs.getInt("mwithd");
				dtos.add(new MemberDto(mid, mpw, mname, mtel, mbirth, memail, maddress, mgender, mrdate, mwithd));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	
	// id 중복검사하기
	public int confirmID(String mid) {
		int result = EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) {  // 있으면 아이디 중복 FAIL
				result = EXISTENT;
				System.out.println("아이디 중복 (사용불가능)");
			} else {  // 없으면 아이디 사용가능 SUCCESS 
				result = NONEXISTENT;
				System.out.println("아이디 사용가능 (사용 가능)");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	//  9. 이름, 전화번호, 이메일, 생일, 주소 업데이트
	public int updateAllMember(String mname, String mtel, String memail, String maddress, String mid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MNAME = ?," + 
				"                    MTEL = ?," + 
				"                     MEMAIL = ?," + 
				"                            MADDRESS = ?" + 
				"                    WHERE MID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mname);
			pstmt.setString(2, mtel);
			pstmt.setString(3, memail);
			pstmt.setString(4, maddress);
			pstmt.setNString(5, mid);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "회원 정보 수정 성공" : "회원 정보 수정 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("회원정보수정 실패 : " + mname + mtel + memail+maddress+ mid);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	
	

}
