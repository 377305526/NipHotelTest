package com.NipHotel.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.NipHotel.DB.ConnectionDB;
import com.NipHotel.Entity.Room;


public class RoomDao extends BaseDao<Room> implements GetEntity<Room> {
	/**
	 * 查询所有房间信息
	 * @return
	 */
	public ArrayList<Room> getAllroom(){
		
			String sql = "select * from room order by Room_id";
			
			return this.getAllInfo(sql, this);		
	
	}
	/**
	 * 查询单个房间的信息
	 * @param room_id	房间ID号
	 * @return	所查房间的对象
	 */
	public Room getRoom(int room_id){
		String sql = "select * from room where room_id="+room_id;
		
		return this.getOne(sql,this);		
	}
	
	/**
	 * 获取所有空房信息 返回空房
	 * @return
	 */
	public ArrayList<Room> getEmptyRoom()
	{
		String sql = "select * FROM room where Room_State ='未入住'";
		return this.getAllInfo(sql, this);		
	}
	public ArrayList<Room> getEmptyRoom(String roomType)
	{
		 String param = "%" + roomType + "%";
		String sql = "select * FROM room where Room_State ='未入住' and room_type like ? ";
		return this.getAllInfo(sql, this,param);		
	}
	
	
	public Room getEntity(ResultSet rs) throws SQLException {
		Room r = new Room();
		r.setRoom_ID(rs.getInt("room_id"));
		r.setRemark(rs.getString("remark"));
		r.setRoom_price(rs.getDouble("room_price"));
		r.setRoom_Size(rs.getDouble("room_Size"));
		r.setRoom_State(rs.getString("room_State"));
		r.setRoom_Type(rs.getString("room_Type"));
		
		return r;
	} 

	/***
	 * 房间分页
	 * @param thisPage
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Room> limitSelectRoom(int thisPage , int pageSize)
	{
		
		String sql = "select * from room limit ?,?";
		return this.getAllInfo(sql, this, (thisPage-1)*pageSize , pageSize);
		
	}
	
	/**
	 * 删除房间
	 * @param input
	 * @return
	 */
	public int deleteRoom(int input)
	{
		String sql = "delete from room where room_id = ?";
		 return executeSQL(sql,input);
	}

	
	/**
	 * 对房间信息进行修改
	 * @param rL
	 * @return
	 */
	public int changeRoom(ArrayList<Room> rL)
	{
		String sql = "update room set Room_id = ? , room_Type = ? , Room_price = ? , room_size = ? ,room_state = ? , remark = ? where room_id = ?";

		int k = 0 ;
			for(int i = 0 ; i < rL.size() ; i++)
			{
				int id =rL.get(i).getRoom_ID();
				String type =rL.get(i).getRoom_Type();
				Double price = rL.get(i).getRoom_price();
				Double size =rL.get(i).getRoom_Size();
				String state = rL.get(i).getRoom_State();
				String remark = rL.get(i).getRemark();
				k+=this.executeSQL(sql, id,type,price,size,state,remark,id);		
			}
				
		return k ;
	}
	
	/**
	 * 添加房间
	 * @param r
	 * @return
	 */
	public int addRoom(Room r)
	{
		String sql = "insert into room values(?,?,?,?,?,?)";
		return this.executeSQL(sql,r.getRoom_ID(),r.getRoom_Type(),r.getRoom_price(),r.getRoom_Size(),r.getRoom_State(),r.getRemark());
	}
	

	/**
	 * 房间信息的更新
	 * @param RoomId
	 * @param i  退房或者入住改变房间的备注 更新按钮状态和颜色 
	 * @return
	 */
	public int updataRoom(int RoomId , int i){
		java.sql.Connection conn = ConnectionDB.getCon();
		String sql = "" ;
		if(i==1)
		{
			sql = "update Room set Room_State = '已经入住' where Room_ID="+RoomId  ;
		}
		else{
			sql = "update Room set Room_State = '未入住' where Room_ID="+RoomId ;
		}
		PreparedStatement stmt =null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			row = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeDB(null,stmt,conn);
		}
		return row;		
	
	}
	/**
	 * 模糊查询所有房间类型的信息
	 * @return
	 */
	public ArrayList<Room> getAllroom(String roomType){
		
		
			String sql = "select * from room where room_Type like ?";

			 String param = "%" + roomType + "%";
			 
			 return this.getAllInfo(sql, this, param);
	}
	

	
}
