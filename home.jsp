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
	<a href="message">新規投稿</a>
	<a href="manage">ユーザー管理</a>
	<a href="logout">ログアウト</a>
</div>
<div class="main-contents">
	<form action="./index.jsp" method="get">
		<select name="category" id="category">
			<option value="" ><c:out value="ALL"/> </option>
			<c:forEach items="${categorys}" var="category">
				<option value="${category.category}" ><c:out value="${category.category}"/> </option>
			</c:forEach>
		</select>
		<input type="submit" value="カテゴリー検索">
	</form><br />
	期間検索
	<form action="./index.jsp" method="post">
		<label for="startDate">開始期間
		<input name="startDate" id="startDate"/><br />
		</label>
		<label for="endDate">開始期間
		<input name="endDate" id="endDate"/>
		</label>
		<input type="submit" value="検索">
	</form><br />
	<div class="messages">
		<c:forEach items="${messages}" var="message">
		<div class="message">
				<div class="account">
					<span class="login_id"><c:out value="${message.login_id}" /></span>
					<span class="name"><c:out value="${message.name}" /></span>
				</div>
				<div class="object"><c:out value="${message.object}" /></div>
				<div class="category"><c:out value="${message.category}" /></div>
				<div class="text"><c:out value="${message.text}" /></div>
				<div class="insert_time"><c:out value="${message.insert_time}" /></div>

				<c:forEach items="${comments}" var="comment">
					<c:if test="${comment.message_id == message.message_id}" var="comment.message_id">
						<div class= "comment">
							<div class="account">
								<span class="name"><c:out value="${comment.name}" /></span>
								<span class="login_id"><c:out value="${comment.login_id}" /></span>
							</div>
							<div class="text"><c:out value="${comment.text}" /></div>
							<div class="insert_time"><c:out value="${comment.insert_time}" /></div>
						</div>
					</c:if>

				</c:forEach>
				<div class="comment">
					<form action="./index.jsp" method="post">
						コメント<br />
						<textarea name="text" cols="20" rows="3" class="text"></textarea>
						<br />
						<input type="submit" value="投稿">（500文字まで）
						<input type ="hidden" name="message_id" value="${message.message_id}">
					</form>
				</div>
			</div>

		</c:forEach>
	</div>
</div>
<div class="copyright">Copyright(c)Misaki Oishi</div>
</body>
</html>