<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<h1登陆</h1>
<br><br>
<form action="${base}/login" method="POST">
<table border="0" align="center" cellspacing="4" cellpadding="4">
	<tr>
		<td>用户名:</td>
		<td><input name="name"></td>
	</tr>
	<tr>
		<td>密码:</td>
		<td><input name="pwd" type="password"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="submit" value="&nbsp;&nbsp;登陆&nbsp;&nbsp;" /></td>
	</tr>
</table>
</form>
<%@ include file="/common/footer.jsp" %>