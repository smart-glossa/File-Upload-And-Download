package com.smartglossa.FileUploadAndDownload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		String operation = request.getParameter("operation");
		if (operation.equals("add")) {
			JSONObject result = new JSONObject();
		try {
			List<FileItem> items = sfu.parseRequest(request);
			FileItem image = (FileItem) items.get(0);
			FileClass file1 = new FileClass();
			file1.addImage(image);
			result.put("status", 1);
		} catch (Exception e) {
			result.put("status", 0);
			e.printStackTrace();
		}
		response.getWriter().println(result);

	}
		
		else if(operation.equals("getAll")){
			DiskFileItemFactory factory1 = new DiskFileItemFactory();
			ServletFileUpload sfu1 = new ServletFileUpload(factory);
			JSONArray result=new JSONArray();
			try {
				List<FileItem> items = sfu.parseRequest(request);
				FileItem image = (FileItem) items.get(0);
				FileClass file=new FileClass();
				result=file.getAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().println(result);
		}
	
}
}
