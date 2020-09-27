package com.NipHotel.Entity;

public class UserInfo {
	private int user_ID;// 用户编号
	private String user_Name;// 用户姓名
	private String user_Sex;// 用户性别
	private String user_Identity;// 用户身份证号
	private String user_Tel;// 用户电话号
	private int vip_ID;// 会员编号
	private String remark;// 备注
	private int Room_Id ;//房间号
	
	public int getRoom_Id() {
		return this.Room_Id;
	}

	public void setRoom_Id(int room_Id) {
		Room_Id = room_Id;
	}

	public int getUser_ID() {
		return this.user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public String getUser_Name() {
		return this.user_Name;
	}

	public void setUser_Name(String user_Name) {
		 this.user_Name=user_Name;
	}

	public String getUser_Sex() {
		return this.user_Sex;
	}

	public void setUser_Sex(String user_Sex) {
		this.user_Sex = user_Sex;
	}

	public String getUser_Identity() {
		return this.user_Identity;
	}

	public void setUser_Identity(String user_Identity) {
		this.user_Identity = user_Identity;
	}

	public String getUser_Tel() {
		return this.user_Tel;
	}

	public void setUser_Tel(String user_Tel) {
		this.user_Tel = user_Tel ;
	}

	public int getVIP_ID() {
		return this.vip_ID;
	}

	public void setVIP_ID(int vIP_ID) {
		vip_ID = vIP_ID;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
