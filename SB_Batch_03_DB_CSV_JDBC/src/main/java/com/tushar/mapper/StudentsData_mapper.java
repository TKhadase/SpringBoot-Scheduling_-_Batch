/**
 * 
 */
package com.tushar.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tushar.model.Students;

public class StudentsData_mapper implements RowMapper<Students> {
	
public StudentsData_mapper() {
	System.out.println("StudentsData_mapper.mapRow()"); 
}
	
	@Override
	public Students mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Students(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
	}

}
