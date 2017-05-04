window.onload = function(){
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0,pos);
	var url1 = localhostPath+"接口地址";
	xmlHttp.open("POST",url1,true);
	xmlHttp.send(null);
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			var message = eval("("+xmlHttp.responseText+")");
			if(message.code==1){
				var proArray = message.data;
				var div0 = document.getElementById("foodStart");
				for(var i=0;i<proArray.length;i++){
					var pro = proArray[i];
					
					var a1 = document.createElement("a");
					var img = document.createElement("img");
					img.setAttribute("src","返回的照片地址");
					img.setAttribute("alt","food或者换为食品名字");
					a1.appendChild(img);
					
					var a2 = document.createElement("a");
					var num = i%4+1;
					a2.className = "inner-overlay inner-overlay-"+num;
					a2.innerHTML = "食品的描述";
					var div1 = document.createElement("div");
					div1.className = "work-holder";
					div1.appendChild(a1);
					div1.appendChild(a2);
					
					var h = document.createElement("h3");
					var a3 = document.createElement("a");
					a3.id = "foodid"+"编号";
					a3.innerHTML = "食品的名称和价格";
					h.appendChild(a3);
					var span = document.createElement("span");
					span.innerHTML = "还剩"+"数量";
					var br1 = document.createElement("br");
					var button1 = document.createElement("button");
					button1.innerHTML = "减少";
					button1.id= "del"+i ;
					var a4 = document.createElement("a");
					a4.innerHTML = 0;
					a4.id = "foodnum"+i;
					var delNum = "delNum("+a4.id+")";
					button1.setAttribute("onclick",delNum);
					var button2 = document.createElement("button");
					button2.innerHTML = "增加";
					button2.id= "add"+i ;
					var addNum = "addNum("+a4.id+","+"商品总量"+")";
					button2.setAttribute("onclick",addNum);
					var br2 = document.createElement("br");
					var button3 = document.createElement("button");
					button3.innerHTML = "添加到购物车";
					button3.id="addcar"+i;
					var addCar = "addCar("+a4.id+","+"食品ID"+")";
					button2.setAttribute("onclick",addCar);
					var div2 = document.createElement("div");
					div2.className = "desc";
					div2.appendChild(h);
					div2.appendChild(span);
					div2.appendChild(br1);
					div2.appendChild(button1);
					div2.appendChild(a4);
					div2.appendChild(button2);
					div2.appendChild(br2);
					div2.appendChild(button3);
					
					var div3 = document.createElement("div");
					div3.className = "fh5co-grid-work";
					div3.appendChild(div1);
					div3.appendChild(div2);
					var div4 = document.createElement("div");
					div4.className = "col-md-4 col-sm-6";
					div4.appendChild(div3);
					div0.appendChild(div4);
					
/*				<div class="col-md-4 col-sm-6">
					<div class="fh5co-grid-work">
						<div class="work-holder">
							<a><img src="../resources/picture/portfolio_pic4.jpg" alt="food"></a>
							<a class="inner-overlay inner-overlay-3">食品描述</a>
						</div>
						<div class="desc">
							<h3><a href="#">商品名字 价格</a></h3>
							<span>剩余多少</span></br>
							<button>减少</button><a id="foodnum">0</a><button>增加</button></br>
							<button>添加购物车</button>
						</div>
					</div>
				</div>*/
				}
			}
			else{
				alert(message.msg);
			}
		}
	};
};