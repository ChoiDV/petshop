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

import com.lec.petshop.dto.Cat_ReservationDto;
import com.lec.petshop.dto.Dog_ReservationDto;

public class Cat_ReservationDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	
	private static Cat_ReservationDao instance = new Cat_ReservationDao();

	public static Cat_ReservationDao getInstance() {
		return instance;
	}

	private Cat_ReservationDao() {
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
	public int cReservationCat(Cat_ReservationDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CAT_RESERVATION ( MID, CNUM, CR_DATE)" + 
				"    VALUES ( ? , ? , ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setInt(2, dto.getCnum());
			pstmt.setDate(3, dto.getCr_date());
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
		public ArrayList<Cat_ReservationDto> listReservationCat(String mid) {
			ArrayList<Cat_ReservationDto> dtos = new ArrayList<Cat_ReservationDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT CR.*, C.CNAME,C.CGENDER, C.CBIRTH, C.CPRICE, (SELECT CC.CBREEDNAME FROM CAT CO, CBREED CC WHERE CO.CBREEDNO = CC.CBREEDNO) CBREEDNAME" + 
					"    FROM CAT_RESERVATION CR, CAT C" + 
					"     WHERE CR.CNUM = C.CNUM " + 
					"         AND MID= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int cnum = rs.getInt("cnum");
					Date cr_date = rs.getDate("cr_date");
					String cname = rs.getString("cname");
					String cgender = rs.getString("cgender");
					Date cbirth = rs.getDate("cbirth");
					int cprice = rs.getInt("cprice");
					String cbreedname = rs.getString("cbreedname");
					dtos.add(new Cat_ReservationDto(mid, cnum, cr_date, cname, cgender, cbirth, cprice, cbreedname));
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
		public int changeCRdate(Date cr_date, String mid, int cnum) {
			int result = SUCCESS;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE CAT_RESERVATION SET CR_DATE = ? " + 
					"                            WHERE MID= ? "
					+ "								AND CNUM= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setDate(1, cr_date);
				pstmt.setString(2, mid);
				pstmt.setInt(3, cnum);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "예약일 변경 성공 " : "예약일 변경 실패");
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"예약일 변경  실패 "+ cr_date + mid + cnum);
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
		public int cancelCRdate(String mid, int cnum) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE CAT_RESERVATION WHERE MID= ? " + 
					"                                AND CNUM= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				pstmt.setInt(2, cnum);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "예약  취소 성공 " : "예약 취소 실패");
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"예약 취소 실패 " + mid + cnum);
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
