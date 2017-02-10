package com.smartglossa.FileUploadAndDownload;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONObject;

public class FileClass {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	PreparedStatement ps=null;
	public FileClass()throws ClassNotFoundException,SQLException{
		openConnection();
		
		
	}
	public void addImage(FileItem image) throws SQLException,ClassNotFoundException, IOException{
	JSONObject result=new JSONObject();
	try {
		//String query="insert into image(image)values('"+image+"')";
		//stat.execute(query);
		ps = conn.prepareStatement("insert into image(image) values(?)");
		//ps.setString(2, uname);
		ps.setBinaryStream(1, image.getInputStream(), (int) image.getSize());
		ps.executeUpdate();
	} 
	finally {
		closeConnection();
	}

		
	}
	private void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FileUploadAndDownload", "root", "root");
		stat = conn.createStatement();
	}

	private void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stat != null) {
			stat.close();
		}
		if (rs != null) {
			rs.close();

		}
	}

}