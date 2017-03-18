package com.hpn.model.cr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import zone.framework.model.base.Base;

@Entity
@Table(name = "hpn_customer", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CustomerPO extends Base implements Serializable{

	/**
	 * 
	 * 继成父类的 deleteFlag 作为作废标识 0：正常；1：作废
	 */
	private static final long serialVersionUID = 1L;
	//客户姓名
	private String name;
	//身份证件号码
	private String idNumber;
	//出生日期
	private Date birthday;
	//职业
	private String occupation;
	//性别
	private String sex;
	//客户照片
	private String photo;
	//身份证件地址
	private String idAddress;
	//居住地址
	private String address;
	//联系电话号码
	private String phoneNumber;
	//备用电话号码
	private String secondPhoneNumber;
	
	@Column(name = "idNumber", length = 20)
	public String getIdNumber() {
		return idNumber;
	}
	
	@Column(name = "name", length = 64)
	public String getName() {
		return name;
	}

	@Column(name = "sex", length = 1)
	public String getSex() {
		return sex;
	}
	
	@Column(name = "occupation", length = 16)
	public String getOccupation() {
		return occupation;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 8)
	public Date getBirthday() {
		return birthday;
	}

	@Column(name = "photo", length = 200)
	public String getPhoto() {
		return this.photo;
	}

	@Column(name = "idAddress", length = 16)
	public String getIdAddress() {
		return idAddress;
	}

	@Column(name = "address", length = 16)
	public String getAddress() {
		return address;
	}

	@Column(name = "phoneNumber", length = 16)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Column(name = "secondPhoneNumber", length = 16)
	public String getSecondPhoneNumber() {
		return secondPhoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSecondPhoneNumber(String secondPhoneNumber) {
		this.secondPhoneNumber = secondPhoneNumber;
	}
}
