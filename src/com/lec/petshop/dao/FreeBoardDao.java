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

import com.lec.petshop.dto.FreeBoardDto;

public class FreeBoardDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	private static FreeBoardDao instance = new FreeBoardDao();

	public static FreeBoardDao getInstance() {
		return instance;
	}

	private FreeBoardDao() {
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

	// 글 목록 출력
	public ArrayList<FreeBoardDto> listBoard(int startRow, int endRow) {
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM" + 
				"    (SELECT ROWNUM RN, A.* FROM (SELECT F.*, MNAME FROM FREEBOARD F, MEMBER M WHERE F.MID= M.MID ORDER BY FGROUP DESC, FSTEP ) A )" + 
				"          WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fnum = rs.getInt("fnum");
				String mid = rs.getString("mid");
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				Date frdate = rs.getDate("frdate");
				int fhit = rs.getInt("fhit");
				int fgroup = rs.getInt("fgroup");
				int fstep = rs.getInt("fstep");
				int findent = rs.getInt("findent");
				String fip = rs.getString("fip");
				String ffilename1 = rs.getString("ffilename1");
				String ffilename2 = rs.getString("ffilename2");
				String ffilename3 = rs.getString("ffilename3");
				String mname = rs.getString("mname");
				dtos.add(new FreeBoardDto(fnum, mid, ftitle, fcontent, frdate, fhit, fgroup, fstep, findent, fip,
						ffilename1, ffilename2, ffilename3, mname));
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
		return dtos;
	}

	// 글 전체 개수 가져오기
	public int getBoardTotalCnt() {
		int totalCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM FREEBOARD";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totalCnt = rs.getInt(1);
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
		return totalCnt;
	}

	// 원글 쓰기
	public int writeFreeBoard(String mid, String ftitle, String fcontent, String fip, String ffilename1,
			String ffilename2, String ffilename3) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD( FNUM, MID, FTITLE, FCONTENT, FGROUP, FSTEP, FINDENT, FIP, FFILENAME1, FFILENAME2, FFILENAME3 )"
				+ "    VALUES ( FREE_SEQ.NEXTVAL, ? , ? , " + "                            ? , FREE_SEQ.CURRVAL, 0, 0,"
				+ "                                    ?, ? , ? , ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, ftitle);
			pstmt.setString(3, fcontent);
			pstmt.setString(4, fip);
			pstmt.setString(5, ffilename1);
			pstmt.setString(6, ffilename2);
			pstmt.setString(7, ffilename3);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
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

	// 4.조회수 올리기
	public void hitUp(int fnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FHIT = FHIT + 1" + "    WHERE FNUM = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
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
	}

	// 글 상세보기
	public FreeBoardDto contentView(int fnum) {
		FreeBoardDto dto = null;
		hitUp(fnum);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT FB.*, MNAME FROM FREEBOARD FB, MEMBER M WHERE FB.MID = M.MID AND  FNUM =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mid = rs.getString("mid");
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				Date frdate = rs.getDate("frdate");
				int fhit = rs.getInt("fhit");
				int fgroup = rs.getInt("fgroup");
				int fstep = rs.getInt("fstep");
				int findent = rs.getInt("findent");
				String fip = rs.getString("fip");
				String ffilename1 = rs.getString("ffilename1");
				String ffilename2 = rs.getString("ffilename2");
				String ffilename3 = rs.getString("ffilename3");
				String mname = rs.getString("mname");
				dto = new FreeBoardDto(fnum, mid, ftitle, fcontent, frdate, fhit, fgroup, fstep, findent, fip,
						ffilename1, ffilename2, ffilename3, mname);
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
		return dto;
	}

	// 글 수정할떄 dto 가져오기
	public FreeBoardDto modifyView_replyView(int fnum) {
		FreeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT FB.*, MNAME FROM FREEBOARD FB, MEMBER M WHERE FB.MID = M.MID AND  FNUM =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mid = rs.getString("mid");
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				Date frdate = rs.getDate("frdate");
				int fhit = rs.getInt("fhit");
				int fgroup = rs.getInt("fgroup");
				int fstep = rs.getInt("fstep");
				int findent = rs.getInt("findent");
				String fip = rs.getString("fip");
				String ffilename1 = rs.getString("ffilename1");
				String ffilename2 = rs.getString("ffilename2");
				String ffilename3 = rs.getString("ffilename3");
				String mname = rs.getString("mname");
				dto = new FreeBoardDto(fnum, mid, ftitle, fcontent, frdate, fhit, fgroup, fstep, findent, fip,
						ffilename1, ffilename2, ffilename3, mname);
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
		return dto;
	}

	// step A
	private void stepA(int bgroup, int bstep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FSTEP = FSTEP +1" + "    WHERE FGROUP=? AND FSTEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bgroup);
			pstmt.setInt(2, bstep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "step A");
		} finally {
			try {
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
	}

	// 답변글 쓰기
	public int reply(String mid, String ftitle, String fcontent, String fip, int fgroup, int fstep, int findent,
			String ffilename1, String ffilename3, String ffilename2) {
		int result = FAIL;
		stepA(fgroup, fstep);
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD(FNUM, MID, FTITLE, FCONTENT, FGROUP, FSTEP, FINDENT, FIP, FFILENAME1, FFILENAME2, FFILENAME3 )"
				+ "    VALUES ( FREE_SEQ.NEXTVAL, ? , ? , ? , ? , ? , ? , ?, ?, ?, ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, ftitle);
			pstmt.setString(3, fcontent);
			pstmt.setInt(4, fgroup);
			pstmt.setInt(5, fstep + 1); // 꼭 +1 쓰기
			pstmt.setInt(6, findent + 1);
			pstmt.setString(7, fip);
			pstmt.setString(8, ffilename1);
			pstmt.setString(9, ffilename2);
			pstmt.setString(10, ffilename3);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "답변글 성공" : "답변글 실패 ");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "reply 답변글 실패");
		} finally {
			try {
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

	// 9. 글 수정
	public int modifyBoard(String ftitle, String fcontent, String fip, String ffilename1, String ffilename2,
			String ffilename3, int fnum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FTITLE = ? ," + "                        FCONTENT = ? ,"
				+ "                            FIP = ? ," + "                                FFILENAME1 = ?,"
				+ "                                    FFILENAME2 = ?,"
				+ "                                        FFILENAME3 = ?,"
				+ "                                            FRDATE = SYSDATE"
				+ "                            WHERE FNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ftitle);
			pstmt.setString(2, fcontent);
			pstmt.setString(3, fip);
			pstmt.setString(4, ffilename1);
			pstmt.setString(5, ffilename2);
			pstmt.setString(6, ffilename3);
			pstmt.setInt(7, fnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글 수정 성공" : "글 수정  실패 ");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 수정 실패");
		} finally {
			try {
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
	
	// 글삭제 
	public int delete(int fgroup, int fstep, int findent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FREEBOARD WHERE FGROUP = ? AND (FSTEP>=? AND " + 
				"    FSTEP<(SELECT NVL(MIN(FSTEP),9999) FROM FREEBOARD WHERE FGROUP=? AND FSTEP>? AND FINDENT<=?))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,fgroup);
			pstmt.setInt(2,fstep);
			pstmt.setInt(3,fgroup);
			pstmt.setInt(4,fstep);
			pstmt.setInt(5,findent);		
			result = pstmt.executeUpdate();
			System.out.println(result >= SUCCESS ? result+" 개 글 삭제 성공" : "글 삭제  실패 ");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 삭제 실패");
		} finally {
			try {
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
	
	

}
