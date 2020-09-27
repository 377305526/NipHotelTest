package com.NipHotel.Dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.NipHotel.Entity.UserInfo;


public class UserDao extends BaseDao<UserInfo> implements GetEntity<UserInfo>{

	
	ArrayList<UserInfo> uL = new ArrayList<UserInfo>();
	
	public UserInfo getEntity(ResultSet rs) throws SQLException {
		UserInfo u = new UserInfo();
		u.setUser_ID(rs.getInt("user_id"));
		u.setRoom_Id(rs.getInt("room_id"));
		u.setRemark(rs.getString("remark"));
		u.setUser_Name(rs.getString("user_name"));
		u.setUser_Identity(rs.getString("user_identity"));
		u.setUser_Sex(rs.getString("user_sex"));
		u.setUser_Tel(rs.getString("user_tel"));
		u.setVIP_ID(rs.getInt("vip_id"));
		return u;
	} 
	
	/**
	 * 获取所有顾客信息集
	 */
	public ArrayList<UserInfo> getAllUser()
	{
		
		String sql = "select * from userInfo ";

		return this.getAllInfo(sql, this);
	}

	
	
	/**
	 * 获取最新的顾客
	 * @return
	 */
	public UserInfo getId()
	{
		
	
		String sql = "select * from userinfo where user_id =(select max(User_ID) from userinfo)";
		return this.getOne(sql, this);
	}
	
	/**
	 * 添加入住顾客信息 可能会员号没有  1-有会员号 2-没有会员号
	 * @param u
	 * @param vipid
	 * @return 返回一个int类型知道有改变的行数就知道有没有添加进去
	 */
	public int addUseInfo(UserInfo u , int vipid)
	{
		
		String sql1 = "insert into userinfo values (null,?,?,?,?,?,?,?)";
		String sql2 = "insert into userinfo values (null,?,?,?,?,null,?,?)";
		if(vipid ==1)
		{
			
			return this.executeSQL(sql1,u.getUser_Name(), u.getUser_Sex(), u.getUser_Identity(), u.getUser_Tel(), u.getVIP_ID(), u.getRemark(), u.getRoom_Id());
		}
		
		if(vipid ==2)
		{
			
			return this.executeSQL(sql2,u.getUser_Name(), u.getUser_Sex(), u.getUser_Identity(), u.getUser_Tel(), u.getRemark(), u.getRoom_Id());
		}
		return 0 ;
	}
	
	

	/**
	 * 删除顾客
	 * @param order_id
	 * @return
	 */
	public int deleteUserInfo(int user_id)
	{
	
		String sql = "delete from userinfo where user_id = ?";
		return this.executeSQL(sql, user_id);
	}
	
	
	
	
	public int deleteUserInfoByVipcard(int id)
	{
	
		String sql = "delete a.* from userinfo  a inner join vip b  on a.vip_id = b.vip_card and b.vip_card = ?";
		return this.executeSQL(sql, id);
	}
	public int deleteUserInfoByRoomId(int id)
	{
		
		String sql = "delete a.* from userinfo  a inner join room b  on a.room_id = b.room_id and b.room_id = ?";
		return this.executeSQL(sql, id);
	}
	/**
	 * 条件查找
	 * @param selectIndex
	 * @param parameter
	 * @return
	 */
	public ArrayList<UserInfo> someUsers(int selectIndex , Object parameter)
	{
		String sql1 = "select * from userinfo where user_Name = ?";
		String sql2 = "select * from userinfo where user_Tel = ?";
		String sql3 = "select * from userinfo where vip_id = ?";
		String sql = "";
		uL.clear();
		switch(selectIndex)
		{
		case 0 :
			sql= sql1 ;
			break;
		case 1 :
			sql = sql2;
			break ;
		case 2 :
			sql = sql3;
			break;
		
		}
		return this.getAllInfo(sql, this, parameter);
		
	}
	/**
	 * 修改订单
	 * @param cOL
	 * @return
	 */
	public int  changeUserInfo(ArrayList<UserInfo> cOL)
	{
		
		String sql = "update userInfo set user_Name = ?,user_Sex = ? , user_tel= ? , remark = ?,vip_id = ? , room_id = ? where user_id = ?";
		int k = 0 ;
			for(int i = 0 ;  i < cOL.size(); i++)
			{
				String name =cOL.get(i).getUser_Name();
				String sex =cOL.get(i).getUser_Sex();
				String tel = cOL.get(i).getUser_Tel();
				String remark =cOL.get(i).getRemark();
				int Vipid = cOL.get(i).getVIP_ID();
				int roomId=  cOL.get(i).getRoom_Id();
				int userId =cOL.get(i).getUser_ID();
				k+=this.executeSQL(sql, name,sex,tel,remark,Vipid,roomId,userId);
			}
	return k ;
	}
	
	/**
	  * 分页查询
	  * @param thisPage
	  * @param pageSize
	  * @return
	  */
		public ArrayList<UserInfo> limitSelectUserInfo(int thisPage , int pageSize)
		{
			
			String sql = "select * from userinfo limit ?,?";
			return this.getAllInfo(sql, this, (thisPage-1)*pageSize , pageSize);
			
		}
		

	
}
