function login() {
	var loginname = document.getElementById("username").value;
	var loginpwd = document.getElementById("password").value;
	var flag = true;
	if (loginname == null || loginname == "") {
		flag = false;
	}
	if (loginpwd == null || loginpwd == "") {
		flag = false;
	}
	if (flag == true) {
		var xmlHttp = createXMLHttpRequest();
		var curPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curPath.indexOf(pathName);
		var localhostPath = curPath.substring(0,pos);
		var url = localhostPath+"/user/login";
		xmlHttp.open("POST",url,true);
		var mess = "loginname="+loginname+"&loginpwd="+loginpwd;
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttp.send(mess);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var message = eval("(" + xmlHttp.responseText + ")");
				if (message.code == 1) {
					var data = message.data;
					if(data.groupid == 1){
						window.location.href = "Muser.html";
					}else{
						window.location.href = "index.html";
					}
				} else {
					alert(message.msg);
				}
			} 
		};
	} else {
		alert("账号或密码不能为空");
	}
};
function toregist() {
	window.location.href = "register.html";
};