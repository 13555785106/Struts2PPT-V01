package com.hr.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOracleTest01 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "123456");
			stmt = conn.prepareStatement("SELECT * FROM employees WHERE salary < ? AND salary >? ");
			stmt.setDouble(1, 15000);
			stmt.setDouble(2, 10000);
			rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null)
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
