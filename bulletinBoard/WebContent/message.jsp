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
<title>新規投稿</title>
</head>
<body>
<h1>わったい菜掲示板</h1>

<div class="navi">
	<a href="./" style="color:#004040;font-size:18px;font-weight: bold;">ホーム</a>
</div>
<div class="loginUser"style="font-size:18px;font-weight: bold;">${loginUser.name}がログイン中</div><br />

<div class="errorMessages" >
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
			<a style="font-size:30px;">新規投稿</a>
		</div><br />

		<div class="form-area">
			<form action="message" method="post">
				<a  style="color:#004040;">件名</a>
				（50文字以下）<br />
				<textarea class="object-text" name="object" id="object"  onkeyup="resize(this)"style="width:50%;max-height:100px;resize: vertical;">${message.object}</textarea>
				<br /><br />
				<a  style="color:#004040;">本文</a>
				<c:if test="${not empty message.text}">
					<textarea name="text" class="message-text" onkeyup="ShowLength( 'inputlength2' , value );resize(this);">${message.text}</textarea>
				</c:if>
				<c:if test="${ empty message.text}">
					<textarea name="text" class="message-text" onkeyup="ShowLength( 'inputlength2' , value );resize(this);"></textarea>
				</c:if>
				<div id="inputlength2" style="text-align:right;" >（0/1000 文字）</div>
				<label for="category" style="color:#004040;">カテゴリーの選択</label>
				<select name="selectCategory" id="selectCategory" style="width:173px;height:21px;">
					<option value=""><c:out value="未選択" />
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
				</select><br /> <a  style="color:#004040;">カテゴリーの追加</a>
				（10文字以下）<br />
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
	<div class="copyright">Copyright(c)Misaki Oishi</div>
	<script type="text/javascript"><!--
	function ShowLength( idn, str ) {
		document.getElementById(idn).innerHTML = str.length + "/1000 文字";
	}
	// -->
	<!--
	  function resize(Tarea){
	    var areaH = Tarea.style.height;
	     if(Tarea.value == ""){areaH=26+"px";}
	    areaH = parseInt(areaH) - 30;
	      if(areaH < 30){areaH = 30}
	  Tarea.style.height = areaH + "px";
	Tarea.style.height = (parseInt(Tarea.scrollHeight)) + "px";
     }
-->
	</script>

</body>
</html>