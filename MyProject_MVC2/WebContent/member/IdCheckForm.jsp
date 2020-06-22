<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#wrap {
	width: 490px;
	text-align: center;
	margin: 0 auto 0 auto;
}

#chk {
	text-align: center;
}

#cancelBtn {
	visibility: visible;
}

#useBtn {
	visibility: hidden;
}
</style>
<script type="text/javascript">

	var httpRequest = null;
	
	/* httpRequest 객체 생성 https://jamong-icetea.tistory.com/150 */
	function getXMLHttpRequest(){

		var httpRequest = null;
		/* IE일 경우 */
		if(window.ActiveXObject) {
			try {
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e){
				try{
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e2){
					httpRequest = null;
				}
			}
			/* 그 외 익스플로러일 경우 */
		} else if(window.XMLHttpRequest){
			httpRequest = new window.XMLHttpRequest();
		}
		return httpRequest;
	}

	/* function pValue(){
		document.getElementById("userId").value = opener.document.userInfo.id.value;
	} */
	
	function idCheck(){
		var id = document.getElementById("userId").value;
		
		if(!id){
			alert("You have to Insert ID");
			return false;
		} else if ((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")) {
			alert("You can use Enlgish and number on ID");
			return false;
		} else if(id.length < 5){
			alert("Input at least 5 letters required");
			return false;
		} else {
			var param="id="+id
			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = callback;
			httpRequest.open("POST", "MemberIdCheckAction.do", true);	
			httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
			httpRequest.send(param);
		}
	}
	
	function callback(){
		if(httpRequest.readyState == 4){
			var resultText = httpRequest.responseText;
			if(resultText == 0){
				alert("This Id is Unavailable");
				document.getElementById("cancelBtn").style.visibility = 'visible';
				document.getElementById("useBtn").style.visibility = 'hidden';
				document.getElementById("msg").innerHTML = "";
			} else if(resultText == 1){
				document.getElementById("cancelBtn").style.visibility = 'hidden';
				document.getElementById("useBtn").style.visibility = 'visible';
				document.getElementById("msg").innerHTML = "You can use this ID";
			}
		}
	}
	
	function sendCheckValue(){
		opener.document.userInfo.idDuplication.value="idCheck";
		opener.document.userInfo.id.value = document.getElementById("userId").value;
		
		if(opener != null){
			opener.chkForm = null;
			self.close();
		}
	}
	
</script>
</head>
<body onload="pValue()">
	<div id="wrap">
		<br> <b><font size="4" color="darkgrey">ID Check</font></b>
		<hr size="1" width="450">
		<br>
		<div id="chk">
			<form id="checkForm">
				<input type="text" name="idinput" id="userId"> 
				<input type="button" value="Check" onclick="idCheck()">
			</form>
			<div id="msg"></div>
			<br> <input type="button" id="cancelBtn" value="Cancel"
				onclick="window.close()"><br> <input type="button"
				id="useBtn" value="Use" onclick="sendCheckValue()">
		</div>
	</div>
</body>
</html>