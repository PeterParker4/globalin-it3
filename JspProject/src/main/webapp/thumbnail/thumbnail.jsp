<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 썸네일 이미지를 만들기 위해 JAI 클래스 및 그래픽 관련 클래스를 임포트 함 -->    
<%@ page import="java.awt.Graphics2D" %>
<%@ page import="java.awt.image.renderable.ParameterBlock" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.media.jai.JAI" %>
<%@ page import="javax.media.jai.RenderedOp" %>
<%@ page import="javax.imageio.ImageIO" %>

<!-- 썸네일 파일을 만들기 전에 썸네일 이미지로 변환할 이미지를 업로드 해야하므로 업로드 관련된 클래스를 임포트 -->
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%
	String imagePath = request.getRealPath("upload");

	int size = 1 * 1024 * 1024;
	
	String filename = "";
	
	try {
		// 썸네일 이미지로 변환할 이미지 업로드
		MultipartRequest multi = new MultipartRequest(request,
				imagePath, size, "utf-8", new DefaultFileRenamePolicy());
		
		// 업로드된 이미지 파일의 이름을 얻음
		Enumeration files = multi.getFileNames();
		String file = (String)files.nextElement();
		filename = multi.getFilesystemName(file);
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	// 파라미터 블럭 클래스에 변환할 이미지를 저장하고 그 이미지를 불러옴
	// 파라미터 블럭 클래스를 통해서만 이미지를 저장할 수 있음
	// 파라미터 블럭에 업로드된 이미지를 담고 JAI가 제공하는 코덱을 사용한다는 옵션을 사용해야 한다.
	ParameterBlock pb = new ParameterBlock();
	pb.add(imagePath + "/" + filename);
	RenderedOp rOp = JAI.create("fileload", pb);
	
	// 불러온 이미지에서 버퍼이미지를 생성해서 저장함
	BufferedImage bi = rOp.getAsBufferedImage();
	
	// 썸네일이라는 이미지 버퍼를 생성후 버퍼의 사이즈를 설정함(100*100)
	BufferedImage thumb = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
	
	// 이미지 버퍼에 원본 이미지를 정해진 버퍼 사이즈로 맞춰서 드로우함
	Graphics2D g = thumb.createGraphics();
	g.drawImage(bi, 0, 0, 100, 100, null);
	
	// 출력 위치와 파일 이름을 설정후 썸네일 이미지를 생성함
	File file = new File(imagePath + "/sm_" + filename);
	ImageIO.write(thumb, "jpg", file);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
원본 이미지<br>
<img alt="원본" src="/JspProject/upload/<%=filename%>"><p>
썸네일 이미지 <br>
<img alt="썸네일" src="/JspProject/upload/sm_<%=filename%>">
</body>
</html>