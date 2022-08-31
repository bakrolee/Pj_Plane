<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>항공권 검색 결과</title>
<style type="text/css">
	.nowfee {
		color: blue;
	}
	.minfee {
		color: green;
	}
	.maxfee {
		color: red;
	}
</style>
</head>
<body>
	<h3>${ param.date }</h3>
	<h3>${ param.dep_loc }</h3>
	<h3>${ param.arr_loc }</h3>
	<div>
		<p class="nowfee">현재가 = blue</p>
		<p class="minfee">최저가 = green</p>
		<p class="maxfee">최고가 = red</p>
	</div>
	<ol>
		<c:if test="${ not empty list }">
			<c:forEach var="plane" items="${ list }">
				<li>
					<span>${ plane.airLine }</span>
					<span>${ plane.depTime }</span>
					<span>${ plane.arrTime }</span>
					<span class="nowfee">${ plane.nowfee }</span>
					<span class="minfee">${ plane.minfee }</span>
					<span class="maxfee">${ plane.maxfee }</span>
					<button>가격차트보기</button>
				</li>
			</c:forEach>
		</c:if>
	</ol>
	<c:if test="${ empty list }">
		<h3>검색하신 항공권이 없습니다</h3>
	</c:if>
</body>
<script type="text/javascript">
	
</script>
</html>