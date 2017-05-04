function changefood(foodid) {

};
function delfood(foodid) {
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Food/deleteFoodById";
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	var mess = "id=" + foodid;
	xmlHttp.send(mess);
	xmlHttp.onreadystatechange = function() {
		if (message.code == -1) {
			window.location.href = "login.html";
		}
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var message = eval("(" + xmlHttp.responseText + ")");
			if (message.code == 1) {
				var tr = document.getElementById(foodid + "biaoji");
				tr.style.display = "none";
			} else {
				alert(message.msg);
			}
		}
	};
};

function addfood() {
	var name = document.getElementById("foodname").value;
	var price = document.getElementById("foodprice").value;
	var info = document.getElementById("foodinfo").value;
	var type = document.getElementById("foodtype").value;
	var num = document.getElementById("foodnum").value;
	var picture = document.getElementById("foodpic").files[0];

	var flag = true;
	if (name == null || name == "") {
		flag = false;
	}
	if (price == null || price == "") {
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
		var url = localhostPath + "/food/foodpicupload";
		xmlHttp.open("POST", url, true);
		xmlHttp.send(form);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var message = eval("(" + xmlHttp.responseText + ")");
				if (message.code == -1) {
					window.location.href = "login.html";
				}
				if (message.code == 1) {
					var pic = message.data.pic;
					var xmlHttp1 = createXMLHttpRequest();
					var url2 = localhostPath + "/food/addFood";
					xmlHttp1.open("POST", url2, true);
					xmlHttp1.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					var mess1 = "name=" + name + "&price=" + price + "&info="
							+ info + "&type=" + type + "&num=" + num + "&pic="
							+ pic;
					xmlHttp1.send(mess1);
					xmlHttp1.onreadystatechange = function() {
						if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200) {
							var message1 = eval("(" + xmlHttp1.responseText
									+ ")");
							if (message1.code == 1) {
								alert("添加成功");
							} else {
								alert(message1.msg);
							}
						}
					};
				}
			}
		};
	} else {
		alert("名字和价格不能为空");
	}
};