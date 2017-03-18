$(function() {
	$('#camera').photobooth().on("image", function(event, dataUrl) {
		var timestamp = getRandom();// 生成唯一id
		var num = mathRand();
		createTag(timestamp, num, dataUrl);
	});

});

// 获取时间戳
function getRandom() {
	var timestamp = Date.parse(new Date());
	return timestamp;
}
function mathRand() {
	var num = "";
	for ( var i = 0; i < 6; i++) {
		num += Math.floor(Math.random() * 10);
	}
	return num;

}

// 拍照生成图片的位置
function createTag(timestamp, num, dataUrl) {
	var div = document.createElement("div");
	var input = document.createElement("input");
	var button = document.createElement("input");
	var divTag = document.getElementById("gallery");

	div.setAttribute("id", timestamp + num);
	div.setAttribute("style", "margin-top: 0px;margin-bottom: 0px;");
	input.setAttribute("id", timestamp + num);
	input.setAttribute("value", dataUrl);
	input.setAttribute("name", "picture");
	input.setAttribute("type", "hidden");
	button.setAttribute("type", "button");
	button.setAttribute("class", "button ml10");
	button.setAttribute("value", "删除图片");

	divTag.appendChild(div);
	divTag.appendChild(input);
	divTag.appendChild(button);

	button.onclick = function() {
		divTag.removeChild(div);
		divTag.removeChild(input);
		divTag.removeChild(button);
	};
	var id = "#" + timestamp + num;
	$(id).show().html('<img src="' + dataUrl + '" >');
}

/********************
*基本描述：
* 图片缩略,使用js的静态类实现
* by 西楼冷月 20080817 www.chinacms.org | www.xilou.net | qq : 39949376
*参数说明：
* @ im : 可以为image对象或img的id
*基本功能:
* Img(im).Resize(nWidth,nHeight)             : 按给定的宽和高进行智能缩小
* Img(im).ResizedByWH(nWidth,nHeight)        : 按给定的宽和高进行固定缩小(会出现图片变形情况)
* Img(im).ResizedByWidth(nWidth)             : 按给定的宽进行等比例缩小
* Img(im).ResizedByHeight(nHeight)           : 按给定的高进行等比例缩小
* Img(im).ResizedByPer(nWidthPer,nHeightPer) : 宽和高按百分比缩小
*使用例子:
* img id="demo" src="demo.gif" width="191" height="143" onload="Img(this).Resize(200,500);" /
* img id="demo" src="demo.gif" width="191" height="143" onload="Img('demo').ResizedByPer(200,500);" /
********************/
function Img(im)
{
    ImgCls.Obj = ( im && typeof im == 'object' ) ? im : document.getElementById(im) ;
    return ImgCls ;
}
var ImgCls =
{
    Obj : null ,
    
    //按给定的宽和高进行智能缩小
    Resize : function ( nWidth , nHeight )
    {
        var w , h , p1 , p2 ;
        //计算宽和高的比例
        p1 = nWidth / nHeight ;
        p2 = ImgCls.Obj.width / ImgCls.Obj.height ;
        w = 0 ; h = 0 ;
        if( p1>p2 )
        {
            //按宽度来计算新图片的宽和高
            w = nWidth ;
            h = nWidth * ( 1 / p2 ) ;
        }
        else
        {
            //按高度来计算新图片的宽和高
            h = nHeight ;
            w = nHeight * p2 ;
        }
        ImgCls.Obj.width  = w ;
        ImgCls.Obj.height = h ;
    },
    
    //按给定的宽和高进行固定缩小(会出现图片变形情况)
    //http://bizhi.knowsky.com/
    ResizedByWH : function ( nWidth , nHeight )
    {
        ImgCls.Obj.width  = nWidth ;
        ImgCls.Obj.height = nHeight ;
    },
    
    //按给定的宽进行等比例缩小
    ResizedByWidth : function ( nWidth )
    {
        var p = nWidth / ImgCls.Obj.width ;
        ImgCls.Obj.width  = nWidth ;
        ImgCls.Obj.height = parseInt ( ImgCls.Obj.height * p ) ;
    },
    
    //按给定的高进行等比例缩小
    ResizedByHeight : function ( nHeight )
    {
        var p = nHeight / ImgCls.Obj.height ;
        ImgCls.Obj.height  = nHeight ;
        ImgCls.Obj.width = parseInt ( ImgCls.Obj.width * p ) ;
    },
    
    //宽和高按百分比缩小
    ResizedByPer : function ( nWidthPer , nHeightPer )
    {
        ImgCls.Obj.width  = parseInt(ImgCls.Obj.width * nWidthPer / 100) ;
        ImgCls.Obj.height = parseInt(ImgCls.Obj.height * nHeightPer / 100) ;
    }
}

//显示缩略图
function DrawImage(ImgD,width_s,height_s){
	/*var width_s=139;
	var height_s=104;
	*/
	var image=new Image();
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		flag=true;
		if((image.width/image.height)>=(width_s/height_s)){
			if(image.width>width_s){
				ImgD.width=width_s;
				ImgD.height=(image.height*width_s)/image.width;
			}else{
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		}else{
			if(image.height>height_s){
				ImgD.height=height_s;
				ImgD.width=(image.width*height_s)/image.height;
			}else{
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		}
	}
/*else{
ImgD.src="";
ImgD.alt=""
}*/
}
