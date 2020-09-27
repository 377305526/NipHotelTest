package com.NipHotel.Dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.NipHotel.Entity.Orders;
import com.NipHotel.Entity.UserInfo;
/**
 * 订单操作类  
 * @author Nipppppp
 *
 */
public class OrdersDao extends BaseDao<Orders> implements GetEntity<Orders>{

	 ArrayList<Orders> oL = new ArrayList<Orders>();
	 
	 public Orders getEntity(ResultSet rs) throws SQLException {
		    Orders o = new Orders();
		    o.setUser_ID(rs.getInt("user_id"));
		 	o.setOrder_ID(rs.getInt("order_id"));
			o.setStaff_ID(rs.getInt("staff_id"));
			o.setRoom_ID(rs.getInt("room_id"));
			o.setOrder_Time(rs.getString("order_time"));
			o.setOrder_Front(rs.getDouble("order_front"));
			o.setRemark(rs.getString("remark"));
			
			return o;
		} 
	/**
	 * 根据开房用户增加订单信息
	 * @param u
	 * @return
	 */
	public int addOrders(UserInfo u ,int staffId)
	{
			
			 String sql = "insert into orders values (null,?,?,?,?,100,?)";
			 SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			 String time =df.format(new java.util.Date()).toString();
			 return	this.executeSQL(sql,u.getUser_ID(),staffId,u.getRoom_Id(),time, u.getRemark());		
				
		 }
		
	/**
	 *获取所有订单的集合
	 * @return
	 */
	public ArrayList<Orders> getAllOrders()
	{
		
		 String sql = "select * from orders order by order_id desc ";
		 return this.getAllInfo(sql, this);
	}
					
	/**
	 * 条件查找
	 * @param selectIndex
	 * @param parameter
	 * @return
	 */
	public ArrayList<Orders> someOrders(int selectIndex , Object parameter)
	{
		String sql1 = "select * from orders where Room_Id = ?";
		String sql2 = "select a.* from orders a  inner join userinfo b  on a.user_id = b.user_id and b.user_name = ? ";
		String sql3 = "select a.* from orders a  inner join userinfo b  on a.user_id = b.user_id and b.user_tel = ?";
		String sql = "";
		String sql4 = "select a.* from orders a  inner join userinfo b  on a.user_id = b.user_id and b.vip_id = ?";
		oL.clear();
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
		case 3 :
			sql = sql4;
		
		}
		return this.getAllInfo(sql, this, parameter);
		
	}
	
	/**
	 * 删除订单
	 * @param order_id
	 * @return
	 */
	public int deleteOrders(int order_id)
	{
	
		String sql = "delete from orders where order_id = ?";
		return this.executeSQL(sql, order_id);
	}
	public int deleteOrdersByStaff(int staffId)
	{
		
		String sql = "delete from orders where staff_id= ?";
		return this.executeSQL(sql, staffId);
	}
	
	
	public int deleteOrdersByVipId(int Vip_id)
	{
	
		String sql = "delete a.* from orders  a inner join userinfo b  on a.user_Id = b.user_id and b.vip_id = ? ";
		return this.executeSQL(sql, Vip_id);
	}
	
	public int deleteOrdersByRoomId(int roomId)
	{
		
		String sql = "delete a.* from orders  a inner join room b  on a.room_id = b.room_id and b.room_id = ? ";
		return this.executeSQL(sql, roomId);
	}
	
	/**
	 * 修改订单
	 * @param cOL
	 * @return
	 */
	public int  changeOrders(ArrayList<Orders> cOL)
	{
		
		String sql = "update orders set staff_id = ?,room_id = ? , order_time =? , order_front = ? , remark = ? where order_id = ?";
		int k = 0 ;
			for(int i = 0 ;  i < cOL.size(); i++)
			{
				int staffId =cOL.get(i).getStaff_ID();
				int roomId =cOL.get(i).getRoom_ID();
				String time= cOL.get(i).getOrder_Time();
				Double front =cOL.get(i).getOrder_Front();
				String remark =cOL.get(i).getRemark();
				int orderId =cOL.get(i).getOrder_ID();
				k+=this.executeSQL(sql, staffId,roomId,time,front,remark,orderId);
			}
			return k ;
	}
	
	 /**
	  * 分页查询
	  * @param thisPage
	  * @param pageSize
	  * @return
	  */
		public ArrayList<Orders> limitSelectOrders(int thisPage , int pageSize)
		{
		
			String sql = "select * from orders limit ?,?";
			return this.getAllInfo(sql, this, (thisPage-1)*pageSize ,pageSize);
		}
		
	}
	
	
