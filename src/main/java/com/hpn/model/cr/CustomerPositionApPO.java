package com.hpn.model.cr;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "hpn_customerPositionAp", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CustomerPositionApPO extends CustomerPositionPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
