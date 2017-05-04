function createXMLHttpRequest(){
	try{
		return new XMLHttpRequest();
	}catch(e){
		try{
			return new ActvieXobject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				return new ActvieXObject("Microsoft.XMLHTTP");
			}catch(e){
				alert("无法识别浏览器");
				throw e;
			}
		}
	}
}