function plusReady(){  
// 弹出系统选择按钮框  

//	外观拍照
	mui("body").on("tap","#camera",function(){ 
	    page2.imgUp();  
	})  
}  


var page2=null;  
page2={  
    imgUp:function(){  
        var m=this;  
        plus.nativeUI.actionSheet({cancel:"取消",buttons:[  
//	        {title:"拍照"},  
	        {title:"从相册中选择"}  
	    ]}, function(e){//1 是拍照  2 从相册中选择  
	        switch(e.index){  
//	            case 1:clickCamera2();break;  
	            case 1:clickGallery2();break;  
	        }  
	    });  
	}
}  


var pathsImg=[];


//外观图片从相册发送图片
function clickGallery2(){  
  plus.gallery.pick( function(path){  
        for(var i in path.files){  
//      	alert(e.files[i]);
//			yasuo(path.files[i]);
        	var imgObj={path:path.files[i]};
            pathsImg.push(imgObj);
            $('.imgbox').append("<div class='img-two'><img src="+path.files[i]+"/></div>");
//          alert(JSON.stringify(imgObj));
        }  
//			$('.camera2>img').attr('src','../my-img/cameraRed.png');
          
    }, function ( e ) {  
        console.log( "取消选择图片" );  
    },{  
        filter: "image",  
        multiple: true,  
        maximum: 10,  
        system: false,  
        onmaxed: function() {  
            plus.nativeUI.alert('最多只能选择10张图片');  
        }  
    });  
}


//压缩方法
function yasuo(path){
//	alert(JSON.stringify(path));
	plus.zip.compressImage({  
            src: path,  
            dst: path,  
            quality: 100,  
            overwrite: true,
            width: '600px'
          
//          alert(imgSrc);
        })
}

      

if(window.plus){  
    plusReady();  
}else{  
    document.addEventListener("plusready",plusReady,false);  
}

