package com.NipHotel.Dao;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.NipHotel.Entity.Room;
import com.NipHotel.Entity.VIP;
import com.NipHotel.Service.RoomService;
import com.NipHotel.Service.VipService;

public class PageDao {
	private String[] tColumns = {"房间编号", "房间类型", "房间价格", "房间大小", "房间状态","备注"};
	/**
	 * VIP页码及分页查询模块
	 * @param page	起始页
	 * @param size	页面容量
	 * @return
	 */
	public TableModel loadpage(int page,int size){
		VipService vs = new VipService();		
		ArrayList<VIP> al = new ArrayList<VIP>();	
		int start = page -1;
		al = vs.allVipInfo(start,size);
		Object[][] info = new Object[al.size()][7];
		for (int i = 0; i < info.length; i++) {
			VIP v = al.get(i); 
			info[i][0] = v.getVIP_ID();
			info[i][1] = v.getVIP_Card();
			info[i][2] = v.getVIP_Name();
			info[i][3] = v.getVIP_BD();
			info[i][4] = v.getVIP_Level();
			info[i][5] = v.getVIP_Tel();
			info[i][6] = v.getRemark();
		}
		TableModel dataModel = new DefaultTableModel(info, tColumns);
		
		return dataModel;
		
		
	}
	/**
	 * VIP获取分页总页数
	 * @param size	页面容量
	 * @return
	 */
	public int getPage(int size){
		VipService vs = new VipService();
		ArrayList<VIP> al = new ArrayList<VIP>();
		al = vs.allVipInfo();
		int allpage;
		if(al.size()%size == 0){
			allpage = al.size()/size;
		}else{
			allpage = al.size()/size+1;
		}
		return allpage;		
	}

	/**
	 * Room分页模块
	 * @param page
	 * @param size
	 * @return
	 */
	public TableModel roompage(int page,int size){
		RoomService rs = new RoomService();		
		ArrayList<Room> al = new ArrayList<Room>();	
		int start = page -1;
		//al = rs.allRoomInfo(page,size);
		Object[][] info = new Object[al.size()][6];
		for (int i = 0; i < info.length; i++) {
			Room r = al.get(i); 
			info[i][0]= r.getRoom_ID();
			info[i][2]= r.getRoom_price();
			info[i][3]= r.getRoom_Size();
			info[i][4]= r.getRoom_State();
			info[i][1]= r.getRoom_Type();
			info[1][5]= r.getRemark();
		}
		TableModel dataModel = new DefaultTableModel(info, tColumns);
		
		return dataModel;
		
		
	}
	/**
	 * Room总页数查询
	 * @param size
	 * @return
	 */
	public int roomAllPage(int size){
		RoomService rs = new RoomService();
		ArrayList<Room> al = new ArrayList<Room>();
		//al = rs.allRoomInfo();
		int allpage;
		if(al.size()%size == 0){
			allpage = al.size()/size;
		}else{
			allpage = al.size()/size+1;
		}
		return allpage;		
	}

}
