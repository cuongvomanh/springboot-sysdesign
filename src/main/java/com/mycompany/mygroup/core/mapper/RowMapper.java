package com.mycompany.mygroup.core.mapper;
import java.sql.ResultSet;

public interface RowMapper<T> {
	T mapRow(ResultSet rs);
}
