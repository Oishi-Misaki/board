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
<h1>わったいな掲示板</h1><br />
<h3>新規投稿</h3><br />
<div class="main-contents">

<div class="form-area">
<form action="message" method="post">
	<label for="object">件名</label>
	<input name="object" id="object"/><br />
	本文
	<textarea name="text" cols="50" rows="5" class="text"></textarea>
	<br />
	<label for="category">カテゴリー</label>
	<input name="category" id="category"/><br />
	<br />
	<input type="submit" value="投稿">（1000文字まで）
</form>

</div>
</div>
</body>
</html>