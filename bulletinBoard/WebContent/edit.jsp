<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/board.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>わったい菜掲示板</title>
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
		<a style="font-size:30px;">ユーザー情報の編集</a>
	</div>
	<form action="edit" method="post"><br />
		<input type ="hidden" name="user_id" value="${editUser.id}">
		<label for="login_id" style="color:#004040;">ログインID（半角英数字6-20文字）</label>
		<input name="login_id" id="login_id" value="${editUser.login_id }"/><br />

		<label for="password1" style="color:#004040;">パスワード（半角英数字6-255文字）</label>
		<input name="password1" type="password" id="password1" /><br />

		<label for="password2" style="color:#004040;">確認用パスワード</label>
		<input name="password2" type="password" id="password2" /><br />
		<input type ="hidden" name="password" value="${editUser.password}">

		<label for="name" style="color:#004040;">名前（10文字以下）</label>
		<input type="text" name="name" id="name" value="${editUser.name}" /><br />

		<c:if test="${loginUser.id != editUser.id }">
		<label for="post_id" style="color:#004040;">支店</label>
			<select name="branch_id" id="branch_id">
				<option value="0" ><c:out value="未選択"/> </option>
				<c:forEach items="${branches}" var="branch">
					<c:choose>
						<c:when test="${branch.id == editUser.branch_id}">
							<option value="${branch.id}" selected><c:out value="${branch.name}" /></option>
						</c:when>
						<c:when test="${branch.id != editUser.branch_id}">
							<option value="${branch.id}" ><c:out value="${branch.name}" /></option>
						</c:when>
					</c:choose>
				</c:forEach>
			</select><br />
			<label for="post_id" style="color:#004040;">部署・役職</label>
			<select name="post_id" id="post_id">
				<option value="0" ><c:out value="未選択"/> </option>
				<c:forEach items="${posts}" var="post">
					<c:if test="${post.id == editUser.post_id}">
						<option value="${post.id}" selected><c:out value="${post.name}"/> </option>
					</c:if>
					<c:if test="${post.id != editUser.post_id}">
						<option value="${post.id}" ><c:out value="${post.name}"/> </option>
					</c:if>
				</c:forEach>
			</select><br />


		</c:if>
		<c:if test="${loginUser.id == editUser.id }">
		<label for="post_id" style="color:#004040;">支店</label>
		<c:forEach items="${branches}" var="branch">
			<c:if  test="${branch.id == editUser.branch_id}">
			<c:out value="${branch.name}" />
			</c:if>
		</c:forEach>
		<label for="post_id" style="color:#004040;">部署・役職</label>
		<c:forEach items="${posts}" var="post">
			<c:if  test="${post.id == editUser.post_id}">
				<c:out value="${post.name}"/>
			</c:if>
		</c:forEach>
		<input type="hidden"  name="branch_id" id="branch_id" value="${editUser.branch_id }">
		<input type="hidden"  name="post_id" id="post_id" value="${editUser.post_id }">
		</c:if><br />


		<input type="submit" value="登録" style="margin-top:10px;"/>
		<br />
	</form>
</div>

<div class="copyright">Copyright(c)Misaki Oishi</div>
</body>
</html>