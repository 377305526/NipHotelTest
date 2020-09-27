package com.NipHotel.Entity;

public class Staff {
	private int staff_ID;// 员工编号
	private String staff_Name;// 员工姓名
	private String staff_Sex;// 员工性别
	private String staff_Tel;// 员工手机号
	private String staff_PWD;// 员工密码
	private String remark;// 备注

	public int getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}

	public String getStaff_Name() {
		return staff_Name;
	}

	public void setStaff_Name(String staff_Name) {
		this.staff_Name = staff_Name;
	}

	public String getStaff_Sex() {
		return staff_Sex;
	}

	public void setStaff_Sex(String staff_Sex) {
		this.staff_Sex = staff_Sex;
	}

	public String getStaff_Tel() {
		return staff_Tel;
	}

	public void setStaff_Tel(String staff_Tel) {
		this.staff_Tel = staff_Tel;
	}

	public String getStaff_PWD() {
		return staff_PWD;
	}

	public void setStaff_PWD(String staff_PWD) {
		this.staff_PWD = staff_PWD;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
