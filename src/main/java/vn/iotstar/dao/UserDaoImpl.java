package vn.iotstar.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import vn.iotstar.models.User;

import vn.iotstar.config.DBconnectMySQL;

public class UserDaoImpl implements UserDao{
	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	@Override
	public User get(String username) {
		String sql="SELECT * FROM Users WHERE username=?";
		try
		{
			conn=new DBconnectMySQL().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			while(rs.next())
			{
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void insert(User user) {
		String sql = "INSERT INTO Users(email,username,fullname,password,avatar,roleid,phone,createdDate) VALUES (?,?,?,?,?,?,?,?)";
		try 
		{
			conn = new DBconnectMySQL().getConnection();
			ps = conn.prepareStatement(sql);
			System.out.println("hello connection");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAvatar());
			ps.setInt(6,user.getRoleid());
			ps.setString(7,user.getPhone());
			ps.setDate(8, (Date) user.getCreatedDate());
			ps.executeUpdate();
		} 
		catch (Exception e) {e.printStackTrace();}

		
	}
	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from Users where email = ?";
		try 
		{
			conn = new DBconnectMySQL().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				duplicate = true;
			}
			ps.close();
			conn.close();
		} 
		catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from Users where username = ?";
		try 
		{
			conn = new DBconnectMySQL().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
			duplicate = true;
			}
			ps.close();
			conn.close();
		} 
		catch (Exception ex) {}
		return duplicate;

	}
	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from Users where phone = ?";
		try 
		{
			conn = new DBconnectMySQL().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if (rs.next()) {
			duplicate = true;
			}
			ps.close();
			conn.close();
		} 
		catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public void modified(String username, String password) {
		String query="UPDATE Users SET password = ? WHERE username = ?";
		try
		{
			conn=new DBconnectMySQL().getConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, password);
			ps.setString(2, username);
			ps.executeUpdate();
			rs=ps.executeQuery();
			ps.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void updatefullname(String username, String fullname) {
		String query="UPDATE Users SET fullname = ? WHERE username = ?";
		try
		{
			conn=new DBconnectMySQL().getConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, fullname);
			ps.setString(2, username);
			ps.executeUpdate();
			rs=ps.executeQuery();
			System.out.print(fullname);
			ps.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void updatephone(String username, String phone) {
		String query="UPDATE Users SET phone = ? WHERE username = ?";
		try
		{
			conn=new DBconnectMySQL().getConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, phone);
			ps.setString(2, username);
			ps.executeUpdate();
			rs=ps.executeQuery();
			ps.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void updateavatar(String username, String avatar) {
		String query="UPDATE Users SET avatar = ? WHERE username = ?";
		try
		{
			conn=new DBconnectMySQL().getConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, "/HelloWorld/views/img/"+avatar);
			ps.setString(2, username);
			ps.executeUpdate();
			rs=ps.executeQuery();
			ps.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}