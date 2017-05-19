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
<h2>ユーザー管理</h2>

 ユーザー一覧
<table>
<tr>
	<th>ログインID</th>
	<th>名前</th>
	<th>支店</th>
	<th>所属</th>
	<th>状態</th>
	<th>状態変更</th>
	<th>編集</th>
	<th>削除</th>
</tr>
<c:forEach items="${users}" var="user">
	<tr>
		<td>${user.login_id} </td>
		<td>${user.name} </td>
		<td>${user.branch_name}</td>
		<td>${user.post_name}</td>
		<td>
			<c:if test="${ user.can_use == 1 }">停止中</c:if>
			<c:if test="${ user.can_use == 0 }">使用中</c:if>
		<td>
			<c:if test="${loginUser.id != user.id }">
				<form action="manage" method="post"><br />
					<c:if test="${ user.can_use == 1 }">
						<input type ="hidden" name="can_use" value="0">
						<input type ="hidden" name="user_id" value="${user.id}">
						<input type='submit'  value='復活' onclick='return confirm("本当にアカウントを復活しますか？");'/>
					</c:if>
					<c:if test="${ user.can_use == 0 }">
						<input type ="hidden" name="can_use" value="1">
						<input type ="hidden" name="user_id" value="${user.id}">
						<input type='submit'  value='停止' onclick='return confirm("本当にアカウントを停止しますか？");'/>
					</c:if>
				</form>
			</c:if>
		</td>
		<td>
			<form action="edit" method="get"><br />
				<input type ="hidden" name="user_id" value="${user.id}">
				<input type="submit"  value="編集">
			</form>
		</td>
		<td>
			<c:if test="${loginUser.id != user.id }">
				<form action="delete" method="get"><br />
					<input type ="hidden" name="user_id" value="${user.id}">
					<input type="submit"  value="削除">
				</form>
			</c:if>
		</td>
	</tr>
</c:forEach>
</table>
<div class="copyright">Copyright(c)Misaki Oishi</div>
</body>
</html>