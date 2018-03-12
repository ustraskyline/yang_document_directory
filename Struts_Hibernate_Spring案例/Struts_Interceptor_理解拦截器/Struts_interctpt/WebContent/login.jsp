<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<center>
		${requestScope.msg}<br>

		<form action="loginAction" method="post">
			<table>
				<tr>
					<td><label style="text-align: right;">用户名:</label></td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td><label style="text-align: right;">密码:</label></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td align="right" colspan="2"><input type="submit" value="登录"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>