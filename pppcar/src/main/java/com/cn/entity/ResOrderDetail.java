package com.cn.entity;

import java.util.Set;

/**
 * 个人中心-我的订单-订单详情
 * @author chendongdong
 *
 */
public class ResOrderDetail{
	
	private Long id;//订单id
	
	private String orderNumber;//订单编号
	
	private String state;//订单状态
	
	private String payWay;//支付方式
	
	private String createTime;//创建时间
	
	private ResBsLogistics bsLogistics;//物流信息
	
	private String consignee;//收货人
	
	private String address;//收货地址
	
	private String mobileNumber;//收货人电话
	
	private Set<ResOrderList> orderList;//订单产品
	
	private Double dealPrice;//订单总额
	
	private ResInvoiceInfo resInvoiceInfo;//发票信息
	
	private Integer  totleIntegralDeduction;//计算出订单总的积分价格

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public ResBsLogistics getBsLogistics() {
		return bsLogistics;
	}

	public void setBsLogistics(ResBsLogistics bsLogistics) {
		this.bsLogistics = bsLogistics;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Double getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(Double dealPrice) {
		this.dealPrice = dealPrice;
	}

	public ResInvoiceInfo getResInvoiceInfo() {
		return resInvoiceInfo;
	}

	public void setResInvoiceInfo(ResInvoiceInfo resInvoiceInfo) {
		this.resInvoiceInfo = resInvoiceInfo;
	}

	public Set<ResOrderList> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<ResOrderList> orderList) {
		this.orderList = orderList;
	}

	public Integer getTotleIntegralDeduction() {
		if(orderList!=null&&orderList.size()>0){
			Integer IntegralDeduction=0;
			for(ResOrderList r: orderList){
				if(r.getIntegralDeduction()!=null){
					IntegralDeduction+=r.getIntegralDeduction();
				}
			}
			return IntegralDeduction;
		}
		return totleIntegralDeduction;
	}

	public void setTotleIntegralDeduction(Integer totleIntegralDeduction) {
		this.totleIntegralDeduction = totleIntegralDeduction;
	}
	
	
}
