package com.NipHotel.Entity;

public class Room {
	private int room_ID;// 房间编号
	private String room_Type;// 房间类型
	private double room_price;// 房间价格
	private double room_Size;// 房间大小
	private String room_State;// 房间状态
	private String remark;// 备注
	private int setRoom_ID;

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public String getRoom_Type() {
		return room_Type;
	}

	public void setRoom_Type(String room_Type) {
		this.room_Type = room_Type;
	}

	public double getRoom_price() {
		return room_price;
	}

	public void setRoom_price(double room_price) {
		this.room_price = room_price;
	}

	public double getRoom_Size() {
		return room_Size;
	}

	public void setRoom_Size(double room_Size) {
		this.room_Size = room_Size;
	}

	public String getRoom_State() {
		return room_State;
	}

	public void setRoom_State(String room_State) {
		this.room_State = room_State;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSetRoom_ID() {
		return setRoom_ID;
	}

	public void setSetRoom_ID(int setRoom_ID) {
		this.setRoom_ID = setRoom_ID;
	}
}
