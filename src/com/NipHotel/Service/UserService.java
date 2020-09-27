package com.NipHotel.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.NipHotel.Dao.UserDao;
import com.NipHotel.Entity.Orders;
import com.NipHotel.Entity.UserInfo;

public class UserService {
	
	
	UserDao ud = new UserDao();
	RoomService rs = new RoomService();
	
	/**
	 * 删除选定id的顾客
	 * @param id
	 * @return
	 * @throws NumberFormatException
	 */
	public boolean deleteUserInfo(int id) 
	{
	
		if(ud.deleteUserInfo(id)>0){
			return true ;
		}
		return false ;
		
		
	}
	
	
	
	public boolean deleteUserInfoByvipcard(int id) 
	{
	
		if(ud.deleteUserInfoByVipcard(id)>0){
			return true ;
		}
		return false ;
		
		
	}
	public boolean deleteUserInfoByRoomId(int id) 
	{
		
		if(ud.deleteUserInfoByRoomId(id)>0){
			return true ;
		}
		return false ;
		
		
	}
	/**
	 * 获取指定查询的顾客集合
	 * @param selectIndex
	 * @param txt
	 * @return
	 * @throws NumberFormatException
	 */
	public ArrayList<UserInfo> someUsers (int selectIndex , String txt)  throws NumberFormatException
	{
		if(selectIndex==2)
		{
			
			int a = Integer.parseInt(txt);
			return ud.someUsers(selectIndex, a);
		}
		else{
			return ud.someUsers(selectIndex, txt);
		}
		
	}
	/**
	 * 修改订单
	 * @param cOL
	 * @return
	 * @throws SQLException
	 */
	public boolean changeUserInfo(ArrayList<UserInfo> cOL) throws SQLException
	{
		for(int i = 0 ; i < cOL.size() ; i++)
		{
			if(rs.roomInfo(cOL.get(i).getRoom_Id())==null){
				return false ;
			}
		}
		if(ud.changeUserInfo(cOL)>0){
			return true ;
		}
		return false  ;
		
		
	}
	/**
	 * 获取最新一笔的顾客资料 去更新订单页面
	 * @return
	 */
	public UserInfo getUserId()
	{
		return ud.getId();
	}
	
	/**
	 * 添加顾客信息
	 * @param u
	 * @param i
	 * @return
	 */
	public boolean addUserInfo(UserInfo u ,int i)
	{
		boolean flag = false ;
		if(ud.addUseInfo(u,i)>0)
		{
			flag = true ;
		}
		
		return flag;
	}
	
	/**
	 * 获取所有的顾客信息集
	 * @return
	 */
	public ArrayList<UserInfo> getAllUser()
	{ 
		return ud.getAllUser();
	}
	
	
	/**
	 * 顾客分页查询
	 * @param thisPage
	 * @param pageSize
	 * @return
	 */
	public ArrayList<UserInfo> limitSelectOrders(int thisPage , int pageSize)
	{
		return ud.limitSelectUserInfo(thisPage, pageSize);
	}
	
}
