package kr.co.itcen.emaillist1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.emaillist1.vo.EmaillistVo;

public class EmaillistDao {
	public Boolean insert(EmaillistVo emailVo) {
		Boolean result = false;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "insert into emaillist values(null, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, emailVo.getFirstName());
			pstmt.setString(2, emailVo.getLastName());
			pstmt.setString(3, emailVo.getEmail());
			
			int count = pstmt.executeUpdate();
			
			result = (count == 1); 
			
			stmt = connection.createStatement();
			// mysql에만 있는 함수
			rs = stmt.executeQuery("select last_insert_id()");
			if(rs.next()) {
				Long no = rs.getLong(1);
				emailVo.setNo(no);
			}
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void delete() {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			
			String sql = "delete from emaillist";
			pstmt = connection.prepareStatement(sql);
						
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<EmaillistVo> getList() {
		List<EmaillistVo> result = new ArrayList<EmaillistVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "select no, first_name, last_name, email from emaillist order by no desc";
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				
				EmaillistVo emailVo = new EmaillistVo();
				emailVo.setNo(no);
				emailVo.setFirstName(firstName);
				emailVo.setLastName(lastName);
				emailVo.setEmail(email);
				
				result.add(emailVo);
			}
						
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.1.84:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch(ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		} 
		return connection;
	}
	
}
