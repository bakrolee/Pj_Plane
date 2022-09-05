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
					<span>
						<p>아이디: ${ plane.id }</p>
						<form method="post">
							<input type="hidden" name="id" value="${ plane.id }" />
							<input type="button" id="submit" value="가격차트보기" onclick="open_popup();" />
						</form>
						
					</span>
				</li>
			</c:forEach>
		</c:if>
	</ol>
	<c:if test="${ empty list }">
		<h3>검색하신 항공권이 없습니다</h3>
	</c:if>
</body>

<script type="text/javascript">
/* $(document).ready(function(){
	$("#submit").click(function(){   //submit 버튼을 클릭하였을 때
		$.ajax({
			type: "post",   //post 방식으로 전송
			url: "./viewchart",   //데이터를 주고받을 파일 주소
			data: $('input[name=id]').val(),   //위의 변수에 담긴 데이터를 전송해준다.
			success : function(reslut){   
				if (result) {
					alert("불러오기 성공");
				}
			}
		});
	});
}) */

	function open_popup() {
		window.open("./viewchart", "chart", "width=500, height=400");
		
		/* var frmData = document.frmData;
		frmData.target = title;
		frmData.action = url;
		
		frmData.submit(); */
	}
</script>
</html>