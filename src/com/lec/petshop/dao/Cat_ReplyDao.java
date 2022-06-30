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

import com.lec.petshop.dto.Cat_ReplyDto;

public class Cat_ReplyDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	
	private static Cat_ReplyDao instance = new Cat_ReplyDao();

	public static Cat_ReplyDao getInstance() {
		return instance;
	}

	private Cat_ReplyDao() {
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
	
	// 고양이 분양글 댓글 쓰기 
	public int replyCat(Cat_ReplyDto dto) {
		int result = SUCCESS;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CAT_REPLY (RNO, CNUM, MID, REPLY_CONTENT, RIP ) " + 
				"    VALUES (CREPLY_SEQ.NEXTVAL, ? , ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getCnum());
			pstmt.setString(2, dto.getMid());
			pstmt.setString(3, dto.getReply_content());
			pstmt.setString(4, dto.getRip());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "고양이 답변글 작성 성공 " : "고양이 답변글 작성 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"고양이 답변글 작성 실패 "+dto);
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
	
	// 분양글 댓글 출력하기 
		public ArrayList<Cat_ReplyDto> listCReply(int cnum, int startRow, int endRow){
			ArrayList<Cat_ReplyDto> dtos = new ArrayList<Cat_ReplyDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT C.* FROM(SELECT ROWNUM RN, A.* FROM " + 
					"                (SELECT * FROM CAT_REPLY WHERE CNUM= ? ORDER BY RDATE) A) C " + 
					"    WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cnum);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int rn = rs.getInt("rn");  // 댓글 출력 번호가 될아이.
					int rno = rs.getInt("rno");
					String mid = rs.getString("mid");
					String reply_content = rs.getString("reply_content");
					Date rdate = rs.getDate("rdate");
					String rip = rs.getString("rip");
					dtos.add(new Cat_ReplyDto(rn, rno, cnum, mid, reply_content, rdate, rip));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage() + cnum+" 번 댓글 출력");
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
		
		// 댓글 수정
		public int updateCReply(String reply_content, String rip, int rno) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE CAT_REPLY SET REPLY_CONTENT= ? ," + 
					"                        RIP = ? " + 
					"                            WHERE RNO= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,reply_content);
				pstmt.setString(2, rip);
				pstmt.setInt(3, rno);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "댓글 수정 성공" : "댓글 수정 실패 ");
			} catch (SQLException e) {
				System.out.println(e.getMessage() + rno+" 번 댓글 수정 실패");
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
		
		// 댓글 삭제
		public int deleteCReply(int rno) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE CAT_REPLY WHERE RNO= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rno);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "댓글 삭제 성공" : "댓글 삭제 실패 ");
			} catch (SQLException e) {
				System.out.println(e.getMessage() + rno+" 번 댓글 삭제실패");
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
