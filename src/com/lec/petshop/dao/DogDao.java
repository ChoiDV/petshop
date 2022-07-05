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

import com.lec.petshop.dto.DbreedDto;
import com.lec.petshop.dto.DogDto;

public class DogDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	private static DogDao instance = new DogDao();

	public static DogDao getInstance() {
		return instance;
	}

	private DogDao() {
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

	// 강아지 분양 글 등록
	public int insertDog(DogDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO DOG (DNUM, DNAME, DGENDER, DBIRTH, DPRICE, DBREEDNO, AID, DCONTENT, DIMAGE1, DIMAGE2,DIMAGE3,DIMAGE4,DIMAGE5, DIP)"
				+ "    VALUES(DOG_SEQ.NEXTVAL, ? , ? , ? , ? ," + "					 ? , ? , ? , ? ,"
				+ "										 ? , ? , ? , ? , ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getDgender());
			pstmt.setDate(3, dto.getDbirth());
			pstmt.setInt(4, dto.getDprice());
			pstmt.setInt(5, dto.getDbreedno());
			pstmt.setString(6, dto.getAid());
			pstmt.setString(7, dto.getDcontent());
			pstmt.setString(8, dto.getDimage1());
			pstmt.setString(9, dto.getDimage2());
			pstmt.setString(10, dto.getDimage3());
			pstmt.setString(11, dto.getDimage4());
			pstmt.setString(12, dto.getDimage5());
			pstmt.setString(13, dto.getDip());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "강아지 분양글 등록 성공" : "강아지 분양글 등록 실패");
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

	// 강아지 목록 출력 (startRow ~ endRow )
	public ArrayList<DogDto> listDog(int startRow, int endRow) {
		ArrayList<DogDto> dtos = new ArrayList<DogDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT ROWNUM RN, A.* FROM"
				+ "                (SELECT D.*, DB.DBREEDNAME FROM DOG D, DBREED DB WHERE D.DBREEDNO = DB.DBREEDNO ORDER BY DRDATE DESC ) A)"
				+ "        WHERE RN BETWEEN ? AND ? " + "            ORDER BY RN";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int dnum = rs.getInt("dnum");
				String dname = rs.getString("dname");
				String dgender = rs.getString("dgender");
				Date dbirth = rs.getDate("dbirth");
				int dprice = rs.getInt("dprice");
				int dbreedno = rs.getInt("dbreedno");
				String aid = rs.getString("aid");
				String dcontent = rs.getString("dcontent");
				String dimage1 = rs.getString("dimage1");
				String dimage2 = rs.getString("dimage2");
				String dimage3 = rs.getString("dimage3");
				String dimage4 = rs.getString("dimage4");
				String dimage5 = rs.getString("dimage5");
				String dip = rs.getString("dip");
				int dhit = rs.getInt("dhit");
				int dr_check = rs.getInt("dr_check");
				Date drdate = rs.getDate("drdate");
				String dbreedname = rs.getString("dbreedname");
				dtos.add(new DogDto(dnum, dname, dgender, dbirth, dprice, dbreedno, aid, dcontent, dimage1, dimage2,
						dimage3, dimage4, dimage5, dip, dhit, dr_check, drdate, dbreedname));
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

	// hit 높은순으로 강아지 목록 출력
	public ArrayList<DogDto> hitListDog() {
		ArrayList<DogDto> dtos = new ArrayList<DogDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT ROWNUM RN, A.* FROM"
				+ "                (SELECT * FROM DOG ORDER BY DHIT DESC ) A) D, DBREED DB"
				+ "    WHERE D.DBREEDNO = DB.DBREEDNO" + "        AND RN BETWEEN 1 AND 9 " + "            ORDER BY RN";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int dnum = rs.getInt("dnum");
				String dname = rs.getString("dname");
				String dgender = rs.getString("dgender");
				Date dbirth = rs.getDate("dbirth");
				int dprice = rs.getInt("dprice");
				int dbreedno = rs.getInt("dbreedno");
				String aid = rs.getString("aid");
				String dcontent = rs.getString("dcontent");
				String dimage1 = rs.getString("dimage1");
				String dimage2 = rs.getString("dimage2");
				String dimage3 = rs.getString("dimage3");
				String dimage4 = rs.getString("dimage4");
				String dimage5 = rs.getString("dimage5");
				String dip = rs.getString("dip");
				int dhit = rs.getInt("dhit");
				int dr_check = rs.getInt("dr_check");
				Date drdate = rs.getDate("drdate");
				String dbreedname = rs.getString("dbreedname");
				dtos.add(new DogDto(dnum, dname, dgender, dbirth, dprice, dbreedno, aid, dcontent, dimage1, dimage2,
						dimage3, dimage4, dimage5, dip, dhit, dr_check, drdate, dbreedname));
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

	// 글 상세보기 ( dnum으로 Dto 가져오기 ) hitup 없는
	public DogDto dogModifyContent(int dnum) {
		DogDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM DOG D, DBREED DB " + "    WHERE D.DBREEDNO = DB.DBREEDNO " + "        AND DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dname = rs.getString("dname");
				String dgender = rs.getString("dgender");
				Date dbirth = rs.getDate("dbirth");
				int dprice = rs.getInt("dprice");
				int dbreedno = rs.getInt("dbreedno");
				String aid = rs.getString("aid");
				String dcontent = rs.getString("dcontent");
				String dimage1 = rs.getString("dimage1");
				String dimage2 = rs.getString("dimage2");
				String dimage3 = rs.getString("dimage3");
				String dimage4 = rs.getString("dimage4");
				String dimage5 = rs.getString("dimage5");
				String dip = rs.getString("dip");
				int dhit = rs.getInt("dhit");
				int dr_check = rs.getInt("dr_check");
				Date drdate = rs.getDate("drdate");
				String dbreedname = rs.getString("dbreedname");
				dto = new DogDto(dnum, dname, dgender, dbirth, dprice, dbreedno, aid, dcontent, dimage1, dimage2,
						dimage3, dimage4, dimage5, dip, dhit, dr_check, drdate, dbreedname);
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

	// hitup 있는 상세보기
	public DogDto dogContent(int dnum) {
		DogDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM DOG D, DBREED DB " + "    WHERE D.DBREEDNO = DB.DBREEDNO " + "        AND DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hitUp(dnum);
				String dname = rs.getString("dname");
				String dgender = rs.getString("dgender");
				Date dbirth = rs.getDate("dbirth");
				int dprice = rs.getInt("dprice");
				int dbreedno = rs.getInt("dbreedno");
				String aid = rs.getString("aid");
				String dcontent = rs.getString("dcontent");
				String dimage1 = rs.getString("dimage1");
				String dimage2 = rs.getString("dimage2");
				String dimage3 = rs.getString("dimage3");
				String dimage4 = rs.getString("dimage4");
				String dimage5 = rs.getString("dimage5");
				String dip = rs.getString("dip");
				int dhit = rs.getInt("dhit");
				int dr_check = rs.getInt("dr_check");
				Date drdate = rs.getDate("drdate");
				String dbreedname = rs.getString("dbreedname");
				dto = new DogDto(dnum, dname, dgender, dbirth, dprice, dbreedno, aid, dcontent, dimage1, dimage2,
						dimage3, dimage4, dimage5, dip, dhit, dr_check, drdate, dbreedname);
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
	private void hitUp(int dnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE DOG SET DHIT= DHIT +1 " + "    WHERE DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dnum);
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
	public int totalDog() {
		int totalDog = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM DOG";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totalDog = rs.getInt(1);
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
		return totalDog;
	}

	// 강아지 분양글 수정
	public int updateDog(DogDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE DOG SET DNAME= ? , " + "                DGENDER= ? , " + "                 DBIRTH= ? ,"
				+ "                  DPRICE= ? ," + "                   DBREEDNO= ? , " + "                    AID= ? ,"
				+ "                     DCONTENT= ? ," + "                      DIMAGE1= ? ,"
				+ "                       DIMAGE2= ? ," + "                        DIMAGE3= ? ,"
				+ "                         DIMAGE4= ? ," + "                          DIMAGE5= ? ,"
				+ "                           DIP = ? " + "                    WHERE DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getDgender());
			pstmt.setDate(3, dto.getDbirth());
			pstmt.setInt(4, dto.getDprice());
			pstmt.setInt(5, dto.getDbreedno());
			pstmt.setString(6, dto.getAid());
			pstmt.setString(7, dto.getDcontent());
			pstmt.setString(8, dto.getDimage1());
			pstmt.setString(9, dto.getDimage2());
			pstmt.setString(10, dto.getDimage3());
			pstmt.setString(11, dto.getDimage4());
			pstmt.setString(12, dto.getDimage5());
			pstmt.setString(13, dto.getDip());
			pstmt.setInt(14, dto.getDnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "강아지 분양글 수정 성공" : "강아지 분양글 수정 실패");
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

	// 강아지 글 삭제하기
	public int deleteDog(int dnum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE DOG WHERE DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "강아지 분양글 삭제 성공" : "강아지 분양글 삭제 실패");
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
	public int reservationDog(int dnum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE DOG SET DR_CHECK=0 " + "    WHERE DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "강아지 예약 성공" : "강아지 예약 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage() + "강아지 예약 실패");
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
	public int cancellationDog(int dnum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE DOG SET DR_CHECK=0 " + "    WHERE DNUM= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "강아지 예약취소 성공" : "강아지 예약취소 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage() + "강아지 예약취소 실패");
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

	// 강아지 나이계산
	public int dogAge(Date dbirth, int dnum) {
		int age = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TRUNC(MONTHS_BETWEEN(SYSDATE, ?)/12)*12+MOD(CEIL(MONTHS_BETWEEN(SYSDATE, ?)),12) MONTH FROM DOG WHERE DNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, dbirth);
			pstmt.setDate(2, dbirth);
			pstmt.setInt(3, dnum);
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

	// 견종 불러오기
	public ArrayList<DbreedDto> breedList() {
		ArrayList<DbreedDto> dtos = new ArrayList<DbreedDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DBREEDNO, DBREEDNAME FROM DBREED";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int dbreedno = rs.getInt("dbreedno");
				String dbreedname = rs.getString("dbreedname");
				dtos.add(new DbreedDto(dbreedno, dbreedname));
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

	// 견종으로 검색하기
	public ArrayList<DogDto> serchDog(int dbreedno) {
		ArrayList<DogDto> dtos = new ArrayList<DogDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM DOG D, DBREED DB" + "    WHERE D.DBREEDNO = DB.DBREEDNO"
				+ "        AND D.DBREEDNO= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int dnum = rs.getInt("dnum");
				String dname = rs.getString("dname");
				String dgender = rs.getString("dgender");
				Date dbirth = rs.getDate("dbirth");
				int dprice = rs.getInt("dprice");
				String aid = rs.getString("aid");
				String dcontent = rs.getString("dcontent");
				String dimage1 = rs.getString("dimage1");
				String dimage2 = rs.getString("dimage2");
				String dimage3 = rs.getString("dimage3");
				String dimage4 = rs.getString("dimage4");
				String dimage5 = rs.getString("dimage5");
				String dip = rs.getString("dip");
				int dhit = rs.getInt("dhit");
				int dr_check = rs.getInt("dr_check");
				Date drdate = rs.getDate("drdate");
				String dbreedname = rs.getString("dbreedname");
				dtos.add(new DogDto(dnum, dname, dgender, dbirth, dprice, dbreedno, aid, dcontent, dimage1, dimage2,
						dimage3, dimage4, dimage5, dip, dhit, dr_check, drdate, dbreedname));
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
