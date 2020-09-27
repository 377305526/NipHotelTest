package com.NipHotel.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.NipHotel.Entity.Staff;


/**
 * 员工操作类 
 * @author Nipppppp 
 *
 */
public class staffDao extends BaseDao<Staff> implements GetEntity<Staff>
{

	/**
	 * 获取所有staff
	 * @return
	 */
	public ArrayList<Staff> getAllStaff()
	{
		String sql = "select * from staff";
		return this.getAllInfo(sql, this);
	}
	
	public Staff getEntity(ResultSet rs) throws SQLException {
		Staff s = new Staff();
		s.setStaff_ID(rs.getInt("staff_id"));
		s.setStaff_Name(rs.getString("staff_name"));
		s.setRemark(rs.getString("remark"));
		s.setStaff_PWD(rs.getString("staff_pwd"));
		s.setStaff_Sex(rs.getString("staff_sex"));
		s.setStaff_Tel(rs.getString("staff_tel"));
		
		return s;
	} 
	
	/**
	 * 登录判断
	 * @param staffId
	 * @param staffPwd
	 */

	public int isOk(int staffId , String staffPwd)
	{
	
		String sql = "select * from staff where staff_id = ? and staff_pwd = ?" ;
		if(this.getOne(sql, this, staffId,staffPwd)!=null)return 1;
		return 0 ;
	}
	
	/**
	 * 添加staff
	 * @param sL
	 * @return
	 */
	public int addStaff(Staff s)
	{
	
		String sql="insert into staff values(?,?,?,?,?,?)";
		
		return this.executeSQL(sql, s.getStaff_ID(),s.getStaff_Name(),s.getStaff_Sex(),s.getStaff_Tel(),s.getStaff_PWD(),s.getRemark());
	}
	
	/**
	 * 修改staff
	 * @param sL
	 * @return
	 */
	public int changeStaff(ArrayList<Staff> sL )
	{
		
		String sql="update staff set staff_id = ? , staff_name = ? , staff_sex = ? , staff_tel = ? , staff_pwd = ? , remark = ? where staff_id = ?";
		int k = 0 ; 
		for(int i = 0 ; i < sL.size() ; i++)
		{
			int id = sL.get(i).getStaff_ID();
			String name = sL.get(i).getStaff_Name();
			String sex = sL.get(i).getStaff_Sex();
			String tel = sL.get(i).getStaff_Tel() ; 
			String pwd = sL.get(i).getStaff_PWD() ;
			String remark =  sL.get(i).getRemark() ;	
			k +=this.executeSQL(sql, id , name , sex , tel , pwd , remark , id);
		}
				return k ;
				
		
	}
	
	/**
	 * 删除id
	 * @param staffId
	 * @return
	 */
	public int deleteStaff(int staffId)
	{

		String sql= "delete from staff where staff_id = ?" ;
		return this.executeSQL(sql, staffId);
	}
	
	/**
	 * 查询某位工作人员
	 * @param txt
	 * @param selectItem
	 * @return
	 */
	public Staff getSomeStaff(String txt , int selectItem)
	{
		String sql1 = "select * from staff where staff_name = ?" ;
		String sql2 = "select * from staff where staff_Tel = ?" ;
		String sql3 = "select * from staff where staff_id = ?" ;
		String sql = "";
		switch(selectItem)
		{
		case 0 :sql = sql1 ;break ;
		case 1 :sql = sql2 ;break ;
		case 2 :sql = sql3 ;break ;
		}
		return this.getOne(sql, this, txt);
	}
	
	}

