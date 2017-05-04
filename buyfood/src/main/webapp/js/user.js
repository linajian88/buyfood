window.onload = function() {
	// 测试正确
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/user/getMessage";
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(null);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var message = eval("(" + xmlHttp.responseText + ")");
			if (message.code == -1) {
				window.location.href = "login.html";
			}
			if (message.code == 1) {
				var pro = message.data;
				var nameEle = document.getElementById("name");
				var telEle = document.getElementById("tel");
				var typeEle = document.getElementById("type");
				var idcard = document.getElementById("idcard");
				var srcEle = document.getElementById("pic");
				srcEle.src = pro.pic;
				nameEle.innerHTML = pro.name;
				telEle.innerHTML = pro.usertel;
				idcard.innerHTML = pro.idcard;
				if (pro.groupid == 1) {
					typeEle.innerHTML = "管理员";
				} else {
					typeEle.innerHTML = "普通用户";
				}
			} else {
				alert(message.msg);
			}
		}
	}
};