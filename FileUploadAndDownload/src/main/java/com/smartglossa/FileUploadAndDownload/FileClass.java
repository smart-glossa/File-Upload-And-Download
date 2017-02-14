package com.smartglossa.FileUploadAndDownload;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
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
		ps = conn.prepareStatement("insert into images(image) values(?)");
		//ps.setString(2, uname);
		ps.setBinaryStream(1, image.getInputStream(), (int) image.getSize());
		ps.executeUpdate();
	} 
	finally {
		closeConnection();
	}

		
	}
	
	public JSONArray getAll() throws SQLException {
		JSONArray result = new JSONArray();
		try {
			String query = "select * from images";
			rs = stat.executeQuery(query);
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("imageId", rs.getInt("imageId"));
				obj.put("image", rs.getString("image"));
				result.put(obj);

			}

		} finally {
			closeConnection();

		}
		return result;

	}

	private void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FileUpload", "root", "root");
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