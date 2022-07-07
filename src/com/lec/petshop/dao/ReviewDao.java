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

import com.lec.petshop.dto.ReviewDto;

public class ReviewDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	private static ReviewDao instance = new ReviewDao();

	public static ReviewDao getInstance() {
		return instance;
	}

	private ReviewDao() {
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
	public ArrayList<ReviewDto> listReview(int startRow, int endRow) {
		ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM (SELECT R.*, MNAME FROM REVIEW R, MEMBER M WHERE R.MID= M.MID ORDER BY RGROUP DESC, RSTEP ) A )" + 
				"          WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rnum = rs.getInt("rnum");
				String mid = rs.getString("mid");
				String aid = rs.getString("aid");
				String rtitle = rs.getString("rtitle");
				String rcontent = rs.getString("rcontent");
				Date rrdate = rs.getDate("rrdate");
				int rhit = rs.getInt("rhit");
				int rgroup = rs.getInt("rgroup");
				int rstep = rs.getInt("rstep");
				int rindent = rs.getInt("rindent");
				String rip = rs.getString("rip");
				String rfilename1 = rs.getString("rfilename1");
				String rfilename2 = rs.getString("rfilename2");
				String rfilename3 = rs.getString("rfilename3");
				String mname = rs.getString("mname");
				dtos.add(new ReviewDto(rnum, mid, aid, rtitle, rcontent, rrdate, rhit, rgroup, rstep, rindent, rip, rfilename1, rfilename2, rfilename3, mname));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"목록 출력 실패");
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
	public int getReviewTotalCnt() {
		int totalCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM REVIEW";
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
	public int writeReview(String mid, String aid,String rtitle, String rcontent, String rip, String rfilename1, String rfilename2, String rfilename3) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW (RNUM, MID, AID, RTITLE, RCONTENT, RGROUP, RSTEP, RINDENT, RIP, RFILENAME1, RFILENAME2, RFILENAME3 )" + 
				"    VALUES (REVIEW_SEQ.NEXTVAL, ?,?, ?, ?, REVIEW_SEQ.CURRVAL, 0, 0, ?, ?,?, ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, aid);
			pstmt.setString(3, rtitle);
			pstmt.setString(4, rcontent);
			pstmt.setString(5, rip);
			pstmt.setString(6, rfilename1);
			pstmt.setString(7, rfilename2);
			pstmt.setString(8, rfilename3);
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
	public void hitUp(int rnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET RHIT = RHIT + 1" + 
				"    WHERE RNUM = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rnum);
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
	public ReviewDto contentReview(int rnum) {
		ReviewDto dto = null;
		hitUp(rnum);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, MNAME FROM REVIEW R, MEMBER M WHERE R.MID = M.MID AND  RNUM =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mid = rs.getString("mid");
				String aid = rs.getString("aid");
				String rtitle = rs.getString("rtitle");
				String rcontent = rs.getString("rcontent");
				Date rrdate = rs.getDate("rrdate");
				int rhit = rs.getInt("rhit");
				int rgroup = rs.getInt("rgroup");
				int rstep = rs.getInt("rstep");
				int rindent = rs.getInt("rindent");
				String rip = rs.getString("rip");
				String rfilename1 = rs.getString("rfilename1");
				String rfilename2 = rs.getString("rfilename2");
				String rfilename3 = rs.getString("rfilename3");
				String mname = rs.getString("mname");
				dto = new ReviewDto(rnum, mid, aid, rtitle, rcontent, rrdate, rhit, rgroup, rstep, rindent, rip, rfilename1, rfilename2, rfilename3, mname);
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

	// 글 수정할떄 dto 가져오기  (hitup) 없는거 
	public ReviewDto modifyGetDto(int rnum) {
		ReviewDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, MNAME FROM REVIEW R, MEMBER M WHERE R.MID = M.MID AND  RNUM =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mid = rs.getString("mid");
				String aid = rs.getString("aid");
				String rtitle = rs.getString("rtitle");
				String rcontent = rs.getString("rcontent");
				Date rrdate = rs.getDate("rrdate");
				int rhit = rs.getInt("rhit");
				int rgroup = rs.getInt("rgroup");
				int rstep = rs.getInt("rstep");
				int rindent = rs.getInt("rindent");
				String rip = rs.getString("rip");
				String rfilename1 = rs.getString("rfilename1");
				String rfilename2 = rs.getString("rfilename2");
				String rfilename3 = rs.getString("rfilename3");
				String mname = rs.getString("mname");
				dto = new ReviewDto(rnum, mid, aid, rtitle, rcontent, rrdate, rhit, rgroup, rstep, rindent, rip, rfilename1, rfilename2, rfilename3, mname);
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
	private void stepA(int rgroup, int rstep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET RSTEP = RSTEP +1" + 
				"    WHERE RGROUP=? AND RSTEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rgroup);
			pstmt.setInt(2, rstep);
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
	public int replyReview(String mid, String rtitle, String rcontent, String rip, int rgroup, int rstep, int rindent,
			String rfilename1, String rfilename2, String rfilename3) {
		int result = FAIL;
		stepA(rgroup, rstep);
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW (RNUM, MID,  RTITLE, RCONTENT, RGROUP, RSTEP, RINDENT, RIP, RFILENAME1, RFILENAME2, RFILENAME3 )" + 
				"    VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ? , ?, ?, ? , ?, ?, ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, rtitle);
			pstmt.setString(3, rcontent);
			pstmt.setInt(4, rgroup);
			pstmt.setInt(5, rstep + 1); // 꼭 +1 쓰기
			pstmt.setInt(6, rindent + 1);
			pstmt.setString(7, rip);
			pstmt.setString(8, rfilename1);
			pstmt.setString(9, rfilename2);
			pstmt.setString(10, rfilename3);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "답변글 성공" : "답변글 실패 ");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "ReviewReply 답변글 실패");
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
	public int modifyReview(String rtitle, String rcontent, String rip, String rfilename1, String rfilename2,
			String rfilename3, int rnum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET RTITLE = ?," + 
				"                        RCONTENT = ?," + 
				"                            RIP = ?," + 
				"                                RFILENAME1 = ? ," + 
				"                                    RFILENAME2 = ? ," + 
				"                                        RFILENAME3 = ? ," + 
				"                                            RRDATE = SYSDATE" + 
				"                            WHERE RNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rtitle);
			pstmt.setString(2, rcontent);
			pstmt.setString(3, rip);
			pstmt.setString(4, rfilename1);
			pstmt.setString(5, rfilename2);
			pstmt.setString(6, rfilename3);
			pstmt.setInt(7, rnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "후기글 수정 성공" : "글 수정  실패 ");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "후기글 수정 실패");
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
	public int deleteReview(int rgroup, int rstep, int rindent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM REVIEW WHERE RGROUP = ? AND (RSTEP>=? AND" + 
				"    RSTEP<(SELECT NVL(MIN(RSTEP),9999) FROM REVIEW WHERE RGROUP=? AND RSTEP>? AND RINDENT<=?))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,rgroup);
			pstmt.setInt(2,rstep);
			pstmt.setInt(3,rgroup);
			pstmt.setInt(4,rstep);
			pstmt.setInt(5,rindent);		
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
