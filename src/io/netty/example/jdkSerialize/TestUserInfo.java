package io.netty.example.jdkSerialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {
	public static void main(String[] args) throws IOException {
		UserInfo info = new UserInfo("welcome to netty", 18);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(info);
		os.flush();
		os.close();
		byte[] b = out.toByteArray();
		System.out.println("The jdk serializable length is :" +b.length);
		out.close();
		System.out.println("---------------------------");
		System.out.println("The byte array serializable length is :"+info.codeC().length);
	}
}
