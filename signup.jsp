<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h2>新規登録</h2>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<form action="signup" method="post"><br />

	<label for="login_id">ログインID（半角英数字6-20文字）</label>
	<c:if test="${not empty signUpUser.login_id}">
		<input name="login_id" id="login_id" value="${signUpUser.login_id}"/><br />
	</c:if>
	<c:if test="${ empty signUpUser.login_id}">
		<input name="login_id" id="login_id"/><br />
	</c:if>

	<label for="password">パスワード（半角英数字6-255文字）</label>
	<input name="password1" type="password" id="password1"/> <br />
	<label for="password">パスワード（再確認）</label>
	<input name="password2" type="password" id="password2"/> <br />

	<label for="name">名前（10文字以下）</label>
	<c:if test="${not empty signUpUser.name}">
		<input name="name" id="name" value="${signUpUser.name}"/><br />
	</c:if>
	<c:if test="${ empty signUpUser.name}">
		<input name="name" id="name"/><br />
	</c:if>

	<label for="branch_id">支店</label>
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
		<label for="post_id">部署・役職</label>
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
		</select><br />
	<input type="submit" value="登録" /> <br />
	<br />
	<a href ="./">戻る</a>

</form>
<div class="copyright">Copyright(c)Misaki_Oishi</div>
</div>
</body>
</html>