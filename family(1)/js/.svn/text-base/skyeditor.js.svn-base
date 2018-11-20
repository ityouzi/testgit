
var skyeditor = {};
skyeditor.isfirst=true;
skyeditor.root = document.domain?"http://" + document.domain + "/plugin/skyeditor/":"";
skyeditor.uploadUrl = "/shop.php?m=upload&a=base64";
skyeditor.uploadVideo = "/shop.php?m=upload&a=UploadVideo";
skyeditor.colors=['#1ebdc0','#f8375b','#fd8f43','#a4ce3b','#35dab6','#199de1','#9581f3','#333','#8f8f94'];
skyeditor.$toolsItem=new Map();
skyeditor.html='<div style="height: 50px;"></div><div class="sky-editor-loading">上传中...</div><div class="sky-editor">	<div class="sky-editor-colors">			</div>	<div class="sky-editor-emojis">			</div>	<div class="sky-editor-tools">		</div>	<input type="file" id="sky-editor-file" name="upimg" multiple="multiple" style="display: none;" />	<input type="file" id="sky-editor-file-video" name="upimg" style="display: none;" /></div><div class="sky-editor-el-before"><i class="skyeditor-iconfont skyeditor-icon-el-before"></i> </div><div class=" sky-editor-el-after"><i class="skyeditor-iconfont skyeditor-icon-el-after"></i></div>';
skyeditor.skyUpload = function (upid, url, success, error, uploadProgress) {
    var vFD = new FormData();
    var f = document.getElementById(upid).files;
    $("#" + upid + "loading").show();
    for (var i = 0; i < f.length; i++) {
        vFD.append('upimg', document.getElementById(upid).files[i]);
        // create XMLHttpRequest object, adding few event listeners, and POSTing our data
        var oXHR = new XMLHttpRequest();
        oXHR.addEventListener('load', success, false);
        oXHR.addEventListener('error', error, false);
        if (uploadProgress != undefined) {
            oXHR.upload.addEventListener("progress", uploadProgress, false);
        }
        oXHR.open('POST', url);
        oXHR.send(vFD);

    }
}
skyeditor.loadCss=function($css){
	$("<link>")
	.attr({
		rel: "stylesheet",
		type: "text/css",
		href: skyeditor.root + $css
	}).appendTo("head");
}
skyeditor.init = function () {
   	if(skyeditor.isfirst){ 
		skyeditor.loadCss("css/skyeditor.css");
		if($(".sky-editor").length==0){
			$("body").append(skyeditor.html);
		}
		
		skyeditor.isfirst=false;
	} 
//  skyeditor.skyEmojis();   
//	skyeditor.setColors();
//  skyeditor.setTools();
    

}
 
skyeditor.setColors=function(){
	var html='';	
	for(var i in skyeditor.colors){
		html=html+'<div class="sky-editor-excute" style="background-color:'+skyeditor.colors[i]+';" data-role="ForeColor"></div>';
	}
	$(".sky-editor-colors").html(html);
}

skyeditor.tools=["h3","pic","color","B","video","emoji","center"];



$(function () {
    skyeditor.init();
    skyeditor.skyEl = $(".sky-editor-content");

    document.documentElement.addEventListener('touchstart', function (event) {
        if (event.touches.length > 1) {
            event.preventDefault();
        }
    }, false);
    $(document).on("click", ".sky-editor-emoji-toggle", function () {
        $(".sky-editor-emojis").toggle();
    })
    $(document).on("click", ".sky-editor-emoji", function () {
        var index = $(this).index() + 1;
        var indexTag = "";
        if (index > 9) {
            indexTag = "j_00" + index;
        } else {
            indexTag = "j_000" + index;
        }
        skyeditor.skyEl.append('<img src="' + skyeditor.root + 'images/jx2/' + indexTag + '.gif" />');
        $(".sky-editor-emojis").hide();
    })
    

    
    $(document).on("click", ".sky-editor-content", function () {
        if ($(".sky-editor-content div").length == 0) {
//          $(".sky-editor-content").prepend('<div style="color:#555;">&nbsp;</div>')
  			$(".sky-editor-content").prepend('<div>&nbsp;</div>')
        }
        skyeditor.skyEl = $(this).find("div").eq(0);

    })
   
    $(document).on("keyup", ".sky-editor-content", function (event) {
        
        if (event.keyCode == "13"){     	 
        	setTimeout(function(){
        		if(skyeditor.skyEl.next().length>0){
        			skyeditor.skyEl = skyeditor.skyEl.next();
        		}	
        	},60) 	
        	return false;
        }
        
        
    })

   
});
