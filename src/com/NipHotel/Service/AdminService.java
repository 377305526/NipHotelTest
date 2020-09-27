package com.NipHotel.Service;

import com.NipHotel.Dao.AdminDao;
import com.NipHotel.Entity.Admin;

public class AdminService {

	AdminDao ad  = new AdminDao();
	/**
	 * 管理员登录判断
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean isLogin(String id , String pwd)
	{
		Admin a = new Admin();
		a.setAdmin_id(id);
		a.setAdmin_Pwd(pwd);
		if(ad.isLogin(a)>0){
			return true ;
		}
		return false ;
		
	}
}
