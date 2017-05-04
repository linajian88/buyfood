window.onload = function() {
	// 测试正确
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0, pos);
	var url = localhostPath + "/Food/getFoodByPage";
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	var reg = new RegExp("(^|&)" + "page" + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	var page = 1;
	if (r != null) {
		page = unescape(r[2]);
	}
	var page1 = page;
	var mess = "page=" + page;
	xmlHttp.send(mess);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var message = eval("(" + xmlHttp.responseText + ")");
			if (message.code == 1 || message.code == 2) {

				if (message.code == 2) {
					var lli = document.getElementById("login");
					lli.style.display = "none";
				}

				var proArray = message.data;
				var div0 = document.getElementById("start");
				for (var i = 0; i < proArray.length; i = i + 3) {
					var j = i;
					var ul = document.createElement("ul");
					ul.className = "thumbnails";
					for (var count = 0; count < 3 && j < proArray.length; count++, j++) {
						var pro = proArray[j];

						var img = document.createElement("img");
						img.alt = "300x200";
						img.src = pro.pic;

						var h3 = document.createElement("h3");
						var foodmess = pro.name + "&nbsp;&nbsp;￥" + pro.price;
						h3.innerHTML = foodmess;
						h3.id = pro.id;
						var p1 = document.createElement("p");
						p1.innerText = pro.info;
						var p2 = document.createElement("p");
						var a = document.createElement("a");
						a.className = "btn btn-primary";
						var addtoCar = "addtoCar(" + pro.id + ")";
						a.setAttribute("onclick", addtoCar);
						a.innerHTML = "添加到购物车";
						p2.appendChild(a);
						var div1 = document.createElement("div");
						div1.className = "caption";
						div1.appendChild(h3);
						div1.appendChild(p1);
						div1.appendChild(p2);

						var div2 = document.createElement("div");
						div2.className = "thumbnail";
						div2.appendChild(img);
						div2.appendChild(div1);

						var li = document.createElement("li");
						li.className = "span4";
						li.appendChild(div2);
						ul.appendChild(li);
					}
					div0.appendChild(ul);
					/*
					 * <li class="span4"> <div class="thumbnail"> <img
					 * alt="300x200" src="../image/4.jpg" /> <div
					 * class="caption"> <h3>冯诺尔曼结构</h3> <p>
					 * 也称普林斯顿结构，是一种将程序指令存储器和数据存储器合并在一起的存储器结构。程序指令存储地址和数据存储地址指向同一个存储器的不同物理位置。
					 * </p> <p> <a class="btn btn-primary" href="#">添加购物车</a>
					 * </p> </div> </div> </li>
					 */
				}
				var allpage = message.allpage;
				var fen = document.getElementById("fenye");
				var first = document.createElement("li");
				var faf = document.createElement("a");
				faf.innerHTML = "首页";
				faf.href = "index.html?page=1";
				first.appendChild(faf);
				fen.appendChild(first);
				var back = document.createElement("li");
				var fafn = document.createElement("a");
				fafn.innerHTML = "上一页";
				if (page > 1) {
					fafn.href = "index.html?page=" + (page - 1);
				}
				back.appendChild(fafn);
				fen.appendChild(back);
				var begin = 1;
				var end = 10;
				if (allpage <= 10) {
					end = allpage;
				} else {
					begin = page - 5;
					end = page;
					for (var i = 0; i < 4; i++) {
						++end;
					}
					if (begin < 1) {
						begin = 1;
						end = 10;
					}
					if (end > allpage) {
						begin = allpage;
						for (var i = 0; i < 9; i++) {
							begin = begin - 1;
						}
						end = allpage;
					}
				}
				for (var i = begin; i <= end; i++) {
					var li = document.createElement("li");
					var a = document.createElement("a");
					a.innerHTML = i;
					if (i != page) {
						a.href = "index.html?page=" + i;
					}
					li.appendChild(a);
					fen.appendChild(li);
				}
				var next = document.createElement("li");
				var fafn = document.createElement("a");
				fafn.innerHTML = "下一页";
				if (page < allpage) {
					fafn.href = "index.html?page=" + (++page);
				}
				next.appendChild(fafn);
				fen.appendChild(next);

				var endd = document.createElement("li");
				var fafn = document.createElement("a");
				fafn.innerHTML = "尾页";
				if (page1 != allpage) {
					fafn.href = "index.html?page=" + allpage;
				}
				endd.appendChild(fafn);
				fen.appendChild(endd);

				/*
				 * <ul id="fenye"> <li><a href="index.html&page=1">首页</a></li>
				 * <li><a href="">上一页</a></li> <li><a href="">下一页</a></li>
				 * <li><a href="index.html&page=1">首页</a></li> </ul>
				 */
			} else {
				alert(message.msg);
			}
		}
	};
};