package com.upload;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/Upload")
@MultipartConfig(maxFileSize = 1024*1024*2, location = "c:\\upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 파일명 얻기
	private String getFileName(Part part) {
		String fileName = null;
		String contentDispositionHeader = 
				part.getHeader("content-disposition");
		
		String[] elements = contentDispositionHeader.split(";");
		
		for(String element : elements) {
			if(element.trim().startsWith("filename")) {
				fileName = element.substring(element.indexOf('=')+1);
				fileName = fileName.trim().replace("\"", "");
			}
		}
		return fileName;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Part part = request.getPart("theFile");
		String fileName = getFileName(part);
		
		if(fileName != null && !fileName.isEmpty()) {
			part.write(fileName);
		}
		
		String author = request.getParameter("theAuthor");
		author = new String(author.getBytes("utf-8"),"utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
//		out.print("작성자:"+author+"<br>");
//		out.print("파일명:"+fileName+"<br>");
//		out.print("파일크기:"+part.getSize()+"<br>");
		
		out.println("작성자:"+author+"<br>");
		out.println("파일명:<a href='FileDown?file_name="+fileName+"'>"+fileName+"</a><br>");
		out.println("파일크기:"+part.getSize()+"<br>");
		
		
	}
}
