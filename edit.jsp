<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>わったいな掲示板</title>
</head>
<body>
<h1>わったいな掲示板</h1>
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
</div>
<div class="header">
	<a href="signup">新規登録</a>
	<a href="./">ホーム</a>
</div>
<h2>ユーザー情報編集</h2>
	<form action="edit" method="post"><br />
		<input type ="hidden" name="user_id" value="${editUser.id}">
		<label for="login_id">ログインID</label>
		<input name="login_id" id="login_id" value="${editUser.login_id }"/><br />

		<label for="password1">パスワード</label>
		<input name="password1" type="password" id="password1" /><br />

		<label for="password2">パスワード（確認用）</label>
		<input name="password2" type="password" id="password2" /><br />
		<input type ="hidden" name="password" value="${editUser.password}">

		<label for="name">名前</label>
		<input type="text" name="name" id="name" value="${editUser.name}" /><br />

		<c:if test="${loginUser.id != editUser.id }">
			<label for="post_id">部署・役職</label>
			<select name="post_id" id="post_id">
				<option value="" ><c:out value="未選択"/> </option>
				<c:forEach items="${posts}" var="post">
					<c:if test="${post.id == editUser.post_id}">
						<option value="${post.id}" selected><c:out value="${post.name}"/> </option>
					</c:if>
					<c:if test="${post.id != editUser.post_id}">
						<option value="${post.id}" ><c:out value="${post.name}"/> </option>
					</c:if>
				</c:forEach>
			</select><br />

			<label for="post_id">支店</label>
			<select name="branch_id" id="branch_id">
				<option value="" ><c:out value="未選択"/> </option>
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
		</c:if>
		<c:if test="${loginUser.id == editUser.id }">
			<input type="hidden"  name="branch_id" id="branch_id" value="${editUser.branch_id }">
			<input type="hidden"  name="post_id" id="post_id" value="${editUser.post_id }">
		</c:if>


		<input type="submit" value="登録" /> <br />
		<br />
		<a href ="manage">戻る</a>
	</form>

<div class="copyright">Copyright(c)Misaki Oishi</div>
</body>
</html>