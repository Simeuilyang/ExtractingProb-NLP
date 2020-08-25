<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.BufferedOutputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>pdf view</title>
</head>
<body>
	<%
			FileInputStream fis = null;
			BufferedOutputStream bos = null;
			 
			try{
			    String fileName = "C:\\Users\\dmlfi\\Desktop\\pdfFile\\test.pdf";
			    File file = new File(fileName);
			    
			    // 보여주기
			    response.setContentType("application/pdf");
			    response.setHeader("Content-Description", "JSP Generated Data");
			    // 다운로드
			    //response.addHeader("Content-Disposition", "attachment; filename = " + file.getName() + ".pdf");
			 
			    fis = new FileInputStream(file);
			    int size = fis.available();
			    
			    byte[] buf = new byte[size];
			    int readCount = fis.read(buf);
			 
			    response.flushBuffer();
			 
			    bos = new BufferedOutputStream(response.getOutputStream());
			    bos.write(buf, 0, readCount);
			    bos.flush();
			} catch(Exception e) {
			    response.setContentType("text/html;charset=euc-kr");
			    out.println("<script language='javascript'>");
			    out.println("alert('파일 오픈 중 오류가 발생하였습니다.');");
			    out.println("</script>");
			    e.printStackTrace();
			} finally{
			    try{
			        if(fis != null) fis.close();
			        if(bos != null) bos.close();
			    } catch(IOException e){
			        e.printStackTrace();
			    }    
			}
	
		%>
</body>
</html>