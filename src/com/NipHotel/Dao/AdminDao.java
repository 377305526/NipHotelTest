package com.NipHotel.Dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import com.NipHotel.Entity.Admin;

public class AdminDao extends BaseDao<Admin>implements GetEntity<Admin>{
	
	 public Admin getEntity(ResultSet rs) throws SQLException {
		 Admin o = new Admin();
		 o.setAdmin_id(rs.getString("admin_id"));
		 o.setAdmin_Pwd(rs.getString("admin_pwd"));
			return o;
		} 

	/**
	 * 管理员登录判断
	 * @param ad
	 * @return
	 */
	public int isLogin(Admin a)
	{
	
		String sql = "select * from admin where admin_id = ? and admin_pwd = ?" ;
		if(this.getOne(sql, this,  a.getAdmin_id(),a.getAdmin_Pwd()) != null)
		{
			return 1 ;
		}
		return 0 ;
	}
}
