window.onload = function() {
	// 测试正确
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Food/getAllFoodInfo";
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
				var proArray = message.data;
				var tbody = document.getElementById("start");
				var style = [ "", "success", "error", "warning", "info" ];
				for (var i = 0; i < proArray.length; i++) {
					var pro = proArray[i];
					var td1 = document.createElement("td");
					td1.innerHTML = pro.name;
					td1.id = "name";
					var td2 = document.createElement("td");
					td2.innerHTML = pro.price;
					td2.id = "price";
					var td3 = document.createElement("td");
					td3.innerHTML = pro.num;
					td3.id = "num";
					var td4 = document.createElement("td");
					td4.innerHTML = pro.info;
					td4.id = "info";
					var td5 = document.createElement("td");
					
					var button2 = document.createElement("button");
					button2.innerHTML = "删除";
					var delfood = "delfood(" + pro.id + ")";
					button2.setAttribute("onclick", delfood);
					td5.appendChild(button2);

					var tr = document.createElement("tr");
					var type = i % 5;
					tr.className = style[type];
					tr.id = pro.id + "biaoji";
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);

					tbody.appendChild(tr);
					/*
					 * <tr> <td> 1 </td> <td> TB - Monthly </td> <td> 01/04/2012
					 * </td> <td> Default </td> <td> <button>修改</button><button>删除</button>
					 * </td> </tr>
					 */

				}
			} else {
				alert(message.msg);
			}
		}
	};
};