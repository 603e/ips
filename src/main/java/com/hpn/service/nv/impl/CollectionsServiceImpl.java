package com.hpn.service.nv.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import zone.framework.service.impl.BaseServiceImpl;

import com.hpn.model.nv.CollectionsPO;
import com.hpn.service.nv.CollectionsServiceI;

/**
 * 客户管理
 * 
 * @author 刘领献
 * 
 */
@Service
public class CollectionsServiceImpl extends BaseServiceImpl<CollectionsPO> implements CollectionsServiceI {

	@Override
	public List<CollectionsPO> obtainCollectionses(double latitude,double longitude, int azimuth) {
		double raidus=0.001;
		String hql = new StringBuilder("select c from CollectionsPO c")
				.append(" WHERE c.latitude >").append(latitude).append(" - ").append(raidus)
				.append(" AND c.latitude < ").append(latitude).append(" + ").append(raidus)
				.append(" AND c.longitude >").append(longitude).append(" - ").append(raidus)
				.append(" AND c.longitude < ").append(longitude).append(" + ").append(raidus)
				.append(" ORDER BY ACOS( SIN((").append(latitude)
				.append(" * 3.1415) / 180) * SIN((c.latitude * 3.1415) / 180) + COS(( ").append(latitude)
				.append(" * 3.1415) / 180) * COS((c.latitude * 3.1415) / 180) * COS(( ").append(longitude)
				.append(" * 3.1415) / 180 - (c.longitude * 3.1415) / 180 )) * 6380 ASC ")
				.toString();
		List<CollectionsPO> pos = find(hql);
		return pos;
		}
}
