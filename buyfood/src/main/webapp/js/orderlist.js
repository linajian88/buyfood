window.onload = function() {
	// 测试正确
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Orderlist/getAllOrderlist";
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
			if (message.code == 1 || message.code == 2) {
				var proArray = message.data;
				var div0 = document.getElementById("start");
				for (var i = 0; i < proArray.length; i = i + 2) {
					var j = i;
					var divs = document.createElement("div");
					divs.className = "accordion";
					divs.id = "accordion-" + i;
					for (var count = 0; count < 2, j < proArray.length; count++, j++) {
						var pro = proArray[j];
						var a1 = document.createElement("a");
						var xq = pro.username + "--" + pro.useraddr + "--"
								+ pro.usertel;
						a1.innerHTML = xq;
						a1.className = "accordion-toggle collapsed";
						a1.setAttribute("contenteditable", "true");
						a1.setAttribute("data-parent", "#accordion-" + i);
						a1.setAttribute("cdata-toggle", "collapse");
						var div1 = document.createElement("div");
						div1.className = "accordion-heading";
						div1.appendChild(a1);

						var thead = document.createElement("thead");
						var tr = document.createElement("tr");
						var th1 = document.createElement("th");
						th1.innerHTML = "编号";
						var th2 = document.createElement("th");
						th2.innerHTML = "产品";
						var th3 = document.createElement("th");
						th3.innerHTML = "数量";
						var th4 = document.createElement("th");
						th4.innerHTML = "价格";
						tr.appendChild(th1);
						tr.appendChild(th2);
						tr.appendChild(th3);
						tr.appendChild(th4);
						thead.appendChild(tr);

						var table = document.createElement("table");
						table.className = "table";
						table.appendChild(thead);

						var style = [ "", "success", "error", "warning", "info" ];

						var orders = pro.orders;
						var tbody = document.createElement("tbody");
						for (var k = 0; k < orders.length; k++) {
							var order = orders[k]
							var ntr = document.createElement("tr");
							var flag = k % 5;
							ntr.className = style[flag];
							var td1 = document.createElement("td");
							td1.innerHTML = order.foodid;
							var td2 = document.createElement("td");
							td2.innerHTML = order.name;
							var td3 = document.createElement("td");
							td3.innerHTML = order.num;
							var td4 = document.createElement("td");
							td4.innerHTML = order.price;
							ntr.appendChild(td1);
							ntr.appendChild(td2);
							ntr.appendChild(td3);
							ntr.appendChild(td4);
							tbody.appendChild(ntr);
						}
						table.appendChild(tbody);
						var div2 = document.createElement("div");
						div2.className = "accordion-inner";
						div2.appendChild(table);

						var div3 = document.createElement("div");
						div3.className = "accordion-body in collapse";
						div3.id = "accordion-element-" + j;
						div3.appendChild(div2);

						var div4 = document.createElement("div");
						div4.className = "accordion-group";
						div4.appendChild(div1);
						div4.appendChild(div3);
						divs.appendChild(div4);
					}
					div0.appendChild(divs);
				}
			} else {
				alert(message.msg);
			}
		}
	};
};