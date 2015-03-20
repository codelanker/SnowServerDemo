package io.netty.example.jdkSerialize.nettyExample;

import java.io.Serializable;

public class SubscribeReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int subReqId;
	public String userName;
	public String productName;
	public String phoneNumber;
	public String address;
	@Override
	public String toString() {
		return "SubscribeReq["
				+ "subReqId="+subReqId
				+ ",userName="+userName
				+ ",productName="+productName
				+ ",phoneNumber="+phoneNumber
				+ ",address="+address
				+ "]";
	}
}
