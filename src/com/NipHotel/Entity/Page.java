package com.NipHotel.Entity;

import java.util.ArrayList;

/**
 * 实现分页显示的的类
 * 
 * @author Nipppppp  2019年8月22日08:53:47
 * 
 */
public class Page<T> {

	// 属性
	private int pageSize ; // 页面容量
	private int total;// 总页数
	private int currPage;// 当前页
	private int privPage;// 上一页
	private int nextPage;// 下一页
	private int allPage;// 总页数

	private ArrayList<T> list = new ArrayList<T>(); // 数据集合

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;

		// 算出总页数
		if (this.total % this.pageSize == 0) {
			this.allPage = this.total / this.pageSize;
		} else {
			this.allPage = this.total / this.pageSize + 1;

		}

	}

	public int getCurrPage() {
		return currPage;
	}

	/**
	 * 设置当前页
	 * 
	 * @param currPage
	 */
	public void setCurrPage(int currPage) {
		if (this.allPage == 1) { // 只有一页  <=
			this.privPage = 1;
			this.currPage = 1;
			this.nextPage = 1;
		} else if (currPage == 1) { // 在首页，第一页  <=
			this.privPage = 1;
			this.currPage = 1;
			this.nextPage = 2;
		} else if (currPage == this.allPage) { // 最后一页  >=
			this.privPage = this.allPage - 1;
			this.currPage = this.allPage;
			this.nextPage = this.allPage;
		} else { // 正常情况

			this.privPage = currPage - 1;
			this.currPage = currPage;
			this.nextPage = currPage + 1;

		}
	}

	public int getPrivPage() {
		return privPage;
	}

	/*public void setPrivPage(int privPage) {
		this.privPage = privPage;
	}*/

	public int getNewxtPage() {
		return nextPage;
	}

	/*public void setNewxtPage(int newxtPage) {
		this.newxtPage = newxtPage;
	}
*/
	public int getAllPage() {
		return allPage;
	}

	/*
	 * public void setAllPage(int allPage) { this.allPage = allPage; }
	 */
	public ArrayList<T> getList() {
		return list;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}

}
