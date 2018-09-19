<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
用户管理<br />


<c:forEach items="${privilege }" var="item">
	<c:if test="${item.restype == 2 && item.resdesc == 'user:add' }">
		<a href="${item.url }" >添加</a>
	</c:if>
	<c:if test="${item.restype == 2 && item.resdesc == 'user:delete' }">
		<a href="${item.url }">删除</a>
	</c:if>
	<c:if test="${item.restype == 2 && item.resdesc == 'user:update' }">
		<a href="${item.url }">更新</a>
	</c:if>
</c:forEach>


</body>
</html>