package com.lec.petshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.petshop.dto.CatZimDto;
import com.lec.petshop.dto.ZimDto;

public class CatZimDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int ZIM_CHECK =1;
	public static final int ZIM_UNCHECK =0;
	
	private static CatZimDao instance = new CatZimDao();
	public static CatZimDao getInstance() {
		return instance;
	}
	
	private CatZimDao() {}
	
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
	
//	 찜 하기 누르면 정보가 테이블로 들어옴
	public int insertZim(String mid, int cnum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CZIM(ZNO, MID, CNUM ) VALUES (CZIM_SEQ.NEXTVAL, ? , ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setInt(2, cnum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
//	 찜 눌렀는지 안눌렀는지 여부 
	public int zimCheck(String mid, int cnum) {
		int result = ZIM_UNCHECK;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM CZIM WHERE MID=? AND CNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setInt(2, cnum);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
//	 찜 취소
	public int deleteZim(String mid, int cnum) {
		int result = ZIM_UNCHECK;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM CZIM WHERE MID= ? AND CNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setInt(2, cnum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	
//	 내가 찜한 리스트 
	public ArrayList<CatZimDto> myZim(String mid){
		ArrayList<CatZimDto> zims = new ArrayList<CatZimDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT CNUM, CNAME, CIMAGE1, CBREEDNAME FROM (SELECT * FROM CAT C, CBREED CB WHERE C.CBREEDNO = CB.CBREEDNO)" + 
				"    WHERE CNUM IN (SELECT CNUM FROM CZIM WHERE MID= ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cnum = rs.getInt("cnum");
				String cname = rs.getString("cname");
				String cimage1 = rs.getString("cimage1");
				String cbreedname = rs.getString("cbreedname");
				zims.add(new CatZimDto(cnum, cname, cimage1, cbreedname));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return zims;
	}
}
