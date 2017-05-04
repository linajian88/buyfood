function deluser(id) {
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/user/delete";
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	var mess = "id=" + id;
	xmlHttp.send(mess);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var message = eval("(" + xmlHttp.responseText + ")");
			if (message.code == -1) {
				window.location.href = "login.html";
			}
			if (message.code == 1) {
				var tr = document.getElementById(id + "biaoji");
				tr.style.display = "none";
			} else {
				alert(message.msg);
			}
		}
	};
};