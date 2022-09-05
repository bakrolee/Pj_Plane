<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>항공권 검색</title>
</head>
<body>
	<form action="./searchplane" method="post" onsubmit="return equalLoc()">
		<input type="date" id="Date" name="date" required /> 
		<select id="seldep" name="dep_loc" required>
			<option value="" disabled selected>출발지</option>
			<option value="PUS">부산(김해)</option>
			<option value="GMP">김포</option>
			<option value="CJU">제주</option>
		</select> 
		<select id="selarr" name="arr_loc" required>
			<option value="" disabled selected>도착지</option>
			<option value="PUS">부산(김해)</option>
			<option value="GMP">김포</option>
			<option value="CJU">제주</option>
		</select> 
		<input type="submit" value="검색" />
	</form>
</body>

<script type="text/javascript">
// 내일 날짜 구하기
	function someday(date) {
		// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
		var now_utc = date; // 지금 날짜를 밀리초로
		
		// new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
		var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
		
		return new Date(now_utc-timeOff).toISOString().split("T")[0];
	}

	function operateDate(plus) {
		var today = Date.now();
		var oneDay = 86400000;
		return Date.now() + (oneDay * plus);
	}
	
	var start = operateDate(-1);
// 	document.getElementById("Date").setAttribute("min", someday(Date.now()));
	document.getElementById("Date").setAttribute("min", someday(start));
	
	var tomorrow = operateDate(1);
	document.getElementById("Date").setAttribute("max", someday(tomorrow));

	
	function equalLoc() {
		var id = document.getElementById("seldep");
		var depVal = id.options[id.selectedIndex].value;
		id = document.getElementById("selarr");
		var arrVal = id.options[id.selectedIndex].value;
		
		console.log("출발 :" + depVal);
		console.log("도착 :" + arrVal);
		
		if (depVal == arrVal) {
			alert("출발지와 도착지가 같습니다. 바꿔주세요");
			return false;
		} else {
			return true;
		}
	}
</script>
</html>