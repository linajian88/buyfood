function delNum(nid){
	var ele = document.getElementById(nid);
	var num = ele.innerText;
	if(num>=1){
		ele.innerHTML = (num-1);
	}
};

function addNum(nid,sun){
	var ele = document.getElementById(nid);
	var num = ele.innerText;
	if(num<sun){
		ele.innerHTML = (num+1);
	}
};

function addCar(nid,foodId){
	var ele = document.getElementById(nid);
	var foodNum = ele.innerText;
	var xmlHttp = createXMLHttpRequest();
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhostPath = curPath.substring(0,pos);
	var url1 = localhostPath+"接口地址";
	xmlHttp.open("POST",url1,true);
	var mess = "foodId="+foodId+"&foodNum="+foodNum;
	xmlHttp.send(mess);
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			var message = eval("("+xmlHttp.responseText+")");
			if(message.code==1){
				alert("添加购物车成功");
			}
		}
	};
	
};