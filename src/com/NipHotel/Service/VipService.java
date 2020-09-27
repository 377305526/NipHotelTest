package com.NipHotel.Service;

import java.util.ArrayList;
import com.NipHotel.Dao.VipDao;
import com.NipHotel.Entity.VIP;

/**
 * 对Vip信息处理的服务层
 * 
 * @author   Nip
 *
 */
public class VipService {

	
	VIP v = new VIP();
	VipDao vd = new VipDao();
	ArrayList<VIP> l = new ArrayList<VIP>();

	
	/**
	 * 获取所有会员信息
	 * @return
	 */
	public ArrayList<VIP> allVipInfo()
	{
		return vd.getAll();
		
	}
	public ArrayList<VIP> allVipInfo(int start,int size)
	{
		return vd.getAll(start,size);
		
	}
	
	/**
	 * 判断卡号是否存在
	 * @param r
	 * @return
	 */
	public boolean isCardOk(int r){
		boolean flag=false;
		if(vd.pick(r)>0)
		{
			flag = true ;
		}
		return flag;
	}
	
	/**
	 * 返回选项的会员信息集
	 * @param selectedItem
	 * @param txt
	 * @param vL
	 * @return
	 */
	public ArrayList<VIP> oneVipInfo(int selectedItem , String txt)
	{
		return vd.oneVipInfo(selectedItem, txt);
	}
	
	/**
	 * 添加会员信息
	  * @param aL
	 * @return
	 */
	public boolean addVipInfo(ArrayList<VIP> aL)
	{
		boolean flag = false ;
		int i = 0 ;
		//传进来的是VIP集合 循环一个一个添加进数据库
		for(VIP _a:aL)
		{
			i+=vd.addVIP(_a);
		}
		if(i>0){
			flag = true ;
		}
		return flag ;
		
	}
	
	/**
	 * 更新会员信息
	 * @param v
	 * @return
	 */
	public boolean updateVip(VIP v)
	{
		boolean flag = false ;
		if(vd.updateVIP(v)>0){
			flag = true ;
		}
		return flag;
		
	}
	
	/**
	 * 删除VIP
	 * @return
	 */
	public boolean delVIP(int vip_card){
		if(vd.delVIP(vip_card) > 0){
		return true;
		}
		return false;
	}

}
