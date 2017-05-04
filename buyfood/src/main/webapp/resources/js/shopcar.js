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
				
				
			}else{
				alert(message.msg);
			}
		}
	};
};