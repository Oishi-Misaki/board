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
<div>
<div class="navi">
	<a href="message" style="color:#004040;font-size:18px;font-weight: bold;">新規投稿</a>
	<c:if test="${loginUser.post_id == 1}">
		<a href="manage" style="color:#004040;font-size:18px;font-weight: bold;">ユーザー管理</a>
	</c:if>

</div>
<div class="loginUser"style="font-size:18px;font-weight: bold;">${loginUser.name}がログイン中　<a href="logout" style="color:red;font-size:18px;font-weight: bold;">ログアウト</a></div><br />
</div>
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

<div class="search">
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
		</select>
		　　　期間
		<input type="date" name="startDate" id="startDate" value="${startDate}"/>
		～<input type="date" name="endDate" id="endDate" value="${endDate}"/>
		<input type="submit" value="検索">
	</form><br />
</div>

<div class="main-contents" >
	<div class="infor">
		<a style="font-size:30px;">投稿</a>
		<a style="color:gray;text-align:right;dispaly:inline;">検索結果：${messageNum}件</a>
	</div>
	<div class="messages">
		<c:forEach items="${messages}" var="message">
			<div class="message">
					<div class="category">カテゴリー：<a href="./index.jsp?category=${message.category}&startDate=&endDate="><input type ="hidden" name="category" value="${message.category}">${message.category}</a></div>
					<div class="object"><c:out value="${message.object}" /></div>
					<div class="account" ><c:out value="${message.name}" />@<c:out value="${message.login_id}"/></div>
					<c:forEach var="s" items="${message.getSplitedText()}">
					    <c:out value="${s}"/>
					</c:forEach>
					<div class="insert_time"><c:out value="${message.insert_time}" /></div>
					<c:choose>
						<c:when test="${loginUser.post_id==2 || loginUser.id == message.user_id}">
							<form action="delete" method="get"style="text-align:right;">
								<input type ="hidden" name="deleteMessage" value="${message.message_id}">
								<input type="submit" value="削除" onclick='return confirm("本当に削除しますか？");' style="background-color:#ff8080;">
							</form>
						</c:when>
						<c:when test="${loginUser.branch_id==message.branch_id && loginUser.post_id == 3}">
							<form action="delete" method="get"style="text-align:right;">
								<input type ="hidden" name="deleteMessage" value="${message.message_id}">
								<input class="delete" type="submit" value="削除" onclick='return confirm("本当に削除しますか？");' style="background-color:#ff8080;">
							</form>
						</c:when>
					</c:choose>
				<div class="comments">
				<c:forEach items="${comments}" var="comment">
					<c:if test="${comment.message_id == message.message_id}" var="comment.message_id">
						<div class="comment">
							<div class="account">
								<c:out value="${comment.name}" />@<c:out value="${comment.login_id}" />
							</div>
							<c:forEach var="s" items="${comment.getSplitedText()}">
							    <c:out value="${s}"/>
							</c:forEach>

							<div class="insert_time"><c:out value="${comment.insert_time}" /></div>
						</div>

						<c:choose>
							<c:when test="${loginUser.post_id==2 || loginUser.id == comment.user_id}">
								<form action="delete" method="get"style="text-align:right;">
									<input type ="hidden" name="deleteComment" value="${comment.comment_id}">
									<input type ="hidden" name="category" value="${category}">
									<input type ="hidden" name="startDate" value="${startDate}">
									<input type ="hidden" name="endDate" value="${endDate}">
									<input  type="submit" value="コメント削除"onclick='return confirm("本当に削除しますか？");' style="background-color:#ff8080;">
								</form>
							</c:when>
							<c:when test="${loginUser.branch_id==comment.branch_id && loginUser.post_id==3}">
								<form action="delete" method="get"style="text-align:right;">
									<input type ="hidden" name="deleteComment" value="${comment.comment_id}">
									<input type ="hidden" name="category" value="${category}">
									<input type ="hidden" name="startDate" value="${startDate}">
									<input type ="hidden" name="endDate" value="${endDate}">
									<input  type="submit" value="コメント削除" onclick='return confirm("本当に削除しますか？");' style="background-color:#ff8080;">
								</form>
							</c:when>
						</c:choose>
					</c:if>

				</c:forEach>

					<div class="insertComment">
					<form action="./index.jsp" method="post">
						<div style="text-align:left;">コメント</div>
						<c:if test="${comment.message_id == message.message_id}">
							<textarea name="text"  class="comment-box"onkeyup="ShowLength( '${message.message_id}' , value );resize(this);">${comment.text}</textarea><br />
						</c:if>
						<c:if test="${comment.message_id != message.message_id}">
							<textarea name="text" class="comment-box"onkeyup="ShowLength( '${message.message_id}' , value );resize(this);"></textarea><br />
						</c:if>
						<input type ="hidden" name="category" value="${category}">
						<input type ="hidden" name="startDate" value="${startDate}">
						<input type ="hidden" name="endDate" value="${endDate}">
						<a id="${message.message_id}" style="text-align:right;">0/500 文字 </a><input type="submit" value="投稿" >
						<input type ="hidden" name="message_id" value="${message.message_id}">
					</form>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="copyright">Copyright(c)Misaki Oishi</div>
<script type="text/javascript"><!--
function ShowLength( idn, str ) {
	document.getElementById(idn).innerHTML = str.length + "/500 文字";
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