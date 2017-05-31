<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/board.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h1>わったい菜掲示板</h1>

<div class="navi">
	<a href="./" style="color:#004040;font-size:18px;font-weight: bold;">ホーム</a>
	<a href="manage" style="color:#004040;font-size:18px;font-weight: bold;">ユーザー管理</a>
</div>
<div class="loginUser" style="font-size:18px;font-weight: bold;">${loginUser.name}がログイン中</div><br />
	<div class="errorMessages">
	<c:if test="${ not empty errorMessages }">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	<c:remove var="errorMessages" scope="session"/>
	</c:if>
	</div>
<div class="main-contents">
<div class="infor">
		<a style="font-size:30px;">ユーザー登録</a>
	</div>
<form action="signup" method="post"><br />

	<label for="login_id" style="color:#004040;">ログインID（半角英数字6-20文字）</label>
	<c:if test="${not empty signUpUser.login_id}">
		<input name="login_id" id="login_id" value="${signUpUser.login_id}" maxlength='20'  /><br />
	</c:if>
	<c:if test="${ empty signUpUser.login_id}">
		<input name="login_id" id="login_id"/><br />
	</c:if>

	<label for="password" style="color:#004040;">パスワード（半角英数字6-255文字）</label>
	<input name="password1" type="password" id="password1"/> <br />
	<label for="password" style="color:#004040;">パスワード（再確認）</label>
	<input name="password2" type="password" id="password2"/> <br />

	<label for="name" style="color:#004040;">名前（10文字以下）</label>
	<c:if test="${not empty signUpUser.name}">
		<input name="name" id="name" value="${signUpUser.name}"/><br />
	</c:if>
	<c:if test="${ empty signUpUser.name}">
		<input name="name" id="name"/><br />
	</c:if>

	<label for="branch_id" style="color:#004040;">支店</label>
		<select name="branch_id" id="branch_id">
			<option value="0" ><c:out value="未選択"/> </option>
			<c:forEach items="${branches}" var="branch">
			<c:choose>
					<c:when test="${branch.id == signUpUser.branch_id}">
						<option value="${branch.id}" selected><c:out value="${branch.name}"/> </option>
					</c:when>
					<c:when test="${branch.id != signUpUser.branch_id}">
						<option value="${branch.id}" ><c:out value="${branch.name}"/> </option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select><br />

	<label for="post_id" style="color:#004040;">部署・役職</label>
		<select name="post_id" id="post_id">
			<option value="0" ><c:out value="未選択"/> </option>
			<c:forEach items="${posts}" var="post">
			<c:choose>
					<c:when test="${post.id == signUpUser.post_id}">
						<option value="${post.id}" selected><c:out value="${post.name}"/> </option>
					</c:when>
					<c:when test="${post.id != signUpUser.post_id}">
						<option value="${post.id}" ><c:out value="${post.name}"/> </option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select>
		<br />
	<input type="submit" value="登録"  />
	<br />

</form>
<div class="copyright">Copyright(c)Misaki_Oishi</div>
</div>
</body>
</html>