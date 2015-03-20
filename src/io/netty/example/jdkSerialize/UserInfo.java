package io.netty.example.jdkSerialize;

import java.io.Serializable;
import java.nio.ByteBuffer;


public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String userName;
	public int age;
	public UserInfo(String userName,int age) {
		this.userName = userName;
		this.age = age;
	}
	public byte[] codeC(){
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte[] userNameBytes = this.userName.getBytes();
		buffer.putInt(userNameBytes.length);
		buffer.put(userNameBytes);
		buffer.putInt(this.age);
		buffer.flip();
		userNameBytes = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
	}
	public byte[] codeC(ByteBuffer buffer){
		buffer.clear();
		byte[] userNameBytes = this.userName.getBytes();
		buffer.putInt(userNameBytes.length);
		buffer.put(userNameBytes);
		buffer.putInt(this.age);
		buffer.flip();
		userNameBytes = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
	}
}
