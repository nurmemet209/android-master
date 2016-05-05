package com.cn.entity;





public class ReturnBean implements java.io.Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 5761386302316325878L;

	/** returnCode 返回码 */
	private String returnCode;
	
	/** ReturnMsg 错误描述 */
	private String returnMsg;

	/** data 返回值 */
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	@Override
	public String toString() {
		return "ReturnBean [returnCode=" + returnCode + ", returnMsg="
				+ returnMsg + ", data=" + data + "]";
	}



}
