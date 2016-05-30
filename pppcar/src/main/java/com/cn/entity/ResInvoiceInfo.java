package com.cn.entity;


import java.io.Serializable;

public class ResInvoiceInfo implements Serializable{
	
	private long id;
	private String type;/*发票类型*/
	private String invoiceTitle;/*发票抬头，type为普通发票时*/
	private Integer isDefault;/*发票抬头，type为普通发票时*/
	/*以下是增值税发票信息*/
	private String companyName;/*单位名称*/
	private String code;/*纳税人识别码*/
	private String address;/*注册地址*/
	private String tel;/*注册电话*/
	private String bank;/*开户行*/
	private String bankAccount;/*银行账户*/
	private String takerName;/*收票人姓名*/
	private String takerPhone;/*收票人手机*/
	private String takerAddress;/*收票人地址*/
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getTakerName() {
		return takerName;
	}
	public void setTakerName(String takerName) {
		this.takerName = takerName;
	}
	public String getTakerPhone() {
		return takerPhone;
	}
	public void setTakerPhone(String takerPhone) {
		this.takerPhone = takerPhone;
	}
	public String getTakerAddress() {
		return takerAddress;
	}
	public void setTakerAddress(String takerAddress) {
		this.takerAddress = takerAddress;
	}


	@Override
	public boolean equals(Object o) {
		if (o instanceof ResInvoiceInfo){
			ResInvoiceInfo rif= (ResInvoiceInfo) o;
			if (rif.getId()==this.getId()){
				return true;
			}
		}
		return false;
	}
}
