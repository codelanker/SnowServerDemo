package io.netty.example.jdkSerialize.nettyExample;

import java.io.Serializable;

public class SubscribeResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int subReqId;
	public int respCode;
	public String desc;
	public SubscribeResp(int subReqId,int respCode,String desc) {
		this.subReqId = subReqId;
		this.respCode = respCode;
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "SubscribeResp["
				+ "subReqId="+subReqId
				+ ",respCode="+respCode
				+ ",desc="+desc
				+ "]";
	}

}
