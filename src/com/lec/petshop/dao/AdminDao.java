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

import com.lec.petshop.dto.AdminDto;

public class AdminDao {
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	
	private static AdminDao instance = new AdminDao();

	public static AdminDao getInstance() {
		return instance;
	}

	private AdminDao() {
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
	
	// 관리자 로그인
	public int loginAdmin(String aid, String apw) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN " + 
				"    WHERE AID= ? AND APW= ? ";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, aid);
				pstmt.setString(2, apw);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = LOGIN_SUCCESS;
					System.out.println("관리자 로그인 성공");
				} else {
					result = LOGIN_FAIL;
					System.out.println("관리자 로그인 실패");
					System.out.println("아이디와 비밀번호를 확인해주세요.");
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
	
	// 관리자 로그인 후 dto 가져오기
	public AdminDto aLoginSuccess(String aid) {
		AdminDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN " + "  		  WHERE AID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, aid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String apw = rs.getString("apw");
				String aname = rs.getString("aname");
				dto = new AdminDto(aid, apw, aname);
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
	
	// 관리자 ( 브리더 등록) 
	public int joinAdmin(AdminDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ADMIN (AID, APW, ANAME ) " + 
				"    VALUES ( ? , ? , ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getApw());
			pstmt.setString(3, dto.getAname());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "관리자 등록 성공" : "관리자 등록 실패");
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
	
	// 관리자 삭제
	public int deleteAdmin(String aid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE ADMIN WHERE AID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "관리자 삭제 성공" : "관리자 삭제 실패");
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
	
	// 관리자 목록 출력
	public ArrayList<AdminDto> listAdmin(){
		ArrayList<AdminDto> dtos = new ArrayList<AdminDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String aid = rs.getString("aid");
				String apw = rs.getString("apw");
				String aname = rs.getString("aname");
				dtos.add(new AdminDto(aid, apw, aname));
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
	
	// 관리자 이름 가져오기
	public String getName(String aid) {
		String aname = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ANAME FROM ADMIN WHERE AID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			rs = pstmt.executeQuery();
			rs.next();
			aname = rs.getString("aname");
		} catch (Exception e) {
			System.out.println(e.getMessage()+"없는 aid입니다." + aid);
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
		return aname;
	}
	
	
}
