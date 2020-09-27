package com.NipHotel.Dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.NipHotel.Entity.VIP;


/**
 * 会员信息操作
 * @author   Nippppp
 *
 */
public class VipDao extends BaseDao<VIP> implements GetEntity<VIP>{

	ArrayList<VIP> l = new ArrayList<VIP>();
	
	
	public VIP getEntity(ResultSet rs) throws SQLException {
		VIP v= new VIP();
		v.setRemark(rs.getString("remark"));
		v.setVIP_BD(rs.getString("VIP_BD"));
		v.setVIP_Card(rs.getInt("VIP_Card"));
		v.setVIP_ID(rs.getInt("VIP_ID"));
		v.setVIP_Level(rs.getInt("VIP_Level"));
		v.setVIP_Name(rs.getString("VIP_Name"));
		v.setVIP_Tel(rs.getString("VIP_Tel"));
		
		return v;
	} 
	
 /**
  * 提供会员卡号删除会员
  * @param VIP_Card
  * @return
  */
 public int delVIP(int VIP_Card){

	 String sql = "delete from VIP where VIP_Card=?";
	 return this.executeSQL(sql, VIP_Card);
 }
 /**
  * 查找是否有该会员卡号 
  * @param r
  * @return
  */
 public int pick(int r){
		
		String sql="select * from VIP where vip_card = ? ";
		if( this.getOne(sql, this, r)!=null)
		{
			return 1 ;
		}
		return 0 ;
 }


	/**
	 * 查询所有会员信息 
	 * @return 会员的集合 
	 */
	public ArrayList<VIP> getAll()
	{
		
		String sql = "select * from vip";
		return this.getAllInfo(sql, this);		
		
	}
	
	 
	 
	 
	/**
	 * 添加会员信息 
	 * @param vipp 传进的是单笔的会员实体
	 * @return
	 */
	 public int addVIP(VIP v) {

		 String sql = "insert into VIP values (null,?,?,?,?,?,?)";
		 return this.executeSQL(sql, v.getVIP_Card(), v.getVIP_Name(), v.getVIP_Tel(), v.getVIP_BD(), v.getVIP_Level(), v.getRemark());
		 }
				
	 
	 
	/**
	 * 修改会员信息
	 * @param v
	 * @return
	 */
	 public int updateVIP(VIP v) {

		
		String sql = "UPDATE VIP SET VIP_Card=?,VIP_Name=?,VIP_Tel= ?,VIP_BD= ?,Remark= ? WHERE VIP_ID= ?";
		return this.executeSQL(sql, v.getVIP_Card(), v.getVIP_Name(),v.getVIP_Tel(),v.getVIP_BD(),v.getRemark(),v.getVIP_ID());
		
	 }
		

	
	
	/**
	 *  * 查找是否存在会员集合
	 * @param selectedItem 0-卡号查找 1-姓名查找 2-电话号码查找
	 * @param txt 可能是输入的值可能是卡号姓名或者电话号码 string 需要转成int 的要转型
	 * @param vL 传进来的所有会员数组 进行遍历查询
	 * @return
	 */
	public ArrayList<VIP> oneVipInfo(int selectedItem , String txt)
	{
		String sql1 = "select * from VIP where vip_card = ?" ;
		String sql2 = "select * from VIP where vip_Name = ?" ;
		String sql3 = "select * from VIP where vip_Tel = ?" ;
		String sql = "";
		switch(selectedItem)
		{
		case 0 : sql = sql1;
		int card = Integer.parseInt(txt);
		return this.getAllInfo(sql, this,card );
		case 1 : sql = sql2 ; return this.getAllInfo(sql, this, txt);
		case 2 : sql = sql3 ; return this.getAllInfo(sql, this, txt);
		}
		return null;
		
	
	}
	
	
	
	//????
	public ArrayList<VIP> getAll(int thisPage,int pageSize)
	{
		
			String sql = "select * from vip limit ? , ? ";
			return this.getAllInfo(sql, this, (thisPage-1)*pageSize,pageSize);
			
		
	}
	
	
	 
}