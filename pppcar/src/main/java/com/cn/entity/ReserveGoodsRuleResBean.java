package com.cn.entity;

public class ReserveGoodsRuleResBean {
	/**
	 * 方案描述
	 */
	private String ruleDesc;
	/**
	 * 方案id
	 */
	private long ruleId;
	public String getRuleDesc() {
		return ruleDesc;
	}
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	public long getRuleId() {
		return ruleId;
	}
	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}

	@Override
	public String toString() {
		if (ruleDesc!=null){
			return ruleDesc;
		}
		return "";
	}
}
