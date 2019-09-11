<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.itcen.emaillist1.dao.EmaillistDao" %>
<%@ page import="kr.co.itcen.emaillist1.vo.EmaillistVo" %>
<%
	request.setCharacterEncoding("utf-8");

	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	
	EmaillistVo emailVo = new EmaillistVo();
	emailVo.setFirstName(firstName);
	emailVo.setLastName(lastName);
	emailVo.setEmail(email);
	
	new EmaillistDao().insert(emailVo);
	
	response.sendRedirect(request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>성공적으로 등록 되었습니다.</h1>
</body>
</html>