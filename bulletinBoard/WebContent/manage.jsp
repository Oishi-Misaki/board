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
	<a href="signup" style="color:#004040;font-size:18px;font-weight: bold;">新規登録</a>
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
		<a style="font-size:30px;">ユーザー管理</a>
		<a style="font-size:15px;">（ユーザー一覧）</a>
	</div><br />

<table class="table1">
<thead>
<tr>
	<th>ログインID</th>
	<th>名前</th>
	<th>支店</th>
	<th>部署・役職</th>
	<th>停止/復活</th>
	<th>編集</th>
	<th>削除</th>
</tr>
</thead>
<tbody>
<c:forEach items="${users}" var="user">
	<tr>
		<td>${user.login_id} </td>
		<td>${user.name} </td>
		<td>${user.branch_name}</td>
		<td>${user.post_name}</td>
		<td>
			<c:if test="${loginUser.id != user.id }">
				<form action="manage" method="post"><br />
					<c:if test="${ user.can_use == 1 }">
						<input type ="hidden" name="can_use" value="0">
						<input type ="hidden" name="user_id" value="${user.id}">
						<input type='submit'  value='復活する' style="color:#0080ff;margin:0;" onclick='return confirm("本当にアカウントを復活しますか？");'/>
					</c:if>
					<c:if test="${ user.can_use == 0 }">
						<input type ="hidden" name="can_use" value="1">
						<input type ="hidden" name="user_id" value="${user.id}">
						<input type='submit'  value='停止する' style="color:#ff0000;margin:0;" onclick='return confirm("本当にアカウントを停止しますか？");'/>
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
					<input type="submit"  value="削除" style="background-color:#ff8080;" onclick='return confirm("本当にアカウントを削除しますか？");'>
				</form>
			</c:if>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>
<div class="copyright">Copyright(c)Misaki Oishi</div>
</body>
</html>