package com.NipHotel.DB;

/**
 * 	创建数据库
 *  @author   Nipppppp  2019年8月21日17:54:19
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	static final String DRIVER = "com.mysql.jdbc.Driver"; // DRIVER
	static final String URL = "jdbc:mysql://localhost:3306/hotel"; // 找到数据库
	static final String USER = "root"; //
	static final String PWD = "123456"; //

	/**
	 * 获取连接对象
	 * 
	 * @return
	 */
	public static Connection getCon() {
		Connection conn = null;
		// 1.加载驱动
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2.获取数据库连接对象
		return conn;
	}

	/**
	 * 关闭资源
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void closeDB(ResultSet rs, Statement stmt, Connection conn) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
