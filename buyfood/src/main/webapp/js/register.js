preview.config({preBox:'preview'}).listen('#pic')
function regist() {
	var loginname = document.getElementById("number").value;
	var loginpwd = document.getElementById("password").value;
	var name = document.getElementById("username").value;
	var usertel = document.getElementById("phonenum").value;
	var age = document.getElementById("age").value;
	var idcard = document.getElementById("idcard").value;
	var groupid = document.getElementById("groupid").value;
	var picture = document.getElementById("pic").files[0];
	var flag = true;
	if (loginname == null || loginname == "") {
		flag = false;
	}
	if (name == null || name == "") {
		flag = false;
	}
	if (loginpwd == null || loginpwd == "") {
		flag = false;
	}
	if (flag == true) {
		var form = new FormData();
		form.append("file", picture);
		var xmlHttp = createXMLHttpRequest();
		var curPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curPath.indexOf(pathName);
		var localhostPath = curPath.substring(0, pos);
		var url = localhostPath + "/user/userpicupload";
		
		xmlHttp.open("POST", url, true);
/*		xmlHttp.setRequestHeader("Content-Type",
				"multipart/form-data");*/
		xmlHttp.send(form);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var message = eval("(" + xmlHttp.responseText + ")");
				alert("loginname=" + loginname + "&loginpwd=" + loginpwd
						+ "&name=" + name + "&age=" + age + "&usertel="
						+ usertel + "&groupid=" + groupid + "&idcard=" + idcard
						+ "&pic=" + pic);
				
				if (message.code == 1) {
					var pic = message.data.pic;
					var xmlHttp1 = createXMLHttpRequest();
					var url2 = localhostPath + "/user/register";
					
					xmlHttp1.open("POST", url2, true);
					xmlHttp1.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					var mess1 = "loginname=" + loginname + "&loginpwd="
							+ loginpwd + "&name=" + name + "&age=" + age
							+ "&usertel=" + usertel + "&groupid=" + groupid
							+ "&idcard=" + idcard + "&pic=" + pic;
					xmlHttp1.send(mess1);
					xmlHttp1.onreadystatechange = function() {
						if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200) {
							var message1 = eval("(" + xmlHttp1.responseText + ")");
							if (message1.code == 1) {
								window.location.href = "login.html";
							} else {
								alert(message1.msg);
							}
						}
					};
				}
			}
		};
	} else {
		alert("用户名或密码不能为空");
	}
};