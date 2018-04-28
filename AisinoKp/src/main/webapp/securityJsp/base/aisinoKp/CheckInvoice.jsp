<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	String type=request.getParameter("type");
	if (type == null) {
		type = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查看发票</title>
    
	
	
	<jsp:include page="../../../inc.jsp"></jsp:include>
	<script type="text/javascript">
	/* function showPdf(pdfBase64) {
        var container = document.getElementById("container");
        container.style.display = "block";
        var url = convertDataURIToBinary(pdfBase64);//encodeBase64是后台传递的Base64编码的二进制字符串

        PDFJS.workerSrc = 'js/pdf.worker.js';
        PDFJS.getDocument(url).then(function getPdfHelloWorld(pdf) {
           var $pop = $('#pop');
            var shownPageCount = pdf.numPages                
var getPageAndRender = function (pageNumber) {
            pdf.getPage(pageNumber).then(function getPageHelloWorld(page) {
                     var scale = 2;
                     var viewport = page.getViewport(scale);
                     var $canvas = $('<canvas></canvas>').attr({
                         'height': viewport.height,
                         'width': viewport.width
                     });
                     $pop.append($canvas);
                     
                     page.render({//设置渲染方式
                        canvasContext: $canvas[0].getContext('2d'),//二维绘图环境
                         viewport: viewport
                     });
                 });
              if (pageNumber < shownPageCount) {
                     pageNumber++;
                     getPageAndRender(pageNumber);
                 }  
            };
            getPageAndRender(1);            
        });
        
    }
function convertDataURIToBinary(dataURI) { //将encodeBase64解码
		console.log(dataURI);
       // var raw = window.atob(dataURI.replace(" ",""));// 进行解码
      var raw = decodeURIComponent(escape(atob(dataURI.substring(dataURI.indexOf(',') + 1))));
        var rawLength = raw.length;
        //转换成pdf.js能直接解析的Uint8Array类型,见pdf.js-4068
        var array = new Uint8Array(new ArrayBuffer(rawLength));  
        for(i = 0; i < rawLength; i++) {
          array[i] = raw.charCodeAt(i); //charCodeAt() 方法可返回指定位置的字符的 Unicode 编码。
        }
        return array;
     } */
	$(function(){
		var id = $('#id').val();
		if (id.length > 0) {
			$.post(sy.contextPath + '/base/fpmng/e-invoice!checkOrder.sy', {id : id,type:$("#type").val()} ,
					function(result) {
					if(result.success){
						var src="data:application/pdf;base64,"+result.obj;
						$("#iframe").attr('src',src);
					}else{
						$.messager.alert('提示',
								result.msg,
								'info');
					}
			},'json');
		}
	});
	</script>
  </head>
  
  <body>
  <input type="hidden" id="id" name="id" class="transinput" value="<%=id%>" readonly="readonly" />
  <input type="hidden" id="type" name="type" class="transinput" value="<%=type%>" readonly="readonly">
  	<!-- <div id="container" style="display: none;">
        <div id="pop" class="pop">
        </div>
	</div> -->
	<iframe id="iframe" src="" width="750px" height="540px" ></iframe>
  </body>
</html>
