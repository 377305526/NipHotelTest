package com.NipHotel.Entity;

public class VIP {
	private int vip_ID;// 会员编号
	private int vip_Card;// 会员卡号
	private String vip_Name;// 会员名字
	private String vip_BD;// 会员生日
	private int vip_Level;// 会员等级
	private String remark;// 备注
	private String vip_Tel;//会员手机号

	public String getVIP_Tel() {
		return vip_Tel;
	}

	public void setVIP_Tel(String vip_Tel) {
		this.vip_Tel = vip_Tel;
	}

	public int getVIP_ID() {
		return vip_ID;
	}

	public void setVIP_ID(int vIP_ID) {
		this.vip_ID = vIP_ID;
	}

	public int getVIP_Card() {
		return vip_Card;
	}

	public void setVIP_Card(int vIP_Card) {
		this.vip_Card = vIP_Card;
	}

	public String getVIP_Name() {
		return vip_Name;
	}

	public void setVIP_Name(String vIP_Name) {
		this.vip_Name = vIP_Name;
	}

	public String getVIP_BD() {
		return vip_BD;
	}

	public void setVIP_BD(String vIP_BD) {
		this.vip_BD = vIP_BD;
	}

	public int getVIP_Level() {
		return vip_Level;
	}

	public void setVIP_Level(int vIP_Level) {
		this.vip_Level = vIP_Level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
