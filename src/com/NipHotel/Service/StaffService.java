package com.NipHotel.Service;

import java.util.ArrayList;

import com.NipHotel.Dao.staffDao;
import com.NipHotel.Entity.Staff;

public class StaffService {

	staffDao sd = new staffDao();
	
	public boolean addStaff(Staff s)
	{
		if(sd.addStaff(s)>0)return true ;
		return false ;
	}
	/**
	 * 获取所有staff集
	 * @return
	 */
	public ArrayList<Staff> getAllStaff()
	{
		return sd.getAllStaff();
	}
	
	/**
	 * 更新工作人员
	 * @param sL
	 * @return
	 */
	public boolean updateStaff(ArrayList<Staff> sL)
	{
		if(sd.changeStaff(sL)>0)
		{
			return true ;
		}
		return false ;
			
	}
	
	/**
	 * 指定获取某位工作人员信息
	 * @param txt
	 * @param seletItem
	 * @return
	 */
	public Staff getOneStaff(String txt , int seletItem)
	{
		return sd.getSomeStaff(txt, seletItem);
	}

	/**
	 * 删除某id工作人员
	 * 
	 * @param staffId
	 * @return
	 */
	public boolean deleteStaff(int staffId)
	{
		if(sd.deleteStaff(staffId)>0)return true;
		return false ;
	}
}
