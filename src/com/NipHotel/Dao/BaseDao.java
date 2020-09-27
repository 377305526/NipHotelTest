package com.NipHotel.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.NipHotel.DB.ConnectionDB;


public class BaseDao<T> {
	
	/**
	 * 获取所有信息
	 * @param sql sql语句
	 * @param ge  传入的类型
	 * @param parameter sql语句中的参数
	 * @return
	 */
	public ArrayList<T> getAllInfo(String sql,GetEntity<T> ge,Object...parameter){
		ArrayList<T> l = new ArrayList<T>();  //返回时保存数据的集合
		Connection conn = ConnectionDB.getCon();	//获取链接
		
		PreparedStatement stmt = null;	//发送语句的对象
		ResultSet rs = null;
		
		try {
			//发送查询语句
			stmt = conn.prepareStatement(sql);
			//设置参数
			for(int i = 0;i < parameter.length;i++){
				stmt.setObject(i+1,  parameter[i]); 
			}
			rs = stmt.executeQuery();
			//取出数据
			while(rs.next()){
				T t = ge.getEntity(rs);
				l.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionDB.closeDB(rs, stmt, conn);
		}
		return l;	
		
	}
	/**
	 * 获取单个对象
	 * @param sql	sql语句
	 * @param ge	传入的对象
	 * @param parameter	sql语句中的参数
	 * @return
	 */
	public T getOne(String sql,GetEntity<T> ge,Object...parameter){
		Connection conn = ConnectionDB.getCon();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		T t= null;
		try {
			stmt = conn.prepareStatement(sql);
			for(int i = 0;i < parameter.length;i++){
				stmt.setObject(i+1,  parameter[i]); 
			} 
			rs = stmt.executeQuery();
			while(rs.next()){
				 t =  ge.getEntity(rs);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
		}
		return t;	
	}
	/**
	 * 执行增删改操作
	 * @param sql
	 * @param parameter
	 * @return
	 */
	public int executeSQL(String sql,Object...parameter){
		Connection conn = ConnectionDB.getCon();
		PreparedStatement stmt =null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			for(int i = 0;i < parameter.length;i++){
				stmt.setObject(i+1,  parameter[i]); 
			}
			row = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeDB(null,stmt,conn);
		}
		return row;		
	}

}
