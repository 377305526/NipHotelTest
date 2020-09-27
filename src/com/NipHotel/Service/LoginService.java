package com.NipHotel.Service;
import com.NipHotel.Dao.staffDao;

public class LoginService {
	
	staffDao sd = new staffDao();
	
	
	/**
	 * 判断是否登录
	 * @param isOk
	 * @return
	 */
	public boolean isLogin(int staffId , String staffPwd)
	{
		boolean flag = false ;
		int i = 0 ;
		i =sd.isOk(staffId, staffPwd);
		if(i==1)
		{
			flag = true;
		}
		
		return flag ;
	}

}
