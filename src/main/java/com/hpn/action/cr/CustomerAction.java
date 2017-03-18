package com.hpn.action.cr;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import zone.framework.action.BaseAction;
import zone.framework.model.easyui.Json;
import zone.framework.util.base.BeanUtils;
import zone.framework.util.base.HqlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpn.model.cr.CustomerPO;
import com.hpn.service.cr.CustomerServiceI;

/**
 * 客户管理
 * 
 * action访问地址是/hpn/cr/customer.do
 * 
 * @author 刘领献
 * 
 */
@Namespace("/hpn/cr")
@Action
public class CustomerAction extends BaseAction<CustomerPO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(CustomerServiceI service) {
		this.service = service;
	}

	/**
	 * 新建一个客户
	 */
	synchronized public void save() {
		Json json = new Json();
		if (data != null) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#idNumber_S_EQ", data.getIdNumber());
			hqlFilter.addFilter("QUERY_t#name_S_EQ", data.getName());
			CustomerPO customer = service.getByFilter(hqlFilter);
			if (customer != null) {
				json.setMsg("新建用户失败，用户名已存在！");
			} else {
				service.save(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 更新一个用户
	 */
	synchronized public void update() {
		Json json = new Json();
		json.setMsg("更新失败！");
		if (data != null && !StringUtils.isBlank(data.getId())) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#id_S_EQ", data.getId());
			//hqlFilter.addFilter("QUERY_t#deleteFlag_S_EQ", "0");
			CustomerPO customer = service.getByFilter(hqlFilter);
			if (customer == null) {
				json.setMsg("更新用户失败，该用户不存在！");
			} else {
				CustomerPO	 t = service.getById(data.getId());
				BeanUtils.copyNotNullProperties(data, t, new String[] { "updateDatetime" });
				service.update(t);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}
		}
		writeJson(json);
	}

}
