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
<title>新規投稿</title>
</head>
<body>
	<h1>わったいな掲示板</h1>
	<br />
	<div class="header">
		<h3>新規投稿</h3>
		<br /> <a href="./">ホーム</a>
	</div>
	<div class="main-contents">
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>

		<div class="form-area">
			<form action="message" method="post">
				<label for="object">件名</label><br />
				<c:if test="${not empty message.object}">
					<input name="object" id="object" value="${message.object}" />
					<br />
				</c:if>
				<c:if test="${ empty message.object}">
					<input name="object" id="object" />
					<br />
				</c:if>

				本文（1000文字以下）<br />
				<c:if test="${not empty message.text}">
					<textarea name="text" cols="50" rows="5" class="text"/>${message.text}</textarea>
					<br />
				</c:if>
				<c:if test="${ empty message.text}">
					<textarea name="text" cols="50" rows="5" class="text" /></textarea>
					<br />
				</c:if>


				<br /> <label for="category">カテゴリーの選択</label>
				<select name="selectCategory" id="selectCategory">
					<option value=""><c:out value="ALL" />
					</option>
					<c:forEach items="${categorys}" var="categorys">
						<c:choose>
							<c:when test="${categorys.category == selectCategory}">
								<option value="${categorys.category}" selected>
								<c:out value="${categorys.category}" />
								</option>
							</c:when>
							<c:when test="${categorys.category != selectCategory}">
								<option value="${categorys.category}">
								<c:out value="${categorys.category}" />
								</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select><br />

				<label for="category">カテゴリーの追加</label>
				<c:if test="${not empty category}">
					<input name="category" id="category" value="${category}" />
					<br />
					<br />
				</c:if>
				<c:if test="${ empty category}">
					<input name="category" id="category" />
					<br />
					<br />
				</c:if>

				<input type="submit" value="投稿">
			</form>
		</div>
	</div>
</body>
</html>