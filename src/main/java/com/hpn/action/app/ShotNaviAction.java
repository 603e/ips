package com.hpn.action.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpn.model.nv.CollectionsPO;
import com.hpn.model.nv.ShotNaviPO;
import com.hpn.service.nv.ShotNaviServiceI;

import sun.misc.BASE64Decoder;
import zone.framework.action.BaseAction;
import zone.framework.util.base.ConfigUtil;
import zone.framework.util.base.ImageBase64Util;

/**
 * 客户管理
 * 
 * action访问地址是/hpn/nv/shotNavi.do
 * 
 * @author 刘领献
 * 
 */
@Namespace("/app/hpn")
@Action(value = "/shotNavi")
public class ShotNaviAction extends BaseAction<ShotNaviPO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(ShotNaviServiceI service) {
		this.service = service;
	}

	/**
	 * 获取导览当前位置的藏品信息
	 */
	synchronized public void findCollections() {
		try {
			if (data == null) {
				HttpServletRequest request = getRequest();
				request.setCharacterEncoding("utf-8");  
		        String macCode = request.getParameter("macCode");  
		        String operater = request.getParameter("operater");  
		        String photo = request.getParameter("photo");		        
		        try {  
		        	  
		            // 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密  
		            byte[] photoimg = new BASE64Decoder().decodeBuffer(photo);  
		            for (int i = 0; i < photoimg.length; ++i) {  
		                if (photoimg[i] < 0) {  
		                    // 调整异常数据  
		                    photoimg[i] += 256;  
		                }  
		            } 
		            // byte[] photoimg = Base64.decode(photo);//此处不能用Base64.decode（）方法解密，我调试时用此方法每次解密出的数据都比原数据大  所以用上面的函数进行解密，在网上直接拷贝的，花了好几个小时才找到这个错误（菜鸟不容易啊）  
		            System.out.println("图片的大小：" + photoimg.length);
		            
		        } catch (Exception e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }  
		        data = new ShotNaviPO();
		        data.setPhoto(photo);
		        data.setMacCode(macCode);
		        
			}
			//处理拍摄的客户的照片
			if(!StringUtils.isBlank(data.getPhoto())){
				String webPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() ;
				String webSrcPath = ConfigUtil.get("uploadImage");
				webPath = new StringBuilder(webPath).append("../../").append(webSrcPath).toString();
				String fileName = new StringBuilder(dateFormat.format(new Date())).append(".jpg").toString();
				ImageBase64Util.makeOriginalImg(data.getPhoto(),webPath, fileName);
				String contextPath =  getSession().getServletContext().getContextPath();
				data.setPhotoUrl(new StringBuilder(contextPath).append(webSrcPath).append(fileName).toString());				
			}
			HttpServletRequest request = getRequest();
			String imgUrl = new StringBuilder("http://").append(request.getServerName())// 服务器地址
					.append(":").append(request.getServerPort()) // 端口号
					.append(request.getContextPath()).append("/resources/uploadImg/d9b811f3c51d843.jpg").toString(); // 项目名称
			Set<CollectionsPO> collectionses = ((ShotNaviServiceI) service).saveShotNavi(data, imgUrl);
			data.setCollectionses(collectionses);
			writeJson(data);
		} catch (Exception e) { 
            e.printStackTrace();
        }		
	}
}
