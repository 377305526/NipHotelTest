package com.NipHotel.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 获取对象
 * @author Administrator
 *
 * @param <T>传入的类型
 */
public interface GetEntity<T> {
	T getEntity(ResultSet rs) throws SQLException;

}
