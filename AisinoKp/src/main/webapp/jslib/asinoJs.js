//将undefined转换为""
function undefinedToString(s){
	if (typeof(s) == "undefined")
	{
	    return "";
	}else{
		return s;
	}
}




//js统计Table单元格实际行列 不用rowSpan colSpan，而用offsetLeft
function containsArray(array, obj) {  
    for (var i = 0; i < array.length; i++) {  
        if (array[i] == obj) {  
            return i;  
            break;  
        }  
    }  
    return -1;  
}  
  
Array.prototype.contains = function(obj) {  
    return containsArray(this, obj);  
}
function PrintTableToExcel(tableId) {  
	  
    var offsetLeftArray = new Array();  
    var cell;// 单元格Dom  
    var col;// 单元格实际所在列  
    var cellStr;// 每个cell以row,col,rowSpan,colSpan,value形式  
    var cellStrArray = [];  
    var objTab = document.getElementById(tableId);  
  
    // 遍历第一次取出offsetLeft集合  
    for (var i = 0; i < objTab.rows.length; i++) {  
        for (var j = 0; j < objTab.rows[i].cells.length; j++) {  
            cell = objTab.rows[i].cells[j];  
            if (offsetLeftArray.contains(cell.offsetLeft) == -1)  
                offsetLeftArray.push(cell.offsetLeft);  
        }  
    }  
  
    offsetLeftArray.sort(function(x, y) { return parseInt(x) - parseInt(y); });  
  
    // 遍历第二次生成cellStrArray  
    for (var i = 0; i < objTab.rows.length; i++) {  
        for (var j = 0; j < objTab.rows[i].cells.length; j++) {  
            cell = objTab.rows[i].cells[j];  
            col = offsetLeftArray.contains(cell.offsetLeft);  
            cellStr = i + ',' + col + ',' + cell.rowSpan + ',' + cell.colSpan ;  
            cellStrArray.push(cellStr);  
        }  
    } 
    var str = "";  
    str += cellStrArray.join('+');  
    return str;
  
} 

//js替换字符串中所有符合要求的目标
//str是目标字符串 
//reallyDo是替换谁 
//replaceWith是替换成什么。 
function replaceAllstr(str,reallyDo,replaceWith) { 
var e=new RegExp(reallyDo,"g"); 
words = str.replace(e, replaceWith); 
return words; 
} 
//获取当前日期
function getCurrentDate(){
	var d = new Date().pattern("yyyy-MM-dd");
	return d;
}

Date.prototype.pattern=function(fmt) {     
    var o = {     
    "M+" : this.getMonth()+1, //月份     
    "d+" : this.getDate(), //日     
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时     
    "H+" : this.getHours(), //小时     
    "m+" : this.getMinutes(), //分     
    "s+" : this.getSeconds(), //秒     
    "q+" : Math.floor((this.getMonth()+3)/3), //季度     
    "S" : this.getMilliseconds() //毫秒     
    };     
    var week = {     
    "0" : "/u65e5",     
    "1" : "/u4e00",     
    "2" : "/u4e8c",     
    "3" : "/u4e09",     
    "4" : "/u56db",     
    "5" : "/u4e94",     
    "6" : "/u516d"    
    };     
    if(/(y+)/.test(fmt)){     
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));     
    }     
    if(/(E+)/.test(fmt)){     
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);     
    }     
    for(var k in o){     
        if(new RegExp("("+ k +")").test(fmt)){     
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));     
        }     
    }     
    return fmt;     
};   
//自定义map
function Map() {     
    /** 存放键的数组(遍历用到) */    
    this.keys = new Array();     
    /** 存放数据 */    
    this.data = new Object();     
         
    /**   
     * 放入一个键值对   
     * @param {String} key   
     * @param {Object} value   
     */    
    this.put = function(key, value) {     
        if(this.data[key] == null){     
            this.keys.push(key);     
        }     
        this.data[key] = value;     
    };     
         
    /**   
     * 获取某键对应的值   
     * @param {String} key   
     * @return {Object} value   
     */    
    this.get = function(key) {     
        return this.data[key];     
    };     
         
    /**   
     * 删除一个键值对   
     * @param {String} key   
     */    
    this.remove = function(key) {     
        this.keys.remove(key);     
        this.data[key] = null;     
    };     
         
    /**   
     * 遍历Map,执行处理函数   
     *    
     * @param {Function} 回调函数 function(key,value,index){..}   
     */    
    this.each = function(fn){     
        if(typeof fn != 'function'){     
            return;     
        }     
        var len = this.keys.length;     
        for(var i=0;i<len;i++){     
            var k = this.keys[i];     
            fn(k,this.data[k],i);     
        }     
    };     
         
    /**   
     * 获取键值数组(类似Java的entrySet())   
     * @return 键值对象{key,value}的数组   
     */    
    this.entrys = function() {     
        var len = this.keys.length;     
        var entrys = new Array(len);     
        for (var i = 0; i < len; i++) {     
            entrys[i] = {     
                key : this.keys[i],     
                value : this.data[i]     
            };     
        }     
        return entrys;     
    };     
         
    /**   
     * 判断Map是否为空   
     */    
    this.isEmpty = function() {     
        return this.keys.length == 0;     
    };     
         
    /**   
     * 获取键值对数量   
     */    
    this.size = function(){     
        return this.keys.length;     
    };     
         
    /**   
     * 重写toString    
     */    
    this.toString = function(){     
        var s = "{";     
        for(var i=0;i<this.keys.length;i++,s+=','){     
            var k = this.keys[i];     
            s += k+"="+this.data[k];     
        }     
        s+="}";     
        return s;     
    };     
}

//处理中文符号乱码
function decodeHtml(str){
	if(str){
	str=str.replace(/&lt;/g, "<");
	str=str.replace(/&gt;/g, ">");
	str=str.replace(/&rsquo;/g, "'");
	str=str.replace(/&lsquo;/g, "'");
	str=str.replace(/&ldquo;/g, '"');
	str=str.replace(/&rdquo;/g, '"');
	str=str.replace(/&quot;/g, '"');
	str=str.replace(/&#39;/g,"'");
	return str;
	}else{
		return "";
	}
}
//JS加法
function accAdd(arg1, arg2) {
	     var r1, r2, m, c;
	     try {
	         r1 = arg1.toString().split(".")[1].length;
	     }
	    catch (e) {
	         r1 = 0;
	     }
	    try {
	        r2 = arg2.toString().split(".")[1].length;
	     }
	     catch (e) {
	        r2 = 0;
	     }
	     c = Math.abs(r1 - r2);
	     m = Math.pow(10, Math.max(r1, r2));
	     if (c > 0) {
	         var cm = Math.pow(10, c);
	         if (r1 > r2) {
	             arg1 = Number(arg1.toString().replace(".", ""));
	             arg2 = Number(arg2.toString().replace(".", "")) * cm;
	         } else {
	             arg1 = Number(arg1.toString().replace(".", "")) * cm;
	             arg2 = Number(arg2.toString().replace(".", ""));
	         }
	     } else {
	         arg1 = Number(arg1.toString().replace(".", ""));
	        arg2 = Number(arg2.toString().replace(".", ""));
	     }
	     return (arg1 + arg2) / m;
	 }
//JS减法
function accSub(arg1, arg2) {
	      var r1, r2, m, n;
	      try {
	         r1 = arg1.toString().split(".")[1].length;
	     }
	     catch (e) {
	         r1 = 0;
	     }
	     try {
	         r2 = arg2.toString().split(".")[1].length;
	     }
	     catch (e) {
	         r2 = 0;
	     }
	     m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
	     n = (r1 >= r2) ? r1 : r2;
	     return ((arg1 * m - arg2 * m) / m).toFixed(n);
	 }
//JS乘法
function accMul(arg1, arg2) {
	      var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	      try {
	         m += s1.split(".")[1].length;
	     }
	     catch (e) {
	     }
	     try {
	         m += s2.split(".")[1].length;
	     }
	     catch (e) {
	     }
	     return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
	 }
//JS除法
function accDiv(arg1, arg2) {
	      var t1 = 0, t2 = 0, r1, r2;
	      try {
	         t1 = arg1.toString().split(".")[1].length;
	     }
	     catch (e) {
	     }
	     try {
	         t2 = arg2.toString().split(".")[1].length;
	     }
	     catch (e) {
	     }
	     with (Math) {
	         r1 = Number(arg1.toString().replace(".", ""));
	         r2 = Number(arg2.toString().replace(".", ""));
	         return (r1 / r2) * pow(10, t2 - t1);
	     }
	 }