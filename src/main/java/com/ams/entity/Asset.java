package com.ams.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assetid;
	private String assetname;
	private String producttype;
	private String manufacturename;
	private String expirydate;
	private String status;

	@ManyToOne
	@JoinColumn(name = "empid")
	private Employee employee;

	public Asset() {
		super();

	}

	public Asset(Long assetid, String assetname, String producttype, String manufacturename, String expirydate,
			String status, Employee employee) {
		super();
		this.assetid = assetid;
		this.assetname = assetname;
		this.producttype = producttype;
		this.manufacturename = manufacturename;
		this.expirydate = expirydate;
		this.status = status;
		this.employee = employee;
	}

	public Long getAssetid() {
		return assetid;
	}

	public void setAssetid(Long assetid) {
		this.assetid = assetid;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getManufacturename() {
		return manufacturename;
	}

	public void setManufacturename(String manufacturename) {
		this.manufacturename = manufacturename;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
