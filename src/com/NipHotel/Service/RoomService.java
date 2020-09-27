package com.NipHotel.Service;


import java.sql.SQLException;
import java.util.ArrayList;
import com.NipHotel.Dao.RoomDao;
import com.NipHotel.Entity.Room;


public class RoomService {
	RoomDao rd=new RoomDao();
	
	
public Room  roomInfo(int btnName) throws SQLException{
	return rd.getRoom(btnName);
	
}

/**
 * 是否入住
 * @param roomId
 * @return
 */
public boolean isCheckIn(int roomId)
{
	Room a = new Room();
	a=rd.getRoom(roomId);
	
	if(a.getRoom_State().equals("已经入住")){
		return true ;
	}
	return false ;
	}
	
	
public ArrayList<Room> allRoom()
{
	
	return rd.getAllroom();
	}


/**
 * 更新为已入住
 * @param roomId
 * @return
 */
public boolean updateRoom(int roomId , int i)
{
	boolean flag = false ;
	if(rd.updataRoom(roomId , i)>0){
		flag = true ;
	}



	return flag ;
	}

/**
 * 获取空房
 * @return
 */
public ArrayList<Room> EmptyRoom()
{
	return rd.getEmptyRoom();
}
public ArrayList<Room> EmptyRoom(String type)
{
	return rd.getEmptyRoom(type);
}


public ArrayList<Room> EmptyRoomType(String roomType){
	return rd.getAllroom(roomType);
}


	/**
	 * 分页查询
	 * @param start
	 * @param size
	 * @return
	 */
	public ArrayList<Room> roomLimit(int thisPage , int pageSize)
	{
		return rd.limitSelectRoom(thisPage, pageSize);
	}
	
	/**
	 * 删除房间
	 * @param input
	 * @return
	 * @throws NumberFormatException
	 */
	public boolean deleteRoom(String input)throws NumberFormatException
	{
		int deleteId = Integer.parseInt(input);
		if(rd.deleteRoom(deleteId)>0){
			return true ;
		}
		return false ;
	}
	
	/**
	 * 修改房间
	 * @param rL
	 * @return
	 */
	public boolean updateRoom(ArrayList<Room> rL)
	{
		if(rd.changeRoom(rL)>0)
		{
			return true ;
		}
		return false ;
	}
	/**
	 * 添加房间
	 * @param r
	 * @return
	 */
	public boolean addRoom(Room r)
	{
		if(rd.addRoom(r)>0)return true;
		return false ;
		
	}
}
