package com.NipHotel.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.NipHotel.Dao.OrdersDao;
import com.NipHotel.Entity.Orders;
import com.NipHotel.Entity.UserInfo;

public class OrdersService {
	
	
	OrdersDao od = new OrdersDao();
	RoomService rs = new RoomService();
	
	
	
	/**
	 * 添加订单信息
	 * @param u
	 * @return
	 */
	public boolean addOrders(UserInfo u , int staffId)
	{
		
		if(od.addOrders(u,staffId)>0)
		{
			return true ;
		}
		return false ;
	}
	/**
	 * 获取所有订单集合
	 * @return
	 */
	public ArrayList<Orders> getAllOrders()
	{
		return od.getAllOrders();
	}
	
	/**
	 * 获取指定查询的订单集合
	 * @param selectIndex
	 * @param txt
	 * @return
	 * @throws NumberFormatException
	 */
	public ArrayList<Orders> someOrders (int selectIndex , String txt)  throws NumberFormatException
	{
		if(selectIndex==0)
		{
			
			int a = Integer.parseInt(txt);
			return od.someOrders(selectIndex, a);
		}else if (selectIndex == 3){
			int a = Integer.parseInt(txt);
			return od.someOrders(selectIndex, a);
		}
		else{
			return od.someOrders(selectIndex, txt);
		}
		
		
	}
	
	/**
	 * 删除选定id的订单
	 * @param id
	 * @return
	 * @throws NumberFormatException
	 */
	public boolean deleteOrder(int id) 
	{
	
		if(od.deleteOrders (id)>0){
			return true ;
		}
		return false ;
		
		
	}
	public boolean deleteOrderByStaff(int staffId) 
	{
		
		if(od.deleteOrdersByStaff (staffId)>0){
			return true ;
		}
		return false ;
		
		
	}
	
	
	public boolean deleteOrderByVipId(int id) 
	{
	
		if(od.deleteOrdersByVipId (id)>0){
			return true ;
		}
		return false ;
		
		
	}
	public boolean deleteOrderByRoomId(int roomId) 
	{
		
		if(od.deleteOrdersByRoomId(roomId)>0){
			return true ;
		}
		return false ;
		
		
	}
	
	/**
	 * 修改订单信息
	 * @param cOL
	 * @return
	 * @throws SQLException
	 */
	public boolean changeOrders(ArrayList<Orders> cOL) throws SQLException
	{
		for(int i = 0 ; i < cOL.size() ; i++)
		{
			if(rs.roomInfo(cOL.get(i).getRoom_ID())==null){
				return false ;
			}
		}
		if(od.changeOrders(cOL)>0){
			return true ;
		}
		return false  ;
		
		
	}
	/**
	 * 分页查询
	 * @param thisPage
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Orders> limitSelectOrders(int thisPage , int pageSize)
	{
		return od.limitSelectOrders(thisPage, pageSize);
	}

}