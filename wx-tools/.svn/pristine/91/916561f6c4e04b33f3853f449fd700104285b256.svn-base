//获取计算 样式  显示样式
var getStyle = function(obj) {
	if(obj.currentStyle) { //说明current对象 存在  ie
		//ie
		return obj.currentStyle;
	} else {
		//fei ie
		return getComputedStyle(obj);

	}
};

//rem字体大小的方法
var rem = function() {
	var width = window.screen.width;
	var htmlDom = document.getElementsByTagName("html")[0];
	var font = 100 * width / 750;
	htmlDom.style.cssText = "font-size: " + font + "px";
};

//限制字符个数显示省略号
var elicpse = function(lengthNum) { //参数是限制的字数
	$('.elicpseName').each(function() {
		var maxwidth = lengthNum;
		if($(this).text().length > maxwidth) {
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '...');
		}
	});
};
//向上滚动方法
function gonggao() {
	var fns = {
		_up: function() {
			$(".gonggao>ul").animate({
				marginTop: "-0.3rem"
			}, 500, function() {
				$(".gonggao>ul>li:lt(1)").appendTo($(".gonggao>ul"));
				$(".gonggao>ul").css("marginTop", 0);
			});
		}
	};

	var autoplay = function() {
		autoup = setInterval(function() {
			fns._up();
		}, 2000);
	};
	autoplay();
};
rem();


//		


