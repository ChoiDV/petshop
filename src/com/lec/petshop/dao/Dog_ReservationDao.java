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

import com.lec.petshop.dto.Dog_ReservationDto;

public class Dog_ReservationDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	
	private static Dog_ReservationDao instance = new Dog_ReservationDao();

	public static Dog_ReservationDao getInstance() {
		return instance;
	}

	private Dog_ReservationDao() {
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
	
	// 예약하기
	public int dReservationDog(Dog_ReservationDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO DOG_RESERVATION ( MID, DNUM, DR_DATE)" + 
				"    VALUES ( ? , ? , ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setInt(2, dto.getDnum());
			pstmt.setDate(3, dto.getDr_date());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "예약 등록 성공 " : "예약 등록 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"예약 등록 실패 "+dto);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// 내 페이지에서 예약 확인하기
	public ArrayList<Dog_ReservationDto> listReservationDog(String mid) {
		ArrayList<Dog_ReservationDto> dtos = new ArrayList<Dog_ReservationDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DR.*, D.DNAME,D.DGENDER, D.DBIRTH, D.DPRICE, (SELECT DD.DBREEDNAME FROM DOG DO, DBREED DD WHERE DO.DBREEDNO = DD.DBREEDNO) DBREEDNAME " + 
				"    FROM DOG_RESERVATION DR, DOG D " + 
				"     WHERE DR.DNUM = D.DNUM " + 
				"         AND MID= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int dnum = rs.getInt("dnum");
				Date dr_date = rs.getDate("dr_date");
				String dname = rs.getString("dname");
				String dgender = rs.getString("dgender");
				Date dbirth = rs.getDate("dbirth");
				int dprice = rs.getInt("dprice");
				String dbreedname = rs.getString("dbreedname");
				dtos.add(new Dog_ReservationDto(mid, dnum, dr_date, dname, dgender, dbirth, dprice, dbreedname));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "예약 목록 출력 실패");
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	
	// 예약일 변경하기
	public int changeDRdate(Date dr_date, String mid, int dnum) {
		int result = SUCCESS;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE DOG_RESERVATION SET DR_DATE = ? " + 
				"                            WHERE MID= ?"
				+ "								AND DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, dr_date);
			pstmt.setString(2, mid);
			pstmt.setInt(3, dnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "예약일 변경 성공 " : "예약일 변경 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"예약일 변경  실패 "+ dr_date + mid + dnum);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// 예약 취소하기
	public int cancelDRdate(String mid, int dnum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE DOG_RESERVATION WHERE MID= ? AND DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setInt(2, dnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "예약  취소 성공 " : "예약 취소 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"예약 취소 실패 " + mid + dnum);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
