package com.hr.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.hr.db.model.Employee;

public class Dac {
	private static Dac instance = null;
	private JdbcTemplate jdbcTemplate = null;
	private Properties propSQL = new Properties();
	private Dac() {
		try {
			String sqlFileName = getClass().getName();
			sqlFileName = sqlFileName.replace('.', '/');
			/* /com/hr/db.Dac.xml */
			sqlFileName = "/"+sqlFileName+".xml";
			propSQL.loadFromXML(getClass().getResourceAsStream(sqlFileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static Dac getInstance() {
		if (instance == null)
			instance = new Dac();
		return instance;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void addEmployee(Employee emp) {
		try {
			jdbcTemplate.execute(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					String sql = propSQL.getProperty("addEmployee");
					return conn.prepareStatement(sql, new String[] { "employee_id" });
				}
			}, new PreparedStatementCallback<Integer>() {
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, emp.getFirstName());
					ps.setString(2, emp.getLastName());
					ps.setString(3, emp.getEmail());
					ps.setString(4, emp.getPhoneNumber());
					ps.setDate(5, new java.sql.Date(emp.getHireDate().getTime()));
					ps.setString(6, emp.getJobId());
					ps.setObject(7, emp.getSalary());
					ps.setObject(8, emp.getCommissionPct());
					ps.setObject(9, emp.getManagerId());
					ps.setObject(10, emp.getDepartmentId());
					int ret = ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next())
						emp.setEmployeeId(rs.getInt(1));
					return ret;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public List<Employee> allEmployees(){
		String sql = propSQL.getProperty("allEmployees");
		return jdbcTemplate.query(sql ,
				new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	public Employee getEmployee(int employee_id) {
		String sql = propSQL.getProperty("getEmployee");
		List<Employee> empList = jdbcTemplate.query(sql ,
				new Object[] {employee_id},
				new BeanPropertyRowMapper<Employee>(Employee.class));
		if(empList.size()==1)
			return empList.get(0);
		return null;
	}

	
	public boolean chgEmployee(Employee emp) {
		String sql = propSQL.getProperty("chgEmployee");
		if(1==jdbcTemplate.update(sql, new Object[] {
				emp.getFirstName(),
				emp.getLastName(),
				emp.getEmail(),
				emp.getPhoneNumber(),
				new java.sql.Date(emp.getHireDate().getTime()),
				emp.getJobId(),
				emp.getSalary(),
				emp.getCommissionPct(),
				emp.getManagerId(),
				emp.getDepartmentId(),
				emp.getEmployeeId()
				
		})) return true;
		
		return false;
	}
	
	public boolean delEmployee(int employee_id) {
		String sql = propSQL.getProperty("delEmployee");
		if(1==jdbcTemplate.update(sql, new Object[] {
				employee_id	
		})) return true;
		
		return false;
	}
}
