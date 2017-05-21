package com.hpn.service.cr;

import java.util.List;

import com.hpn.model.cr.CustomerOnlinePO;

import zone.framework.service.BaseServiceI;
/**
 * 客户业务
 * 
 * @author 刘领献
 * 
 */
public interface CustomerOnlineServiceI extends BaseServiceI<CustomerOnlinePO> {	
	public CustomerOnlinePO findLastLoginCustomer (String customerId,String macCode);	
	public List<CustomerOnlinePO> findLoginCustomers (String customerId,String macCode);
}
