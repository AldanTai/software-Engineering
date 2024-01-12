package LoginSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Image.*;

import java.util.ArrayList;
import java.util.Base64;


public class mySQLSaveUsers {
	
	//玩家狀態列表
	public String name;
	public String password;
	public String email;
	public String team;
	public String photo;
	public String selfIntroduction;
	public int win = 0;
	public int lose = 0;
	private boolean logincheck;
	
	//確認狀態
	private boolean usercheck = true;//確認是否有此使用者
	private boolean PWcheck = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驅動程式類別");
			e.printStackTrace();
			}
	}
	
	
	
	//登入與確認註冊有無被使用
	public String login(String database,String userName, String Password){
		String check = "OK";
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM "+database);
			while(resultSet.next()) {
				//獲得欄位名稱"q"的值
				if(userName.equals(resultSet.getString("name") )) {
					usercheck = false;
					
					//提供值
						name=userName;
						email=resultSet.getString("email");
						team=resultSet.getString("team");
						selfIntroduction=resultSet.getString("selfIntroduction");
						photo=resultSet.getString("photo");
						win=resultSet.getInt("win");
						lose=resultSet.getInt("lose");
						
					if(Password.equals(resultSet.getString("password") )) {
						PWcheck = false;
						
						
						password=Password;
						statement.execute("UPDATE user SET login = 1 WHERE name = '"+name+"'");
						System.out.println(resultSet.getBoolean("login"));
			        	
						break;
					}
				}
			}
			if(usercheck) {
				check="ID";
				return check;
			}
			else {
				if(PWcheck) {
					check="PW";
					return check;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//確認登入
	public boolean checklogin(){
		
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
			while(resultSet.next()) {
				if(resultSet.getBoolean("login")) {
					name=resultSet.getString("Name");
					password=resultSet.getString("Password");
					email=resultSet.getString("email");
					team=resultSet.getString("team");
					selfIntroduction=resultSet.getString("selfIntroduction");
					photo=resultSet.getString("photo");
					win=resultSet.getInt("win");
					lose=resultSet.getInt("lose");
					logincheck=resultSet.getBoolean("login");
					break;
				}
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}
		
		return logincheck;
	}
	
	
	//註冊帳號並將內容輸入進去
	public void Register(String userName, String password, String email,String team, String photo, String formyself , int i) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO user ( name, Password, email , team , photo ,selfIntroduction, win ,lose, login)"
					+ " VALUES ( '"+userName+"', '"+password+"', '"+email+"', '"+team+"', '"+photo+"', '"+formyself+"', '"+win+"', '"+lose+"', "+i+");");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//檢查名稱信箱
	public boolean checkUser(String database , String name, String email) {
		boolean check=false;
		login(database,name,"");
		if(email.equals(this.email)) {//確認信箱內容是否正確
			System.out.println("信箱驗證通過");
			check=true;
		}
		return check;
	}
	
	//修改密碼
	public void changepassword(String userName,String password) {
		//更改密碼狀態
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("UPDATE user SET password ='"+password+"' WHERE name = '"+userName+"'");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//修改個資
	public void changeself(String user, String email,String team,String photo,String formyself) {
		login("user",user,"");
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			
			if(!email.equals(this.email) && email != null) {
				this.email=email;
				statement.execute("UPDATE user SET email ='"+email+"' WHERE name = '"+user+"'");
			}
			if(!team.equals(this.team) && team != null) {
				this.team=team;
				statement.execute("UPDATE user SET team ='"+team+"' WHERE name = '"+user+"'");
			}
			if(!photo.equals(this.photo) && photo != null) {
				this.photo=photo;
				statement.execute("UPDATE user SET photo ='"+photo+"' WHERE name = '"+user+"'");
			}
			if(!formyself.equals(this.selfIntroduction) && formyself != null) {
				this.selfIntroduction=formyself;
				statement.execute("UPDATE user SET selfIntroduction ='"+formyself+"' WHERE name = '"+user+"'");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//登出將所有資料刪除
 	public void Logout(String name) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
			
			statement.execute("UPDATE user SET login = 0 WHERE name = '"+name+"'");
			logincheck =resultSet.getBoolean("login");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		name=null;
		password=null;
		email=null;
		team=null;
		selfIntroduction=null;
		photo=null;
		win=0;
		lose=0;
	}
	
	//提供資料夾位置
	private Connection getConnection() throws SQLException {
		// String serverName = "140.127.74.170"; //伺服器網址
		String serverName = "localhost:3306"; //伺服器網址
		String database = "db";
		String user = "root";
		String password = "12345678";
		String url = "jdbc:mysql://" + serverName + "/" + database; //JDBC連線網址
		return DriverManager.getConnection(url, user, password);
	}
	
	
}
