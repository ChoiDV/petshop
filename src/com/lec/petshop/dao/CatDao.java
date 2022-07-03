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

import com.lec.petshop.dto.CatDto;
import com.lec.petshop.dto.CbreedDto;
import com.lec.petshop.dto.DbreedDto;

public class CatDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	private static CatDao instance = new CatDao();

	public static CatDao getInstance() {
		return instance;
	}

	private CatDao() {
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

	// 고양이 분양글 등록
	public int insertCat(CatDto dto) {  
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CAT (CNUM, CNAME, CGENDER, CBIRTH, CPRICE," + " CBREEDNO, AID, CCONTENT,"
				+ " CIMAGE1, CIMAGE2,CIMAGE3,CIMAGE4,CIMAGE5, CIP)"
				+ "    VALUES(CAT_SEQ.NEXTVAL, ?, ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCname());
			pstmt.setString(2, dto.getCgender());
			pstmt.setDate(3, dto.getCbirth());
			pstmt.setInt(4, dto.getCprice());
			pstmt.setInt(5, dto.getCbreedno());
			pstmt.setString(6, dto.getAid());
			pstmt.setString(7, dto.getCcontent());
			pstmt.setString(8, dto.getCimage1());
			pstmt.setString(9, dto.getCimage2());
			pstmt.setString(10, dto.getCimage3());
			pstmt.setString(11, dto.getCimage4());
			pstmt.setString(12, dto.getCimage5());
			pstmt.setString(13, dto.getCip());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "고양이 분양글 등록 성공" : "고양이 분양글 등록 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("고양이 등록 실패 : " + dto);
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

	// 고양이 분양글 출력 (startRow ~ endRow )
	public ArrayList<CatDto> listCat(int startRow, int endRow) {
		ArrayList<CatDto> dtos = new ArrayList<CatDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT ROWNUM RN, A.* FROM" + 
				"                (SELECT * FROM CAT ORDER BY CRDATE DESC) A) C, CBREED CB" + 
				"    WHERE C.CBREEDNO = CB.CBREEDNO" + 
				"        AND RN BETWEEN ? AND ?" + 
				"            ORDER BY RN ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cnum = rs.getInt("cnum");
				String cname = rs.getString("cname");
				String cgender = rs.getString("cgender");
				Date cbirth = rs.getDate("cbirth");
				int cprice = rs.getInt("cprice");
				int cbreedno = rs.getInt("cbreedno");
				String aid = rs.getString("aid");
				String ccontent = rs.getString("ccontent");
				String cimage1 = rs.getString("cimage1");
				String cimage2 = rs.getString("cimage2");
				String cimage3 = rs.getString("cimage3");
				String cimage4 = rs.getString("cimage4");
				String cimage5 = rs.getString("cimage5");
				String cip = rs.getString("cip");
				int chit = rs.getInt("chit");
				int cr_check = rs.getInt("cr_check");
				Date crdate = rs.getDate("crdate");
				String cbreedname = rs.getString("cbreedname");
				dtos.add(new CatDto(cnum, cname, cgender, cbirth, cprice, cbreedno, aid, ccontent, cimage1, cimage2,
						cimage3, cimage4, cimage5, cip, chit, cr_check, crdate, cbreedname));
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

	// 글상세보기  hitup
	public CatDto catContent(int cnum) {
		CatDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CAT C, CBREED CB" + 
				"    WHERE C.CBREEDNO = CB.CBREEDNO" + 
				"        AND CNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hitUp(cnum);
				String cname = rs.getString("cname");
				String cgender = rs.getString("cgender");
				Date cbirth = rs.getDate("cbirth");
				int cprice = rs.getInt("cprice");
				int cbreedno = rs.getInt("cbreedno");
				String aid = rs.getString("aid");
				String ccontent = rs.getString("ccontent");
				String cimage1 = rs.getString("cimage1");
				String cimage2 = rs.getString("cimage2");
				String cimage3 = rs.getString("cimage3");
				String cimage4 = rs.getString("cimage4");
				String cimage5 = rs.getString("cimage5");
				String cip = rs.getString("cip");
				int chit = rs.getInt("chit");
				int cr_check = rs.getInt("cr_check");
				Date crdate = rs.getDate("crdate");
				String cbreedname = rs.getString("cbreedname");
				dto = new CatDto(cnum, cname, cgender, cbirth, cprice, cbreedno, aid, ccontent, cimage1, cimage2,
						cimage3, cimage4, cimage5, cip, chit, cr_check, crdate, cbreedname);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "상세보기 가져오기 실패" + dto);
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
	// hitup 없이 dto 가져오기
	public CatDto catModifyContent(int cnum) {
		CatDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CAT C, CBREED CB" + 
				"    WHERE C.CBREEDNO = CB.CBREEDNO" + 
				"        AND CNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cname = rs.getString("cname");
				String cgender = rs.getString("cgender");
				Date cbirth = rs.getDate("cbirth");
				int cprice = rs.getInt("cprice");
				int cbreedno = rs.getInt("cbreedno");
				String aid = rs.getString("aid");
				String ccontent = rs.getString("ccontent");
				String cimage1 = rs.getString("cimage1");
				String cimage2 = rs.getString("cimage2");
				String cimage3 = rs.getString("cimage3");
				String cimage4 = rs.getString("cimage4");
				String cimage5 = rs.getString("cimage5");
				String cip = rs.getString("cip");
				int chit = rs.getInt("chit");
				int cr_check = rs.getInt("cr_check");
				Date crdate = rs.getDate("crdate");
				String cbreedname = rs.getString("cbreedname");
				dto = new CatDto(cnum, cname, cgender, cbirth, cprice, cbreedno, aid, ccontent, cimage1, cimage2,
						cimage3, cimage4, cimage5, cip, chit, cr_check, crdate, cbreedname);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "상세보기 가져오기 실패" + dto);
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
	// 글 조회수 올리기
	private void hitUp(int cnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE CAT SET CHIT= CHIT +1" +
					"   	 WHERE CNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			pstmt.executeUpdate();
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
	}

	// 전체 글 개수 가져오기
	public int totalCat() {
		int totalCat = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM CAT";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totalCat = rs.getInt(1);
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totalCat;
	}
	
	// 고양이 분양글 수정하기 
	public int updateCat(CatDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE CAT SET CNAME= ? ," + 
				"                CGENDER= ? ," + 
				"                 CBIRTH= ? ," + 
				"                  CPRICE= ? ," + 
				"                   CBREEDNO= ? ," + 
				"                    AID= ? ," + 
				"                     CCONTENT= ? ," + 
				"                      CIMAGE1= ? ," + 
				"                       CIMAGE2= ?," + 
				"                        CIMAGE3= ? ," + 
				"                         CIMAGE4= ? ," + 
				"                          CIMAGE5= ? ," + 
				"                           CIP = ? " + 
				"                    WHERE CNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCname());
			pstmt.setString(2, dto.getCgender());
			pstmt.setDate(3, dto.getCbirth());
			pstmt.setInt(4, dto.getCprice());
			pstmt.setInt(5, dto.getCbreedno()); 
			pstmt.setString(6, dto.getAid());
			pstmt.setString(7, dto.getCcontent());
			pstmt.setString(8, dto.getCimage1());
			pstmt.setString(9, dto.getCimage2());
			pstmt.setString(10, dto.getCimage3());
			pstmt.setString(11, dto.getCimage4());
			pstmt.setString(12, dto.getCimage5());
			pstmt.setString(13, dto.getCip());
			pstmt.setInt(14, dto.getCnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "고양이 분양글 수정 성공" : "고양이 분양글 수정 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("등록 실패 : " + dto);
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
	
	// 고양이 글 삭제하기 
		public int deleteCat(int cnum) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE CAT WHERE CNUM= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cnum);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "고양이 분양글 삭제 성공" : "고양이 분양글 삭제 실패");
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
		
		// 예약하기
		public int reservationCat(int cnum) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE CAT SET CR_CHECK=0 " + 
					"    WHERE CNUM= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cnum);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "고양이 예약 성공" : "고양이 예약 실패");
			} catch (Exception e) {
				System.out.println(e.getMessage()+"고양이 예약 실패");
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
		
		// 예약 취소
		public int cancellationCat(int cnum) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE CAT SET CR_CHECK=1 " + 
					"    WHERE CNUM= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cnum);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "고양이 예약취소 성공" : "고양이 예약취소 실패");
			} catch (Exception e) {
				System.out.println(e.getMessage()+"고양이 예약취소 실패");
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
		
		// 고양이 나이계산
		public int catAge(Date cbirth, int cnum) {
			int age = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT TRUNC(MONTHS_BETWEEN(SYSDATE, ? )/12)*12+MOD(TRUNC(MONTHS_BETWEEN(SYSDATE, ? )),12) MONTH FROM CAT WHERE CNUM= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setDate(1, cbirth);
				pstmt.setDate(2, cbirth);
				pstmt.setInt(3, cnum);
				rs = pstmt.executeQuery();
				rs.next();
				age = rs.getInt(1);
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
			return age;
		}
		
		// 묘종 불러오기
		public ArrayList<CbreedDto> breedList(){
			ArrayList<CbreedDto> dtos = new ArrayList<CbreedDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM CBREED";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int cbreedno = rs.getInt("cbreedno");
					String cbreedname = rs.getString("cbreedname");
					dtos.add(new CbreedDto(cbreedno, cbreedname));
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
	
		

}
