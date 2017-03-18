var frm = frm || {};

/**
 * 去字符串空格
 * 
 * @author linux liu
 */
frm.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, '');
};
frm.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
};
frm.rtrim = function(str) {
	return str.replace(/(\s*$)/g, '');
};

/**
 * 判断开始字符是否是XX
 * 
 * @author linux liu
 */
frm.startWith = function(source, str) {
	var reg = new RegExp("^" + str);
	return reg.test(source);
};
/**
 * 判断结束字符是否是XX
 * 
 * @author linux liu
 */
frm.endWith = function(source, str) {
	var reg = new RegExp(str + "$");
	return reg.test(source);
};

/**
 * iframe自适应高度
 * 
 * @author linux liu
 * 
 * @param iframe
 */
frm.autoIframeHeight = function(iframe) {
	iframe.style.height = iframe.contentWindow.document.body.scrollHeight + "px";
};

/**
 * 设置iframe高度
 * 
 * @author linux liu
 * 
 * @param iframe
 */
frm.setIframeHeight = function(iframe, height) {
	iframe.height = height;
};