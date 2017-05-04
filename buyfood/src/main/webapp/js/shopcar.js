window.onload = function() {
	// 测试正确
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Shopcar/getBuylist";
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
					td1.innerHTML = pro.listFood.name;

					var td2 = document.createElement("td");
					td2.innerHTML = pro.listFood.num;

					var td3 = document.createElement("td");
					var addBun = document.createElement("button");
					addBun.innerHTML = "增加";
					var addNum = "addNum(" + pro.id + "," + pro.listFood.num
							+ ")";
					addBun.setAttribute("onclick", addNum);

					var buyNum = document.createElement("a");
					buyNum.innerHTML = pro.foodNum;
					buyNum.id = pro.id;

					var lesBun = document.createElement("button");
					lesBun.innerHTML = "减少";
					var lesNum = "lesNum(" + pro.id + ")";
					lesBun.setAttribute("onclick", lesNum);

					td3.appendChild(addBun);
					td3.appendChild(buyNum);
					td3.appendChild(lesBun);

					var td4 = document.createElement("td");
					td4.innerHTML = pro.listFood.price;

					var td5 = document.createElement("td");
					var changeBun = document.createElement("button");
					changeBun.innerHTML = "修改";
					var changeNum = "changeNum(" + pro.id + ")";
					changeBun.setAttribute("onclick", changeNum);

					var delBun = document.createElement("button");
					delBun.innerHTML = "删除";
					var deltoCar = "deltoCar(" + pro.id + ")";
					delBun.setAttribute("onclick", deltoCar);
					td5.appendChild(changeBun);
					td5.appendChild(delBun);

					var tr = document.createElement("tr");
					var flag = i % 5;
					tr.className = style[flag];
					tr.id = pro.id + "biaoji";
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);

					tbody.appendChild(tr);
					/*
					 * <tr> <td> TB - Monthly </td> <td> 01/04/2012 </td> <td>
					 * <button>增加</button><a id="num">1</a><button>减少</button>
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