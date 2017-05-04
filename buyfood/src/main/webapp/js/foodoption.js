function addtoCar(foodId) {
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Shopcar/addBuylist";
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	var mess = "foodId=" + foodId + "&foodNum=1";
	xmlHttp.send(mess);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var message = eval("(" + xmlHttp.responseText + ")");
			alert(message.msg);
		}
	};
};

function addNum(id, max) {
	var numEle = document.getElementById(id);
	var num = numEle.innerText;
	if (num < max) {
		numEle.innerHTML = ++num;
	}
};

function lesNum(id) {
	var numEle = document.getElementById(id);
	var num = numEle.innerHTML;
	if (num > 1) {
		numEle.innerHTML = num - 1;
	}
};

function deltoCar(id) {
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Shopcar/delBuyList";
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	var mess = "buyId=" + id;
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

function changeNum(id) {
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Shopcar/changeBuyList";
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	var numEle = document.getElementById(id);
	var foodNum = numEle.innerHTML;
	var mess = "buyId=" + id + "&foodNum=" + foodNum;
	xmlHttp.send(mess);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var message = eval("(" + xmlHttp.responseText + ")");
			alert(message.msg);
		}
	};
};

function buy() {
	var useraddr = document.getElementById("addra").value;
	var usertel = document.getElementById("phonea").value;
	var flag = true;
	if (useraddr == null || useraddr == "") {
		flag = false;
	}
	if (usertel == null || usertel == "") {
		flag = false;
	}
	if (flag == true) {
		var xmlHttp = createXMLHttpRequest();
		var curPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curPath.indexOf(pathName);
		var localhostPath = curPath.substring(0, pos);
		var url = localhostPath + "/Orderlist/addOrderlist";
		alert(url);
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		var mess = "useraddr=" + useraddr + "&usertel=" + usertel;
		xmlHttp.send(mess);
		alert(mess);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var message = eval("(" + xmlHttp.responseText + ")");
				if (message.code == 1) {
					window.location.href = "orderlist.html";
				} else {
					alert(message.msg);
				}
			}
		};
	} else {
		alert("地址和电话不能为空");
	}
};

