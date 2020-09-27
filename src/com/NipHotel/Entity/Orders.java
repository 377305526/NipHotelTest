package com.NipHotel.Entity;

public class Orders {
	private int order_ID;// 订单编号
	private int user_ID;// 用户编号
	private int staff_ID;// 员工编号
	private int room_ID;// 房间编号
	private String order_Time;// 订单时间
	private double order_Front;// 订单押金
	private String remark;// 备注

	public int getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public int getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public String getOrder_Time() {
		return order_Time;
	}

	public void setOrder_Time(String order_Time) {
		this.order_Time = order_Time;
	}

	public double getOrder_Front() {
		return order_Front;
	}

	public void setOrder_Front(double order_Front) {
		this.order_Front = order_Front;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
