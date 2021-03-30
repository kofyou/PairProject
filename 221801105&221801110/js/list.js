window.onload = function(){
	var tr=document.getElementsByTagName("tr");
	for (var i= 0;i < tr.length;i ++){
		bgcChange(tr[i]);
	}
}

//鼠标悬停事件
function bgcChange(obj){
	obj.onmouseover=function(){
		obj.style.backgroundColor="#CCC";
	}
	obj.onmouseout=function(){
		obj.style.backgroundColor="#fff";
	}
}

//创建删除函数
function remove(obj){
	var hobby=obj.parentNode.parentNode;
	hobby.parentNode.removeChild(hobby);
}