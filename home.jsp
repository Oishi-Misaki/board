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
<h3>${loginUser.name}がログイン中</h3>
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
	<c:if test="${loginUser.post_id == 1}">
		<a href="manage">ユーザー管理</a>
	</c:if>
	<a href="logout">ログアウト</a>
</div>
<div class="main-contents">
	<form action="./index.jsp" method="get">
		カテゴリー
		<select name="category" id="category">
			<option value="" ><c:out value="ALL"/> </option>
			<c:forEach items="${categorys}" var="categorys">
				<c:choose>
					<c:when test="${categorys.category == category}">
						<option value="${categorys.category}" selected><c:out value="${categorys.category}"/> </option>
					</c:when>
					<c:when test="${categorys.category != category}">
						<option value="${categorys.category}" ><c:out value="${categorys.category}"/> </option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select><br />
		検索期間<br />
		<label for="startDate">開始
			<input type="date" name="startDate" id="startDate" value="${startDate}"/>
		</label>
		<label for="endDate">終了
			<input type="date" name="endDate" id="endDate" value="${ endDate}"/>
		</label>
		<input type="submit" value="検索">
	</form><br />

	<div class="messages">
		<c:forEach items="${messages}" var="message">
		<div class="message">
				<div class="account">投稿者：<c:out value="${message.name}" /></div>
				<div class="object">件名：<c:out value="${message.object}" /></div>
				<div class="category">カテゴリー：<c:out value="${message.category}" /></div>
				<div class="text">本文：<c:out value="${message.text}" /></div>
				<div class="insert_time"><c:out value="${message.insert_time}" /></div>
				<c:if test="${loginUser.post_id==2 || loginUser.id == message.user_id}">
					<form action="delete" method="get">
						<input type ="hidden" name="deleteMessage" value="${message.message_id}">
						<input type="submit" value="削除">
					</form>
				</c:if>
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

						<c:choose>
							<c:when test="${loginUser.post_id==2 || loginUser.id == comment.user_id}">
								<form action="delete" method="get">
									<input type ="hidden" name="deleteComment" value="${comment.comment_id}">
									<input type="submit" value="コメント削除">
								</form>
							</c:when>
							<c:when test="${loginUser.branch_id==comment.branch_id && loginUser.post_id==3}">
								<form action="delete" method="get">
									<input type ="hidden" name="deleteComment" value="${comment.comment_id}">
									<input type="submit" value="コメント削除">
								</form>
							</c:when>
						</c:choose>
					</c:if>

				</c:forEach>
				<div class="comment">
					<form action="./index.jsp" method="post">
						コメント<br />
						<c:if test="${comment.message_id == message.message_id}">
							<textarea name="text" cols="20" rows="3" class="text">${comment.text}</textarea><br />
						</c:if>
						<c:if test="${comment.message_id != message.message_id}">
							<textarea name="text" cols="20" rows="3" class="text"></textarea><br />
						</c:if>
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