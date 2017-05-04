window.onload = function(){
	document.getElementById("login").onclick = function(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var flag = "true";
		if(username == ""||username == undefine||username == null){
			flag = "false";
			var nameEle = document.getElementById("namemessage");
			nameEle.innerHTML = "用户名不能为空";
		}
		if(password==""||password == undefine||password == null){
			flag = "false";
			var passEle = document.getElementById("passwordmessage");
			passEle.innerHTML = "密码不能为空";
		}
		if(flag == "true"){
			var xmlHttp = createXMLHttpRequest();
			var curPath = window.document.location.href;
			var pathName = window.document.location.pathname;
			var pos = curPath.indexOf(pathName);
			var localhostPath = curPath.substring(0,pos);
			var url1 = localhostPath+"接口地址";
			xmlHttp.open("POST",url1,true);
			var message = "username="+username+"&password="+password;
			xmlHttp.send(message);
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState==4&&xmlHttp.status==200){
					var proArray = eval("("+xmlHttp.responseText+")");
					if(proArray.xxx == 1){
						var url2 = localhostPath+"/htmls/allFood.html";
						window.location.href=url;
					}else{
						var errorEle = document.getElementById("errormessage");
						errorEle.innerHTML = "账号或密码错误";
					}
				}
			};
		}
	};
};